package v2.org.analysis.apihandle.winapi.wsock32;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

import v2.org.analysis.apihandle.winapi.structures.WinSock.WSADATA;

public interface Wsock32DLL extends StdCallLibrary {
	Wsock32DLL INSTANCE = (Wsock32DLL) Native.loadLibrary("wsock32", Wsock32DLL.class);
	Wsock32DLL SYNC_INSTANCE = (Wsock32DLL) Native.synchronizedLibrary(INSTANCE);

	// Not working, use Kernel32.INSTANCE.GetLastError instead
	// int WSASetLastError();

	int WSAStartup(int wVersionRequested, WSADATA lpWSAData);	
}