/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetThreadLocale.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LCID;

/**
 * Sets the current locale of the calling thread.
 * 
 * @param Locale
 *            Locale identifier that specifies the locale. You can use the
 *            MAKELCID macro to create a locale identifier or use one of the
 *            following predefined values. LOCALE_CUSTOM_DEFAULT
 * 
 *            LOCALE_CUSTOM_UI_DEFAULT
 * 
 *            LOCALE_CUSTOM_UNSPECIFIED
 * 
 *            LOCALE_INVARIANT
 * 
 *            LOCALE_SYSTEM_DEFAULT
 * 
 *            LOCALE_USER_DEFAULT
 * 
 * @return The function should return an LCID on success. This is the LCID of
 *         the previous thread locale.
 * 
 * @author Yen Nguyen
 *
 */
public class SetThreadLocale extends Kernel32API {

	public SetThreadLocale() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		LCID Locale = new LCID(t1);
		BOOL ret = Kernel32DLL.INSTANCE.SetThreadLocale(Locale);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
