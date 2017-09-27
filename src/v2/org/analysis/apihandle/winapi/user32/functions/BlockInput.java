/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: BlockInput.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Blocks keyboard and mouse input events from reaching applications.
 * 
 * @param fBlockIt
 *            The function's purpose. If this parameter is TRUE, keyboard and
 *            mouse input events are blocked. If this parameter is FALSE,
 *            keyboard and mouse events are unblocked. Note that only the thread
 *            that blocked input can successfully unblock input.
 * 
 * @return If the function succeeds, the return value is nonzero. If input is
 *         already blocked, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class BlockInput extends User32API {

	public BlockInput() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int originalError = Kernel32.INSTANCE.GetLastError();

		BOOL fBlockIt = new BOOL(t1);
		BOOL ret = User32DLL.INSTANCE.BlockInput(fBlockIt);
		
		System.out.println("\t\tSPECIAL WINDOWS API: CAUSE GUI NOT RESPOND - NEED TO PASS");

		long value = ret.longValue();
		if (value == 0L) {
			int error = Kernel32.INSTANCE.GetLastError();
			if (error == 5L) {
				// ERROR_ACCESS_DENIED //
				// You must run in administrator mode to access it
				// So, pass it and change the return value to success value
				value = 1;
				// And the clear current error value, restore previous value
				Kernel32.INSTANCE.SetLastError(originalError);
			}
		}
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
