/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: SetPixelFormat.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinGDI.PIXELFORMATDESCRIPTOR;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class SetPixelFormat extends Gdi32API {
	public SetPixelFormat () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		int iPixelFormat = (int) t1;
		PIXELFORMATDESCRIPTOR ppfd = null;
		if ( t2 != 0L) {
			ppfd = new PIXELFORMATDESCRIPTOR ();
			ppfd.nSize = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			ppfd.nVersion = (short) ((LongValue)memory.getWordMemoryValue (t2)).getValue();
			t2 += 2;
			ppfd.dwFlags = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			ppfd.iPixelType = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cColorBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cRedBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cRedShift = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cGreenBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cGreenShift = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cBlueBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cBlueShift = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAlphaBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAlphaShift = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAccumBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAccumRedBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAccumGreenBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAccumBlueBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAccumAlphaBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cDepthBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cStencilBits = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.cAuxBuffers = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.iLayerType = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.bReserved = (byte) ((LongValue)memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
			ppfd.dwLayerMask = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			ppfd.dwVisibleMask = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
			ppfd.dwDamageMask = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue();
			t2 += 4;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.SetPixelFormat (hdc, iPixelFormat, ppfd);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}