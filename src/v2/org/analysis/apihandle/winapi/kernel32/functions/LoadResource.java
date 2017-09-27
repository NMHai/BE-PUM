/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import org.jakstab.Program;
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
 * Retrieves a handle that can be used to obtain a pointer to the first byte of
 * the specified resource in memory.
 * 
 * @param hModule
 *            A handle to the module whose executable file contains the
 *            resource. If hModule is NULL, the system loads the resource from
 *            the module that was used to create the current process.
 * 
 * @param hResInfo
 *            A handle to the resource to be loaded. This handle is returned by
 *            the FindResource or FindResourceEx function.
 * 
 * @return If the function succeeds, the return value is a handle to the data
 *         associated with the resource. If the function fails, the return value
 *         is NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadResource extends Kernel32API {

	public LoadResource() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HMODULE hModule = new HMODULE();
		if (t1 != 0L) {
			hModule.setPointer(new Pointer(t1));
		} else {
			String path = Program.getProgram().getAbsolutePathFile().replace('/', '\\');
			long handle = new LoadLibrary().execute(path);
			hModule.setPointer(new Pointer(handle));
		}
		HRSRC hResInfo = new HRSRC(new Pointer(t2));
		HANDLE ret = Kernel32DLL.INSTANCE.LoadResource(hModule, hResInfo);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
