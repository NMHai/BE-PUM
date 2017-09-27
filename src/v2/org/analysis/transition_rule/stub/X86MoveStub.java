package v2.org.analysis.transition_rule.stub;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Value;

public abstract class X86MoveStub extends AssemblyInstructionStub {
	
	// Bien dung de xu ly CMOVcc
	protected boolean isSet_CMOVcc = false; // Xet dieu kien cua CMOVcc

	public X86MoveStub() {
		this.groupName = "X86Move";
	}

	@Override
	protected BPState preExecute() {
		// DO NOTHING
		return null;
	}

	@Override
	protected void postExecute() {
		// DO NOTHING
	}

	protected BPState cmov() {
		
		if (isSet_CMOVcc == true) {
			Value s = null;
			//long temp_s;
			
			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// X86MemoryOperand t = env.getMemory().evaluateAddress(
				// (X86MemoryOperand) src, env);

				if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
					// SEH Exploit
					// System.out.println("SEH:"
					// + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}

				s = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			}
			
			env.getRegister().setRegisterValue(dest.toString(), s);
			isSet_CMOVcc = false;
			// Modified by Khanh: MSSV 51101594
			/*if (s instanceof LongValue) {
				temp_s = ((LongValue) s).getValue();
				env.getRegister().setRegisterValue(dest.toString(), new LongValue(temp_s));
				isSet_CMOVcc = false;
			}
			// Modified by Khanh: MSSV 51101594
			else{
				Program.getProgram().setLog("CMOVcc with Symbol Value at " + curState.getLocation());
				rule.generateNextInstruction(inst, path, pathList, true);
				return curState;
			}*/
		}
		
		return null;
	}
}