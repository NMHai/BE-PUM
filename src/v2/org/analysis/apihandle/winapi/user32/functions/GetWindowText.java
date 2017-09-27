/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowText.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

/**
 * Copies the text of the specified window's title bar (if it has one) into a
 * buffer. If the specified window is a control, the text of the control is
 * copied. However, GetWindowText cannot retrieve the text of a control in
 * another application.
 * 
 * @param hWnd
 *            A handle to the window or control containing the text.
 * 
 * @param lpString
 *            The buffer that will receive the text. If the string is as long or
 *            longer than the buffer, the string is truncated and terminated
 *            with a null character.
 * 
 * @param nMaxCount
 *            The maximum number of characters to copy to the buffer, including
 *            the null character. If the text exceeds this limit, it is
 *            truncated.
 * 
 * @return If the function succeeds, the return value is the length, in
 *         characters, of the copied string, not including the terminating null
 *         character. If the window has no title bar or text, if the title bar
 *         is empty, or if the window or control handle is invalid, the return
 *         value is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowText extends User32API {

	public GetWindowText() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HWND hWnd = new HWND(new Pointer(t1));
		int nMaxCount = (int) t3;
		char[] lpString = new char[nMaxCount];

		int ret = User32DLL.INSTANCE.GetWindowText(hWnd, lpString, nMaxCount);

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
		
		System.out.println("lpString: " + Convert.reduceText(lpString));
		
		for(int i = 0; i < ret; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t2 + i), new LongValue(lpString[i]));
		}
	}

}
