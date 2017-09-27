package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Creates a new shape for the system caret and assigns ownership of the caret
 * to the specified window. The caret shape can be a line, a block, or a bitmap.
 * 
 * @param hWnd
 *            A handle to the window that owns the caret.
 * 
 * @param hBitmap
 *            A handle to the bitmap that defines the caret shape. If this
 *            parameter is NULL, the caret is solid. If this parameter is
 *            (HBITMAP) 1, the caret is gray. If this parameter is a bitmap
 *            handle, the caret is the specified bitmap. The bitmap handle must
 *            have been created by the CreateBitmap, CreateDIBitmap, or
 *            LoadBitmap function. If hBitmap is a bitmap handle, CreateCaret
 *            ignores the nWidth and nHeight parameters; the bitmap defines its
 *            own width and height.
 * 
 * @param nWidth
 *            The width of the caret, in logical units. If this parameter is
 *            zero, the width is set to the system-defined window border width.
 *            If hBitmap is a bitmap handle, CreateCaret ignores this parameter.
 * 
 * @param nHeight
 *            The height of the caret, in logical units. If this parameter is
 *            zero, the height is set to the system-defined window border
 *            height. If hBitmap is a bitmap handle, CreateCaret ignores this
 *            parameter.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateCaret extends User32API {

	public CreateCaret() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HWND hWnd = new HWND(new Pointer(t1));
		HBITMAP hBitmap = (t2 == 0L) ? null : new HBITMAP(new Pointer(t2));
		int nWidth = (int) t3;
		int nHeight = (int) t4;
		
		BOOL ret = User32DLL.INSTANCE.CreateCaret(hWnd, hBitmap, nWidth, nHeight);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
