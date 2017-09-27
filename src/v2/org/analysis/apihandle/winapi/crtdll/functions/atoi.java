/**
 * 
 */
package v2.org.analysis.apihandle.winapi.crtdll.functions;

import v2.org.analysis.apihandle.winapi.crtdll.CrtdllAPI;
import v2.org.analysis.value.LongValue;

/**
 * @author HaiNM
 *
 */
public class atoi extends CrtdllAPI {

	public atoi() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		String str = memory.getText(this, t1);

		System.out.println(String.format("Address: %d, String: %s", t1, str));
		int ret = myAtoi(str);		
		
		register.setRegisterValue("eax", new LongValue(ret));		
	}

	private int myAtoi(String str) {
		// TODO Auto-generated method stub
		if (str == null || str.length() < 1) {
			return 0;
		}
		
		int result = 0;
		try {
	        result = Integer.parseInt(str);
	    } catch (Exception e) {
	    }
	    
		return result;	    
	}

}
