/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: CreateEllipticRgn.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

 
public class CreateEllipticRgn extends Gdi32API {
	public CreateEllipticRgn () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		int nLeftRect = (int) t0;
		int nTopRect = (int) t1;
		int nRightRect = (int) t2;
		int nBottomRect = (int) t3;

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.CreateEllipticRgn (nLeftRect, nTopRect, nRightRect, nBottomRect);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));

	}
}