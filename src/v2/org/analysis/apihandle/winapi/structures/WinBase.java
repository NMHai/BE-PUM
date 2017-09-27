package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 * Ported from Winbase.h (kernel32.dll/kernel services). Fix some mistake in
 * com.sun.jna.platform.win32.WinBase.STARTUPINFO
 * 
 * @author Yen Nguyen
 */
public interface WinBase extends StdCallLibrary, WinDef, BaseTSD {
	/**
	 * Specifies the window station, desktop, standard handles, and appearance
	 * of the main window for a process at creation time.
	 */
	public static class STARTUPINFO extends Structure {
		/**
		 * The size of the structure, in bytes.
		 */
		public DWORD cb;

		/**
		 * Reserved; must be NULL.
		 */
		public Pointer lpReserved;

		/**
		 * The name of the desktop, or the name of both the desktop and window
		 * station for this process. A backslash in the string indicates that
		 * the string includes both the desktop and window station names. For
		 * more information, see Thread Connection to a Desktop.
		 */
		public Pointer lpDesktop;

		/**
		 * For console processes, this is the title displayed in the title bar
		 * if a new console window is created. If NULL, the name of the
		 * executable file is used as the window title instead. This parameter
		 * must be NULL for GUI or console processes that do not create a new
		 * console window.
		 */
		public Pointer lpTitle;

		/**
		 * If dwFlags specifies STARTF_USEPOSITION, this member is the x offset
		 * of the upper left corner of a window if a new window is created, in
		 * pixels. Otherwise, this member is ignored.
		 * 
		 * The offset is from the upper left corner of the screen. For GUI
		 * processes, the specified position is used the first time the new
		 * process calls CreateWindow to create an overlapped window if the x
		 * parameter of CreateWindow is CW_USEDEFAULT.
		 */
		public DWORD dwX;

		/**
		 * If dwFlags specifies STARTF_USEPOSITION, this member is the y offset
		 * of the upper left corner of a window if a new window is created, in
		 * pixels. Otherwise, this member is ignored.
		 * 
		 * The offset is from the upper left corner of the screen. For GUI
		 * processes, the specified position is used the first time the new
		 * process calls CreateWindow to create an overlapped window if the y
		 * parameter of CreateWindow is CW_USEDEFAULT.
		 */
		public DWORD dwY;

		/**
		 * If dwFlags specifies STARTF_USESIZE, this member is the width of the
		 * window if a new window is created, in pixels. Otherwise, this member
		 * is ignored.
		 * 
		 * For GUI processes, this is used only the first time the new process
		 * calls CreateWindow to create an overlapped window if the nWidth
		 * parameter of CreateWindow is CW_USEDEFAULT.
		 */
		public DWORD dwXSize;

		/**
		 * If dwFlags specifies STARTF_USESIZE, this member is the height of the
		 * window if a new window is created, in pixels. Otherwise, this member
		 * is ignored.
		 * 
		 * For GUI processes, this is used only the first time the new process
		 * calls CreateWindow to create an overlapped window if the nHeight
		 * parameter of CreateWindow is CW_USEDEFAULT.
		 */
		public DWORD dwYSize;

		/**
		 * If dwFlags specifies STARTF_USECOUNTCHARS, if a new console window is
		 * created in a console process, this member specifies the screen buffer
		 * width, in character columns. Otherwise, this member is ignored.
		 */
		public DWORD dwXCountChars;

		/**
		 * If dwFlags specifies STARTF_USECOUNTCHARS, if a new console window is
		 * created in a console process, this member specifies the screen buffer
		 * height, in character rows. Otherwise, this member is ignored.
		 */
		public DWORD dwYCountChars;

		/**
		 * If dwFlags specifies STARTF_USEFILLATTRIBUTE, this member is the
		 * initial text and background colors if a new console window is created
		 * in a console application. Otherwise, this member is ignored.
		 * 
		 * This value can be any combination of the following values:
		 * FOREGROUND_BLUE, FOREGROUND_GREEN, FOREGROUND_RED,
		 * FOREGROUND_INTENSITY, BACKGROUND_BLUE, BACKGROUND_GREEN,
		 * BACKGROUND_RED, and BACKGROUND_INTENSITY. For example, the following
		 * combination of values produces red text on a white background:
		 * 
		 * FOREGROUND_RED| BACKGROUND_RED| BACKGROUND_GREEN| BACKGROUND_BLUE
		 */
		public DWORD dwFillAttribute;

		/**
		 * A bit field that determines whether certain STARTUPINFO members are
		 * used when the process creates a window.
		 */
		public DWORD dwFlags;

		/**
		 * If dwFlags specifies STARTF_USESHOWWINDOW, this member can be any of
		 * the values that can be specified in the nCmdShow parameter for the
		 * ShowWindow function, except for SW_SHOWDEFAULT. Otherwise, this
		 * member is ignored.
		 * 
		 * For GUI processes, the first time ShowWindow is called, its nCmdShow
		 * parameter is ignored wShowWindow specifies the default value. In
		 * subsequent calls to ShowWindow, the wShowWindow member is used if the
		 * nCmdShow parameter of ShowWindow is set to SW_SHOWDEFAULT.
		 */
		public WORD wShowWindow;

