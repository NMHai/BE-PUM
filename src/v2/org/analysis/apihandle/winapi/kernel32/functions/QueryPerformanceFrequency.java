/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: QueryPerformanceFrequency.java
 * Created date: Oct 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;

/**
 * Retrieves the frequency of the performance counter. The frequency of the
 * performance counter is fixed at system boot and is consistent across all
 * processors. Therefore, the frequency need only be queried upon application
 * initialization, and the result can be cached.
 * 
 * @param lpFrequency
 *            A pointer to a variable that receives the current
 *            performance-counter frequency, in counts per second. If the
 *            installed hardware doesn't support a high-resolution performance
 *            counter, this parameter can be zero (this will not occur on
 *            systems that run Windows XP or later).
 * 
 * @return If the installed hardware supports a high-resolution performance
 *         counter, the return value is nonzero. If the function fails, the
 *         return value is zero. To get extended error information, call
 *         GetLastError. On systems that run Windows XP or later, the function
 *         will always succeed and will thus never return zero.
 * 
 * @author Yen Nguyen
 *
 */
public class QueryPerformanceFrequency extends Kernel32API {

	public QueryPerformanceFrequency() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		LARGE_INTEGER lpFrequency = new LARGE_INTEGER();
		BOOL ret = Kernel32DLL.INSTANCE.QueryPerformanceFrequency(lpFrequency);

		memory.setDoubleWordMemoryValue(t1, new LongValue(lpFrequency.getLow().longValue()));
		memory.setDoubleWordMemoryValue(t1 + 4, new LongValue(lpFrequency.getHigh().longValue()));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
