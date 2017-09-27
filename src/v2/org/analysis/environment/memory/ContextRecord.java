package v2.org.analysis.environment.memory;

import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class ContextRecord {
	// CONTEXT RECORD pointer
	private long context_record_ptr;
	// CONTEXT FLAG
	private static long context_flag = 0x1007f; // ESP + 0
	// DEBUG REGISTER
	private long[] debug_registers; // ESP + 4
	// FLOATING POINT
	private long ControlWord; // ESP + 1C
	private long StatusWord; // ESP + 20
	private long TagWord; // ESP + 24
	private long ErrorOffset; // ESP + 28
	private long ErrorSelector; // ESP + 2C
	private long DataOffset; // ESP + 30
	private long DataSelector; // ESP + 34
	private long[] FPRegisters; // ESP + 38
	private long Cr0NpxState; // ESP + 88
	// SEGMENT REGISTER
	private long gs_register; // ESP + 8C
	private long fs_register; // ESP + 90
	private long es_register; // ESP + 94
	private long ds_register; // ESP + 98
	// ORDINARY REGISTERS
	private long edi_register; // ESP + 9C
	private long esi_register; // ESP + A0
	private long ebx_register; // ESP + A4
	private long edx_register; // ESP + A8
	private long ecx_register; // ESP + AC
	private long eax_register; // ESP + B0
	// CONTROL REGISTERS
	private long ebp_register; // ESP + B4
	private long eip_register; // ESP + B8
	private long cs_register; // ESP + BC
	private long efl_register; // ESP + C0
	private long esp_register; // ESP + C4
	private long ss_register; // ESP + C8

	public ContextRecord() {
	}

	public void setContextRecord(BPState curState) {
		long[] dr = { 0, 0, 0, 0, 0, 0 };
		this.debug_registers = dr;
		this.ControlWord = 0x27f;
		this.StatusWord = 0;
		this.TagWord = 0xffff;
		this.ErrorOffset = 0;
		this.ErrorSelector = 0;
		this.DataOffset = 0;
		this.DataSelector = 0;
		long[] fpr = new long[20];
		for (int i = 0; i < 20; i++) {
			fpr[i] = 0;
		}
		this.FPRegisters = fpr;
		this.Cr0NpxState = 0xa6e7160;
		this.gs_register = 0x2b;
		this.fs_register = 0x53;
		this.es_register = 0x2b;
		this.ds_register = 0x2b;
		Value edi = curState.getEnvironement().getRegister().getRegisterValue("edi");
		if (edi instanceof LongValue) {
			this.edi_register = ((LongValue) edi).getValue();
		} else {
			this.edi_register = 0;
		}
		
		//this.esi_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("esi")).getValue();
		Value esi = curState.getEnvironement().getRegister().getRegisterValue("esi");
		if (esi instanceof LongValue) {
			this.esi_register = ((LongValue) esi).getValue();
		} else {
			this.esi_register = 0;
		}
		
		//this.ebx_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("ebx")).getValue();
		Value ebx = curState.getEnvironement().getRegister().getRegisterValue("ebx");
		if (ebx instanceof LongValue) {
			this.ebx_register = ((LongValue) ebx).getValue();
		} else {
			this.ebx_register = 0;
		}
		
		//this.edx_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("edx")).getValue();
		Value edx = curState.getEnvironement().getRegister().getRegisterValue("edx");
		if (edx instanceof LongValue) {
			this.edx_register = ((LongValue) edx).getValue();
		} else {
			this.edx_register = 0;
		}
		
		//this.ecx_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("ecx")).getValue();
		Value ecx = curState.getEnvironement().getRegister().getRegisterValue("ecx");
		if (ecx instanceof LongValue) {
			this.ecx_register = ((LongValue) ecx).getValue();
		} else {
			this.ecx_register = 0;
		}
		
		//this.eax_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("eax")).getValue();
		Value eax = curState.getEnvironement().getRegister().getRegisterValue("eax");
		if (eax instanceof LongValue) {
			this.eax_register = ((LongValue) eax).getValue();
		} else {
			this.eax_register = 0;
		}
		
		//this.ebp_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("ebp")).getValue();
		Value ebp = curState.getEnvironement().getRegister().getRegisterValue("ebp");
		if (ebp instanceof LongValue) {
			this.ebp_register = ((LongValue) ebp).getValue();
		} else {
			this.ebp_register = 0;
		}
		
		this.eip_register = curState.getLocation().getValue();
		/*Value eip = curState.getEnvironement().getRegister().getRegisterValue("eip");
		if (eip instanceof LongValue)
			this.eip_register = ((LongValue) eip).getValue();
		else 
			this.eip_register = 0;*/
		
		this.cs_register = 0x23;		
		this.efl_register = curState.getEnvironement().getFlag().getEFlags();
		//this.esp_register = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("esp")).getValue();
		Value esp = curState.getEnvironement().getRegister().getRegisterValue("esp");
		if (esp instanceof LongValue) {
			this.esp_register = ((LongValue) esp).getValue();
		} else {
			this.esp_register = 0;
		}
		this.ss_register = 0x2b;
	}

	public void toStack(BPState curState) {
		curState.getEnvironement().getStack().push(new LongValue(this.ss_register));
		curState.getEnvironement().getStack().push(new LongValue(this.esp_register));
		curState.getEnvironement().getStack().push(new LongValue(this.efl_register));
		curState.getEnvironement().getStack().push(new LongValue(this.cs_register));
		curState.getEnvironement().getStack().push(new LongValue(this.eip_register));
		curState.getEnvironement().getStack().push(new LongValue(this.ebp_register));
		curState.getEnvironement().getStack().push(new LongValue(this.eax_register));
		curState.getEnvironement().getStack().push(new LongValue(this.ecx_register));
		curState.getEnvironement().getStack().push(new LongValue(this.edx_register));
		curState.getEnvironement().getStack().push(new LongValue(this.ebx_register));
		curState.getEnvironement().getStack().push(new LongValue(this.esi_register));
		curState.getEnvironement().getStack().push(new LongValue(this.edi_register));
		curState.getEnvironement().getStack().push(new LongValue(this.ds_register));
		curState.getEnvironement().getStack().push(new LongValue(this.es_register));
		curState.getEnvironement().getStack().push(new LongValue(this.fs_register));
		curState.getEnvironement().getStack().push(new LongValue(this.gs_register));
		curState.getEnvironement().getStack().push(new LongValue(this.Cr0NpxState));
		for (int i = 0; i < 20; i++) {
			curState.getEnvironement().getStack().push(new LongValue(this.FPRegisters[i]));
		}
		curState.getEnvironement().getStack().push(new LongValue(this.DataSelector));
		curState.getEnvironement().getStack().push(new LongValue(this.DataOffset));
		curState.getEnvironement().getStack().push(new LongValue(this.ErrorSelector));
		curState.getEnvironement().getStack().push(new LongValue(this.ErrorOffset));
		curState.getEnvironement().getStack().push(new LongValue(this.TagWord));
		curState.getEnvironement().getStack().push(new LongValue(this.StatusWord));
		curState.getEnvironement().getStack().push(new LongValue(this.ControlWord));
		for (int i = 0; i < 6; i++) {
			curState.getEnvironement().getStack().push(new LongValue(this.debug_registers[i]));
		}
		curState.getEnvironement().getStack().push(new LongValue(context_flag));
		// Set context record pointer
		Value esp = curState.getEnvironement().getRegister().getRegisterValue("esp");
		if (esp != null && esp instanceof LongValue) {
			this.setContext_record_ptr(((LongValue) esp)
					.getValue());
		}
	}

	public long getContext_record_ptr() {
		return context_record_ptr;
	}

	public void setContext_record_ptr(long context_record_ptr) {
		this.context_record_ptr = context_record_ptr;
	}
}
