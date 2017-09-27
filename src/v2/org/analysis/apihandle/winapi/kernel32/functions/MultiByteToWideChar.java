/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Maps a character string to a UTF-16 (wide character) string. The character
 * string is not necessarily from a multibyte character set.
 * 
 * @param CodePage
 *            Code page to use in performing the conversion. This parameter can
 *            be set to the value of any code page that is installed or
 *            available in the operating system. For a list of code pages, see
 *            Code Page Identifiers.
 * 
 * @param dwFlags
 *            Flags indicating the conversion type. The application can specify
 *            a combination of the following values, with MB_PRECOMPOSED being
 *            the default. MB_PRECOMPOSED and MB_COMPOSITE are mutually
 *            exclusive. MB_USEGLYPHCHARS and MB_ERR_INVALID_CHARS can be set
 *            regardless of the state of the other flags.
 * 
 * @param lpMultiByteStr
 *            Pointer to the character string to convert.
 * 
 * @param cbMultiByte
 *            Size, in bytes, of the string indicated by the lpMultiByteStr
 *            parameter. Alternatively, this parameter can be set to -1 if the
 *            string is null-terminated. Note that, if cbMultiByte is 0, the
 *            function fails.
 * 
 * @param lpWideCharStr
 *            Pointer to a buffer that receives the converted string.
 * 
 * @param cchWideChar
 *            Size, in characters, of the buffer indicated by lpWideCharStr. If
 *            this value is 0, the function returns the required buffer size, in
 *            characters, including any terminating null character, and makes no
 *            use of the lpWideCharStr buffer.
 * 
 * @return Returns the number of characters written to the buffer indicated by
 *         lpWideCharStr if successful. If the function succeeds and cchWideChar
 *         is 0, the return value is the required size, in characters, for the
 *         buffer indicated by lpWideCharStr. If the input byte/char sequences
 *         are invalid, returns U+FFFD for UTF encodings. The function returns 0
 *         if it does not succeed.
 * 
 * @author Yen Nguyen
 *
 */
public class MultiByteToWideChar extends Kernel32API {

	public MultiByteToWideChar() {
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

		UINT CodePage = new UINT(t1);
		DWORD dwFlags = new DWORD(t2);
		String lpMultiByteStr = memory.getTextLimit(t3, 1, (int) t4);
		int cbMultiByte = (int) t4;
		char[] lpWideCharStr = (t5 != 0L && t6 > 0) ? new char[(int) t6] : null;
		int cchWideChar = (int) t6;

		int ret = Kernel32DLL.INSTANCE.MultiByteToWideChar(CodePage, dwFlags, lpMultiByteStr, cbMultiByte,
				lpWideCharStr, cchWideChar);
		
		register.mov("eax", new LongValue(ret));

		if (lpWideCharStr != null) {
			//System.out.println(" - [Str][W] " + lpMultiByteStr);
			memory.setText(2, t5, lpMultiByteStr);
		}
	}

}
