package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;

public class bt extends X86InstructionStub {

	@Override
	public BPState execute() {
		long temp_d = 0;
		long temp_s = 0;
		long result = 0;
		int get_bit = rule.getBitCount(inst);
//		Program.getProgram().setLog("===============================================" + "\n" + 
//						"Before implement BT:");
//		Program.getProgram().backupState(curState);
		if (dest.getClass().getSimpleName().equals("X86Register")
				|| dest.getClass().getSimpleName().equals("X86RegisterPart")
				|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t =
			// env.getMemory().evaluateAddress((X86MemoryOperand) dest,
			// env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}

		if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("Immediate")) {
			s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
					rule.getBitCount(inst)));
		}
//		Program.getProgram().setLog("Dest = " + d + ", Source = " + s);
		if (d != null && d instanceof LongValue && s != null && s instanceof LongValue) {
			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
//			Program.getProgram().setLog("Temp_d = " + temp_d + ", Temp_s = " + temp_s);
			result = new AnalysisBit().BT(temp_d, temp_s, get_bit);
//			Program.getProgram().setLog("Result = " + result);
			if (result == 1) {
				env.getFlag().setCFlag(new BooleanValue(true));
			} else {
				env.getFlag().setCFlag(new BooleanValue(false));
			}
//			Program.getProgram().setLog("After implement BT:");
//			Program.getProgram().backupState(curState);
//			Program.getProgram().setLog("===============================================");
		} else {
			Program.getProgram().setLog("BT with Symbol Value at " + curState.getLocation());
			rule.generateNextInstruction(inst, path, pathList, true);
			return curState;
		}
		return null;
	}

}
