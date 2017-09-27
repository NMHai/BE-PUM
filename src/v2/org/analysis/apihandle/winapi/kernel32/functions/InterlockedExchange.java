/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: InterlockedExchange.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

/**
 * Sets a 32-bit variable to the specified value as an atomic operation.
 * 
 * @param Target
 *            A pointer to the value to be exchanged. The function sets this
 *            variable to Value, and returns its prior value.
 * 
 * @param Value
 *            The value to be exchanged with the value pointed to by Target.
 * 
 * @return The function returns the initial value of the Target parameter.
 * 
 * @author Yen Nguyen
 *
 */
public class InterlockedExchange extends Kernel32API {

	public InterlockedExchange() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(t2));

		register.mov("eax", new LongValue(t2));
	}

}
