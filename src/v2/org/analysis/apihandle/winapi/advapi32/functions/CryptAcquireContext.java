/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptAcquireContext.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * The CryptAcquireContext function is used to acquire a handle to a particular
 * key container within a particular cryptographic service provider (CSP). This
 * returned handle is used in calls to CryptoAPI functions that use the selected
 * CSP..
 * 
 * @param phProv
 *            A pointer to a handle of a CSP. When you have finished using the
 *            CSP, release the handle by calling the CryptReleaseContext
 *            function.
 * 
 * @param pszContainer
 *            The key container name. This is a null-terminated string that
 *            identifies the key container to the CSP. This name is independent
 *            of the method used to store the keys. Some CSPs store their key
 *            containers internally (in hardware), some use the system registry,
 *            and others use the file system. In most cases, when dwFlags is set
 *            to CRYPT_VERIFYCONTEXT, pszContainer must be set to NULL. However,
 *            for hardware-based CSPs, such as a smart card CSP, can be access
 *            publically available information in the specfied container.
 * 
 * @param pszProvider
 *            A null-terminated string that contains the name of the CSP to be
 *            used.
 * 
 * @param dwProvType
 *            Specifies the type of provider to acquire. Defined provider types
 *            are discussed in Cryptographic Provider Types.
 * 
 * @param dwFlags
 *            Flag values. This parameter is usually set to zero, but some
 *            applications set one or more of the following flags.
 * 
 * @return If the function succeeds, the function returns nonzero (TRUE). If the
 *         function fails, it returns zero (FALSE). For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptAcquireContext extends Advapi32API {

	public CryptAcquireContext() {
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

		Pointer phProv = memory.getPointer(t1);
		Pointer pszContainer = memory.getPointer(t2);
		Pointer pszProvider = memory.getPointer(t3);
		int dwProvType = (int) (t4);
		int dwFlags = (int) (t5);

		System.out.println("pszContainer: " + ((pszContainer != null) ? memory.getText(this, t2) : null)
				+ " ,pszProvider: " + ((pszProvider != null) ? memory.getText(this, t3) : null));

		BOOL ret = Advapi32DLL.INSTANCE.CryptAcquireContext(phProv, pszContainer, pszProvider, dwProvType, dwFlags);
		
		System.out.println("phProv: " + phProv.getLong(0));
		
		long value = ret.booleanValue() ? 1 : 0;
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
