/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualFree.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Locks the specified region of the process's virtual address space into physical memory, ensuring that subsequent access to the region will not incur a page fault.
 * BOOL WINAPI VirtualLock(
 *  _In_ LPVOID lpAddress,
 *  _In_ SIZE_T dwSize
 * );
 * 
 * @author HaiNM
 *
 */
public class VirtualLock extends Kernel32API {

	/**
	 * 
	 */
	public VirtualLock() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		// String fileName = symbolValueMemoryOperand.getText(this, new
		// X86MemoryOperand(DataType.INT32, t1));
		System.out.println("Base Address:" + t1 + ", Size:" + t2);

		BOOL ret = Kernel32DLL.INSTANCE.VirtualLock(new LPVOID(t1), new SIZE_T(t2));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
