/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: memcpy.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * Copy block of memory
 * 
 * Copies the values of num bytes from the location pointed to by source
 * directly to the memory block pointed to by destination.
 * 
 * The underlying type of the objects pointed to by both the source and
 * destination pointers are irrelevant for this function; The result is a binary
 * copy of the data.
 * 
 * The function does not check for any terminating null character in source - it
 * always copies exactly num bytes.
 * 
 * To avoid overflows, the size of the arrays pointed to by both the destination
 * and source parameters, shall be at least num bytes, and should not overlap
 * (for overlapping memory blocks, memmove is a safer approach).
 * 
 * @param destination
 *            Pointer to the destination array where the content is to be
 *            copied, type-casted to a pointer of type void*.
 * 
 * @param source
 *            Pointer to the source of data to be copied, type-casted to a
 *            pointer of type const void*.
 * 
 * @param num
 *            Number of bytes to copy. size_t is an unsigned integral type.
 *
 * @return destination is returned.
 * 
 * @author Yen Nguyen
 *
 */
public class memcpy extends MSVCRTAPI {

	public memcpy() {
		super();
		NUM_OF_PARMS = 3;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		byte[] array = memory.getBytesArray(new AbsoluteAddress(t2), (int) t3);
		for (int i = 0; i < array.length; i++) {
			memory.setByteMemoryValue(t1 + i, new LongValue(array[i]));
		}
		
		register.mov("eax", new LongValue(t1));
	}

}
