/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.comctl32
 * File name: Comctl32DLL.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.comctl32;

import v2.org.analysis.apihandle.structures.TRACKMOUSEEVENT;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author Yen Nguyen
 *
 */
public interface Comctl32DLL extends StdCallLibrary {
	Comctl32DLL INSTANCE = (Comctl32DLL) Native.loadLibrary("comctl32", Comctl32DLL.class);
	Comctl32DLL SYNC_INSTANCE = (Comctl32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Registers and initializes certain common control window classes. This
	 * function is obsolete. New applications should use the
	 * InitCommonControlsEx function.
	 */
	void InitCommonControls();
	
	int TaskDialog ( HWND hWndParent, HINSTANCE hInstance, CHARByReference pszWindowTitle, CHARByReference pszMainInstruction, CHARByReference pszContent, int dwCommonButtons, CHARByReference pszIcon, IntByReference pnButton );

	int _TrackMouseEvent ( TRACKMOUSEEVENT lpEventTrack );

}
