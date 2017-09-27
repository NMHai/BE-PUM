package v2.org.analysis.value;

import v2.org.analysis.structure.BitVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridBooleanValue implements Value {
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

	public HybridBooleanValue(Value left, int con, Value right) {
		super();
		this.left = left;
		this.connector = con;
		this.right = right;
	}

	public HybridBooleanValue(Value left, String con, Value right) {
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

	public Value evaluate() {
		// TODO Auto-generated method stub
		if (left instanceof BooleanValue && right instanceof BooleanValue) {
			boolean l = ((BooleanValue) left).getValue();
			boolean r = ((BooleanValue) right).getValue();

			if (getConnector().equals("and"))
				return new BooleanValue(l & r);

			if (getConnector().equals("or"))
				return new BooleanValue(l | r);

			if (getConnector().equals("xor"))
				return new BooleanValue(l ^ r);

			if (getConnector().equals("="))
				return new BooleanValue(l == r);

			if (getConnector().equals("=="))
				return new BooleanValue(l == r);

			if (getConnector().equals("!="))
				return new BooleanValue(l != r);
		}

		if (left instanceof LongValue && right instanceof LongValue) {
			long l = ((LongValue) left).getValue();
			long r = ((LongValue) right).getValue();

			if (getConnector().equals("=="))
				return new BooleanValue(l == r);

			if (getConnector().equals(">"))
				return new BooleanValue(l > r);

			if (getConnector().equals("<"))
				return new BooleanValue(l < r);

			if (getConnector().equals(">="))
				return new BooleanValue(l >= r);

			if (getConnector().equals("<="))
				return new BooleanValue(l <= r);
		}

		return this;
	}

	public HybridBooleanValue(Value getsFlag, Value getoFlag, String string) {
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
		for (int i = 0; i < HybridBooleanValue.MAX_CON; i++)
			if (this.con_mean[i].equals(con)) {
				this.connector = i;
				return;
			}
	}

	@Override
	public String toString() {
		String conn = getConnector();
		if (conn.equals("not")) {
			return "(" + conn + " " + left.toString() + ")";
		}
		return "(" + left.toString() + " " + this.getConnector() + " " + right.toString() + ")";
	}

	public String toStringZ3() {
		String conn = getConnector();
		if (conn.equals("not")) {
			return "(" + this.getConnector() + " " + left.toString() + ")";
		}
		return "( " + this.getConnector() + " " + left.toString() + " " + right.toString() + ")";
	}

	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new SymbolValue(((SymbolValue) exp).getVarName());
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(((HybridBooleanValue) exp).getLeft(),
					((HybridBooleanValue) exp).getConnector(), ((HybridBooleanValue) exp).getRight());
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value addFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "+", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "+", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "+", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	public HybridBooleanValue clone() {
		return new HybridBooleanValue(left.clone(), connector, right.clone());
	}

	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "-", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "-", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "-", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp, int size) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "/", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "/", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "/", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedDivFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "/", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "/", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "/", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "and", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "and", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "and", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "or", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "or", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "or", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return this.clone();
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	static int k = 0;
	@Override
	public String toStringPreFix() {
//		 k++;
//		 System.err.println(" - k=" + k);
//		 if (k == 7941) {
//			 System.err.println("DEBUG");
//		 }
		// TODO Auto-generated method stub
		String conn = getConnector();
		if (conn.equals("!="))
			return "(not(=" + " " + left.toStringPreFix() + " " + right.toStringPreFix() + "))";
		else if (conn.equals("not"))
			return "(" + getBVConnector() + " (" + left.toStringPreFix() + "))";
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
	public Value evaluate(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		left.setValueMap(z3Value);
		right.setValueMap(z3Value);
		Value l = left.evaluate(z3Value);

		if ((l == null) || (!(l instanceof BooleanValue) && !(l instanceof LongValue)))
			return this;

		Value r = right.evaluate(z3Value);
		if (r == null || (!(r instanceof BooleanValue) && !(r instanceof LongValue)))
			return this;

		if (l instanceof BooleanValue && r instanceof BooleanValue) {
			BooleanValue l1 = (BooleanValue) l;
			BooleanValue r1 = (BooleanValue) r;

			if (getConnector().equals("and"))
				return new BooleanValue(l1.getValue() & r1.getValue());

			if (getConnector().equals("or"))
				return new BooleanValue(l1.getValue() | r1.getValue());

			if (getConnector().equals("xor"))
				return new BooleanValue(l1.getValue() ^ r1.getValue());
		}

		if (getConnector().equals(">"))
			return new BooleanValue(BitVector.gt(((LongValue) l).getValue(), ((LongValue) r).getValue()));
		
		if (getConnector().equals("=="))
			return new BooleanValue(((LongValue) l).getValue() == ((LongValue) r).getValue());

		if (getConnector().equals(">="))
			return new BooleanValue(BitVector.ge(((LongValue) l).getValue(), ((LongValue) r).getValue()));

		if (getConnector().equals("<"))
			return new BooleanValue(BitVector.lt(((LongValue) l).getValue(), ((LongValue) r).getValue()));

		if (getConnector().equals("<="))
			return new BooleanValue(BitVector.le(((LongValue) l).getValue(), ((LongValue) r).getValue()));

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
		return null;
	}

	public List<String> getVariable() {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		if (left != null) {
			List<String> temp = left.getVariable();
			if (temp != null)
				result.addAll(temp);
		}

		if (right != null) {
			List<String> temp = right.getVariable();
			if (temp != null)
				result.addAll(temp);
		}

		return result;
	}

	@Override
	public Value modFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "%", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "%", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "%", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value powFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridBooleanValue(this.clone(), "^", exp);
		else if (exp instanceof SymbolValue)
			result = new HybridBooleanValue(this.clone(), "^", exp);
		else if (exp instanceof HybridBooleanValue)
			result = new HybridBooleanValue(this.clone(), "^", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}
}
