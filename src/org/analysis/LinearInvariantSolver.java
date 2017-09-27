package org.analysis;

import java.util.ArrayList;

// Initial condition is at the form:
//    eax = c1
//    ebx = c2
//    ecx = c3
//    edx = c4
// Path condition is at the form
//    eax' = eax + d1
//    ebx' = ebx + d2
//    ecx' = ecx + d3
//    edx' = edx + d4
// Invariant is at the form
//    Aeax + Bebx + Cecx + Dedx = E
//    => E = Ac1 + Bc2 + Cc3 + Dc4
//    => Constraint on A, B, C, D:
//       Ad1 + Bd2 + Cd3 + Dd4 = 0
/**
 * In limited scope, this class accepts init and path condition with limited
 * form, and return invariant for those conditions
 * 
 * @author Binh Ngo
 * 
 */
public class LinearInvariantSolver {

	private SymbolicCondition initCondition;
	private SymbolicCondition pathCondition;
	private ArrayList<String> variableSet;

	/**
	 * initiate a new solver with the variable set, init and path condition
	 * 
	 * @param variableSet
	 *            variables in conditions
	 * @param initialConstraint
	 *            initial condition at beginning of the loop
	 * @param pathConstraint
	 *            path condition for the loop
	 */
	public LinearInvariantSolver(ArrayList<String> variableSet, SymbolicCondition initialConstraint,
			SymbolicCondition pathConstraint) {
		initCondition = initialConstraint;
		pathCondition = pathConstraint;
		this.variableSet = variableSet;
	}

	/**
	 * check for registers which preserve the same values after the loop. those
	 * registers will be added as invariants
	 * 
	 * @return unchanged registers
	 */
	public ArrayList<String> getPreservedRegs() {
		ArrayList<String> result = new ArrayList<String>();
		// regs with di = 0
		// cond is already satisfied in control flow
		SymbolicValue tmpExpr = null;
		for (int i = 0; i < variableSet.size(); i++) {
			tmpExpr = pathCondition.getConditionSet().get(i).getRHS();
			if (tmpExpr.getType() == SymbolicValue.SYMBOL && tmpExpr.getSymbolValue().equals(variableSet.get(i))) {
				result.add(variableSet.get(i));
			} else if (tmpExpr.getType() == SymbolicValue.SYMBOLIC_EXPRESSION) {
				ArrayList<SymbolicValue> exprList = tmpExpr.getExprValue();
				Long di = new Long(0);
				int isSat = 0;
				for (int j = 0; j < exprList.size(); j++) {
					if (exprList.get(j).getType() == SymbolicValue.SYMBOL) {
						if (exprList.get(j).getSymbolValue().equals(variableSet.get(i))) {
							if (isSat >= 0) {
								isSat = 1;
							}
						} else {
							isSat = -1;
						}
					} else {
						if (exprList.get(j).calculateExprIntVal() != null) {
							if (tmpExpr.getExprType() == SymbolicValue.ADD_EXPR) {
								di += exprList.get(j).calculateExprIntVal();
							} else if (tmpExpr.getExprType() == SymbolicValue.SUB_EXPR) {
								di -= exprList.get(j).calculateExprIntVal();
							}
						}
					}
				}
				if (di.longValue() == 0 && isSat == 1) {
					result.add(variableSet.get(i));
				}
			}
		}
		return result;
	}

