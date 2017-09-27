/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: HeapDestroy.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
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
 * Destroys the specified heap object. It decommits and releases all the pages
 * of a private heap object, and it invalidates the handle to the heap.
 * 
 * @param hHeap
 *            :
 *            <p>
 *            A handle to the heap to be destroyed. This handle is returned by
 *            the <a href=
 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366599(v=vs.85).aspx"
 *            ><strong
 *            xmlns="http://www.w3.org/1999/xhtml">HeapCreate</strong></a>
 *            function. Do not use the handle to the process heap returned by
 *            the <a href=
 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366569(v=vs.85).aspx"
 *            ><strong
 *            xmlns="http://www.w3.org/1999/xhtml">GetProcessHeap</strong ></a>
 *            function.
 *            </p>
 * 
 * @return <p>
 *         If the function succeeds, the return value is nonzero.
 *         </p>
 *         <p>
 *         If the function fails, the return value is zero. To get extended
 *         error information, call <a href=
 *         "https://msdn.microsoft.com/en-us/library/windows/desktop/ms679360(v=vs.85).aspx"
 *         ><strong
 *         xmlns="http://www.w3.org/1999/xhtml">GetLastError</strong></a>.
 *         </p>
 * 
 * @author Yen Nguyen
 *
 */
public class HeapDestroy extends Kernel32API {

	/**
	 * 
	 */
	public HeapDestroy() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		boolean ret = Kernel32DLL.INSTANCE.HeapDestroy(new HANDLE(new Pointer(t1)));

		register.mov("eax", new LongValue((ret) ? 1 : 0));
	}

}
