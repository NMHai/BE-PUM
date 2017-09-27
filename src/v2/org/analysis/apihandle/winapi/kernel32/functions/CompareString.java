/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;

/**
 * Compares two character strings, for a locale specified by identifier.
 * 
 * @param Locale
 *            [in] Locale identifier of the locale used for the comparison. You
 *            can use the MAKELCID macro to create a locale identifier or use
 *            one of the following predefined values.
 *
 * @param dwCmpFlags
 *            [in] Flags that indicate how the function compares the two
 *            strings. For detailed definitions, see the dwCmpFlags parameter of
 *            CompareStringEx.
 * 
 * @param lpString1
 *            [in] Pointer to the first string to compare.
 * 
 * @param cchCount1
 *            [in] Length of the string indicated by lpString1, excluding the
 *            terminating null character. This value represents bytes for the
 *            ANSI version of the function and wide characters for the Unicode
 *            version. The application can supply a negative value if the string
 *            is null-terminated. In this case, the function determines the
 *            length automatically.
 * 
 * @param lpString2
 *            [in] Pointer to the second string to compare.
 * 
 * @param cchCount2
 *            [in] Length of the string indicated by lpString2, excluding the
 *            terminating null character. This value represents bytes for the
 *            ANSI version of the function and wide characters for the Unicode
 *            version. The application can supply a negative value if the string
 *            is null-terminated. In this case, the function determines the
 *            length automatically.
 * 
 * @author Yen Nguyen
 *
 */
public class CompareString extends Kernel32API {

	public CompareString() {
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

		LCID Locale = new LCID(t1);
		DWORD dwCmpFlags = new DWORD(t2);
		WString lpString1 = new WString(memory.getText(this, t3));
		int cchCount1 = (int) t4;
		WString lpString2 = new WString(memory.getText(this, t5));
		int cchCount2 = (int) t6;

		int ret = Kernel32DLL.INSTANCE.CompareString(Locale, dwCmpFlags, lpString1, cchCount1, lpString2, cchCount2);

		register.mov("eax", new LongValue(ret));
	}

}
