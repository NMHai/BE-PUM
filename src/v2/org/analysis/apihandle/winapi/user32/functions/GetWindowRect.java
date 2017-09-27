/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowRect.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the dimensions of the bounding rectangle of the specified window.
 * The dimensions are given in screen coordinates that are relative to the
 * upper-left corner of the screen.
 * 
 * @param hWnd
 *            A handle to the window.
 * 
 * @param lpRect
 *            A pointer to a RECT structure that receives the screen coordinates
 *            of the upper-left and lower-right corners of the window.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowRect extends User32API {

	public GetWindowRect() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = new HWND(new Pointer(t1));
		RECT lpRect = new RECT();
		
		BOOL ret = User32DLL.INSTANCE.GetWindowRect(hWnd, lpRect);
		
		register.mov("eax", new LongValue(ret.longValue()));
		
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpRect.left));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(lpRect.top));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(lpRect.right));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(lpRect.bottom));
	}

}
