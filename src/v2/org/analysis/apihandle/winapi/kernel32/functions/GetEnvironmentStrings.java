/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetEnvironmentStrings.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the environment variables for the current process.
 * 
 * @return If the function succeeds, the return value is a pointer to the
 *         environment block of the current process. If the function fails, the
 *         return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetEnvironmentStrings extends Kernel32API {

	/**
	 * 
	 */
	public GetEnvironmentStrings() {
		super();
		NUM_OF_PARMS = 0;
	}


	@Override
	public void execute() {

		// This function has no parameters.
		System.out.println("Argument: This function has no parameters");

		Pointer ret = Kernel32DLL.INSTANCE.GetEnvironmentStrings();

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret);
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value:" + value);
	}

}
