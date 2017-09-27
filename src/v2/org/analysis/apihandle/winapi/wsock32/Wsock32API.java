/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32
 * File name: Ws2_32API.java
 * Created date: Aug 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wsock32;

import v2.org.analysis.apihandle.winapi.API;
import v2.org.analysis.apihandle.winapi.structures.WinSock.SOCKADDR;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public abstract class Wsock32API extends API {
 
	public Wsock32API() {
		this.libraryName = "Wsock32.dll";
	}
	
	public static SOCKADDR.ByReference toSOCKADDR(MemoryV2 memory, long addr) {
		SOCKADDR.ByReference sa = new SOCKADDR.ByReference();
		sa.sa_family = (int) ((LongValue)memory.getWordMemoryValue(addr)).getValue();
		addr += 2;

		for (int i=0; i<14; i++) {
			sa.sa_data[i] = (byte) ((LongValue)memory.getByteMemoryValue(addr + i)).getValue();;
		}
		// for (int i = 6; i < 14; i++)
		// sa.sa_data[i] = -52; //this.sin_zero[i - 6];
		
		System.out.println("Sa_Family:" + sa.sa_family);
		for (int i=0; i<14; i++) {
			System.out.println(String.format("sa_data[%d]:%d", i, sa.sa_data[i]));
		}
		
		return sa;
	}

}
