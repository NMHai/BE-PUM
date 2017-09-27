/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: InflateRect.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The InflateRect function increases or decreases the width and height of the
 * specified rectangle. The InflateRect function adds dx units to the left and
 * right ends of the rectangle and dy units to the top and bottom. The dx and dy
 * parameters are signed values; positive values increase the width and height,
 * and negative values decrease them.
 * 
 * @param lprc
 *            A pointer to the RECT structure that increases or decreases in
 *            size.
 * 
 * @param dx
 *            The amount to increase or decrease the rectangle width. This
 *            parameter must be negative to decrease the width.
 * 
 * @param dy
 *            The amount to increase or decrease the rectangle height. This
 *            parameter must be negative to decrease the height.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class InflateRect extends User32API {

	public InflateRect() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		RECT lprc = new RECT();
		lprc.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1)).getValue();
		lprc.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lprc.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		lprc.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(t1 += 4)).getValue();
		int dx = (int) t2;
		int dy = (int) t3;

		BOOL ret = User32DLL.INSTANCE.InflateRect(lprc, dx, dy);

		register.mov("eax", new LongValue(ret.longValue()));

		t1 = this.params.get(0);
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(lprc.left));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lprc.top));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lprc.right));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lprc.bottom));
	}

}
