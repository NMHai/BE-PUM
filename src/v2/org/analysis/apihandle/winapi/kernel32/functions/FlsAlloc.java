/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: FlsAlloc.java
 * Created date: Aug 23, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;

import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Allocates a fiber local storage (FLS) index. Any fiber in the process can
 * subsequently use this index to store and retrieve values that are local to
 * the fiber.
 * 
 * @param lpCallback
 *            A pointer to the application-defined callback function of type
 *            PFLS_CALLBACK_FUNCTION. This parameter is optional. For more
 *            information, see FlsCallback.
 * 
 *            An application-defined function. If the FLS slot is in use,
 *            FlsCallback is called on fiber deletion, thread exit, and when an
 *            FLS index is freed. Specify this function when calling the
 *            FlsAlloc function. The PFLS_CALLBACK_FUNCTION type defines a
 *            pointer to this callback function. FlsCallback is a placeholder
 *            for the application-defined function name.
 * 
 * @return If the function succeeds, the return value is an FLS index
 *         initialized to zero. If the function fails, the return value is
 *         FLS_OUT_OF_INDEXES. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class FlsAlloc extends Kernel32API {

	public FlsAlloc() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		try {
			DWORD ret = Kernel32DLL.INSTANCE.FlsAlloc(null);

			System.out.println("Return Value:" + ret.longValue());
			register.mov("eax", new LongValue(ret.longValue()));
		} catch (Exception e) {
			register.mov("eax", new SymbolValue(SymbolValue.generateString(this)));
			e.printStackTrace();
		}
	}

}
