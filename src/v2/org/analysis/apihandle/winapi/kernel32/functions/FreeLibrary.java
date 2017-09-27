/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetModuleHandle.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Frees the loaded dynamic-link library (DLL) module and, if necessary,
 * decrements its reference count. When the reference count reaches zero, the
 * module is unloaded from the address space of the calling process and the
 * handle is no longer valid.
 * 
 * @param hModule
 *            A handle to the loaded library module. The LoadLibrary,
 *            LoadLibraryEx, GetModuleHandle, or GetModuleHandleEx function
 *            returns this handle.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FreeLibrary extends Kernel32API {

	public FreeLibrary() {

		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HMODULE hModule = new HMODULE();
		hModule.setPointer(new Pointer(t1));
		BOOL ret = Kernel32DLL.INSTANCE.FreeLibrary(hModule);
		System.out.println("Return Value: " + ret);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
