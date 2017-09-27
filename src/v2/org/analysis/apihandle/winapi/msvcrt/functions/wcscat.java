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
public class wcscat extends MSVCRTAPI {

	public wcscat() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		Pointer destination = memory.getPointer(t1);
		Pointer source = memory.getPointer(t2);
		Pointer ret = MSVCRTDLL.INSTANCE.wcscat(destination, source);

		register.mov("eax", new LongValue(t1));
	}

}
