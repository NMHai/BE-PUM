/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Changes the size or the attributes of a specified local memory object. The
 * size can increase or decrease.
 * 
 * @param hMem
 *            A handle to the local memory object to be reallocated. This handle
 *            is returned by either the LocalAlloc or LocalReAlloc function.
 * 
 * @param uBytes
 *            The new size of the memory block, in bytes. If uFlags specifies
 *            LMEM_MODIFY, this parameter is ignored.
 * 
 * @param uFlags
 *            The reallocation options. If LMEM_MODIFY is specified, the
 *            function modifies the attributes of the memory object only (the
 *            uBytes parameter is ignored.) Otherwise, the function reallocates
 *            the memory object.
 * 
 * @return If the function succeeds, the return value is a handle to the
 *         reallocated memory object. If the function fails, the return value is
 *         NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LocalReAlloc extends Kernel32API {
	public LocalReAlloc() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HANDLE hMem = new HANDLE(new Pointer(t1));
		SIZE_T uBytes = new SIZE_T(t2);
		UINT uFlags = new UINT(t3);
		HANDLE ret = Kernel32DLL.INSTANCE.LocalReAlloc(hMem, uBytes, uFlags);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
