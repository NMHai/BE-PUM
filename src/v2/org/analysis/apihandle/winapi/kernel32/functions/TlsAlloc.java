/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: TlsAlloc.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * Allocates a thread local storage (TLS) index. Any thread of the process can
 * subsequently use this index to store and retrieve values that are local to
 * the thread, because each thread receives its own slot for the index.
 * 
 * @return If the function succeeds, the return value is a TLS index. The slots
 *         for the index are initialized to zero. If the function fails, the
 *         return value is TLS_OUT_OF_INDEXES. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class TlsAlloc extends Kernel32API {

	public TlsAlloc() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// This function has no parameters.
		DWORD ret = Kernel32DLL.INSTANCE.TlsAlloc();
		System.out.println("TLS index:" + ret.longValue());

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
