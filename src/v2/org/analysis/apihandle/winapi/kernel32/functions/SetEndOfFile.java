/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetEndOfFile.java
 * Created date: Feb 6, 2015
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
 * Sets the physical file size for the specified file to the current position of
 * the file pointer. The physical file size is also referred to as the end of
 * the file. The SetEndOfFile function can be used to truncate or extend a file.
 * To set the logical end of a file, use the SetFileValidData function.
 * 
 * @param hFile
 *            : A handle to the file to be extended or truncated.
 * 
 * @author Yen Nguyen
 *
 */
public class SetEndOfFile extends Kernel32API {

	/**
	 * 
	 */
	public SetEndOfFile() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		System.out.println("Handle of file:" + t1);
		BOOL ret = Kernel32DLL.INSTANCE.SetEndOfFile(new HANDLE(new Pointer(t1)));
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
