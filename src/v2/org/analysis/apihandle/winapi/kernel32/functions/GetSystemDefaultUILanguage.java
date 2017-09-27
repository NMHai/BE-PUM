/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemDefaultUILanguage.java
 * Created date: Oct 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.WORD;

/**
 * Retrieves the language identifier for the system default UI language of the
 * operating system, also known as the "install language" on Windows Vista and
 * later. For more information, see User Interface Language Management.
 * 
 * @return Returns the language identifier for the system default UI language of
 *         the operating system. For more information, see the Remarks section.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemDefaultUILanguage extends Kernel32API {

	public GetSystemDefaultUILanguage() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {

		WORD ret = Kernel32DLL.INSTANCE.GetSystemDefaultUILanguage();

		register.mov("eax", new LongValue(ret.longValue()));

	}

}
