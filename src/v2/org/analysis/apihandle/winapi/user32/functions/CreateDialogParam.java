/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CreateDialogParam.java
 * Created date: Mar 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author Yen Nguyen
 *
 */
public class CreateDialogParam extends User32API {

	public CreateDialogParam() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		System.out.println("\t\tSPECIAL WINDOWS API: CALLBACK - UNIMPLEMENTED");
		register.mov("eax", new LongValue(0));
	}

}
