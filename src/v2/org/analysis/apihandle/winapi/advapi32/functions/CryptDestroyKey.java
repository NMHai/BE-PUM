/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptDestroyKey.java
 * Created date: May 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The CryptDestroyKey function releases the handle referenced by the hKey
 * parameter. After a key handle has been released, it is no longer valid and
 * cannot be used again.
 * 
 * @param hKey
 *            The handle of the key to be destroyed.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptDestroyKey extends Advapi32API {

	public CryptDestroyKey() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);

		ULONG_PTR hKey = new ULONG_PTR(t1);
		BOOL ret = Advapi32DLL.INSTANCE.CryptDestroyKey(hKey);

		register.mov("eax", new LongValue(ret.longValue()));
		System.out.println("Return Value: " + ret.booleanValue());
	}

}
