/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32.functions
 * File name: gethostname.java
 * Created date: Mar 4, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ws2_32.functions;

import java.nio.charset.StandardCharsets;

import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32API;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The gethostname function retrieves the standard host name for the local
 * computer.
 * 
 * @param name
 *            [out] A pointer to a buffer that receives the local host name.
 * 
 * @param namelen
 *            [in] The length, in bytes, of the buffer pointed to by the name
 *            parameter.
 * 
 * @return If no error occurs, gethostname returns zero. Otherwise, it returns
 *         SOCKET_ERROR and a specific error code can be retrieved by calling
 *         WSAGetLastError.
 * 
 * @author Yen Nguyen
 */
public class gethostname extends Ws2_32API {

	public gethostname() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		int namelen = (int) t2;
		byte[] name = new byte[namelen];
		int ret = Ws2_32DLL.INSTANCE.gethostname(name, namelen);

		register.mov("eax", new LongValue(ret));
		String str = new String(name, StandardCharsets.UTF_8);
		
		System.out.println("Hostname:" + str);

		for (int i = 0; i < namelen; i++) {
			memory.setByteMemoryValue(t1 + i, new LongValue(name[i]));
		}
	}

}
