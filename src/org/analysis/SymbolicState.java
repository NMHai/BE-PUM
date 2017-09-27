package org.analysis;

import org.jakstab.asm.MemoryOperand;
import org.jakstab.util.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Symbolic properties of a program state
 * 
 * @author Binh Ngo
 * 
 */
public class SymbolicState {

	private final static Logger logger = Logger.getLogger(SymbolicState.class);

	private ArrayList<String> generalPurposeRegs;
	private ArrayList<SymbolicValue> registerValues;
	// preserved for CMP, TEST
	private SymbolicCondition compareStatus;
	private SymbolicCondition stateCondition;
	// stack
	private Stack<SymbolicValue> symbolicStack;
	// memory location values
	private TreeMap<MemoryOperand, SymbolicValue> memoryValues;

	/**
	 * Create a state that store values for general purpose registers and flags
	 */
	public SymbolicState() {
		generalPurposeRegs = new ArrayList<String>();
		registerValues = new ArrayList<SymbolicValue>();
		// adding general purpose registers to state monitoring elements
		generalPurposeRegs.add("%eax");
		generalPurposeRegs.add("%ebx");
		generalPurposeRegs.add("%ecx");
		generalPurposeRegs.add("%edx");
		// Since registers are uninitialized,
		// there's no different with their values
		for (int i = 0; i < generalPurposeRegs.size(); i++) {
			registerValues.add(new SymbolicValue());
		}
		stateCondition = new SymbolicCondition();
		compareStatus = null;
		symbolicStack = new Stack<SymbolicValue>();
		memoryValues = new TreeMap<MemoryOperand, SymbolicValue>();
	}

	/**
	 * Set register value of a state
	 * 
	 * @param register
	 *            register name
	 * @param value
	 *            register value
	 */
	public void setRegValue(String register, SymbolicValue value) {
		int index = generalPurposeRegs.indexOf(register);
		if (index >= 0 && index < registerValues.size()) {
			registerValues.set(index, value);
		} else {
			logger.error("Error setting values for register " + register);
		}
	}

	/**
	 * Get register value
	 * 
	 * @param register
	 *            register name
	 * @return register value
	 */
	public SymbolicValue getRegValue(String register) {
		int index = generalPurposeRegs.indexOf(register);
		if (index >= 0) {
			return registerValues.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Get symbolic run-time stack
	 * 
	 * @return stack
	 */
	public Stack<SymbolicValue> getStack() {
		return symbolicStack;
	}

	/**
	 * Get register list
	 * 
	 * @return register list
	 */
	public ArrayList<String> getRegisterList() {
		return generalPurposeRegs;
	}

	/**
	 * Get memory values set
	 * 
	 * @return memory value set
	 */
	public TreeMap<MemoryOperand, SymbolicValue> getMemoryValues() {
		return memoryValues;
	}

	/**
	 * Get memory value
	 * 
	 * @return memory location
	 */
	public SymbolicValue getMemoryValue(MemoryOperand addr) {
		return memoryValues.get(addr);
	}

	/**
	 * Add memory location's value
	 * 
	 * @param location
	 *            absolute address
	 * @param value
	 *            memory value
	 */
	public void addMemoryValue(MemoryOperand location, SymbolicValue value) {
		/*
		 * If the map previously contained a mapping for the key, the old value
		 * is replaced by the specified value
		 */
		memoryValues.put(location, value);
	}

	/**
	 * Get condition
	 * 
	 * @return state condition
	 */
	public SymbolicCondition getCondition() {
		return stateCondition;
	}

	/**
	 * Set condition
	 * 
	 * @param cond
	 *            state condition
	 */
	public void setCondition(SymbolicCondition cond) {
		stateCondition = cond;
	}

	/**
	 * Add condition
	 * 
	 * @param cond
	 *            state condition
	 */
	public void addCondition(SymbolicCondition cond) {
		stateCondition.addCondition(cond);
	}

	/**
	 * Get compare value
	 * 
	 * @return state compare value
	 */
	public SymbolicCondition getCompareStatus() {
		return compareStatus;
	}

	/**
	 * Set compare condition
	 * 
	 * @param cond
	 *            state compare condition
	 */
	public void setCompareStatus(SymbolicCondition cond) {
		compareStatus = cond;
	}

	/**
	 * clone a new symbolic state with the same structure
	 */
	public SymbolicState clone() {
		SymbolicState res = new SymbolicState();

		for (int i = 0; i < generalPurposeRegs.size(); i++) {
			res.setRegValue(generalPurposeRegs.get(i), registerValues.get(i).clone());
		}
		res.setCondition(stateCondition.clone());
		for (int i = 0; i < symbolicStack.size(); i++) {
			res.getStack().add(symbolicStack.get(i));
		}
		if (compareStatus != null) {
			res.setCompareStatus(compareStatus.clone());
		}

		for (Map.Entry<MemoryOperand, SymbolicValue> entry : memoryValues.entrySet()) {
			res.addMemoryValue(entry.getKey(), entry.getValue().clone());
		}

		return res;
	}

	/**
	 * print symbolic state's information
	 */
	public void printInfo() {
		System.out.print("REGS:");
		for (int i = 0; i < generalPurposeRegs.size(); i++) {
			System.out.println("\t" + generalPurposeRegs.get(i).toUpperCase() + " : "
					+ registerValues.get(i).toString());
		}
		if (compareStatus != null) {
			System.out.println("COMPARARE: " + compareStatus.toString());
		}
		if (stateCondition.getConditionSet().size() > 0) {
			System.out.println("CONDITION: " + stateCondition.toString());
		}
		if (symbolicStack.size() > 0) {
			// Stack structure: bottom -> top
			System.out.print("STACK: |-> ");
			for (int i = 0; i < symbolicStack.size(); i++) {
				System.out.print(symbolicStack.get(i).toString());
				if (i < symbolicStack.size() - 1) {
					System.out.print(" -> ");
				}
			}
			System.out.println();
		}
		if (memoryValues.size() > 0) {
			System.out.print("---MEMORY: ");
			for (Map.Entry<MemoryOperand, SymbolicValue> entry : memoryValues.entrySet()) {
				System.out.print("<" + entry.getKey().toString() + " : " + entry.getValue().toString() + ">");
			}
			System.out.println();
		}
	}
}
