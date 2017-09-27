package v2.org.analysis.interactive;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Value;

public class AnalysisHelloWorld extends AnalysisFactory {

	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		if (address == 0x40494e) {
			System.out.println("Debug");
		}
		
		String strInst = String.format("0x00%s\t%s",
				Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
		System.out.println(strInst);
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
