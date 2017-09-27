/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class _lclose extends Kernel32API {

	public _lclose() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t = this.params.get(0);
		System.out.println("Object Handle:" + t);

		int ret = Kernel32DLL.INSTANCE._lclose((int) t);
		register.mov("eax", new LongValue(ret));

		System.out.println("Return Value:" + ret);
	}

}
