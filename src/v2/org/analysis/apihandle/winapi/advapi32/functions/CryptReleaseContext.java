/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptReleaseContext.java
 * Created date: May 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The CryptReleaseContext function releases the handle of a cryptographic
 * service provider (CSP) and a key container. At each call to this function,
 * the reference count on the CSP is reduced by one. When the reference count
 * reaches zero, the context is fully released and it can no longer be used by
 * any function in the application.
 * 
 * @param hProv
 *            Handle of a cryptographic service provider (CSP) created by a call
 *            to CryptAcquireContext.
 * 
 * @param dwFlags
 *            Reserved for future use and must be zero. If dwFlags is not set to
 *            zero, this function returns FALSE but the CSP is released.
 * 
 * @return If the function succeeds, the return value is nonzero (TRUE). If the
 *         function fails, the return value is zero (FALSE). For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptReleaseContext extends Advapi32API {

	public CryptReleaseContext() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		ULONG_PTR hProv = new ULONG_PTR(t1);
		DWORD dwFlags = new DWORD(t2);
		BOOL ret = Advapi32DLL.INSTANCE.CryptReleaseContext(hProv, dwFlags);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
