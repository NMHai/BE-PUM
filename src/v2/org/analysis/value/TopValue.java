package v2.org.analysis.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Unknown Value
public class TopValue implements Value {

	@Override
	public Value clone() {
		return new TopValue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "TOP";
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		return "TOP";
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		return exp;
	}

	@Override
	public Value addFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value signedMulFunction(Value source, int size) {
		return this;
	}

	@Override
	public Value signedMulFunction(Value source) {
		return this;
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value signedDivFunction(Value exp) {
		return this;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		return this;
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
		return this;
	}

	@Override
	public Value rl16Function(Value s) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value rr16Function(Value s) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value rr8Function(Value s) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value powFunction(int i) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		ArrayList<String> r = new ArrayList<String>();
		r.add(getName());
		return r;
	}

	@Override
	public Value modFunction(Value v) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Value powFunction(Value v) {
		return this;
	}

}
