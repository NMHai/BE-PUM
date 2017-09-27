/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetTitleBarInfo.java
 * Created date: Sep 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

import v2.org.analysis.apihandle.winapi.structures.WinUser.TITLEBARINFO;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about the specified title bar.
 * 
 * @param hwnd
 *            A handle to the title bar whose information is to be retrieved.
 * 
 * @param pti
 *            A pointer to a TITLEBARINFO structure to receive the information.
 *            Note that you must set the cbSize member to sizeof(TITLEBARINFO)
 *            before calling this function.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetTitleBarInfo extends User32API {

	public GetTitleBarInfo() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hwnd = new HWND(new Pointer(t1));
		TITLEBARINFO pti = new TITLEBARINFO();
		pti.cbSize = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2)).getValue());
		pti.rcTitleBar = new RECT();
		pti.rcTitleBar.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();
		pti.rcTitleBar.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();
		pti.rcTitleBar.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();
		pti.rcTitleBar.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue();
		t2 += 4;
		for (int i = 0; i < pti.rgstate.length; i++) {
			pti.rgstate[i] = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 + (i * 4))).getValue());
		}

		BOOL ret = User32DLL.INSTANCE.GetTitleBarInfo(hwnd, pti);

		register.mov("eax", new LongValue(ret.longValue()));

		t2 = this.params.get(1);
		memory.setDoubleWordMemoryValue( //
				new X86MemoryOperand(DataType.INT32, t2), //
				new LongValue(pti.cbSize.longValue()));
		memory.setDoubleWordMemoryValue( //
				new X86MemoryOperand(DataType.INT32, t2 += 4),//
				new LongValue(pti.rcTitleBar.left));
		memory.setDoubleWordMemoryValue( //
				new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(pti.rcTitleBar.top));
		memory.setDoubleWordMemoryValue( //
				new X86MemoryOperand(DataType.INT32, t2 += 4), //
				new LongValue(pti.rcTitleBar.right));
		memory.setDoubleWordMemoryValue( //
				new X86MemoryOperand(DataType.INT32, t2 += 4), //
				new LongValue(pti.rcTitleBar.bottom));
		t2 += 4;
		for (int i = 0; i < pti.rgstate.length; i++) {
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + (i * 4)), //
					new LongValue(pti.rgstate[i].longValue()));
		}
	}
}
