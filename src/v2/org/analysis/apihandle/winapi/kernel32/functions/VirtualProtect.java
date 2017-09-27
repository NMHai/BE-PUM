/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualProtect.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LPVOID;

/**
 * Changes the protection on a region of committed pages in the virtual address
 * space of the calling process.
 * 
 * @param lpAddress
 *            A pointer an address that describes the starting page of the
 *            region of pages whose access protection attributes are to be
 *            changed.
 * 
 * @param dwSize
 *            The size of the region whose access protection attributes are to
 *            be changed, in bytes. The region of affected pages includes all
 *            pages containing one or more bytes in the range from the lpAddress
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
 *            protection value of the first page in the specified region of
 *            pages. If this parameter is NULL or does not point to a valid
 *            variable, the function fails.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualProtect extends Kernel32API {

	public VirtualProtect() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		if (Program.getProgram().isInside(new AbsoluteAddress(t1))) {
			System.out.println("*** BINARY CODE IS TRYING CHANGE PROTECTION OF INSIDE PROGRAM ***");

			register.mov("eax", new LongValue(1));

			memory.setDoubleWordMemoryValue(t4, new LongValue(2));
		} else {

			LPVOID lpAddress = new LPVOID(t1);
			SIZE_T dwSize = new SIZE_T(t2);
			DWORD flNewProtect = new DWORD(t3);
			DWORDByReference lpflOldProtect = new DWORDByReference();
			BOOL ret = Kernel32DLL.INSTANCE.VirtualProtect(lpAddress, dwSize, flNewProtect, lpflOldProtect);

			register.mov("eax", new LongValue(ret.longValue()));
			System.out.println("Return Value: " + ret.longValue());

			memory.setDoubleWordMemoryValue(t4, new LongValue(lpflOldProtect.getValue().longValue()));
		}
	}

}
