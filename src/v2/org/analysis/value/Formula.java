package v2.org.analysis.value;

import java.util.ArrayList;
import java.util.List;

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

	public boolean isBoolean() {
		return (rightExp == null && leftExp != null && leftExp instanceof BooleanValue)
				|| (leftExp == null && rightExp != null && rightExp instanceof BooleanValue);
	}

	public boolean getBooleanValue() {
		if (leftExp != null && leftExp instanceof BooleanValue)
			return ((BooleanValue) leftExp).getValue();

		if (rightExp != null && rightExp instanceof BooleanValue)
			return ((BooleanValue) rightExp).getValue();

		return false;
	}

	public void printInfo() {
		System.out.print(leftExp.toString());
		System.out.print(" " + operator + " ");
		System.out.print(rightExp.toString());
	}

	public String toString() {
		if (operator.equals("not"))
			return "( not " + leftExp.toString() + ")";
		if (operator.equals(""))
			return "( " + " " + leftExp.toString() + ")";

		return "(" + leftExp.toString() + " " + operator + " " + rightExp.toString() + ")";
	}

	public String toStringPrefix() {
		if (operator.equals("not"))
			return "( not " + " " + leftExp.toStringPreFix() + ")";
		if (operator.equals(""))
			return "( " + " " + leftExp.toStringPreFix() + ")";

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
		return this.getOperator() == sf.getOperator()
				&& this.getLeftExp() != null
				&& sf.getLeftExp() != null
				&& this.getLeftExp().equal(sf.getLeftExp())
				&& ((this.getRightExp() != null && sf.getRightExp() != null && this.getRightExp().equal(
						sf.getRightExp())) || (this.getRightExp() == null && sf.getRightExp() == null));
	}

	public Formula clone() {
		if (operator.equals("not") || operator.equals(""))
			return new Formula(leftExp.clone(), operator);

		return new Formula(leftExp.clone(), rightExp.clone(), operator);
	}

	public Formula evaluate() {
		// TODO Auto-generated method stub
		if (operator.equals("not")) {
			if (leftExp instanceof BooleanValue) {
				boolean l = ((BooleanValue) leftExp).getValue();
				return new Formula(new BooleanValue(!l), "");
			}
		}
		if (operator.equals("")) {
			return this;
		}

		if (leftExp instanceof BooleanValue && rightExp instanceof BooleanValue) {
			boolean l = ((BooleanValue) leftExp).getValue();
			boolean r = ((BooleanValue) rightExp).getValue();

			if (operator.equals("and"))
				return new Formula(new BooleanValue(l & r), "");

			if (operator.equals("or"))
				return new Formula(new BooleanValue(l | r), "");

			if (operator.equals("xor"))
				return new Formula(new BooleanValue(l ^ r), "");

			if (operator.equals("="))
				return new Formula(new BooleanValue(l = r), "");
		}

		return this;
	}

	public List<String> getVariable() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		if (leftExp != null) {
			List<String> temp = leftExp.getVariable();
			if (temp != null)
				result.addAll(temp);
		}

		if (rightExp != null) {
			List<String> temp = rightExp.getVariable();
			if (temp != null)
				result.addAll(temp);
		}

		return result;
	}
}
