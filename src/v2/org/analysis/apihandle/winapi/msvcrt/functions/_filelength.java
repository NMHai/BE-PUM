/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: fopen.java
 * Created date: Sep 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Open a file.
 * int _open( const char *filename, int oflag [, int pmode] );
 * 
 * @author HaiNM
 *
 */
public class _filelength extends MSVCRTAPI {

	public _filelength() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		int handle = (int) t1;
		System.out.println("File Handle: " + handle);

		int ret = MSVCRTDLL.INSTANCE._filelength(handle);		
		register.mov("eax", new LongValue(ret));		
	}
}
