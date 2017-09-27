package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 
 * @author Yen Nguyen
 *
 */
public interface Internal extends StdCallLibrary, WinDef, BaseTSD {
	public static class _startupinfo extends Structure {
		public int newmode;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "newmode" });
		}

		public static class ByReference extends _startupinfo implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public _startupinfo() {

		}

		public _startupinfo(Pointer memory) {
			super(memory);
			read();
		}
	}
}
