/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CreateDialogParam.java
 * Created date: Mar 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class CreateWindowEx extends User32API {

	public CreateWindowEx() {
		super();
		NUM_OF_PARMS = 12;
	}

	@Override
	public void execute() {
		System.out.println("\t\tSPECIAL WINDOWS API: CALLBACK - UNIMPLEMENTED");
		register.mov("eax", new LongValue(0));
	}

}
