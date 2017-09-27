/**
 * 
 */
package org.analysis.wpds;

import org.analysis.wpds.semiring.SemiRing;
import org.analysis.wpds.weight.Weight;
import v2.org.analysis.cfg.AddressList;

import java.util.List;

/**
 * @author NMHai
 * 
 */
public abstract class MOPV {
	private State target;
	private List<AddressList> trace;
	private List<WConf> confList;
	private Weight initial;
	private SemiRing sr;

	public abstract Weight computeMOPV();

	/**
	 * @return the target
	 */
	public State getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(State target) {
		this.target = target;
	}

	/**
	 * @return the trace
	 */
	public List<AddressList> getTrace() {
		return trace;
	}

	/**
	 * @param trace
	 *            the trace to set
	 */
	public void setTrace(List<AddressList> trace) {
		this.trace = trace;
	}

	/**
	 * @return the confList
	 */
	public List<WConf> getConfigurationList() {
		return confList;
	}

	/**
	 * @param confList
	 *            the confList to set
	 */
	public void setConfigurationList(List<WConf> confList) {
		this.confList = confList;
	}

	/**
	 * @return the initial
	 */
	public Weight getInitialWeight() {
		return initial;
	}

	/**
	 * @param initial
	 *            the initial to set
	 */
	public void setInitialWeight(Weight initial) {
		this.initial = initial;
	}

	/**
	 * @return the sr
	 */
	public SemiRing getSemiRing() {
		return sr;
	}

	/**
	 * @param sr
	 *            the sr to set
	 */
	public void setSemiRing(SemiRing sr) {
		this.sr = sr;
	}
}
