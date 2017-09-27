/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Opens an existing named event object.
 * 
 * @param dwDesiredAccess
 *            The access to the event object. The function fails if the security
 *            descriptor of the specified object does not permit the requested
 *            access for the calling process. For a list of access rights, see
 *            Synchronization Object Security and Access Rights.
 * 
 * @param bInheritHandle
 *            If this value is TRUE, processes created by this process will
 *            inherit the handle. Otherwise, the processes do not inherit this
 *            handle.
 * 
 * @param lpName
 *            The name of the event to be opened. Name comparisons are case
 *            sensitive. This function can open objects in a private namespace.
 *            For more information, see Object Namespaces.
 * 
 * @return If the function succeeds, the return value is a handle to the event
 *         object. If the function fails, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenEvent extends Kernel32API {

	public OpenEvent() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		DWORD dwDesiredAccess = new DWORD(t1);
		BOOL bInheritHandle = new BOOL(t2);
		WString lpName = new WString(memory.getText(this, t3));
		HANDLE ret = Kernel32DLL.INSTANCE.OpenEvent(dwDesiredAccess, bInheritHandle, lpName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
