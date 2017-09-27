/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetUserDefaultLCID.java
 * Created date: Oct 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.LCID;

/**
 * Returns the locale identifier for the user default locale.
 * 
 * @return Returns the locale identifier for the user default locale,
 *         represented as LOCALE_USER_DEFAULT. If the user default locale is a
 *         custom locale, this function always returns LOCALE_CUSTOM_DEFAULT,
 *         regardless of the custom locale that is selected. For example,
 *         whether the user locale is Hawaiian (US), value.
 * 
 * @author Yen Nguyen
 *
 */
public class GetUserDefaultLCID extends Kernel32API {

	public GetUserDefaultLCID() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		LCID ret = Kernel32DLL.INSTANCE.GetUserDefaultLCID();
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
