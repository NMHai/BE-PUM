/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetCloseHandle.java
 * Created date: Mar 11, 2016
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
 * @author Yen Nguyen
 *
 */
public class InternetCloseHandle extends WininetAPI {

	public InternetCloseHandle() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HANDLE hInternet = new HANDLE(new Pointer(t1));
		BOOL ret = WininetDLL.INSTANCE.InternetCloseHandle(hInternet);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
