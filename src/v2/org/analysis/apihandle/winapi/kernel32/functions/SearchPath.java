/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SearchPath.java
 * Created date: Mar 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Searches for a specified file in a specified path.
 * 
 * @param lpPath
 *            The path to be searched for the file. If this parameter is NULL,
 *            the function searches for a matching file using a
 *            registry-dependent system search path. For more information, see
 *            the Remarks section.
 * 
 * @param lpFileName
 *            The name of the file for which to search.
 * 
 * @param lpExtension
 *            The extension to be added to the file name when searching for the
 *            file. The first character of the file name extension must be a
 *            period (.). The extension is added only if the specified file name
 *            does not end with an extension. If a file name extension is not
 *            required or if the file name contains an extension, this parameter
 *            can be NULL.
 * 
 * @param nBufferLength
 *            The size of the buffer that receives the valid path and file name
 *            (including the terminating null character), in TCHARs.
 * 
 * @param lpBuffer
 *            A pointer to the buffer to receive the path and file name of the
 *            file found. The string is a null-terminated string.
 * 
 * @param lpFilePart
 *            A pointer to the variable to receive the address (within lpBuffer)
 *            of the last component of the valid path and file name, which is
 *            the address of the character immediately following the final
 *            backslash (\) in the path.
 * 
 * @return If the function succeeds, the value returned is the length, in
 *         TCHARs, of the string that is copied to the buffer, not including the
 *         terminating null character. If the return value is greater than
 *         nBufferLength, the value returned is the size of the buffer that is
 *         required to hold the path, including the terminating null character.
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SearchPath extends Kernel32API {

	public SearchPath() {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		String lpPath = (t1 != 0L) ? memory.getText(this, t1) : null;
		String lpFileName = (t2 != 0L) ? memory.getText(this, t2) : null;
		String lpExtension = (t3 != 0L) ? memory.getText(this, t3) : null;
		DWORD nBufferLength = new DWORD(t4);
		char[] lpBuffer = new char[(int) t4];
		Pointer lpFilePart = null;

		DWORD ret = null;
		if (t1 != 0L && lpPath.length() > 0) {
			String lpMPath = Storage.getMappingPath(lpPath);
			ret = Kernel32DLL.INSTANCE
					.SearchPath(lpMPath, lpFileName, lpExtension, nBufferLength, lpBuffer, lpFilePart);
		}
		if (ret == null || (ret != null && ret.longValue() == 0L)) {
			ret = Kernel32DLL.INSTANCE.SearchPath(lpPath, lpFileName, lpExtension, nBufferLength, lpBuffer, lpFilePart);
		}
		register.mov("eax", new LongValue(ret.longValue()));

		if (lpBuffer != null) {
			String path = Convert.reduceText(lpBuffer);
			memory.setText(this, t5, path);

			if (t6 != 0L && ret.intValue() > 0 && ret.intValue() <= nBufferLength.intValue()) {
				int index = path.lastIndexOf('\\');
				if (index > -1) {
					index += t5;
					memory.setDoubleWordMemoryValue(t6, new LongValue(t5));
				}
			}
		}
	}

}
