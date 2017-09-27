/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: DlgDirListComboBox.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class DlgDirListComboBox extends User32API {
	public DlgDirListComboBox () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		HWND hDlg = null;
		if ( t0 != 0L ) {
			hDlg = new HWND ();
			hDlg.setPointer(new Pointer(t0));
		}
		char[] lpPathSpec = null;
		if ( t1 != 0L ) lpPathSpec = new char[(int) t2];
		for (int i = 0; i < lpPathSpec.length; i++) {
			lpPathSpec [i] = (char) ((LongValue) memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
		}
		int nIDComboBox = (int) t2;
		int nIDStaticPath = (int) t3;
		UINT uFiletype = new UINT (t4);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.DlgDirListComboBox (hDlg, lpPathSpec, nIDComboBox, nIDStaticPath, uFiletype);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}