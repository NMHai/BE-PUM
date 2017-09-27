/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: AreFileApisANSI.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines whether the file I/O functions are using the ANSI or OEM character
 * set code page. This function is useful for 8-bit console input and output
 * operations.
 * 
 * @return If the set of file I/O functions is using the ANSI code page, the
 *         return value is nonzero. If the set of file I/O functions is using
 *         the OEM code page, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class AreFileApisANSI extends Kernel32API {

	public AreFileApisANSI() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		BOOL ret = Kernel32DLL.INSTANCE.AreFileApisANSI();

		register.mov("eax", new LongValue(ret.longValue()));
		System.out.println("Return Value: " + ret.longValue());

	}

}
