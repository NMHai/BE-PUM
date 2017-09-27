/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: VirtualProtectEx.java
 * Created date: Jul 26, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Changes the protection on a region of committed pages in the virtual address
 * space of a specified process.
 * 
 * @param hProcess
 *            A handle to the process whose memory protection is to be changed.
 *            The handle must have the PROCESS_VM_OPERATION access right. For
 *            more information, see Process Security and Access Rights.
 * 
 * @param lpAddress
 *            A pointer to the base address of the region of pages whose access
 *            protection attributes are to be changed.
 * 
 * @param dwSize
 *            The size of the region whose access protection attributes are
 *            changed, in bytes. The region of affected pages includes all pages
 *            containing one or more bytes in the range from the lpAddress
 *            parameter to (lpAddress+dwSize). This means that a 2-byte range
 *            straddling a page boundary causes the protection attributes of
 *            both pages to be changed.
 * 
 * @param flNewProtect
 *            The memory protection option. This parameter can be one of the
 *            memory protection constants.
 * 
 * @param lpflOldProtect
 *            A pointer to a variable that receives the previous access
 *            protection of the first page in the specified region of pages. If
 *            this parameter is NULL or does not point to a valid variable, the
 *            function fails.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualProtectEx extends Kernel32API {

	public VirtualProtectEx() {
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
		LPVOID lpAddress = new LPVOID(t2);
		SIZE_T dwSize = new SIZE_T(t3);
		DWORD flNewProtect = new DWORD(t4);
		DWORDByReference lpflOldProtect = new DWORDByReference();

		BOOL ret = Kernel32DLL.INSTANCE.VirtualProtectEx(hProcess, lpAddress, dwSize, flNewProtect, lpflOldProtect);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));

		System.out.println("Return Value: " + value);

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), 
				new LongValue(lpflOldProtect.getValue().longValue()));
	}

}
