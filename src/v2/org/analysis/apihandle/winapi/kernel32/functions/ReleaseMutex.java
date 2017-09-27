/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: ReleaseMutex.java
 * Created date: Mar 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Releases ownership of the specified mutex object.
 * 
 * @param hMutex
 *            A handle to the mutex object. The CreateMutex or OpenMutex
 *            function returns this handle.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class ReleaseMutex extends Kernel32API {

	public ReleaseMutex() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HANDLE hMutex = new HANDLE(new Pointer(t1));
		BOOL ret = Kernel32DLL.INSTANCE.ReleaseMutex(hMutex);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
