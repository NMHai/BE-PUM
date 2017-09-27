package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.Immediate;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fsubp extends X86InstructionStub {

	@Override
	public BPState execute() {
	if (dest == null) {
			return curState;
		}

		d = env.getRegister().getRegisterValue(dest.toString());
		s = env.getRegister().getRegisterValue(src.toString());
		
		double dbOp1, dbOp2;
		dbOp1 = Double.longBitsToDouble(((LongValue) d).getValue());
		dbOp2 = Double.longBitsToDouble(((LongValue) s).getValue());
		double dbRet = dbOp2 - dbOp1;
		LongValue ret = new LongValue(Double.doubleToLongBits(dbRet));
		System.out.println(String.format(" -> %.2f - %.2f = %.2f", dbOp2, dbOp1, dbRet));
		env.getRegister().setRegisterValue(dest.toString(), ret);
		env.getRegister().popFPU();
		return null;
	}

}
