/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindNextFile.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;

import org.jakstab.Program;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Determines the location of a resource with the specified type and name in the
 * specified module.
 *
 * @param hModule
 * 		A handle to the module whose portable executable file or an accompanying MUI file contains the resource. If this parameter is NULL, the function searches the module used to
 * 		create the current process.
 * @param lpName
 * 		The name of the resource. Alternately, rather than a pointer, this parameter can be MAKEINTRESOURCE(ID), where ID is the integer identifier of the resource. For more
 * 		information, see the Remarks section below.
 * @param lpType
 * 		The resource type. Alternately, rather than a pointer, this parameter can be MAKEINTRESOURCE(ID), where ID is the integer identifier of the given resource type. For standard
 * 		resource types, see Resource Types. For more information, see the Remarks section below.
 *
 * @author Yen Nguyen
 * @return If the function succeeds, the return value is a handle to the specified resource's information block. To obtain a handle to the resource, pass this handle to the
 * LoadResource function.
 */
public class FindResource extends Kernel32API {

	public FindResource() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HMODULE hModule = new HMODULE();

		if (t1 == 0L) {
			String path = Program.getProgram().getAbsolutePathFile().replace('/', '\\');
			long handle = new LoadLibrary().execute(path);
			hModule.setPointer(new Pointer(handle));
		} else {
			hModule.setPointer(new Pointer(t1));
		}

		String lpName = memory.getText(this, t2);
		String lpType = memory.getText(this, t3);

		System.out.printf("lpName: %s, lpType: %s", lpName, lpType);

		HRSRC ret;

		if (lpName.length() == 0)
			ret = Kernel32DLL.INSTANCE.FindResource(hModule, new Pointer(t2), lpType);
		else
			ret = Kernel32DLL.INSTANCE.FindResource(hModule, lpName, lpType);

		if (ret != null) {
			register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
		} else {
			register.mov("eax", new LongValue(0));
		}
	}

}
