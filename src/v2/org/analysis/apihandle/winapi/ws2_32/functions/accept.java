/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32.functions
 * File name: gethostname.java
 * Created date: Mar 4, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ws2_32.functions;

import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.structures.WinSock.SOCKADDR;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32API;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The accept function permits an incoming connection attempt on a socket.
 * 
 * SOCKET accept(
  _In_    SOCKET          s,
  _Out_   struct sockaddr *addr,
  _Inout_ int             *addrlen
);
 * 
 * @author HaiNM
 */
public class accept extends Ws2_32API {

	public accept() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		
		int s = (int) t1;
		SOCKADDR.ByReference addr = Ws2_32API.toSOCKADDR(memory, t2);
		IntByReference addrlen = new IntByReference((int) t3);
		System.out.println(String.format("Socket:%d, Addr:%d, AddrLen:%d", s, t2, addrlen));		
		int ret = Ws2_32DLL.INSTANCE.accept(s, addr, addrlen);

		register.mov("eax", new LongValue(ret));
	}

}
