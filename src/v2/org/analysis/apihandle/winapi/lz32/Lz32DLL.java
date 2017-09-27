package v2.org.analysis.apihandle.winapi.lz32;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.OFSTRUCT;


public interface Lz32DLL extends StdCallLibrary {
	Lz32DLL INSTANCE = (Lz32DLL) Native.loadLibrary("lz32", Lz32DLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int GetExpandedName ( String lpszSource, String lpszBuffer );

	void LZClose ( int hFile );

	int LZCopy ( int hfSource, int hfDest );

	int LZInit ( int hfSource );

	int LZOpenFile ( String lpFileName, OFSTRUCT lpReOpenBuf, WORD wStyle );

	int LZRead ( int hFile, byte[] lpBuffer, int cbRead );

	int LZSeek ( int hFile, LONG lOffset, int iOrigin );

}