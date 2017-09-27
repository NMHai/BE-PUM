package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.Immediate;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;

public class cmp extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (dest.getClass().getSimpleName().equals("X86Register")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("Immediate")) {
			d = new LongValue(Convert.convetUnsignedValue(((Immediate) dest).getNumber().intValue(),
					rule.getBitCount(inst)));
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}

		if (src.getClass().getSimpleName().equals("X86Register")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("Immediate")) {
			s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
					rule.getBitCount(inst)));
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			s = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
		}

		if (inst.getName().startsWith("cmp")) {
			env.getFlag().changeFlagWithSUB(d, s, env, rule.getBitCount(inst));
		} else {
			env.getFlag().changeFlagWithTEST(d, s, env, rule.getBitCount(inst));
			// set compare status
		}
		return null;
	}

}
