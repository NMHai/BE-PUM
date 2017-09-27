/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.value
 * File name: ReturnMainThreadValue.java
 * Created date: Sep 27, 2015
 * Description:
 */
package v2.org.analysis.value;

import java.util.List;
import java.util.Map;

/**
 * @author Yen Nguyen
 *
 */
public class ReturnMainThreadValue implements Value {

	public ReturnMainThreadValue() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#toStringPreFix()
	 */
	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#equal(v2.org.analysis.value.Value)
	 */
	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#movFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#addFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value addFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#subFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#unsignedMulFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#signedMulFunction(v2.org.analysis.value.Value, int)
	 */
	@Override
	public Value signedMulFunction(Value exp, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#signedMulFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value signedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#unsignedDivFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#signedDivFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value signedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#andFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#orFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#modFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value modFunction(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#powFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value powFunction(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#notFunction()
	 */
	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#xorFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#rrFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#rlFunction(v2.org.analysis.value.Value)
	 */
	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#clone()
	 */
	@Override
	public Value clone() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#evaluate(java.util.Map)
	 */
	@Override
	public Value evaluate(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#getValueMap()
	 */
	@Override
	public Map<String, Long> getValueMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#setValueMap(java.util.Map)
	 */
	@Override
	public void setValueMap(Map<String, Long> valueMap) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#rl8Function(v2.org.analysis.value.Value)
	 */
	@Override
	public Value rl8Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#rl16Function(v2.org.analysis.value.Value)
	 */
	@Override
	public Value rl16Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#rr16Function(v2.org.analysis.value.Value)
	 */
	@Override
	public Value rr16Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#rr8Function(v2.org.analysis.value.Value)
	 */
	@Override
	public Value rr8Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#powFunction(int)
	 */
	@Override
	public Value powFunction(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.value.Value#getVariable()
	 */
	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		return null;
	}

}
