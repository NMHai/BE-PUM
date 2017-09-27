/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetLocalTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the current local date and time.
 * 
 * @param lpSystemTime
 *            A pointer to a SYSTEMTIME structure to receive the current local
 *            date and time.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLocalTime extends Kernel32API {

	public GetLocalTime() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		SYSTEMTIME lpSystemTime = new SYSTEMTIME();
		Kernel32.INSTANCE.GetLocalTime(lpSystemTime);

		/*
		 * typedef struct _SYSTEMTIME { WORD wYear; WORD wMonth; WORD
		 * wDayOfWeek; WORD wDay; WORD wHour; WORD wMinute; WORD wSecond; WORD
		 * wMilliseconds; } SYSTEMTIME, *PSYSTEMTIME;
		 */

		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(lpSystemTime.wYear));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(lpSystemTime.wMonth));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(lpSystemTime.wDayOfWeek));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(lpSystemTime.wDay));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(lpSystemTime.wHour));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(lpSystemTime.wMinute));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(lpSystemTime.wSecond));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 2), new LongValue(
				lpSystemTime.wMilliseconds));
	}

}
