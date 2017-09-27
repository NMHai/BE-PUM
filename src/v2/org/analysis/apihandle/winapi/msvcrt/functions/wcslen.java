/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Get wide string length
 * 
 * Returns the length of the C wide string wcs.
 * 
 * This is the number of wide characters between wcs and the first null wide
 * character (without including it).
 * 
 * This is the wide character equivalent of strlen (<cstring>).
 * 
 * @param wcs
 *            C wide string.
 * 
 * @return The length of C wide string.
 * 
 * @author YenNLH	
 *
 */
public class wcslen extends MSVCRTAPI {


	public wcslen() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}
	
	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		Pointer wcs = memory.getPointer(t1);
		SIZE_T ret = MSVCRTDLL.INSTANCE.wcslen(wcs);
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
