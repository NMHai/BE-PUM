/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: ScrollDC.java
 * Created date: Oct 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HRGN;
import com.sun.jna.platform.win32.WinDef.RECT;

/**
 * The ScrollDC function scrolls a rectangle of bits horizontally and
 * vertically.
 * 
 * @param hDC
 *            Handle to the device context that contains the bits to be
 *            scrolled.
 * 
 * @param dx
 *            Specifies the amount, in device units, of horizontal scrolling.
 *            This parameter must be a negative value to scroll to the left.
 * 
 * @param dy
 *            Specifies the amount, in device units, of vertical scrolling. This
 *            parameter must be a negative value to scroll up.
 * 
 * @param lprcScroll
 *            Pointer to a RECT structure containing the coordinates of the bits
 *            to be scrolled. The only bits affected by the scroll operation are
 *            bits in the intersection of this rectangle and the rectangle
 *            specified by lprcClip. If lprcScroll is NULL, the entire client
 *            area is used.
 * 
 * @param lprcClip
 *            Pointer to a RECT structure containing the coordinates of the
 *            clipping rectangle. The only bits that will be painted are the
 *            bits that remain inside this rectangle after the scroll operation
 *            has been completed. If lprcClip is NULL, the entire client area is
 *            used.
 * 
 * @param hrgnUpdate
 *            Handle to the region uncovered by the scrolling process. ScrollDC
 *            defines this region; it is not necessarily a rectangle.
 * 
 * @param lprcUpdate
 *            Pointer to a RECT structure that receives the coordinates of the
 *            rectangle bounding the scrolling update region. This is the
 *            largest rectangular area that requires repainting. When the
 *            function returns, the values in the structure are in client
 *            coordinates, regardless of the mapping mode for the specified
 *            device context. This allows applications to use the update region
 *            in a call to the InvalidateRgn function, if required.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class ScrollDC extends User32API {

	public ScrollDC() {
		super();
		NUM_OF_PARMS = 7;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);

		HDC hDC = new HDC();
		hDC.setPointer(new Pointer(t1));
		int dx = (int) t2;
		int dy = (int) t3;

		RECT lprcScroll = new RECT();
		lprcScroll.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue();
		t4 += 4;
		lprcScroll.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue();
		t4 += 4;
		lprcScroll.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue();
		t4 += 4;
		lprcScroll.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue();

		RECT lprcClip = new RECT();
		lprcClip.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue();
		t5 += 4;
		lprcClip.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue();
		t5 += 4;
		lprcClip.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue();
		t5 += 4;
		lprcClip.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue();

		HRGN hrgnUpdate = new HRGN(new Pointer(t6));
		RECT lprcUpdate = new RECT();

		BOOL ret = User32DLL.INSTANCE.ScrollDC(hDC, dx, dy, lprcScroll, lprcClip, hrgnUpdate, lprcUpdate);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t7), new LongValue(lprcUpdate.left));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t7 += 4), new LongValue(lprcUpdate.top));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t7 += 4), new LongValue(lprcUpdate.right));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t7 += 4), new LongValue(lprcUpdate.bottom));
	}

}
