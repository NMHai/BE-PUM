/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: FtpGetFile.java
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
 * Retrieves a file from the FTP server and stores it under the specified file
 * name, creating a new local file in the process.
 * 
 * @param hConnect
 *            Handle to an FTP session.
 * 
 * @param lpszRemoteFile
 *            Pointer to a null-terminated string that contains the name of the
 *            file to be retrieved.
 * 
 * @param lpszNewFile
 *            Pointer to a null-terminated string that contains the name of the
 *            file to be created on the local system.
 * 
 * @param fFailIfExists
 *            Indicates whether the function should proceed if a local file of
 *            the specified name already exists. If fFailIfExists is TRUE and
 *            the local file exists, FtpGetFile fails.
 * 
 * @param dwFlagsAndAttributes
 *            File attributes for the new file. This parameter can be any
 *            combination of the FILE_ATTRIBUTE_* flags used by the CreateFile
 *            function.
 * 
 * @param dwFlags
 *            Controls how the function will handle the file download. The first
 *            set of flag values indicates the conditions under which the
 *            transfer occurs. These transfer type flags can be used in
 *            combination with the second set of flags that control caching.
 * 
 * @param dwContext
 *            Pointer to a variable that contains the application-defined value
 *            that associates this search with any application data. This is
 *            used only if the application has already called
 *            InternetSetStatusCallback to set up a status callback function.
 * 
 * @return Returns TRUE if successful, or FALSE otherwise. To get a specific
 *         error message, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class FtpGetFile extends WininetAPI {

	public FtpGetFile() {
		super();
		NUM_OF_PARMS = 7;
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

		HANDLE hConnect = new HANDLE(new Pointer(t1));
		String lpszRemoteFile = (t2 == 0L) ? null : memory.getText(this, t2);
		String lpszNewFile = (t3 == 0L) ? null : memory.getText(this, t3);
		boolean fFailIfExists = (t4 == 0L) ? false : true;
		int dwFlagsAndAttributes = (int) t5;
		int dwFlags = (int) t6;
		DWORD_PTR dwContext = new DWORD_PTR(t7);		
		boolean ret = WininetDLL.INSTANCE.FtpGetFile(hConnect, lpszRemoteFile, lpszNewFile, fFailIfExists, dwFlagsAndAttributes, dwFlags, dwContext);
		
		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
