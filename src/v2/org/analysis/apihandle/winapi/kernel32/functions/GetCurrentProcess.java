/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentProcess.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * 
 * This function returns a pseudohandle for the current process.
 * 
 * @return The return value is a pseudohandle to the current process.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentProcess extends Kernel32API {

	/**
	 * Constructor
	 */
	public GetCurrentProcess() {
		super();
		NUM_OF_PARMS = 0;
	}


	@Override
	public void execute() {

		// Call the real windows API
		HANDLE handle = Kernel32.INSTANCE.GetCurrentProcess();
		long value = Pointer.nativeValue(handle.getPointer());

		System.out.println("Return Value: " + value);

		// Store
		register.mov("eax", new LongValue(value));
	}

}
