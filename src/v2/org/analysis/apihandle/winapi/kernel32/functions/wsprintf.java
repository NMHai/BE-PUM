/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: wsprintf.java
 * Created date: Mar 29, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.path.BPState;

/**
 * Writes formatted data to the specified buffer. Any arguments are converted
 * and copied to the output buffer according to the corresponding format
 * specification in the format string. The function appends a terminating null
 * character to the characters it writes, but the return value does not include
 * the terminating null character in its character count.
 * 
 * @param lpOut
 *            The buffer that is to receive the formatted output. The maximum
 *            size of the buffer is 1,024 bytes.
 * 
 * @param lpFmt
 *            The format-control specifications. In addition to ordinary ASCII
 *            characters, a format specification for each argument appears in
 *            this string. For more information about the format specification,
 *            see the Remarks section.
 * 
 * @param args
 *            One or more optional arguments. The number and type of argument
 *            parameters depend on the corresponding format-control
 *            specifications in the lpFmt parameter.
 * 
 * @return If the function succeeds, the return value is the number of
 *         characters stored in the output buffer, not counting the terminating
 *         null character. If the function fails, the return value is less than
 *         the length of the expected output. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class wsprintf extends Kernel32API {

	public wsprintf() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int dontknowhowtoimplementthisapi = 1;
	}

}
