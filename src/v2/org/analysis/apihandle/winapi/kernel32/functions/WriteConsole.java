/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: WriteConsole.java
 * Created date: Mar 11, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * @author Yen Nguyen
 *
 */
public class WriteConsole extends Kernel32API {
	public WriteConsole() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
//		long t5 = this.params.get(4); // useless

		HANDLE hConsoleOutput = new HANDLE(new Pointer(t1));
		String lpBuffer = (t2 == 0L) ? null : memory.getText(this, t2);
		DWORD nNumberOfCharsToWrite = new DWORD(t3);
		DWORDByReference lpNumberOfCharsWritten = new DWORDByReference();
		LPVOID lpReserved = null;

		System.out.println(String.format("lpBuffer: %s", lpBuffer));

		BOOL ret = Kernel32DLL.INSTANCE.WriteConsole(hConsoleOutput, lpBuffer, nNumberOfCharsToWrite,
				lpNumberOfCharsWritten, lpReserved);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(t4, new LongValue(lpNumberOfCharsWritten.getValue().longValue()));
	}

}
