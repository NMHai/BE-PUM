/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetLastError.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the current Windows ANSI code page identifier for the operating
 * system.
 * 
 * @return Returns the current Windows ANSI code page (ACP) identifier for the
 *         operating system. See Code Page Identifiers for a list of identifiers
 *         for Windows ANSI code pages and other code pages.
 * 
 * @author Yen Nguyen
 *
 */
public class GetACP extends Kernel32API {

	public GetACP() {
		super();
		NUM_OF_PARMS = 0;
	}


	@Override
	public void execute() {
		Environment env = curState.getEnvironement();
		Register register = env.getRegister();

		// This function has no parameters.
		UINT ret = Kernel32DLL.INSTANCE.GetACP();
		System.out.println("ACP:" + ret.longValue());

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
