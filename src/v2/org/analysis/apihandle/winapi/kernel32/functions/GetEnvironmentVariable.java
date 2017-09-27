/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetEnvironmentVariable.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Kernel32;

/**
 * Retrieves the contents of the specified variable from the environment block
 * of the calling process.
 * 
 * @param lpName
 *            The name of the environment variable.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the contents of the specified
 *            environment variable as a null-terminated string. An environment
 *            variable has a maximum size limit of 32,767 characters, including
 *            the null-terminating character.
 * 
 * @param nSize
 *            The size of the buffer pointed to by the lpBuffer parameter,
 *            including the null-terminating character, in characters.
 * 
 * @return If the function succeeds, the return value is the number of
 *         characters stored in the buffer pointed to by lpBuffer, not including
 *         the terminating null character. If lpBuffer is not large enough to
 *         hold the data, the return value is the buffer size, in characters,
 *         required to hold the string and its terminating null character and
 *         the contents of lpBuffer are undefined. If the function fails, the
 *         return value is zero. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetEnvironmentVariable extends Kernel32API {

	public GetEnvironmentVariable() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpName = memory.getText(this, t1);
		char[] lpBuffer = new char[(int) t3];
		int ret = Kernel32.INSTANCE.GetEnvironmentVariable(lpName, lpBuffer, (int) t3);

		String variable = new String(lpBuffer);
		memory.setText(this, t2, variable, variable.length());

		register.mov("eax", new LongValue(ret));
	}

}
