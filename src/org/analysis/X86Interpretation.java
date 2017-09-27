package org.analysis;

import org.jakstab.asm.*;
import org.jakstab.asm.x86.*;
import org.jakstab.util.Logger;

//
// Operand type supported: {
// X86Registers (registers8[], registers16[], registers32[])
// X86MemoryOperand
// Immediate
// X86AbsoluteAddress
// X86PCRelativeAddress }
// 
// Operand type not supported: {
// X86XMMRegisters
// X86FloatRegisters
// X86ControlRegisters } 
//
/**
 * Symbolic execution of X86 instructions. Overwrite all available instruction
 * 
 * @author Binh Ngo
 * 
 */
public class X86Interpretation {
	public static final int FAILED = -1;
	public static final int SUCCESS = 0;
	public static final int MISSING_MEMORY = 1;
	public static final int MISSING_STACK = 2;
	private static int halt_status = SUCCESS;
	private final static Logger logger = Logger.getLogger(X86Interpretation.class);

	private static long startAddress = 0;
	private static SymbolicValue returnAddress = null;
	private static MemoryOperand memoryAddress = null;
	private static SymbolFinder symFinder = null;

	/**
	 * set address of instruction to be interpreted
	 * 
	 * @param addr
	 *            instruction address
	 */
	public static void setStartAddress(AbsoluteAddress addr) {
		startAddress = addr.getValue();
		memoryAddress = null;
		returnAddress = null;
		halt_status = SUCCESS;
		symFinder = SymbolicExecution.program.getModule(addr).getSymbolFinder();
	}

	/**
	 * 
	 * @return address of interpreted instruction
	 */
	public static long getStartAddress() {
		return startAddress;
	}

	/**
	 * 
	 * @return instruction finishing status
	 */
	public static int getReturnStatus() {
		return halt_status;
	}

	/**
	 * @return memory location (preserved for missing memory status)
	 */
	public static MemoryOperand getMemoryLocation() {
		MemoryOperand res = memoryAddress;
		memoryAddress = null;
		return res;
	}

	/**
	 * symbolic interprete arithmetic instructions
	 * 
	 * @param inst
	 *            arithmetic instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86ArithmeticInterprete(X86ArithmeticInstruction inst, SymbolicState prevState) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		SymbolicValue result = null;
		// System.out.println("Instruction: " + inst.getName());
		// second operand
		if (src != null) {
			if (src.getClass().getSimpleName().equals("Immediate")) {
				result = new SymbolicValue(((Immediate) src).getNumber().intValue());
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				result = prevState.getMemoryValue((X86MemoryOperand) src);
				if (result == null) {
					halt_status = MISSING_MEMORY;
					memoryAddress = (X86MemoryOperand) src;
					SymbolicExecution.programTrace.setVisited(startAddress, null,
							inst.toString(startAddress, symFinder));
					return null;
				}
				result = result.clone();
			} else if (src.getClass().getSimpleName().equals("X86Register")) {
				result = prevState.getRegValue(((X86Register) src).toString()).clone();
			}
		}

		// first operand
		SymbolicValue destResult = null;
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			destResult = prevState.getMemoryValue((X86MemoryOperand) dest);
			if (destResult == null) {
				halt_status = MISSING_MEMORY;
				memoryAddress = (X86MemoryOperand) dest;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			destResult = prevState.getRegValue(((X86Register) dest).toString());
		}

		SymbolicValue newDest = new SymbolicValue(destResult);
		switch (inst.getOperation()) {
		case ADD:
			// Statement supported: add, inc
			if (inst.getName().startsWith("add")) {
				newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else if (inst.getName().startsWith("inc")) {
				result = new SymbolicValue(1);
				newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else {
				logger.error("Instruction not supported: " + inst.getName());
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			break;
		case AND:
			// Statement supported: and
			if (inst.getName().startsWith("and")) {
				newDest.addExprValue(SymbolicValue.AND_EXPR, result);
			} else {
				logger.error("Instruction not supported: " + inst.getName());
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			break;
		case SUB:
			/* Statement supported: sub, dec */
			if (inst.getName().startsWith("sub")) {
				newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			} else if (inst.getName().startsWith("dec")) {
				result = new SymbolicValue(1);
				newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			} else {
				logger.error("Instruction not supported: " + inst.getName());
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			break;
		case OR:
			// Statement supported: or
			if (inst.getName().startsWith("or")) {
				newDest.addExprValue(SymbolicValue.OR_EXPR, result);
			} else {
				logger.error("Instruction not supported: " + inst.getName());
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			break;
		default:
			logger.error("Arithmetic statement not supported: " + inst.getName());
			halt_status = FAILED;
			SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
			return null;
		}

		// update destination information
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			prevState.getMemoryValues().put((X86MemoryOperand) dest, newDest);
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			prevState.setRegValue(((X86Register) dest).toString(), newDest);
		}

