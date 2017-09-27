
package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 
 * @author Yen Nguyen
 */
public interface GeneratedStruct extends StdCallLibrary, WinNT {

	public static class Example extends Structure {
		public int nBufferLength;
		public char[] lpBuffer;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { 
				"nBufferLength",
				"lpBuffer"
			});
		}

		public static class ByReference extends Example implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public Example() {

		}

		public Example(Pointer memory) {
			super(memory);
			read();
		}
	}

	//////////////////////////////////////////////////////////////////////////////////// 

	public static class IsValidAcl extends Structure {
		public int nBufferLength;
		public char[] lpBuffer;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { 
				"nBufferLength",
				"lpBuffer"
			});
		}

		public static class ByReference extends IsValidAcl implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public IsValidAcl() {

		}

		public IsValidAcl(Pointer memory) {
			super(memory);
			read();
		}
	}

	//////////////////////////////////////////////////////////////////////////////////// \$newStruct
	
}
