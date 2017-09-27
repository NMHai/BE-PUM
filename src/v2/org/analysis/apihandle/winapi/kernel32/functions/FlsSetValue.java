/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FlsSetValue.java
 * Created date: Aug 23, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.PVOID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Stores a value in the calling fiber's fiber local storage (FLS) slot for the
 * specified FLS index. Each fiber has its own slot for each FLS index.
 * 
 * @param dwFlsIndex
 *            The FLS index that was allocated by the FlsAlloc function.
 * 
 * @param lpFlsData
 *            The value to be stored in the FLS slot for the calling fiber.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError. The following errors can be returned.
 * 
 * @author Yen Nguyen
 *
 */
public class FlsSetValue extends Kernel32API {

	public FlsSetValue() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		DWORD dwFlsIndex = new DWORD(t1);
		PVOID lpFlsData = new PVOID(new Pointer(t2));
		
		BOOL ret = Kernel32DLL.INSTANCE.FlsSetValue(dwFlsIndex, lpFlsData);
		
		System.out.println("Return Value:" + ret.longValue());
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
