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
 * Creates or opens a named or unnamed mutex object.
 * 
 * @param lpMutexAttributes
 *            [in, optional] A pointer to a SECURITY_ATTRIBUTES structure. If
 *            this parameter is NULL, the handle cannot be inherited by child
 *            processes.
 * 
 * @param bInitialOwner
 *            [in] If this value is TRUE and the caller created the mutex, the
 *            calling thread obtains initial ownership of the mutex object.
 *            Otherwise, the calling thread does not obtain ownership of the
 *            mutex. To determine if the caller created the mutex, see the
 *            Return Values section.
 * 
 * @param lpName
 *            [in, optional] The name of the mutex object. The name is limited
 *            to MAX_PATH characters. Name comparison is case sensitive.
 * 
 * @return If the function succeeds, the return value is a handle to the newly
 *         created mutex object.
 * 
 *         If the function fails, the return value is NULL. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateMutex extends Kernel32API {

	public CreateMutex() {
		super();
		NUM_OF_PARMS = 3;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		SECURITY_ATTRIBUTES lpMutexAttributes = null;
		BOOL bInitialOwner = new BOOL(t2);
		String lpName = (t3 != 0L) ? memory.getText(this, t3) : null;
		HANDLE ret = Kernel32DLL.INSTANCE.CreateMutex(lpMutexAttributes, bInitialOwner, lpName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
	}
}
