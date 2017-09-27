package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.*;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The CryptImportKey function transfers a cryptographic key from a key BLOB into a cryptographic service provider (CSP). This function can be used to import an Schannel
 * session key, regular session key, public key, or public/private key pair. For all but the public key, the key or key pair is encrypted.
 *
 * @param hProv
 * 		The handle of a CSP obtained with the CryptAcquireContext function.
 * @param pbData
 * 		A BYTE array that contains a PUBLICKEYSTRUC BLOB header followed by the encrypted key. This key BLOB is created by the CryptExportKey function, either in this application or
 * 		by another application possibly running on a different computer.
 * @param dwDataLen
 * 		Contains the length, in bytes, of the key BLOB.
 * @param hPubKey
 * 		A handle to the cryptographic key that decrypts the key stored in pbData. This key must come from the same CSP to which hProv refers.
 * @param dwFlags
 * 		Currently used only when a public/private key pair in the form of a PRIVATEKEYBLOB is imported into the CSP. This parameter can be one of the following values.
 * @param phKey
 * 		A pointer to a HCRYPTKEY value that receives the handle of the imported key. When you have finished using the key, release the handle by calling the CryptDestroyKey function.
 *
 * @author YenNLH
 * @return If the function succeeds, the function returns nonzero. If the function fails, it returns zero. For extended error information, call GetLastError.
 */
public class CryptImportKey extends Advapi32API {

	public CryptImportKey() {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		HANDLE hProv = new HANDLE(new Pointer(t1));
		Pointer pbData = memory.getPointer(t2);
		int dwDataLen = (int) t3;
		HANDLE hPubKey = new HANDLE(new Pointer(t4));
		int dwFlags = (int) t5;
		HANDLEByReference phKey = new HANDLEByReference();

		BOOL ret = Advapi32DLL.INSTANCE.CryptImportKey(hProv, pbData, dwDataLen, hPubKey, dwFlags, phKey);

		register.mov("eax", new LongValue(ret.booleanValue() ? 1 : 0));

		memory.setDoubleWordMemoryValue(t6, new LongValue(Pointer.nativeValue(phKey.getValue().getPointer())));
	}
}
