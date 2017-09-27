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
 * The socket function creates a socket that is bound to a specific transport service provider.
 *
 * SOCKET WSAAPI socket(
  	_In_ int af,
  	_In_ int type,
  	_In_ int protocol
);
 * 
 * @author HaiNM
 */
public class socket extends Ws2_32API {

	public socket() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		int af = (int) t1;
		int type = (int) t2;
		int protocol = (int)t3;
		
		System.out.println(String.format("AF:%d, Type:%d, Protocol:%d", af, type, protocol));
		int ret = Ws2_32DLL.INSTANCE.socket(af, type, protocol);
		register.mov("eax", new LongValue(ret));			
	}
}
