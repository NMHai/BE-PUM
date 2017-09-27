/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mscoree.functions
 * File name: _CorExeMain.java
 * Created date: Aug 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.mscoree.functions;

import v2.org.analysis.apihandle.winapi.mscoree.MsCorEEAPI;
import v2.org.analysis.value.SymbolValue;

/**
 * @author Yen Nguyen
 *
 */
public class _CorExeMain extends MsCorEEAPI {

	public _CorExeMain() {
		super();
	}

	@Override
	public void execute() {
		
		System.out.println("Return value: undefined");
		register.setRegisterValue("eax", new SymbolValue("api_eax__CorExeMain@mscoree.dll"));
	}

}
