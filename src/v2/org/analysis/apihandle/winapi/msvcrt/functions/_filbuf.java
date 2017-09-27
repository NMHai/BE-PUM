/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: strrchr.java
 * Created date: Sep 21, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.SymbolValue;

/**
 * _filbuf
 * 
 * @author HaiNM
 *
 */
public class _filbuf extends MSVCRTAPI {

	public _filbuf() {
		super();
		NUM_OF_PARMS = 0;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		System.out.println("No param");
		register.mov("eax", new SymbolValue(apiName));
	}

}
