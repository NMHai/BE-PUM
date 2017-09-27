package org.analysis.concrete_execution;

public class AnyValue {
	private String varName = "Any";

	public String getName() {
		// TODO Auto-generated method stub
		return "Any";
	}

	public AnyValue() {
		super();
	}

	public boolean equal(Object e) {
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

	public void setVarName(String varName) {
		this.varName = varName;
	}

}
