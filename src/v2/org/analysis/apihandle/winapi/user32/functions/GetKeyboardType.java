/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about the current keyboard.
 * 
 * @param nTypeFlag
 *            The type of keyboard information to be retrieved.
 * 
 * @return If the function succeeds, the return value specifies the requested
 *         information.
 * 
 * @author Yen Nguyen
 *
 */
public class GetKeyboardType extends User32API {

	public GetKeyboardType() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int nTypeFlag = (int) t1;
		int ret = User32DLL.INSTANCE.GetKeyboardType(nTypeFlag);

		register.mov("eax", new LongValue(ret));
	}

}
