package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * The DeleteObject function deletes a logical pen, brush, font, bitmap, region,
 * or palette, freeing all system resources associated with the object. After
 * the object is deleted, the specified handle is no longer valid.
 * 
 * @param hObject
 *            A handle to a logical pen, brush, font, bitmap, region, or
 *            palette.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         specified handle is not valid or is currently selected into a DC, the
 *         return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class DeleteObject extends Gdi32API {

	public DeleteObject() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HANDLE hObject = new HANDLE(new Pointer(t1));
		BOOL ret = Gdi32DLL.INSTANCE.DeleteObject(hObject);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
