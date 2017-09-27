/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetFilePointer.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class _llseek extends Kernel32API {

	public _llseek() {
		super();
		NUM_OF_PARMS = 3;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		LONG ret = Kernel32DLL.INSTANCE._llseek((int) t1, new LONG(t2), (int) t3);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
