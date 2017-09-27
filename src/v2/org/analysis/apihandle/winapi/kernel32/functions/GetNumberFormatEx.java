/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetNumberFormatEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.NUMBERFMT;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetNumberFormatEx extends Kernel32API {
	public GetNumberFormatEx () {
		super();
		NUM_OF_PARMS = 6;
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
		
		// Step 2: type conversion from C++ to Java
		CHARByReference lpLocaleName = new CHARByReference (new CHAR(t0));
		DWORD dwFlags = new DWORD (t1);
		CHARByReference lpValue = new CHARByReference (new CHAR(t2));
		NUMBERFMT lpFormat = null;
		if ( t3 != 0L) {
			lpFormat = new NUMBERFMT ();
			lpFormat.NumDigits = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpFormat.LeadingZero = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpFormat.Grouping = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpFormat.lpDecimalSep = memory.getText(this, t3);
			t3 += 4;
			lpFormat.lpThousandSep = memory.getText(this, t3);
			t3 += 4;
			lpFormat.NegativeOrder = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
		}
		char[] lpNumberStr = null;
		if ( t4 != 0L ) lpNumberStr = new char[(int) t5];
		for (int i = 0; i < lpNumberStr.length; i++) {
			lpNumberStr [i] = (char) ((LongValue) memory.getWordMemoryValue (t4)).getValue();
			t4 += 2;
		}
		int cchNumber = (int) t5;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetNumberFormatEx (lpLocaleName, dwFlags, lpValue, lpFormat, lpNumberStr, cchNumber);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t4 = this.params.get(4);
		for (int i = 0; i < lpNumberStr.length; i++) {
			memory.setWordMemoryValue (t4, new LongValue(lpNumberStr [i]));
			t4 += 2;
		}
	}
}