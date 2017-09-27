package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;

public class rdtsc extends X86InstructionStub {
	
	@Override
	public BPState execute() {
		// System.out.println("rdtsc");
		long time_stamp = System.nanoTime();
		AnalysisBit analysisBit = new AnalysisBit();
		long rdtsc_eax = analysisBit.RDTSC_EAX(time_stamp);
		long rdtsc_edx = analysisBit.RDTSC_EDX(time_stamp);
//		env.getRegister().setRegisterValue("eax", new LongValue(rdtsc_eax));
		env.getRegister().setRegisterValue("eax", new SymbolValue("eax_" + rdtsc_eax));
		env.getRegister().setRegisterValue("edx", new LongValue(rdtsc_edx));
		
		return null;
	}

}
