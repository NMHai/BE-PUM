package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.Immediate;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class enter extends X86InstructionStub {

	@Override
	public BPState execute() {
		long ebp = 0;
		long esp = 0;
		long op1 = Convert.convetUnsignedValue(((Immediate) dest).getNumber().intValue(), 16);
		long op2 = Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), 8);
		long _size = op1 + 4 * op2;
		// xoa.
		if (env.getRegister().getRegisterValue("ebp") instanceof LongValue) {
			ebp = ((LongValue) env.getRegister().getRegisterValue("ebp")).getValue();
		}
		if (env.getRegister().getRegisterValue("esp") instanceof LongValue) {
			esp = ((LongValue) env.getRegister().getRegisterValue("esp")).getValue();
		}

		env.getStack().push(env.getRegister().getRegisterValue("ebp"));
		Value tempreg = env.getRegister().getRegisterValue("esp");
		if (op2 > 0) {
			Long botAddr;
			Value bot = env.getRegister().getRegisterValue("ebp");

			if (bot instanceof LongValue) {
				botAddr = ((LongValue) bot).getValue();
				for (int i = 1; i < op2; i++) {
					botAddr -= 4;
					Value p = env.getMemory().getDoubleWordMemoryValue(botAddr);
					env.getStack().push(p);
				}
			}// end if

			env.getStack().push(tempreg);

		}// end if(op2 >0 )
			// new base(test);
		Long oldEBP = (long) 0;
		oldEBP = ebp;

		env.getRegister().setRegisterValue("ebp", tempreg);
		Long esp_value = ((LongValue) tempreg).getValue() - _size;
		env.getRegister().setRegisterValue("esp", new LongValue(esp_value));
		// xoa.
		ebp = ((LongValue) env.getRegister().getRegisterValue("ebp")).getValue();
		esp = ((LongValue) env.getRegister().getRegisterValue("esp")).getValue();

		return null;
	}

}
