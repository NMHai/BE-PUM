/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: free.java
 * Created date: Sep 16, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.Pointer;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Deallocate memory block
 * 
 * A block of memory previously allocated by a call to malloc, calloc or realloc
 * is deallocated, making it available again for further allocations.
 * 
 * If ptr does not point to a block of memory allocated with the above
 * functions, it causes undefined behavior.
 * 
 * If ptr is a null pointer, the function does nothing.
 * 
 * Notice that this function does not change the value of ptr itself, hence it
 * still points to the same (now invalid) location.
 * 
 * @param ptr
 *            Pointer to a memory block previously allocated with malloc, calloc
 *            or realloc.
 * 
 * @author Yen Nguyen
 *
 */
public class free extends MSVCRTAPI {

	public free() {
		super();
		NUM_OF_PARMS = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see v2.org.analysis.apihandle.winapi.API#execute()
	 */
	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		MSVCRTDLL.INSTANCE.free(new Pointer(t1));

		// Special API - it does not pop value
		stack.push(new LongValue(t1));
	}

}
