/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: QueryPerformanceCounter.java
 * Created date: Aug 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LONGLONGByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the current value of the performance counter, which is a high
 * resolution (<1us) time stamp that can be used for time-interval measurements.
 * 
 * @param lpPerformanceCount
 *            A pointer to a variable that receives the current
 *            performance-counter value, in counts.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError. On systems that run Windows XP or
 *         later, the function will always succeed and will thus never return
 *         zero.
 * 
 * @author Yen Nguyen
 *
 */
public class QueryPerformanceCounter extends Kernel32API {

	public QueryPerformanceCounter() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		LONGLONGByReference lpPerformanceCount = new LONGLONGByReference();
		BOOL ret = Kernel32DLL.INSTANCE.QueryPerformanceCounter(lpPerformanceCount);
		
		long value = (ret == null) ? 0 : ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
		
		if (lpPerformanceCount != null) {
			long v = lpPerformanceCount.getValue().longValue();
			long v1 = v >> 32;
			long v2 = v & 0xFFFFFFFF;
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(v1));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 + 4), new LongValue(v2));
		}
	}

}
