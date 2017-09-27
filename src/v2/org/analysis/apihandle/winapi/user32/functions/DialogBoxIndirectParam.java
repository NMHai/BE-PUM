/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DialogBoxIndirectParam.java
 * Created date: Aug 18, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.SymbolValue;

/**
 * @author Yen Nguyen
 *
 */
public class DialogBoxIndirectParam extends User32API {

	public DialogBoxIndirectParam() {
		super();
		NUM_OF_PARMS = 5;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.apihandle.winapi.API#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		register.setRegisterValue("eax", new SymbolValue("api_eax_" + getFullName()));
	}

}
