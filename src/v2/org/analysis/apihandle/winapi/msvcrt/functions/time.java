/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: time.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.ptr.LongByReference;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Get current time
 * 
 * Get the current calendar time as a value of type time_t.
 * 
 * The function returns this value, and if the argument is not a null pointer,
 * it also sets this value to the object pointed by timer.
 * 
 * The value returned generally represents the number of seconds since 00:00
 * hours, Jan 1, 1970 UTC (i.e., the current unix timestamp). Although libraries
 * may use a different representation of time: Portable programs should not use
 * the value returned by this function directly, but always rely on calls to
 * other elements of the standard library to translate them to portable types
 * (such as localtime, gmtime or difftime).
 * 
 * @param timer
 *            Pointer to an object of type time_t, where the time value is
 *            stored. Alternatively, this parameter can be a null pointer, in
 *            which case the parameter is not used (the function still returns a
 *            value of type time_t with the result).
 * 
 * @return The current calendar time as a time_t object.
 * 
 *         If the argument is not a null pointer, the return value is the same
 *         as the one stored in the location pointed by argument timer.
 * 
 *         If the function could not retrieve the calendar time, it returns a
 *         value of -1.
 * 
 *         time_t is an alias of a fundamental arithmetic type capable of
 *         representing times.
 * 
 * @author Yen Nguyen
 *
 */
public class time extends MSVCRTAPI {

	public time() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		LongByReference timer = (t1 == 0L) ? null : new LongByReference();
		long ret = MSVCRTDLL.INSTANCE.time(timer);
		
		register.mov("eax", new LongValue(ret));
		
		if (t1 != 0L) {
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(timer.getValue()));
		}
	}

}
