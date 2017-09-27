/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualAlloc.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;

/**
 * Formats a date as a date string for a locale specified by the locale
 * identifier. The function formats either a specified date or the local system
 * date.
 * 
 * @param Locale
 *            Locale identifier that specifies the locale this function formats
 *            the date string for. You can use the MAKELCID macro to create a
 *            locale identifier or use one of the following predefined values.
 * 
 * @param dwFlags
 *            Flags specifying date format options. For detailed definitions,
 *            see the dwFlags parameter of GetDateFormatEx.
 * 
 * @param lpDate
 *            Pointer to a SYSTEMTIME structure that contains the date
 *            information to format. The application sets this parameter to NULL
 *            if the function is to use the current local system date.
 * 
 * @param lpFormat
 *            Pointer to a format picture string that is used to form the date.
 *            Possible values for the format picture string are defined in Day,
 *            Month, Year, and Era Format Pictures.
 * 
 * @param lpDateStr
 *            Pointer to a buffer in which this function retrieves the formatted
 *            date string.
 * 
 * @param cchDate
 *            Size, in characters, of the lpDateStr buffer. The application can
 *            set this parameter to 0 to return the buffer size required to hold
 *            the formatted date string. In this case, the buffer indicated by
 *            lpDateStr is not used.
 * 
 * @return Returns the number of characters written to the lpDateStr buffer if
 *         successful. If the cchDate parameter is set to 0, the function
 *         returns the number of characters required to hold the formatted date
 *         string, including the terminating null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDateFormat extends Kernel32API {

	public GetDateFormat() {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(3);
		long t6 = this.params.get(5);

		LCID Locale = new LCID(t1);
		DWORD dwFlags = new DWORD(t2);
		SYSTEMTIME lpDate = null;
		if (t3 != 0L) {
			lpDate = new SYSTEMTIME();
			lpDate.wYear = (short) ((LongValue) memory.getWordMemoryValue(t3)).getValue();
			lpDate.wMonth = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
			lpDate.wDayOfWeek = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
			lpDate.wDay = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
			lpDate.wHour = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
			lpDate.wMinute = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
			lpDate.wSecond = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
			lpDate.wMilliseconds = (short) ((LongValue) memory.getWordMemoryValue(t3 += 2)).getValue();
		}
		WString lpFormat = (t4 == 0L) ? null : new WString(memory.getText(this, t4));
		char[] lpDateStr = (t5 == 0L) ? null : new char[(int) t6 + 1];
		int cchDate = (int) t6;

		int ret = Kernel32DLL.INSTANCE.GetDateFormatW(Locale, dwFlags, lpDate, lpFormat, lpDateStr, cchDate);

		register.mov("eax", new LongValue(ret));

		if (t5 != 0L && cchDate != 0) {
			memory.setText(this, t5, new String(lpDateStr), ret);
		}
	}
}
