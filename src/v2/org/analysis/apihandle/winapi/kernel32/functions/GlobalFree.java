/**
 * 
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
 * Frees the specified global memory object and invalidates its handle.
 * 
 * @param hMem
 *            A handle to the global memory object. This handle is returned by
 *            either the GlobalAlloc or GlobalReAlloc function. It is not safe
 *            to free memory allocated with LocalAlloc.
 * 
 * @return If the function succeeds, the return value is NULL.
 * 
 * @author Yen Nguyen
 * 
 */
public class GlobalFree extends Kernel32API {

	public GlobalFree() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HANDLE hMem = new HANDLE(new Pointer(t1));
		HANDLE ret = Kernel32DLL.INSTANCE.GlobalFree(hMem);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
