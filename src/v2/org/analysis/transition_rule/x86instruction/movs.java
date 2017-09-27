package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class movs extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (inst.hasPrefixREPZ() || inst.hasPrefixREPNZ()) {
			Value ecx = env.getRegister().getRegisterValue("ecx");
			Value cx = env.getRegister().getRegisterValue("cx");
			if (ecx != null && ecx instanceof LongValue) {
				long t = ((LongValue) ecx).getValue();
//				t = LoopAlgorithm.getInstance().normalizeLoop(t);
				Program.getProgram().backupState(curState);

				for (int i = 0; i < t; i++) {
					movString(env, inst, opSize);
					env.getRegister().sub("ecx", new LongValue(1));
				}
				// env.getRegister().setRegisterValue("ecx", new
				// LongValue(0));
			} else if (cx != null && cx instanceof LongValue) {
				long t = ((LongValue) cx).getValue();
//				t = LoopAlgorithm.getInstance().normalizeLoop(t);
				Program.getProgram().backupState(curState);
				
				for (int i = 0; i < t; i++) {
					movString(env, inst, opSize);
					env.getRegister().sub("cx", new LongValue(1)); 
				}
				// env.getRegister().setRegisterValue("cx", new
				// LongValue(0));
			} else {
				movString(env, inst, opSize);
			}
		} else {
			movString(env, inst, opSize);
		}
		return null;
	}

	private void movString(Environment env, X86Instruction inst, int opSize) {
		int s = opSize;
		switch (opSize) {
		case 8:
			s = 1;
			break;
		case 16:
			s = 2;
			break;
		case 32:
			s = 4;
			break;
		}
		Value esi = env.getRegister().getRegisterValue("esi");
		Value edi = env.getRegister().getRegisterValue("edi");
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		if (dest != null && src != null && dest instanceof X86MemoryOperand && src instanceof X86MemoryOperand) {
			Value temp = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			env.getMemory().setMemoryValue((X86MemoryOperand) dest, temp, inst);
			Value df = env.getFlag().getDFlag();

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue() == true) {
				env.getRegister().sub("%esi", s);
				env.getRegister().sub("%edi", s);
			}
			// DF = 0
			else {
				env.getRegister().add("%esi", s);
				env.getRegister().add("%edi", s);
			}
		}
	}
}
