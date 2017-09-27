/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FindAtom.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.ATOM;

/**
 * Searches the local atom table for the specified character string and
 * retrieves the atom associated with that string.
 * 
 * @param lpString
 *            The character string for which to search. Alternatively, you can
 *            use an integer atom that has been converted using the MAKEINTATOM
 *            macro. See Remarks for more information.
 * 
 * @return If the function succeeds, the return value is the atom associated
 *         with the given string. If the function fails, the return value is
 *         zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class FindAtom extends Kernel32API {

	public FindAtom() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String lpString = (t1 == 0) ? null : memory.getText(this, t1);
		ATOM ret = Kernel32DLL.INSTANCE.FindAtom(lpString);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
