/**
 * 
 */
package v2.org.analysis.environment.stack;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author NMHai
 * 
 */
public class StackV1 extends Stack {
	private static final int capacity = 50;
	Value arr[] = new Value[capacity];
	int top = -1;

	@Override
	public StackV1 clone() {
		StackV1 ret = new StackV1();
		int l = length();
		for (int i = 0; i < l; i++) {
			if (arr[i] != null)
				ret.push(arr[i].clone());
		}

		return ret;
	}

	@Override
	public void push(Value pushedElement) {
		if (top < capacity - 1) {
			top++;
			arr[top] = pushedElement;
			// System.out.println("Element " + pushedElement.getName() +
			// " is pushed to Stack !");
			// printElements();
		} else {
			// System.out.println("Stack Overflow !")
			;
		}
	}

	@Override
	public boolean equals(Stack s) {
		if (s.length() != length())
			return false;

		for (int i = 0; i < length(); i++)
			if (!arr[i].equal(s.getIndex(i)))
				return false;

		return true;
	}

	@Override
	public Value pop() {
		Value result = null;
		if (top >= 0) {
			result = arr[top];
			arr[top] = new LongValue(0);
			top--;
			// System.out.println("Pop operation done !");
		} else {
			// System.out.println("Stack Underflow !");
			long returnValue = (long) (Math.random() * Math.pow(2, 31));
			result = new LongValue(returnValue);
		}
		return result;
	}

	@Override
	public Value getValueStackFromIndex(long desp, int num) {
		return null;
	}

	public void printElements() {
		if (top >= 0) {
			System.out.println("Elements in stack :");
			for (int i = 0; i <= top; i++) {
				System.out.println(arr[i].getName());
			}
		}
	}

	// Truy cap cac thanh phan trong stack [esp + desp]
	public Value getValueStackFromIndex(long desp) {
		// TODO Auto-generated method stub
		if (this.top < desp / 4)
			return new LongValue((long) (Math.random() * Math.pow(2, 31)));
		return arr[(int) (this.top - desp / 4)];
	}

	@Override
	public Value getIndex(int index) {
		if (index < length())
			return arr[index];
		return null;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return top + 1;
	}

	@Override
	public void sub(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.subFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void add(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.addFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void mul(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.unsignedMulFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void xor(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.xorFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void or(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.orFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void and(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.andFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void div(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.unsignedDivFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void rr(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.rrFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	@Override
	public void rl(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = null;
		if (this.top < desp / 4)
			// return new LongValue((long) (Math.random() * Math.pow(2, 31)));
			return;

		r = arr[(int) (this.top - desp / 4)];
		r = r.rlFunction(v);

		r = normalizeValue(r, inst);
		arr[(int) (this.top - desp / 4)] = r;
	}

	private Value normalizeValue(Value v, Instruction inst) {
		if (v instanceof LongValue) {
			long t = ((LongValue) v).getValue();
			return new LongValue(Convert.convetUnsignedValue(t, Convert.getBitCount(inst)));
		}

		return v;
	}

	@Override
	public void pop(long t) {
		// TODO Auto-generated method stub
		for (int i = 0; i < t / 4; i++)
			pop();
	}

	@Override
	public boolean isInsideStack(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void push16(Value pushedElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public Value pop16() {
		// TODO Auto-generated method stub
		return null;
	}
}
