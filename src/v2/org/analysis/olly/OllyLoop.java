package v2.org.analysis.olly;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Flag;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV2;

public class OllyLoop {
	public long getLoopID() {
		return loopID;
	}

	public void setLoopID(long loop) {
		this.loopID = loop;
	}

	private long loopID;

	public AbsoluteAddress getAddress() {
		return address;
	}

	public void setAddress(AbsoluteAddress ins_address) {
		this.address = ins_address;
	}

	private AbsoluteAddress address;
	private OllyRegister regs;
	private OllyFlag flags;

	public OllyMemory getMems() {
		return mems;
	}

	private OllyMemory mems;

	public OllyMemory getStack() {
		return stack;
	}

	private OllyMemory stack;

	public OllyLoop(long loop, long ins_address, OllyRegister regs, OllyFlag flags, OllyMemory mems, OllyMemory stack) {
		this.loopID = loop;
		this.address = new AbsoluteAddress(ins_address);
		this.regs = regs;
		this.flags = flags;
		this.mems = mems;
		this.stack = stack;
	}

	/*
	 * public boolean compareRegister(Register bpreg){ return
	 * this.regs.compare(bpreg); }
	 */

	/*
	 * public boolean compareFlag(Flag bpflag){ return
	 * this.flags.compare(bpflag); }
	 * 
	 * public boolean compareMemory(Memory bpmem){ return
	 * this.mems.compare(bpmem); }
	 * 
	 * public boolean compareStack(Memory bpmem){ return
	 * this.stack.compare(bpmem); }
	 */

	public String compareBEPUM(Environment env, OllyLoop l, long memoryStartAddr, long memoryEndAddr, long stackIndex) {
		String ret = "";
		Register r = env.getRegister();
		Flag f = env.getFlag();
		MemoryV2 m = env.getMemory();
//		Stack s = env.getStack();
		String compare = compareBEPUMRegister(l, r);
		if (compare == "") {
			ret += "Register: Equal\n";
		} else {
			ret += "Register: Bug " + compare + "\n";
		}

		compare = compareBEPUMFlag(l, f);
		if (compare == "") {
			ret += "Flag: Equal\n";
		} else {
			ret += "Flag: Bug " + compare + "\n";
		}

		compare = compareBEPUMMemory(l, m, memoryStartAddr, memoryEndAddr);

		if (compare == "") {
			ret += "Memory: Equal\n";
		} else {
			ret += "Memory: Unequal " + compare + "\n";
		}

		long esp = regs.getRegisterValue("esp");
		compare = compareBEPUMStack(l, m, esp, stackIndex);
		if (compare == "") {
			ret += "Stack: Equal\n";
		} else {
			ret += "Stack: Unequal " + compare + "\n";
		}
		return ret;
	}

	private String compareBEPUMStack(OllyLoop l, MemoryV2 s, long esp, long stackIndex) {
		if (stack == null) {
			return "No Stack";
		}
		
		return stack.compareStack(l, s, esp, stackIndex);
	}

	private String compareBEPUMMemory(OllyLoop l, MemoryV2 m, long memoryStartAddr, long memoryEndAddr) {
		if (mems == null) {
			return "No Mem";
		}
		
		return mems.compareMemory(l, m, memoryStartAddr, memoryEndAddr);
	}

	private String compareBEPUMFlag(OllyLoop l, Flag f) {
		if (flags == null) {
			return "No Flag";
		}
		
		return flags.compare(f);
	}

	private String compareBEPUMRegister(OllyLoop l, Register r) {
		if (regs == null) {
			return "No Register";
		}
		return regs.compare(r);
	}
}
