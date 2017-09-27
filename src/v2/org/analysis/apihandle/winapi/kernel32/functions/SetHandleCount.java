/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetHandleCount.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.value.LongValue;

/**
 * The SetHandleCount function sets the number of file handles available to a
 * process. This function has no effect under Windows NT and Windows 95, because
 * there is no explicit file handle limit for applications on these platforms.
 * Under Win32s, there are only 20 file handles available to a process by
 * default; however you could use SetHandleCount to allow a process to use up to
 * 255 file handles.
 * 
 * @param uNumber
 *            : Specifies the number of file handles needed by the application.
 * 
 * @return Under Windows NT and Windows 95, this function simply returns the
 *         value specified in the uNumber parameter. Under Win32s, the return
 *         value specifies the number of file handles actually available to the
 *         application. It may be fewer than the number specified by the uNumber
 *         parameter.
 * 
 * @author Yen Nguyen
 *
 */
public class SetHandleCount extends Kernel32API {

	/**
	 * 
	 */
	public SetHandleCount() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		UINT ret = Kernel32DLL.INSTANCE.SetHandleCount(new UINT(t1));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
