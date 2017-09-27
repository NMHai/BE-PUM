/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32
 * File name: Kernel32DLL.java
 * Created date: Jan 30, 2015
 * Decription:
 * 
 */
package v2.org.analysis.apihandle.winapi.psapi;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */

public interface PsapiDLL extends StdCallLibrary {
	PsapiDLL INSTANCE = (PsapiDLL) Native.loadLibrary("psapi", PsapiDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	PsapiDLL SYNC_INSTANCE = (PsapiDLL) Native.synchronizedLibrary(INSTANCE);

	int GetModuleBaseName(HANDLE hProcess, HMODULE hModule, char[] lpBaseName, int nSize);
}
