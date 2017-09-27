/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: RemoveDirectory.java
 * Created date: Sep 17, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.ATOM;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Deletes an existing empty directory.
 * 
 * To perform this operation as a transacted operation, use the
 * RemoveDirectoryTransacted function.
 * 
 * @param lpPathName
 *            he path of the directory to be removed. This path must specify an
 *            empty directory, and the calling process must have delete access
 *            to the directory. In the ANSI version of this function, the name
 *            is limited to MAX_PATH characters. To extend this limit to 32,767
 *            wide characters, call the Unicode version of the function and
 *            prepend "\\?\" to the path. For more information, see Naming a
 *            File.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class RemoveDirectory extends Kernel32API {

	public RemoveDirectory() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String lpPathName = (t1 == 0L) ? null : memory.getText(this, t1);
		
		lpPathName = Storage.getMappingPath(lpPathName);
		
		BOOL ret = Kernel32DLL.INSTANCE.RemoveDirectory(lpPathName);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
