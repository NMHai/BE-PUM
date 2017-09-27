/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetDiskFreeSpaceEx.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.LongByReference;

/**
 * The GetDiskFreeSpaceEx function retrieves information about the amount of
 * space that is available on a disk volume, which is the total amount of space,
 * the total amount of free space, and the total amount of free space available
 * to the user that is associated with the calling thread.
 * 
 * @param lpDirectoryName
 *            A pointer to a null-terminated string that specifies a directory
 *            on a disk. If this parameter is NULL, the function uses the root
 *            of the current disk. If this parameter is a UNC name, it must
 *            include a trailing backslash, for example, \\MyServer\MyShare\.
 *            This parameter does not have to specify the root directory on a
 *            disk. The function accepts any directory on a disk.
 * 
 * @param lpFreeBytesAvailable
 *            A pointer to a variable that receives the total number of free
 *            bytes on a disk that are available to the user who is associated
 *            with the calling thread. This parameter can be NULL.
 * 
 * @param lpTotalNumberOfBytes
 *            A pointer to a variable that receives the total number of bytes on
 *            a disk that are available to the user who is associated with the
 *            calling thread. This parameter can be NULL.
 * 
 * @param lpTotalNumberOfFreeBytes
 *            A pointer to a variable that receives the total number of free
 *            bytes on a disk. This parameter can be NULL.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is 0 (zero). To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDiskFreeSpaceEx extends Kernel32API {

	public GetDiskFreeSpaceEx() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		String lpDirectoryName = (t1 == 0L) ? null : memory.getText(this, t1);
		LongByReference lpFreeBytesAvailable = (t2 == 0L) ? null : new LongByReference(
				((LongValue) memory.getDoubleWordMemoryValue(t2)).getValue());
		LongByReference lpTotalNumberOfBytes = (t3 == 0L) ? null : new LongByReference(
				((LongValue) memory.getDoubleWordMemoryValue(t3)).getValue());
		LongByReference lpTotalNumberOfFreeBytes = (t4 == 0L) ? null : new LongByReference(
				((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue());

		boolean ret = Kernel32.INSTANCE.GetDiskFreeSpaceEx(lpDirectoryName, lpFreeBytesAvailable, lpTotalNumberOfBytes,
				lpTotalNumberOfFreeBytes);

		register.mov("eax", new LongValue((ret) ? 1 : 0));

		if (t2 != 0L) {
			memory.setDoubleWordMemoryValue(t2, new LongValue(lpFreeBytesAvailable.getValue()));
		}
		if (t3 != 0L) {
			memory.setDoubleWordMemoryValue(t3, new LongValue(lpTotalNumberOfBytes.getValue()));
		}
		if (t4 != 0L) {
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpTotalNumberOfFreeBytes.getValue()));
		}
	}
}
