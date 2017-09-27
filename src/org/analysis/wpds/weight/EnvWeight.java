/**
 * 
 */
package org.analysis.wpds.weight;

/**
 * This class provides the description of Environment Weight which is used in
 * BE-PUM
 * 
 * @author NMHai
 * 
 */
public class EnvWeight extends Weight {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.AbstractWeight#toString()
	 */

	// private int num = 0;
	// private List<Environment> env = new ArrayList<Environment> ();;
	private WeightEnvironment env;

	public WeightEnvironment getEnvironment() {
		return env;
	}

	public EnvWeight(WeightEnvironment e) {
		super();
		// setNumber(1);
		env = e;
	}

	public EnvWeight() {
		// TODO Auto-generated constructor stub
		super();
		env = new WeightEnvironment();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.analysis.wpds.AbstractWeight#equal(org.analysis.wpds.AbstractWeight)
	 */
	@Override
	public boolean equal(Weight aw) {
		// TODO Auto-generated method stub
		/*
		 * if (aw instanceof EnvWeight) { EnvWeight ew = (EnvWeight) aw; }
		 */
		return false;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		env.printInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.analysis.wpds.AbstractWeight#combine(org.analysis.wpds.AbstractWeight
	 * )
	 */
	// Join operation
	// @Override
	/*
	 * public AbstractWeight combine(AbstractWeight aw) { // TODO Auto-generated
	 * method stub if (aw instanceof WeightZero) return this; return null; }
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.analysis.wpds.AbstractWeight#extend(org.analysis.wpds.AbstractWeight)
	 * 
	 * //Pointwise operation
	 * 
	 * @Override public AbstractWeight extend(AbstractWeight aw) { // TODO
	 * Auto-generated method stub if (aw instanceof WeightOne) return this;
	 * return null; }
	 */
}
