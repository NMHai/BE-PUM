package org.analysis.formula;

public class Formula {
	private Value leftExp, rightExp;
	private String operator;

	// private int operator = 0;
	// private String [] mean_op;

	/*
	 * Undefined operator = 0; EQUAL operator = 1; GREATER operator = 2; LESS
	 * operator = 3; NOT_EQUAL operator = 4; NOT_GREATER operator = 5; NOT_LESS
	 * operator = 6; ASSIGNMENT oprerator = 7;
	 */

	public void printInfo() {
		System.out.print(leftExp.toString());
		System.out.print(" " + operator + " ");
		System.out.print(rightExp.toString());
	}

	public String toString() {
		return "(" + leftExp.toString() + " " + operator + " " + rightExp.toString() + ")";
	}

	public String toStringPrefix() {
		if (operator.equals("not"))
			return "( not " + " " + leftExp.toStringPreFix() + ")";

		String result = "(" + operator + " " + leftExp.toStringPreFix() + " " + rightExp.toStringPreFix() + ")";

		if (operator.equals("=="))
			result = "( = " + " " + leftExp.toStringPreFix() + " " + rightExp.toStringPreFix() + ")";
		else if (operator.equals("!="))
			result = "(not ( =" + " " + leftExp.toStringPreFix() + " " + rightExp.toStringPreFix() + ") )";

		return result;
	}

	public Formula(Value left, String op) {
		leftExp = left;
		rightExp = null;
		// mean_op = new String[] {"Undefined", "=", ">", "<", "!=", "<=", ">=",
		// ":="};
		operator = op;
	}

	public Formula(String op, Value left) {
		leftExp = left;
		rightExp = null;
		// mean_op = new String[] {"Undefined", "=", ">", "<", "!=", "<=", ">=",
		// ":="};
		operator = op;
	}

	public Formula(Value left, Value right) {
		leftExp = left;
		rightExp = right;
		// mean_op = new String[] {"Undefined", "=", ">", "<", "!=", "<=", ">=",
		// ":="};
		operator = "";
	}

	public Formula(Value left, Value right, String op) {
		leftExp = left;
		rightExp = right;
		// mean_op = new String[] {"Undefined", "=", ">", "<", "!=", "<=", ">=",
		// ":="};
		operator = op;
	}

	public Value getLeftExp() {
		return leftExp;
	}

	public void setLeftExp(Value leftExp) {
		this.leftExp = leftExp;
	}

	public Value getRightExp() {
		return rightExp;
	}

	public void setRightExp(Value rightExp) {
		this.rightExp = rightExp;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean equal(Formula sf) {
		return this.getOperator() == sf.getOperator() && this.getLeftExp().equal(sf.getLeftExp())
				&& this.getRightExp().equal(sf.getRightExp());
	}
}
