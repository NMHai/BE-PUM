/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.Pointer;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Duplicate strings.
 * char *_strdup( const char *strSource );
 * 
 * @author NMHai
 *
 */
public class _strdup extends MSVCRTAPI {

	public _strdup() {
		super();
		NUM_OF_PARMS = 1;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		String sourceStr = memory.getText(this, t1);
		System.out.println("Source String:" + sourceStr);
		Pointer source = memory.getPointer(t1);		
		
		Pointer ret = MSVCRTDLL.INSTANCE._strdup(source);
		long dest = Pointer.nativeValue(ret);
//		memory.createMemory(dest, sourceStr.length());
		memory.setText(this, dest, sourceStr);
		System.out.println("Dest String:" + memory.getText(this, dest));
		register.mov("eax", new LongValue(dest));
	}

}
