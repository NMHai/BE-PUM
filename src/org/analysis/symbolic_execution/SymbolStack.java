package org.analysis.symbolic_execution;

import org.analysis.formula.LongValueOld;
import org.analysis.formula.Value;

public class SymbolStack {
	private static final int capacity = 50;
	Value arr[] = new Value[capacity];
	int top = -1;

	public SymbolStack clone() {
		return null;
	}

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

	public Value pop() {
		Value result = null;
		if (top >= 0) {
			result = arr[top];
			arr[top] = new LongValueOld(0);
			top--;
			// System.out.println("Pop operation done !");
		} else {
			// System.out.println("Stack Underflow !");
			long returnValue = (long) (Math.random() * Math.pow(2, 31));
			result = new LongValueOld(returnValue);
		}
		return result;
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
	public Value get(long desp) {
		// TODO Auto-generated method stub
		if (this.top < desp / 4)
			return new LongValueOld((long) (Math.random() * Math.pow(2, 31)));
		return arr[(int) (this.top - desp / 4)];
	}

	public int length() {
		// TODO Auto-generated method stub
		return top;
	}

}
