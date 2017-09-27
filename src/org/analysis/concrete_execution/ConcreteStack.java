package org.analysis.concrete_execution;

public class ConcreteStack {
	private static final int capacity = 50;
	long arr[] = new long[capacity];
	int top = -1;

	public void push(long pushedElement) {
		if (top < capacity - 1) {
			top++;
			arr[top] = pushedElement;
			// System.out.println("Element " + pushedElement +
			// " is pushed to Stack !");
			// printElements();
		} else {
			// System.out.println("Stack Overflow !")
			;
		}
	}

	public long pop() {
		long result = 0;
		if (top >= 0) {
			result = arr[top];
			arr[top] = 0;
			top--;
			// System.out.println("Pop operation done !");
		} else {
			// System.out.println("Stack Underflow !");
			result = (long) (Math.random() * Math.pow(2, 31));
		}
		return result;
	}

	public void printElements() {
		if (top >= 0) {
			System.out.println("Elements in stack :");
			for (int i = 0; i <= top; i++) {
				System.out.println(arr[i]);
			}
		}
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return top == -1;
	}

	// Truy cap cac thanh phan trong stack [esp + desp]
	public long get(long desp) {
		// TODO Auto-generated method stub
		if (this.top < desp / 4)
			return (long) (Math.random() * Math.pow(2, 31));
		return arr[(int) (this.top - desp / 4)];
	}

}
