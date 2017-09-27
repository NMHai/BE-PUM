/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentProcessId.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.value.LongValue;

/**
 * This function returns the process identifier of the calling process.
 * 
 * @return The return value is the process identifier of the calling process.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentProcessId extends Kernel32API {

	public GetCurrentProcessId() {
		super();
		NUM_OF_PARMS = 0;
	}


	@Override
	public void execute() {
		Environment env = curState.getEnvironement();
		Register register = env.getRegister();
		int ret = Kernel32.INSTANCE.GetCurrentProcessId();
		System.out.println("Return Value: " + ret);
		register.mov("eax", new LongValue(ret));
	}

}
