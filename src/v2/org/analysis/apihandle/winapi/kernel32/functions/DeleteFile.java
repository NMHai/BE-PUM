/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: DeleteFileA.java
 * Created date: Feb 2, 2015
 * QC: Passed
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * Deletes an existing file.
 * 
 * @param filename
 *            The name of the file to be deleted.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero (0). To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class DeleteFile extends Kernel32API {

	public DeleteFile() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		String fName = memory.getText(this, this.params.get(0));
		fName = Storage.getMappingPath(fName);

		System.out.println("Delete file: " + fName);
		boolean ret = Kernel32.INSTANCE.DeleteFile(fName);

		register.mov("eax", new LongValue((ret) ? 1 : 0));
	}
}
