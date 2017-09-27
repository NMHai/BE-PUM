/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the current directory for the current process.
 * 
 * @param nBufferLength
 *            : The length of the buffer for the current directory string, in
 *            TCHARs. The buffer length must include room for a terminating null
 *            character.
 * 
 * @param lpBuffer
 *            : A pointer to the buffer that receives the current directory
 *            string. This null-terminated string specifies the absolute path to
 *            the current directory. To determine the required buffer size, set
 *            this parameter to NULL and the nBufferLength parameter to 0.
 * 
 * @return If the function succeeds, the return value specifies the number of
 *         characters that are written to the buffer, not including the
 *         terminating null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentDirectory extends Kernel32API {

	public static boolean IS_SET = false;

	/**
	 * 
	 */
	public GetCurrentDirectory() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		char[] lpBuffer = new char[(int) t1];

		String curDir = Storage.CurrentDirectory;
		int ret = 0;

		if (IS_SET) {
			ret = curDir.length();
		} else {
			ret = Kernel32DLL.INSTANCE.GetCurrentDirectory(new DWORD(t1), lpBuffer);

			curDir = new String(lpBuffer);
			curDir = curDir.substring(0, ret);
		}

		memory.setText(this, this.params.get(1), curDir, curDir.length());
		System.out.println("Current Directory:" + curDir);
		register.mov("eax", new LongValue(ret));
	}

}
