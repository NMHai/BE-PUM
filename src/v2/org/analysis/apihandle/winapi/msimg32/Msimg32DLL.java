package v2.org.analysis.apihandle.winapi.msimg32;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinUser.BLENDFUNCTION;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface Msimg32DLL extends StdCallLibrary {
	Msimg32DLL INSTANCE = (Msimg32DLL) Native.loadLibrary("msimg32", Msimg32DLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int AlphaBlend ( HDC hdcDest, int xoriginDest, int yoriginDest, int wDest, int hDest, HDC hdcSrc, int xoriginSrc, int yoriginSrc, int wSrc, int hSrc, BLENDFUNCTION ftn );

	int TransparentBlt ( HDC hdcDest, int xoriginDest, int yoriginDest, int wDest, int hDest, HDC hdcSrc, int xoriginSrc, int yoriginSrc, int wSrc, int hSrc, UINT crTransparent );

}