package org.analysis.loop;

import org.analysis.formula.Formula;
import org.analysis.formula.FormulaSet;
import org.analysis.formula.LongValueOld;
import org.analysis.formula.Value;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.*;
import v2.org.analysis.cfg.AddressList;

import java.util.Map;

public class SymbolicExecutionFarkas {
	private AbsoluteAddress target;
	private AddressList traceList;
	private Map<AbsoluteAddress, Instruction> assemblyMap;
	// long evaluateValue;
	// Map<AbsoluteAddress, AbsoluteAddress> neg;
	private FormulaSet formulas;

	// SymbolValue var;
	// SymbolVariable sv;

	public SymbolicExecutionFarkas(AddressList first, Map<AbsoluteAddress, Instruction> assemblyMap2) {
		// TODO Auto-generated constructor stub
		this.traceList = first;
		this.assemblyMap = assemblyMap2;
		this.formulas = new FormulaSet();
	}

	public boolean execute() {
		// this.printInfor();
		// AbsoluteAddress result = null;
		/* AddressList addrL = traceList.clone(); */
		while (true) {
			target = traceList.pop();
			if (target == null)
				// System.out.println("End of Symbolic Execution");
				// formulas.printInfo();
				break;

			Instruction ins = assemblyMap.get(target);
			/*
			 * for (int i = 0; i < ins.getOperandCount(); i++) if
			 * (ins.getOperand(i).getClass().getSimpleName()
			 * .equals("X86Register"))
			 * var.add(ins.getOperand(i).toString().substring(1), 0); else if
			 * (ins.getOperand(i).getClass().getSimpleName()
			 * .equals("X86MemoryOperand")) var.add("op_addr_" +
			 * ((X86MemoryOperand) ins.getOperand(i)).toString(), 0);
			 */

			if (ins instanceof X86ArithmeticInstruction)
				this.X86ArithmeticInterprete((X86ArithmeticInstruction) ins);
			else if (ins instanceof X86CallInstruction)
				this.X86CallInterprete((X86CallInstruction) ins);
			else if (ins instanceof X86CondJmpInstruction)
				this.X86ConditionalJumpInterprete((X86CondJmpInstruction) ins);
			else if (ins instanceof X86JmpInstruction)
				this.X86JumpInterprete((X86JmpInstruction) ins);
			else if (ins instanceof X86MoveInstruction)
				this.X86MoveInterprete((X86MoveInstruction) ins);
			else if (ins instanceof X86RetInstruction)
				this.X86ReturnInterprete((X86RetInstruction) ins);
			else if (ins instanceof X86Instruction)
				this.X86InstructionInterprete((X86Instruction) ins);
		}
		return true;
	}

