/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: ChoosePixelFormat.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinGDI.PIXELFORMATDESCRIPTOR;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class ChoosePixelFormat extends Gdi32API {
	public ChoosePixelFormat () {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		
		// Step 2: type conversion from C++ to Java
		HDC hdc = null;
		if ( t0 != 0L ) {
			hdc = new HDC ();
			hdc.setPointer(new Pointer(t0));
		}
		PIXELFORMATDESCRIPTOR ppfd = null;
		if ( t1 != 0L) {
			ppfd = new PIXELFORMATDESCRIPTOR ();
			ppfd.nSize = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			ppfd.nVersion = (short) ((LongValue)memory.getWordMemoryValue (t1)).getValue();
			t1 += 2;
			ppfd.dwFlags = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			ppfd.iPixelType = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cColorBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cRedBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cRedShift = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cGreenBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cGreenShift = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cBlueBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cBlueShift = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAlphaBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAlphaShift = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAccumBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAccumRedBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAccumGreenBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAccumBlueBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAccumAlphaBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cDepthBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cStencilBits = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.cAuxBuffers = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.iLayerType = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.bReserved = (byte) ((LongValue)memory.getByteMemoryValue (t1)).getValue();
			t1 += 1;
			ppfd.dwLayerMask = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			ppfd.dwVisibleMask = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
			ppfd.dwDamageMask = (int) ((LongValue)memory.getDoubleWordMemoryValue (t1)).getValue();
			t1 += 4;
		}

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.ChoosePixelFormat (hdc, ppfd);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}