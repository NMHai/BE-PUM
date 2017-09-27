package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cmovne extends X86InstructionStub {

	public cmovne() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BPState execute() {
		boolean isSet_CMOVcc = false; // Xet dieu kien cua CMOVcc

		if (inst.getName().startsWith("cmovne") || inst.getName().equals("cmovnz")) {

			if (env.getFlag().getZFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}
		// dieu kien ZF = 1 or CF = 1
		else if (inst.getName().startsWith("cmovbe") || inst.getName().equals("cmovna")) {

			if (env.getFlag().getCFlag().equal(new BooleanValue(1))
					|| env.getFlag().getZFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien ZF = 0 and CF = 0
		else if (inst.getName().startsWith("cmova") || inst.getName().equals("cmovnbe")) {

			if (env.getFlag().getCFlag().equal(new BooleanValue(0))
					&& env.getFlag().getZFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: OF = 0;
		else if (inst.getName().startsWith("cmovno")) {

			if (env.getFlag().getOFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF = 0;
		else if (inst.getName().startsWith("cmovns")) {

			if (env.getFlag().getSFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: PF = 0;
		else if (inst.getName().startsWith("cmovnp") || inst.getName().equals("cmovpo")) {

			if (env.getFlag().getPFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: ZF = 1;
		else if (inst.getName().startsWith("cmove") || inst.getName().equals("cmovz")) {

			if (env.getFlag().getZFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: CF = 1;
		else if (inst.getName().startsWith("cmovb") || inst.getName().equals("cmovc")
				|| inst.getName().equals("cmovnae")) {

			if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}
		// dieu kien: PF = 1;
		else if (inst.getName().startsWith("cmovp") || inst.getName().equals("cmovpe")) {

			if (env.getFlag().getPFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF = OF;
		else if (inst.getName().startsWith("cmovge") || inst.getName().equals("cmovnl")) {

			boolean SF, OF;

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if (SF == OF) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: ZF = 0 and SF = OF;
		else if (inst.getName().startsWith("cmovg") || inst.getName().equals("cmovnle")) {

			boolean ZF, SF, OF;

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());
			ZF = ((BooleanValue) zFlag).getValue();

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if ((ZF == false) && (SF == OF)) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: ZF = 1 or SF != OF;
		else if (inst.getName().startsWith("cmovle") || inst.getName().equals("cmovng")) {

			boolean ZF, SF, OF;

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());
			ZF = ((BooleanValue) zFlag).getValue();

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if ((ZF == true) || (SF != OF)) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF != OF;
		else if (inst.getName().startsWith("cmovl") || inst.getName().equals("cmovnge")) {

			boolean SF, OF;

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if (SF != OF) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: OF = 1;
		else if (inst.getName().startsWith("cmovo")) {

			if (env.getFlag().getOFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF = 1;
		else if (inst.getName().startsWith("cmovs")) {

			if (env.getFlag().getSFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		if (isSet_CMOVcc == true) {
			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// X86MemoryOperand t = env.getMemory().evaluateAddress(
				// (X86MemoryOperand) src, env);

				if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
					// SEH Exploit
					// System.out.println("SEH:"
					// + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}

				s = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			}

			long temp_s = ((LongValue) s).getValue();
			env.getRegister().setRegisterValue(dest.toString(), new LongValue(temp_s));
			isSet_CMOVcc = false;
		}

		return null;
	}

}
