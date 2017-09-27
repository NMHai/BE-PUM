/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentProcess.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.Program;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves a pseudo handle for the calling thread.
 * 
 * @return This function has no parameters.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentThread extends Kernel32API {

	/**
	 * Constructor
	 */
	public GetCurrentThread() {
		super();
		NUM_OF_PARMS = 0;
	}


	@Override
	public void execute() {
		// Prepare environment
		Environment env = curState.getEnvironement();
		Register register = env.getRegister();
		Program program = Program.getProgram();

		HANDLE handle = Kernel32.INSTANCE.GetCurrentThread();
		long value = Pointer.nativeValue(handle.getPointer());

		program.generageCFG(program.getAbsolutePathFile() + "_test");

		// Store
		register.mov("eax", new LongValue(value));
	}

}
