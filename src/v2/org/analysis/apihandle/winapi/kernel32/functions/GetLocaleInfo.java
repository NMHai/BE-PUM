/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;

/**
 * Retrieves information about a locale specified by identifier.
 * 
 * @param Locale
 *            Locale identifier for which to retrieve information. You can use
 *            the MAKELCID macro to create a locale identifier or use one of the
 *            following predefined values.
 * 
 * @param LCType
 *            The locale information to retrieve. For detailed definitions, see
 *            the LCType parameter of GetLocaleInfoEx.
 * 
 * @param lpLCData
 *            Pointer to a buffer in which this function retrieves the requested
 *            locale information. This pointer is not used if cchData is set to
 *            0. For more information, see the Remarks section.
 * 
 * @param cchData
 *            Size, in TCHAR values, of the data buffer indicated by lpLCData.
 *            Alternatively, the application can set this parameter to 0. In
 *            this case, the function does not use the lpLCData parameter and
 *            returns the required buffer size, including the terminating null
 *            character.
 * 
 * @return Returns the number of characters retrieved in the locale data buffer
 *         if successful and cchData is a nonzero value. If the function
 *         succeeds, cchData is nonzero, and LOCALE_RETURN_NUMBER is specified,
 *         the return value is the size of the integer retrieved in the data
 *         buffer; that is, 2 for the Unicode version of the function or 4 for
 *         the ANSI version. If the function succeeds and the value of cchData
 *         is 0, the return value is the required size, in characters including
 *         a null character, for the locale data buffer.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLocaleInfo extends Kernel32API {

	public GetLocaleInfo() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		LCID Locale = new LCID(t1);
		DWORD LCType = new DWORD(t2);
		char[] lpLCData = (t4 == 0L) ? null : new char[(int) t4];
		int cchData = (int) t4;
		int ret = Kernel32DLL.INSTANCE.GetLocaleInfo(Locale, LCType, lpLCData, cchData);

		register.mov("eax", new LongValue(ret));

		if (lpLCData != null && t4 != 0) {
			memory.setText(this, t3, new String(lpLCData), ret);
		}
	}

}
