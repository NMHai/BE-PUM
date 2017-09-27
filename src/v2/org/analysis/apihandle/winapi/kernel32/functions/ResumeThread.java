/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: ResumeThread.java
 * Created date: Aug 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Decrements a thread's suspend count. When the suspend count is decremented to
 * zero, the execution of the thread is resumed.
 * 
 * @param hThread
 *            A handle to the thread to be restarted.
 * 
 * @return If the function succeeds, the return value is the thread's previous
 *         suspend count.
 * 
 * @author Yen Nguyen
 *
 */
public class ResumeThread extends Kernel32API {

	public ResumeThread() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HANDLE hThread = new HANDLE(new Pointer(t1));
		DWORD ret = Kernel32DLL.INSTANCE.ResumeThread(hThread);
		
		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
	}

}
