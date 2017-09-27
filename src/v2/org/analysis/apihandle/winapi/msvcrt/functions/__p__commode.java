/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: __p__commode.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Points to the _commode global variable, which specifies the default file
 * commit mode for file I/O operations.
 * 
 * @return Pointer to the _commode global variable.
 * 
 * @author Yen Nguyen
 *
 */
public class __p__commode extends MSVCRTAPI {

	public __p__commode() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		IntByReference ret = MSVCRTDLL.INSTANCE.__p__commode();

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, Pointer.nativeValue(ret.getPointer())),
				new LongValue(ret.getValue()));
	}

}
