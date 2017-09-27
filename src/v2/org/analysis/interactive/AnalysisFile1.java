package v2.org.analysis.interactive;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Value;

public class AnalysisFile1 extends AnalysisFactory {
	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
//		String strInst = String.format("0x00%s\t%s",
//				Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address)));
//		System.out.println(strInst);



//		if (address == 0x00427513) {
//			memory.toString();
//			System.out.println("debug");
//		}

		if ((address == 0x00407ce8)) {
			String strInst = String.format("0x00%s\t%s",
					Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
			System.out.println(strInst);
			System.out.println("EBP = " + state.getEnvironement().getRegister().getRegisterValue("ebp"));
			System.out.println("Mem = " + state.getEnvironement().getMemory().getMemoryValue((X86MemoryOperand)state.getInstruction().getOperand(0), state.getInstruction()));
		} else if ((address == 0x00407cc2)) {
			String strInst = String.format("0x00%s\t%s",
					Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
			System.out.println(strInst);
			System.out.println("AL=" + state.getEnvironement().getRegister().getRegisterValue("al"));
		} else if (address == 0x407cd4) {
			System.out.println(onReadMem(memAddr, 100));
		} else if (state.getInstruction().getName().startsWith("shld")) {
			Program.getProgram().backupState(state);
			String strInst = String.format("0x00%s\t%s",
					Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
			System.out.println(strInst);
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
