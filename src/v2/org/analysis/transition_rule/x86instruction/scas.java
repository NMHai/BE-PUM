package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class scas extends X86InstructionStub {

	@Override
	public BPState execute() {
		/*
		 * @_1: scasb jnz @_1 sub edi,esi ; EDI = API Name size
		 */
		// Scas is followed by jump condition. This is a loop
		// So we run this loop by symbolic execution with while loop
		// System.out.println("Debug Instruction scas");
		opSize = rule.getBitCount(inst) / 8;
		if (inst.hasPrefixREPNZ()) {
			while (true) {
				Value x = env.getRegister().getRegisterValue(dest.toString());
				Value y = env.getRegister().getRegisterValue("%edi");

				if (x instanceof LongValue && y instanceof LongValue) {
					long x1 = ((LongValue) x).getValue();
					long y1 = ((LongValue) y).getValue();

					Value z = env.getMemory().getMemoryValue(
							new X86MemoryOperand(((X86MemoryOperand) inst.getOperand2()).getDataType(), y1), inst);
					// PHONG: fix here
					env.getFlag().changeFlagWithSUB(x, z, env, rule.getBitCount(inst));

					// PHONG: fix here
					if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
						env.getRegister().sub("ecx", new LongValue(1));
						env.getRegister().add("%edi", new LongValue(opSize));
					} else {
						env.getRegister().add("ecx", new LongValue(1));
						env.getRegister().sub("%edi", new LongValue(opSize));
					}

					// Dieu kien bang xay ra
					// Thoat khoi vong lap
					if (z != null && z.equal(x)) {
						env.getFlag().setZFlag(new BooleanValue(true));
						break;
					} else {
						env.getFlag().setZFlag(new BooleanValue(false));
					}

					// Thoat ra khi ECX = 0
					Value ecx = env.getRegister().getRegisterValue("%ecx");
					if (ecx instanceof LongValue && ((LongValue) ecx).getValue() == 0) {
						break;
					}
				} else {
					break;
				}
			}

		} else if (inst.hasPrefixREPZ()) {
			while (true) {
				Value x = env.getRegister().getRegisterValue(dest.toString());
				Value y = env.getRegister().getRegisterValue("%edi");

				if (x instanceof LongValue && y instanceof LongValue) {
					long x1 = ((LongValue) x).getValue();
					long y1 = ((LongValue) y).getValue();

					Value z = env.getMemory().getMemoryValue(y1, inst);
					// PHONG: fix here
					env.getFlag().changeFlagWithSUB(x, z, env, rule.getBitCount(inst));

					// PHONG: fix here
					if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
						env.getRegister().sub("ecx", new LongValue(1));
						env.getRegister().add("%edi", new LongValue(opSize));
					} else {
						env.getRegister().add("ecx", new LongValue(1));
						env.getRegister().sub("%edi", new LongValue(opSize));
					}

					// Dieu kien khong bang xay ra
					// Thoat khoi vong lap
					if (z != null && z instanceof LongValue && x1 == ((LongValue) z).getValue()) {
						env.getFlag().setZFlag(new BooleanValue(true));
						// break;
					} else {
						env.getFlag().setZFlag(new BooleanValue(false));
						break;
					}

					// Thoat ra khi ECX = 0
					Value ecx = env.getRegister().getRegisterValue("%ecx");
					if (ecx instanceof LongValue && ((LongValue) ecx).getValue() == 0) {
						break;
					}
				} else {
					break;
				}
			}

		} else {
			Value x = env.getRegister().getRegisterValue(dest.toString());
			Value y = env.getRegister().getRegisterValue("%edi");

			if (x instanceof LongValue && y instanceof LongValue) {
				long x1 = ((LongValue) x).getValue();
				long y1 = ((LongValue) y).getValue();
				// while (true) {
				Value z = env.getMemory().getMemoryValue(y1, inst);
				// PHONG: fix here
				env.getFlag().changeFlagWithSUB(x, z, env, rule.getBitCount(inst));
				// Dieu kien bang xay ra
				if (z != null && z instanceof LongValue && x1 == ((LongValue) z).getValue()) {
					// break;
					env.getFlag().setZFlag(new BooleanValue(true));
				} else {
					env.getFlag().setZFlag(new BooleanValue(false));
				}

				// PHONG: fix here
				if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
					env.getRegister().add("%edi", new LongValue(opSize));
				} else {
					env.getRegister().sub("%edi", new LongValue(opSize));
				}
			}
		}
		return null;
	}

}
