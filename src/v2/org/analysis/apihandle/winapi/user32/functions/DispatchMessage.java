/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.LongValue;

/**
 * This function dispatches a message to a window procedure. It is typically
 * used to dispatch a message retrieved by the GetMessage function.
 * 
 * @param lpMsg
 *            Pointer to an MSG structure that contains the message.
 * @return The return value specifies the value returned by the window
 *         procedure. Although its meaning depends on the message being
 *         dispatched, the return value generally is ignored.
 * 
 * @author Yen Nguyen
 *
 */
public class DispatchMessage extends User32API {

	public DispatchMessage() {
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

		LRESULT ret = User32.INSTANCE.DispatchMessage(lpMsg);
		register.mov("eax", new LongValue(ret.longValue()));

		t1 = this.params.get(0);
		long value = Pointer.nativeValue(lpMsg.hWnd.getPointer());
		memory.setDoubleWordMemoryValue(t1, new LongValue(value));

		value = lpMsg.message;
		memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));

		value = lpMsg.wParam.longValue();
		memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));

		value = lpMsg.lParam.longValue();
		memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));

		value = lpMsg.time;
		memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));

		memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(lpMsg.pt.x));
		memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(lpMsg.pt.y));
	}

}
