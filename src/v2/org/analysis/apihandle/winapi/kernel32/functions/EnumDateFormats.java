/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CopyFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

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
 * Enumerates the long date, short date, or year/month formats that are
 * available for a specified locale.
 * 
 * @author Yen Nguyen
 *
 */
public class EnumDateFormats extends Kernel32API {

	public EnumDateFormats() {
		super();
		NUM_OF_PARMS = 3;
	}


	@Override
	public void execute() {
		// long t1 = this.params.get(0);
		// long t2 = this.params.get(1);
		// long t3 = this.params.get(2);

		register.mov("eax", new LongValue(0));
	}
}
