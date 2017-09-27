package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cmps extends X86InstructionStub {

	@Override
	public BPState execute() {
		opSize = rule.getBitCount(inst) / 8;
		if (inst.hasPrefixREPZ()) {
			// System.out.println("Debug Instruction REPZ:" +
			// inst.getName());

			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
					&& src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// System.out.println();
				X86MemoryOperand edi = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				X86MemoryOperand esi = env.getMemory().evaluateAddress((X86MemoryOperand) src, env);

				// Comapre between EDI and ESI, if not true, increase ESI
				// and
				// EDI by 1
				Value ecx = env.getRegister().getRegisterValue("%ecx");

				if (ecx instanceof LongValue) {
					int j = 0;
					boolean zf = true;
					int t = (int) ((LongValue) ecx).getValue();
					for (int i = 0; i < t; i++) {
						Value ediValue = env.getMemory().getMemoryValue(
								new X86MemoryOperand(edi.getDataType(), edi.getDisplacement() + i * opSize), inst);
						Value esiValue = env.getMemory().getMemoryValue(
								new X86MemoryOperand(esi.getDataType(), esi.getDisplacement() + i * opSize), inst);
						// PHONG: fix here
						env.getFlag().changeFlagWithSUB(esiValue, ediValue, env, rule.getBitCount(inst));
						j++;
						if (!ediValue.equal(esiValue)) {
							// env.getFlag().setZFlag(new
							// BooleanValue(false));
							zf = false;
							break;
						}
					}
					env.getFlag().setZFlag(new BooleanValue(zf));
					if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
						env.getRegister().add("esi", j * opSize);
						env.getRegister().add("edi", j * opSize);
					} else {
						env.getRegister().sub("esi", j * opSize);
						env.getRegister().sub("edi", j * opSize);
					}
					env.getRegister().sub("ecx", j);
				}
			}
		} else if (inst.hasPrefixREPNZ()) {
			// System.out.println("Debug Instruction REPNZ" +
			// inst.getName());
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
					&& src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand edi = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				X86MemoryOperand esi = env.getMemory().evaluateAddress((X86MemoryOperand) src, env);

				// Comapre between EDI and ESI, if not true, increase ESI
				// and
				// EDI by 1
				Value ecx = env.getRegister().getRegisterValue("%ecx");

				if (ecx instanceof LongValue) {
					int j = 0;
					boolean zf = true;
					int t = (int) ((LongValue) ecx).getValue();
					for (int i = 0; i < t; i++) {
						Value ediValue = env.getMemory().getMemoryValue(
								new X86MemoryOperand(edi.getDataType(), edi.getDisplacement() + i * opSize), inst);
						Value esiValue = env.getMemory().getMemoryValue(
								new X86MemoryOperand(esi.getDataType(), esi.getDisplacement() + i * opSize), inst);
						// PHONG: fix here
						env.getFlag().changeFlagWithSUB(esiValue, ediValue, env, rule.getBitCount(inst));
						j++;
						if (ediValue.equal(esiValue)) {
							// env.getFlag().setZFlag(new
							// BooleanValue(false));
							zf = false;
							break;
						}
					}
					env.getFlag().setZFlag(new BooleanValue(zf));
					if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
						env.getRegister().add("esi", j * opSize);
						env.getRegister().add("edi", j * opSize);
					} else {
						env.getRegister().sub("esi", j * opSize);
						env.getRegister().sub("edi", j * opSize);
					}
					env.getRegister().sub("ecx", j);
				}
			}
		} else {
			// System.out.println("Debug Instruction " + inst.getName());

			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
					&& src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand edi = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				X86MemoryOperand esi = env.getMemory().evaluateAddress((X86MemoryOperand) src, env);

				// Comapre between EDI and ESI, if not true, increase ESI
				// and
				// EDI by 1
				// Value ecx = env.getRegister().getRegisterValue("%ecx");

				// boolean zf = true;
				Value ediValue = env.getMemory().getMemoryValue(edi, inst);
				Value esiValue = env.getMemory().getMemoryValue(esi, inst);
				// PHONG: fix here
				env.getFlag().changeFlagWithSUB(esiValue, ediValue, env, rule.getBitCount(inst));

				env.getFlag().setZFlag(new BooleanValue(ediValue.equal(esiValue)));
				if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
					env.getRegister().add("esi", new LongValue(opSize));
					env.getRegister().add("edi", new LongValue(opSize));
				} else {
					env.getRegister().sub("esi", new LongValue(opSize));
					env.getRegister().sub("edi", new LongValue(opSize));
				}
				// env.getRegister().sub("ecx", j);
			}
		}
		return null;
	}

}
