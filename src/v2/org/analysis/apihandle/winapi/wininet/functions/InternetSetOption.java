/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetSetOption.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

/**
 * Sets an Internet option.
 * 
 * @param hInternet
 *            Handle on which to set information.
 * 
 * @param dwOption
 *            Internet option to be set. This can be one of the Option Flags
 *            values.
 * 
 * @param lpBuffer
 *            Pointer to a buffer that contains the option setting.
 * 
 * @param dwBufferLength
 *            Size of the lpBuffer buffer. If lpBuffer contains a string, the
 *            size is in TCHARs. If lpBuffer contains anything other than a
 *            string, the size is in bytes.
 * 
 * @return Returns TRUE if successful, or FALSE otherwise. To get a specific
 *         error message, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class InternetSetOption extends WininetAPI {

	public InternetSetOption() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HANDLE hInternet = new HANDLE(new Pointer(t1));
		int dwOption = (int) (t2);
		Pointer lpBuffer = memory.getPointer(t3);
		int dwBufferLength = (int) (t4);
		BOOL ret = WininetDLL.INSTANCE.InternetSetOption(hInternet, dwOption, lpBuffer, dwBufferLength);

		long value = ret == null ? 0 : ret.longValue();
		register.mov("eax", new LongValue(value));
	}

}
