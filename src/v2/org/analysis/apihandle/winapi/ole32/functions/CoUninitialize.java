/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32.functions
 * File name: CoUninitialize.java
 * Created date: Mar 9, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32.functions;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.ole32.Ole32DLL;

/**
 * Closes the COM library on the current thread, unloads all DLLs loaded by the
 * thread, frees any other resources that the thread maintains, and forces all
 * RPC connections on the thread to close.
 * 
 * @author Yen Nguyen
 *
 */
public class CoUninitialize extends Ole32API {

	public CoUninitialize() {
		super();
		NUM_OF_PARMS = 0;
	}


	@Override
	public void execute() {
		Ole32DLL.INSTANCE.CoUninitialize();
	}

}
