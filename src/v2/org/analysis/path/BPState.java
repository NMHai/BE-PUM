/**
 * 
 */
package v2.org.analysis.path;

import java.util.Map;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;

/**
 * @author NMHai
 * 
 */
public class BPState {
	private Environment env;
	private AbsoluteAddress location;
	private Instruction inst;
	private Formulas pathCondition;
	private boolean feasible = true;

	/**
	 * @param env
	 * @param location
	 * @param inst
	 */
	public BPState(Environment env, AbsoluteAddress location, Instruction inst) {
		super();
		this.env = env;
		setLocation(location);
		this.inst = inst;
	}

	public BPState() {
		super();
		this.env = null;
		this.location = null;
		this.inst = null;
	}

	public Environment getEnvironement() {
		return env;
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public AbsoluteAddress getLocation() {
		return location;
	}

	public void setLocation(AbsoluteAddress location) {
		this.location = location;
		if (location != null) {
			env.getRegister().setRegisterValue("eip", new LongValue(location.getValue()));			
		} else {
			env.getRegister().setRegisterValue("eip", null);
		}
	}

	public Instruction getInstruction() {
		return inst;
	}

	public void setInstruction(Instruction inst) {
		this.inst = inst;
	}

	@Override
	public BPState clone() {
		BPState ret = new BPState();
		ret.setEnvironment(env.clone());
		ret.setFeasiblePath(feasible);
		ret.setInstruction(inst);
		if (location != null) {
			ret.setLocation(new AbsoluteAddress(location.getValue()));
		} else {
			ret.setLocation(null);
		}
		return ret;
	}

	public boolean checkFeasiblePath() {
		// TODO Auto-generated method stub
		return feasible;
	}

	/**
	 * @param fesible
	 *            the fesible to set
	 */
	public void setFeasiblePath(boolean fesible) {
		this.feasible = fesible;
	}

	public Formulas getPathCondition() {
		return pathCondition;
	}

	public void setPathCondition(Formulas pathCondition) {
		this.pathCondition = pathCondition;
	}

	public void resetValue(Map<String, Long> z3Value) {
		env.getRegister().resetValue(z3Value);
		env.getMemory().resetValue(z3Value);
		env.getFlag().resetValue(z3Value);
	}

	public void destroy() {
		env.getMemory().free();
	}
}
