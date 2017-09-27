package v2.org.analysis.transition_rule;

import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class X86InstructionInterpreter {
	public BPState execute(X86Instruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		Operand dest = inst.getOperand1();
//		Operation src = inst.getOperand2();
		Environment env = curState.getEnvironement();

		// PHONG - 20150921 - Check SEH before going ///////////////
		AbsoluteAddress curAddr = curState.getLocation();
		if (rule.getSEHHandle().causeException(curAddr)) {
			SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
			sehHandle.setSEHType(SEHHandle.SINGLE_STEP);
			return rule.processSEH(curState);
		}
		// /////////////////////////////////////////////////////////

		// Bien dung de xu ly CMOVcc
//		long temp_s;
//		Value d = null, s = null;
//		int opSize = rule.getBitCount(inst);
		// System.out.println("Instruction: " + inst.getName());

		/*****************
		 ***** Khanh*******
		 *****************/

		if (inst.getName().startsWith("seta")) {

			// PHONG: fix here
			if ((env.getFlag().getZFlag().equal(new BooleanValue(0)) && (env.getFlag().getCFlag()
					.equal(new BooleanValue(0))))) {
				env.getRegister().setRegisterValue(dest.toString(), new LongValue(1));
			} else // bo sung truong hop khong thoa man
			{

			}
		}

		/*****************
		 ***** Khanh*******
		 *****************/

		else if (inst.getName().startsWith("setb")) {

			Value CF = env.getFlag().getCFlag();
			if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (CF instanceof BooleanValue && ((BooleanValue) CF).getValue()) {
					env.getRegister().setRegisterValue(dest.toString(), new LongValue(1));

				} else {
					env.getRegister().setRegisterValue(dest.toString(), new LongValue(0));
					int a = 0;
					a++;
				}
			}

		}

		/*** CBW ***/
		// AX = AL (xet dau)
		else if (inst.getName().startsWith("cbw")) {
			Value AL = env.getRegister().getRegisterValue("al");
			long temp_AL = ((LongValue) AL).getValue();
			long temp = 0;

			temp = new AnalysisBit().MOVS(16, temp_AL);
			env.getRegister().setRegisterValue("ax", new LongValue(temp));
		}

		/*** CWDE ***/
		// EAX = AX (xet dau)
		else if (inst.getName().startsWith("cwde")) {
			Value AX = env.getRegister().getRegisterValue("ax");
			long temp_AX = ((LongValue) AX).getValue();
			long temp = 0;

			temp = new AnalysisBit().MOVS(32, temp_AX);
			env.getRegister().setRegisterValue("eax", new LongValue(temp));
		} else if (inst.getName().startsWith("salc")){
			Value cf = env.getFlag().getCFlag();
			if (cf instanceof BooleanValue) {
				boolean cfVal = ((BooleanValue)cf).getValue();
				long al = (cfVal? 0xff: 0);
				env.getRegister().setRegisterValue("al", new LongValue(al));
			} 
		}
		else {
			Program.getProgram().getLog()
					.error("Instruction Interpreter not supported " + inst.getName() + " at " + curState.getLocation());
		}

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}

}
