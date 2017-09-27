/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: IsValidCodePage.java
 * Created date: Nov 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Determines if a specified code page is valid.
 * 
 * @param CodePage
 *            Code page identifier for the code page to check.
 * 
 * @return Returns a nonzero value if the code page is valid, or 0 if the code
 *         page is invalid.
 * 
 * @author Yen Nguyen
 *
 */
public class IsValidCodePage extends Kernel32API {

	public IsValidCodePage() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		UINT CodePage = new UINT(t1);
		BOOL ret = Kernel32DLL.INSTANCE.IsValidCodePage(CodePage);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
