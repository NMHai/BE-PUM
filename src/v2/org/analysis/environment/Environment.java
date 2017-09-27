package v2.org.analysis.environment;

import org.jakstab.Program;

import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.environment.processthread.PEB;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.environment.stack.StackV1;
import v2.org.analysis.environment.stack.StackV2;
import v2.org.analysis.system.SystemHandle;
import v2.org.analysis.value.LongValue;

public class Environment {
	private Stack stack;
	private Flag flag;
	private MemoryV2 memory;
	private Register register;
//	private MD5 md5;
	public final static SystemHandle system = new SystemHandle();

	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public MemoryV2 getMemory() {
		return memory;
	}

	public void setMemory(MemoryV2 memory) {
		this.memory = memory;
		this.memory.setEnvironment(this);
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	public Environment() {
		stack = new StackV2();

		flag = new Flag();
		flag.init();
		memory = new MemoryV2();
		memory.setEnvironment(this);
		memory.cloneProgramImage(Program.getProgram());
		// memory.resetImportTable(Program.getProgram());

		register = new Register();
//		register.mov("edx", new
//		LongValue(Program.getProgram().getEntryPoint()
//		.getValue()));
		register.mov("edx", new LongValue(0x7C90E4F4)); // 0x7C90E4F4
		register.mov("esi", new LongValue(0x0)); // 0xFFFFFFFF
		register.mov("ecx", new LongValue(0x0)); // 0x12FFB0
		register.mov("edi", new LongValue(0x0)); // 0x7C910208
		// zunc: set PEB address to ebx
		register.mov("ebx", new LongValue(PEB.getPEB_Base_Address())); // 0x7FFD6000
		register.mov("eax", new LongValue(system.getKernel().getBaseAddress())); // 0x0

		register.mov("cs", new LongValue(0x0));
		register.mov("ds", new LongValue(0x0));
		register.mov("es", new LongValue(0x0));
		register.mov("gs", new LongValue(0x0));
		register.mov("ss", new LongValue(0x0));
		register.mov("fs", new LongValue(0x7EFDD000));
		// register.mov("esp", new LongValue(((StackV2)stack).getTopAddress()));
		// register.mov("ebp", new
		// LongValue(((StackV2)stack).getBaseAddress()));

		if (stack instanceof StackV2) {
			((StackV2) stack).setEnvironment(this);
			// ((StackV2) stack).init(new LongValue(system.getKernel()
			// .getReturnRandomValue()));
			// ((StackV2) stack).init(new LongValue(0x7C817067));
			((StackV2) stack).init(new LongValue(0x7c8000c0));
		} else if (stack instanceof StackV1) {
			stack.push((new LongValue(system.getKernel().getReturnRandomValue())));
		}
	}

	public SystemHandle getSystem() {
		// TODO Auto-generated method stub
		return system;
	}

	@Override
	public Environment clone() {
		Environment ret = new Environment();
		if (register != null) {
			ret.setRegister(register.clone());
		}

		if (flag != null) {
			ret.setFlag(flag.clone());
		}

		if (memory != null) {
			ret.setMemory(memory.clone());
		}

		if (stack != null) {
			ret.setStack(stack.clone());
		}
		ret.getMemory().setEnvironment(ret);
		if (ret.getStack() instanceof StackV2) {
			((StackV2) ret.getStack()).setEnvironment(ret);
		}

		return ret;
	}

	public boolean equals(Environment e) {
		return register.equals(e.getRegister()) && memory.equals(e.getMemory()) && stack.equals(e.getStack())
		// && flag.equals(e.getFlag())
		;
	}

	public void reset() {
		// TODO Auto-generated method stub
		register.reset();
	}

	public String getHashString() {
		try {
			// Hash memory
//			MD5 md5 = new MD5();
//			String memoryStr = memory.getOrderedStringContent();
//			md5.Update(memoryStr, null);
//			String hash1 = md5.asHex();

//			stringBuilder.append(String.format("%d%d", ((StackV2)stack).getBaseAddress(), ((StackV2)stack).getTopAddress()));
			// Hash Flag + Register + Memory
//			StringBuilder stringBuilder = new StringBuilder();
//			stringBuilder.append(flag.toString());
//			stringBuilder.append(register.toString());
//			md5.Update(stringBuilder.toString(), null);
//			return md5.asHex() + memory.toHashString();
			return register.toHashString() + memory.toHashString();
//			+ flag.toHashString();
//			String hash2 = md5.asHex();
//
//			// Append 2 string of hashing
//			stringBuilder.setLength(0);
//			stringBuilder.append(hash1);
//			stringBuilder.append(hash2);
//
//			// Return the final hashing string
//			md5.Update(stringBuilder.toString(), null);
//			return md5.asHex();
//			long beginTime = System.currentTimeMillis();
//			md5.Update(register.toString(), null);
//			System.out.println("HASHING " + this + " takes " + (System.currentTimeMillis() - beginTime)); 
//			return md5.asHex();
//			return register.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public Environment cloneWithoutMem() {
		// TODO Auto-generated method stub
		Environment ret = new Environment();
		if (register != null) {
			ret.setRegister(register.clone());
		}

		if (flag != null) {
			ret.setFlag(flag.clone());
		}
		
		return ret;

	}
}
