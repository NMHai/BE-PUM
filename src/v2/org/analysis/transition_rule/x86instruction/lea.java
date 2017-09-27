package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class lea extends X86InstructionStub {
	@Override
	public BPState execute() {
		// just like MOV but with minor difference - Come back later
		// http://stackoverflow.com/questions/1699748/what-is-the-difference-between-mov-and-lea

		// CHECK SRC IS VALID
		if (src.getClass().getSimpleName().equals("X86Register")) {
			Value srcVal = rule.getValueOperand(src, env, inst);
			if (srcVal != null && srcVal instanceof LongValue) {
				AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue) srcVal).getValue());
				if (!rule.checkAddressValid(env, aAddr)) {
					return rule.processSEH(curState);
				}
			}
		}

		if (dest.getClass().getSimpleName().equals("X86Register")) {
			if (src.getClass().getSimpleName().equals("X86Register")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("Immediate")) {
				env.getRegister().mov(dest.toString(),
						Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), rule.getBitCount(inst)));
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = (X86MemoryOperand) src;
				env.getRegister().setRegisterValue(dest.toString(), env.getMemory().calculateAddress(t));
			}
		} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
			if (src.getClass().getSimpleName().equals("X86Register")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("Immediate")) {
				env.getRegister().mov(dest.toString(),
						Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), rule.getBitCount(inst)));
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = (X86MemoryOperand) src;
				env.getRegister().setRegisterValue(dest.toString(), env.getMemory().calculateAddress(t));
			}
		} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			if (src.getClass().getSimpleName().equals("X86Register")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("X86RegisterPart")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				env.getRegister().mov(dest.toString(), src.toString());
			} else if (src.getClass().getSimpleName().equals("Immediate")) {
				env.getRegister().mov(dest.toString(),
						Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), rule.getBitCount(inst)));
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = (X86MemoryOperand) src;
				env.getRegister().setRegisterValue(dest.toString(), env.getMemory().calculateAddress(t));
			}
		}

		return null;
	}

}
