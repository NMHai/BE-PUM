package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.LCID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

/**
 * Returns the locale identifier for the system locale.
 * 
 * @return Returns the locale identifier for the system default locale,
 *         identified by LOCALE_SYSTEM_DEFAULT.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemDefaultLCID extends Kernel32API {

	public GetSystemDefaultLCID() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		LCID ret = Kernel32.INSTANCE.GetSystemDefaultLCID();
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
