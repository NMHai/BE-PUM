/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32.functions
 * File name: gethostname.java
 * Created date: Mar 4, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ws2_32.functions;

import v2.org.analysis.apihandle.winapi.structures.WinSock.SOCKADDR;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32API;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The bind function associates a local address with a socket
 * 
 * int bind(
 *  _In_ SOCKET                s,
 *  _In_ const struct sockaddr *name,
 *  _In_ int                   namelen
);
 * 
 * @author HaiNM
 */
public class bind extends Ws2_32API {

	public bind() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		
		int socket = (int) t1;
//		Pointer name = new Pointer(t2);
		SOCKADDR.ByReference name = Ws2_32API.toSOCKADDR(memory, t2);		
		int namelen = (int) t3;
		System.out.println(String.format("Socket:%d, Name Pointer:%d, NameLen:%d", socket, t2, namelen));		
		int ret = Ws2_32DLL.INSTANCE.bind(socket, name, namelen);

		register.mov("eax", new LongValue(ret));
	}	
}
