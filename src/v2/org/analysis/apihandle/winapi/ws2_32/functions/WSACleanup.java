/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32.functions
 * File name: WSACleanup.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ws2_32.functions;

import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32API;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32DLL;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class WSACleanup extends Ws2_32API {

	public WSACleanup() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		int ret = Ws2_32DLL.INSTANCE.WSACleanup();

		register.mov("eax", new LongValue(ret));
	}

}
