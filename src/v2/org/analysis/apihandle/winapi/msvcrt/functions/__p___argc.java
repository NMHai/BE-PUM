/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.Pointer;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * @author yennlh
 *
 */
public class __p___argc extends MSVCRTAPI {

	public __p___argc() {
		super();
		NUM_OF_PARMS = 0;
	}

	
	@Override
	public void execute() {
		Pointer ret = MSVCRTDLL.INSTANCE.__p___argc();

		register.mov("eax", new LongValue(Pointer.nativeValue(ret)));
	}

}
