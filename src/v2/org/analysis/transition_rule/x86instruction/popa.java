package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class popa extends X86InstructionStub {

	@Override
	public BPState execute() {
		// System.out.println("Process " + inst.getName());
		Value edi = env.getStack().pop();
		if (edi == null) {
			edi = new SymbolValue("edi");
		}

		Value esi = env.getStack().pop();
		if (esi == null) {
			esi = new SymbolValue("esi");
		}

		Value ebp = env.getStack().pop();
		if (ebp == null) {
			ebp = new SymbolValue("ebp");
		}

		Value esp = env.getStack().pop();
		if (esp == null) {
			esp = new SymbolValue("esp");
		}

		Value ebx = env.getStack().pop();
		if (ebx == null) {
			ebx = new SymbolValue("ebx");
		}

		Value edx = env.getStack().pop();
		if (edx == null) {
			edx = new SymbolValue("edx");
		}

		Value ecx = env.getStack().pop();
		if (ecx == null) {
			ecx = new SymbolValue("ecx");
		}

		Value eax = env.getStack().pop();
		if (eax == null) {
			eax = new SymbolValue("eax");
		}

		env.getRegister().setRegisterValue("edi", edi);
		env.getRegister().setRegisterValue("esi", esi);
		env.getRegister().setRegisterValue("ebp", ebp);
		// env.getRegister().setRegisterValue("esp", esp);
		env.getRegister().setRegisterValue("ebx", ebx);
		env.getRegister().setRegisterValue("edx", edx);
		env.getRegister().setRegisterValue("ecx", ecx);
		env.getRegister().setRegisterValue("eax", eax);

		Program.getProgram()
				.getLog()
				.debugString(
						"Restore value of Register: EAX=" + eax.toString() + " EBX=" + ebx.toString() + " ECX="
								+ ecx.toString() + " EDX=" + edx.toString() + " ESI=" + esi.toString() + " EDI="
								+ edi.toString() + " ESP=" + esp.toString() + " EBP=" + ebp.toString());
		// Program.getProgram().generageCFG(
		// Program.getProgram().getAbsolutePathFile() + "_test");
		return null;
	}

}
