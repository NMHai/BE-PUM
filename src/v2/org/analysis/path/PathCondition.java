package v2.org.analysis.path;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.value.Formulas;

public class PathCondition {
	private Formulas condition;
	private Environment env;
	
	public Formulas getCondition() {
		return condition;
	}
	public void setCondition(Formulas condition) {
		this.condition = condition;
	}
	public Environment getEnvironment() {
		return env;
	}
	public void setEnvironment(Environment env) {
		this.env = env;
	} 
}
