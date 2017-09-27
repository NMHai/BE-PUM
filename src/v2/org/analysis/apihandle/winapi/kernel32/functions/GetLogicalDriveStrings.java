/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetLogicalDriveStrings.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * The GetLogicalDriveStrings function fills a buffer with strings that specify
 * valid drives in the system.
 * 
 * @param nBufferLength
 *            Maximum size of the buffer pointed to by lpBuffer, in TCHARs. This
 *            size does not include the terminating null character. If this
 *            parameter is zero, lpBuffer is not used.
 * 
 * @param lpBuffer
 *            Pointer to a buffer that receives a series of null-terminated
 *            strings, one for each valid drive in the system, plus with an
 *            additional null character. Each string is a device name.
 * 
 * @return If the function succeeds, the return value is the length, in
 *         characters, of the strings copied to the buffer, not including the
 *         terminating null character. Note that an ANSI-ASCII null character
 *         uses one byte, but a Unicode null character uses two bytes. If the
 *         buffer is not large enough, the return value is greater than
 *         nBufferLength. It is the size of the buffer required to hold the
 *         drive strings. If the function fails, the return value is zero. To
 *         get extended error information, use the GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLogicalDriveStrings extends Kernel32API {

	public GetLogicalDriveStrings() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		DWORD nBufferLength = new DWORD(t1);
		char[] lpBuffer = new char[(int) t1];
		DWORD ret = Kernel32.INSTANCE.GetLogicalDriveStrings(nBufferLength, lpBuffer);

		String logicalDriveStrings = new String(lpBuffer);
		memory.setText(this, t2, logicalDriveStrings, ret.intValue());
		System.out.println("Logical Drive Strings:" + logicalDriveStrings);
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
