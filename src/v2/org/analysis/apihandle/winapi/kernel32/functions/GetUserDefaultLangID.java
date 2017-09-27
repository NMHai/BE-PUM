/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetUserDefaultLangID.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.WORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Returns the language identifier of the Region Format setting for the current
 * user.
 * 
 * @return Returns the language identifier for the current user as set under
 *         Control Panel > Clock, Language, and Region > Change date, time, or
 *         number formats > Formats tab > Format dropdown.
 * 
 * @author Yen Nguyen
 *
 */
public class GetUserDefaultLangID extends Kernel32API {

	public GetUserDefaultLangID() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		WORD ret = Kernel32DLL.INSTANCE.GetUserDefaultLangID();

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
