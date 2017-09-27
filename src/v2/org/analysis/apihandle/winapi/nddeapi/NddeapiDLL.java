package v2.org.analysis.apihandle.winapi.nddeapi;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface NddeapiDLL extends StdCallLibrary {
	NddeapiDLL INSTANCE = (NddeapiDLL) Native.loadLibrary("nddeapi", NddeapiDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int NDdeGetErrorString ( UINT uErrorCode, char[] lpszErrorString, DWORD cBufSize );

	int NDdeGetShareSecurity ( String lpszServer, String lpszShareName, int si, Pointer pSD, DWORD cbSD, IntByReference lpcbsdRequired );

	int NDdeGetTrustedShare ( String lpszServer, String lpszShareName, IntByReference lpdwTrustOptions, IntByReference lpdwShareModId0, IntByReference lpdwShareModId1 );

	int NDdeIsValidAppTopicList ( String targetTopic );

	int NDdeIsValidShareName ( String shareName );

	int NDdeSetShareSecurity ( String lpszServer, String lpszShareName, int si, Pointer pSD );

	int NDdeSetTrustedShare ( String lpszServer, String lpszShareName, DWORD dwTrustOptions );

	int NDdeShareAdd ( String lpszServer, UINT nLevel, Pointer pSD, byte lpBuffer, DWORD cBufSize );

	int NDdeShareDel ( String lpszServer, String lpszShareName, UINT wReserved );

	int NDdeShareEnum ( String lpszServer, UINT nLevel, byte[] lpBuffer, DWORD cBufSize, IntByReference lpnEntriesRead, int[] lpcbTotalAvailable );

	int NDdeShareGetInfo ( String lpszServer, String lpszShareName, UINT nLevel, byte[] lpBuffer, DWORD cBufSize, IntByReference lpnTotalAvailable, ShortByReference lpnItems );

	int NDdeShareSetInfo ( String lpszServer, String lpszShareName, UINT nLevel, byte lpBuffer, DWORD cBufSize, WORD sParmNum );

	int NDdeTrustedShareEnum ( String lpszServer, UINT nLevel, byte[] lpBuffer, DWORD cBufSize, IntByReference lpnEntriesRead, IntByReference lpcbTotalAvailable );

}