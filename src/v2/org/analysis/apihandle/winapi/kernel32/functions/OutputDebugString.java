/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.WString;

/**
 * Sends a string to the debugger for display.
 * 
 * @param lpOutputString
 *            The null-terminated string to be displayed.
 * 
 * @author Yen Nguyen
 *
 */
public class OutputDebugString extends Kernel32API {

	public OutputDebugString() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		WString lpOutputString = new WString(memory.getText(this, t1));
		Kernel32DLL.INSTANCE.OutputDebugString(lpOutputString);

	}

}
