/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: InterlockedDecrement.java
 * Created date: Nov 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

/**
 * Decrements (decreases by one) the value of the specified 32-bit variable as
 * an atomic operation. To operate on 64-bit values, use the
 * InterlockedDecrement64 function.
 * 
 * @param Addend
 *            A pointer to the variable to be decremented.
 * 
 * @return The function returns the resulting decremented value.
 * 
 * @author Yen Nguyen
 *
 */
public class InterlockedDecrement extends Kernel32API {

	public InterlockedDecrement() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		LongValue value = (LongValue) memory.getDoubleWordMemoryValue(t1);
		
		long v = value.getValue();
		v--;
		value = new LongValue(v);
		
		memory.setDoubleWordMemoryValue(t1, value);
		
		register.mov("eax", value);
	}

}
