/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.LongValue;

/**
 * Determines whether a message is intended for the specified dialog box and, if
 * it is, processes the message.
 * 
 * @param hDlg
 *            A handle to the dialog box.
 * 
 * @param lpMsg
 *            A pointer to an MSG structure that contains the message to be
 *            checked.
 * 
 * @return If the message has been processed, the return value is nonzero. If
 *         the message has not been processed, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class IsDialogMessage extends User32API {
	public IsDialogMessage() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HWND hDlg = new HWND(new Pointer(t1));
		MSG lpMsg = new MSG();
		lpMsg.hWnd = new HWND(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t2))).getValue()));
		lpMsg.message = (int) ((LongValue) memory
				.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4))).getValue();
		lpMsg.wParam = new WPARAM(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				t2 += 4))).getValue());
		lpMsg.lParam = new LPARAM(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				t2 += 4))).getValue());
		lpMsg.time = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
				.getValue();
		lpMsg.pt = new POINT((int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				t2 += 4))).getValue(), (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t2 += 4))).getValue());
		BOOL ret = User32DLL.INSTANCE.IsDialogMessage(hDlg, lpMsg);

		System.out.println("Return: " + ret.longValue());
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
