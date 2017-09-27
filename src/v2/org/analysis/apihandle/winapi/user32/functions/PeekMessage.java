/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: PeekMessage.java
 * Created date: Feb 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import v2.org.analysis.value.LongValue;

/**
 * This function checks a thread message queue for a message and places the
 * message (if any) in the specified structure.
 * 
 * @param lpMsg
 *            Pointer to an MSG structure that receives message information.
 * 
 * @param hWnd
 *            Handle to the window whose messages are to be examined.
 * 
 * @param wMsgFilterMin
 *            Specifies the value of the first message in the range of messages
 *            to be examined.
 * 
 * @param wMsgFilterMax
 *            Specifies the value of the last message in the range of messages
 *            to be examined.
 * 
 * @param wRemoveMsg
 *            Specifies how messages are handled. This parameter can be one of
 *            the following values.
 * 
 * @return Nonzero indicates success. Zero indicates failure.
 * 
 * @author Yen Nguyen
 *
 */
public class PeekMessage extends User32API {

	public PeekMessage() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		/*
		 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
		 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
		 */
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		MSG lpMsg = new MSG();
		HWND hWnd = new HWND(new Pointer(t2));
		int wMsgFilterMin = (int) t3;
		int wMsgFilterMax = (int) t4;
		int wRemoveMsg = (int) t5;

		boolean ret = User32.INSTANCE.PeekMessage(lpMsg, hWnd, wMsgFilterMin, wMsgFilterMax, wRemoveMsg);
		register.mov("eax", new LongValue((ret) ? 1 : 0));

		long value = (lpMsg.hWnd == null) ? 0 : Pointer.nativeValue(lpMsg.hWnd.getPointer());
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
