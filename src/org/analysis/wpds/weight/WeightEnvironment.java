/**
 * 
 */
package org.analysis.wpds.weight;

import org.analysis.symbolic_execution.*;
import org.jakstab.Program;
import v2.org.analysis.system.SystemHandle;

/**
 * @author NMHai
 * 
 */
public class WeightEnvironment {
	private SymbolValueRegister symbolValueRegister;
	private SymbolValueRegisterPart symbolValueRegisterPart;
	private SymbolValueMemoryOperand symbolValueMemoryOperand;
	private SymbolValueSegment symbolValueSegment;
	private SymbolFlag symbolFlag;
	private SymbolStack symbolStack;
	// StubProvider stubLibrary;
	// String funcName = "";
	private Program program;
	private SystemHandle system;

	public WeightEnvironment(SymbolValueRegister symbolValueRegister, SymbolValueRegisterPart symbolValueRegisterPart,
			SymbolValueMemoryOperand symbolValueMemoryOperand, SymbolValueSegment symbolValueSegment,
			SymbolFlag symbolFlag, SymbolStack symbolStack, Program program, SystemHandle system) {
		super();
		this.setRegister(symbolValueRegister);
		this.setSymbolValueRegisterPart(symbolValueRegisterPart);
		this.setSymbolValueMemoryOperand(symbolValueMemoryOperand);
		this.setSymbolValueSegment(symbolValueSegment);
		this.setSymbolFlag(symbolFlag);
		this.setSymbolStack(symbolStack);
		this.setProgram(program);
		this.setSystem(system);
	}

	public WeightEnvironment() {
		// TODO Auto-generated constructor stub
		super();
	}

	public SymbolValueRegister getRegister() {
		return symbolValueRegister;
	}

	public void setRegister(SymbolValueRegister symbolValueRegister) {
		this.symbolValueRegister = symbolValueRegister;
	}

	/**
	 * @return the symbolValueRegisterPart
	 */
	public SymbolValueRegisterPart getSymbolValueRegisterPart() {
		return symbolValueRegisterPart;
	}

	/**
	 * @param symbolValueRegisterPart
	 *            the symbolValueRegisterPart to set
	 */
	public void setSymbolValueRegisterPart(SymbolValueRegisterPart symbolValueRegisterPart) {
		this.symbolValueRegisterPart = symbolValueRegisterPart;
	}

	/**
	 * @return the symbolValueMemoryOperand
	 */
	public SymbolValueMemoryOperand getSymbolValueMemoryOperand() {
		return symbolValueMemoryOperand;
	}

	/**
	 * @param symbolValueMemoryOperand
	 *            the symbolValueMemoryOperand to set
	 */
	public void setSymbolValueMemoryOperand(SymbolValueMemoryOperand symbolValueMemoryOperand) {
		this.symbolValueMemoryOperand = symbolValueMemoryOperand;
	}

	/**
	 * @return the symbolValueSegment
	 */
	public SymbolValueSegment getSymbolValueSegment() {
		return symbolValueSegment;
	}

	/**
	 * @param symbolValueSegment
	 *            the symbolValueSegment to set
	 */
	public void setSymbolValueSegment(SymbolValueSegment symbolValueSegment) {
		this.symbolValueSegment = symbolValueSegment;
	}

	/**
	 * @return the symbolFlag
	 */
	public SymbolFlag getSymbolFlag() {
		return symbolFlag;
	}

	/**
	 * @param symbolFlag
	 *            the symbolFlag to set
	 */
	public void setSymbolFlag(SymbolFlag symbolFlag) {
		this.symbolFlag = symbolFlag;
	}

	/**
	 * @return the symbolStack
	 */
	public SymbolStack getSymbolStack() {
		return symbolStack;
	}

	/**
	 * @param symbolStack
	 *            the symbolStack to set
	 */
	public void setSymbolStack(SymbolStack symbolStack) {
		this.symbolStack = symbolStack;
	}

	/**
	 * @return the program
	 */
	public Program getProgram() {
		return program;
	}

	/**
	 * @param program
	 *            the program to set
	 */
	public void setProgram(Program program) {
		this.program = program;
	}

	/**
	 * @return the system
	 */
	public SystemHandle getSystem() {
		return system;
	}

	/**
	 * @param system
	 *            the system to set
	 */
	public void setSystem(SystemHandle system) {
		this.system = system;
	}

	public void printInfo() {
		// TODO Auto-generated method stub
		if (symbolValueRegister.length() > 0) {
			System.out.print("[");
			symbolValueRegister.printInfo();
			System.out.println("]");
		}

		if (symbolStack.length() >= 0) {
			System.out.print("Stack:");
			symbolStack.printElements();
		}

		if (symbolValueMemoryOperand.length() > 0) {
			System.out.print("Memory Operand:");
			symbolValueMemoryOperand.printInfo();
		}
		// symbolValueSegment.printInfo();
	}

}
