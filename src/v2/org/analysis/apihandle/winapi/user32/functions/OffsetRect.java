/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: OffsetRect.java
 * Created date: Jul 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The OffsetRect function moves the specified rectangle by the specified
 * offsets.
 * 
 * @param lprc
 *            Pointer to a RECT structure that contains the logical coordinates
 *            of the rectangle to be moved.
 * 
 * @param dx
 *            Specifies the amount to move the rectangle left or right. This
 *            parameter must be a negative value to move the rectangle to the
 *            left.
 * 
 * @param dy
 *            Specifies the amount to move the rectangle up or down. This
 *            parameter must be a negative value to move the rectangle up.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class OffsetRect extends User32API {
	public OffsetRect() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		RECT lprc = new RECT();
		lprc.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1)))
				.getValue();
		lprc.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4)))
				.getValue();
		lprc.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4)))
				.getValue();
		lprc.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4)))
				.getValue();
		int dx = (int) t2;
		int dy = (int) t3;

		User32DLL.INSTANCE.OffsetRect(lprc, dx, dy);

		t1 = this.params.get(0);
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(lprc.left));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lprc.top));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lprc.right));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lprc.bottom));
	}

}
