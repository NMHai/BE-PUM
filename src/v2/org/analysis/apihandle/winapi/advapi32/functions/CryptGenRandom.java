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
 * 
 * @author HaiNM
 *
 */
public class CryptGenRandom extends Advapi32API {
	public CryptGenRandom() {
		super();
		NUM_OF_PARMS = 3;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HANDLE hProv = new HANDLE(new Pointer(t1));
		Pointer pbData = memory.getPointer(t3);
		int dwLen = (int) (t2);
		
		BOOL ret = Advapi32DLL.INSTANCE.CryptGenRandom(hProv, dwLen, pbData);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
		System.out.println("Gen Random:" + pbData.getString(0));
	}

}
