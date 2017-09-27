/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindClose.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import v2.org.analysis.value.LongValue;

/**
 * Closes a file search handle opened by the FindFirstFile, FindFirstFileEx,
 * FindFirstFileNameW, FindFirstFileNameTransactedW, FindFirstFileTransacted,
 * FindFirstStreamTransactedW, or FindFirstStreamW functions.
 * 
 * @param hFindFile
 *            : The file search handle.
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FindClose extends Kernel32API {

	public FindClose() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		long t = this.params.get(0);
		HANDLE in_output = new HANDLE((t != 0L) ? new Pointer(t) : Pointer.NULL);
		BOOL ret = Kernel32DLL.INSTANCE.FindClose(in_output);

		// memory.setDoubleWordMemoryValue(t, new
		// LongValue(Pointer.nativeValue(in_output.getPointer())));

		register.mov("eax", new LongValue((ret.booleanValue()) ? 1 : 0));
	}

}
