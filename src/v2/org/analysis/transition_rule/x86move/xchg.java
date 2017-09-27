package v2.org.analysis.transition_rule.x86move;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86MoveStub;
import v2.org.analysis.value.Value;

public class xchg extends X86MoveStub {

	@Override
	public BPState execute() {// normal move
		Value temp = rule.getValueOperand(dest, env, inst);
		Value source = rule.getValueOperand(src, env, inst);

		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t = env.getMemory().evaluateAddress(
			// (X86MemoryOperand) dest, env);

			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				return rule.processSEH(curState);
			}
		}

		rule.setValueOperand(dest, source, env, inst);
		rule.setValueOperand(src, temp, env, inst);
		return null;
	}

}
