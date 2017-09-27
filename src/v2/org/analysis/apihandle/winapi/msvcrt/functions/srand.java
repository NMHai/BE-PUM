/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: srand.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Initialize random number generator
 * 
 * The pseudo-random number generator is initialized using the argument passed
 * as seed.
 * 
 * For every different seed value used in a call to srand, the pseudo-random
 * number generator can be expected to generate a different succession of
 * results in the subsequent calls to rand.
 * 
 * Two different initializations with the same seed will generate the same
 * succession of results in subsequent calls to rand.
 * 
 * If seed is set to 1, the generator is reinitialized to its initial value and
 * produces the same values as before any call to rand or srand.
 * 
 * In order to generate random-like numbers, srand is usually initialized to
 * some distinctive runtime value, like the value returned by function time
 * (declared in header <ctime>). This is distinctive enough for most trivial
 * randomization needs.
 * 
 * @param seed
 *            An integer value to be used as seed by the pseudo-random number
 *            generator algorithm.
 * 
 * @author Yen Nguyen
 *
 */
public class srand extends MSVCRTAPI {

	public srand() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		int seed = (int) t1;
		int ret = MSVCRTDLL.INSTANCE.srand(seed);
		register.setRegisterValue("eax", new LongValue(ret));
	}

}
