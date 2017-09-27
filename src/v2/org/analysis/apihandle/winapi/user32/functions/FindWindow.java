/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: FindWindow.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

/**
 * This function retrieves the handle to the top-level window whose class name
 * and window name match the specified strings. This function does not search
 * child windows.
 * 
 * @param lpClassName
 *            Long pointer to a null-terminated string that specifies the class
 *            name or is an atom that identifies the class-name string. If this
 *            parameter is an atom, it must be a global atom created by a
 *            previous call to the GlobalAddAtom function. The atom, a 16-bit
 *            value, must be placed in the low-order word of lpClassName; the
 *            high-order word must be zero.
 * 
 * @param lpWindowName
 *            Long pointer to a null-terminated string that specifies the window
 *            name (the window's title). If this parameter is NULL, all window
 *            names match.
 * 
 * @return A handle to the window that has the specified class name and window
 *         name indicates success. NULL indicates failure. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class FindWindow extends User32API {

	/**
	 * 
	 */
	public FindWindow() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		/*
		 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
		 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
		 */
		String className = (this.params.get(0) == 0L) ? null : memory.getText(this, this.params.get(0));
		String windowName = (this.params.get(1) == 0L) ? null : memory.getText(this, this.params.get(1));

		System.out.println("Class Name:" + className + ", Window Name Address:" + this.params.get(1) + ", Window Name:"
				+ windowName);

		HWND ret = User32.INSTANCE.FindWindow(className, windowName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
	}
}
