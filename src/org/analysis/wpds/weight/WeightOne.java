/**
 * 
 */
package org.analysis.wpds.weight;

/**
 * This class is the description of Weight 1
 * 
 * @author NMHai
 * 
 */
public class WeightOne extends Weight {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Weight 1";
	}

	// 1 = 1
	@Override
	public boolean equal(Weight aw) {
		// TODO Auto-generated method stub
		return aw instanceof WeightOne;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public AbstractWeight combine(AbstractWeight aw) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * //x * 1 = x
	 * 
	 * @Override public AbstractWeight extend(AbstractWeight aw) { // TODO
	 * Auto-generated method stub return aw; }
	 */

}
