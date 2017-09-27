/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.structures
 * File name: WinSock.java
 * Created date: Aug 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.structures;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.USHORT;
import com.sun.jna.platform.win32.WinDef.WORD;

/**
 * @author Yen Nguyen
 *
 */
public interface WinSock {
	public class WSADATA extends Structure {
		private final int WSADESCRIPTION_LEN = 256;
		private final int WSASYS_STATUS_LEN = 128;

		public WORD wVersion; // unsigned short 16bits
		public WORD wHighVersion; // unsigned short 16bits
		public byte[] szDescription = new byte[WSADESCRIPTION_LEN + 1];
		public byte[] szSystemStatus = new byte[WSASYS_STATUS_LEN + 1];
		public USHORT iMaxSockets; // unsigned short 16bits
		public USHORT iMaxUdpDg; // unsigned short 16bits
		public Pointer lpVendorInfo;

		@Override
		protected List<String> getFieldOrder() {
			// if (Platform.is64Bit()) {
			// "wVersion,wHighVersion,iMaxSockets,iMaxUdpDg,lpVendorInfo,szDescription,szSystemStatus";
			// } else {
			// "wVersion,wHighVersion,szDescription,szSystemStatus,iMaxSockets,iMaxUdpDg,lpVendorInfo";

			// Tested field order
			// "wVersion,wHighVersion,iMaxSockets,iMaxUdpDg,szDescription,szSystemStatus,lpVendorInfo";
			return Arrays.asList(new String[] { "wVersion", "wHighVersion",
					"szDescription", "szSystemStatus", "iMaxSockets", "iMaxUdpDg", "lpVendorInfo" });
		}

		public WSADATA() {
		}

		public static class ByReference extends WSADATA implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public WSADATA(Pointer memory) {
			super(memory);
			read();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("wVersion :" + this.wVersion.intValue() + "\r\n");
			sb.append("wHighVersion :" + this.wHighVersion.intValue() + "\r\n");
			sb.append("szDescription :" + this.getszDescription() + "\r\n");
			sb.append("szSystemStatus :" + this.getszSystemStatus() + "\r\n");
			sb.append("iMaxSockets :" + this.iMaxSockets.intValue() + "\r\n");
			sb.append("iMaxUdpDg :" + this.iMaxUdpDg.intValue() + "\r\n");
			sb.append("lpVendorInfo :" + this.getlpVendorInfo());
			return sb.toString();
		}

		public int getwVersion() {
			// Return last 16bits from buffer
			return (this.wVersion.intValue() & 0xFFFF);
		}

		public int getwHighVersion() {
			// Return first 16bits from buffer
			return (this.wVersion.intValue() >> 16);
		}

		public String getszDescription() {
			return new String(szDescription);
		}

		public String getszSystemStatus() {
			return new String(szSystemStatus);
		}

		public String getlpVendorInfo() {
			return "";//lpVendorInfo.getString(0);
		}
	}

	public class SOCKADDR extends Structure {
		public int sa_family; // Address family.
		public byte[] sa_data = new byte[14]; // Up to 14 bytes of direct
												// address.

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "sa_family", "sa_data" });
		}

		public SOCKADDR() {
		}

		public static class ByReference extends SOCKADDR implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public SOCKADDR(Pointer memory) {
			super(memory);
			read();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(sa_family);
			sb.append(" [");

			for (byte b : this.sa_data) {
				sb.append((int) b);
				sb.append(',');
			}
			sb.append(']');
			return sb.toString();
		}
	}

	public class SOCKADDR_IN extends Structure {
		public ConstantSocketAddressFamily sin_family; // Address family.
		public int sin_port;
		public String sin_addr;
		public byte[] sin_zero = new byte[8];

		public SOCKADDR_IN() {
		}

		public static class ByReference extends SOCKADDR_IN implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "sin_family", "sin_port", "sin_addr", "sin_zero" });
		}

		public SOCKADDR_IN(Pointer memory) {
			super(memory);
			read();
		}

		public static int byteArrayToInt(byte[] b) {
			int value = 0;
			for (int i = 0; i < 4; i++) {
				int shift = (4 - 1 - i) * 8;
				value += (b[i] & 0x000000FF) << shift;
			}
			return value;
		}

		public SOCKADDR.ByReference toSOCKADDR() {
			SOCKADDR.ByReference sa = new SOCKADDR.ByReference();
			sa.sa_family = this.sin_family.getNumVal();

			byte[] bytes = ByteBuffer.allocate(4).putInt(this.sin_port).array();
			sa.sa_data[0] = bytes[3];
			sa.sa_data[1] = bytes[2];

			String[] sp = this.sin_addr.split("\\.");
			sa.sa_data[2] = (byte) (Integer.parseInt(sp[0]));
			sa.sa_data[3] = (byte) (Integer.parseInt(sp[1]));
			sa.sa_data[4] = (byte) (Integer.parseInt(sp[2]));
			sa.sa_data[5] = (byte) (Integer.parseInt(sp[3]));

			// for (int i = 6; i < 14; i++)
			// sa.sa_data[i] = -52; //this.sin_zero[i - 6];

			return sa;
		}
	}

	public class hostent extends Structure {
		public Pointer h_name;
		public Pointer h_aliases;
		public short h_addrtype;
		public short h_length;
		public Pointer h_addr_list;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "sin_family", "h_aliases", "h_addrtype", "h_length,h_addr_list" });
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Official name: ");
			sb.append(this.h_name.getString(0));

			sb.append("\r\nAddress type: ");
			sb.append(ConstantSocketAddressFamily.getValueFromNum(this.h_addrtype).name());

			sb.append("\r\nAddress length: ");
			sb.append(this.h_length);

			sb.append("\r\nIP Address: \r\n");
			for (String str : this.h_addr_list.getStringArray(0)) {
				sb.append(str);
				sb.append("\r\n");
			}
			return sb.toString();
		}
	}
}
