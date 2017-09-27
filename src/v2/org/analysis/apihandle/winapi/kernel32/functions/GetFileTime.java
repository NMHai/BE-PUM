/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the date and time that a file or directory was created, last
 * accessed, and last modified.
 * 
 * @param hFile
 *            A handle to the file or directory for which dates and times are to
 *            be retrieved. The handle must have been created using the
 *            CreateFile function with the GENERIC_READ access right.
 * 
 * @param lpCreationTime
 *            A pointer to a FILETIME structure to receive the date and time the
 *            file or directory was created. This parameter can be NULL if the
 *            application does not require this information.
 * 
 * @param lpLastAccessTime
 *            A pointer to a FILETIME structure to receive the date and time the
 *            file or directory was last accessed. The last access time includes
 *            the last time the file or directory was written to, read from, or,
 *            in the case of executable files, run. This parameter can be NULL
 *            if the application does not require this information.
 * 
 * @param lpLastWriteTime
 *            A pointer to a FILETIME structure to receive the date and time the
 *            file or directory was last written to, truncated, or overwritten
 *            (for example, with WriteFile or SetEndOfFile). This date and time
 *            is not updated when file attributes or security descriptors are
 *            changed. This parameter can be NULL if the application does not
 *            require this information.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileTime extends Kernel32API {

	public GetFileTime() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HANDLE hFile = new HANDLE(new Pointer(t1));
		FILETIME lpCreationTime = new FILETIME();
		FILETIME lpLastAccessTime = new FILETIME();
		FILETIME lpLastWriteTime = new FILETIME();
		boolean ret = Kernel32.INSTANCE.GetFileTime(hFile, lpCreationTime, lpLastAccessTime, lpLastWriteTime);

		register.mov("eax", new LongValue(ret ? 1 : 0));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
				lpCreationTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4), new LongValue(
				lpCreationTime.dwHighDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(
				lpLastAccessTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 + 4), new LongValue(
				lpLastAccessTime.dwHighDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(
				lpLastWriteTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4 + 4), new LongValue(
				lpLastWriteTime.dwHighDateTime));
	}

}
