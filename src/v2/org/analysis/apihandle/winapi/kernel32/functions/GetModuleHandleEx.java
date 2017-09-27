/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetModuleHandleEx.java
 * Created date: Nov 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.util.Map.Entry;

import org.jakstab.Program;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

/**
 * Retrieves a module handle for the specified module and increments the
 * module's reference count unless GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT
 * is specified. The module must have been loaded by the calling process.
 * 
 * @param dwFlags
 *            This parameter can be zero or one or more of the following values.
 *            If the module's reference count is incremented, the caller must
 *            use the FreeLibrary function to decrement the reference count when
 *            the module handle is no longer needed.
 * 
 * @param lpModuleName
 *            The name of the loaded module (either a .dll or .exe file), or an
 *            address in the module (if dwFlags is
 *            GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS).
 * 
 * @param phModule
 *            A handle to the specified module. If the function fails, this
 *            parameter is NULL.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, see GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetModuleHandleEx extends Kernel32API {

	public GetModuleHandleEx() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		int dwFlags = (int) t1;
		String lpModuleName = memory.getText(this, t2);
		HANDLEByReference phModule = new HANDLEByReference();

		System.out.println("Library Name: " + libraryName);

		BOOL ret = new BOOL(0);

		// Get from cache
		for (Entry<Long, PairEntry<String, Integer>> entry : APIHandle.libraryHandleMap.entrySet()) {
			if (entry.getValue().getKey().equals(libraryName)) {
				ret.setValue(1);
				phModule.setValue(new HANDLE(new Pointer(entry.getKey())));
			}
		}

		// If fail
		if (!ret.booleanValue()) {
			if (t2 == 0L) {
				ret.setValue(1);
				phModule.setValue(new HANDLE(new Pointer(Program.getProgram().getImageBase())));
				// returnValue = Kernel32.INSTANCE.GetModuleHandle(null);
			} else {
				ret = Kernel32DLL.INSTANCE.GetModuleHandleEx(dwFlags, lpModuleName, phModule);
			}
		}

		register.mov("eax", new LongValue(ret.longValue()));

		if (phModule.getValue() != null) {
			long value = Pointer.nativeValue(phModule.getValue().getPointer());
			if (libraryName != null) {
				APIHandle.libraryHandleMap.put(value, new PairEntry<String, Integer>(libraryName, -1));
			}
			memory.setDoubleWordMemoryValue(t3, new LongValue(value));
		}
	}

}
