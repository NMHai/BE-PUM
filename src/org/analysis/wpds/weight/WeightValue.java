/**
 * 
 */
package org.analysis.wpds.weight;

/**
 * @author NMHai
 * 
 */
public abstract class WeightValue {
	public abstract String toString();

	public abstract WeightValue add(WeightValue v);

	public abstract WeightValue sub(WeightValue v);

	public abstract WeightValue mul(WeightValue v);

	public abstract WeightValue div(WeightValue v);

	public abstract boolean equal(WeightValue v);
}
