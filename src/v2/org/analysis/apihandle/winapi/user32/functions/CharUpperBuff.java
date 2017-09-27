/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Converts lowercase characters in a buffer to uppercase characters. The
 * function converts the characters in place.
 * 
 * @param lpsz
 *            [in, out] A buffer containing one or more characters to be
 *            processed.
 * 
 * @param cchLength
 *            The size, in characters, of the buffer pointed to by lpsz.
 * 
 * @return The return value is the number of characters processed.
 * 
 * @author Yen Nguyen
 *
 */
public class CharUpperBuff extends User32API {

	public CharUpperBuff() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String str = memory.getText(this, t1);

		char[] lpsz = str.toCharArray();
		DWORD cchLength = new DWORD(t2);
		DWORD ret = User32DLL.INSTANCE.CharUpperBuff(lpsz, cchLength);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setText(this, t1, new String(lpsz));
	}

}
