/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Creates or opens a named or unnamed event object.
 * 
 * @param lpEventAttributes
 *            [in, optional] A pointer to a SECURITY_ATTRIBUTES structure. If
 *            this parameter is NULL, the handle cannot be inherited by child
 *            processes.
 * 
 * @param bManualReset
 *            [in] If this parameter is TRUE, the function creates a
 *            manual-reset event object, which requires the use of the
 *            ResetEvent function to set the event state to nonsignaled. If this
 *            parameter is FALSE, the function creates an auto-reset event
 *            object, and system automatically resets the event state to
 *            nonsignaled after a single waiting thread has been released.
 * 
 * @param bInitialState
 *            [in] If this parameter is TRUE, the initial state of the event
 *            object is signaled; otherwise, it is nonsignaled.
 * 
 * @param lpName
 *            [in, optional] The name of the event object. The name is limited
 *            to MAX_PATH characters. Name comparison is case sensitive.
 * 
 * @return If the function succeeds, the return value is a handle to the event
 *         object. If the named event object existed before the function call,
 *         the function returns a handle to the existing object and GetLastError
 *         returns ERROR_ALREADY_EXISTS.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateEvent extends Kernel32API {

	public CreateEvent() {
		super();
		NUM_OF_PARMS = 4;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		SECURITY_ATTRIBUTES lpEventAttributes = null;
		BOOL bManualReset = new BOOL(t2);
		BOOL bInitialState = new BOOL(t3);
		String lpName = (t4 != 0L) ? memory.getText(this, t4) : null;
		
		System.out.println(String.format("lpName:%s", lpName));
		
		HANDLE ret = Kernel32DLL.INSTANCE.CreateEvent(null, bManualReset, bInitialState, lpName);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}
}
