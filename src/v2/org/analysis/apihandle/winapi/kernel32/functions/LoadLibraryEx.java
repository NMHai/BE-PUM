/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: LoadLibraryEx.java
 * Created date: Jul 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Loads the specified module into the address space of the calling process. The
 * specified module may cause other modules to be loaded.
 * 
 * @param lpFileName
 *            A string that specifies the file name of the module to load. This
 *            name is not related to the name stored in a library module itself,
 *            as specified by the LIBRARY keyword in the module-definition
 *            (.def) file.
 * 
 * @param hFile
 *            This parameter is reserved for future use. It must be NULL.
 * 
 * @param dwFlags
 *            The action to be taken when loading the module. If no flags are
 *            specified, the behavior of this function is identical to that of
 *            the LoadLibrary function. This parameter can be one of the
 *            following values.
 *            <ul>
 *            <li>DONT_RESOLVE_DLL_REFERENCES 0x00000001
 *            <li>LOAD_IGNORE_CODE_AUTHZ_LEVEL 0x00000010
 *            <li>LOAD_LIBRARY_AS_DATAFILE 0x00000002
 *            <li>LOAD_LIBRARY_AS_DATAFILE_EXCLUSIVE 0x00000040
 *            <li>LOAD_LIBRARY_AS_IMAGE_RESOURCE 0x00000020
 *            <li>LOAD_LIBRARY_SEARCH_APPLICATION_DIR 0x00000200
 *            <li>LOAD_LIBRARY_SEARCH_DEFAULT_DIRS 0x00001000
 *            <li>LOAD_LIBRARY_SEARCH_DLL_LOAD_DIR 0x00000100
 *            <li>LOAD_LIBRARY_SEARCH_SYSTEM32 0x00000800
 *            <li>LOAD_LIBRARY_SEARCH_USER_DIRS 0x00000400
 *            <li>LOAD_WITH_ALTERED_SEARCH_PATH 0x00000008
 *            </ul>
 * 
 * @return If the function succeeds, the return value is a handle to the loaded
 *         module. If the function fails, the return value is NULL. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 */
public class LoadLibraryEx extends Kernel32API {

	private HMODULE apiCallReturn = null;

	public LoadLibraryEx() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpFileName = memory.getText(this, t1);
		HANDLE hFile = (this.params.get(1) == 0L) ? null : new HANDLE(new Pointer(t2));
		DWORD dwFlags = new DWORD(t3);

		System.out.println(" Library Name:" + libraryName);

		// Avoid to run so long
		LoadLibThread thread = new LoadLibThread(lpFileName, hFile, dwFlags);
		try {
			thread.start();
			Thread.sleep(100);
			if (this.apiCallReturn == null) {
				Thread.sleep(1000);
			}
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long value = (apiCallReturn == null) ? 0 : Pointer.nativeValue(apiCallReturn.getPointer());
		register.mov("eax", new LongValue(value));

		System.out.println("Return Value: " + value);

		value = ((LongValue) register.getRegisterValue("eax")).getValue();
		APIHandle.libraryHandleMap.put(value, new PairEntry<String, Integer>(libraryName, -1));
	}

	class LoadLibThread extends Thread {
		private String lpFileName = null;
		private HANDLE hFile = null;
		private DWORD dwFlags = null;

		public LoadLibThread(String lpFileName, HANDLE hFile, DWORD dwFlags) {
			this.lpFileName = lpFileName;
			this.hFile = hFile;
			this.dwFlags = dwFlags;
		}

		@Override
		public void run() {
			apiCallReturn = Kernel32DLL.INSTANCE.LoadLibraryEx(lpFileName, hFile, dwFlags);
		}
	}
}
