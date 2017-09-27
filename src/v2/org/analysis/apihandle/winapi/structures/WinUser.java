package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

import java.util.Arrays;
import java.util.List;

/**
 * Ported from WinUser.h (user32.dll/user services).
 * 
 * @author Yen Nguyen
 */
public interface WinUser extends StdCallLibrary, WinDef, BaseTSD {

	public class MSG extends Structure {
		public HWND hWnd;
		public UINT message;
		public WPARAM wParam;
		public LPARAM lParam;
		public DWORD time;
		public POINT pt;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "hWnd", "message", "wParam", "lParam", "time", "pt" });
		}

		public static class ByReference extends MSG implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public MSG() {

		}

		public MSG(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class MENUITEMINFO extends Structure {
		public UINT cbSize;
		public UINT fMask;
		public UINT fType; // used if MIIM_TYPE (4.0) or MIIM_FTYPE (>4.0)
		public UINT fState; // used if MIIM_STATE
		public UINT wID; // used if MIIM_ID
		public HMENU hSubMenu; // used if MIIM_SUBMENU
		public HBITMAP hbmpChecked; // used if MIIM_CHECKMARKS
		public HBITMAP hbmpUnchecked; // used if MIIM_CHECKMARKS
		public ULONG_PTR dwItemData; // used if MIIM_DATA
		public WString dwTypeData; // used if MIIM_TYPE (4.0) or MIIM_STRING
									// (>4.0)
		public UINT cch; // used if MIIM_TYPE (4.0) or MIIM_STRING (>4.0)
		public HBITMAP hbmpItem; // used if MIIM_BITMAP

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "cbSize", "fMask", "fType", "fState", "wID", "hSubMenu", "hbmpChecked",
					"hbmpUnchecked", "dwItemData", "dwTypeData", "cch", "hbmpItem" });
		}
	}

	public class WNDCLASS extends Structure {
		public static interface WNDPROC extends Callback {
			LRESULT invoke(HWND hwnd, UINT unit, WPARAM wparam, LPARAM lparam);
		}

		public UINT style;
		public WNDPROC lpfnWndProc;
		public int cbClsExtra;
		public int cbWndExtra;
		public HINSTANCE hInstance;
		public HICON hIcon;
		public HCURSOR hCursor;
		public HBRUSH hbrBackground;
		public WString lpszMenuName;
		public WString lpszClassName;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "style", "lpfnWndProc", "cbClsExtra", "cbWndExtra", "hInstance",
					"hIcon", "hCursor", "hbrBackground", "lpszMenuName", "lpszClassName" });
		}

		public static class ByReference extends WNDCLASS implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public WNDCLASS() {

		}

		public WNDCLASS(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static interface DLGPROC extends Callback {
		INT_PTR invoke(HWND hwnd, UINT unit, WPARAM wparam, LPARAM lparam);
	}

	public class DLGTEMPLATE extends Structure {
		public DWORD style;
		public DWORD dwExtendedStyle;
		public WORD cdit;
		public short x;
		public short y;
		public short cx;
		public short cy;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "style", "dwExtendedStyle", "cdit", "x", "y", "cx", "cy" });
		}

		public static class ByReference extends DLGTEMPLATE implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public DLGTEMPLATE() {

		}

		public DLGTEMPLATE(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class TITLEBARINFO extends Structure {
		public DWORD cbSize;
		public RECT rcTitleBar;
		public DWORD rgstate[] = new DWORD[6];

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "cbSize", "rcTitleBar", "rgstate"});
		}

		public static class ByReference extends TITLEBARINFO implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public TITLEBARINFO() {

		}

		public TITLEBARINFO(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class COORD extends Structure {
		public short X;
		public short Y;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "X", "Y"});
		}

		public static class ByReference extends COORD implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public COORD() {

		}

		public COORD(Pointer memory) {
			super(memory);
			read();
		}
	}
}
