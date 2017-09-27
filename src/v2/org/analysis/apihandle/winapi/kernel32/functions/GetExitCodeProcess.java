/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetExitCodeProcess.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the termination status of the specified process.
 * 
 * @param hProcess
 *            A handle to the process.
 * 
 * @param lpExitCode
 *            A pointer to a variable to receive the process termination status.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetExitCodeProcess extends Kernel32API {
	public GetExitCodeProcess() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hProcess = new HANDLE(new Pointer(t1));
		IntByReference lpExitCode = new IntByReference();
		boolean ret = Kernel32.INSTANCE.GetExitCodeProcess(hProcess, lpExitCode);

		register.mov("eax", new LongValue(ret ? 1 : 0));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpExitCode.getValue()));
	}

}
