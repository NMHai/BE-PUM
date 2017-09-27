/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _controlfp.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Gets and sets the floating-point control word. A more secure version of
 * _controlfp is available; see _controlfp_s.
 * 
 * @param nnew
 *            New control-word bit values.
 * 
 * @param mask
 *            Mask for new control-word bits to set.
 * 
 * @return For _control87 and _controlfp, the bits in the value returned
 *         indicate the floating-point control state. For a complete definition
 *         of the bits that are returned by _control87, see FLOAT.H.
 * 
 * @author Yen Nguyen
 *
 */
public class _controlfp extends MSVCRTAPI {

	public _controlfp() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		
		UINT nnew = new UINT(t1);
		UINT mask = new UINT(t2);
		UINT ret = MSVCRTDLL.INSTANCE._controlfp(nnew, mask);
		
		long value = ret.longValue();

		register.mov("eax", new LongValue(value));
		
//		stack.push(new LongValue(t2));
//		stack.push(new LongValue(t1));
	}

}
