/**
 * 
 */
package v2.org.analysis.apihandle.winapi.crtdll.functions;

import org.jakstab.Program;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.crtdll.CrtdllAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Internal._startupinfo;
import v2.org.analysis.value.LongValue;

/**
 * @author HaiNM
 *
 */
public class __getmainargs extends CrtdllAPI {

	public __getmainargs() {
		super();
		NUM_OF_PARMS = 4;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		
		IntByReference _Argc = new IntByReference();
		com.sun.jna.Memory _Argv = new com.sun.jna.Memory(4);
		com.sun.jna.Memory _Env = new com.sun.jna.Memory(4);
		int _DoWildCard = (int) t4;
		_startupinfo _StartInfo = new _startupinfo();
		_StartInfo.newmode = 0;
		
//		int ret = CrtdllDLL.INSTANCE.__getmainargs(_Argc, _Argv, _Env, _DoWildCard, _StartInfo);
		int ret = MSVCRTDLL.INSTANCE.__getmainargs(_Argc, _Argv, _Env, _DoWildCard, _StartInfo);
		
		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
		
		System.out.println("The number of arguments:" + _Argc.getValue());
//		memory.setDoubleWordMemoryValue(t1, new LongValue(_Argc.getValue()));
		// HaiNM: always set the number of arguments to 1
		memory.setDoubleWordMemoryValue(t1, new LongValue(1));

		// Store the absolute path instead of the real value
		memory.setDoubleWordMemoryValue(t2, new LongValue(Pointer.nativeValue(_Argv.getPointer(0))));
		memory.setDoubleWordMemoryValue(Pointer.nativeValue(_Argv.getPointer(0)),
				new LongValue(Pointer.nativeValue(_Argv.getPointer(0).getPointer(0))));
		if (Program.getProgram().getFileName().contains("hostname")) {
			memory.setDoubleWordMemoryValue(Pointer.nativeValue(_Argv.getPointer(0)) + 4,
					new LongValue(0));
		}
		memory.setDoubleWordMemoryValue(Pointer.nativeValue(_Argv.getPointer(0).getPointer(0)),
				new LongValue(Pointer.nativeValue(_Argv.getPointer(0).getPointer(0).getPointer(0))));

		memory.setText(this, Pointer.nativeValue(_Argv.getPointer(0).getPointer(0).getPointer(0)), Program.getProgram()
				.getAbsolutePathFile());
		System.out.println("_Argv[0]: " + Program.getProgram().getAbsolutePathFile());
		// for (int i = 0; i < _Argc.getValue(); i++) {
		// System.out.println(i + ": " + _Argv.getPointer(0).getPointer(i *
		// 4).getString(0));
		// }

		// Pointer to memory space of _Env
		memory.setDoubleWordMemoryValue(t3, new LongValue(Pointer.nativeValue(_Env.getPointer(0))));

		int c = 0;
		while (true) {
			try {
				memory.setDoubleWordMemoryValue(Pointer.nativeValue(_Env.getPointer(0)),
						new LongValue(Pointer.nativeValue(_Env.getPointer(0).getPointer(c * 4))));
				memory.setDoubleWordMemoryValue(Pointer.nativeValue(_Env.getPointer(0).getPointer(c * 4)),
						new LongValue(Pointer.nativeValue(_Env.getPointer(0).getPointer(c * 4).getPointer(0))));

				String env = _Env.getPointer(0).getPointer(c * 4).getString(0);
				memory.setText(this, Pointer.nativeValue(_Env.getPointer(0).getPointer(c * 4).getPointer(0)), env);
				System.out.println(String.format("_Env[%d]: %s", c, env));
				c += 1;
			} catch (Exception ex) {
				break;
			}
		}	
	}	
}
