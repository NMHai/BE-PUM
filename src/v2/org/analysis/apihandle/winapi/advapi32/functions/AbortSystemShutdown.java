/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: AbortSystemShutdown.java
 * Created date: Mar 9, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * Stops a system shutdown that has been initiated.
 * 
 * @param lpMachineName
 *            [in, optional] The network name of the computer where the shutdown
 *            is to be stopped. If lpMachineName is NULL or an empty string, the
 *            function stops the shutdown on the local computer.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class AbortSystemShutdown extends Advapi32API {

	public AbortSystemShutdown() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String lpMachineName = (t1 == 0) ? null : memory.getText(this, t1);
		System.out.println("lpMachineName: " + lpMachineName);
		BOOL ret = Advapi32DLL.INSTANCE.AbortSystemShutdown(lpMachineName);

		register.mov("eax", new LongValue(ret.longValue()));
//		register.mov("eax", new SymbolValue(SymbolValue.generateString(this)));
	}

}
