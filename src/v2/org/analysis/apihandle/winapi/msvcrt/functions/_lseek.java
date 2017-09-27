package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Move a file pointer to the specified location.
 * long _lseek( int handle, long offset, int origin );
 * 
 * @author HaiNM
 *
 */
public class _lseek extends MSVCRTAPI {

	public _lseek() {
		super();
		NUM_OF_PARMS = 3;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		int handle = (int) t1;
		long offset = t2;
		int origin = (int) t3; 
		System.out.println(String.format("Handle:%d, Offset:%d, Origin:%d", handle, offset, origin));
		
//		SEEK_END
//         The file offset is set to the size of the file plus offset
//         bytes.
		if (origin == 2) {
			int size = MSVCRTDLL.INSTANCE._filelength(handle);
			register.mov("eax", new LongValue(size + offset));
			return; 
		}
		
		long ret = MSVCRTDLL.INSTANCE._lseek(handle, offset, origin);

		register.mov("eax", new LongValue(ret));		
	}
}
