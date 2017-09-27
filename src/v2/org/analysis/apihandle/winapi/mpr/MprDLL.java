package v2.org.analysis.apihandle.winapi.mpr;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.CONNECTDLGSTRUCT;
import v2.org.analysis.apihandle.structures.DISCDLGSTRUCT;
import v2.org.analysis.apihandle.structures.NETCONNECTINFOSTRUCT;
import v2.org.analysis.apihandle.structures.NETINFOSTRUCT;
import v2.org.analysis.apihandle.structures.NETRESOURCE;


public interface MprDLL extends StdCallLibrary {
	MprDLL INSTANCE = (MprDLL) Native.loadLibrary("mpr", MprDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int MultinetGetConnectionPerformance ( NETRESOURCE lpNetResource, NETCONNECTINFOSTRUCT lpNetConnectInfoStruct );

	int WNetAddConnection ( String lpRemoteName, String lpPassword, String lpLocalName );

	int WNetAddConnection2 ( NETRESOURCE lpNetResource, String lpPassword, String lpUsername, DWORD dwFlags );

	int WNetAddConnection3 ( HWND hwndOwner, NETRESOURCE lpNetResource, String lpPassword, String lpUserName, DWORD dwFlags );

	int WNetCancelConnection ( String lpName, BOOL fForce );

	int WNetCancelConnection2 ( String lpName, DWORD dwFlags, BOOL fForce );

	int WNetCloseEnum ( HANDLE hEnum );

	int WNetConnectionDialog ( HWND hwnd, DWORD dwType );

	int WNetConnectionDialog1 ( CONNECTDLGSTRUCT lpConnDlgStruct );

	int WNetDisconnectDialog ( HWND hwnd, DWORD dwType );

	int WNetDisconnectDialog1 ( DISCDLGSTRUCT lpConnDlgStruct );

	int WNetGetConnection ( String lpLocalName, String lpRemoteName, IntByReference lpnLength );

	int WNetGetLastError ( IntByReference lpError, char[] lpErrorBuf, DWORD nErrorBufSize, char[] lpNameBuf, DWORD nNameBufSize );

	int WNetGetNetworkInformation ( String lpProvider, NETINFOSTRUCT lpNetInfoStruct );

	int WNetGetProviderName ( DWORD dwNetType, char[] lpProviderName, IntByReference lpBufferSize );

	int WNetGetUser ( String lpName, char[] lpUserName, IntByReference lpnLength );

	int WNetOpenEnum ( DWORD dwScope, DWORD dwType, DWORD dwUsage, NETRESOURCE lpNetResource, HANDLE lphEnum );

	int WNetUseConnection ( HWND hwndOwner, NETRESOURCE lpNetResource, String lpPassword, String lpUserID, DWORD dwFlags, char[] lpAccessName, IntByReference lpBufferSize, IntByReference lpResult );

}