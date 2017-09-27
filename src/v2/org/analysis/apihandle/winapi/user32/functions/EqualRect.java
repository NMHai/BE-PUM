/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: EqualRect.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines the visibility state of the specified window.
 * 
 * @param hWnd
 *            A handle to the window to be tested.
 * 
 * @return If the specified window, its parent window, its parent's parent
 *         window, and so forth, have the WS_VISIBLE style, the return value is
 *         nonzero. Otherwise, the return value is zero. Because the return
 *         value specifies whether the window has the WS_VISIBLE style, it may
 *         be nonzero even if the window is totally obscured by other windows.
 * 
 * @author Yen Nguyen
 *
 */
public class EqualRect extends User32API {

	public EqualRect() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		RECT lprc1 = new RECT();
		lprc1.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1)).getValue();
		lprc1.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lprc1.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lprc1.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();

		RECT lprc2 = new RECT();
		lprc2.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2)).getValue();
		lprc2.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();
		lprc2.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();
		lprc2.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();

		BOOL ret = User32DLL.INSTANCE.EqualRect(lprc1, lprc2);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
