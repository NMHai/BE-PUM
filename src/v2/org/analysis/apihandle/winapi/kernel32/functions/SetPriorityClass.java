/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SetPriorityClass.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Sets the priority class for the specified process. This value together with
 * the priority value of each thread of the process determines each thread's
 * base priority level.
 * 
 * @param hProcess
 *            A handle to the process. The handle must have the
 *            PROCESS_SET_INFORMATION access right. For more information, see
 *            Process Security and Access Rights.
 * 
 * @param dwPriorityClass
 *            The priority class for the process.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetPriorityClass extends Kernel32API {

	public SetPriorityClass() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hProcess = new HANDLE(new Pointer(t1));
		DWORD dwPriorityClass = new DWORD(t2);
		BOOL ret = Kernel32DLL.INSTANCE.SetPriorityClass(hProcess, dwPriorityClass);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
