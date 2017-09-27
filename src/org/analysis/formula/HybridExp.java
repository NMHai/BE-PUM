package org.analysis.formula;

import v2.org.analysis.structure.BitVector;

import java.util.HashMap;
import java.util.Map;

public class HybridExp implements Value {
	private Value left, right;
	private int connector;
	public static int MAX_CON = 20;
	public Map<String, Long> valueMap = new HashMap<String, Long>();
	String[] con_mean = new String[] { "Undefined", "+", "-", "*", "/", "and", "or", "not", "xor", "=", ">", "<", ">=",
			"<=", "!=", "==", "u>", "u<", "u>=", "u<=" };

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

	public HybridExp(Value left, int con, Value right) {
		super();
		this.left = left;
		this.connector = con;
		this.right = right;
	}

	public HybridExp(Value left, String con, Value right) {
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

	public HybridExp(Value getsFlag, Value getoFlag, String string) {
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
		for (int i = 0; i < HybridExp.MAX_CON; i++)
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
		else if (exp instanceof HybridExp)
			result = new HybridExp(((HybridExp) exp).getLeft(), ((HybridExp) exp).getConnector(),
					((HybridExp) exp).getRight());
		return result;
	}

	@Override
	public Value addFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "+", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "+", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "+", exp);
		return result;
	}

	public HybridExp clone() {
		return new HybridExp(left, connector, right);
	}

	@Override
	public Value subFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "-", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "-", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "-", exp);
		return result;
	}

	@Override
	public Value mulFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "*", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "*", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "*", exp);
		return result;
	}

	@Override
	public Value divFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "/", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "/", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "/", exp);
		return result;
	}

	@Override
	public Value andFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "and", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "and", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "and", exp);
		return result;
	}

	@Override
	public Value orFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "or", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "or", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "or", exp);
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
			result = new HybridExp(this.clone(), "xor", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "xor", exp);
		return result;
	}

	@Override
	public Value rrFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "xor", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "xor", exp);
		return result;
	}

	@Override
	public Value rlFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(this.clone(), "xor", exp);
		else if (exp instanceof SymbolExp)
			result = new HybridExp(this.clone(), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(this.clone(), "xor", exp);
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
			result = "bvand";
		else if (result.equals("or"))
			result = "bvor";
		else if (result.equals("not"))
			result = "bvnot";
		else if (result.equals("xor"))
			result = "bvxor";
		else if (result.equals("+"))
			result = "bvadd";
		else if (result.equals("-"))
			result = "bvsub";
		else if (result.equals("*"))
			result = "bvmul";
		else if (result.equals("/"))
			result = "bvsmod";
		else if (result.equals("<="))
			result = "bvsle";
		else if (result.equals("<"))
			result = "bvslt";
		else if (result.equals(">="))
			result = "bvsge";
		else if (result.equals(">"))
			result = "bvsgt";
		else if (result.equals("=="))
			result = "=";
		else if (result.equals("u<="))
			result = "bvule";
		else if (result.equals("u<"))
			result = "bvult";
		else if (result.equals("u>="))
			result = "bvuge";
		else if (result.equals("u>"))
			result = "bvugt";

		return result;
	}

	@Override
	public Value evaluate() {
		// TODO Auto-generated method stub
		left.setValueMap(valueMap);
		right.setValueMap(valueMap);
		Value l = left.evaluate();
		if (!(l instanceof LongValueOld))
			return this;
		LongValueOld l1 = (LongValueOld) l;

		Value r = right.evaluate();
		if (!(r instanceof LongValueOld))
			return this;
		LongValueOld r1 = (LongValueOld) r;

		if (getConnector().equals("+"))
			return new LongValueOld(BitVector.add(r1.getValue(), l1.getValue()));

		if (getConnector().equals("-"))
			return new LongValueOld(BitVector.sub(l1.getValue(), r1.getValue()));

		if (getConnector().equals("*"))
			return new LongValueOld(BitVector.signedMul(l1.getValue(), r1.getValue()));

		if (getConnector().equals("/"))
			return new LongValueOld(BitVector.unsignedDiv(l1.getValue(), r1.getValue()));

		if (getConnector().equals("and"))
			return new LongValueOld(BitVector.and(l1.getValue(), r1.getValue()));

		if (getConnector().equals("or"))
			return new LongValueOld(BitVector.or(l1.getValue(), r1.getValue()));

		if (getConnector().equals("xor"))
			return new LongValueOld(BitVector.xor(l1.getValue(), r1.getValue()));

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
