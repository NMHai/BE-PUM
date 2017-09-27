/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.mscoree
 * File name: MsCorEEDLL.java
 * Created date: Aug 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.mscoree;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface MsCorEEDLL extends StdCallLibrary {
	MsCorEEDLL INSTANCE = (MsCorEEDLL) Native.loadLibrary("mscoree", MsCorEEDLL.class);
	MsCorEEDLL SYNC_INSTANCE = (MsCorEEDLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Initializes the common language runtime (CLR), locates the managed entry
	 * point in the executable assembly's CLR header, and begins execution.
	 * 
	 * @return This function is called by the loader in processes created from
	 *         managed executable assemblies. For DLL assemblies, the loader
	 *         calls the _CorDllMain function instead. The operating system
	 *         loader calls this method regardless of the entry point specified
	 *         in the image file. In Windows 98, Windows ME, Windows NT, and
	 *         Windows 2000, the _CorExeMain function is called indirectly
	 *         through a fixup in the operating system loader. In all other
	 *         versions of Windows, it is called directly by the operating
	 *         system loader.
	 */
	int _CorExeMain();
}
