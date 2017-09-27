/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemTime.java
 * Created date: Feb 2, 2015
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
 * Converts a file time to system time format. System time is based on
 * Coordinated Universal Time (UTC).
 * 
 * @param lpFileTime
 *            A pointer to a FILETIME structure containing the file time to be
 *            converted to system (UTC) date and time format.
 * 
 * @param lpSystemTime
 *            A pointer to a SYSTEMTIME structure to receive the converted file
 *            time.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FileTimeToSystemTime extends Kernel32API {

	public FileTimeToSystemTime() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		FILETIME lpFileTime = new FILETIME();
		SYSTEMTIME lpSystemTime = new SYSTEMTIME();

		lpFileTime.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1))).getValue();
		lpFileTime.dwHighDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1 + 4))).getValue();

		BOOL ret = Kernel32DLL.INSTANCE.FileTimeToSystemTime(lpFileTime, lpSystemTime);
		register.mov("eax", new LongValue(ret.longValue()));

		memory.setWordMemoryValue(t2, new LongValue(lpSystemTime.wYear));
		memory.setWordMemoryValue(t2 + 2, new LongValue(lpSystemTime.wMonth));
		memory.setWordMemoryValue(t2 + 4, new LongValue(lpSystemTime.wDayOfWeek));
		memory.setWordMemoryValue(t2 + 6, new LongValue(lpSystemTime.wDay));
		memory.setWordMemoryValue(t2 + 8, new LongValue(lpSystemTime.wHour));
		memory.setWordMemoryValue(t2 + 10, new LongValue(lpSystemTime.wMinute));
		memory.setWordMemoryValue(t2 + 12, new LongValue(lpSystemTime.wSecond));
		memory.setWordMemoryValue(t2 + 14, new LongValue(lpSystemTime.wMilliseconds));
	}

}
