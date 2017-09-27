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
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Retrieves header information associated with an HTTP request.
 *
 * @param hRequest
 *         A handle returned by a call to the HttpOpenRequest or InternetOpenUrl function.
 * @param dwInfoLevel
 *         A combination of an attribute to be retrieved and flags that modify the request. For a list of possible
 *         attribute and modifier values, see Query Info Flags.
 * @param lpvBuffer
 *         A pointer to a buffer to receive the requested information. This parameter must not be NULL.
 * @param lpdwBufferLength
 *         A pointer to a variable that contains, on entry, the size in bytes of the buffer pointed to by lpvBuffer.
 *         When the function returns successfully, this variable contains the number of bytes of information written
 *         to the buffer. In the case of a string, the byte count does not include the string's terminating null
 *         character. When the function fails with an extended error code of ERROR_INSUFFICIENT_BUFFER, the variable
 *         pointed to by lpdwBufferLength contains on exit the size, in bytes, of a buffer large enough to receive
 *         the requested information. The calling application can then allocate a buffer of this size or larger, and
 *         call the function again.
 * @param lpdwIndex
 *         A pointer to a zero-based header index used to enumerate multiple headers with the same name. When
 *         calling the function, this parameter is the index of the specified header to return. When the function
 *         returns, this parameter is the index of the next header. If the next index cannot be found,
 *         ERROR_HTTP_HEADER_NOT_FOUND is returned.
 *
 * @return Returns TRUE if successful, or FALSE otherwise. To get extended error information, call GetLastError.
 *
 * @author YenNLH
 *
 */
public class HttpQueryInfo extends WininetAPI {

	public HttpQueryInfo() {
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
		int dwInfoLevel = (int) t2;
		Pointer lpvBuffer = memory.getPointer(t3);
		Pointer lpdwBufferLength = memory.getPointer(t4);
		Pointer lpdwIndex = memory.getPointer(t5);
		
		BOOL ret = WininetDLL.INSTANCE.HttpQueryInfo(hRequest, dwInfoLevel, lpvBuffer, lpdwBufferLength, lpdwIndex);
		
		register.mov("eax", new LongValue(ret.booleanValue() ? 1 : 0));
	}

}
