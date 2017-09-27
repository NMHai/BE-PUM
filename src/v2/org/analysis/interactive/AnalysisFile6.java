package v2.org.analysis.interactive;

/**
*
* @author NMHai

* Process the malware hostname_mew.exe
*/

import java.io.FileWriter;
import java.io.IOException;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPPath;

public class AnalysisFile6 extends AnalysisFactory {
	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}
	
	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = String.format("0x00%s\t%s",
				Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
		System.out.println(strInst);
		System.out.println(register.toString());
		
		if (address == 0x00401025) {
			System.out.println("Debug");
		}
				
		return ACTION_OP.NOP;
	}
}
