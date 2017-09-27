/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetProcessVersion.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetProcessVersion function retrieves the major and minor version numbers
 * of the system on which the specified process expects to run.
 * 
 * @param processId
 *            Process identifier of the process of interest. A value of zero
 *            specifies the calling process.
 * 
 * @return If the function succeeds, the return value is the version of the
 *         system on which the process expects to run. The high word of the
 *         return value contains the major version number. The low word of the
 *         return value contains the minor version number. If the function
 *         fails, the return value is zero. To get extended error information,
 *         call GetLastError. The function fails if ProcessId is an invalid
 *         value.
 * 
 * @author Yen Nguyen
 *
 */
public class GetProcessVersion extends Kernel32API {

	/**
	 * 
	 */
	public GetProcessVersion() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);

		int processId = (int) t;
		int ret = Kernel32.INSTANCE.GetProcessVersion(processId);

		register.mov("eax", new LongValue(ret));
	}

}
