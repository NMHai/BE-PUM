/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.comctl32.functions
 * File name: TaskDialog.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.comctl32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.comctl32.Comctl32API;
import v2.org.analysis.apihandle.winapi.comctl32.Comctl32DLL;
import v2.org.analysis.value.LongValue;
 
public class TaskDialog extends Comctl32API {
	public TaskDialog () {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		long t5 = this.params.get(5);
		long t6 = this.params.get(6);
		long t7 = this.params.get(7);
		
		// Step 2: type conversion from C++ to Java
		HWND hWndParent = null;
		if ( t0 != 0L ) {
			hWndParent = new HWND ();
			hWndParent.setPointer(new Pointer(t0));
		}
		HINSTANCE hInstance = null;
		if ( t1 != 0L ) {
			hInstance = new HINSTANCE ();
			hInstance.setPointer(new Pointer(t1));
		}
		CHARByReference pszWindowTitle = new CHARByReference (new CHAR(t2));
		CHARByReference pszMainInstruction = new CHARByReference (new CHAR(t3));
		CHARByReference pszContent = new CHARByReference (new CHAR(t4));
		int dwCommonButtons = (int) t5;
		CHARByReference pszIcon = new CHARByReference (new CHAR(t6));
		IntByReference pnButton = new IntByReference ((int) t7);

		// Step 3: call API function
		int ret = Comctl32DLL.INSTANCE.TaskDialog (hWndParent, hInstance, pszWindowTitle, pszMainInstruction, pszContent, dwCommonButtons, pszIcon, pnButton);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t7, new LongValue(pnButton.getValue()));

		

	}
}