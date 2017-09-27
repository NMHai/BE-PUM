/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.advapi32.functions
 * File name: RegCloseKey.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

//import com.sun.jna.platform.win32.Advapi32;
//import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.value.LongValue;

/**
 * The RegCloseKey function releases a handle to the specified registry key.
 *
 * @param hKey
 *            Handle to the open key to be closed. The handle must have been
 *            opened by the RegCreateKeyEx, RegOpenKeyEx, or RegConnectRegistry
 *            function.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h.
 * 
 * @author Yen Nguyen
 *
 */
public class RegCloseKey extends Advapi32API {

	public RegCloseKey() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
//		int ret = Advapi32.INSTANCE.RegCloseKey(new HKEY(this.params.get(0).intValue()));
		// Always close successfully
		long hKey = this.params.get(0);
		
		System.out.println("HKey:" + hKey);
		
		register.mov("eax", new LongValue(0));
	}

}
