/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi
 * File name: APIClass.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.interactive.AnalysisModel;
import v2.org.analysis.interactive.IInteractive;
import v2.org.analysis.json.JSONManager;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.analysis.SymbolicValue;
import v2.org.analysis.value.SymbolValue;

/**
 * 
 * The stub abstract class is used for describe some primary attributes &
 * methods
 * 
 * @author Yen Nguyen
 * @version 0.2
 */
public abstract class API {
//	private final static boolean IS_ALWAYS_RETURN_SYMBOLIC = true;
	private static HashSet<String> unsafeAPISet = null;
	static {
		String path = "/" + API.class.getPackage().getName().replace(".", "/") + "/UnsafeAPIs.json";
		InputStream stream = API.class.getResourceAsStream(path);

		unsafeAPISet = new Gson().fromJson(new InputStreamReader(stream), 
				(new TypeToken<HashSet<String>>() {}).getType());
	}

	private boolean isSymbolArg = false;
	
	protected int NUM_OF_PARMS = 0;
	protected boolean IS_POP_STACK_VALUE = true; 

	protected String libraryName;
	protected String apiName;

	protected BPState curState = null;
	protected Stack stack = null;
	protected MemoryV2 memory = null;
	protected Register register = null;

	protected List<Long> params = null;

	public String getLibraryName() {
		return this.libraryName;
	}

	public String getAPIName() {
		return this.apiName;
	}

	public String getFullName() {
		return String.format("%s@%s.DLL", this.getAPIName(), this.getLibraryName());
	}

	public boolean is64bit() {
		if (this.apiName.charAt(this.apiName.length() - 1) == 'W') {
			return true;
		}
		return false;
	}

	protected void initAttributes() throws APIException {
		Environment env = curState.getEnvironement();
		this.stack = env.getStack();
		this.memory = env.getMemory();
		this.register = env.getRegister();

		if (NUM_OF_PARMS > 0) {
			this.params = new ArrayList<>();

			System.out.print("Argument:");

			for (int i = 0; i < NUM_OF_PARMS; i++) {
				Value value = stack.pop();

				if (value instanceof LongValue) {
					this.params.add(((LongValue) value).getValue());
				} else {
					isSymbolArg = true;					
				}
				System.out.print(String.format("\tx%d: %s\t", i + 1, value.toString()));				
			}
			System.out.println();
			if (isSymbolArg) {
				throw new APIException(this);
			}
			
			if (!IS_POP_STACK_VALUE) {
				for (int i = this.params.size(); i > 0; i--) {
					stack.push(new LongValue(this.params.get(i - 1)));
				}
			}
		}		
	}

	public void run(AbsoluteAddress address, BPState curState, Instruction inst, String apiName) throws APIException {
		this.apiName = apiName;
		this.curState = curState;
		this.initAttributes();

		IInteractive analysis = AnalysisModel.getInstance().getAnalysis();
		Value valInt = null;
		if (analysis != null) {
			valInt = analysis.onAPI(this.curState, address.getValue(), inst, apiName, params);
		}

		// Check whether this API is unsafe or not.
		if (unsafeAPISet.contains(apiName) || 
				unsafeAPISet.contains(apiName.toLowerCase())) {
			// If it is unsafe, run it by the extraordinary way.
			APIThread seperatedAPIthread = new APIThread();
			try {
				seperatedAPIthread.start();
				Thread.sleep(200);
				
				if (seperatedAPIthread.isAlive()) {
					// It is still alive, try to wait a little bit more
					Thread.sleep(2000);
					// We have run out of mercy, let kill it
					System.out.println("Interrup executing unsafe API " + this.apiName);
					seperatedAPIthread.interrupt();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Otherwise, just execute it
			if (valInt == null) {
				this.execute();
			}
			
			// Insert a behaviour object in JSON file
			Value ret = valInt != null ? valInt : register.getRegisterValue("eax");
			if (ret instanceof SymbolValue) {
				register.setRegisterValue("eax", ret);
			}
			if (ret != null && ret instanceof LongValue) {
				JSONManager.getInstance().addAPIBehaviour(apiName, ((LongValue)ret).getValue(), this.params);
			} else {
				JSONManager.getInstance().addAPIBehaviour(apiName, 0, this.params);
			}
		}
		
//		if (!IS_ALWAYS_RETURN_SYMBOLIC) {
//			this.execute();
//		} else {
//			register.setRegisterValue("eax", new SymbolValue("api_eax_" + apiName));
//		}
	}

	public abstract void execute();
	
	/**
	 * Executing JNA on another thread and wait it to join to main thread.
	 * If timeout is reached, kill it.
	 * 
	 * @author Yen Nguyen
	 */
	class APIThread extends Thread {

		public APIThread() {
		}

		@Override
		public void run() {
			API.this.execute();
		}
	}
}
