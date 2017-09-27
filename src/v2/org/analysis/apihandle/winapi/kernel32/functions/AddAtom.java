/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: AddAtom.java
 * Created date: Jul 12, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Adds a character string to the local atom table and returns a unique value
 * (an atom) identifying the string.
 * 
 * @param lpString
 *            The null-terminated string to be added. The string can have a
 *            maximum size of 255 bytes. Strings differing only in case are
 *            considered identical. The case of the first string added is
 *            preserved and returned by the GetAtomName function. Alternatively,
 *            you can use an integer atom that has been converted using the
 *            MAKEINTATOM macro. See the Remarks for more information.
 * 
 * @return If the function succeeds, the return value is the newly created atom.
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class AddAtom extends Kernel32API {
	
	public AddAtom() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String lpString = memory.getText(this, t1);
		
		int ret = Kernel32DLL.INSTANCE.AddAtom(lpString );
		
		register.mov("eax", new LongValue(ret));
	}

}
