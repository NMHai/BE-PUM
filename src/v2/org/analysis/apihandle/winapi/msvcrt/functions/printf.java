/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * @author HaiNM
 *
 */
public class printf extends MSVCRTAPI {

	public printf() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long pFILE = this.params.get(0);
		String str = memory.getText(this, pFILE);

		System.out.println(String.format("Address: %d, String: %s", pFILE, str));
		
		if (str != null) {
			register.mov("eax", new LongValue(str.length()));			
		} else {
			register.mov("eax", new LongValue(-1));
		}
	}

}
