package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;

public class setnae extends X86InstructionStub {

	@Override
	public BPState execute() {
		boolean isSet = false;
		if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
			isSet = true;
		}
		if (dest.getClass().getSimpleName().equals("X86Register")
				|| dest.getClass().getSimpleName().equals("X86RegisterPart")
				|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			env.getRegister().setRegisterValue(dest.toString(), ((isSet) ? new LongValue(1) : new LongValue(0)));

		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t = env.getMemory().evaluateAddress(
			// (X86MemoryOperand) dest, env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			env.getMemory()
					.setByteMemoryValue((X86MemoryOperand) dest, ((isSet) ? new LongValue(1) : new LongValue(0)));
		}

		return null;
	}

}
