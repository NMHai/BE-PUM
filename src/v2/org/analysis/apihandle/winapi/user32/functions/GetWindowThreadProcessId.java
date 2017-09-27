/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the identifier of the thread that created the specified window and,
 * optionally, the identifier of the process that created the window..
 * 
 * @param hWnd
 *            A handle to the window.
 * 
 * @param lpdwProcessId
 *            A pointer to a variable that receives the process identifier. If
 *            this parameter is not NULL, GetWindowThreadProcessId copies the
 *            identifier of the process to the variable; otherwise, it does not.
 * 
 * @return The return value is the identifier of the thread that created the
 *         window.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowThreadProcessId extends User32API {

	public GetWindowThreadProcessId() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = new HWND(new Pointer(t1));
		DWORDByReference lpdwProcessId = (t2 == 0L) ? null : new DWORDByReference(new DWORD(t2));
		DWORD ret = User32DLL.INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);

		register.mov("eax", new LongValue(ret.longValue()));

		if (t2 != 0L)
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpdwProcessId
					.getValue().longValue()));
	}
}
