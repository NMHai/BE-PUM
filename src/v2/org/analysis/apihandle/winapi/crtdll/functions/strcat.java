/**
 * 
 */
package v2.org.analysis.apihandle.winapi.crtdll.functions;

import v2.org.analysis.apihandle.winapi.crtdll.CrtdllAPI;
import v2.org.analysis.value.LongValue;

/**
 * @author HaiNM
 * Implement the method STRCAT
 */
public class strcat extends CrtdllAPI {

	public strcat() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		
		String destination = memory.getText(this, t1);
		String source = memory.getText(this, t2);
		System.out.println("Source:" + source + ", Dest:" + destination);
		if (source.length() > 0) {			
			destination = destination.concat(source);

			memory.setText(this, t1, destination, destination.length());
			// Null-terminated
//			memory.setByteMemoryValue(t1 + destination.length(), new LongValue(0));
		}
		
		System.out.println("New String:" + memory.getText(this, t1));
		register.mov("eax", new LongValue(t1));	
	}	
}
