/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetSysColor.java
 * Created date: Mar 16, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class GetSysColor extends User32API {

	public GetSysColor() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long x = this.params.get(0);

		int nIndex = (int) x;
		DWORD ret = User32DLL.INSTANCE.GetSysColor(nIndex);
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
