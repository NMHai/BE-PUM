package org.analysis.formula;

import java.util.HashMap;
import java.util.Map;

public class HybridBooleanExp implements Value {
	private Value left, right;
	private int connector;
	public static int MAX_CON = 16;
	public Map<String, Long> valueMap = new HashMap<String, Long>();
	String[] con_mean = new String[] { "Undefined", "+", "-", "*", "/", "and", "or", "not", "xor", "=", ">", "<", ">=",
			"<=", "!=", "==" };

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		return false;
	}

	public HybridBooleanExp(Value left, int con, Value right) {
		super();
		this.left = left;
		this.connector = con;
		this.right = right;
	}

	public HybridBooleanExp(Value left, String con, Value right) {
		super();
		this.left = left;
		this.setConnector(con);
		this.right = right;
	}

	// public LinearExp(Exp left, Exp right) {
	// super();
	// this.left = left;
	// this.connector = 0;
	// this.right = right;
	// }

	public HybridBooleanExp(Value getsFlag, Value getoFlag, String string) {
		// TODO Auto-generated constructor stub
		super();
		this.left = getsFlag;
		this.setConnector(string);
		this.right = getoFlag;
	}

	public Value getLeft() {
		return left;
	}

	public void setLeft(Value left) {
		this.left = left;
	}

	public Value getRight() {
		return right;
	}

	public void setRight(Value right) {
		this.right = right;
	}

	public String getConnector() {
		return this.con_mean[this.connector];
	}

	public void setConnector(String con) {
		for (int i = 0; i < HybridBooleanExp.MAX_CON; i++)
			if (this.con_mean[i].equals(con)) {
				this.connector = i;
				return;
			}
	}

	@Override
	public String toString() {
		return "(" + left.toString() + " " + this.getConnector() + " " + right.toString() + ")";
	}

	public String toStringZ3() {
		return "( " + this.getConnector() + " " + left.toString() + " " + right.toString() + ")";
	}

	@Override
	public Value movFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new LongValueOld(((LongValueOld) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new SymbolExp(((SymbolExp) exp).getVarName());
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(((HybridBooleanExp) exp).getLeft(), ((HybridBooleanExp) exp).getConnector(),
					((HybridBooleanExp) exp).getRight());
		return result;
	}

	@Override
	public Value addFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "+", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "+", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "+", exp);
		return result;
	}

	public HybridBooleanExp clone() {
		return new HybridBooleanExp(left, connector, right);
	}

	@Override
	public Value subFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "-", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "-", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "-", exp);
		return result;
	}

	@Override
	public Value mulFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "*", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "*", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "*", exp);
		return result;
	}

	@Override
	public Value divFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "/", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "/", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "/", exp);
		return result;
	}

	@Override
	public Value andFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "and", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "and", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "and", exp);
		return result;
	}

	@Override
	public Value orFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "or", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "or", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "or", exp);
		return result;
	}

	@Override
	public Value notFunc() {
		// TODO Auto-generated method stub
		return this.clone();
	}

	@Override
	public Value xorFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		return result;
	}

	@Override
	public Value rrFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		return result;
	}

	@Override
	public Value rlFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		else if (exp instanceof HybridBooleanExp)
			result = new HybridBooleanExp(this.clone(), "xor", exp);
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		if (getConnector().equals("!="))
			return "(not(=" + " " + left.toStringPreFix() + " " + right.toStringPreFix() + "))";
		return "(" + getBVConnector() + " " + left.toStringPreFix() + " " + right.toStringPreFix() + ")";
	}

	private String getBVConnector() {
		// TODO Auto-generated method stub
		String result = getConnector();
		if (result.equals("and"))
			result = "and";
		else if (result.equals("or"))
			result = "or";
		else if (result.equals("not"))
			result = "not";
		else if (result.equals("xor"))
			result = "xor";
		else if (result.equals("+"))
			result = "+";
		else if (result.equals("-"))
			result = "-";
		else if (result.equals("*"))
			result = "*";
		else if (result.equals("/"))
			result = "/";
		else if (result.equals("<="))
			result = "<=";
		else if (result.equals("<"))
			result = "<";
		else if (result.equals(">="))
			result = ">=";
		else if (result.equals(">"))
			result = ">";
		else if (result.equals("=="))
			result = "=";

		return result;
	}

	@Override
	public Value evaluate() {
		// TODO Auto-generated method stub
		left.setValueMap(valueMap);
		right.setValueMap(valueMap);
		Value l = left.evaluate();
		if (!(l instanceof BooleanExp))
			return this;
		BooleanExp l1 = (BooleanExp) l;

		Value r = right.evaluate();
		if (!(r instanceof BooleanExp))
			return this;
		BooleanExp r1 = (BooleanExp) r;

		if (getConnector().equals("and"))
			return new BooleanExp(l1.getValue() & r1.getValue());

		if (getConnector().equals("or"))
			return new BooleanExp(l1.getValue() | r1.getValue());

		if (getConnector().equals("xor"))
			return new BooleanExp(l1.getValue() ^ r1.getValue());

		return this;
	}

	@Override
	public Map<String, Long> getValueMap() {
		// TODO Auto-generated method stub
		return this.valueMap;
	}

	@Override
	public void setValueMap(Map<String, Long> valueMap) {
		// TODO Auto-generated method stub
		this.valueMap = valueMap;
	}
}
