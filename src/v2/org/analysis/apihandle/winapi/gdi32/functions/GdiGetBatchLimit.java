/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GdiGetBatchLimit.java
 * Created date: Sep 18, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The GdiGetBatchLimit function returns the maximum number of function calls
 * that can be accumulated in the calling thread's current batch. The system
 * flushes the current batch whenever this limit is exceeded.
 * 
 * @return If the function succeeds, the return value is the batch limit. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class GdiGetBatchLimit extends Gdi32API {

	public GdiGetBatchLimit() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {

		DWORD ret = Gdi32DLL.INSTANCE.GdiGetBatchLimit();

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
