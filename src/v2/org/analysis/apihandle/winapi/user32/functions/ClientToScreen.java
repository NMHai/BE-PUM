/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ClientToScreen.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The ClientToScreen function converts the client-area coordinates of a
 * specified point to screen coordinates.
 * 
 * @param hWnd
 *            A handle to the window whose client area is used for the
 *            conversion.
 * 
 * @param lpPoint
 *            A pointer to a POINT structure that contains the client
 *            coordinates to be converted. The new screen coordinates are copied
 *            into this structure if the function succeeds.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class ClientToScreen extends User32API {

	public ClientToScreen() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = new HWND(new Pointer(t1));
		POINT lpPoint = new POINT();
		lpPoint.x = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2)))
				.getValue();
		lpPoint.y = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4)))
				.getValue();

		BOOL ret = User32DLL.INSTANCE.ClientToScreen(hWnd, lpPoint);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpPoint.x));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4), new LongValue(lpPoint.y));
	}

}
