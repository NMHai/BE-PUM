/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetConnect.java
 * Created date: Mar 13, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Opens an File Transfer Protocol (FTP) or HTTP session for a given site.
 * 
 * @param hInternet
 *            Handle returned by a previous call to InternetOpen.
 * 
 * @param lpszServerName
 *            Pointer to a null-terminated string that specifies the host name
 *            of an Internet server. Alternately, the string can contain the IP
 *            number of the site, in ASCII dotted-decimal format (for example,
 *            11.0.1.45).
 * 
 * @param nServerPortTransmission
 *            Control Protocol/Internet Protocol (TCP/IP) port on the server.
 *            These flags set only the port that is used. The service is set by
 *            the value of dwService. This parameter can be one of the following
 *            values.
 * 
 * @param lpszUsername
 *            Pointer to a null-terminated string that specifies the name of the
 *            user to log on. If this parameter is NULL, the function uses an
 *            appropriate default. For the FTP protocol, the default is
 *            "anonymous".
 * 
 * @param lpszPassword
 *            Pointer to a null-terminated string that contains the password to
 *            use to log on. If both lpszPassword and lpszUsername are NULL, the
 *            function uses the default "anonymous" password. In the case of
 *            FTP, the default password is the user's email name. If
 *            lpszPassword is NULL, but lpszUsername is not NULL, the function
 *            uses a blank password.
 * 
 * @param dwService
 *            Type of service to access. This parameter can be one of the
 *            following values.
 * 
 * @param dwFlags
 *            Options specific to the service used. If dwService is
 *            INTERNET_SERVICE_FTP, INTERNET_FLAG_PASSIVE causes the application
 *            to use passive FTP semantics.
 * 
 * @param dwContext
 *            Pointer to a variable that contains an application-defined value
 *            that is used to identify the application context for the returned
 *            handle in callbacks.
 * 
 * @return Returns a valid handle to the session if the connection is
 *         successful, or NULL otherwise. To retrieve extended error
 *         information, call GetLastError. An application can also use
 *         InternetGetLastResponseInfo to determine why access to the service
 *         was denied.
 * 
 * @author Yen Nguyen
 *
 */
public class InternetConnect extends WininetAPI {

	public InternetConnect() {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);
		long t8 = this.params.get(7);

		HANDLE hInternet = new HANDLE(new Pointer(t1));
		String lpszServerName = (t2 == 0L) ? null : memory.getText(this, t2);
		short nServerPort = (short) t3;
		String lpszUsername = (t4 == 0L) ? null : memory.getText(this, t4);
		String lpszPassword = (t5 == 0L) ? null : memory.getText(this, t5);
		int dwService = (int) t6;
		int dwFlags = (int) t7;
		DWORD_PTR dwContext = new DWORD_PTR(t8);
		
		System.out.println("lpszServerName:" + lpszServerName +", " + 
				"nServerPort:" + nServerPort +", " + 
				"lpszUsername:" + lpszUsername +", " + 
				"lpszPassword:" + lpszPassword +", " + 
				"dwService:" + dwService +", ");

		HANDLE ret = WininetDLL.INSTANCE.InternetConnect(hInternet, lpszServerName, nServerPort, lpszUsername,
				lpszPassword, dwService, dwFlags, dwContext);

		register.mov("eax", new LongValue(ret == null ? 0L : Pointer.nativeValue(ret.getPointer())));
	}

}
