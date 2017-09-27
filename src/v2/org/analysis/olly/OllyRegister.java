package v2.org.analysis.olly;

import v2.org.analysis.environment.Register;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class OllyRegister {

	private static int EIP = 0;
	private static int EAX = 1;
	private static int ECX = 2;
	private static int EDX = 3;
	private static int EBX = 4;
	private static int ESP = 5;
	private static int EBP = 6;
	private static int ESI = 7;
	private static int EDI = 8;

	private long eip, eax, ecx, edx, ebx, esp, ebp, esi, edi;

	public OllyRegister(long[] regs) {
		this.eip = regs[EIP];
		this.eax = regs[EAX];
		this.ecx = regs[ECX];
		this.edx = regs[EDX];
		this.ebx = regs[EBX];
		this.esp = regs[ESP];
		this.ebp = regs[EBP];
		this.esi = regs[ESI];
		this.edi = regs[EDI];
	}

	/*
	 * public boolean compare(Register regs){ long eip_value =
	 * ((LongValue)regs.getRegisterValue("eip")).getValue(); long eax_value =
	 * ((LongValue)regs.getRegisterValue("eax")).getValue(); long ecx_value =
	 * ((LongValue)regs.getRegisterValue("ecx")).getValue(); long edx_value =
	 * ((LongValue)regs.getRegisterValue("edx")).getValue(); long ebx_value =
	 * ((LongValue)regs.getRegisterValue("ebx")).getValue(); long esp_value =
	 * ((LongValue)regs.getRegisterValue("esp")).getValue(); long ebp_value =
	 * ((LongValue)regs.getRegisterValue("ebp")).getValue(); long esi_value =
	 * ((LongValue)regs.getRegisterValue("esi")).getValue(); long edi_value =
	 * ((LongValue)regs.getRegisterValue("edi")).getValue(); return (eip_value
	 * == this.eip) && (eax_value == this.eax) && (ecx_value == this.ecx) &&
	 * (edx_value == this.edx) && (ebx_value == this.ebx) && (esp_value ==
	 * this.esp) && (ebp_value == this.ebp) && (esi_value == this.esi) &&
	 * (edi_value == this.edi); }
	 */

	public String compare(Register regs) {
		// long eip_value =
		// ((LongValue)regs.getRegisterValue("eip")).getValue();
		String ret = "";
		Value temp = regs.getRegisterValue("eax");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.eax)
			ret += "EAX:" + temp + " vs " + Long.toHexString(this.eax) + "; ";
		temp = regs.getRegisterValue("ebx");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.ebx)
			ret += "EBX:" + temp + " vs " + Long.toHexString(this.ebx) + "; ";
		temp = regs.getRegisterValue("ecx");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.ecx)
			ret += "ECX:" + temp + " vs " + Long.toHexString(this.ecx) + "; ";
		temp = regs.getRegisterValue("edx");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.edx)
			ret += "EDX:" + temp + " vs " + Long.toHexString(this.edx) + "; ";

		temp = regs.getRegisterValue("esp");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.esp)
			ret += "ESP:" + temp + " vs " + Long.toHexString(this.esp) + "; ";
		temp = regs.getRegisterValue("ebp");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.ebp)
			ret += "EBP:" + temp + " vs " + Long.toHexString(this.ebp) + "; ";

		temp = regs.getRegisterValue("esi");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.esi)
			ret += "ESI:" + temp + " vs " + Long.toHexString(this.esi) + "; ";
		temp = regs.getRegisterValue("edi");
		if (temp == null || !(temp instanceof LongValue) || ((LongValue) temp).getValue() != this.edi)
			ret += "EDI:" + temp + " vs " + Long.toHexString(this.edi) + "; ";

		return ret;
	}

	public long getRegisterValue(String esp) {
		return this.esp;
	}
}
