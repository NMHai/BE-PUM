package org.analysis.formula;

import java.util.Map;

public class AnyExp implements Value {
	private String varName = "AnyTime";

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return varName;
	}

	public AnyExp() {
		super();
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		/*
		 * if (e instanceof SymbolExp) return varName.equals(((SymbolExp)
		 * e).getVarName()); return false;
		 */
		return true;
	}

	public String getVarName() {
		return varName;
	}

	@Override
	public String toString() {
		return varName;
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
			result = new HybridExp(new SymbolExp(varName), "+", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "+", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "+", exp);
		return result;
	}

	@Override
	public Value subFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "-", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "-", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "-", exp);
		return result;
	}

	@Override
	public Value mulFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "*", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "*", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "*", exp);
		return result;
	}

	@Override
	public SymbolExp clone() {
		return new SymbolExp(varName);
	}

	@Override
	public Value divFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "/", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "/", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "/", exp);
		return result;
	}

	@Override
	public Value andFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "and", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "and", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "and", exp);
		return result;
	}

	@Override
	public Value orFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "or", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "or", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "or", exp);
		return result;
	}

	@Override
	public Value notFunc() {
		// TODO Auto-generated method stub
		return new SymbolExp(varName);
	}

	@Override
	public Value xorFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "xor", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "xor", exp);
		return result;
	}

	@Override
	public Value rrFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "xor", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "xor", exp);
		return result;
	}

	@Override
	public Value rlFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValueOld)
			result = new HybridExp(new SymbolExp(varName), "xor", new LongValueOld(((LongValueOld) exp).getValue()));
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new SymbolExp(varName), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new SymbolExp(varName), "xor", exp);
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		String var = new String(varName);
		/*
		 * if (var.startsWith("%")) var = var.substring(1); else if
		 * (var.startsWith("0x")) var = "op_addr_" + var; else if
		 * (var.startsWith("(%")) var = "op_addr_base_" + var.substring(2,
		 * var.length() - 1);
		 */

		if (var.startsWith("%") && !var.contains(":(%"))
			var = var.substring(1);
		else if (var.startsWith("0x"))
			if (var.contains(",")) {
				int pos1 = var.lastIndexOf("(");
				int pos2 = var.lastIndexOf(",");
				var = "op_addr_base2_disp_" + var.substring(0, pos1) + "_" + var.substring(pos1 + 2, pos2) + "_"
						+ var.substring(pos2 + 2, var.length() - 1);
			} else if (var.contains("(%")) {
				// String t[] = var.split("(");
				int pos = var.lastIndexOf("(");
				var = "op_addr_base_disp_" + var.substring(0, pos) + "_" + var.substring(pos + 2, var.length() - 1);
			} else
				var = "op_addr_disp_" + var;
		else if (var.startsWith("(%")) {
			if (var.contains(",")) {
				int pos = var.lastIndexOf(",");
				var = "op_addr_base_index_" + var.substring(2, pos - 1) + "_"
						+ var.substring(pos + 2, var.length() - 1);
			} else
				var = "op_addr_base_" + var.substring(2, var.length() - 1);
		} else if (var.startsWith("%") && var.contains(":(%")) {
			int pos = var.lastIndexOf(":");
			var = "op_addr_base_index_disp_" + var.substring(1, pos) + "_" + var.substring(pos + 3, var.length() - 1);
		} else if (var.contains(",") && var.contains("(%")) {
			int pos1 = var.lastIndexOf("(");
			int pos2 = var.lastIndexOf(",");
			var = "op_addr_base_index_base_" + var.substring(0, pos1 - 1) + "_" + var.substring(pos1 + 2, pos2) + "_"
					+ var.substring(pos2 + 2, var.length() - 1);
		}

		return var;
	}

	@Override
	public Value evaluate() {
		// TODO Auto-generated method stub
		return null;
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
