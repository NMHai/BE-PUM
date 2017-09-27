/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.environment.stack;

import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 *
 * @author zunc
 */
public class FPUStack {
	
	private static final int NSTACK = 8;
	private Value st[] = new Value[NSTACK];
	private int bottom = 0, top = 0;
	
	private boolean validateIndex(int idx) {
		return idx >= 0 && idx < NSTACK;
	}
	
	public Value get(int idx) {
		if (!validateIndex(idx)) {
			return null;
		}
		return st[idx];
	}
	
	public boolean set(int idx, Value val) {
		if (!validateIndex(idx)) {
			return false;
		}
		st[idx] = val;
		return true;
	}
	
	public Value getByName(String stName) {
		String strIdx = stName.substring(stName.length() - 1, stName.length());
		int idx = Integer.parseInt(strIdx);
		return get(idx);
	}
	
	public boolean setByName(String stName, Value val) {
		String strIdx = stName.substring(stName.length() - 1, stName.length());
		int idx = Integer.parseInt(strIdx);
		return set(idx, val);
	}
	
	public void push(Value val) {
		set(top, val);
		top = ++top % NSTACK;
	}
	
	public Value pop() {
		top = --top < bottom ? bottom : top;
		return get(top);
	}
	
	public void reset() {
		for (int i = 0; i < NSTACK; i++) {
			set(i, new LongValue(0));
		}
		bottom = top = 0;
	}
	
	public Value getSt() {
		if (top == bottom) {
			System.out.println("FPUStack.getSt: logic failed");
			return null;
		}
		return get(top - 1);
	}
	
	public void setSt(Value val) {
		if (top == bottom) {
			System.out.println("FPUStack.setSt: logic failed");
			return;
		}
		set(top - 1, val);
	}
	
	public static void main(String[] args) {
		System.out.println("FPUStack - Test");
		FPUStack st = new FPUStack();
		
		///--- test 1
		System.out.println("--- Test 1");
		// set
		for (int i = 0; i < NSTACK; i++) {
			String name = "st" + i;
			LongValue val = new LongValue(i);
			st.setByName(name, val);
		}
		
		// get
		for (int i = 0; i < NSTACK; i++) {
			String name = "st" + i;
			LongValue val = (LongValue) st.getByName(name);
			System.out.println(String.format("[%d]=%s", i, val));
		}
		
		///--- test 2
		System.out.println("--- Test 2");
		st.reset();
		for (int i = 0; i < NSTACK - 1; i++) {
			LongValue val = new LongValue(i);
			st.push(val);
		}
		
		// get
		for (int i = 0; i < NSTACK - 1; i++) {
			LongValue val = (LongValue) st.pop();
			System.out.println(String.format("[%d]=%s", i, val));
		}
		System.out.println("DONE");
	}
	
}
