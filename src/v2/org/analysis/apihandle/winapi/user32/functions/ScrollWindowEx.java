/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ScrollWindowEx.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HRGN;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;
 
public class ScrollWindowEx extends User32API {
	public ScrollWindowEx () {
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
		HWND hWnd = null;
		if ( t0 != 0L ) {
			hWnd = new HWND ();
			hWnd.setPointer(new Pointer(t0));
		}
		int dx = (int) t1;
		int dy = (int) t2;
		RECT prcScroll = null;
		if ( t3 != 0L) {
			prcScroll = new RECT ();
			prcScroll.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			prcScroll.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			prcScroll.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
			prcScroll.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t3)).getValue();
			t3 += 4;
		}
		RECT prcClip = null;
		if ( t4 != 0L) {
			prcClip = new RECT ();
			prcClip.left = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			prcClip.top = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			prcClip.right = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
			prcClip.bottom = (int) ((LongValue)memory.getDoubleWordMemoryValue (t4)).getValue();
			t4 += 4;
		}
		HRGN hrgnUpdate = null;
		if ( t5 != 0L ) {
			hrgnUpdate = new HRGN ();
			hrgnUpdate.setPointer(new Pointer(t5));
		}
		RECT prcUpdate = new RECT ();
		UINT flags = new UINT (t7);

		// Step 3: call API function
		int ret = User32DLL.INSTANCE.ScrollWindowEx (hWnd, dx, dy, prcScroll, prcClip, hrgnUpdate, prcUpdate, flags);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t6 = this.params.get(6);
		memory.setDoubleWordMemoryValue (t6, new LongValue(prcUpdate.left));
		t6 += 4;
		memory.setDoubleWordMemoryValue (t6, new LongValue(prcUpdate.top));
		t6 += 4;
		memory.setDoubleWordMemoryValue (t6, new LongValue(prcUpdate.right));
		t6 += 4;
		memory.setDoubleWordMemoryValue (t6, new LongValue(prcUpdate.bottom));
		t6 += 4;

	}
}