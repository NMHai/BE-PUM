/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: MoveFileEx.java
 * Created date: Sep 18, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Moves an existing file or directory, including its children, with various
 * move options. The MoveFileWithProgress function is equivalent to the
 * MoveFileEx function, except that MoveFileWithProgress allows you to provide a
 * callback function that receives progress notifications. To perform this
 * operation as a transacted operation, use the MoveFileTransacted function.
 * 
 * @param lpExistingFileName
 *            The current name of the file or directory on the local computer.
 * 
 * @param lpNewFileName
 *            The new name of the file or directory on the local computer.
 * 
 * @param dwFlags
 *            This parameter can be one or more of the following values.
 * 
 *            MOVEFILE_COPY_ALLOWED 2 (0x2)
 * 
 *            MOVEFILE_CREATE_HARDLINK 16 (0x10)
 * 
 *            MOVEFILE_DELAY_UNTIL_REBOOT 4 (0x4)
 * 
 *            MOVEFILE_FAIL_IF_NOT_TRACKABLE 32 (0x20)
 * 
 *            MOVEFILE_REPLACE_EXISTING 1 (0x1)
 * 
 *            MOVEFILE_WRITE_THROUGH 8 (0x8)
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero (0). To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class MoveFileEx extends Kernel32API {

	public MoveFileEx() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpExistingFileName = (t1 == 0L) ? null : memory.getText(this, t1);
		lpExistingFileName = Storage.getMappingPath(lpExistingFileName);
		String lpNewFileName = (t1 == 0L) ? null : memory.getText(this, t2);
		lpNewFileName = Storage.getMappingPath(lpNewFileName);
		DWORD dwFlags = new DWORD(t3);

		System.out.println("Old File:" + lpExistingFileName + ", New File:" + lpNewFileName);

		BOOL ret = Kernel32DLL.INSTANCE.MoveFileEx(lpExistingFileName, lpNewFileName, dwFlags);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
