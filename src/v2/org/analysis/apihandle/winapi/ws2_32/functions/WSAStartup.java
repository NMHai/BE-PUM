/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ws2_32.functions
 * File name: WSAStartup.java
 * Created date: Aug 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ws2_32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.structures.WinSock.WSADATA;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32API;
import v2.org.analysis.apihandle.winapi.ws2_32.Ws2_32DLL;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class WSAStartup extends Ws2_32API {

	public WSAStartup() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		int wVersionRequested = (int) t1;
		WSADATA lpWSAData = new WSADATA();

		int ret = Ws2_32DLL.INSTANCE.WSAStartup(wVersionRequested, lpWSAData);

		register.mov("eax", new LongValue(ret));

		// public WORD wVersion; // unsigned short 16bits
		// public WORD wHighVersion; // unsigned short 16bits
		// public byte[] szDescription = new byte[WSADESCRIPTION_LEN + 1];
		// public byte[] szSystemStatus = new byte[WSASYS_STATUS_LEN + 1];
		// public USHORT iMaxSockets; // unsigned short 16bits
		// public USHORT iMaxUdpDg; // unsigned short 16bits
		// public Pointer lpVendorInfo;
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
				new LongValue(lpWSAData.wVersion.longValue()));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lpWSAData.wHighVersion.longValue()));

		t2 += 4;
		for (int i = 0; i < lpWSAData.szDescription.length; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
					lpWSAData.szDescription[i]));
			t2++;
		}
		for (int i = 0; i < lpWSAData.szSystemStatus.length; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
					lpWSAData.szSystemStatus[i]));
			t2++;
		}
		
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
				new LongValue(lpWSAData.iMaxSockets.longValue()));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lpWSAData.iMaxUdpDg.longValue()));
	}

}
