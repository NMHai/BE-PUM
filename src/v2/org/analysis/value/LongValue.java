package v2.org.analysis.value;

import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;

import java.util.List;
import java.util.Map;

public class LongValue implements Value {
	private long value;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Concrete Value";
	}

	public LongValue(long value) {
		super();
		this.value = value;
	}

	public LongValue(double value) {
		super();
		this.value = (long) value;
	}

	public LongValue(long intValue, int bitCount) {
		// TODO Auto-generated constructor stub
		super();
		if (bitCount == 8)
			this.value = intValue & 0xFF;
		else if (bitCount == 16)
			this.value = intValue & 0xFFFF;
		else
			this.value = intValue;
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof LongValue)
			return value == ((LongValue) e).getValue();
		return false;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return Convert.longToHex(value) + "";
		// return value + "";
	}

	@Override
	public LongValue clone() {
		return new LongValue(value);
	}

	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new SymbolValue(((SymbolValue) exp).getVarName());
		else if (exp instanceof HybridValue)
			result = new HybridValue(((HybridValue) exp).getLeft(), ((HybridValue) exp).getConnector(),
					((HybridValue) exp).getRight());
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value addFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value + ((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new HybridValue(exp, "+", new LongValue(this.value));
		else if (exp instanceof HybridValue)
			result = new HybridValue(exp, "+", new LongValue(this.value));
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value - ((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "-", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "-", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(BitVector.unsignedMul(value, ((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "*", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp, int size) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(BitVector.signedMul(value, ((LongValue) exp).getValue(), size));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "*", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(BitVector.signedMul(value, ((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "*", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			if (((LongValue) exp).getValue() == 0)
				return null;
			result = new LongValue(BitVector.unsignedDiv(value, ((LongValue) exp).getValue()));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "/", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "/", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedDivFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue) {
			if (((LongValue) exp).getValue() == 0)
				return null;
			result = new LongValue(BitVector.signedDiv(value, ((LongValue) exp).getValue()));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "/", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "/", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value & ((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "and", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "and", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value | ((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "or", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "or", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return new LongValue(~value);
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new LongValue(value ^ ((LongValue) exp).getValue());
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "xor", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			long l1 = ((LongValue) exp).getValue();
			result = new LongValue(BitVector.rr(value, l1));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "rr", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "rr", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			long l1 = ((LongValue) exp).getValue();
			result = new LongValue(BitVector.rl(value, l1));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "rl", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "rl", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	public Value rl8Function(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			long l1 = ((LongValue) exp).getValue();
			result = new LongValue(BitVector.rl8(value, l1));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "rl", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "rl", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
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
	public Value evaluate(Map<String, Long> z3Value) {
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

	@Override
	public Value rl16Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rr16Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rr8Function(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			long l1 = ((LongValue) exp).getValue();
			result = new LongValue(BitVector.rr8(value, l1));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "rr", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "rr", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value powFunction(int i) {
		// TODO Auto-generated method stub
		return new LongValue(BitVector.pow(i, value));
	}

	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value modFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			if (((LongValue) exp).getValue() == 0)
				return null;

			result = new LongValue(value % ((LongValue) exp).getValue());
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "mod", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "mod", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;

	}

	@Override
	public Value powFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue) {
			if (((LongValue) exp).getValue() == 0)
				return new LongValue(1);

			result = new LongValue((long) Math.pow(value, ((LongValue) exp).getValue()));
		} else if (exp instanceof SymbolValue)
			result = new HybridValue(new LongValue(this.value), "^", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new LongValue(this.value), "^", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

}
