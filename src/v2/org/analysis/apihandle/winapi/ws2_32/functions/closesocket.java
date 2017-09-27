/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32.functions
 * File name: gethostname.java
 * Created date: Mar 4, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ws2_32.functions;

import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32API;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The closesocket function closes an existing socket.
 * 
 * int closesocket(
 *  _In_ SOCKET s
);
 * 
 * @author HaiNM
 */
public class closesocket extends Ws2_32API {

	public closesocket() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);	
		int socket = (int) t1;
		System.out.println("Close Socket:" + socket);
		int ret = Ws2_32DLL.INSTANCE.closesocket(socket);
		register.setRegisterValue("eax", new LongValue(ret));		
	}

}