		returnAddress = new SymbolicValue(startAddress + inst.getSize());
		SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
				inst.toString(startAddress, symFinder));
		// store state for next address
		SymbolicExecution.preservedState = prevState;
		// reset compare status
		prevState.setCompareStatus(null);
		return returnAddress;
	}

	/**
	 * symbolic interprete call instructions
	 * 
	 * @param inst
	 *            call instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86CallInterprete(X86CallInstruction inst, SymbolicState prevState) {
		// statement supported: call, lcall + AbsoluteAddress/ RelativeAddress
		Operand dest = inst.getOperand1();
		// System.out.println("Instruction: " + inst.getName());

		// call structure: push call next eip to stack, jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			returnAddress = new SymbolicValue(((AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			returnAddress = new SymbolicValue(((X86PCRelativeAddress) dest).getEffectiveValue(startAddress));
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			returnAddress = (prevState.getRegValue(((X86Register) dest).toString())).clone();
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			returnAddress = (prevState.getMemoryValue((X86MemoryOperand) dest));
			if (returnAddress == null) {
				halt_status = MISSING_MEMORY;
				memoryAddress = (X86MemoryOperand) dest;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			returnAddress = returnAddress.clone();
		}
		// push next eip to stack
		SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
				inst.toString(startAddress, symFinder));
		prevState.getStack().push(new SymbolicValue(startAddress + inst.getSize()));
		// store state for next address
		SymbolicExecution.preservedState = prevState;
		// reset compare status
		prevState.setCompareStatus(null);
		return returnAddress;
	}

	/**
	 * symbolic interprete conditional jump instructions
	 * 
	 * @param inst
	 *            conditional jump instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86ConditionalJumpInterprete(X86CondJmpInstruction inst, SymbolicState prevState) {
		// conditional jump
		Operand dest = inst.getOperand1();
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			returnAddress = new SymbolicValue(((X86AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			returnAddress = new SymbolicValue(((X86PCRelativeAddress) dest).getEffectiveValue(startAddress));
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			returnAddress = prevState.getRegValue(((X86Register) dest).toString()).clone();
		}

		// System.out.println("Instruction: " + inst.getName());

		// structure: create new work associated with condition-false case
		// jump to condition-true case
		// add state condition into each trace
		SymbolicCondition compareStatus = prevState.getCompareStatus();
		// we accept loop not to be constrained by compareStatus
		if (!inst.getName().equals("loop")) {
			if (compareStatus == null) {
				logger.error("Only accept conditional statement after CMP");
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
		}
		SymbolicCondition positiveCond = new SymbolicCondition();
		SymbolicCondition negativeCond = new SymbolicCondition();
		int positiveConnector, negativeConnector;

		if (inst.getName().equals("ja") || inst.getName().equals("jg")) {
			positiveConnector = SymbolicCondition.B_OP_GREATER;
			negativeConnector = SymbolicCondition.B_OP_NOT_GREATER;
		} else if (inst.getName().equals("jae") || inst.getName().equals("jge")) {
			positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
			negativeConnector = SymbolicCondition.B_OP_LESS;
		} else if (inst.getName().equals("jb") || inst.getName().equals("jl")) {
			positiveConnector = SymbolicCondition.B_OP_LESS;
			negativeConnector = SymbolicCondition.B_OP_NOT_LESS;
		} else if (inst.getName().equals("jbe") || inst.getName().equals("jle")) {
			positiveConnector = SymbolicCondition.B_OP_NOT_GREATER;
			negativeConnector = SymbolicCondition.B_OP_GREATER;
		} else if (inst.getName().equals("je")) {
			positiveConnector = SymbolicCondition.B_OP_EQUAL;
			negativeConnector = SymbolicCondition.B_OP_NOT_EQUAL;
		} else if (inst.getName().equals("jne")) {
			positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
			negativeConnector = SymbolicCondition.B_OP_EQUAL;
		} else if (inst.getName().equals("jnl")) {
			positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
			negativeConnector = SymbolicCondition.B_OP_LESS;
		} else if (inst.getName().equals("loop")) {
			// decrease ecx, check if it is equal to 0
			SymbolicValue ecx = prevState.getRegValue("%ecx");
			SymbolicValue newDest = new SymbolicValue(ecx);
			newDest.addExprValue(SymbolicValue.SUB_EXPR, new SymbolicValue(1));
			prevState.setRegValue("%ecx", newDest);
			// set condition
			compareStatus = new SymbolicCondition();
			compareStatus.addCondition(prevState.getRegValue("%ecx").clone(), new SymbolicValue(0),
					SymbolicCondition.B_OP_UNDEFINED);
			positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
			negativeConnector = SymbolicCondition.B_OP_EQUAL;
		} else {
			logger.error("Unsupported instruction: " + inst.getName());
			halt_status = FAILED;
			SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
			return null;
		}

		positiveCond.addCondition(compareStatus, positiveConnector);
		negativeCond.addCondition(compareStatus, negativeConnector);

		if (SymbolicExecution.runningMode == SymbolicExecution.NORMAL_EXECUTION
				|| SymbolicExecution.runningMode == SymbolicExecution.INVARIANT_POPULATING_EXECUTION) {
			TraceTracker pgmTrace = SymbolicExecution.cloneTraceMap();
			pgmTrace.setVisited(startAddress, new SymbolicValue(startAddress + inst.getSize()),
					inst.toString(startAddress, symFinder));

			SymbolicValue negativeAddr = new SymbolicValue(startAddress + inst.getSize());

			SymbolicState clonedState = prevState.clone();
			clonedState.setCompareStatus(null);

			SymbolicWorkList.addWork(negativeAddr, negativeCond, SymbolicExecution.cloneStateMap(), pgmTrace,
					clonedState);

			prevState.addCondition(positiveCond);
			// store state for next address
			SymbolicExecution.preservedState = prevState;
			// reset compare status
			SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
					inst.toString(startAddress, symFinder));
			prevState.setCompareStatus(null);
		} else {
			// invariant finding mode, trace follows previous trace
			long nextAddress = SymbolicExecution.loopTrace.getLocationInfo(startAddress).getNextAddress()
					.calculateExprIntVal().longValue();
			// positive trace
			Long positiveNextAddr = returnAddress.calculateExprIntVal();

			if (positiveNextAddr == null || nextAddress != positiveNextAddr.longValue()) {
				// follow negative path
				prevState.addCondition(negativeCond);
			} else {
				prevState.addCondition(positiveCond);
			}
			prevState.setCompareStatus(null);
			// store state for next address
			SymbolicExecution.preservedState = prevState;
		}
		return returnAddress;
	}

	/**
	 * symbolic interprete jump instructions
	 * 
	 * @param inst
	 *            jump instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86JumpInterprete(X86JmpInstruction inst, SymbolicState prevState) {
		// statement supported: jump, ljump + AbsoluteAddress/ RelativeAddress
		Operand dest = inst.getOperand1();
		// System.out.println("Instruction: " + inst.getName());

		// jump structure: jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			returnAddress = new SymbolicValue(((AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			returnAddress = new SymbolicValue(((X86PCRelativeAddress) dest).getEffectiveValue(startAddress));
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			returnAddress = (prevState.getRegValue(((X86Register) dest).toString())).clone();
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			returnAddress = prevState.getMemoryValue((X86MemoryOperand) dest);
			if (returnAddress == null) {
				halt_status = MISSING_MEMORY;
				memoryAddress = (X86MemoryOperand) dest;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			returnAddress = returnAddress.clone();
		}
		// store state for next address
		SymbolicExecution.preservedState = prevState;
		SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
				inst.toString(startAddress, symFinder));
		// reset compare status
		prevState.setCompareStatus(null);
		return returnAddress;
	}

	/**
	 * symbolic interprete move instructions
	 * 
	 * @param inst
	 *            move instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86MoveInterprete(X86MoveInstruction inst, SymbolicState prevState) {
		// conditional move, normal move
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		// System.out.println("Instruction: " + inst.getName());
		SymbolicValue toMoveVal = null;

		if (src.getClass().getSimpleName().equals("Immediate")) {
			toMoveVal = new SymbolicValue(((Immediate) src).getNumber().intValue());
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			toMoveVal = prevState.getMemoryValue((X86MemoryOperand) src);
			if (toMoveVal == null) {
				halt_status = MISSING_MEMORY;
				memoryAddress = (X86MemoryOperand) src;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}
			toMoveVal = toMoveVal.clone();
		} else if (src.getClass().getSimpleName().equals("X86Register")) {
			toMoveVal = prevState.getRegValue(((X86Register) src).toString()).clone();
		}

		if (inst.getName().startsWith("mov")) {
			// normal move
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				prevState.getMemoryValues().put((MemoryOperand) dest, toMoveVal);
			} else if (dest.getClass().getSimpleName().equals("X86Register")) {
				prevState.setRegValue(((X86Register) dest).toString(), toMoveVal);
			}
		} else {
			// conditional move
			// structure: create new work associated with condition-false case
			// move in condition-true case
			// remain the same in condition-false case

			SymbolicCondition compareStatus = prevState.getCompareStatus();
			if (compareStatus == null) {
				logger.error("Only accept conditional statement after CMP");
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}

			// ignore signs
			SymbolicCondition positiveCond = new SymbolicCondition();
			SymbolicCondition negativeCond = new SymbolicCondition();
			int positiveConnector, negativeConnector;

			if (inst.getName().equals("cmova") || inst.getName().equals("cmovg")) {
				positiveConnector = SymbolicCondition.B_OP_GREATER;
				negativeConnector = SymbolicCondition.B_OP_NOT_GREATER;
			} else if (inst.getName().equals("cmovae") || inst.getName().equals("cmovge")) {
				positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
				negativeConnector = SymbolicCondition.B_OP_LESS;
			} else if (inst.getName().equals("cmovb") || inst.getName().equals("cmovl")) {
				positiveConnector = SymbolicCondition.B_OP_LESS;
				negativeConnector = SymbolicCondition.B_OP_NOT_LESS;
			} else if (inst.getName().equals("cmovbe") || inst.getName().equals("cmovle")) {
				positiveConnector = SymbolicCondition.B_OP_NOT_GREATER;
				negativeConnector = SymbolicCondition.B_OP_GREATER;
			} else if (inst.getName().equals("cmove")) {
				positiveConnector = SymbolicCondition.B_OP_EQUAL;
				negativeConnector = SymbolicCondition.B_OP_NOT_EQUAL;
			} else if (inst.getName().equals("cmovne")) {
				positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
				negativeConnector = SymbolicCondition.B_OP_EQUAL;
			} else if (inst.getName().equals("cmovnl")) {
				positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
				negativeConnector = SymbolicCondition.B_OP_LESS;
			} else {
				logger.error("Unsupported instruction: " + inst.getName());
				halt_status = FAILED;
				SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
				return null;
			}

			positiveCond.addCondition(compareStatus, positiveConnector);
			negativeCond.addCondition(compareStatus, negativeConnector);

			if (SymbolicExecution.runningMode == SymbolicExecution.NORMAL_EXECUTION) {
				TraceTracker pgmTrace = SymbolicExecution.cloneTraceMap();
				pgmTrace.setVisited(startAddress, new SymbolicValue(startAddress + inst.getSize()),
						inst.toString(startAddress, symFinder));

				SymbolicValue negativeAddr = new SymbolicValue(startAddress + inst.getSize());
				SymbolicState clonedState = prevState.clone();
				clonedState.setCompareStatus(null);

				SymbolicWorkList.addWork(negativeAddr, negativeCond, SymbolicExecution.cloneStateMap(), pgmTrace,
						clonedState);

				prevState.addCondition(positiveCond);
				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					prevState.getMemoryValues().put((MemoryOperand) dest, toMoveVal);
				} else if (dest.getClass().getSimpleName().equals("X86Register")) {
					prevState.setRegValue(((X86Register) dest).toString(), toMoveVal);
				}
			} else {
				// invariant finding mode, trace follows previous trace
				long nextAddress = SymbolicExecution.loopTrace.getLocationInfo(startAddress).getNextAddress()
						.calculateExprIntVal().longValue();
				// positive trace
				Long positiveNextAddr = returnAddress.calculateExprIntVal();

				if (positiveNextAddr == null || nextAddress != positiveNextAddr.longValue()) {
					// follow negative path
					prevState.addCondition(negativeCond);
				} else {
					prevState.addCondition(positiveCond);
				}
				prevState.setCompareStatus(null);
				// store state for next address
				SymbolicExecution.preservedState = prevState;
				return SymbolicExecution.loopTrace.getLocationInfo(startAddress).getNextAddress();
			}
		}
		returnAddress = new SymbolicValue(startAddress + inst.getSize());
		SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
				inst.toString(startAddress, symFinder));
		// store state for next address
		SymbolicExecution.preservedState = prevState;
		// reset compare status
		prevState.setCompareStatus(null);
		return returnAddress;
	}

	/**
	 * symbolic interprete return instructions
	 * 
	 * @param inst
	 *            return instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86ReturnInterprete(X86RetInstruction inst, SymbolicState prevState) {
		// statement supported: iret, lret, ret + <added arguments (ignore)>
		// System.out.println("Instruction: " + inst.getName());
		// return structure: pop next value from stack, jump to it
		// store state for next address
		if (prevState.getStack().isEmpty()) {
			halt_status = MISSING_STACK;
			SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
			return null;
		}
		SymbolicExecution.preservedState = prevState;
		returnAddress = prevState.getStack().pop();
		SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
				inst.toString(startAddress, symFinder));
		// reset compare status
		prevState.setCompareStatus(null);
		return returnAddress;
	}

	/**
	 * symbolic interprete general instructions
	 * 
	 * @param inst
	 *            general instruction
	 * @param prevState
	 *            previous state
	 * @return symbolic value of next address
	 */
	public static SymbolicValue X86InstructionInterprete(X86Instruction inst, SymbolicState prevState) {
		// general instruction
		// supported instruction: cmp, pop, push, nop
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		// System.out.println("Instruction: " + inst.getName());

		if (inst.getName().startsWith("cmp") || inst.getName().startsWith("test")) {
			SymbolicValue lhs = null, rhs = null;
			if (src.getClass().getSimpleName().equals("Immediate")) {
				rhs = new SymbolicValue(((Immediate) src).getNumber().intValue());
			} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				rhs = prevState.getMemoryValue((X86MemoryOperand) src);
				if (rhs == null) {
					halt_status = MISSING_MEMORY;
					memoryAddress = (X86MemoryOperand) src;
					SymbolicExecution.programTrace.setVisited(startAddress, null,
							inst.toString(startAddress, symFinder));
					return null;
				}
				rhs = rhs.clone();
			} else if (src.getClass().getSimpleName().equals("X86Register")) {
				rhs = prevState.getRegValue(((X86Register) src).toString()).clone();
			}

			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				lhs = prevState.getMemoryValue((X86MemoryOperand) dest);
				if (lhs == null) {
					halt_status = MISSING_MEMORY;
					memoryAddress = (X86MemoryOperand) dest;
					SymbolicExecution.programTrace.setVisited(startAddress, null,
							inst.toString(startAddress, symFinder));
					return null;
				}
				lhs = lhs.clone();
			} else if (dest.getClass().getSimpleName().equals("X86Register")) {
				lhs = prevState.getRegValue(((X86Register) dest).toString()).clone();
			}
			// set compare status
			SymbolicCondition compareCond = new SymbolicCondition();
			compareCond.addCondition(lhs, rhs, SymbolicCondition.B_OP_UNDEFINED);
			prevState.setCompareStatus(compareCond);
		} else if (inst.getName().startsWith("pop")) {
			if (dest.getClass().getSimpleName().equals("X86Register")) {
				String regName = ((X86Register) dest).toString();
				if (prevState.getStack().isEmpty()) {
					halt_status = MISSING_STACK;
					SymbolicExecution.programTrace.setVisited(startAddress, null,
							inst.toString(startAddress, symFinder));
					return null;
				}
				prevState.setRegValue(regName, prevState.getStack().pop());
			} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand memoryAddress = (X86MemoryOperand) dest;
				if (prevState.getStack().isEmpty()) {
					halt_status = MISSING_STACK;
					SymbolicExecution.programTrace.setVisited(startAddress, null,
							inst.toString(startAddress, symFinder));
					return null;
				}
				prevState.getMemoryValues().put(memoryAddress, prevState.getStack().pop());
				// reset compare status
				prevState.setCompareStatus(null);
			}
		} else if (inst.getName().startsWith("push")) {
			if (dest.getClass().getSimpleName().equals("X86Register")) {
				String regName = ((X86Register) dest).toString();
				prevState.getStack().push(prevState.getRegValue(regName));
			} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand memoryAddress = (X86MemoryOperand) dest;
				SymbolicValue memoryVal = prevState.getMemoryValue(memoryAddress);
				if (memoryVal == null) {
					halt_status = MISSING_MEMORY;
					memoryAddress = (X86MemoryOperand) dest;
					SymbolicExecution.programTrace.setVisited(startAddress, null,
							inst.toString(startAddress, symFinder));
					return null;
				}
				prevState.getStack().push(memoryVal);
				// reset compare status
				prevState.setCompareStatus(null);
			}
		} else if (inst.getName().startsWith("nop")) {
			// do nothing
		} else {
			logger.error("General statement not supported: " + inst.getName());
			halt_status = FAILED;
			SymbolicExecution.programTrace.setVisited(startAddress, null, inst.toString(startAddress, symFinder));
			return null;
		}
		returnAddress = new SymbolicValue(startAddress + inst.getSize());
		SymbolicExecution.programTrace.setVisited(startAddress, returnAddress.clone(),
				inst.toString(startAddress, symFinder));
		// store state for next address
		SymbolicExecution.preservedState = prevState;
		return returnAddress;
	}
}
