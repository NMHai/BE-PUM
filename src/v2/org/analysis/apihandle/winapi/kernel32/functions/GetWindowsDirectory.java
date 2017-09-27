/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetWindowsDirectory.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Retrieves the path of the Windows directory.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the path. This path does not
 *            end with a backslash unless the Windows directory is the root
 *            directory. For example, if the Windows directory is named Windows
 *            on drive C, the path of the Windows directory retrieved by this
 *            function is C:\Windows. If the system was installed in the root
 *            directory of drive C, the path retrieved is C:\.
 * 
 * @param uSize
 *            The maximum size of the buffer specified by the lpBuffer
 *            parameter, in TCHARs. This value should be set to MAX_PATH.
 * 
 * @return If the function succeeds, the return value is the length of the
 *         string copied to the buffer, in TCHARs, not including the terminating
 *         null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowsDirectory extends Kernel32API {

	/**
	 * 
	 */
	public GetWindowsDirectory() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		char[] lpBuffer = new char[(int) t2];
		UINT uSize = new UINT(t2);
		UINT ret = Kernel32DLL.INSTANCE.GetWindowsDirectory(lpBuffer, uSize);
		String curDir = new String(lpBuffer);
		curDir = curDir.substring(0, ret.intValue());

		memory.setText(this, t1, curDir, ret.intValue());
		System.out.println("Windows Directory:" + curDir);
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
