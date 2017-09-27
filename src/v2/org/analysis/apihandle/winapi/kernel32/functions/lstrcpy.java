/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: lstrcpy.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;

/**
 * Copies a string to a buffer.
 * 
 * @param lpString1
 *            : A buffer to receive the contents of the string pointed to by the
 *            lpString2 parameter. The buffer must be large enough to contain
 *            the string, including the terminating null character.
 * 
 * @param lpString2
 *            : The null-terminated string to be copied.
 * 
 * @return If the function succeeds, the return value is a pointer to the
 *         buffer.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcpy extends Kernel32API {

	/**
	 * 
	 */
	public lstrcpy() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long destAddr = this.params.get(0);
		long scrAddr = this.params.get(1);

		String dest = memory.getText(this, destAddr);
		String src = memory.getText(this, scrAddr);

		System.out.println("Destination Address:" + destAddr + ", Source String:" + src);

		WString ret = Kernel32DLL.INSTANCE.lstrcpy(new WString(dest), new WString(src));
		memory.setText(this, destAddr, ret.toString());

		// TODO: Fix here
		register.mov("eax", new LongValue((ret != null) ? destAddr : 0));
	}

}
