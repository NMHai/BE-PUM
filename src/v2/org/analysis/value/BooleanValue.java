package v2.org.analysis.value;

import java.util.List;
import java.util.Map;

public class BooleanValue implements Value {
	private boolean value;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Constant";
	}

	public BooleanValue(boolean value) {
		super();
		this.value = value;
	}

	public BooleanValue(int i) {
		// TODO Auto-generated constructor stub
		this.value = (i != 0);
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof BooleanValue) {
			return value == ((BooleanValue) e).getValue();
		}
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
	public BooleanValue clone() {
		return new BooleanValue(value);
	}

	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanValue) {
			result = new BooleanValue(((BooleanValue) exp).getValue());
		} else if (exp instanceof SymbolValue) {
			result = new SymbolValue(((SymbolValue) exp).getVarName());
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(((HybridValue) exp).getLeft(), ((HybridValue) exp).getConnector(),
					((HybridValue) exp).getRight());
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value addFunction(Value exp) {
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
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		if (exp instanceof BooleanValue) {
			boolean temp = ((BooleanValue)exp).getValue();
			if (temp != value) {
				return new BooleanValue(1);
			} else {
				return new BooleanValue(0);
			}						
		}
		
		/*
		 * if (exp instanceof ConstantBooleanExp) result = new
		 * ConstantBooleanExp(value || ((ConstantBooleanExp)
		 * exp).getValueOperand()); else if (exp instanceof VarExp) result = new
		 * OtherExp(new ConstantBooleanExp(this.value), "-", exp); else if (exp
		 * instanceof OtherExp) result = new OtherExp(new
		 * ConstantBooleanExp(this.value), "-", exp);
		 */
		return null;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
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
	public Value signedMulFunction(Value exp, int size) {
		return null;
	}

	@Override
	public Value signedMulFunction(Value source) {
		return null;
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
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
	public Value signedDivFunction(Value exp) {
		return null;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanValue) {
			result = new BooleanValue(value & ((BooleanValue) exp).getValue());
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(new BooleanValue(this.value), "and", exp);
		} else if (exp instanceof LongValue) {
			result = new HybridValue(new BooleanValue(this.value), "and", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(new BooleanValue(this.value), "and", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanValue) {
			result = new BooleanValue(value | ((BooleanValue) exp).getValue());
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(new BooleanValue(this.value), "or", exp);
		} else if (exp instanceof LongValue) {
			result = new HybridValue(new BooleanValue(this.value), "or", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(new BooleanValue(this.value), "or", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return new BooleanValue(!value);
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanValue) {
			result = new BooleanValue(value ^ ((BooleanValue) exp).getValue());
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof LongValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanValue) {
			result = new BooleanValue(value ^ ((BooleanValue) exp).getValue());
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof LongValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof BooleanValue) {
			result = new BooleanValue(value ^ ((BooleanValue) exp).getValue());
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof LongValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(new BooleanValue(this.value), "xor", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		return value + "";
		// return (value) ? ("bv" + 1 + "[32]") : ("bv" + 0 + "[32]");
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
	public Value rl8Function(Value s) {
		// TODO Auto-generated method stub
		return null;
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
	public Value rr8Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value powFunction(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value modFunction(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value powFunction(Value v) {
		return null;
	}

}