	/**
	 * solve condition to infer invariant
	 * 
	 * @return list of invariant components
	 */
	public ArrayList<SymbolicValue> solve() {
		// EXPECTED INVARIANT: SUM(Ai * VAR_I) = D
		ArrayList<SymbolicValue> result = new ArrayList<SymbolicValue>();
		for (int i = 0; i < variableSet.size(); i++) {
			result.add(null);
		}
		// c1, c2, c3, c4 are right hand side of initial condition
		// calculate d1, d2, d3, d4
		// with the limitation, d1, d2, d3, d4 are long type
		// Ad1 + Bd2 + Cd3 + Dd4 = 0
		// di = 0 => coefficient = 0
		// with the remaining coefficient:
		// other than last one: 1
		// last one: -1/(other di)
		ArrayList<Long> dArray = new ArrayList<Long>();
		SymbolicValue tmpExpr = null;
		int lastIndex = -1;
		// condition for path condition: NEW_REG = OLD_REG +- expr
		int condSatisfied = 0;
		for (int i = 0; i < variableSet.size(); i++) {
			condSatisfied = 0;
			tmpExpr = pathCondition.getConditionSet().get(i).getRHS();
			if (tmpExpr.getType() == SymbolicValue.SYMBOL && tmpExpr.getSymbolValue().equals(variableSet.get(i))) {
				dArray.add(null);
				condSatisfied = 1;
			} else if (tmpExpr.getType() == SymbolicValue.INTEGER || tmpExpr.getType() == SymbolicValue.FLOAT) {
				lastIndex = i;
				dArray.add(null);
				condSatisfied = 1;
				// register becomes constant, append later
			} else if (tmpExpr.getType() == SymbolicValue.SYMBOLIC_EXPRESSION) {
				ArrayList<SymbolicValue> exprList = tmpExpr.getExprValue();
				Long di = new Long(0);
				for (int j = 0; j < exprList.size(); j++) {
					if (exprList.get(j).getType() == SymbolicValue.SYMBOL) {
						if (exprList.get(j).getSymbolValue().equals(variableSet.get(i))) {
							if (condSatisfied >= 0) {
								condSatisfied = 1;
							}
						} else {
							condSatisfied = -1;
						}
					} else {
						if (exprList.get(j).calculateExprIntVal() != null) {
							if (tmpExpr.getExprType() == SymbolicValue.ADD_EXPR) {
								di += exprList.get(j).calculateExprIntVal();
							} else if (tmpExpr.getExprType() == SymbolicValue.SUB_EXPR) {
								di -= exprList.get(j).calculateExprIntVal();
							}
						}
					}
				}
				if (di.longValue() != 0) {
					lastIndex = i;
					dArray.add(di);
				} else {
					dArray.add(null);
				}
			} else {
				System.out.println("NOT SUPPORTED THIS TYPE OF INVARIANT");
				return null;
			}
			if (condSatisfied != 1) {
				System.out.println("NOT SUPPORTED THIS TYPE OF INVARIANT");
				return null;
			}
		}

		// return invariant
		if (lastIndex == -1) {
			return null;
		}
		long tmpValue = 0;
		// Ad1 + Bd2 + Cd3 + Dd4 = 0
		// other than last one: 1
		// last one: -1/(other di)
		for (int i = 0; i < variableSet.size(); i++) {
			if (i == lastIndex) {
				if (dArray.get(i) == null || dArray.get(i).longValue() == 0 || tmpValue == 0) {
					result.set(i, null);
				} else {
					result.set(i, new SymbolicValue(-tmpValue / dArray.get(i).longValue()));
				}
				break;
			}
			if (dArray.get(i) != null) {
				result.set(i, new SymbolicValue(1));
				tmpValue += dArray.get(i);
			}
		}
		// Aeax + Bebx + Cecx + Dedx = E
		tmpValue = 0;
		SymbolicValue res = null;
		for (int i = 0; i < variableSet.size(); i++) {
			if (dArray.get(i) != null && dArray.get(i).longValue() != 0) {
				tmpExpr = initCondition.getConditionSet().get(i).getRHS();
				if (tmpExpr.calculateExprIntVal() != null) {
					tmpValue += tmpExpr.calculateExprIntVal() * result.get(i).calculateExprIntVal();
				} else {
					SymbolicValue tmp = result.get(i);
					if (tmp.calculateExprIntVal() == 1) {
						tmp = tmpExpr;
					} else {
						tmp.addExprValue(SymbolicValue.MUL_EXPR, tmpExpr);
					}
					if (res == null) {
						res = new SymbolicValue(tmp);
					} else {
						res.addExprValue(SymbolicValue.ADD_EXPR, tmp);
					}
				}
			}
		}
		if (tmpValue != 0) {
			if (res == null) {
				res = new SymbolicValue(tmpValue);
			} else {
				res.addExprValue(SymbolicValue.ADD_EXPR, new SymbolicValue(tmpValue));
			}
		}
		result.add(res);
		return result;
	}
}
