/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Retrieves the path of the system directory. The system directory contains
 * system files such as dynamic-link libraries and drivers.
 * 
 * @param lpBuffer
 *            : A pointer to the buffer to receive the path. This path does not
 *            end with a backslash unless the system directory is the root
 *            directory. For example, if the system directory is named
 *            Windows\System32 on drive C, the path of the system directory
 *            retrieved by this function is C:\Windows\System32.
 * 
 * @param uSize
 *            : The maximum size of the buffer, in TCHARs.
 * 
 * @return If the function succeeds, the return value is the length, in TCHARs,
 *         of the string copied to the buffer, not including the terminating
 *         null character. If the length is greater than the size of the buffer,
 *         the return value is the size of the buffer required to hold the path,
 *         including the terminating null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemDirectory extends Kernel32API {

	/**
	 * 
	 */
	public GetSystemDirectory() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		char[] lpBuffer = new char[(int) t2];
		UINT uSize = new UINT(t2);
		UINT ret = Kernel32DLL.INSTANCE.GetSystemDirectory(lpBuffer, uSize);
		String curDir = new String(lpBuffer);
		curDir = curDir.substring(0, ret.intValue());

		memory.setText(this, t1, curDir, ret.intValue());
		System.out.println("System Directory:" + curDir);
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
