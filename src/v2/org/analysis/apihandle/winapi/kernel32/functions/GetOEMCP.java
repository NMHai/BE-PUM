/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetOEMCP.java
 * Created date: Sep 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Returns the current original equipment manufacturer (OEM) code page
 * identifier for the operating system.
 * 
 * @return Returns the current OEM code page identifier for the operating
 *         system.
 * 
 * @author Yen Nguyen
 *
 */
public class GetOEMCP extends Kernel32API {

	public GetOEMCP() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		UINT ret = Kernel32DLL.INSTANCE.GetOEMCP();

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
