/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: puts.java
 * Created date: Mar 18, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Write string to stdout
 * 
 * Writes the C string pointed by str to the standard output (stdout) and
 * appends a newline character ('\n').
 * 
 * The function begins copying from the address specified (str) until it reaches
 * the terminating null character ('\0'). This terminating null-character is not
 * copied to the stream.
 * 
 * Notice that puts not only differs from fputs in that it uses stdout as
 * destination, but it also appends a newline character at the end automatically
 * (which fputs does not).
 * 
 * @param str
 *            C string to be printed.
 * 
 * @return On success, a non-negative value is returned. On error, the function
 *         returns EOF and sets the error indicator (ferror).
 * 
 * @author Yen Nguyen
 *
 */
public class puts extends MSVCRTAPI {

	public puts() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String str = memory.getText(this, t1);
		int ret = MSVCRTDLL.INSTANCE.puts(str);
		
		register.mov("eax", new LongValue(ret));
	}

}
