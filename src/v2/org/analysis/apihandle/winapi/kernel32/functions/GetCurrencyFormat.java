/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetCurrencyFormat.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.structures.CURRENCYFMT;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetCurrencyFormat extends Kernel32API {
	public GetCurrencyFormat () {
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
		LCID Locale = new LCID (t0);
		DWORD dwFlags = new DWORD (t1);
		String lpValue = null;
		if ( t2 != 0L ) lpValue = memory.getText(this, t2);
		CURRENCYFMT lpFormat = null;
		if ( t3 != 0L) {
			lpFormat = new CURRENCYFMT ();
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
			lpFormat.PositiveOrder = new UINT (((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue());
			t3 += 4;
			lpFormat.lpCurrencySymbol = memory.getText(this, t3);
			t3 += 4;
		}
		char[] lpCurrencyStr = null;
		if ( t4 != 0L ) lpCurrencyStr = new char[(int) t5];
		for (int i = 0; i < lpCurrencyStr.length; i++) {
			lpCurrencyStr [i] = (char) ((LongValue) memory.getByteMemoryValue (t4)).getValue();
			t4 += 1;
		}
		int cchCurrency = (int) t5;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetCurrencyFormat (Locale, dwFlags, lpValue, lpFormat, lpCurrencyStr, cchCurrency);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t4 = this.params.get(4);
		for (int i = 0; i < lpCurrencyStr.length; i++) {
			memory.setByteMemoryValue (t4, new LongValue(lpCurrencyStr [i]));
			t4 += 1;
		}
	}
}