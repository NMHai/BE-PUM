package org.analysis;

import java.util.ArrayList;

/**
 * Set of conditions in 'and' forms
 * 
 * @author Binh Ngo
 * 
 */
public class SymbolicCondition {

	// condition type
	public static final int B_OP_UNDEFINED = 0;
	public static final int B_OP_EQUAL = 1;
	public static final int B_OP_GREATER = 2;
	public static final int B_OP_LESS = 3;
	public static final int B_OP_NOT_EQUAL = 4;
	public static final int B_OP_NOT_GREATER = 5;
	public static final int B_OP_NOT_LESS = 6;

	/**
	 * condition element in the form of [lhs] [connector] [rhs]
	 * 
	 * @author Binh Ngo
	 * 
	 */
	public class CondElement {
		private SymbolicValue lhs;
		private SymbolicValue rhs;
		private int connector;

		/**
		 * clone a new element with the same structure
		 */
		public CondElement clone() {
			CondElement res = new CondElement(lhs.clone(), rhs.clone(), connector);
			return res;
		}

		/**
		 * initiate a new element
		 * 
		 * @param lhs
		 *            left-hand-side value
		 * @param rhs
		 *            right-hand-side value
		 * @param connector
		 *            relationship between lhs and rhs
		 */
		public CondElement(SymbolicValue lhs, SymbolicValue rhs, int connector) {
			this.lhs = lhs;
			this.rhs = rhs;
			this.connector = connector;
		}

		/**
		 * @return left-hand-side value
		 */
		public SymbolicValue getLHS() {
			return lhs;
		}

		/**
		 * @return right-hand-side value
		 */
		public SymbolicValue getRHS() {
			return rhs;
		}

		/**
		 * @return relationship between lhs and rhs
		 */
		public int getConnector() {
			return connector;
		}

		/**
		 * resolve condition element to string
		 */
		public String toString() {
			String ret = "";
			// if (lhs.getType() == SymbolicValue.SYMBOLIC_EXPRESSION) {
			// ret += "(" + lhs.toString() + ")";
			// }
			// else {
			ret += lhs.toString();
			// }
			switch (connector) {
			case B_OP_EQUAL:
				ret += " = ";
				break;
			case B_OP_GREATER:
				ret += " > ";
				break;
			case B_OP_LESS:
				ret += " < ";
				break;
			case B_OP_NOT_EQUAL:
				ret += " != ";
				break;
			case B_OP_NOT_GREATER:
				ret += " <= ";
				break;
			case B_OP_NOT_LESS:
				ret += " >= ";
				break;
			case B_OP_UNDEFINED:
				ret += " >< ";
			default:
				ret += " " + connector + " ";
			}
			// if (rhs.getType() == SymbolicValue.SYMBOLIC_EXPRESSION) {
			// ret += "(" + rhs.toString() + ")";
			// }
			// else {
			ret += rhs.toString();
			// }
			return ret;
		}
	}

	private ArrayList<CondElement> conditionSet;

	/**
	 * initiate a new symbolic condition
	 */
	public SymbolicCondition() {
		conditionSet = new ArrayList<CondElement>();
	}

	/**
	 * clone a new condition with the same structure
	 */
	public SymbolicCondition clone() {
		SymbolicCondition res = new SymbolicCondition();
		for (int i = 0; i < conditionSet.size(); i++) {
			res.addCondition(conditionSet.get(i).lhs, conditionSet.get(i).rhs, conditionSet.get(i).connector);
		}
		return res;
	}

	/**
	 * add a new condition element with given properties
	 * 
	 * @param lhs
	 *            left-hand-side value
	 * @param rhs
	 *            right-hand-side value
	 * @param connector
	 *            relationship between lhs and rhs
	 */
	public void addCondition(SymbolicValue lhs, SymbolicValue rhs, int connector) {
		CondElement condElm = new CondElement(lhs, rhs, connector);
		conditionSet.add(condElm);
	}

	/**
	 * update condition with elements of given condition
	 * 
	 * @param cond
	 *            condition with elements to be added
	 */
	public void addCondition(SymbolicCondition cond) {
		if (cond == null) {
			return;
		}
		CondElement tmpElm = null;
		for (int i = 0; i < cond.conditionSet.size(); i++) {
			tmpElm = cond.conditionSet.get(i);
			addCondition(tmpElm.lhs, tmpElm.rhs, tmpElm.connector);
		}
	}

	/**
	 * update condition with elements of given condition
	 * 
	 * @param cond
	 *            condition with elements to be added
	 * @param connector
	 *            relationship between new and old elements
	 */
	public void addCondition(SymbolicCondition cond, int connector) {
		if (cond == null) {
			return;
		}
		CondElement tmpElm = null;
		for (int i = 0; i < cond.conditionSet.size(); i++) {
			tmpElm = cond.conditionSet.get(i);
			addCondition(tmpElm.lhs, tmpElm.rhs, connector);
		}
	}

	/**
	 * 
	 * @return set of condition elements
	 */
	public ArrayList<CondElement> getConditionSet() {
		return conditionSet;
	}

	/**
	 * resolve condition to string
	 */
	public String toString() {
		String res = "";
		for (int i = 0; i < conditionSet.size(); i++) {
			res += conditionSet.get(i).toString();
			if (i < conditionSet.size() - 1) {
				res += "\n           ";
			}
		}
		return res;
	}
}
