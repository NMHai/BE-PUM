/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptHashData.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * The CryptHashData function adds data to a specified hash object. This
 * function and CryptHashSessionKey can be called multiple times to compute the
 * hash of long or discontinuous data streams.
 * 
 * @param hHash
 *            Handle of the hash object.
 * 
 * @param pbData
 *            A pointer to a buffer that contains the data to be added to the
 *            hash object.
 * 
 * @param dwDataLen
 *            Number of bytes of data to be added. This must be zero if the
 *            CRYPT_USERDATA flag is set.
 * 
 * @param dwFlags
 *            The following flag values are defined.
 * 
 * @return If the function succeeds, the return value is TRUE. If the function
 *         fails, the return value is FALSE. For extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptHashData extends Advapi32API {
	public CryptHashData() {
		super();
		NUM_OF_PARMS = 4;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		
		System.out.println("pbData: " + memory.getText(this, t2));
		
		HANDLE hHash = new HANDLE(new Pointer(t1));
		Pointer pbData = memory.getPointer(t2);
		int dwDataLen = (int) (t3);
		int dwFlags = (int) (t4);
		
		BOOL ret = Advapi32DLL.INSTANCE.CryptHashData(hHash, pbData, dwDataLen, dwFlags);
		
		System.out.println("pbData: " + memory.getText(this, t2));
		
		long value = ret.booleanValue() ? 1 : 0;
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
