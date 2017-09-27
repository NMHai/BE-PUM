/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _initterm.java
 * Created date: Aug 22, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author Yen Nguyen
 *
 */
public class _initterm_e extends MSVCRTAPI {
	
	public _initterm_e() {
		super();
		// Although this API have 2 parameter, but it does not pop them out
		// Therefore we don't need to do any thing
		NUM_OF_PARMS = 0;		
//		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		Value t1 = this.stack.getIndex(0);
		Value t2 = this.stack.getIndex(4);
		
		if (t1 != null && t1 instanceof LongValue && t2 != null && t2 instanceof LongValue) {
			int ret = MSVCRTDLL.INSTANCE._initterm(memory.getPointer(((LongValue)t1).getValue()), memory.getPointer(((LongValue)t2).getValue()));

			register.mov("eax", new LongValue(ret));
			System.out.println("Return Value: " + ret);	
		} 		
	}
}
