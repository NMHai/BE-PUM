/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetCursorPos.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.POINT;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the position of the mouse cursor, in screen coordinates.
 * 
 * @param lpPoint
 *            A pointer to a POINT structure that receives the screen
 *            coordinates of the cursor.
 * 
 * @return Returns nonzero if successful or zero otherwise. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCursorPos extends User32API {

	public GetCursorPos() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		POINT lpPoint = new POINT();
		BOOL ret = User32DLL.INSTANCE.GetCursorPos(lpPoint);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(lpPoint.x));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(lpPoint.y));
	}

}
