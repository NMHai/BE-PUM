/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: TlsGetValue.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the value in the calling thread's thread local storage (TLS) slot
 * for the specified TLS index. Each thread of a process has its own slot for
 * each TLS index.
 * 
 * @param dwTlsIndex
 *            The TLS index that was allocated by the TlsAlloc function.
 * 
 * @return If the function succeeds, the return value is the value stored in the
 *         calling thread's TLS slot associated with the specified index. If
 *         dwTlsIndex is a valid index allocated by a successful call to
 *         TlsAlloc, this function always succeeds. If the function fails, the
 *         return value is zero. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class TlsGetValue extends Kernel32API {

	public TlsGetValue() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		DWORD dwTlsIndex = new DWORD(t1);
		LPVOID ret = Kernel32DLL.INSTANCE.TlsGetValue(dwTlsIndex);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
