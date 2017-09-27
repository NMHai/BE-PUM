/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetLargestConsoleWindowSize.java
 * Created date: Sep 17, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the size of the largest possible console window, based on the
 * current font and the size of the display.
 * 
 * @param hConsoleOutput
 *            A handle to the console screen buffer.
 * 
 * @return If the function succeeds, the return value is a COORD structure that
 *         specifies the number of character cell rows (X member) and columns (Y
 *         member) in the largest possible console window. Otherwise, the
 *         members of the structure are zero. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLargestConsoleWindowSize extends Kernel32API {

	public GetLargestConsoleWindowSize() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HANDLE hConsoleOutput = new HANDLE(new Pointer(t1));
		int ret = Kernel32DLL.INSTANCE.GetLargestConsoleWindowSize(hConsoleOutput);

		register.mov("eax", new LongValue(ret));
	}

}
