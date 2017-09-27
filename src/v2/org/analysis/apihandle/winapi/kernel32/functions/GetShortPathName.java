/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetShortPathName.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * Retrieves the short path form of the specified path.
 * 
 * @param lpszLongPath
 *            The path string.
 * 
 * @param lpdzShortPath
 *            A pointer to a buffer to receive the null-terminated short form of
 *            the path that lpszLongPath specifies.
 * 
 * @param cchBuffer
 *            The size of the buffer that lpszShortPath points to, in TCHARs.
 * 
 * @return If the function succeeds, the return value is the length, in TCHARs,
 *         of the string that is copied to lpszShortPath, not including the
 *         terminating null character. If the lpszShortPath buffer is too small
 *         to contain the path, the return value is the size of the buffer, in
 *         TCHARs, that is required to hold the path and the terminating null
 *         character. If the function fails for any other reason, the return
 *         value is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetShortPathName extends Kernel32API {

	public GetShortPathName() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpszLongPath = memory.getText(this, t1);
		char[] lpdzShortPath = new char[(int) t3];
		int cchBuffer = (int) t3;
		int ret = Kernel32.INSTANCE.GetShortPathName(lpszLongPath, lpdzShortPath, cchBuffer);

		String shortPathName = new String(lpdzShortPath);
		memory.setText(this, t2, shortPathName, ret);
		
		System.out.println(String.format("LongPath: %s, ShortPath: %s", lpszLongPath, shortPathName));

		register.mov("eax", new LongValue(ret));
	}

}
