/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GlobalFindAtom.java
 * Created date: Sep 17, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.ATOM;

/**
 * Searches the global atom table for the specified character string and
 * retrieves the global atom associated with that string.
 * 
 * @param lpString
 *            The null-terminated character string for which to search.
 *            Alternatively, you can use an integer atom that has been converted
 *            using the MAKEINTATOM macro. See the Remarks for more information.
 * 
 * @return If the function succeeds, the return value is the global atom
 *         associated with the given string. If the function fails, the return
 *         value is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GlobalFindAtom extends Kernel32API {

	public GlobalFindAtom() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String lpString = (t1 == 0L) ? null : memory.getText(this, t1);
		ATOM ret = Kernel32DLL.INSTANCE.GlobalFindAtom(lpString);
		
		if (ret.longValue() != 0) {
			register.mov("eax", new LongValue(ret.longValue()));
		} else {
			// HaiNM: Manually set the return value
			register.mov("eax", new LongValue(t1));
		}
	}

}
