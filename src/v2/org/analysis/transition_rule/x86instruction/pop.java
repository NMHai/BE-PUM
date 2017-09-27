package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;

public class pop extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (dest == null) {
			return curState;
		}

		if (dest.getClass().getSimpleName().equals("X86Register")
				|| dest.getClass().getSimpleName().equals("X86RegisterPart")
				|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			env.getRegister().setRegisterValue(dest.toString(), env.getStack().pop());
		} else if (dest.getClass().getSimpleName().equals("Immediate")) {
			;
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			env.getMemory().setMemoryValue((X86MemoryOperand) dest, env.getStack().pop(), inst);
		}
		return null;
	}

}
