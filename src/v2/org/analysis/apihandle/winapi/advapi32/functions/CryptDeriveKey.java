/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptDeriveKey.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The CryptDeriveKey function generates cryptographic session keys derived from
 * a base data value. This function guarantees that when the same cryptographic
 * service provider (CSP) and algorithms are used, the keys generated from the
 * same base data are identical. The base data can be a password or any other
 * user data.
 * 
 * @param hProv
 *            A HCRYPTPROV handle of a CSP created by a call to
 *            CryptAcquireContext.
 * 
 * @param Algid
 *            An ALG_ID structure that identifies the symmetric encryption
 *            algorithm for which the key is to be generated. The algorithms
 *            available will most likely be different for each CSP. For more
 *            information about which algorithm identifier is used by the
 *            different providers for the key specs AT_KEYEXCHANGE and
 *            AT_SIGNATURE, see ALG_ID.
 * 
 * @param hBaseData
 *            A handle to a hash object that has been fed the exact base data.
 * 
 * @param dwFlags
 *            Specifies the type of key generated.
 * 
 * @param phKey
 *            A pointer to a HCRYPTKEY variable to receive the address of the
 *            handle of the newly generated key. When you have finished using
 *            the key, release the handle by calling the CryptDestroyKey
 *            function.
 * 
 * @return If the function succeeds, the function returns nonzero (TRUE). If the
 *         function fails, it returns zero (FALSE). For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptDeriveKey extends Advapi32API {

	public CryptDeriveKey() {
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

		ULONG_PTR hProv = new ULONG_PTR(t1);
		UINT Algid = new UINT(t2);
		ULONG_PTR hBaseData = new ULONG_PTR(t3);
		DWORD dwFlags = new DWORD(t4);
		ULONG_PTRByReference phKey = new ULONG_PTRByReference();
		BOOL ret = Advapi32DLL.INSTANCE.CryptDeriveKey(hProv, Algid, hBaseData, dwFlags, phKey);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(phKey.getValue()
				.longValue()));
	}

}
