/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _setmode.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Sets the file translation mode.
 * 
 * @param fd
 *            File descriptor.
 * 
 * @param mode
 *            New translation mode.
 * 
 * @return If successful, returns the previous translation mode. If invalid
 *         parameters are passed to this function, the invalid-parameter handler
 *         is invoked, as described in Parameter Validation. If execution is
 *         allowed to continue, this function returns 1 and sets errno to
 *         either EBADF, which indicates an invalid file descriptor, or EINVAL,
 *         which indicates an invalid mode argument.
 * 
 * @author Yen Nguyen
 *
 */
public class _setmode extends MSVCRTAPI {

	public _setmode() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		
		int fd = (int) t1;
		int mode = (int) t2;
		
		int ret = MSVCRTDLL.INSTANCE._setmode(fd, mode);

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
	}

}
