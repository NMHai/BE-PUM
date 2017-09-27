/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemTime.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetSystemTime function retrieves the current system date and time. The
 * system time is expressed in Coordinated Universal Time (UTC).
 * 
 * @param lpSystemTime
 *            Pointer to a SYSTEMTIME structure to receive the current system
 *            date and time.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemTime extends Kernel32API {

	/**
	 * 
	 */
	public GetSystemTime() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);

		SYSTEMTIME lpSystemTime = new SYSTEMTIME();
		Kernel32.INSTANCE.GetSystemTime(lpSystemTime);

		// typedef unsigned short WORD;
		// It just use 2 bytes of memory
		memory.setWordMemoryValue(t, new LongValue(lpSystemTime.wYear));
		memory.setWordMemoryValue(t + 2, new LongValue(lpSystemTime.wMonth));
		memory.setWordMemoryValue(t + 4, new LongValue(lpSystemTime.wDayOfWeek));
		memory.setWordMemoryValue(t + 6, new LongValue(lpSystemTime.wDay));
		memory.setWordMemoryValue(t + 8, new LongValue(lpSystemTime.wHour));
		memory.setWordMemoryValue(t + 10, new LongValue(lpSystemTime.wMinute));
		memory.setWordMemoryValue(t + 12, new LongValue(lpSystemTime.wSecond));
		memory.setWordMemoryValue(t + 14, new LongValue(lpSystemTime.wMilliseconds));
	}

}
