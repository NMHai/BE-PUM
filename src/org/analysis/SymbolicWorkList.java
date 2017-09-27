package org.analysis;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.MemoryOperand;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * worklist that store un-processed path when symbolic executing
 * 
 * @author Binh Ngo
 * 
 */
public class SymbolicWorkList {

	public static final int SUCCESS = 0;
	public static final int UNRESOLVE_ADDRESS = 1;
	public static final int ADDRESS_OUT_OF_BOUND = 2;
	public static final int MISSING_MEMORY = 3;
	public static final int MISSING_STACK = 4;
	public static final int NOT_SUPPORTED_FUNCTION = 5;
	public static final int INVARIANT_FOUND = 6;

	/**
	 * unprocessed symbolic path when symbolic executing
	 * 
	 * @author Binh Ngo
	 * 
	 */
	public static class SymbolicWork {
		private int workId;
		private SymbolicValue nextAddress;
		private SymbolicCondition condition;
		private TreeMap<AbsoluteAddress, SymbolicState> currentStateMap;
		private TraceTracker programTrace;
		private SymbolicState toAppendState;
		private int endingStatus;
		// preserved for unresolve address
		private SymbolicValue unresolvedAddress;
		// preserved for missing memory
		private MemoryOperand missingMemory;

		/**
		 * initiate a new symbolic work with given information
		 * 
		 * @param nextAddress
		 *            next address to process
		 * @param toAppendCond
		 *            condition to start work
		 * @param currentStateMap
		 *            current state map
		 * @param programTrace
		 *            current tracker
		 * @param toAppendState
		 *            state to be added (in case of conjuctions)
		 */
		public SymbolicWork(SymbolicValue nextAddress, SymbolicCondition toAppendCond,
				TreeMap<AbsoluteAddress, SymbolicState> currentStateMap, TraceTracker programTrace,
				SymbolicState toAppendState) {
			workId = index++;
			this.nextAddress = nextAddress;
			condition = toAppendCond;
			this.currentStateMap = currentStateMap;
			this.programTrace = programTrace;
			this.toAppendState = toAppendState;
			endingStatus = SUCCESS;
			unresolvedAddress = null;
			missingMemory = null;
		}

		/**
		 * update work ending state
		 * 
		 * @param status
		 *            status when work stopped
		 */
		public void setEndingStatus(int status) {
			endingStatus = status;
		}

		/**
		 * @return work ending status
		 */
		public int getEndingStatus() {
			return endingStatus;
		}

		/**
		 * set unresolved address when current work meet this problem
		 * 
		 * @param address
		 *            unresolved address
		 */
		public void setUnresolvedAddress(SymbolicValue address) {
			unresolvedAddress = address;
		}

		/**
		 * @return unresolved address when current work meet this problem
		 */
		public SymbolicValue getUnresolvedAddress() {
			return unresolvedAddress;
		}

		/**
		 * set missing memory when current work meet this problem
		 * 
		 * @param memory
		 *            missing memory operand
		 */
		public void setMissingMemory(MemoryOperand memory) {
			missingMemory = memory;
		}

		/**
		 * @return missing memory when current work meet this problem
		 */
		public MemoryOperand getMissingMemory() {
			return missingMemory;
		}

		/**
		 * @return work control id
		 */
		public int getId() {
			return workId;
		}

		/**
		 * @return work item next address to processed
		 */
		public SymbolicValue getNextAddress() {
			return nextAddress;
		}

		/**
		 * @return condition for this work item
		 */
		public SymbolicCondition getCondition() {
			return condition;
		}

		/**
		 * @return current state map of work item
		 */
		public TreeMap<AbsoluteAddress, SymbolicState> getStateMap() {
			return currentStateMap;
		}

		/**
		 * @return trace tracker of work item
		 */
		public TraceTracker getTraceTracker() {
			return programTrace;
		}

		/**
		 * @return (if exist) state to be append to the next address state map
		 */
		public SymbolicState getToAppendState() {
			return toAppendState;
		}
	}

	private static ArrayList<SymbolicWork> workList;
	private static ArrayList<SymbolicWork> processedWorkList;

	private static int index = 0;

	/**
	 * check if all works are finished
	 * 
	 * @return true if everything's done
	 */
	public static boolean isDone() {
		if (workList == null)
			return true;
		return workList.isEmpty();
	}

	/**
	 * initialize work list
	 */
	public static void initWorkList() {
		if (workList == null) {
			workList = new ArrayList<SymbolicWork>();
		} else {
			workList.clear();
		}
		if (processedWorkList == null) {
			processedWorkList = new ArrayList<SymbolicWork>();
		} else {
			processedWorkList.clear();
		}
	}

	/**
	 * get next un-processed work
	 * 
	 * @return next unprocessed work
	 */
	public static SymbolicWork getWork() {
		if (processedWorkList == null || workList.isEmpty()) {
			return null;
		} else {
			SymbolicWork toProcessedWork = workList.remove(0);
			processedWorkList.add(toProcessedWork);
			return toProcessedWork;
		}
	}

	/**
	 * @return all processed works in list
	 */
	public static ArrayList<SymbolicWork> getProcessedWorks() {
		if (processedWorkList == null || processedWorkList.isEmpty()) {
			return null;
		} else {
			return processedWorkList;
		}
	}

	/**
	 * @return all un-processed works in list
	 */
	public static ArrayList<SymbolicWork> getRemainingWorks() {
		if (workList == null || workList.isEmpty()) {
			return null;
		} else {
			return workList;
		}
	}

	/**
	 * add new work with given information
	 * 
	 * @param nextAddress
	 *            next address to process
	 * @param toAppendCond
	 *            condition to start work
	 * @param currentStateMap
	 *            current state map
	 * @param programTrace
	 *            current tracker
	 * @param toAppendState
	 *            state to be added (in case of conjuctions)
	 */
	public static void addWork(SymbolicValue nextAddress, SymbolicCondition toAppendCond,
			TreeMap<AbsoluteAddress, SymbolicState> currentStateMap, TraceTracker programTrace,
			SymbolicState toAppendState) {
		if (workList == null) {
			initWorkList();
		}
		SymbolicWork newJob = new SymbolicWork(nextAddress, toAppendCond, currentStateMap, programTrace, toAppendState);
		workList.add(newJob);
		return;
	}
}
