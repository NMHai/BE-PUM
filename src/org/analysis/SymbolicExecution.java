package org.analysis;

import org.analysis.SymbolicWorkList.SymbolicWork;
import org.analysis.TraceTracker.Location;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.util.Characters;
import org.jakstab.util.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Apply symbolic execution to parsed binary program
 * 
 * @author Binh Ngo
 * 
 */
public class SymbolicExecution {
	public static Program program;
	public static int runningMode;
	// preserved for backtrack invariant finding
	public static long loopStart;
	public static SymbolicState loopStartState;
	public static long loopEnd;
	public static TraceTracker loopTrace;
	// running trace
	public static TraceTracker programTrace;
	// result of last executed instruction
	public static SymbolicState preservedState = null;
	// constants
	public static final boolean INSTRUCTION_DEBUG_MODE = false;
	public static final int NORMAL_EXECUTION = 0;
	public static final int INVARIANT_CHECKING_EXECUTION = 1;
	public static final int INVARIANT_POPULATING_EXECUTION = 2;
	private final static Logger logger = Logger.getLogger(SymbolicExecution.class);
	// data retrieve from program
	private Map<AbsoluteAddress, Instruction> assemblyMap;
	private static TreeMap<AbsoluteAddress, SymbolicState> stateMap;
	private static AbsoluteAddress startAddress = null;

	// only to be called when writing disassembly
	// by default, program startAddress is the minimum address of program
	/**
	 * set program starting address
	 * 
	 * @param address
	 *            long value of starting address
	 */
	public static void setStartAddress(AbsoluteAddress address) {
		if (startAddress == null || address.getValue() < startAddress.getValue()) {
			startAddress = address;
		}
	}

	/**
	 * initiate a symbolic execution process for a given program
	 * 
	 * @param program
	 *            parsed binary program
	 */
	public SymbolicExecution(Program program) {
		SymbolicExecution.program = program;
		runningMode = NORMAL_EXECUTION;
		loopStart = -1;
		loopStartState = null;
		loopEnd = -1;
		loopTrace = null;
		// data retrieve from program
		assemblyMap = program.getAssemblyMap();
		stateMap = new TreeMap<AbsoluteAddress, SymbolicState>();
		programTrace = new TraceTracker();
		// init program trace
		for (Map.Entry<AbsoluteAddress, Instruction> inst : assemblyMap.entrySet()) {
			stateMap.put(inst.getKey(), null);
			programTrace.addTraceAddress(inst.getKey().getValue());
		}
		SymbolicWorkList.initWorkList();
	}

	/**
	 * clone a state map with the same structure
	 * 
	 * @return state map (between address and state)
	 */
	public static TreeMap<AbsoluteAddress, SymbolicState> cloneStateMap() {
		if (stateMap == null) {
			return null;
		}
		TreeMap<AbsoluteAddress, SymbolicState> res = new TreeMap<AbsoluteAddress, SymbolicState>();
		for (Map.Entry<AbsoluteAddress, SymbolicState> entry : stateMap.entrySet()) {
			if (entry.getValue() != null) {
				res.put(entry.getKey(), entry.getValue().clone());
			} else {
				res.put(entry.getKey(), null);
			}
		}
		return res;
	}

	/**
	 * clone a trace tracker with the same structure
	 * 
	 * @return cloned trace tracker
	 */
	public static TraceTracker cloneTraceMap() {
		return programTrace.clone();
	}

