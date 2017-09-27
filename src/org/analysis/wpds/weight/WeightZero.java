/**
 * 
 */
package org.analysis.wpds.weight;

/**
 * This class provides the description of Weight 0
 * 
 * @author NMHai
 * 
 */
public class WeightZero extends Weight {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Weight 0";
	}

	// 0 = 0
	@Override
	public boolean equal(Weight aw) {
		// TODO Auto-generated method stub
		return aw instanceof WeightZero;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub

	}

	/*
	 * //0 + x = 0
	 * 
	 * @Override public AbstractWeight combine(AbstractWeight aw) { // TODO
	 * Auto-generated method stub return aw; }
	 * 
	 * @Override public AbstractWeight extend(AbstractWeight aw) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
