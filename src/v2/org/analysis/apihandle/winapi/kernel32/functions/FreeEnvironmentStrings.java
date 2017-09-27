/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FreeEnvironmentStrings.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.value.LongValue;

/**
 * Frees a block of environment strings.
 * 
 * @param lpszEnvironmentBlock
 *            : A pointer to a block of environment strings. The pointer to the
 *            block must be obtained by calling the GetEnvironmentStrings
 *            function.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FreeEnvironmentStrings extends Kernel32API {

	/**
	 * 
	 */
	public FreeEnvironmentStrings() {

		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t = this.params.get(0);
		BOOL ret = Kernel32DLL.INSTANCE.FreeEnvironmentStrings(new Pointer(t));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
