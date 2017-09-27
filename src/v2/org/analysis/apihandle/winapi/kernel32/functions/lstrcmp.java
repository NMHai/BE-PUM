/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: lstrcmp.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Compares two character strings. The comparison is case-sensitive.
 * 
 * @param lpString1
 *            : The first null-terminated string to be compared.
 * 
 * @param lpString2
 *            : The second null-terminated string to be compared.
 * 
 * @return If the string pointed to by lpString1 is less than the string pointed
 *         to by lpString2, the return value is negative. If the string pointed
 *         to by lpString1 is greater than the string pointed to by lpString2,
 *         the return value is positive. If the strings are equal, the return
 *         value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcmp extends Kernel32API {

	/**
	 * 
	 */
	public lstrcmp() {
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

		int ret = Kernel32DLL.INSTANCE.lstrcmp(dest, src);

		register.mov("eax", new LongValue(ret));
	}

}
