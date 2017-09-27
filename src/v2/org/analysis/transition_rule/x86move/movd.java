package v2.org.analysis.transition_rule.x86move;

import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86Register;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86MoveStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class movd extends X86MoveStub {

	@Override
	public BPState execute() {// movd with MMX register
		Value source = rule.getValueOperand(src, env, inst);
		// System.out.println();
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// prevState.getMemoryValues().put((MemoryOperand) dest,
			// toMoveVal);
			X86MemoryOperand y = (X86MemoryOperand) dest;
			// Xu li truong hop mov fs:0, esp
			// Khi do se tac dong den SEH
			boolean b = false;
			if (y.getDisplacement() == 0 && y.getBase() == null) {
				b = true;
			} else {
				if (y.getBase() != null) {
					Value v = env.getRegister().getRegisterValue(y.getBase().toString());
					if (v instanceof LongValue) {
						b = (((LongValue) v).getValue() == 0);
					}
				}
			}

			if (y.getSegmentRegister() != null && y.getSegmentRegister().toString() == "%fs" && b) {
				// System.out.println("SEH Exploit:"
				// + curState.getLocation().toString());
				if (src.getClass().getSimpleName().equals("X86Register")) {
					if (((X86Register) src).toString().equals("%esp")) {
						rule.setSEH(curState);
					}
					// PHONG:
					// 20150501:-------------------------------------------------
					else {
						rule.setSEHOther(curState, ((X86Register) src).toString());
					}
					// -----------------------------------------------------------------
				}
			} else {
				// X86MemoryOperand t = env.getMemory().evaluateAddress(
				// (X86MemoryOperand) dest, env);

				if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
					// SEH Exploit
					return rule.processSEH(curState);
				}

				rule.setValueOperand(dest, source, env, inst);
			}
		} else {
			// PHONG - 20150422
			rule.setValueOperand(dest, source, env, inst);
		}
		return null;
	}

}
