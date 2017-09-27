/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetModuleHandle.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.util.Map.Entry;

import org.jakstab.Program;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.HMODULE;

/**
 * The GetModuleHandle function retrieves a module handle for the specified
 * module if the file has been mapped into the address space of the calling
 * process.
 * 
 * @param name
 *            Pointer to a null-terminated string that contains the name of the
 *            module (either a .dll or .exe file).
 * 
 * @return If the function succeeds, the return value is a handle to the
 *         specified module. If the function fails, the return value is NULL. To
 *         get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetModuleHandle extends Kernel32API {

	public GetModuleHandle() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {

		HMODULE ret = null;
		String libraryName = null;
		long t1 = this.params.get(0);

		libraryName = memory.getText(this, t1);
		System.out.println("Library Name: " + libraryName);

		// Get from cache
		for (Entry<Long, PairEntry<String, Integer>> entry : APIHandle.libraryHandleMap.entrySet()) {
			if (entry.getValue().getKey().equals(libraryName)) {
				ret = new HMODULE();
				ret.setPointer(new Pointer(entry.getKey()));
			}
		}

		// If fail
		if (ret == null) {
			if (t1 == 0L) {
				ret = new HMODULE();
				ret.setPointer(new Pointer(Program.getProgram().getImageBase()));
				// returnValue = Kernel32.INSTANCE.GetModuleHandle(null);
			} else {
				ret = Kernel32.INSTANCE.GetModuleHandle(libraryName);
			}
		}

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));

		if (libraryName != null && value != 0) {
			APIHandle.libraryHandleMap.put(value, new PairEntry<String, Integer>(libraryName, -1));
		}
	}

}
