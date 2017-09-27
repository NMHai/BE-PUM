/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetVersionExA.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.OSVERSIONINFO;

/**
 * The GetVersionEx function obtains extended information about the version of
 * the operating system that is currently running.
 * 
 * @param lpVersionInfo
 *            Pointer to an OSVERSIONINFO data structure that the function fills
 *            with operating system version information.
 * @return If the function succeeds, the return value is a nonzero value. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError. The function fails if you specify an
 *         invalid value for the dwOSVersionInfoSize member of the OSVERSIONINFO
 *         or OSVERSIONINFOEX structure.
 * 
 * @author Yen Nguyen
 *
 */
public class GetVersionEx extends Kernel32API {

	/**
	 * 
	 */
	public GetVersionEx() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);

		OSVERSIONINFO lpVersionInfo = new OSVERSIONINFO();
		boolean ret = Kernel32.INSTANCE.GetVersionEx(lpVersionInfo);

		register.mov("eax", new LongValue(ret ? 1 : 0));

		memory.setDoubleWordMemoryValue(t, new LongValue(lpVersionInfo.dwOSVersionInfoSize.longValue()));
		memory.setDoubleWordMemoryValue(t + 4, new LongValue(lpVersionInfo.dwMajorVersion.longValue()));
		memory.setDoubleWordMemoryValue(t + 8, new LongValue(lpVersionInfo.dwMinorVersion.longValue()));
		memory.setDoubleWordMemoryValue(t + 12, new LongValue(lpVersionInfo.dwBuildNumber.longValue()));
		memory.setDoubleWordMemoryValue(t + 16, new LongValue(lpVersionInfo.dwPlatformId.longValue()));
		memory.setText(this, t + 20, new String(lpVersionInfo.szCSDVersion));
	}

}
