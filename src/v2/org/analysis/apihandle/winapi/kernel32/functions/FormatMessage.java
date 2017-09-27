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
import v2.org.analysis.value.SymbolValue;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;

/**
 * Formats a message string. The function requires a message definition as
 * input. The message definition can come from a buffer passed into the
 * function. It can come from a message table resource in an already-loaded
 * module. Or the caller can ask the function to search the system's message
 * table resource(s) for the message definition. The function finds the message
 * definition in a message table resource based on a message identifier and a
 * language identifier. The function copies the formatted message text to an
 * output buffer, processing any embedded insert sequences if requested.
 * 
 * @param dwFlags
 *            The formatting options, and how to interpret the lpSource
 *            parameter. The low-order byte of dwFlags specifies how the
 *            function handles line breaks in the output buffer. The low-order
 *            byte can also specify the maximum width of a formatted output
 *            line.
 * 
 * @param lpSource
 *            The location of the message definition. The type of this parameter
 *            depends upon the settings in the dwFlags parameter.
 * 
 * @param dwMessageId
 *            The message identifier for the requested message. This parameter
 *            is ignored if dwFlags includes FORMAT_MESSAGE_FROM_STRING.
 * 
 * @param dwLanguageId
 *            The language identifier for the requested message. This parameter
 *            is ignored if dwFlags includes FORMAT_MESSAGE_FROM_STRING.
 * 
 *            If you pass a specific LANGID in this parameter, FormatMessage
 *            will return a message for that LANGID only. If the function cannot
 *            find a message for that LANGID, it sets Last-Error to
 *            ERROR_RESOURCE_LANG_NOT_FOUND. If you pass in zero, FormatMessage
 *            looks for a message for LANGIDs in the following order:
 * 
 *            <ol>
 *            <li>Language neutral
 *            <li>Thread LANGID, based on the thread's locale value
 *            <li>User default LANGID, based on the user's default locale value
 *            <li>System default LANGID, based on the system default locale
 *            value
 *            <li>US English
 *            </ol>
 * 
 *            If FormatMessage does not locate a message for any of the
 *            preceding LANGIDs, it returns any language message string that is
 *            present. If that fails, it returns ERROR_RESOURCE_LANG_NOT_FOUND.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the null-terminated string
 *            that specifies the formatted message. If dwFlags includes
 *            FORMAT_MESSAGE_ALLOCATE_BUFFER, the function allocates a buffer
 *            using the LocalAlloc function, and places the pointer to the
 *            buffer at the address specified in lpBuffer.
 * 
 * @param nSize
 *            If the FORMAT_MESSAGE_ALLOCATE_BUFFER flag is not set, this
 *            parameter specifies the size of the output buffer, in TCHARs. If
 *            FORMAT_MESSAGE_ALLOCATE_BUFFER is set, this parameter specifies
 *            the minimum number of TCHARs to allocate for an output buffer. The
 *            output buffer cannot be larger than 64K bytes.
 * 
 * @param Arguments
 *            An array of values that are used as insert values in the formatted
 *            message. A %1 in the format string indicates the first value in
 *            the Arguments array; a %2 indicates the second argument; and so
 *            on.
 * 
 * @return If the function succeeds, the return value is the number of TCHARs
 *         stored in the output buffer, excluding the terminating null
 *         character.
 * 
 * @author Yen Nguyen
 *
 */
public class FormatMessage extends Kernel32API {

	public FormatMessage() {

		super();
		NUM_OF_PARMS = 7;
	}

	@Override
	public void execute() {
		try {
			long t1 = this.params.get(0);
			long t2 = this.params.get(1);
			long t3 = this.params.get(2);
			long t4 = this.params.get(3);
			long t5 = this.params.get(3);
			long t6 = this.params.get(5);
			long t7 = this.params.get(6);

			DWORD dwFlags = new DWORD(t1);
			LPVOID lpSource = new LPVOID(t2);
			DWORD dwMessageId = new DWORD(t3);
			DWORD dwLanguageId = new DWORD(t4);
			char[] lpBuffer = new char[(int) t6];
			DWORD nSize = new DWORD(t6);
			String[] Arguments = null;
			DWORD ret = Kernel32DLL.INSTANCE.FormatMessage(dwFlags, lpSource, dwMessageId, dwLanguageId, lpBuffer,
					nSize, Arguments);

			register.mov("eax", new LongValue(ret.longValue()));
		} catch (Exception e) {
			register.mov("eax", new SymbolValue(SymbolValue.generateString(this)));
			e.printStackTrace();
		}
	}

}
