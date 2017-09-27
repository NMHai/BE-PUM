/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Reads data from an area of memory in a specified process. The entire area to
 * be read must be accessible or the operation fails.
 * 
 * @param hProcess
 *            A handle to the process with memory that is being read. The handle
 *            must have PROCESS_VM_READ access to the process.
 * 
 * @param lpBaseAddress
 *            A pointer to the base address in the specified process from which
 *            to read. Before any data transfer occurs, the system verifies that
 *            all data in the base address and memory of the specified size is
 *            accessible for read access, and if it is not accessible the
 *            function fails.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the contents from the address
 *            space of the specified process.
 * 
 * @param nSize
 *            The number of bytes to be read from the specified process.
 * 
 * @param lpNumberOfBytesRead
 *            A pointer to a variable that receives the number of bytes
 *            transferred into the specified buffer. If lpNumberOfBytesRead is
 *            NULL, the parameter is ignored.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is 0 (zero). To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class ReadProcessMemory extends Kernel32API {

	public ReadProcessMemory() {
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
		ULONG_PTRByReference lpNumberOfBytesRead = new ULONG_PTRByReference(new ULONG_PTR(t5));
		BOOL ret = Kernel32DLL.INSTANCE
				.ReadProcessMemory(hProcess, lpBaseAddress, lpBuffer, nSize, lpNumberOfBytesRead);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.UINT32, t3), new LongValue(lpBuffer.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.UINT32, t5), new LongValue(lpNumberOfBytesRead
				.getValue().longValue()));
	}

}
