/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: memset.java
 * Created date: Jul 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * Fill block of memory
 * 
 * Sets the first num bytes of the block of memory pointed by ptr to the
 * specified value (interpreted as an unsigned char).
 * 
 * @param ptr
 *            Pointer to the block of memory to fill.
 * 
 * @param second
 *            Value to be set. The value is passed as an int, but the function
 *            fills the block of memory using the unsigned char conversion of
 *            this value.
 * 
 * @param num
 *            Number of bytes to be set to the value. size_t is an unsigned
 *            integral type.
 * 
 * @return ptr is returned.
 * 
 * @author Yen Nguyen
 *
 */
public class memset extends MSVCRTAPI {

	public memset() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		int t3 = this.params.get(2).intValue();

		for (int i = 0; i < t3; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t1), new LongValue(t2));
			t1 += 1;
		}

		long ret = t1;

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
	}

}
