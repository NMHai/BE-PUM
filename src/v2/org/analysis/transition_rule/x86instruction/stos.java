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

public class stos extends X86InstructionStub {
	@Override
	public BPState execute() {// Load String
		if (inst.hasPrefixREPZ() || inst.hasPrefixREPNZ()) {
			// System.out.println("Debug Instruction REPZ STOS:" +
			// inst.toString());
			Value ecx = env.getRegister().getRegisterValue("ecx");
			Value cx = env.getRegister().getRegisterValue("cx");
			if (ecx != null && ecx instanceof LongValue) {
				long t = ((LongValue) ecx).getValue();
//				t = LoopAlgorithm.getInstance().normalizeLoop(t);
				Program.getProgram().backupState(curState);

				for (int i = 0; i < t; i++) {
					storeString(env, inst, opSize);
					env.getRegister().sub("ecx", new LongValue(1));
				}
				// env.getRegister().setRegisterValue("ecx", new
				// LongValue(0));
			} else if (cx != null && cx instanceof LongValue) {
				long t = ((LongValue) cx).getValue();
//				t = LoopAlgorithm.getInstance().normalizeLoop(t);
				Program.getProgram().backupState(curState);

				for (int i = 0; i < t; i++) {
					storeString(env, inst, opSize);
					env.getRegister().sub("cx", new LongValue(1));
				}
				// env.getRegister().setRegisterValue("cx", new
				// LongValue(0));
			} else {
				storeString(env, inst, opSize);
			}

		} else {
			// System.out.println("Debug Instruction stos:" +
			// inst.toString());
			storeString(env, inst, opSize);
		}
		return null;
	}

	private void storeString(Environment env, X86Instruction inst, int opSize) {
		Operand dest = inst.getOperand1();
		Value store = null;

		String base = "edi";
		if (dest instanceof X86MemoryOperand) {
			if (((X86MemoryOperand) dest).getBase() != null) {
				base = ((X86MemoryOperand) dest).getBase().toString();
			}
		}

		Value df = env.getFlag().getDFlag();
		switch (opSize) {
		case 8:
			store = env.getRegister().getRegisterValue("al");
			if (dest instanceof X86MemoryOperand) {
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, store, inst);

				if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
					env.getRegister().sub(base, new LongValue(1));
				} else {
					env.getRegister().add(base, new LongValue(1));
				}
			}
			break;
		case 16:
			store = env.getRegister().getRegisterValue("ax");
			if (dest instanceof X86MemoryOperand) {
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, store, inst);

				if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
					env.getRegister().sub(base, new LongValue(2));
				} else {
					env.getRegister().add(base, new LongValue(2));
				}
			}
			break;
		case 32:
			store = env.getRegister().getRegisterValue("eax");
			if (dest instanceof X86MemoryOperand) {
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, store, inst);

				if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
					env.getRegister().sub(base, new LongValue(4));
				} else {
					env.getRegister().add(base, new LongValue(4));
				}
			}
			break;
		}
	}
}
