package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.Immediate;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class aam extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO: Yen Nguyen - AAM - ASCII Adjust AX After Multiply
		Value AL = env.getRegister().getRegisterValue("al");
		AL = AL.evaluate(AL.getValueMap());
		long ALValue = Long.MIN_VALUE;
		if (inst.getOperandCount() == 0) {
			if (AL instanceof LongValue) {
				ALValue = ((LongValue) AL).getValue();
				env.getRegister().setRegisterValue("ah", new LongValue(ALValue / 10));
				env.getRegister().setRegisterValue("al", new LongValue(ALValue % 10));
			}
		} else {
			dest = inst.getOperand1();

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				Value t = env.getRegister().getRegisterValue(dest.toString());

				if (AL instanceof LongValue && t instanceof LongValue) {
					ALValue = ((LongValue) AL).getValue();
					long tValue = ((LongValue) t).getValue();

					env.getRegister().setRegisterValue("ah", new LongValue(ALValue / tValue));
					env.getRegister().setRegisterValue("al", new LongValue(ALValue % tValue));
				}
			} else if (dest.getClass().getSimpleName().equals("Immediate")) {
				long tValue = Convert.convetUnsignedValue(((Immediate) dest).getNumber().longValue(),
						rule.getBitCount(inst));

				if (AL instanceof LongValue) {
					ALValue = ((LongValue) AL).getValue();

					env.getRegister().setRegisterValue("ah", new LongValue(ALValue / tValue));
					env.getRegister().setRegisterValue("al", new LongValue(ALValue % tValue));
				}
			}
		}
		// PHONG: undefined != false
		env.getFlag().setCFlag(new BooleanValue(false));
		env.getFlag().setOFlag(new BooleanValue(false));
		env.getFlag().setAFlag(new BooleanValue(false));
		if (ALValue != Long.MIN_VALUE) {
			long t = Convert.convertSignedValue(ALValue, opSize);
			env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
			env.getFlag().setSFlag(new BooleanValue(t < 0));
			env.getFlag().setZFlag(new BooleanValue(t == 0));
		}
		return null;
	}

}
