/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet
 * File name: WininetDLL.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.win32.StdCallLibrary;

import v2.org.analysis.apihandle.winapi.structures.Wininet.URL_COMPONENTS;

/**
 * @author Yen Nguyen
 *
 */
public interface WininetWithoutOptionDLL extends StdCallLibrary {
	WininetWithoutOptionDLL INSTANCE = (WininetWithoutOptionDLL) Native.loadLibrary("wininet", WininetWithoutOptionDLL.class);
	WininetWithoutOptionDLL SYNC_INSTANCE = (WininetWithoutOptionDLL) Native.synchronizedLibrary(INSTANCE);


	BOOL InternetCrackUrlA(String lpszUrl, int dwUrlLength, int dwFlags, URL_COMPONENTS lpUrlComponents);
	BOOL InternetCrackUrlW(WString lpszUrl, int dwUrlLength, int dwFlags, URL_COMPONENTS lpUrlComponents);
}
