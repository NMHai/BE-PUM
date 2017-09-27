package v2.org.analysis.apihandle.winapi.rstrtmgr;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.RM_PROCESS_INFO;
import v2.org.analysis.apihandle.structures.RM_UNIQUE_PROCESS;


public interface RstrtmgrDLL extends StdCallLibrary {
	RstrtmgrDLL INSTANCE = (RstrtmgrDLL) Native.loadLibrary("rstrtmgr", RstrtmgrDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int RmAddFilter ( DWORD dwSessionHandle, CHARByReference strFilename, RM_UNIQUE_PROCESS Application, CHARByReference strShortServiceName, int ActionType );

	int RmCancelCurrentTask ( DWORD dwSessionHandle );

	int RmEndSession ( DWORD dwSessionHandle );

	int RmGetFilterList ( DWORD dwSessionHandle, byte[] pbFilterBuf, DWORD cbFilterBuf, IntByReference cbFilterBufNeeded );

	int RmGetList ( DWORD dwSessionHandle, IntByReference pnProcInfoNeeded, IntByReference pnProcInfo, RM_PROCESS_INFO rgAffectedApps[], IntByReference lpdwRebootReasons );

	int RmJoinSession ( IntByReference pSessionHandle, char strSessionKey[] );

	int RmRegisterResources ( DWORD dwSessionHandle, UINT nFiles, CHARByReference rgsFilenames[], UINT nApplications, RM_UNIQUE_PROCESS rgApplications[], UINT nServices, CHARByReference rgsServiceNames[] );

	int RmRemoveFilter ( DWORD dwSessionHandle, CHARByReference strFilename, RM_UNIQUE_PROCESS Application, CHARByReference strShortServiceName );

	int RmStartSession ( IntByReference pSessionHandle, DWORD dwSessionFlags, char strSessionKey[] );

}