/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: HttpAddRequestHeaders.java
 * Created date: Mar 18, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Adds one or more HTTP request headers to the HTTP request handle.
 * 
 * @param hRequest
 *            A handle returned by a call to the HttpOpenRequest function.
 * 
 * @param lpszHeaders
 *            A pointer to a string variable containing the headers to append to
 *            the request. Each header must be terminated by a CR/LF (carriage
 *            return/line feed) pair.
 * 
 * @param dwHeadersLength
 *            The size of lpszHeaders, in TCHARs. If this parameter is -1L, the
 *            function assumes that lpszHeaders is zero-terminated (ASCIIZ), and
 *            the length is computed.
 * 
 * @param dwModifiers
 *            A set of modifiers that control the semantics of this function.
 *            This parameter can be a combination of the following values.
 * 
 * @return Returns TRUE if successful, or FALSE otherwise. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class HttpAddRequestHeaders extends WininetAPI {

	public HttpAddRequestHeaders() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		
		HANDLE hRequest = new HANDLE(new Pointer(t1));
		String lpszHeaders = (t1 == 0L) ? null : memory.getText(this, t2);
		int dwHeadersLength = (int) t3;
		int dwModifiers = (int) t4;
		
		System.out.println("lpszHeaders:" + lpszHeaders);
		boolean ret = WininetDLL.INSTANCE.HttpAddRequestHeaders(hRequest, lpszHeaders, dwHeadersLength, dwModifiers);
		
		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
