/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msimg32.functions
 * File name: AlphaBlend.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.msimg32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinUser.BLENDFUNCTION;

import v2.org.analysis.apihandle.winapi.msimg32.Msimg32API;
import v2.org.analysis.apihandle.winapi.msimg32.Msimg32DLL;
import v2.org.analysis.value.LongValue;
 
public class AlphaBlend extends Msimg32API {
	public AlphaBlend () {
		super();
		NUM_OF_PARMS = 11;
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
		long t8 = this.params.get(8);
		long t9 = this.params.get(9);
		long t10 = this.params.get(10);
		
		// Step 2: type conversion from C++ to Java
		HDC hdcDest = null;
		if ( t0 != 0L ) {
			hdcDest = new HDC ();
			hdcDest.setPointer(new Pointer(t0));
		}
		int xoriginDest = (int) t1;
		int yoriginDest = (int) t2;
		int wDest = (int) t3;
		int hDest = (int) t4;
		HDC hdcSrc = null;
		if ( t5 != 0L ) {
			hdcSrc = new HDC ();
			hdcSrc.setPointer(new Pointer(t5));
		}
		int xoriginSrc = (int) t6;
		int yoriginSrc = (int) t7;
		int wSrc = (int) t8;
		int hSrc = (int) t9;
		BLENDFUNCTION ftn = null;
		if ( t10 != 0L) {
			ftn = new BLENDFUNCTION ();
			ftn.BlendOp = (byte) ((LongValue)memory.getByteMemoryValue (t10)).getValue();
			t10 += 1;
			ftn.BlendFlags = (byte) ((LongValue)memory.getByteMemoryValue (t10)).getValue();
			t10 += 1;
			ftn.SourceConstantAlpha = (byte) ((LongValue)memory.getByteMemoryValue (t10)).getValue();
			t10 += 1;
			ftn.AlphaFormat = (byte) ((LongValue)memory.getByteMemoryValue (t10)).getValue();
			t10 += 1;
		}

		// Step 3: call API function
		int ret = Msimg32DLL.INSTANCE.AlphaBlend (hdcDest, xoriginDest, yoriginDest, wDest, hDest, hdcSrc, xoriginSrc, yoriginSrc, wSrc, hSrc, ftn);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}