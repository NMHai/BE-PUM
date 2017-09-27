/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GdiFlush.java
 * Created date: Sep 18, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.value.LongValue;

/**
 * The GdiFlush function flushes the calling thread's current batch.
 * 
 * @return If all functions in the current batch succeed, the return value is
 *         nonzero. If not all functions in the current batch succeed, the
 *         return value is zero, indicating that at least one function returned
 *         an error.
 * 
 * @author Yen Nguyen
 *
 */
public class GdiFlush extends Gdi32API {

	public GdiFlush() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		register.mov("eax", new LongValue(1));
	}

}
