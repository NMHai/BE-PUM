package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.platform.win32.WinDef.USHORT;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class is_wctype extends MSVCRTAPI {

	public is_wctype() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		USHORT _C = new USHORT(t1);
		USHORT _Type = new USHORT(t2);
		int ret = MSVCRTDLL.INSTANCE.is_wctype(_C, _Type);

		register.mov("eax", new LongValue(ret));
	}

}
