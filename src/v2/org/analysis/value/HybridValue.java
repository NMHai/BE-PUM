package v2.org.analysis.value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import v2.org.analysis.structure.BitVector;

public class HybridValue implements Value {
	private Value left, right;
	private int connector;
	static String[] con_mean = new String[] { "Undefined", "+", "-", "*", "/", "and", "or", "not", "xor", "=", ">", "<", ">=",
			"<=", "!=", "==", "u>", "u<", "u>=", "u<=", "%", "^", "mod", "shr", "sar" };
	public static int MAX_CON = con_mean.length;
	public Map<String, Long> valueMap = new HashMap<String, Long>();


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

	public HybridValue(Value left, int con, Value right) {
		super();
		this.left = left;
		this.connector = con;
		this.right = right;
	}

	public HybridValue(Value left, boolean not) {
		super();
		this.left = left;
		if (!not) {
			setConnector("not");
		}
	}
	
	public HybridValue(Value left, String con, Value right) {
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

	public HybridValue(Value getsFlag, Value getoFlag, String string) {
		// TODO Auto-generated constructor stub
		super();
		this.left = getsFlag;
		this.setConnector(string);
		this.right = getoFlag;
	}

	/*
	 * public HybridValue(Value dest, String string, Value longValue) { // TODO
	 * Auto-generated constructor stub this(dest, longValue, string); }
	 */

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
		String t = normalizeOp(con);

		for (int i = 0; i < HybridValue.MAX_CON; i++) {
			if (this.con_mean[i].equals(t)) {
				this.connector = i;
				return;
			}
		}
	}

	private String normalizeOp(String con) {
		if (con.toLowerCase().equals("mod")) {
			return "%";
		}

		return con.toLowerCase();
	}

	@Override
	public String toString() {
		String conn = getConnector();
		if (conn.equals("not")) {
			return "(" + conn + " " + left.toString() + ")";
		}
		return "(" + left.toString() + " " + conn + " " + right.toString() + ")";
	}

	public String toStringZ3() {
		String conn = getConnector();
		if (conn.equals("not")) {
			return "(" + conn + " " + left.toString() + ")";
		}
		return "( " + conn + " " + left.toString() + " " + right.toString() + ")";
	}

	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new LongValue(((LongValue) exp).getValue());
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
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "+", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "+", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "+", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public HybridValue clone() {
		if (right == null) {
			return new HybridValue(left.clone(), connector, null);
		}

		return new HybridValue(left.clone(), connector, right.clone());
	}

	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "-", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "-", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "-", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp, int size) {
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "*", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "/", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "/", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "/", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value signedDivFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "/", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "/", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "/", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "and", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "and", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "and", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "or", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "or", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "or", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return new HybridValue(this, false);
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "xor", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		String conn = getConnector();
		if (conn.equals("!=")) {
			return "(not(=" + " " + left.toStringPreFix() + " " + right.toStringPreFix() + "))";
		} else if (conn.equals("not")){
			return "(" + getBVConnector() + " ( " + left.toStringPreFix() + " ))";
		}
		return "(" + getBVConnector() + " " + left.toStringPreFix() + " " + right.toStringPreFix() + ")";
	}

	private String getBVConnector() {
		// TODO Auto-generated method stub
		String result = getConnector();
		if (result.equals("and")) {
			result = "bvand";
		} else if (result.equals("or")) {
			result = "bvor";
		} else if (result.equals("not")) {
			result = "bvnot";
		} else if (result.equals("xor")) {
			result = "bvxor";
		} else if (result.equals("mod") || result.equals("%")) {
			result = "bvsmod";
		} else if (result.equals("+")) {
			result = "bvadd";
		} else if (result.equals("-")) {
			result = "bvsub";
		} else if (result.equals("*")) {
			result = "bvmul";
		} else if (result.equals("/")) {
			result = "bvsdiv";
		} else if (result.equals("<=")) {
			result = "bvsle";
		} else if (result.equals("<")) {
			result = "bvslt";
		} else if (result.equals(">=")) {
			result = "bvsge";
		} else if (result.equals(">")) {
			result = "bvsgt";
		} else if (result.equals("==")) {
			result = "=";
		} else if (result.equals("u<=")) {
			result = "bvule";
		} else if (result.equals("u<")) {
			result = "bvult";
		} else if (result.equals("u>=")) {
			result = "bvuge";
		} else if (result.equals("u>")) {
			result = "bvugt";
		} else if (result.equals("shr")) {
			result = "bvlshr";
		} else if (result.equals("sar")) {
			result = "bvashr";
		}

		return result;
	}

	@Override
	public Value evaluate(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		setValueMap(z3Value);
		left.setValueMap(z3Value);
		right.setValueMap(z3Value);
		Value l = left.evaluate(z3Value);
		if (!(l instanceof LongValue)) {
			return this;
		}
		LongValue l1 = (LongValue) l;

		Value r = right.evaluate(z3Value);
		if (!(r instanceof LongValue)) {
			return this;
		}
		LongValue r1 = (LongValue) r;

		if (getConnector().equals("+")) {
			return new LongValue(BitVector.add(r1.getValue(), l1.getValue()));
		}

		if (getConnector().equals("-")) {
			return new LongValue(BitVector.sub(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("*")) {
			return new LongValue(BitVector.signedMul(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("/")) {
			return new LongValue(BitVector.unsignedDiv(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("mod") || getConnector().equals("%")) {
			return new LongValue(BitVector.mod(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("pow") || getConnector().equals("^")) {
			return new LongValue(BitVector.pow(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("and")) {
			return new LongValue(BitVector.and(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("or")) {
			return new LongValue(BitVector.or(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals("xor")) {
			return new LongValue(BitVector.xor(l1.getValue(), r1.getValue()));
		}

		if (getConnector().equals(">")) {
			return new BooleanValue(BitVector.gt(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		}

		if (getConnector().equals(">=")) {
			return new BooleanValue(BitVector.ge(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		}

		if (getConnector().equals("<")) {
			return new BooleanValue(BitVector.lt(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		}

		if (getConnector().equals("<=")) {
			return new BooleanValue(BitVector.le(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		}

		//---
		if (getConnector().equals("shr")) {
			return new BooleanValue(BitVector.le(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		}
		
		if (getConnector().equals("sar")) {
			return new BooleanValue(BitVector.le(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		}
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
		return new HybridValue(this, "pow", new LongValue(i));
	}

	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		if (left != null) {
			List<String> temp = left.getVariable();
			if (temp != null) {
				result.addAll(temp);
			}
		}

		if (right != null) {
			List<String> temp = right.getVariable();
			if (temp != null) {
				result.addAll(temp);
			}
		}

		return result;
	}

	@Override
	public Value modFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "%", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "%", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "%", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}

	@Override
	public Value powFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue) {
			result = new HybridValue(this, "^", exp);
		} else if (exp instanceof SymbolValue) {
			result = new HybridValue(this, "^", exp);
		} else if (exp instanceof HybridValue) {
			result = new HybridValue(this, "^", exp);
		} else if (exp instanceof TopValue) {
			return new TopValue();
		}
		return result;
	}
}