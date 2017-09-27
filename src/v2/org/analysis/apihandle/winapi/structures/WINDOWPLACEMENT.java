package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;

//import com.sun.jna.platform.win32.WinUser.WINDOWPLACEMENT;
public class WINDOWPLACEMENT {
	public int	flags;
	public int	length;
	public POINT	ptMaxPosition;
	public POINT	ptMinPosition;
	public RECT	rcNormalPosition;
	public int	showCmd;
	public static int	WPF_ASYNCWINDOWPLACEMENT;
	public static int	WPF_RESTORETOMAXIMIZED;
	public static int	WPF_SETMINPOSITION;
}