		/**
		 * Reserved for use by the C Run-time; must be zero.
		 */
		public WORD cbReserved2;

		/**
		 * Reserved for use by the C Run-time; must be NULL.
		 */
		public ByteByReference lpReserved2;

		/**
		 * If dwFlags specifies STARTF_USESTDHANDLES, this member is the
		 * standard input handle for the process. If STARTF_USESTDHANDLES is not
		 * specified, the default for standard input is the keyboard buffer.
		 * 
		 * If dwFlags specifies STARTF_USEHOTKEY, this member specifies a hotkey
		 * value that is sent as the wParam parameter of a WM_SETHOTKEY message
		 * to the first eligible top-level window created by the application
		 * that owns the process. If the window is created with the WS_POPUP
		 * window style, it is not eligible unless the WS_EX_APPWINDOW extended
		 * window style is also set. For more information, see CreateWindowEx.
		 * 
		 * Otherwise, this member is ignored.
		 */
		public HANDLE hStdInput;

		/**
		 * If dwFlags specifies STARTF_USESTDHANDLES, this member is the
		 * standard output handle for the process. Otherwise, this member is
		 * ignored and the default for standard output is the console window's
		 * buffer.
		 */
		public HANDLE hStdOutput;

		/**
		 * If dwFlags specifies STARTF_USESTDHANDLES, this member is the
		 * standard error handle for the process. Otherwise, this member is
		 * ignored and the default for standard error is the console window's
		 * buffer.
		 */
		public HANDLE hStdError;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "cb", "lpReserved", "lpDesktop", "lpTitle", "dwX", "dwY", "dwXSize",
					"dwYSize", "dwXCountChars", "dwYCountChars", "dwFillAttribute", "dwFlags", "wShowWindow",
					"cbReserved2", "lpReserved2", "hStdInput", "hStdOutput", "hStdError" });
		}

		public STARTUPINFO() {
			cb = new DWORD(size());
			lpReserved2 = new ByteByReference();
		}
	}

	public static class WIN32_FIND_DATA extends Structure {
		public DWORD dwFileAttributes;
		public FILETIME ftCreationTime;
		public FILETIME ftLastAccessTime;
		public FILETIME ftLastWriteTime;
		public DWORD nFileSizeHigh;
		public DWORD nFileSizeLow;
		public DWORD dwReserved0;
		public DWORD dwReserved1;
		public char cFileName[] = new char[260];
		public char cAlternateFileName[] = new char[14];

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "dwFileAttributes", "ftCreationTime", "ftLastAccessTime",
					"ftLastWriteTime", "nFileSizeHigh", "nFileSizeLow", "dwReserved0", "dwReserved1", "cFileName",
					"cAlternateFileName" });
		}
	}

	/**
	 * Contains information about the current state of both physical and virtual
	 * memory, including extended memory. The GlobalMemoryStatusEx function
	 * stores information in this structure.
	 */
	public static class MEMORYSTATUS extends Structure {
		public DWORD dwLength;
		public DWORD dwMemoryLoad;
		public SIZE_T dwTotalPhys;
		public SIZE_T dwAvailPhys;
		public SIZE_T dwTotalPageFile;
		public SIZE_T dwAvailPageFile;
		public SIZE_T dwTotalVirtual;
		public SIZE_T dwAvailVirtual;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "dwLength", "dwMemoryLoad", "dwTotalPhys", "dwAvailPhys",
					"dwTotalPageFile", "dwAvailPageFile", "dwTotalVirtual", "dwAvailVirtual" });
		}

		public MEMORYSTATUS() {
			dwLength = new DWORD(size());
		}

		public static class ByReference extends MEMORYSTATUS implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public MEMORYSTATUS(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class OFSTRUCT extends Structure {
		public BYTE cBytes;
		public BYTE fFixedDisk;
		public WORD nErrCode;
		public WORD Reserved1;
		public WORD Reserved2;
		public byte szPathName[] = new byte[128];

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "cBytes", "fFixedDisk", "nErrCode", "Reserved1", "Reserved2",
					"szPathName" });
		}

		public OFSTRUCT() {
		}

		public static class ByReference extends OFSTRUCT implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public OFSTRUCT(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class THREADENTRY32 extends Structure {
		public DWORD dwSize;
		public DWORD cntUsage;
		public DWORD th32ThreadID;
		public DWORD th32OwnerProcessID;
		public LONG tpBasePri;
		public LONG tpDeltaPri;
		public DWORD dwFlags;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "dwSize", "cntUsage", "th32ThreadID", "th32OwnerProcessID",
					"tpBasePri", "tpDeltaPri", "dwFlags" });
		}

		public THREADENTRY32() {
		}

		public static class ByReference extends THREADENTRY32 implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public THREADENTRY32(Pointer memory) {
			super(memory);
			read();
		}
	}
}
