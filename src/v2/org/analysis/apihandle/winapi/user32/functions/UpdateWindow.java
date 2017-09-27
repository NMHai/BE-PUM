/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: UpdateWindow.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The UpdateWindow function updates the client area of the specified window by
 * sending a WM_PAINT message to the window if the window's update region is not
 * empty. The function sends a WM_PAINT message directly to the window procedure
 * of the specified window, bypassing the application queue. If the update
 * region is empty, no message is sent.
 * 
 * @param hWnd
 *            Handle to the window to be updated.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class UpdateWindow extends User32API {

	public UpdateWindow() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HWND hWnd = new HWND(new Pointer(t1));
		boolean ret = User32.INSTANCE.UpdateWindow(hWnd);

		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
