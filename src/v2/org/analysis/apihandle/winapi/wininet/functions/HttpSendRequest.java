/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: HttpSendRequest.java
 * Created date: Mar 18, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

/**
 * Sends the specified request to the HTTP server, allowing callers to send
 * extra data beyond what is normally passed to HttpSendRequestEx.
 * 
 * @param hRequest
 *            A handle returned by a call to the HttpOpenRequest function.
 * 
 * @param lpszHeaders
 *            A pointer to a null-terminated string that contains the additional
 *            headers to be appended to the request. This parameter can be NULL
 *            if there are no additional headers to be appended.
 * 
 * @param dwHeadersLength
 *            The size of the additional headers, in TCHARs. If this parameter
 *            is -1L and lpszHeaders is not NULL, the function assumes that
 *            lpszHeaders is zero-terminated (ASCIIZ), and the length is
 *            calculated. See Remarks for specifics.
 * 
 * @param lpOptional
 *            A pointer to a buffer containing any optional data to be sent
 *            immediately after the request headers. This parameter is generally
 *            used for POST and PUT operations. The optional data can be the
 *            resource or information being posted to the server. This parameter
 *            can be NULL if there is no optional data to send.
 * 
 * @param dwOptionalLength
 *            The size of the optional data, in bytes. This parameter can be
 *            zero if there is no optional data to send.
 * 
 * @return Returns TRUE if successful, or FALSE otherwise. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class HttpSendRequest extends WininetAPI {

	public HttpSendRequest() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HANDLE hRequest = new HANDLE(new Pointer(t1));
		String lpszHeaders = (t2 == 0L) ? null : memory.getText(this, t2);
		int dwHeadersLength = (int) t3;
		Pointer lpOptional = memory.getPointer(t4);
		int dwOptionalLength = (int) t5;
		boolean ret = WininetDLL.INSTANCE.HttpSendRequest(hRequest, lpszHeaders, dwHeadersLength, lpOptional,
				dwOptionalLength);
		System.out.println("lpszHeaders:" + lpszHeaders);

		register.mov("eax", new LongValue(ret ? 1 : 0));		
	}

}
