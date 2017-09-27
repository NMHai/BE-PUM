/**
 * 
 */
package v2.org.analysis.environment.stack;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.value.Value;

/**
 * @author MinhHai
 * 
 */
public abstract class Stack {

	public abstract Stack clone();

	public abstract void push(Value pushedElement);

	public abstract boolean equals(Stack s);

	public abstract Value pop();

	// Truy cap cac thanh phan trong stack [esp + desp]
	public abstract Value getValueStackFromIndex(long desp, int num);

	public abstract Value getValueStackFromIndex(long desp);

	public abstract Value getIndex(int index);

	public abstract int length();

	public abstract void sub(long desp, Value v, Instruction inst);

	public abstract void add(long desp, Value v, Instruction inst);

	public abstract void mul(long desp, Value v, Instruction inst);

	public abstract void xor(long desp, Value v, Instruction inst);

	public abstract void or(long desp, Value v, Instruction inst);

	public abstract void and(long desp, Value v, Instruction inst);

	public abstract void div(long desp, Value v, Instruction inst);

	public abstract void rr(long desp, Value v, Instruction inst);

	public abstract void rl(long desp, Value v, Instruction inst);

	public abstract void pop(long t);

	public abstract boolean isInsideStack(AbsoluteAddress addr);

	public abstract boolean isEmpty();

	public abstract void push16(Value pushedElement);

	public abstract Value pop16();
}
