package v2.org.analysis.apihandle.winapi.mincore;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface MincoreDLL extends StdCallLibrary {
	MincoreDLL INSTANCE = (MincoreDLL) Native.loadLibrary("mincore", MincoreDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int GetFileVersionInfoSize ( String lptstrFilename, IntByReference lpdwHandle );

	int VerFindFile ( DWORD dwFlags, String szFileName, String szWinDir, String szAppDir, CHARByReference szCurDir, IntByReference lpuCurDirLen, String szDestDir, IntByReference lpuDestDirLen );

	int VerInstallFile ( DWORD uFlags, String szSrcFileName, String szDestFileName, String szSrcDir, String szDestDir, String szCurDir, String szTmpFile, IntByReference lpuTmpFileLen );

	int VerLanguageName ( DWORD wLang, String szLang, DWORD cchLang );

}