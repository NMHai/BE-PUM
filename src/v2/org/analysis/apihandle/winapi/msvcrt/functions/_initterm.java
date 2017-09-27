/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _initterm.java
 * Created date: Aug 22, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class _initterm extends MSVCRTAPI {
	
	public _initterm() {
		super();
		// Although this API have 2 parameter, but it does not pop them out
		// Therefore we don't need to do any thing
		NUM_OF_PARMS = 0;		
//		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
//		Value t1 = this.stack.getIndex(0);
//		Value t2 = this.stack.getIndex(4);
//		
//		if (t1 != null && t1 instanceof LongValue && t2 != null && t2 instanceof LongValue) {
//			Pointer p1 = memory.getPointer(((LongValue)t1).getValue());
//			Pointer p2 = memory.getPointer(((LongValue)t2).getValue());
//			
//			int ret = MSVCRTDLL.INSTANCE._initterm(p1, p2);
//
//			register.mov("eax", new LongValue(ret));
//			System.out.println("Return Value: " + ret);	
//		} 	
		
		register.mov("eax", new LongValue(0));
		System.out.println("Return Value: " + 0);
	}
}
