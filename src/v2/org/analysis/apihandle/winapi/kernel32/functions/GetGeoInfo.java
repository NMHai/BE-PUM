/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetGeoInfo.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

 
public class GetGeoInfo extends Kernel32API {
	public GetGeoInfo () {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		long t4 = this.params.get(4);
		
		// Step 2: type conversion from C++ to Java
		int Location = (int) t0;
		int GeoType = (int) t1;
		char[] lpGeoData = null;
		if ( t2 != 0L ) lpGeoData = new char[(int) t3];
		for (int i = 0; i < lpGeoData.length; i++) {
			lpGeoData [i] = (char) ((LongValue) memory.getByteMemoryValue (t2)).getValue();
			t2 += 1;
		}
		int cchData = (int) t3;
		short LangId = (short) t4;

		// Step 3: call API function
		int ret = Kernel32DLL.INSTANCE.GetGeoInfo (Location, GeoType, lpGeoData, cchData, LangId);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		for (int i = 0; i < lpGeoData.length; i++) {
			memory.setByteMemoryValue (t2, new LongValue(lpGeoData [i]));
			t2 += 1;
		}
	}
}