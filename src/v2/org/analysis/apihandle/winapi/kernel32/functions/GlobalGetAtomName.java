/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GlobalGetAtomName.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.ATOM;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Retrieves a copy of the character string associated with the specified global
 * atom.
 * 
 * @param nAtom
 *            The global atom associated with the character string to be
 *            retrieved.
 * 
 * @param lpBuffer
 *            The buffer for the character string.
 * 
 * @param nSize
 *            The size, in characters, of the buffer.
 * 
 * @return If the function succeeds, the return value is the length of the
 *         string copied to the buffer, in characters, not including the
 *         terminating null character. If the function fails, the return value
 *         is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GlobalGetAtomName extends Kernel32API {

	public GlobalGetAtomName() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		ATOM nAtom = new ATOM(t1);
		char[] lpBuffer = new char[(int) t3];
		int nSize = (int) t3;

		UINT ret = Kernel32DLL.INSTANCE.GlobalGetAtomName(nAtom, lpBuffer, nSize);

		System.out.println("Return value:" + ret.intValue());
		register.mov("eax", new LongValue(ret.intValue()));

		memory.setText(this, t2, Convert.reduceText(lpBuffer));
	}

}
