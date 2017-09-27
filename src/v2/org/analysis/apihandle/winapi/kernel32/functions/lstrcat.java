/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: lstrcat.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

/**
 * Appends one string to another.
 * 
 * @param lpString1
 *            : The first null-terminated string. This buffer must be large
 *            enough to contain both strings.
 * 
 * @param lpString2
 *            : The null-terminated string to be appended to the string
 *            specified in the lpString1 parameter.
 * 
 * @return If the function succeeds, the return value is a pointer to the
 *         buffer.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcat extends Kernel32API {

	/**
	 * 
	 */
	public lstrcat() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long destAddr = this.params.get(0);
		long scrAddr = this.params.get(1);

		String dest = destAddr == 0 ? null : memory.getText(this, destAddr);
		String src = scrAddr == 0 ? null : memory.getText(this, scrAddr);
		System.out.println("Destination String:" + dest + ", Source String:" + src);

		dest = dest.concat(src);
		System.out.println("Out:" + dest);

		memory.setText(this, destAddr, dest);
		// Null-terminated character
		memory.setByteMemoryValue(destAddr + dest.length(), new LongValue(0));
		register.mov("eax", new LongValue(1));
	}
}
