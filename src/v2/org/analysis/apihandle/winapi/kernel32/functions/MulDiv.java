/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: MulDiv.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Multiplies two 32-bit values and then divides the 64-bit result by a third
 * 32-bit value. The final result is rounded to the nearest integer.
 * 
 * @param nNumber
 *            The multiplicand.
 * 
 * @param nNumerator
 *            The multiplier.
 * 
 * @param nDenominator
 *            The number by which the result of the multiplication operation is
 *            to be divided.
 * 
 * @return If the function succeeds, the return value is the result of the
 *         multiplication and division, rounded to the nearest integer. If the
 *         result is a positive half integer (ends in .5), it is rounded up. If
 *         the result is a negative half integer, it is rounded down. If either
 *         an overflow occurred or nDenominator was 0, the return value is -1.
 * 
 * @author Yen Nguyen
 *
 */
public class MulDiv extends Kernel32API {

	public MulDiv() {
		super();
		NUM_OF_PARMS = 3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see v2.org.analysis.apihandle.winapi.API#execute()
	 */
	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		int nNumber = (int) t1;
		int nNumerator = (int) t2;
		int nDenominator = (int) t3;

		int ret = Kernel32DLL.INSTANCE.MulDiv(nNumber, nNumerator, nDenominator);

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
	}

}
