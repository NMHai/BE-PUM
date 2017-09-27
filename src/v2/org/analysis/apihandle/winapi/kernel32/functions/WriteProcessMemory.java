/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WriteProcessMemory.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Writes data to an area of memory in a specified process. The entire area to
 * be written to must be accessible or the operation fails.
 * 
 * @param hProcess
 *            A handle to the process memory to be modified. The handle must
 *            have PROCESS_VM_WRITE and PROCESS_VM_OPERATION access to the
 *            process.
 * 
 * @param lpBaseAddress
 *            A pointer to the base address in the specified process to which
 *            data is written. Before data transfer occurs, the system verifies
 *            that all data in the base address and memory of the specified size
 *            is accessible for write access, and if it is not accessible, the
 *            function fails.
 * 
 * @param lpBuffer
 *            A pointer to the buffer that contains data to be written in the
 *            address space of the specified process.
 * 
 * @param nSize
 *            The number of bytes to be written to the specified process.
 * 
 * @param lpNumberOfBytesWritten
 *            A pointer to a variable that receives the number of bytes
 *            transferred into the specified process. This parameter is
 *            optional. If lpNumberOfBytesWritten is NULL, the parameter is
 *            ignored.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is 0 (zero). To get extended error
 *         information, call GetLastError. The function fails if the requested
 *         write operation crosses into an area of the process that is
 *         inaccessible.
 * 
 * @author Yen Nguyen
 *
 */
public class WriteProcessMemory extends Kernel32API {

	public WriteProcessMemory() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HANDLE hProcess = new HANDLE(new Pointer(t1));
		LPVOID lpBaseAddress = new LPVOID(t2);
		LPVOID lpBuffer = new LPVOID(t3);
		SIZE_T nSize = new SIZE_T(t4);
		ULONG_PTRByReference lpNumberOfBytesWritten = new ULONG_PTRByReference();
		BOOL ret = Kernel32DLL.INSTANCE.WriteProcessMemory(hProcess, lpBaseAddress, lpBuffer, nSize,
				lpNumberOfBytesWritten);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(lpNumberOfBytesWritten
				.getValue().longValue()));
	}

}
