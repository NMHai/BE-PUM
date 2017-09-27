package v2.org.analysis.interactive;

/**
*
* @author NMHai

* Process the malware api_test_morphnah.exe
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Value;

public class AnalysisFile3 extends AnalysisFactory {
	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}
	private String tempVal = "";
	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = String.format("0x00%s\t%s",
				Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
		System.out.println(strInst);
		System.out.println(register.toString());
		long mem = 0x401000;
		System.out.println(onReadMem(mem, 4));
		
		String temp = onReadByte(mem);
		
		if (!tempVal.equals(temp)) {
			System.out.println("Change memory!");
			tempVal = temp;
		}		
		
		return ACTION_OP.NOP;
	}	

	private long memAddr = 0;
	@Override
	public Value onAPI(BPState state, long address, Instruction inst, String apiName, List<Long> params) {
		if (file == null) {
			return null;
		}
		if (apiName.equals("GetStartupInfoA")) {			
			memAddr = params.get(0);
			System.out.println(onReadMem(memAddr, 100));
		}
		return null;
	}
}
