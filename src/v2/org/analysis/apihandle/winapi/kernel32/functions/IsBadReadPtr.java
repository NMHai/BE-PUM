/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: IsBadReadPtr.java
 * Created date: Oct 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;

/**
 * Verifies that the calling process has read access to the specified range of
 * memory.
 * 
 * @param lp
 *            A pointer to the first byte of the memory block.
 * 
 * @param ucb
 *            The size of the memory block, in bytes. If this parameter is zero,
 *            the return value is zero.
 * 
 * @return If the calling process has read access to all bytes in the specified
 *         memory range, the return value is zero. If the calling process does
 *         not have read access to all bytes in the specified memory range, the
 *         return value is nonzero. If the application is compiled as a
 *         debugging version, and the process does not have read access to all
 *         bytes in the specified memory range, the function causes an assertion
 *         and breaks into the debugger. Leaving the debugger, the function
 *         continues as usual, and returns a nonzero value. This behavior is by
 *         design, as a debugging aid.
 * 
 * @author Yen Nguyen
 *
 */
public class IsBadReadPtr extends Kernel32API {

	public IsBadReadPtr() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		LPVOID lp = new LPVOID(t1);
		UINT_PTR ucb = new UINT_PTR(t2);

		BOOL ret = Kernel32DLL.INSTANCE.IsBadReadPtr(lp, ucb);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
