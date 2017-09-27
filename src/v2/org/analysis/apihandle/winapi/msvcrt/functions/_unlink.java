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
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Delete a file.
 * int _unlink( const char *filename );
 * 
 * @author HaiNM
 *
 */
public class _unlink extends MSVCRTAPI {

	public _unlink() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String filename = memory.getText(this, t1);
		
		filename = Storage.getMappingPath(filename);
		
		System.out.println(String.format("Path: %s", filename));

		int ret = MSVCRTDLL.INSTANCE._unlink(filename);		
		register.mov("eax", new LongValue(ret));		
	}
}
