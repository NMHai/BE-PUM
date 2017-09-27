package org.analysis.formula;

import java.util.Map;

public interface Value {
	public String getName();

	// public int getType();

	// type = 1; LinearExpression
	public String toString();

	public String toStringPreFix();

	public boolean equal(Value e);

	public Value movFunc(Value exp);

	public Value addFunc(Value exp);

	public Value subFunc(Value exp);

	public Value mulFunc(Value exp);

	public Value divFunc(Value exp);

	public Value andFunc(Value exp);

	public Value orFunc(Value exp);

	public Value notFunc();

	public Value xorFunc(Value exp);

	public Value rrFunc(Value exp);

	public Value rlFunc(Value exp);

	public Value clone();

	public Value evaluate();

	public Map<String, Long> getValueMap();

	public void setValueMap(Map<String, Long> valueMap);
}
