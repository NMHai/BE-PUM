/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
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
 * Retrieves a pointer to the specified resource in memory.
 * 
 * @param hResData
 *            A handle to the resource to be accessed. The LoadResource function
 *            returns this handle. Note that this parameter is listed as an
 *            HGLOBAL variable only for backward compatibility. Do not pass any
 *            value as a parameter other than a successful return value from the
 *            LoadResource function.
 * 
 * @return If the loaded resource is available, the return value is a pointer to
 *         the first byte of the resource; otherwise, it is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class LockResource extends Kernel32API {

	public LockResource() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		HANDLE hResData = new HANDLE(new Pointer(t1));
		LPVOID ret = Kernel32DLL.INSTANCE.LockResource(hResData);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.toPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
