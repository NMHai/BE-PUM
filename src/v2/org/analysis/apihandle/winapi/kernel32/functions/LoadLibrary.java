/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: LoadLibrary.java
 * Created date: Feb 2, 2015
 * QC: 2015-02-02 Passed
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.util.Map.Entry;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;

/**
 * Loads the specified module into the address space of the calling process. The
 * specified module may cause other modules to be loaded.
 * 
 * @param lpFileName
 *            The name of the module. This can be either a library module (a
 *            .dll file) or an executable module (an .exe file). The name
 *            specified is the file name of the module and is not related to the
 *            name stored in the library module itself, as specified by the
 *            LIBRARY keyword in the module-definition (.def) file.
 * @return If the function succeeds, the return value is a handle to the module.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadLibrary extends Kernel32API {
	private static final String KNOWN_LIB_NAME[] = { "mapistub.dll", "msvcrt.dll", "msvbvm60.dll", "msvcr110.dll"};
	private static int generatedLibCount = 0;

	private HMODULE apiCallReturn = null;

	public LoadLibrary() {
		super();
		NUM_OF_PARMS = 1;
	}

	private static boolean isKnownLib(String libName) {
		libName = libName.toLowerCase();

		for (String lib : KNOWN_LIB_NAME) {
			if (lib.equals(libName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute() {

		String libraryName = memory.getText(this, this.params.get(0));
		long value = execute(libraryName);

		register.mov("eax", new LongValue(value));

		value = ((LongValue) register.getRegisterValue("eax")).getValue();
	}

	public long execute(String libraryName) {
		System.out.println(" Library Name:" + libraryName);
		
		long value = 0;
		boolean isGen = false;

		// Get from cache
		for (Entry<Long, PairEntry<String, Integer>> entry : APIHandle.libraryHandleMap.entrySet()) {
			if (entry.getValue().getKey().equals(libraryName)) {
				return entry.getKey();
			}
		}

		// If cache fail, call JNA to get that
		LoadLibThread thread = new LoadLibThread(libraryName);
		try {
			thread.start();
			Thread.sleep(100);
			if (this.apiCallReturn == null) {
				Thread.sleep(1000);
			}
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Fail again? Maybe the system has not been installed some needed
		// framework
		if (this.apiCallReturn == null && isKnownLib(libraryName)) {
			// 4000 APIs can be attached to this DLL
			value = APIHandle.BASE_LIB_HANDLE + (4000 * generatedLibCount++);
			isGen = true;
		} else {
			value = (this.apiCallReturn == null) ? 0 : Pointer.nativeValue(apiCallReturn.getPointer());
			isGen = false;
		}

		// Store all the handle value for each library
		if (value != 0) {
			APIHandle.libraryHandleMap.put(value, new PairEntry<String, Integer>(libraryName, ((isGen) ? 0 : -1)));
		} else {
			value = APIHandle.BASE_LIB_HANDLE + (4000 * generatedLibCount++);
			APIHandle.libraryHandleMap.put(value, new PairEntry<String, Integer>(libraryName, 0));
		}

		return value;
	}

	class LoadLibThread extends Thread {
		private String libName = null;

		public LoadLibThread(String lib) {
			this.libName = lib;
		}

		@Override
		public void run() {
			apiCallReturn = Kernel32DLL.INSTANCE.LoadLibrary(this.libName);
		}
	}
}
