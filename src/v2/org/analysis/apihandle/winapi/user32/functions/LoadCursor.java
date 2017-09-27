/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.HCURSOR;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

/**
 * Loads the specified cursor resource from the executable (.EXE) file
 * associated with an application instance.
 * 
 * @param hInstance
 *            A handle to an instance of the module whose executable file
 *            contains the cursor to be loaded.
 * 
 * @param lpCursorName
 *            The name of the cursor resource to be loaded. Alternatively, this
 *            parameter can consist of the resource identifier in the low-order
 *            word and zero in the high-order word. The MAKEINTRESOURCE macro
 *            can also be used to create this value. To use one of the
 *            predefined cursors, the application must set the hInstance
 *            parameter to NULL and the lpCursorName parameter to one the
 *            following values.
 * 
 * @return If the function succeeds, the return value is the handle to the newly
 *         loaded cursor. If the function fails, the return value is NULL. To
 *         get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadCursor extends User32API {
	public LoadCursor() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HINSTANCE hInstance = null;
		if (t1 != 0L) {
			hInstance = new HINSTANCE();
			hInstance.setPointer(new Pointer(t1));
		}
		WString lpCursorName = new WString(memory.getText(this, t2));
		HCURSOR ret = User32DLL.INSTANCE.LoadCursor(hInstance, lpCursorName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
