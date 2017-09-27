/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinBase.FILETIME;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the current system date and time. The information is in Coordinated
 * Universal Time (UTC) format.
 * 
 * @param lpSystemTimeAsFileTime
 *            A pointer to a FILETIME structure to receive the current system
 *            date and time in UTC format.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemTimeAsFileTime extends Kernel32API {

	public GetSystemTimeAsFileTime() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		FILETIME lpSystemTimeAsFileTime = new FILETIME();
		Kernel32DLL.INSTANCE.GetSystemTimeAsFileTime(lpSystemTimeAsFileTime);

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(
				lpSystemTimeAsFileTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 + 4), new LongValue(
				lpSystemTimeAsFileTime.dwHighDateTime));
	}

}
