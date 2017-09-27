/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: RemoveProp.java
 * Created date: Oct 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Removes an entry from the property list of the specified window. The
 * specified character string identifies the entry to be removed.
 * 
 * @param hWnd
 *            A handle to the window whose property list is to be changed.
 * 
 * @param lpString
 *            A null-terminated character string or an atom that identifies a
 *            string. If this parameter is an atom, it must have been created
 *            using the GlobalAddAtom function. The atom, a 16-bit value, must
 *            be placed in the low-order word of lpString; the high-order word
 *            must be zero.
 * 
 * @return The return value identifies the specified data. If the data cannot be
 *         found in the specified property list, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class RemoveProp extends User32API {

	public RemoveProp() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hWnd = new HWND(new Pointer(t1));
		String lpString = memory.getText(this, t2);
		
		HANDLE ret = User32DLL.INSTANCE.RemoveProp(hWnd, lpString);
		
		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
