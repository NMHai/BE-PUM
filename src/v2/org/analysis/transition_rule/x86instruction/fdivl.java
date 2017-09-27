package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.Immediate;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fdivl extends X86InstructionStub {

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
				d = env.getMemory().getQWordMemoryValue(t);
			}

		} else if (dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {
			d = env.getRegister().getRegisterValue(dest.toString());
		}
		
		Value st = env.getRegister().getSt();
		double dbOp1, dbOp2;
		dbOp1 = Double.longBitsToDouble(((LongValue) d).getValue());
		dbOp2 = Double.longBitsToDouble(((LongValue) st).getValue());
		double dbRet = dbOp2 != 0 ? dbOp2 / dbOp1 : 0.0;
		LongValue ret = new LongValue(Double.doubleToLongBits(dbRet));
		env.getRegister().setSt(ret);
		System.out.println(String.format(" -> %.2f / %.2f = %.2f", dbOp2, dbOp1, dbRet));
		return null;
	}

}
