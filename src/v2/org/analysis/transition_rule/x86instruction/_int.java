package v2.org.analysis.transition_rule.x86instruction;

import java.util.Calendar;

import org.jakstab.asm.Immediate;

import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;

public class _int extends X86InstructionStub {

	@Override
	public BPState execute() {
		if (dest != null && dest.getClass().getSimpleName().equals("Immediate")) {
			long x = Convert.convetUnsignedValue(((Immediate) dest).getNumber().intValue(), rule.getBitCount(inst));

			// Process interrupt 68h
			if (x == 104) {
				SEHHandle sehHandle = env.getSystem().getSEHHandler();
				sehHandle.setSEHType(SEHHandle.INTERUPT);
				return rule.processSEH(curState);
			}
			// Process interrupt 80h
			else if (x == 80) {

			} else
			// Process interrupt 21h
			if (x == 33) {
				// Check if AH = 2A
				// Return: CX = year (1980-2099) DH = month DL = day AL =
				// day of week (00h=Sunday)
				if (env.getRegister().getRegisterValue("%ah").equal(new LongValue(42))) {
					env.getRegister().setRegisterValue("%cx", new LongValue(Calendar.getInstance().get(Calendar.YEAR)));
					// int z = Calendar.getInstance().get(Calendar.MONTH);
					env.getRegister().setRegisterValue("%dh",
							new LongValue(Calendar.getInstance().get(Calendar.MONTH) + 1));
					env.getRegister().setRegisterValue("%dl", new LongValue(Calendar.getInstance().get(Calendar.DATE)));
					env.getRegister().setRegisterValue("%al",
							new LongValue(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
				}
			}

		}

		return null;
	}

}
