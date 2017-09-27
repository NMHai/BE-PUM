package v2.org.analysis.apihandle.winapi.ws2_32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

import v2.org.analysis.apihandle.winapi.structures.WinSock.SOCKADDR;
import v2.org.analysis.apihandle.winapi.structures.WinSock.WSADATA;
import v2.org.analysis.apihandle.winapi.structures.WinSock.hostent;

public interface Ws2_32DLL extends StdCallLibrary {
	Ws2_32DLL INSTANCE = (Ws2_32DLL) Native.loadLibrary("ws2_32", Ws2_32DLL.class);
	Ws2_32DLL SYNC_INSTANCE = (Ws2_32DLL) Native.synchronizedLibrary(INSTANCE);

	// Not working, use Kernel32.INSTANCE.GetLastError instead
	// int WSASetLastError();

	int WSAStartup(int wVersionRequested, WSADATA lpWSAData);

	int WSACleanup();

	int socket(int af, int type, int protocol);

	int closesocket(int s);

	int connect(int s, SOCKADDR.ByReference name, int namelen);
//	int connect(int s, Pointer name, int namelen);

	int bind(int s, SOCKADDR.ByReference name, int namelen);
//	int bind(int s, Pointer name, int namelen);

	int accept(int s, SOCKADDR.ByReference addr, IntByReference addrlen);
//	int accept(int s, Pointer addr, IntByReference addrlen);

	int listen(int s, int backlog);

	/**
	 * The gethostname function retrieves the standard host name for the local
	 * computer.
	 * 
	 * @param name
	 *            [out] A pointer to a buffer that receives the local host name.
	 * 
	 * @param namelen
	 *            [in] The length, in bytes, of the buffer pointed to by the
	 *            name parameter.
	 * 
	 * @return If no error occurs, gethostname returns zero. Otherwise, it
	 *         returns SOCKET_ERROR and a specific error code can be retrieved
	 *         by calling WSAGetLastError.
	 */
	int gethostname(byte[] name, int namelen);

	hostent gethostbyname(String name);

	int send(int s, String buf, int len, int flags);

	int recv(int s, Pointer buf, int len, int flags);

	int shutdown(int s, int how);
}