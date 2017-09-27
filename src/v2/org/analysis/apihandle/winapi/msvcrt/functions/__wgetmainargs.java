/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: __getmainargs.java
 * Created date: Jul 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.Program;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Internal._startupinfo;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * Invokes command-line parsing and copies the arguments to main() back through
 * the passed pointers.
 * 
 * @param _Argc
 *            An integer that contains the number of arguments that follow in
 *            argv. The argc parameter is always greater than or equal to 1.
 * 
 * @param _Argv
 *            An array of null-terminated strings representing command-line
 *            arguments entered by the user of the program. By convention,
 *            argv[0] is the command with which the program is invoked, argv[1]
 *            is the first command-line argument, and so on, until argv[argc],
 *            which is always NULL. The first command-line argument is always
 *            argv[1] and the last one is argv[argc  1].
 * 
 * @param _Env
 *            An array of strings that represent the variables set in the user's
 *            environment. This array is terminated by a NULL entry.
 * 
 * @param _DoWildCard
 *            An integer that if set to 1 expands the wildcards in the command
 *            line arguments, or if set to 0 does nothing.
 * 
 * @param _StartInfo
 *            Other information to be passed to the CRT DLL.
 * 
 * @return 0 if successful; a negative value if unsuccessful.
 * 
 * @author Yen Nguyen
 *
 */
public class __wgetmainargs extends MSVCRTAPI {

	public __wgetmainargs() {
		super();
		NUM_OF_PARMS = 5;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		IntByReference _Argc = new IntByReference();
		com.sun.jna.Memory _Argv = new com.sun.jna.Memory(4);
		com.sun.jna.Memory _Env = new com.sun.jna.Memory(4);
		int _DoWildCard = (int) t4;
		_startupinfo _StartInfo = new _startupinfo();

		int ret = MSVCRTDLL.INSTANCE.__wgetmainargs(_Argc, _Argv, _Env, _DoWildCard, _StartInfo);

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);

		memory.setDoubleWordMemoryValue(t1, new LongValue(_Argc.getValue()));

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

		memory.setDoubleWordMemoryValue(t5, new LongValue(_StartInfo.newmode));
	}
}
