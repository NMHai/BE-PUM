/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: HeapFree.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;
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
 * Frees a memory block allocated from a heap by the HeapAlloc or HeapReAlloc
 * function.
 * 
 * @param hHeap
 *            : A handle to the heap whose memory block is to be freed. This
 *            handle is returned by either the HeapCreate or GetProcessHeap
 *            function.
 * 
 * @param dwFlags
 *            : The heap free options. Specifying the following value overrides
 *            the corresponding value specified in the flOptions parameter when
 *            the heap was created by using the HeapCreate function.
 * 
 * @param lpMem
 *            : A pointer to the memory block to be freed. This pointer is
 *            returned by the HeapAlloc or HeapReAlloc function. If this pointer
 *            is NULL, the behavior is undefined.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class HeapFree extends Kernel32API {

	/**
	 * 
	 */
	public HeapFree() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		boolean ret = Kernel32DLL.INSTANCE.HeapFree(new HANDLE(new Pointer(t1)), new DWORD(t2), new LPVOID(t3));

		register.mov("eax", new LongValue((ret) ? 1 : 0));
	}

}
