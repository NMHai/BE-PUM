/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SuspendThread.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Suspends the specified thread.
 * 
 * @param hThread
 *            A handle to the thread that is to be suspended.
 * 
 * @return If the function succeeds, the return value is the thread's previous
 *         suspend count; otherwise, it is (DWORD) -1. To get extended error
 *         information, use the GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class SuspendThread extends Kernel32API {
	private HANDLE currentThread;
	public static List<Long> suspendedThreadList = new ArrayList<Long>();

	public SuspendThread() {
		super();
		int lastError = Kernel32.INSTANCE.GetLastError();
		currentThread = Kernel32.INSTANCE.GetCurrentThread();
		Kernel32.INSTANCE.SetLastError(lastError);
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		// Add all of HANDLE of suspended thread to this list
		suspendedThreadList.add(t1);

		long value = 0;
		if (t1 == Pointer.nativeValue(this.currentThread.getPointer())) {
			System.out.println("** TRY TO SUSPEND CURRENT THREAD - AVOID TO ADVERSELY AFFECT SYSTEM **");
			value = suspendedThreadList.size() - 1;
		} else {
			HANDLE hThread = new HANDLE(new Pointer(t1));
			DWORD ret = Kernel32DLL.INSTANCE.SuspendThread(hThread);
			value = ret.longValue();

			// Restore the system to before be affected by API
			int lastError = Kernel32.INSTANCE.GetLastError();
			Kernel32DLL.INSTANCE.ResumeThread(hThread);
			Kernel32.INSTANCE.SetLastError(lastError);
		}
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
