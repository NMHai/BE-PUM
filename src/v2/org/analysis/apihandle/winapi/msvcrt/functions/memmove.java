/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: memmove.java
 * Created date: Nov 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Move block of memory
 * 
 * Copies the values of num bytes from the location pointed by source to the
 * memory block pointed by destination. Copying takes place as if an
 * intermediate buffer were used, allowing the destination and source to
 * overlap.
 * 
 * The underlying type of the objects pointed by both the source and destination
 * pointers are irrelevant for this function; The result is a binary copy of the
 * data.
 * 
 * The function does not check for any terminating null character in source - it
 * always copies exactly num bytes.
 * 
 * To avoid overflows, the size of the arrays pointed by both the destination
 * and source parameters, shall be at least num bytes.
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
public class memmove extends MSVCRTAPI {

	public memmove() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long destination = this.params.get(0);
		long source = this.params.get(1);
		long num = this.params.get(2);

		for (int i  = 0; i < (int) num; i++) {
			Value sourceValue = memory.getByteMemoryValue(source + i);
			if (sourceValue instanceof LongValue) {
				memory.setByteMemoryValue(destination + i, sourceValue);
			}
		}
		
		register.mov("eax", new LongValue(destination));
	}

}
