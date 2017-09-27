/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetTickCount.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * The GetTickCount function retrieves the number of milliseconds that have
 * elapsed since the system was started, up to 49.7 days.
 * 
 * @return Number of milliseconds that have elapsed since the system was
 *         started.
 * 
 * @author Yen Nguyen
 *
 */
public class GetTickCount extends Kernel32API {

	public GetTickCount() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {

		// Add duration that had been minus from original sleep time
		int ret = Kernel32.INSTANCE.GetTickCount() + Sleep.offset;
		register.mov("eax", new LongValue(ret));
	}

}
