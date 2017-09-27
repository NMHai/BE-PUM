/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetProcessHeap.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves a handle to the default heap of the calling process. This handle
 * can then be used in subsequent calls to the heap functions.
 * 
 * @return If the function succeeds, the return value is a handle to the calling
 *         process's heap.
 * 
 * @author Yen Nguyen
 *
 */
public class GetProcessHeap extends Kernel32API {

	public GetProcessHeap() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// This function has no parameters.
		HANDLE ret = Kernel32DLL.INSTANCE.GetProcessHeap();
		// System.out.println("Last Error:" + verNum);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
