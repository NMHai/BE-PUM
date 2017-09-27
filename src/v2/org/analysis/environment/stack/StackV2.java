/**
 * 
 */
package v2.org.analysis.environment.stack;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 * @author NMHai New stack with treating as Memory
 * 
 */
public class StackV2 extends Stack {
	private static long BASE_ADDR = 0x18D000;
	private static long SIZE = 0x4000;
	public static boolean isInsideStackSession(AbsoluteAddress addr) {
		long val = addr.getValue();
		return val >= BASE_ADDR && val < (BASE_ADDR + SIZE);
	}
	private long baseAddr, topAddr;
	private long scale = 0x23;

	private Environment env;

	@Override
	public void add(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.addFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	@Override
	public void and(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.andFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	@Override
	public Stack clone() {
		// TODO Auto-generated method stub
		StackV2 s = new StackV2();
		s.setBaseAddress(baseAddr);
		s.setTopAddress(topAddr);

		return s;
	}

	@Override
	public void div(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.unsignedDivFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	@Override
	public boolean equals(Stack s) {
		// TODO Auto-generated method stub
		if (s instanceof StackV2) {
			return baseAddr == ((StackV2) s).getBaseAddress() && topAddr == ((StackV2) s).getTopAddress();
		}
		return false;
	}

	/**
	 * @return the baseAddr
	 */
	public long getBaseAddress() {
		return baseAddr;
	}

	/**
	 * @return the env
	 */
	public Environment getEnvironment() {
		return env;
	}

	@Override
	public Value getIndex(int index) {
		// TODO Auto-generated method stub
		return getValueStackFromIndex(index);
	}

	public String getString() {
		String result = "";

		/*
		 * while (index >= topAddr) { result +=
		 * env.getMemory().getDoubleWordMemoryValue(new
		 * X86MemoryOperand(DataType.INT32, index)) + ","; index -= 4; }
		 */

		Value esp = env.getRegister().getRegisterValue("esp");
		Value ebp = env.getRegister().getRegisterValue("ebp");
		if (esp != null && esp instanceof LongValue) {
			long index = ((LongValue) esp).getValue();
			while (index <= baseAddr) {
				result += new AbsoluteAddress(index) + " : "
						+ env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index)) + "\n";
				index += 4;
			}

			if (ebp != null && ebp instanceof LongValue)
			 {
				result += "EBP: "
						+ env.getMemory().getDoubleWordMemoryValue(
								new X86MemoryOperand(DataType.INT32, ((LongValue) ebp).getValue()));
			// return result + " " + (topAddr == ((LongValue) esp).getValue());
			}
		}
		if (result == "") {
			return "Stack is undefined";
		}

		return result;
	}

	/**
	 * @return the topAddr
	 */
	public long getTopAddress() {
		return topAddr;
	}

	@Override
	public Value getValueStackFromIndex(long desp) {
		Value esp = env.getRegister().getRegisterValue("esp");
		if (esp instanceof LongValue) {
			return env.getMemory().getDoubleWordMemoryValue(desp + ((LongValue) esp).getValue());
		}

		return null;
	}

	@Override
	public Value getValueStackFromIndex(long desp, int num) {
		// TODO Auto-generated method stub
		Value esp = env.getRegister().getRegisterValue("esp");
		if (esp instanceof LongValue) {
			if (num == 32) {
				return env.getMemory().getDoubleWordMemoryValue(desp + ((LongValue) esp).getValue());
			}

			if (num == 8) {
				return env.getMemory().getByteMemoryValue(desp + ((LongValue) esp).getValue());
			}

			if (num == 16) {
				return env.getMemory().getWordMemoryValue(desp + ((LongValue) esp).getValue());
			}
		}

		return null;
	}

	public void init(LongValue value) {
		// TODO Auto-generated method stub
		baseAddr = Convert.hexToLong("18FF94"); // 12FFF0
		// baseAddr = Convert.hexToLong("18FF90");
		topAddr = Convert.hexToLong("18FF8C"); // 12FFD0
		// topAddr = Convert.hexToLong("18FF94");
		// oldBaseAddr = Convert.hexToLong("12FFD0");
		env.getMemory().createMemory(BASE_ADDR, SIZE, "Initialization of stack space");
		env.getRegister().mov("esp", new LongValue(getTopAddress()));
		env.getRegister().mov("ebp", new LongValue(getBaseAddress()));		

		// push(new LongValue((long) (Math.random() * Math.pow(10, 5))));
		//push(new LongValue(0x2));
		
		//// zunc: comment
		push(new LongValue((long) (Math.random() * Math.pow(10, 7))));
		push(new LongValue(0x7C910208));
		push(value);

		//// zumc: init JNA range for Stack
//		Value val = new LongValue(0);		
//		for (int pos = 0; pos < size; pos+= 4) {
//			long address = base + pos;
//			env.getMemory().setWordMemoryValue(address, val);
//		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return topAddr > baseAddr;
	}

	@Override
	public boolean isInsideStack(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		Value esp = env.getRegister().getRegisterValue("esp");
		if (esp != null && esp instanceof LongValue) {
			long t = ((LongValue) esp).getValue();
			return addr.getValue() > t - SIZE && addr.getValue() < t + SIZE;
		}

		return false;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return (int) ((topAddr - baseAddr) / 4);
	}

	@Override
	public void mul(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.unsignedMulFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	private Value normalizeValue(Value v, Instruction inst) {
		if (v instanceof LongValue) {
			long t = ((LongValue) v).getValue();
			return new LongValue(Convert.convetUnsignedValue(t, Convert.getBitCount(inst)));
		}

		return v;
	}

	@Override
	public void or(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.orFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	@Override
	public Value pop() {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			Value p = env.getMemory().getDoubleWordMemoryValue(topAddr);

			if (p != null) {
				topAddr += 4;
				env.getRegister().add("esp", new LongValue(4));

				return p;
			}
		}

		System.out.println("Cannot pop out of stack!!!");
//		return null;
		return new SymbolValue("stack");
	}

	@Override
	public void pop(long t) {
		// TODO Auto-generated method stub
		//for (int i = 0; i < t / 4; i++)
		//	pop();
		topAddr += t;
		//env.getRegister().add("esp", new LongValue(4));
		env.getRegister().add("esp", new LongValue(t));
	}

	@Override
	public Value pop16() {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			Value p = env.getMemory().getWordMemoryValue(topAddr);

			if (p != null) {
				topAddr += 2;
				env.getRegister().add("esp", new LongValue(2));

				return p;
			}
		}

		System.out.println("Cannot pop out of stack!!!");
		return null;
	}

	@Override
	public void push(Value pushedElement) {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			topAddr -= 4;

			env.getMemory().setDoubleWordMemoryValue(topAddr, pushedElement);
			env.getRegister().sub("esp", new LongValue(4));
		} else {
			System.out.println("Cannot pushed to stack!!!");
		}
	}

