/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: IsDBCSLeadByte.java
 * Created date: Aug 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.BYTE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines if a specified character is a lead byte for the system default
 * Windows ANSI code page (CP_ACP). A lead byte is the first byte of a two-byte
 * character in a double-byte character set (DBCS) for the code page.
 * 
 * @param TestChar
 *            The character to test.
 * 
 * @return Returns a nonzero value if the test character is potentially a lead
 *         byte. The function returns 0 if the test character is not a lead byte
 *         or if it is a single-byte character. To get extended error
 *         information, the application can call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class IsDBCSLeadByte extends Kernel32API {

	public IsDBCSLeadByte() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		BYTE TestChar = new BYTE(t1);
		
		BOOL ret = Kernel32DLL.INSTANCE.IsDBCSLeadByte(TestChar);
		
		long value = (ret == null) ? 0 : ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
