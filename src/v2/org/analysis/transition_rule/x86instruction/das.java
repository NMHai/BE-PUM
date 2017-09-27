package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.HybridValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class das extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO: Yen Nguyen - DAS - Decimal Adjust for Subtraction
		Value firstCondition = new HybridValue(env.getRegister().getRegisterValue("al")
				.andFunction(new LongValue(0x0F)), ">", new LongValue(9));
		firstCondition = firstCondition.evaluate(firstCondition.getValueMap());

		Value secondCondition = env.getFlag().getAFlag();
		secondCondition = secondCondition.evaluate(secondCondition.getValueMap());

		if ((firstCondition instanceof BooleanValue && ((BooleanValue) firstCondition).getValue() == true)
				|| (secondCondition instanceof BooleanValue && ((BooleanValue) secondCondition).getValue() == true)) {
			env.getRegister().setRegisterValue("al",
					env.getRegister().getRegisterValue("al").subFunction(new LongValue(6)));

			// CF = CF OR BorrowFromLastSubtraction; (* CF OR borrow from AL
			// = AL - 6 *)
			Value CFlagCondition = new HybridValue(env.getRegister().getRegisterValue("al"), "<", new LongValue(6));
			CFlagCondition = CFlagCondition.evaluate(CFlagCondition.getValueMap());
			if (CFlagCondition instanceof BooleanValue && ((BooleanValue) CFlagCondition).getValue() == true) {
				env.getFlag().setCFlag(new BooleanValue(true));
			}

			env.getFlag().setAFlag(new BooleanValue(true));
		} else {
			env.getFlag().setAFlag(new BooleanValue(false));
		}

		firstCondition = new HybridValue(env.getRegister().getRegisterValue("al"), ">", new LongValue(0x9F));
		firstCondition = firstCondition.evaluate(firstCondition.getValueMap());

		secondCondition = env.getFlag().getCFlag();
		secondCondition = secondCondition.evaluate(secondCondition.getValueMap());

		if ((firstCondition instanceof BooleanValue && ((BooleanValue) firstCondition).getValue() == true)
				|| (secondCondition instanceof BooleanValue && ((BooleanValue) secondCondition).getValue() == true)) {
			env.getRegister().setRegisterValue("al",
					env.getRegister().getRegisterValue("al").subFunction(new LongValue(0x60)));
			env.getFlag().setCFlag(new BooleanValue(true));
		} else {
			env.getFlag().setCFlag(new BooleanValue(false));
		}
		return null;
	}

}
