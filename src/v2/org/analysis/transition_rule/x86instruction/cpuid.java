package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.InfoCPU;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cpuid extends X86InstructionStub {

	public cpuid() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BPState execute() {
		Value EAX = env.getRegister().getRegisterValue("eax");
		long temp_eax = ((LongValue) EAX).getValue();
		InfoCPU infoCPU = new InfoCPU();
		infoCPU.CPUID(temp_eax);
		long ebx = infoCPU.getEBX();
		long ecx = infoCPU.getECX();
		long edx = infoCPU.getEDX();

		env.getRegister().setRegisterValue("ebx", new LongValue(ebx));
		env.getRegister().setRegisterValue("ecx", new LongValue(ecx));
		env.getRegister().setRegisterValue("edx", new LongValue(edx));
		return null;
	}

}
