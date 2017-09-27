/**
 * 
 */
package org.analysis.wpds.weight;

/**
 * This is the abstract class for weight description
 * 
 * @author NMHai
 * 
 */
public abstract class Weight {
	private String name = "";

	public abstract String toString();

	public abstract boolean equal(Weight aw);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void printInfo();

	/*
	 * public abstract AbstractWeight combine(AbstractWeight aw);
	 * 
	 * public abstract AbstractWeight extend(AbstractWeight aw);
	 */
}
