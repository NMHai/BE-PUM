/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * Creates a new directory. If the underlying file system supports security on
 * files and directories, the function applies a specified security descriptor
 * to the new directory.
 * 
 * @param lpPathName
 *            The path of the directory to be created.
 * 
 * @param lpSecurityAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure. The
 *            lpSecurityDescriptor member of the structure specifies a security
 *            descriptor for the new directory. If lpSecurityAttributes is NULL,
 *            the directory gets a default security descriptor. The ACLs in the
 *            default security descriptor for a directory are inherited from its
 *            parent directory.
 * 
 *            The target file system must support security on files and
 *            directories for this parameter to have an effect. (This is
 *            indicated when GetVolumeInformation returns FS_PERSISTENT_ACLS.)
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError. Possible errors include the
 *         following.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateDirectory extends Kernel32API {

	public CreateDirectory() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String lpPathName = memory.getText(this, t1);
		lpPathName = Storage.getMappingPath(lpPathName);
		System.out.println("PathName:" + lpPathName + ", pSecurity:" + t2);

		BOOL ret = Kernel32DLL.INSTANCE.CreateDirectory(lpPathName, null);
		register.mov("eax", new LongValue(ret.longValue()));
		System.out.println("Return value:" + ret.longValue());
	}
}
