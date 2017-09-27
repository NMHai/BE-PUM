/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;

/**
 * Copy string
 * 
 * Copies the C string pointed by source into the array pointed by
 * destination, including the terminating null character (and stopping at
 * that point).
 * 
 * To avoid overflows, the size of the array pointed by destination shall be
 * long enough to contain the same C string as source (including the
 * terminating null character), and should not overlap in memory with
 * source.
 * 
 * @param destination
 *            Pointer to the destination array where the content is to be
 *            copied.
 * 
 * @param source
 *            C string to be copied.
 * 
 * @return destination is returned.
 * 
 * @author YenNLH
 *
 */
public class strcpy extends MSVCRTAPI {

	public strcpy() {
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
		
		/*Pointer ret =*/ MSVCRTDLL.INSTANCE.strcpy(destination, source);
		
		register.mov("eax", new LongValue(t1));
	}

}
