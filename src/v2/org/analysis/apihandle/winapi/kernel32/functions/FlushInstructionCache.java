/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CopyFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Flushes the instruction cache for the specified process.
 * 
 * @param hProcess
 *            A handle to a process whose instruction cache is to be flushed.
 * 
 * @param lpBaseAddress
 *            A pointer to the base of the region to be flushed. This parameter
 *            can be NULL.
 * 
 * @param dwSize
 *            The size of the region to be flushed if the lpBaseAddress
 *            parameter is not NULL, in bytes.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FlushInstructionCache extends Kernel32API {

	public FlushInstructionCache() {
		super();
		NUM_OF_PARMS = 3;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HANDLE hProcess = new HANDLE(new Pointer(t1));
		LPVOID lpBaseAddress = new LPVOID(t2);
		SIZE_T dwSize = new SIZE_T(t3);
		BOOL ret = Kernel32DLL.INSTANCE.FlushInstructionCache(hProcess, lpBaseAddress, dwSize);

		register.mov("eax", new LongValue(ret.longValue()));
	}
}
