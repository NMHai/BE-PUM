/**
 * 
 */
package org.analysis.wpds.semiring;

import org.analysis.wpds.weight.Weight;

/**
 * This abstract class provides the description of Semiring Element (or Weight).
 * All implementation of Semiring Element must inherit from this class.
 * 
 * @author Nguyen Minh Hai
 * 
 */
public abstract class SemiRing {
	// private Weight weight;

	public abstract Weight compose(Weight left, Weight right);

	public abstract Weight extend(Weight left, Weight right);
}
