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

public class lods extends X86InstructionStub {

	@Override
	public BPState execute() {
		// Load String
		// System.out.println("Debug Instruction lods");
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
					loadString(env, inst, opSize);
					env.getRegister().sub("ecx", new LongValue(1));
				}
			} else if (cx != null && cx instanceof LongValue) {
				long t = ((LongValue) cx).getValue();
//				t = LoopAlgorithm.getInstance().normalizeLoop(t);
				Program.getProgram().backupState(curState);

				for (int i = 0; i < t; i++) {
					loadString(env, inst, opSize);
					env.getRegister().sub("cx", new LongValue(1));
				}
			} else {
				loadString(env, inst, opSize);
			}

		} else {
			// System.out.println("Debug Instruction stos:" +
			// inst.toString());
			loadString(env, inst, opSize);
		}
		return null;
	}

	private void loadString(Environment env, X86Instruction inst, int opSize) {
		Operand source = inst.getOperand2();
		Value load = null;
		String base = "edi";
		if (source instanceof X86MemoryOperand) {
			load = env.getMemory().getMemoryValue((X86MemoryOperand) source, inst);
			if (((X86MemoryOperand) source).getBase() != null) {
				base = ((X86MemoryOperand) source).getBase().toString();
			}
		}

		if (load == null) {
			return;
		}
		Value df = env.getFlag().getDFlag();
		switch (opSize) {
		case 8:
			env.getRegister().setRegisterValue("al", load);

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
				env.getRegister().sub(base, new LongValue(1));
			} else {
				env.getRegister().add(base, new LongValue(1));
			}

			break;
		case 16:
			env.getRegister().setRegisterValue("ax", load);

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
				env.getRegister().sub(base, new LongValue(2));
			} else {
				env.getRegister().add(base, new LongValue(2));
			}

			break;
		case 32:
			env.getRegister().setRegisterValue("eax", load);

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
				env.getRegister().sub(base, new LongValue(4));
			} else {
				env.getRegister().add(base, new LongValue(4));
			}

			break;
		}
	}

}
