/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetErrorMode.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Controls whether the system will handle the specified types of serious errors
 * or whether the process will handle them.
 * 
 * @param uMode
 *            The process error mode. This parameter can be one or more of the
 *            following values.
 * 
 * @return The return value is the previous state of the error-mode bit flags.
 * 
 * @author Yen Nguyen
 *
 */
public class SetErrorMode extends Kernel32API {

	public SetErrorMode() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		UINT uMode = new UINT(t1);
		UINT ret = Kernel32DLL.INSTANCE.SetErrorMode(uMode);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
