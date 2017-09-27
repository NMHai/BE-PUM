package v2.org.analysis.value;

import java.util.List;
import java.util.Map;

public interface Value {
	public String getName();

	// public int getType();

	// type = 1; LinearExpression
	public String toString();

	public String toStringPreFix();

	public boolean equal(Value e);

	public Value movFunction(Value exp);

	public Value addFunction(Value exp);

	public Value subFunction(Value exp);

	public Value unsignedMulFunction(Value exp);

	public Value signedMulFunction(Value exp, int size);

	public Value signedMulFunction(Value exp);

	public Value unsignedDivFunction(Value exp);

	public Value signedDivFunction(Value exp);

	public Value andFunction(Value exp);

	public Value orFunction(Value exp);

	public Value modFunction(Value v);

	public Value powFunction(Value v);

	public Value notFunction();

	public Value xorFunction(Value exp);

	public Value rrFunction(Value exp);

	public Value rlFunction(Value exp);

	public Value clone();

	public Value evaluate(Map<String, Long> z3Value);

	public Map<String, Long> getValueMap();

	public void setValueMap(Map<String, Long> valueMap);

	public Value rl8Function(Value s);

	public Value rl16Function(Value s);

	public Value rr16Function(Value s);

	public Value rr8Function(Value s);

	public Value powFunction(int i);

	public List<String> getVariable();

}
