/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleMode.java
 * Created date: Oct 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Retrieves the current input mode of a console's input buffer or the current
 * output mode of a console screen buffer.
 * 
 * @param hConsoleHandle
 *            A handle to the console input buffer or the console screen buffer.
 *            The handle must have the GENERIC_READ access right. For more
 *            information, see Console Buffer Security and Access Rights.
 * 
 * @param lpMode
 *            A pointer to a variable that receives the current mode of the
 *            specified buffer.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetConsoleMode extends Kernel32API {

	public GetConsoleMode() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hConsoleHandle = new HANDLE(new Pointer(t1));
		DWORDByReference lpMode = new DWORDByReference();
		BOOL ret = Kernel32DLL.INSTANCE.GetConsoleMode(hConsoleHandle, lpMode);

		memory.setWordMemoryValue(t2, new LongValue(lpMode.getValue().longValue()));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
