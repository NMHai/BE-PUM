/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * @author yennlh
 *
 */
public class strlen extends MSVCRTAPI {

	public strlen() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		System.out.println("String:" + memory.getText(this, t1));
		
		Pointer str = memory.getPointer(t1);
		SIZE_T ret = MSVCRTDLL.INSTANCE.strlen(str);
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
