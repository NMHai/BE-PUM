/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;

/**
 * For a locale specified by identifier, maps one input character string to
 * another using a specified transformation, or generates a sort key for the
 * input string.
 * 
 * @param Locale
 *            Locale identifier that specifies the locale. You can use the
 *            MAKELCID macro to create a locale identifier or use one of the
 *            following predefined values.
 * 
 * @param dwMapFlags
 *            Flags specifying the type of transformation to use during string
 *            mapping or the type of sort key to generate. For detailed
 *            definitions, see the dwMapFlags parameter of LCMapStringEx.
 * 
 * @param lpSrcStr
 *            Pointer to a source string that the function maps or uses for sort
 *            key generation. This string cannot have a size of 0.
 * 
 * @param cchSrc
 *            ize, in characters, of the source string indicated by lpSrcStr.
 *            The size of the source string can include the terminating null
 *            character, but does not have to. If the terminating null character
 *            is included, the mapping behavior of the function is not greatly
 *            affected because the terminating null character is considered to
 *            be unsortable and always maps to itself. The application can set
 *            the parameter to any negative value to specify that the source
 *            string is null-terminated. In this case, if LCMapString is being
 *            used in its string-mapping mode, the function calculates the
 *            string length itself, and null-terminates the mapped string
 *            indicated by lpDestStr. The application cannot set this parameter
 *            to 0.
 * 
 * @param lpDestStr
 *            Pointer to a buffer in which this function retrieves the mapped
 *            string or a sort key. When the application uses this function to
 *            generate a sort key, the destination string can contain an odd
 *            number of bytes. The LCMAP_BYTEREV flag only reverses an even
 *            number of bytes. The last byte (odd-positioned) in the sort key is
 *            not reversed.
 * 
 * @param cchDest
 *            Size, in characters, of the destination string indicated by
 *            lpDestStr. If the application is using the function for string
 *            mapping, it supplies a character count for this parameter. If
 *            space for a terminating null character is included in cchSrc,
 *            cchDest must also include space for a terminating null character.
 * 
 * @return Returns the number of characters or bytes in the translated string or
 *         sort key, including a terminating null character, if successful. If
 *         the function succeeds and the value of cchDest is 0, the return value
 *         is the size of the buffer required to hold the translated string or
 *         sort key, including a terminating null character. This function
 *         returns 0 if it does not succeed. To get extended error information,
 *         the application can call GetLastError, which can return one of the
 *         following error codes:
 * 
 * @author Yen Nguyen
 *
 */
public class LCMapString extends Kernel32API {

	public LCMapString() {
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
		DWORD dwMapFlags = new DWORD(t2);
		WString lpSrcStr = new WString(memory.getText(this, t3));
		int cchSrc = (int) t4;
		char[] lpDestStr = (t5 != 0L && t6 != 0L) ? new char[(int) t6] : null;
		int cchDest = (int) t6;
		int ret = Kernel32DLL.INSTANCE.LCMapString(Locale, dwMapFlags, lpSrcStr, cchSrc, lpDestStr, cchDest);

		register.mov("eax", new LongValue(ret));

		if (lpDestStr != null) {
			memory.setText(this, t5, new String(lpDestStr));
		}

	}

}
