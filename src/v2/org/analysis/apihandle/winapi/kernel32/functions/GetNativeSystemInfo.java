/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetNativeSystemInfo.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;

/**
 * The GetNativeSystemInfo function retrieves information about the current
 * system to an application running under WOW64. If the function is called from
 * a 64-bit application, it is equivalent to the GetSystemInfo function.
 * 
 * @param lpSystemInfo
 *            Pointer to a SYSTEM_INFO structure that receives the information.
 * 
 * @author Yen Nguyen
 *
 */
public class GetNativeSystemInfo extends Kernel32API {

	public GetNativeSystemInfo() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		SYSTEM_INFO lpSystemInfo = new SYSTEM_INFO();
		Kernel32.INSTANCE.GetNativeSystemInfo(lpSystemInfo);
	}

}
