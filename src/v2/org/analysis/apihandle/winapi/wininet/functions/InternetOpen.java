/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetOpen.java
 * Created date: Mar 9, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Initializes an application's use of the WinINet functions.
 * 
 * @param lpszAgent
 *            Pointer to a null-terminated string that specifies the name of the
 *            application or entity calling the WinINet functions. This name is
 *            used as the user agent in the HTTP protocol.
 * 
 * @param dwAccessType
 *            Type of access required. This parameter can be one of the
 *            following values.
 * 
 * @param lpszProxyName
 *            Pointer to a null-terminated string that specifies the name of the
 *            proxy server(s) to use when proxy access is specified by setting
 *            dwAccessType to INTERNET_OPEN_TYPE_PROXY. Do not use an empty
 *            string, because InternetOpen will use it as the proxy name. The
 *            WinINet functions recognize only CERN type proxies (HTTP only) and
 *            the TIS FTP gateway (FTP only). If Microsoft Internet Explorer is
 *            installed, these functions also support SOCKS proxies. FTP
 *            requests can be made through a CERN type proxy either by changing
 *            them to an HTTP request or by using InternetOpenUrl. If
 *            dwAccessType is not set to INTERNET_OPEN_TYPE_PROXY, this
 *            parameter is ignored and should be NULL. For more information
 *            about listing proxy servers, see the Listing Proxy Servers section
 *            of Enabling Internet Functionality.
 * 
 * @param lpszProxyBypass
 *            Pointer to a null-terminated string that specifies an optional
 *            list of host names or IP addresses, or both, that should not be
 *            routed through the proxy when dwAccessType is set to
 *            INTERNET_OPEN_TYPE_PROXY. The list can contain wildcards. Do not
 *            use an empty string, because InternetOpen will use it as the proxy
 *            bypass list. If this parameter specifies the "<local>" macro, the
 *            function bypasses the proxy for any host name that does not
 *            contain a period. By default, WinINet will bypass the proxy for
 *            requests that use the host names "localhost", "loopback",
 *            "127.0.0.1", or "[::1]". This behavior exists because a remote
 *            proxy server typically will not resolve these addresses properly.
 * 
 * @param dwFlags
 *            Options. This parameter can be a combination of the following
 *            values.
 * 
 * @return Returns a valid handle that the application passes to subsequent
 *         WinINet functions. If InternetOpen fails, it returns NULL. To
 *         retrieve a specific error message, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class InternetOpen extends Ole32API {

	public InternetOpen() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		String lpszAgent = (t1 == 0L) ? null : memory.getText(this, t1);
		DWORD dwAccessType = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2)).getValue());
		String lpszProxyName = t3 == 0 ? null : memory.getText(this, t3);
		String lpszProxyBypass = t4 == 0 ? null : memory.getText(this, t4);
		DWORD dwFlags = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue());

		System.out.println("lpszAgent: " + lpszAgent + ", lpszProxyName: " + lpszProxyName + ", lpszProxyBypass: "
				+ lpszProxyBypass);

		HANDLE ret = WininetDLL.INSTANCE.InternetOpen(lpszAgent, dwAccessType, lpszProxyName, lpszProxyBypass, dwFlags);

		register.mov("eax", (ret == null) ? new LongValue(0) : new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
