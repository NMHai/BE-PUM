package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HICON;

public interface ShellApi {

	public static class SHFILEINFO extends Structure {
		public HICON hIcon; // out: icon
		public int iIcon; // out: icon index
		public DWORD dwAttributes; // out: SFGAO_ flags
		public char[] szDisplayName = new char[260]; // out: display name (or path)
		public char[] szTypeName = new char[80]; // out: type name

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "hIcon", "iIcon", "dwAttributes", "szDisplayName", "szTypeName" });
		}

		public SHFILEINFO() {
		}

		public static class ByReference extends SHFILEINFO implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public SHFILEINFO(Pointer memory) {
			super(memory);
			read();
		}
	}

}
