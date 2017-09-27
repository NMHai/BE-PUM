/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.comctl32.functions
 * File name: Comctl32.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.comctl32.functions;

import v2.org.analysis.apihandle.winapi.comctl32.Comctl32API;
import v2.org.analysis.apihandle.winapi.comctl32.Comctl32DLL;

/**
 * @author Yen Nguyen
 *
 */
public class InitCommonControls extends Comctl32API {
	public InitCommonControls() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		Comctl32DLL.INSTANCE.InitCommonControls();
	}

}
