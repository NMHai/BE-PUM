/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetProcessWindowStation.java
 * Created date: Aug 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves a handle to the current window station for the calling process.
 * 
 * @return If the function succeeds, the return value is a handle to the window
 *         station. If the function fails, the return value is NULL. To get
 *         extended error information, call GetLastError. * @author Yen Nguyen
 *
 */
public class GetProcessWindowStation extends User32API {

	public GetProcessWindowStation() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		HANDLE ret = User32DLL.INSTANCE.GetProcessWindowStation();

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
	}

}
