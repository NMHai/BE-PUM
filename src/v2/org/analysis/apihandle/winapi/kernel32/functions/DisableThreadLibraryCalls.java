/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.value.LongValue;

/**
 * Disables the DLL_THREAD_ATTACH and DLL_THREAD_DETACH notifications for the
 * specified dynamic-link library (DLL). This can reduce the size of the working
 * set for some applications.
 * 
 * @param hModule
 *            [in] A handle to the DLL module for which the DLL_THREAD_ATTACH
 *            and DLL_THREAD_DETACH notifications are to be disabled. The
 *            LoadLibrary, LoadLibraryEx, or GetModuleHandle function returns
 *            this handle. Note that you cannot call GetModuleHandle with NULL
 *            because this returns the base address of the executable image, not
 *            the DLL image.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class DisableThreadLibraryCalls extends Kernel32API {

	public DisableThreadLibraryCalls() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long x = this.params.get(0);
		System.out.println("hModule:" + x);

		HMODULE hModule = new HMODULE();
		hModule.setPointer(new Pointer(x));
		BOOL ret = Kernel32DLL.INSTANCE.DisableThreadLibraryCalls(hModule);
		register.mov("eax", new LongValue(ret.longValue()));

		System.out.println("Return Value:" + ret);
	}

}
