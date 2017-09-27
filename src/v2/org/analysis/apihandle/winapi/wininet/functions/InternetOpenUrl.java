/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetOpenUrl.java
 * Created date: Mar 11, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * @author Yen Nguyen
 *
 */
public class InternetOpenUrl extends WininetAPI {

	public InternetOpenUrl() {
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

		HANDLE hInternet = new HANDLE(new Pointer(t1));
		String lpszUrl = (t2 == 0L) ? null : memory.getText(this, t2);
		String lpszHeaders = (t3 == 0L) ? null : memory.getText(this, t3);
		DWORD dwHeadersLength = new DWORD(t4);
		DWORD dwFlags = new DWORD(t5);
		DWORD_PTR dwContext = new DWORD_PTR(t6);

		System.out.println(String.format("lpszUrl: %s, lpszHeaders: %s", lpszUrl, lpszHeaders));

		HANDLE ret = WininetDLL.INSTANCE.InternetOpenUrl(hInternet, lpszUrl, lpszHeaders, dwHeadersLength, dwFlags,
				dwContext);

		register.mov("eax", (ret == null) ? new LongValue(0) : new LongValue(Pointer.nativeValue(ret.getPointer())));
	}
}
