package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.platform.win32.WinDef.HBITMAP;

//import com.sun.jna.platform.win32.WinGDI.ICONINFO;
public class ICONINFO {
	public boolean	fIcon; 
	public HBITMAP	hbmColor; 
	public HBITMAP	hbmMask; 
	public int	xHotspot; 
	public int	yHotspot; 
}
