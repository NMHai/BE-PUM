package v2.org.analysis.apihandle.winapi.wer;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.WER_REPORT_INFORMATION;


public interface WerDLL extends StdCallLibrary {
	WerDLL INSTANCE = (WerDLL) Native.loadLibrary("wer", WerDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int WerAddExcludedApplication ( CHARByReference pwzExeName, BOOL bAllUsers );

	int WerRemoveExcludedApplication ( CHARByReference pwzExeName, BOOL bAllUsers );

	int WerReportAddFile ( HANDLE hReportHandle, CHARByReference pwzPath, int repFileType, DWORD dwFileFlags );

	int WerReportCloseHandle ( HANDLE hReportHandle );

	int WerReportCreate ( CHARByReference pwzEventType, int repType, WER_REPORT_INFORMATION pReportInformation, HANDLEByReference phReportHandle );

	int WerReportSetParameter ( HANDLE hReportHandle, DWORD dwparamID, CHARByReference pwzName, CHARByReference pwzValue );

	int WerReportSetUIOption ( HANDLE hReportHandle, int repUITypeID, CHARByReference pwzValue );

}