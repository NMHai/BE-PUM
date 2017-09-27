/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptCreateHash.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * The CryptCreateHash function initiates the hashing of a stream of data. It
 * creates and returns to the calling application a handle to a cryptographic
 * service provider (CSP) hash object. This handle is used in subsequent calls
 * to CryptHashData and CryptHashSessionKey to hash session keys and other
 * streams of data.
 * 
 * @param hProv
 *            A handle to a CSP created by a call to CryptAcquireContext.
 * 
 * @param Algid
 *            An ALG_ID value that identifies the hash algorithm to use.
 * 
 * @param hKey
 *            If the type of hash algorithm is a keyed hash, such as the
 *            Hash-Based Message Authentication Code (HMAC) or Message
 *            Authentication Code (MAC) algorithm, the key for the hash is
 *            passed in this parameter. For nonkeyed algorithms, this parameter
 *            must be set to zero.
 * 
 * @param dwFlags
 * 
 * @param phHash
 *            The address to which the function copies a handle to the new hash
 *            object. When you have finished using the hash object, release the
 *            handle by calling the CryptDestroyHash function.
 * 
 * @return If the function succeeds, the function returns TRUE. If the function
 *         fails, it returns FALSE. For extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptCreateHash extends Advapi32API {

	public CryptCreateHash() {
		super();
		NUM_OF_PARMS = 5;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HANDLE hProv = new HANDLE(new Pointer(t1));
		UINT Algid = new UINT(t2);
		HANDLE hKey = new HANDLE(new Pointer(t3));
		int dwFlags = (int) (t4);
		Pointer phHash = memory.getPointer(t5);
		BOOL ret = Advapi32DLL.INSTANCE.CryptCreateHash(hProv, Algid, hKey, dwFlags, phHash);

		long value = ret.booleanValue() ? 1 : 0;
		register.mov("eax", new LongValue(value));
		
		System.out.println("phHash: " + new LongValue(phHash.getLong(0)));
		System.out.println("Return Value: " + value);
	}

}
