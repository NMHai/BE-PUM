/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CreateDialogParam.java
 * Created date: Mar 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class CreateRemoteThread extends User32API {

	public CreateRemoteThread() {
		super();
		NUM_OF_PARMS = 7;
	}


	@Override
	public void execute() {

		// Value x1 = stack.pop();
		// Value x2 = stack.pop();
		// Value x3 = stack.pop();
		// Value x4 = stack.pop();
		// Value x5 = stack.pop();
		// Value x6 = stack.pop();
		// Value x7 = stack.pop();
		//
		// System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4
		// + " " + x5 + " " + x6 + " " + x7);

		register.mov("eax", new LongValue(0));

	}

}
