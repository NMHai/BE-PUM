/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualAllocEx.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Reserves or commits a region of memory within the virtual address space of a
 * specified process. The function initializes the memory it allocates to zero,
 * unless MEM_RESET is used.
 * 
 * @param hProcess
 *            The handle to a process. The function allocates memory within the
 *            virtual address space of this process.
 * 
 * @param lpAddress
 *            : The starting address of the region to allocate. If the memory is
 *            being reserved, the specified address is rounded down to the
 *            nearest multiple of the allocation granularity. If the memory is
 *            already reserved and is being committed, the address is rounded
 *            down to the next page boundary. To determine the size of a page
 *            and the allocation granularity on the host computer, use the
 *            GetSystemInfo function. If this parameter is NULL, the system
 *            determines where to allocate the region.
 * 
 * @param dwSize
 *            : The size of the region, in bytes. If the lpAddress parameter is
 *            NULL, this value is rounded up to the next page boundary.
 *            Otherwise, the allocated pages include all pages containing one or
 *            more bytes in the range from lpAddress to lpAddress+dwSize. This
 *            means that a 2-byte range straddling a page boundary causes both
 *            pages to be included in the allocated region.
 * 
 * @param flAllocationType
 *            : The type of memory allocation. This parameter must contain one
 *            of the following values.
 * 
 * @param flProtect
 *            : The memory protection for the region of pages to be allocated.
 *            If the pages are being committed, you can specify any one of the
 *            memory protection constants.
 * 
 * @return If the function succeeds, the return value is the base address of the
 *         allocated region of pages. If the function fails, the return value is
 *         NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualAllocEx extends Kernel32API {

	public VirtualAllocEx() {
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
		LPVOID lpAddress = (t2 != 0L) ? new LPVOID(t2) : null;
		SIZE_T dwSize = new SIZE_T(t3);
		DWORD flAllocationType = new DWORD(t4);
		DWORD flProtect = new DWORD(t5);
		LPVOID ret = Kernel32DLL.INSTANCE.VirtualAllocEx(hProcess, lpAddress, dwSize, flAllocationType, flProtect);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.toPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
