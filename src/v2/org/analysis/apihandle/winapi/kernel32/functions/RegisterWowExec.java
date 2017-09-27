/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: RegisterWowExec.java
 * Created date: Oct 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * @author Yen Nguyen
 *
 */
public class RegisterWowExec extends Kernel32API {

	public RegisterWowExec() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		BOOL ret = Kernel32DLL.INSTANCE.RegisterWowExec(new DWORD(t1));
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
