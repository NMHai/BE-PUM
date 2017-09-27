/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.transition_rule.x86instruction
 * File name: cmpxchg8b.java
 * Created date: Oct 12, 2015
 * Description:
 */
package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author Yen Nguyen
 *
 */
public class cmpxchg8b extends X86InstructionStub {

	/* (non-Javadoc)
	 * @see v2.org.analysis.transition_rule.stub.AssemblyInstructionStub#execute()
	 */
	@Override
	public BPState execute() {
		Value EAX = env.getRegister().getRegisterValue("eax");
		Value EBX = env.getRegister().getRegisterValue("ebx");
		Value ECX = env.getRegister().getRegisterValue("ecx");
		Value EDX = env.getRegister().getRegisterValue("edx");

		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t =
			// env.getMemory().evaluateAddress((X86MemoryOperand) dest,
			// env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}

		if (d instanceof LongValue && EAX instanceof LongValue && EDX instanceof LongValue) {
			long temp_EAX = ((LongValue) EAX).getValue();
			long temp_EDX = ((LongValue) EDX).getValue();

			long temp_d = 0;
			long temp_1 = 0;
			long temp_2 = 0;
			long EDX_EAX = 0;
			long ECX_EBX = 0;

			temp_d = ((LongValue) d).getValue();
			EDX_EAX = new AnalysisBit().Bit64toDec(temp_EAX, temp_EDX);

			if (temp_d == EDX_EAX) {
				env.getFlag().setZFlag(new BooleanValue(true));
				long temp_EBX = ((LongValue) EBX).getValue();
				long temp_ECX = ((LongValue) ECX).getValue();
				ECX_EBX = new AnalysisBit().Bit64toDec(temp_EBX, temp_ECX);
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(ECX_EBX), inst);
			} else {
				env.getFlag().setZFlag(new BooleanValue(false));
				temp_1 = new AnalysisBit().Bit0FromIm(temp_d);
				temp_2 = new AnalysisBit().Bit32FromIm(temp_d);
				env.getRegister().setRegisterValue("eax", new LongValue(temp_1));
				env.getRegister().setRegisterValue("edx", new LongValue(temp_2));
			}
		} else {
			Program.getProgram().setLog("cmpxchg8b with Symbol Value at " + curState.getLocation());
			rule.generateNextInstruction(inst, path, pathList, true);
			return curState;
		}
		
		return null;
	}

}
