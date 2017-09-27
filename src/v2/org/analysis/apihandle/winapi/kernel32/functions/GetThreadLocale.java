/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetThreadLocale.java
 * Created date: Aug 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.LCID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Returns the locale identifier of the current locale for the calling thread.
 * 
 * @return Returns the locale identifier of the locale associated with the
 *         current thread.
 * 
 * @author Yen Nguyen
 *
 */
public class GetThreadLocale extends Kernel32API {
	public GetThreadLocale() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		LCID ret = Kernel32DLL.INSTANCE.GetThreadLocale();

		long value = ret.longValue();
		
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
