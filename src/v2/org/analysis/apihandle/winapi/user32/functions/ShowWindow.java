/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: ShowWindow.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Sets the specified window's show state.
 * 
 * @param hWnd
 *            A handle to the window.
 * 
 * @param nCmdShow
 *            Controls how the window is to be shown. This parameter is ignored
 *            the first time an application calls ShowWindow, if the program
 *            that launched the application provides a STARTUPINFO structure.
 *            Otherwise, the first time ShowWindow is called, the value should
 *            be the value obtained by the WinMain function in its nCmdShow
 *            parameter.
 * 
 * @return If the window was previously visible, the return value is nonzero. If
 *         the window was previously hidden, the return value is zero..
 * @author Yen Nguyen
 *
 */
public class ShowWindow extends User32API {

	public ShowWindow() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = new HWND(new Pointer(t1));
		int nCmdShow = (int) t2;
		BOOL ret = User32DLL.INSTANCE.ShowWindow(hWnd, nCmdShow);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
