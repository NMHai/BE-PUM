/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetConsoleCP.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the input code page used by the console associated with the calling
 * process. A console uses its input code page to translate keyboard input into
 * the corresponding character value.
 * 
 * @return The return value is a code that identifies the code page. For a list
 *         of identifiers, see Code Page Identifiers.
 * 
 * @author Yen Nguyen
 *
 */
public class GetConsoleCP extends Kernel32API {

	public GetConsoleCP() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {

		UINT ret = Kernel32DLL.INSTANCE.GetConsoleCP();

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
