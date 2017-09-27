package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.UINT;

public interface Winnls {

	public class CPINFOEX extends Structure {
		public UINT  MaxCharSize;
		public BYTE  DefaultChar[] = new BYTE[2];
		public BYTE  LeadByte[] = new BYTE[12];
		public char UnicodeDefaultChar;
		public UINT  CodePage;
		public byte CodePageName[] = new byte[260];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "MaxCharSize", "DefaultChar", "LeadByte",
					"UnicodeDefaultChar", "CodePage", "CodePageName"});
		}

		public static class ByReference extends CPINFOEX implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public CPINFOEX() {

		}

		public CPINFOEX(Pointer memory) {
			super(memory);
			read();
		}
	}
	
}
