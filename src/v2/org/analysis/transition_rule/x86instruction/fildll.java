package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.DataType;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;

public class fildll extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (dest == null) {
			return curState;
		}

		if (dest.getClass().getSimpleName().equals("X86Register")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("Immediate")) {
			// Immediate t = (Immediate) dest;
			// long x = t.getNumber().longValue();
			d = new LongValue(Convert.convetUnsignedValue(((Immediate) dest).getNumber().longValue(),
					rule.getBitCount(inst)));
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// d = symbolValueMemoryOperand
			// .getMemoryOperandVal((X86MemoryOperand) dest);
			X86MemoryOperand t = (X86MemoryOperand) dest;
			if (t.getSegmentRegister() != null && t.getSegmentRegister().toString() == "%fs"
					&& t.getDisplacement() == 0) {
				// PHONG: update 20150526-----------------
				d = new LongValue(env.getSystem().getSEHHandler().getStart().getAddrSEHRecord());
				// ---------------------------------------
			} else {
				d = env.getMemory().getMemoryValue(DataType.INT64, t, inst);
			}
		} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		}
		System.out.println(" -> fildll: " + d);
		
		// int -> double -> float_int64
		LongValue lvVal = new LongValue(Double.doubleToLongBits(((LongValue) d).getValue()));
		env.getRegister().pushFPU(lvVal);
		return null;
	}

}
