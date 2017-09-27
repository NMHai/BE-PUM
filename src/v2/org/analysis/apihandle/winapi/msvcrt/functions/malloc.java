/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: malloc.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Allocate memory block
 * 
 * Allocates a block of size bytes of memory, returning a pointer to the
 * beginning of the block.
 * 
 * The content of the newly allocated block of memory is not initialized,
 * remaining with indeterminate values.
 * 
 * If size is zero, the return value depends on the particular library
 * implementation (it may or may not be a null pointer), but the returned
 * pointer shall not be dereferenced.
 * 
 * @param size
 *            Size of the memory block, in bytes. size_t is an unsigned integral
 *            type.
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
public class malloc extends MSVCRTAPI {

	public malloc() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		SIZE_T size = new SIZE_T(t1);
		Pointer ret = MSVCRTDLL.INSTANCE.malloc(size);

		long value = Pointer.nativeValue(ret);

		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
		// HaiNM: allocate a memory
		memory.createMemory(value, t1);
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, value), new LongValue(ret.getInt(0)));

		// Special API - it does not pop value
//		stack.push(new LongValue(t1));
	}

}
