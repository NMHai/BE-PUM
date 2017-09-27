/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SystemTimeToFileTime.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.value.LongValue;

/**
 * Converts a system time to file time format. System time is based on
 * Coordinated Universal Time (UTC).
 * 
 * @param lpSystemTime
 *            A pointer to a SYSTEMTIME structure that contains the system time
 *            to be converted from UTC to file time format.
 * 
 * @param lpFileTime
 *            A pointer to a FILETIME structure to receive the converted system
 *            time.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SystemTimeToFileTime extends Kernel32API {

	public SystemTimeToFileTime() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		SYSTEMTIME lpSystemTime = null;
		if (t1 != 0L) {
			lpSystemTime = new SYSTEMTIME();
			lpSystemTime.wYear = (short) ((LongValue) memory
					.getWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1))).getValue();
			lpSystemTime.wMonth = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t1 += 2))).getValue();
			lpSystemTime.wDayOfWeek = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t1 += 2))).getValue();
			lpSystemTime.wDay = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t1 += 2))).getValue();
			lpSystemTime.wHour = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t1 += 2))).getValue();
			lpSystemTime.wMinute = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t1 += 2))).getValue();
			lpSystemTime.wSecond = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t1 += 2))).getValue();
			lpSystemTime.wMilliseconds = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t1 += 2))).getValue();
		}
		FILETIME lpFileTime = new FILETIME();

		BOOL ret = Kernel32DLL.INSTANCE.SystemTimeToFileTime(lpSystemTime, lpFileTime);
		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
				lpFileTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4), new LongValue(
				lpFileTime.dwHighDateTime));
	}

}
