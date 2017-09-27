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
 * The listen function places a socket in a state in which it is listening for an incoming connection
 * 
 * int listen(
  _In_ SOCKET s,
  _In_ int    backlog
);
 * 
 * @author HaiNM
 */
public class listen extends Ws2_32API {

	public listen() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);		
		
		int socket = (int) t1;
		int backlog = (int) t2;
		
		System.out.println(String.format("Socket:%d, Backlog:%d", socket, backlog));		
		int ret = Ws2_32DLL.INSTANCE.listen(socket, backlog);

		register.mov("eax", new LongValue(ret));
	}

}
