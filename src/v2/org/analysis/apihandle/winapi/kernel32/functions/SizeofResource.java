/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SizeofResource.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.Program;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the size, in bytes, of the specified resource.
 * 
 * @param hModule
 *            A handle to the module whose executable file contains the
 *            resource.
 * 
 * @param hResInfo
 *            A handle to the resource. This handle must be created by using the
 *            FindResource or FindResourceEx function.
 * 
 * @return If the function succeeds, the return value is the number of bytes in
 *         the resource. If the function fails, the return value is zero. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SizeofResource extends Kernel32API {

	public SizeofResource() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HMODULE hModule = new HMODULE();
		if (t1 != 0L) {
			hModule.setPointer(new Pointer(t1));
		} else {
			String path = Program.getProgram().getAbsolutePathFile().replace('/', '\\');
			long handle = new LoadLibrary().execute(path);
			hModule.setPointer(new Pointer(handle));
		}
		HRSRC hResInfo = new HRSRC(new Pointer(t2));
		DWORD ret = Kernel32DLL.INSTANCE.SizeofResource(hModule, hResInfo);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
