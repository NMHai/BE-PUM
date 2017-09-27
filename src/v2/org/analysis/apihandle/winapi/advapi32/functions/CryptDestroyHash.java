/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptDestroyHash.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * The CryptDestroyHash function destroys the hash object referenced by the
 * hHash parameter. After a hash object has been destroyed, it can no longer be
 * used.
 * 
 * @param hHash
 *            The handle of the hash object to be destroyed.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptDestroyHash extends Advapi32API {

	public CryptDestroyHash() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);

		ULONG_PTR hHash = new ULONG_PTR(t1);
		BOOL ret = Advapi32DLL.INSTANCE.CryptDestroyHash(hHash);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
	}

}
