/**
 * Project: be-pum
 * Package name: v2.org.analysis.apihandle.winapi.structures
 * File name: Wininet.java
 * Created date: May 6, 2017
 * Description:
 */
package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import v2.org.analysis.environment.memory.VirtualMemory;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author Yen Nguyen
 *
 */
public interface Wininet extends StdCallLibrary, WinDef, BaseTSD {

	public class URL_COMPONENTS extends Structure {

		public int dwStructSize = 60;
		public Pointer lpszScheme;
		public int dwSchemeLength = 32;
		public int nScheme = 0;

		public Pointer lpszHostName;
		public int dwHostNameLength = 256;

		public int nPort = 0;

		public Pointer lpszUserName;
		public int dwUserNameLength = 128;
		public Pointer lpszPassword;
		public int dwPasswordLength = 128;

		public Pointer lpszUrlPath;
		public int dwUrlPathLength = 1024;
		public Pointer lpszExtraInfo;
		public int dwExtraInfoLength = 1024;
		
		private VirtualMemory virtualMemory = null;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "dwStructSize", "lpszScheme", "dwSchemeLength", "nScheme",
					"lpszHostName", "dwHostNameLength", "nPort", "lpszUserName", "dwUserNameLength", "lpszPassword",
					"dwPasswordLength", "lpszUrlPath", "dwUrlPathLength", "lpszExtraInfo", "dwExtraInfoLength" });
		}

		public static class ByReference extends URL_COMPONENTS implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public URL_COMPONENTS() {
			initializeBuffers();
		}

		public URL_COMPONENTS(Pointer memory) {
			super(memory);
			read();
			initializeBuffers();
		}
		
		public void initializeBuffers() {
			virtualMemory = new VirtualMemory(2800, 4000);
			long basedPointer =  Pointer.nativeValue(virtualMemory.getBasePointer());
			lpszScheme = new Pointer(basedPointer);
			lpszHostName = new Pointer(basedPointer += dwSchemeLength);
			lpszUserName = new Pointer(basedPointer += dwHostNameLength);
			lpszPassword = new Pointer(basedPointer += dwUserNameLength);
			lpszUrlPath = new Pointer(basedPointer += dwPasswordLength);
			lpszExtraInfo = new Pointer(basedPointer += dwUrlPathLength);
		}
		
		public void release() {
			virtualMemory.free();
		}
	}

}
