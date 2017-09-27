package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Converts a long integer to a string. More secure versions of these functions
 * are available
 * 
 * @param value
 *            Number to be converted.
 * 
 * @param str
 *            String result.
 * 
 * @param radix
 *            Base of value.
 * 
 * @return Each of these functions returns a pointer to str. There is no error
 *         return.
 * 
 * @author Yen Nguyen
 *
 */
public class _ltoa extends MSVCRTAPI {

	public _ltoa() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long value = this.params.get(0);
		long ret = this.params.get(1);
		int radix = this.params.get(2).intValue();

		char[] str = new char[36];
		MSVCRTDLL.INSTANCE._ltoa(value, str, radix);

		for (int i = 0; i < str.length; i++) {
			if (str[i] != '\0') {
				memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, ret + i), new LongValue(str[i]));
			} else {
				memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, ret + i), new LongValue('\0'));
				break;
			}
		}

		register.mov("eax", new LongValue(ret));
	}

}