	// add, inc, and, sub, or
	private void X86ArithmeticInterprete(X86ArithmeticInstruction ins) {

		switch (ins.getOperation()) {
		case ADD:
			// Statement supported: add, inc
			if (ins.getName().startsWith("add")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						/*
						 * sv.add(dest.toString().substring(1), src.toString()
						 * .substring(1));
						 */
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						/*
						 * sv.add(dest.toString().substring(1), ((Immediate)
						 * src) .getNumber().intValue());
						 */
					}
				}

				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else if (ins.getName().startsWith("inc")) {
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register"))
					/* sv.add(dest.toString().substring(1), 1) */;
				else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// System.out.println("Information of this X86MemoryOperand");
					// System.out.println(((X86MemoryOperand)dest).toString());
					// System.out.println(((X86MemoryOperand)dest).getDisplacement());
					// System.out.println(((X86MemoryOperand)dest).getScale());
					// System.out.println(((X86MemoryOperand)dest).);
					/*
					 * sv.add((X86MemoryOperand) dest, new OtherExp(new VarExp(
					 * "op_addr_" + ((X86MemoryOperand) dest).toString()), "+",
					 * new ConstantExp(1)))
					 */;
				}

				// result = new SymbolicValue(1);
				// newDest.addExprValue(SymbolicValue.ADD_EXPR, result);
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
			}
			break;
		case AND:
			// Statement supported: and
			if (ins.getName().startsWith("and")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						/*
						 * sv.and(dest.toString().substring(1), src.toString()
						 * .substring(1))
						 */;
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						/*
						 * sv.and(dest.toString().substring(1), ((Immediate)
						 * src) .getNumber().intValue())
						 */;
					}
				}

			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case SUB:
			/* Statement supported: sub, dec */
			if (ins.getName().startsWith("sub")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						/*
						 * sv.sub(dest.toString().substring(1), src.toString()
						 * .substring(1))
						 */;
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						/*
						 * sv.sub(dest.toString().substring(1), ((Immediate)
						 * src) .getNumber().intValue())
						 */;
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			} else if (ins.getName().startsWith("dec")) {
				// result = new SymbolicValue(1);
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register"))
					/* sv.sub(dest.toString().substring(1), 1) */;
			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
			}
			break;
		case OR:
			// Statement supported: or
			if (ins.getName().startsWith("or")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						/*
						 * sv.or(dest.toString().substring(1), src.toString()
						 * .substring(1))
						 */;
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						/*
						 * sv.or(dest.toString().substring(1), ((Immediate) src)
						 * .getNumber().intValue())
						 */;
					}
				}
			} else {
				// logger.error("Instruction not supported: " + ins.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// ins.toString(startAddress, symFinder));
				// return null;
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case SRL:
			if (ins.getName().startsWith("shr")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						/* int y = ((Immediate) src).getNumber().intValue(); */
						/* long x = (long) Math.pow(2, y); */
						/* sv.divide(dest.toString().substring(1), x) */;
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		case SLL:
			if (ins.getName().startsWith("shl")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest.getClass().getSimpleName().equals("X86Register")) {
					if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						/*
						 * sv.unsignedMul(dest.toString().substring(1), (long)
						 * Math.pow(2, ((Immediate)
						 * src).getNumber().intValue()))
						 */;
					}
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
			}
			break;
		default:
			System.out.println("Arithmetic statement not supported: " + ins.getName());
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// ins.toString(startAddress, symFinder));
			// return null;
		}

	}

	private void X86CallInterprete(X86CallInstruction inst) {
		Operand dest = inst.getOperand1();
		// System.out.println("Instruction: " + inst.getName());

		// call structure: push call next eip to stack, jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			// returnAddress = new SymbolicValue(((AbsoluteAddress)
			// dest).getValueOperand());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			// returnAddress = new SymbolicValue(((X86PCRelativeAddress)
			// dest).getEffectiveValue(startAddress));
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// returnAddress = (prevState.getRegValue(((X86Register)
			// dest).toString())).clone();
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// returnAddress = (prevState.getMemoryValue((X86MemoryOperand)
			// dest));
			// if (returnAddress == null) {
			// halt_status = MISSING_MEMORY;
			// memoryAddress = (X86MemoryOperand) dest;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;

			// returnAddress = returnAddress.clone();
		}

	}

	private void X86ConditionalJumpInterprete(X86CondJmpInstruction inst) {
		Operand dest = inst.getOperand1();

		// long returnAddress = 0;
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			// returnAddress = ((X86AbsoluteAddress) dest).getValueOperand();
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			// returnAddress = ((X86PCRelativeAddress) dest)
			// .getEffectiveValue(target.getValueOperand());
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// returnAddress = prevState.getRegValue(((X86Register)
			// dest).toString()).clone();
		}

		// System.out.println("Instruction: " + inst.getName());

		// structure: create new work associated with condition-false case
		// jump to condition-true case
		// add state condition into each trace
		// SymbolicCondition compareStatus = prevState.getCompareStatus();
		// we accept loop not to be constrained by compareStatus
		if (inst.getName().equals("loop")) {
			// if (compareStatus == null) {
			// logger.error("Only accept conditional statement after CMP");
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }
			// FormulaSet f = resolveLoop(inst);
			this.formulas.add(new FarkasAlgorithm(target, assemblyMap, traceList).resolve());
			System.out.println("Process Loop with Destination:" + dest.toString());
			return;
		}
		// SymbolicCondition positiveCond = new SymbolicCondition();
		// SymbolicCondition negativeCond = new SymbolicCondition();
		// int positiveConnector, negativeConnector;

		/*
		 * if (neg.get(target).getValueOperand() == this.traceList.get(0)
		 * .getValueOperand()) this.formulas.getTop().setOperator(
		 * CondJump.getReserCondJump((inst.getName()))); else
		 * this.formulas.getTop().setOperator(
		 * CondJump.getCondJump((inst.getName())));
		 */

		// // if (inst.getName().equals("ja") || inst.getName().equals("jg")) {
		// // // positiveConnector = SymbolicCondition.B_OP_GREATER;
		// // // negativeConnector = SymbolicCondition.B_OP_NOT_GREATER;
		// //
		// // } else if (inst.getName().equals("jae") ||
		// inst.getName().equals("jge")) {
		// // // positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
		// // // negativeConnector = SymbolicCondition.B_OP_LESS;
		// // if (neg.get(target).getValueOperand() ==
		// this.addrTraceList.get(0).getValueOperand())
		// // this.formulas.getTop().setOperator(3);
		// // else
		// // this.formulas.getTop().setOperator(6);
		// // } else if (inst.getName().equals("jb") ||
		// inst.getName().equals("jl")) {
		// // // positiveConnector = SymbolicCondition.B_OP_LESS;
		// // // negativeConnector = SymbolicCondition.B_OP_NOT_LESS;
		// // if (neg.get(target).getValueOperand() ==
		// this.addrTraceList.get(0).getValueOperand())
		// // this.formulas.getTop().setOperator(6);
		// // else
		// // this.formulas.getTop().setOperator(3);
		// // } else if (inst.getName().equals("jbe") ||
		// inst.getName().equals("jle")) {
		// // // positiveConnector = SymbolicCondition.B_OP_NOT_GREATER;
		// // // negativeConnector = SymbolicCondition.B_OP_GREATER;
		// // if (neg.get(target).getValueOperand() ==
		// this.addrTraceList.get(0).getValueOperand())
		// // this.formulas.getTop().setOperator(3);
		// // else
		// // this.formulas.getTop().setOperator(5);
		// // } else if (inst.getName().equals("je")) {
		// // // positiveConnector = SymbolicCondition.B_OP_EQUAL;
		// // // negativeConnector = SymbolicCondition.B_OP_NOT_EQUAL;
		// // if (neg.get(target).getValueOperand() ==
		// this.addrTraceList.get(0).getValueOperand())
		// // this.formulas.getTop().setOperator(4);
		// // else
		// // this.formulas.getTop().setOperator(1);
		// // } else if (inst.getName().equals("jne")) {
		// // // positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
		// // // negativeConnector = SymbolicCondition.B_OP_EQUAL;
		// // if (neg.get(target).getValueOperand() ==
		// this.addrTraceList.get(0).getValueOperand())
		// // this.formulas.getTop().setOperator(1);
		// // else
		// // this.formulas.getTop().setOperator(4);
		// // } else if (inst.getName().equals("jnl")) {
		// // // positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
		// // // negativeConnector = SymbolicCondition.B_OP_LESS;
		// // if (neg.get(target).getValueOperand() ==
		// this.addrTraceList.get(0).getValueOperand())
		// // this.formulas.getTop().setOperator(3);
		// // else
		// // this.formulas.getTop().setOperator(6);
		// } else if (inst.getName().equals("loop")) {
		// // decrease ecx, check if it is equal to 0
		// // SymbolicValue ecx = prevState.getRegValue("%ecx");
		// // SymbolicValue newDest = new SymbolicValue(ecx);
		// // newDest.addExprValue(SymbolicValue.SUB_EXPR, new
		// // SymbolicValue(1));
		// // prevState.setRegValue("%ecx", newDest);
		// // set condition
		// // compareStatus = new SymbolicCondition();
		// // compareStatus.addCondition(prevState.getRegValue("%ecx").clone(),
		// // new SymbolicValue(0), SymbolicCondition.B_OP_UNDEFINED);
		// // positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
		// // negativeConnector = SymbolicCondition.B_OP_EQUAL;
		// } else {
		// // logger.error("Unsupported instruction: " + inst.getName());
		// // halt_status = FAILED;
		// // SymbolicExecution.programTrace.setVisited(startAddress, null,
		// // inst.toString(startAddress, symFinder));
		// // return null;
		// }

	}

	private void X86JumpInterprete(X86JmpInstruction inst) {
		Operand dest = inst.getOperand1();
		// System.out.println("Instruction: " + inst.getName());

		// jump structure: jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			// returnAddress = new SymbolicValue(((AbsoluteAddress)
			// dest).getValueOperand());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			// returnAddress = new SymbolicValue(((X86PCRelativeAddress)
			// dest).getEffectiveValue(startAddress));
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// returnAddress = (prevState.getRegValue(((X86Register)
			// dest).toString())).clone();
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// returnAddress = prevState.getMemoryValue((X86MemoryOperand)
			// dest);
			// if (returnAddress == null) {
			// halt_status = MISSING_MEMORY;
			// memoryAddress = (X86MemoryOperand) dest;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }
			// returnAddress = returnAddress.clone();
		}

	}

	private void X86MoveInterprete(X86MoveInstruction inst) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		// System.out.println("Instruction: " + inst.getName());
		// SymbolicValue toMoveVal = null;

		if (src.getClass().getSimpleName().equals("Immediate")) {
			// toMoveVal = new SymbolicValue(((Immediate)
			// src).getNumber().intValue());
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// toMoveVal = prevState.getMemoryValue((X86MemoryOperand) src);
			// if (toMoveVal == null) {
			// halt_status = MISSING_MEMORY;
			// memoryAddress = (X86MemoryOperand) src;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }
			// toMoveVal = toMoveVal.clone();
		} else if (src.getClass().getSimpleName().equals("X86Register")) {
			// toMoveVal = prevState.getRegValue(((X86Register)
			// src).toString()).clone();
		}

		if (inst.getName().startsWith("mov")) {
			// normal move
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// prevState.getMemoryValues().put((MemoryOperand) dest,
				// toMoveVal);
			} else if (dest.getClass().getSimpleName().equals("X86Register")) {
				// prevState.setRegValue(((X86Register) dest).toString(),
				// toMoveVal);
			}

			if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					/*
					 * sv.mov(dest.toString().substring(1), src.toString()
					 * .substring(1))
					 */;
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					/*
					 * sv.mov(dest.toString().substring(1), ((Immediate) src)
					 * .getNumber().intValue())
					 */;
				}
			}

		} else {
			// conditional move
			// structure: create new work associated with condition-false case
			// move in condition-true case
			// remain the same in condition-false case

			// SymbolicCondition compareStatus = prevState.getCompareStatus();
			// if (compareStatus == null) {
			// logger.error("Only accept conditional statement after CMP");
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// inst.toString(startAddress, symFinder));
			// return null;
			// }

			// ignore signs
			// SymbolicCondition positiveCond = new SymbolicCondition();
			// SymbolicCondition negativeCond = new SymbolicCondition();
			// int positiveConnector, negativeConnector;

			if (inst.getName().equals("cmova") || inst.getName().equals("cmovg")) {
				// positiveConnector = SymbolicCondition.B_OP_GREATER;
				// negativeConnector = SymbolicCondition.B_OP_NOT_GREATER;
			} else if (inst.getName().equals("cmovae") || inst.getName().equals("cmovge")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
				// negativeConnector = SymbolicCondition.B_OP_LESS;
			} else if (inst.getName().equals("cmovb") || inst.getName().equals("cmovl")) {
				// positiveConnector = SymbolicCondition.B_OP_LESS;
				// negativeConnector = SymbolicCondition.B_OP_NOT_LESS;
			} else if (inst.getName().equals("cmovbe") || inst.getName().equals("cmovle")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_GREATER;
				// negativeConnector = SymbolicCondition.B_OP_GREATER;
			} else if (inst.getName().equals("cmove")) {
				// positiveConnector = SymbolicCondition.B_OP_EQUAL;
				// negativeConnector = SymbolicCondition.B_OP_NOT_EQUAL;
			} else if (inst.getName().equals("cmovne")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_EQUAL;
				// negativeConnector = SymbolicCondition.B_OP_EQUAL;
			} else if (inst.getName().equals("cmovnl")) {
				// positiveConnector = SymbolicCondition.B_OP_NOT_LESS;
				// negativeConnector = SymbolicCondition.B_OP_LESS;
			} else {
				// logger.error("Unsupported instruction: " + inst.getName());
				// halt_status = FAILED;
				// SymbolicExecution.programTrace.setVisited(startAddress, null,
				// inst.toString(startAddress, symFinder));
				// return null;
			}
		}

	}

	private void X86ReturnInterprete(X86RetInstruction inst) {

	}

	private void X86InstructionInterprete(X86Instruction inst) {
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		// System.out.println("Instruction: " + inst.getName());

		if (inst.getName().startsWith("cmp") || inst.getName().startsWith("test")) {
			// SymbolicValue lhs = null, rhs = null;

			Value d = null, s = null;
			if (dest.getClass().getSimpleName().equals("X86Register"))
				/* d = sv.getRegVal(dest.toString().substring(1)) */;
			else if (dest.getClass().getSimpleName().equals("Immediate"))
				d = new LongValueOld(((Immediate) dest).getNumber().intValue());
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				/* d = sv.getMemoryOperandVal((X86MemoryOperand) dest) */;
			}

			if (src.getClass().getSimpleName().equals("X86Register"))
				/* s = sv.getRegVal(src.toString().substring(1)) */;
			else if (src.getClass().getSimpleName().equals("Immediate"))
				s = new LongValueOld(((Immediate) src).getNumber().intValue());
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				/* d = sv.getMemoryOperandVal((X86MemoryOperand) dest) */;
			}

			this.formulas.add(new Formula(d.clone(), s.clone()));
			// set compare status

		} else if (inst.getName().startsWith("pop")) {
			if (dest.getClass().getSimpleName().equals("X86Register")) {

			} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// X86MemoryOperand memoryAddress = (X86MemoryOperand) dest;

			}
		} else if (inst.getName().startsWith("push")) {
			if (dest.getClass().getSimpleName().equals("X86Register")) {
				// String regName = ((X86Register) dest).toString();

			} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// X86MemoryOperand memoryAddress = (X86MemoryOperand) dest;

			}
		} else if (inst.getName().startsWith("nop")) {
			// do nothing
		} else {

		}

	}

	public FormulaSet getFormulas() {
		return formulas;
	}

	public void setFormulas(FormulaSet formulas) {
		this.formulas = formulas;
	}
}
