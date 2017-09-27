/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetProcessId.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetProcessId function retrieves the process identifier of the specified
 * process.
 * 
 * @param process
 *            Handle to the process. The handle must have the
 *            PROCESS_QUERY_INFORMATION access right.
 * 
 * @return If the function succeeds, the return value is the process identifier
 *         of the specified process. If the function fails, the return value is
 *         zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetProcessId extends Kernel32API {

	public GetProcessId() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);

		HANDLE process = new HANDLE(new Pointer(t));
		int ret = Kernel32.INSTANCE.GetProcessId(process);

		register.mov("eax", new LongValue(ret));
	}

}
