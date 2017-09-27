/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: TranslateMessage.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Translates virtual-key messages into character messages. The character
 * messages are posted to the calling thread's message queue, to be read the
 * next time the thread calls the GetMessage or PeekMessage function.
 * 
 * @param lpMsg
 *            A pointer to an MSG structure that contains message information
 *            retrieved from the calling thread's message queue by using the
 *            GetMessage or PeekMessage function.
 * 
 * @return If the message is translated (that is, a character message is posted
 *         to the thread's message queue), the return value is nonzero. If the
 *         message is WM_KEYDOWN, WM_KEYUP, WM_SYSKEYDOWN, or WM_SYSKEYUP, the
 *         return value is nonzero, regardless of the translation. If the
 *         message is not translated (that is, a character message is not posted
 *         to the thread's message queue), the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class TranslateMessage extends User32API {

	public TranslateMessage() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		MSG lpMsg = new MSG();
		lpMsg.hWnd = new HWND(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1))).getValue()));
		lpMsg.message = (int) ((LongValue) memory
				.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4))).getValue();
		lpMsg.wParam = new WPARAM(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				t1 += 4))).getValue());
		lpMsg.lParam = new LPARAM(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				t1 += 4))).getValue());
		lpMsg.time = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4)))
				.getValue();
		lpMsg.pt = new POINT((int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				t1 += 4))).getValue(), (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1 += 4))).getValue());

		BOOL ret = User32DLL.INSTANCE.TranslateMessage(lpMsg);
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
