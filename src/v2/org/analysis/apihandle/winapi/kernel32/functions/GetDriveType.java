/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetDriveType.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * The GetDriveType function determines whether a disk drive is a removable,
 * fixed, CD-ROM, RAM disk, or network drive.
 * 
 * @param lpRootPathName
 *            Pointer to a null-terminated string that specifies the root
 *            directory of the disk to return information about. A trailing
 *            backslash is required. If this parameter is NULL, the function
 *            uses the root of the current directory.
 * 
 * @return The return value specifies the type of drive.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDriveType extends Kernel32API {

	public GetDriveType() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String lpRootPathName = memory.getText(this, t1);
		int ret = Kernel32.INSTANCE.GetDriveType(lpRootPathName);
		register.mov("eax", new LongValue(ret));
	}

}
