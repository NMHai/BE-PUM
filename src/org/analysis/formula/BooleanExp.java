package org.analysis.formula;

import java.util.Map;

public class BooleanExp implements Value {
	private boolean value;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Constant";
	}

	public BooleanExp(boolean value) {
		super();
		this.value = value;
	}

	public BooleanExp(int i) {
		// TODO Auto-generated constructor stub
		this.value = (i != 0);
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof BooleanExp)
			return value == ((BooleanExp) e).getValue();
		return false;
	}

	public boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public BooleanExp clone() {
		return new BooleanExp(value);
	}

	@Override
	public Value movFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanExp)
			result = new BooleanExp(((BooleanExp) exp).getValue());
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
		/*
		 * if (exp instanceof ConstantBooleanExp) result = new
		 * ConstantBooleanExp(value && ((ConstantBooleanExp)
		 * exp).getValueOperand()); else if (exp instanceof VarExp) result = new
		 * OtherExp(exp, "+", new ConstantBooleanExp(this.value)); else if (exp
		 * instanceof OtherExp) result = new OtherExp(exp, "+", new
		 * ConstantBooleanExp(this.value));
		 */
		return result;
	}

	@Override
	public Value subFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		/*
		 * if (exp instanceof ConstantBooleanExp) result = new
		 * ConstantBooleanExp(value || ((ConstantBooleanExp)
		 * exp).getValueOperand()); else if (exp instanceof VarExp) result = new
		 * OtherExp(new ConstantBooleanExp(this.value), "-", exp); else if (exp
		 * instanceof OtherExp) result = new OtherExp(new
		 * ConstantBooleanExp(this.value), "-", exp);
		 */
		return result;
	}

	@Override
	public Value mulFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		/*
		 * if (exp instanceof ConstantBooleanExp) result = new
		 * ConstantBooleanExp(value * ((ConstantBooleanExp)
		 * exp).getValueOperand()); else if (exp instanceof VarExp) result = new
		 * OtherExp(new ConstantBooleanExp(this.value), "*", exp); else if (exp
		 * instanceof OtherExp) result = new OtherExp(new
		 * ConstantBooleanExp(this.value), "*", exp);
		 */
		return result;
	}

	@Override
	public Value divFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		/*
		 * if (exp instanceof ConstantBooleanExp) result = new
		 * ConstantBooleanExp(value / ((ConstantBooleanExp)
		 * exp).getValueOperand()); else if (exp instanceof VarExp) result = new
		 * OtherExp(new ConstantBooleanExp(this.value), "/", exp); else if (exp
		 * instanceof OtherExp) result = new OtherExp(new
		 * ConstantBooleanExp(this.value), "/", exp);
		 */
		return result;
	}

	@Override
	public Value andFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanExp)
			result = new BooleanExp(value & ((BooleanExp) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new BooleanExp(this.value), "and", exp);
		else if (exp instanceof LongValueOld)
			result = new HybridExp(new BooleanExp(this.value), "and", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new BooleanExp(this.value), "and", exp);
		return result;
	}

	@Override
	public Value orFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanExp)
			result = new BooleanExp(value | ((BooleanExp) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new BooleanExp(this.value), "or", exp);
		else if (exp instanceof LongValueOld)
			result = new HybridExp(new BooleanExp(this.value), "or", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new BooleanExp(this.value), "or", exp);
		return result;
	}

	@Override
	public Value notFunc() {
		// TODO Auto-generated method stub
		return new BooleanExp(!value);
	}

	@Override
	public Value xorFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanExp)
			result = new BooleanExp(value ^ ((BooleanExp) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		else if (exp instanceof LongValueOld)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		return result;
	}

	@Override
	public Value rrFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanExp)
			result = new BooleanExp(value ^ ((BooleanExp) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		else if (exp instanceof LongValueOld)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		return result;
	}

	@Override
	public Value rlFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanExp)
			result = new BooleanExp(value ^ ((BooleanExp) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		else if (exp instanceof LongValueOld)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new BooleanExp(this.value), "xor", exp);
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		return value + "";
		// return (value) ? ("bv" + 1 + "[32]") : ("bv" + 0 + "[32]");
	}

	@Override
	public Value evaluate() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Map<String, Long> getValueMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValueMap(Map<String, Long> valueMap) {
		// TODO Auto-generated method stub

	}

}
