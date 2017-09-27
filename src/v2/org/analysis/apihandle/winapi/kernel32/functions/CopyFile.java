/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CopyFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * Copies an existing file to a new file.
 * 
 * @param lpExistingFileName
 *            The name of an existing file.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path. For more
 *            information, see Naming a File.
 * 
 *            If lpExistingFileName does not exist, CopyFile fails, and
 *            GetLastError returns ERROR_FILE_NOT_FOUND.
 * 
 * @param lpNewFileName
 *            The name of the new file.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path. For more
 *            information, see Naming a File.
 * 
 * @param bFailIfExists
 *            If this parameter is TRUE and the new file specified by
 *            lpNewFileName already exists, the function fails. If this
 *            parameter is FALSE and the new file already exists, the function
 *            overwrites the existing file and succeeds.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CopyFile extends Kernel32API {

	public CopyFile() {
		super();
		NUM_OF_PARMS = 3;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String fileNameOld = memory.getText(this, t1);
		fileNameOld = Storage.getMappingPath(fileNameOld);
		String fileNameNew = memory.getText(this, t2);
		fileNameNew = Storage.getMappingPath(fileNameNew);
		System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew + ", Flag:" + t3);

		boolean ret = Kernel32.INSTANCE.CopyFile(fileNameOld, fileNameNew, (t3 != 0L));
		register.mov("eax", new LongValue(ret ? 1 : 0));
	}
}
