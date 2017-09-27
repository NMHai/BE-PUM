/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: MoveFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * Moves an existing file or a directory, including its children.
 * 
 * @param lpExistingFileName
 *            The current name of the file or directory on the local computer.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path. For more
 *            information, see Naming a File.
 * 
 * @param lpNewFileName
 *            The new name for the file or directory. The new name must not
 *            already exist. A new file may be on a different file system or
 *            drive. A new directory must be on the same drive.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path. For more
 *            information, see Naming a File.
 * 
 * @return true, if successful If the function succeeds, the return value is
 *         nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class MoveFile extends Kernel32API {

	/**
	 * 
	 */
	public MoveFile() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String fileNameOld = memory.getText(this, t1);
		fileNameOld = Storage.getMappingPath(fileNameOld);
		String fileNameNew = memory.getText(this, t2);
		fileNameNew = Storage.getMappingPath(fileNameNew);
		System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew);

		boolean ret = Kernel32.INSTANCE.MoveFile(fileNameOld, fileNameNew);
		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
