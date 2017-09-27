/**
 * 
 */
package org.analysis.wpds.weight;

/**
 * @author NMHai
 * 
 */
public class IntegerWeight extends Weight {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Weight#toString()
	 */
	private int value;

	public IntegerWeight(int v) {
		value = v;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Weight#equal(org.analysis.wpds.Weight)
	 */
	@Override
	public boolean equal(Weight aw) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Weight#printInfo()
	 */
	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		System.out.println("Value:" + value);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
