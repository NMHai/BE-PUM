package v2.org.analysis.apihandle.generation.stub;

import java.util.Vector;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class ParamsList extends Vector<Variable> {
	private static final long serialVersionUID = 1L;

	@Override
	public synchronized boolean add(Variable v) {
		v.setOrder(this.size() + 1);
		return super.add(v);
	}
}
