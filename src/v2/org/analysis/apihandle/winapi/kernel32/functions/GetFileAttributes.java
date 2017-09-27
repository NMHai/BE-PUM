/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileAttributes.java
 * Created date: Jan 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * Retrieves file system attributes for a specified file or directory.
 * 
 * @param lpFileName
 *            The name of the file or directory. Prepend \\?\ to the path for
 *            names up to 32,767 wide characters
 * 
 * @return INVALID_FILE_ATTRIBUTES if the function fails, otherwise the file
 *         attributes WinNT.FILE_ATTRIBUTE_*
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileAttributes extends Kernel32API {

	public GetFileAttributes() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		String fName = memory.getText(this, this.params.get(0));
		fName = Storage.getMappingPath(fName);

		long attr = Kernel32.INSTANCE.GetFileAttributes(fName);
		register.mov("eax", new LongValue(attr));

		System.out.println("Return Value:" + attr);
	}

}
