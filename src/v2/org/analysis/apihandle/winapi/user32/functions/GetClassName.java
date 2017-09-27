/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetClassName.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * This function retrieves the name of the class to which the specified window
 * belongs.
 * 
 * @param hWnd
 *            Handle to the window and, indirectly, the class to which the
 *            window belongs.
 * 
 * @param lpClassName
 *            Long pointer to the buffer that is to receive the class name
 *            string.
 * 
 * @param nMaxCount
 *            Specifies the length, in characters, of the buffer pointed to by
 *            the lpClassName parameter. The class name string is truncated if
 *            it is longer than the buffer.
 * 
 * @return The number of characters copied to the specified buffer indicates
 *         success. Zero indicates failure. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetClassName extends User32API {

	public GetClassName() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HWND hWnd = new HWND(new Pointer(t1));
		char[] lpClassName = new char[(int) t3];
		int nMaxCount = (int) t3;
		int ret = User32.INSTANCE.GetClassName(hWnd, lpClassName, nMaxCount);

		String className = new String(lpClassName);
		memory.setText(this, t2, className, ret);

		register.mov("eax", new LongValue(ret));
	}

}
