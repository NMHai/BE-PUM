/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: calloc.java
 * Created date: Nov 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;

/**
 * Allocate and zero-initialize array
 * 
 * Allocates a block of memory for an array of num elements, each of them size
 * bytes long, and initializes all its bits to zero.
 * 
 * The effective result is the allocation of a zero-initialized memory block of
 * (num*size) bytes.
 * 
 * If size is zero, the return value depends on the particular library
 * implementation (it may or may not be a null pointer), but the returned
 * pointer shall not be dereferenced.
 * 
 * @param num
 *            Number of elements to allocate.
 * 
 * @param size
 *            Size of each element.
 * 
 * @return On success, a pointer to the memory block allocated by the function.
 *         The type of this pointer is always void*, which can be cast to the
 *         desired type of data pointer in order to be dereferenceable. If the
 *         function failed to allocate the requested block of memory, a null
 *         pointer is returned.
 * 
 * @author Yen Nguyen
 *
 */
public class calloc extends MSVCRTAPI {

	public calloc() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		int num = (int) t1;
		int size = (int) t2;
		Pointer ret = MSVCRTDLL.INSTANCE.calloc(num, size);

		long addr = Pointer.nativeValue(ret);
		LongValue temp = new LongValue(0);
		for (int i = 0; i < (num * size); i++) {
			memory.setByteMemoryValue(addr + i, temp);
		}

		register.mov("eax", new LongValue(addr));
	}

}