	/**
	 * start running symbolic execution
	 */
	public void execute() {
		// Set initial values for start location
		if (startAddress == null) {
			logger.error("Empty program. Symbolic execution exits");
			return;
		}
		// add initial work
		SymbolicState currentState = stateMap.get(startAddress);
		if (currentState == null) {
			currentState = new SymbolicState();
			stateMap.put(startAddress, currentState);
		}
		String tmpName = "";
		logger.info("Initialize symbolic values for start location's register");
		for (int i = 0; i < currentState.getRegisterList().size(); i++) {
			tmpName = currentState.getRegisterList().get(i);
			currentState.getRegValue(tmpName).initialSymbolicValue();
			logger.info(tmpName + " <-- " + currentState.getRegValue(tmpName).toString());
		}
		// TraceTracker.printInfo();
		SymbolicWorkList.addWork(new SymbolicValue(startAddress.getValue()), null, stateMap, programTrace, null);

		// Start symbolic execution
		// The progress will start in 2 step
		// - Traverse the list to find branch statement (call, jump, cond jump,
		// return)
		// - When the destination is available, mark to apply loop invariant -
		// When the destination is not available, notify and exit
		// (This will continue with dynamic checking to find the destination)

		boolean unresolveAddrFound = false;
		boolean addressOutOfBound = false;
		boolean revisitNode = false;

		Instruction currentInstruction = null;
		SymbolicCondition condition = null;
		SymbolicValue nextAddress = null;
		Long nextAddressValue = null;

		// do all jobs
		while (!SymbolicWorkList.isDone()) {
			SymbolicWork remainingJob = SymbolicWorkList.getWork();
			nextAddress = remainingJob.getNextAddress();
			condition = remainingJob.getCondition();
			stateMap = remainingJob.getStateMap();
			programTrace = remainingJob.getTraceTracker();

			unresolveAddrFound = false;
			addressOutOfBound = false;
			revisitNode = false;

			// find next address
			nextAddressValue = nextAddress.calculateExprIntVal();
			if (nextAddressValue == null) {
				// unresolve address found
				unresolveAddrFound = true;
				remainingJob.setEndingStatus(SymbolicWorkList.UNRESOLVE_ADDRESS);
				remainingJob.setUnresolvedAddress(nextAddress);
				break;
			}
			startAddress = new AbsoluteAddress(nextAddressValue.longValue());
			if (remainingJob.getToAppendState() != null) {
				stateMap.put(startAddress, remainingJob.getToAppendState());
			}

			// stop job when address out of bound or unresolve address found
			while (!unresolveAddrFound && !addressOutOfBound) {
				// halt when revisit node or work done
				while (!revisitNode && !unresolveAddrFound && !addressOutOfBound) {
					currentInstruction = assemblyMap.get(startAddress);

					if (currentInstruction == null) {
						addressOutOfBound = true;
						break;
					}

					currentState = stateMap.get(startAddress);
					if (currentState == null) {
						if (preservedState == null) {
							unresolveAddrFound = true;
							break;
						} else {
							currentState = preservedState;
							stateMap.put(startAddress, preservedState);
							preservedState = null;
						}
					} else {
						if (preservedState != null) {
							revisitNode = true;
							break;
						}
					}

					if (condition != null) {
						if (currentState.getCondition() == null) {
							currentState.setCondition(condition);
						} else {
							currentState.getCondition().addCondition(condition);
						}
						condition = null;
					}

					X86Interpretation.setStartAddress(startAddress);
					// compare status is reset after executing statement
					nextAddress = currentInstruction.interprete(currentState.clone());

					// check interpretation status
					if (X86Interpretation.getReturnStatus() == X86Interpretation.FAILED) {
						unresolveAddrFound = true;
						remainingJob.setEndingStatus(SymbolicWorkList.NOT_SUPPORTED_FUNCTION);
						break;
					} else if (X86Interpretation.getReturnStatus() == X86Interpretation.MISSING_MEMORY) {
						unresolveAddrFound = true;
						remainingJob.setEndingStatus(SymbolicWorkList.MISSING_MEMORY);
						remainingJob.setMissingMemory(X86Interpretation.getMemoryLocation());
						break;
					} else if (X86Interpretation.getReturnStatus() == X86Interpretation.MISSING_STACK) {
						unresolveAddrFound = true;
						remainingJob.setEndingStatus(SymbolicWorkList.MISSING_STACK);
						break;
					}

					nextAddressValue = nextAddress.calculateExprIntVal();
					if (nextAddressValue == null) {
						unresolveAddrFound = true;
						remainingJob.setEndingStatus(SymbolicWorkList.UNRESOLVE_ADDRESS);
						remainingJob.setUnresolvedAddress(nextAddress);
					}
					startAddress = new AbsoluteAddress(nextAddressValue.longValue());
				}
				if (revisitNode) {
					// change running mode
					// System.out.println("START LINEAR INVARIANT FINDING AT ADDRESS = $0x"
					// +
					// Long.toHexString(startAddress.getValueOperand()).toUpperCase());
					runningMode = INVARIANT_CHECKING_EXECUTION;
					loopStart = startAddress.getValue();
					loopEnd = X86Interpretation.getStartAddress();
					loopStartState = currentState.clone();
					loopTrace = remainingJob.getTraceTracker();
					// TODO: revisit a node (indicate a loop)
					// we only deal with 1 simple loop now
					// - delete previous added add, cause now we combine traces
					// to find invariants
					SymbolicWorkList.getRemainingWorks().remove(SymbolicWorkList.getRemainingWorks().size() - 1);
					// - variables (eax, ebc, ecx ...)
					// now replaced with symbols ("%eax", "%ebx", "%ecx"...)
					// indicate old variables
					for (int i = 0; i < currentState.getRegisterList().size(); i++) {
						currentState.setRegValue(currentState.getRegisterList().get(i), new SymbolicValue(currentState
								.getRegisterList().get(i)));
					}
					// - execute trace again until we find the loop node (jump)
					// again
					// - FOLLOW THE OLD TRACE BEHAVIOR
					Location tmpLoc = loopTrace.getLocationInfo(loopStart);
					if (tmpLoc.getNextAddress() != null) {
						// a loop call to itself
						if (tmpLoc.getNextAddress().calculateExprIntVal() != null
								&& tmpLoc.getNextAddress().calculateExprIntVal().longValue() == loopStart) {
							System.out.println("NON TERMINATING LOOP FOUND. EXIT");
							remainingJob.setEndingStatus(SymbolicWorkList.NOT_SUPPORTED_FUNCTION);
							unresolveAddrFound = true;
							break;
						}
					}

					// find the relationship between state variables
					do {
						currentInstruction = assemblyMap.get(new AbsoluteAddress(tmpLoc.getAddress()));
						X86Interpretation.setStartAddress(new AbsoluteAddress(tmpLoc.getAddress()));
						// no need to clone running state, we just want to find
						// the relationship
						currentInstruction.interprete(currentState);
						// as the trace is traversed, no need to error checking
						// here
						tmpLoc = loopTrace.getLocationInfo(tmpLoc.getNextAddress().calculateExprIntVal().longValue());
					} while (X86Interpretation.getStartAddress() != loopEnd);

					// - we have the relationship between new and old variables
					// in preservedState
					// currentState.printInfo();
					// System.out.println("SOLVING LINEAR CONSTRAINTS");
					SymbolicCondition initialConstraint = new SymbolicCondition();
					SymbolicCondition pathConstraint = new SymbolicCondition();
					// add information of starting state
					// initialConstraint.addCondition(loopStartState.getCondition());
					// pathConstraint.addCondition(loopStartState.getCondition());

					String regName = "";
					for (int i = 0; i < loopStartState.getRegisterList().size(); i++) {
						regName = loopStartState.getRegisterList().get(i);
						initialConstraint.addCondition(new SymbolicValue(regName), loopStartState.getRegValue(regName)
								.clone(), SymbolicCondition.B_OP_EQUAL);
					}
					// add path behavior
					for (int i = 0; i < currentState.getRegisterList().size(); i++) {
						regName = currentState.getRegisterList().get(i);
						pathConstraint.addCondition(new SymbolicValue(regName + "_NEW"),
								currentState.getRegValue(regName).clone(), SymbolicCondition.B_OP_EQUAL);
					}
					// we have initial constraint and path constraint
					LinearInvariantSolver solver = new LinearInvariantSolver(currentState.getRegisterList(),
							initialConstraint, pathConstraint);
					// solve to find invariants
					ArrayList<SymbolicValue> invariants = solver.solve();
					ArrayList<String> preservedRegs = solver.getPreservedRegs();
					// System.out.println(Characters.LINE_FULL_WIDTH);
					// TODO: add invariant to currentState
					SymbolicCondition invariantCond = null;
					if (invariants != null) {
						SymbolicValue lhs = null, rhs = null, tmpVal = null;
						// System.out.println("Calculated invariant:");
						// last index
						int lastIndex = -1;
						for (int i = 0; i < invariants.size(); i++) {
							if (invariants.get(i) != null) {
								lastIndex = i;
							}
						}
						for (int i = 0; i < invariants.size(); i++) {
							if (invariants.get(i) != null) {
								tmpVal = invariants.get(i);
								if (i < lastIndex) {
									if (tmpVal.calculateExprIntVal() == 1) {
										tmpVal = new SymbolicValue(currentState.getRegisterList().get(i));
									} else {
										tmpVal.addExprValue(SymbolicValue.MUL_EXPR, new SymbolicValue(currentState
												.getRegisterList().get(i)));
									}
									if (lhs == null) {
										lhs = tmpVal;
									} else {
										SymbolicValue newLHS = new SymbolicValue(lhs);
										newLHS.addExprValue(SymbolicValue.ADD_EXPR, tmpVal);
										lhs = newLHS;
									}
									// if (i > 0) {
									// System.out.print(" + ");
									// }
								} else if (i == lastIndex) {
									tmpVal = invariants.get(i);
									rhs = tmpVal;
									// System.out.print(" = ");
								}
								// System.out.print(tmpVal.toString());
							}
						}
						// System.out.println();
						// System.out.println(Characters.LINE_FULL_WIDTH);
						// after invariant is infered, update trace and follow
						// the negative path
						// reset values for the loop start, constant reserved,
						// new symbol created for variables.
						preservedState = loopStartState;
						loopStartState = null;
						for (int i = 0; i < currentState.getRegisterList().size(); i++) {
							regName = currentState.getRegisterList().get(i);
							if (currentState.getRegValue(regName).calculateExprIntVal() != null) {
								preservedState.setRegValue(regName, currentState.getRegValue(regName));
							} else {
								if (!preservedRegs.contains(regName)) {
									// generate new symbol, update invariant
									preservedState.getRegValue(regName).initialSymbolicValue();
									lhs.exchangeSymbol(regName, preservedState.getRegValue(regName).getSymbolValue());
								}
							}
						}
						invariantCond = new SymbolicCondition();
						invariantCond.addCondition(lhs, rhs, SymbolicCondition.B_OP_EQUAL);

						// - execute trace again until we find the loop node
						// (jump) again
						// - FOLLOW THE OLD TRACE BEHAVIOR
						preservedState.addCondition(invariantCond);
						startAddress = new AbsoluteAddress(loopStart);
						runningMode = INVARIANT_POPULATING_EXECUTION;
						revisitNode = false;
						do {
							currentState = preservedState;
							stateMap.put(startAddress, preservedState);
							preservedState = null;

							currentInstruction = assemblyMap.get(startAddress);
							X86Interpretation.setStartAddress(startAddress);
							currentInstruction.interprete(currentState.clone());

							tmpLoc = loopTrace.getLocationInfo(tmpLoc.getNextAddress().calculateExprIntVal()
									.longValue());
							startAddress = new AbsoluteAddress(tmpLoc.getAddress());
						} while (X86Interpretation.getStartAddress() != loopEnd);
						// loop processed, continue with negative path
						unresolveAddrFound = true;
						remainingJob.setEndingStatus(SymbolicWorkList.INVARIANT_FOUND);
						preservedState = null;
					}
				}
			}

			System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
			for (int j = 0; j < 64; j++) {
				System.out.print("=");
			}
			System.out.println("WORK INFORMATION");
			System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
			ArrayList<Location> trackerInfo = remainingJob.getTraceTracker().trackerInfo();
			SymbolicState tmpState = null;
			for (int i = 0; i < trackerInfo.size(); i++) {
				// trace info
				if (i > 0) {
					System.out.println(Characters.LINE_FULL_WIDTH);
				}
				// state information
				tmpState = remainingJob.getStateMap().get(new AbsoluteAddress(trackerInfo.get(i).getAddress()));
				if (tmpState != null) {
					tmpState.printInfo();
				}
				// statement and tracing information
				remainingJob.getTraceTracker().getTraceInfo(trackerInfo.get(i).getAddress());
			}
			System.out.println(Characters.LINE_FULL_WIDTH);
			if (remainingJob.getEndingStatus() == SymbolicWorkList.NOT_SUPPORTED_FUNCTION) {
				System.out.println("<WORK FINISHED. FUNCTIONS UNSUPPORTED>");
			} else if (remainingJob.getEndingStatus() == SymbolicWorkList.MISSING_MEMORY) {
				System.out.println("<WORK FINISHED. MEMORY UNKNOWN>");
			} else if (remainingJob.getEndingStatus() == SymbolicWorkList.MISSING_STACK) {
				System.out.println("<WORK FINISHED. STACK INFORMATION MISSING>");
			} else if (remainingJob.getEndingStatus() == SymbolicWorkList.UNRESOLVE_ADDRESS) {
				System.out.println("<WORK FINISHED. UNRESOLVED ADDRESS FOUND>");
			} else if (remainingJob.getEndingStatus() == SymbolicWorkList.INVARIANT_FOUND) {
				System.out.println("<WORK FINISHED. INVARIANT INFERRED>");
			} else {
				System.out.println("<WORK FINISHED. ALL INSTRUCTION PROCESSED>");
			}
		}
		// finish while all job done or unresolve address found
		// combine execution tree to find condition set for each program
		// location
	}
}