	@Override
	public void push16(Value pushedElement) {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			topAddr -= 2;

			env.getMemory().setWordMemoryValue(topAddr, pushedElement);
			env.getRegister().sub("esp", new LongValue(2));
		} else {
			System.out.println("Cannot pushed to stack!!!");
		}

	}

	@Override
	public void rl(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.rlFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	@Override
	public void rr(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.rrFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	/**
	 * @param baseAddr
	 *            the baseAddr to set
	 */
	public void setBaseAddress(long baseAddr) {
		this.baseAddr = baseAddr;
	}

	/**
	 * @param env
	 *            the env to set
	 */
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	/**
	 * @param topAddr
	 *            the topAddr to set
	 */
	public void setTopAddress(long topAddr) {
		this.topAddr = topAddr;
	}

	public void setValueStackFromIndex(long desp, Value v, int num) {
		// TODO Auto-generated method stub
		Value esp = env.getRegister().getRegisterValue("esp");
		if (esp instanceof LongValue) {
			if (num == 32) {
				env.getMemory().setDoubleWordMemoryValue(desp + ((LongValue) esp).getValue(), v);
			}

			if (num == 16) {
				env.getMemory().setWordMemoryValue(desp + ((LongValue) esp).getValue(), v);
			}

			if (num == 8) {
				env.getMemory().setByteMemoryValue(desp + ((LongValue) esp).getValue(), v);
			}
		}
	}
	
	@Override
	public void sub(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.subFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}

	@Override
	public String toString() {
		String result = "";
		// long index = baseAddr;
		/*
		 * while (index >= topAddr) { result +=
		 * env.getMemory().getDoubleWordMemoryValue(new
		 * X86MemoryOperand(DataType.INT32, index)) + ","; index -= 4; }
		 */

		Value esp = env.getRegister().getRegisterValue("esp");
		Value ebp = env.getRegister().getRegisterValue("ebp");
		if (esp != null && esp instanceof LongValue) {
			long index = ((LongValue) esp).getValue();
			for (int i = 0; i <= scale; i++) {
				result += new AbsoluteAddress(index) + " : "
						+ env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index)) + ", ";
				index += 4;
			}

			if (ebp != null && ebp instanceof LongValue)
			 {
				result += "EBP: "
						+ env.getMemory().getDoubleWordMemoryValue(
								new X86MemoryOperand(DataType.INT32, ((LongValue) ebp).getValue()));
			// return result + " " + (topAddr == ((LongValue) esp).getValue());
			}
		}

		if (result == "") {
			return "Stack is undefined";
		}

		return result;
	}

	@Override
	public void xor(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp, Convert.getBitCount(inst));
		r = r.xorFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r, Convert.getBitCount(inst));
	}
}
