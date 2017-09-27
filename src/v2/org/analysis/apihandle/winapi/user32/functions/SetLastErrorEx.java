/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetLastErrorEx.java
 * Created date: Sep 18, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Sets the last-error code. Currently, this function is identical to the
 * SetLastError function. The second parameter is ignored.
 * 
 * @param dwErrCode
 *            The last-error code for the thread.
 * 
 * @param dwType
 *            This parameter is ignored.
 * 
 * @author Yen Nguyen
 *
 */
public class SetLastErrorEx extends User32API {

	public SetLastErrorEx() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		DWORD dwErrCode = new DWORD(t1);
		DWORD dwType = new DWORD(t2);
		
		User32DLL.INSTANCE.SetLastErrorEx(dwErrCode, dwType);
		
		register.mov("eax", new LongValue(0));
	}

}
