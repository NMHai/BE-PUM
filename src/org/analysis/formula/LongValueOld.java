package org.analysis.formula;

import org.analysis.complement.OldBitVector;

import java.util.Map;

public class LongValueOld implements Value {
	private long value;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Constant";
	}

	public LongValueOld(long value) {
		super();
		this.value = value;
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof LongValueOld)
			return value == ((LongValueOld) e).getValue();
		return false;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public LongValueOld clone() {
		return new LongValueOld(value);
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
			result = new LongValueOld(value + ((LongValueOld) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(exp, "+", new LongValueOld(this.value));
		else if (exp instanceof HybridExp)
			result = new HybridExp(exp, "+", new LongValueOld(this.value));
		return result;
	}

	@Override
	public Value subFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new LongValueOld(value - ((LongValueOld) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "-", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "-", exp);
		return result;
	}

	@Override
	public Value mulFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new LongValueOld(value * ((LongValueOld) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "*", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "*", exp);
		return result;
	}

	@Override
	public Value divFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld) {
			if (((LongValueOld) exp).getValue() == 0)
				return null;

			result = new LongValueOld(value / ((LongValueOld) exp).getValue());
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "/", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "/", exp);
		return result;
	}

	@Override
	public Value andFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new LongValueOld(value & ((LongValueOld) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "and", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "and", exp);
		return result;
	}

	@Override
	public Value orFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new LongValueOld(value | ((LongValueOld) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "or", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "or", exp);
		return result;
	}

	@Override
	public Value notFunc() {
		// TODO Auto-generated method stub
		return new LongValueOld(value);
	}

	@Override
	public Value xorFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld) {
			result = new LongValueOld(value ^ ((LongValueOld) exp).getValue());
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "xor", exp);
		return result;
	}

	@Override
	public Value rrFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld) {
			long l1 = ((LongValueOld) exp).getValue();
			result = new LongValueOld(OldBitVector.rr(value, l1));
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "rr", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "rr", exp);
		return result;
	}

	@Override
	public Value rlFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld) {
			long l1 = ((LongValueOld) exp).getValue();
			result = new LongValueOld(OldBitVector.rl(value, l1));
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValueOld(this.value), "rl", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValueOld(this.value), "rl", exp);
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		String result = "bv";
		if (value >= 0)
			result += value;
		else
			result += signConvert(this.value);

		return result + "[32]";
	}

	private long signConvert(long value2) {
		// TODO Auto-generated method stub
		return (long) (Math.pow(2, 32) + value2);
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
