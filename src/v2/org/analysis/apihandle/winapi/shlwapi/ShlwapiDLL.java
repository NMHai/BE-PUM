/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shlwapi
 * File name: ShlwapiDLL.java
 * Created date: Aug 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shlwapi;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.ShellAPI;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface ShlwapiDLL extends ShellAPI, StdCallLibrary {
	ShlwapiDLL INSTANCE = (ShlwapiDLL) Native.loadLibrary("shlwapi", ShlwapiDLL.class, W32APIOptions.DEFAULT_OPTIONS);

	/**
	 * Searches a path for a file name.
	 * 
	 * @param pPath
	 *            A pointer to a null-terminated string of maximum length
	 *            MAX_PATH that contains the path to search.
	 * 
	 * @return Returns a pointer to the address of the string if successful, or
	 *         a pointer to the beginning of the path otherwise.
	 */
	String PathFindFileName(/* _In_ */String pPath);

	/**
	 * Removes the decoration from a path string.
	 * 
	 * @param pszPath
	 *            A null-terminated string of length MAX_PATH that contains the
	 *            path. When the function returns, pszPath points to the
	 *            undecorated string.
	 */
	void PathUndecorate(/* _Inout_ */char[] pszPath);
	
	int StrChrI ( CHARByReference pszStart, char wMatch );
}
