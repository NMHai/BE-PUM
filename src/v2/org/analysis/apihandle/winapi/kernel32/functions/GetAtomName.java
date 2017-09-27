/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetAtomName.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.ATOM;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Retrieves a copy of the character string associated with the specified local
 * atom.
 * 
 * @param nAtom
 *            The local atom that identifies the character string to be
 *            retrieved.
 * 
 * @param lpBuffer
 *            The character string.
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
public class GetAtomName extends Kernel32API {

	public GetAtomName() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		ATOM nAtom = new ATOM(t1);
		int nSize = (int) t3;
		char[] lpBuffer = new char[nSize];

		UINT ret = Kernel32DLL.INSTANCE.GetAtomName(nAtom, lpBuffer, nSize);

		for (int i = 0; i < ret.intValue(); i++) {
			memory.setByteMemoryValue(t2 + i, new LongValue(lpBuffer[i]));
		}
		
		// Null-terminated
		if (ret.intValue() > 0) {
			memory.setByteMemoryValue(ret.intValue() + 1, new LongValue(0));
		}
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
