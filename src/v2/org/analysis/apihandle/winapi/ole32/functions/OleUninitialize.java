/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32.functions
 * File name: OleUninitialize.java
 * Created date: Aug 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32.functions;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.ole32.Ole32DLL;

/**
 * Closes the COM library on the apartment, releases any class factories, other
 * COM objects, or servers held by the apartment, disables RPC on the apartment,
 * and frees any resources the apartment maintains.
 * 
 * @author Yen Nguyen
 *
 */
public class OleUninitialize extends Ole32API {
	public OleUninitialize() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		Ole32DLL.INSTANCE.OleUninitialize();
	}

}
