/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: __p___initenv.java
 * Created date: Nov 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Internal._startupinfo;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * @author Yen Nguyen
 *
 */
public class __p___initenv extends MSVCRTAPI {

	public __p___initenv() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		Pointer ret = MSVCRTDLL.INSTANCE.__p___initenv();

		int c = 0;

		if (Pointer.nativeValue(ret.getPointer(0)) == 0L) {
			com.sun.jna.Memory _Env = new com.sun.jna.Memory(4);

			MSVCRTDLL.INSTANCE.__getmainargs(new IntByReference(), new com.sun.jna.Memory(4), _Env, 0,
					new _startupinfo());
			ret = _Env;
		}

		while (true) {
			try {
				memory.setDoubleWordMemoryValue(Pointer.nativeValue(ret.getPointer(0)),
						new LongValue(Pointer.nativeValue(ret.getPointer(0).getPointer(c * 4))));
				memory.setDoubleWordMemoryValue(Pointer.nativeValue(ret.getPointer(0).getPointer(c * 4)),
						new LongValue(Pointer.nativeValue(ret.getPointer(0).getPointer(c * 4).getPointer(0))));

				String env = ret.getPointer(0).getPointer(c * 4).getString(0);
				memory.setText(this, Pointer.nativeValue(ret.getPointer(0).getPointer(c * 4).getPointer(0)), env);
				System.out.println(String.format("ret[%d]: %s", c, env));
				c += 1;
			} catch (Exception ex) {
				break;
			}
		}

		// Pointer to memory space of ret
		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer(0))));
	}

}
