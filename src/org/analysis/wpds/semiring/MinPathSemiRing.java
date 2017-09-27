/**
 * 
 */
package org.analysis.wpds.semiring;

import org.analysis.wpds.weight.IntegerWeight;
import org.analysis.wpds.weight.Weight;
import org.analysis.wpds.weight.WeightZero;

/**
 * @author NMHai
 * 
 */
public class MinPathSemiRing extends SemiRing {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.analysis.wpds.semiring.SemiRing#compose(org.analysis.wpds.weight.
	 * Weight, org.analysis.wpds.weight.Weight) The Combine operator is MIN
	 */
	@Override
	public Weight compose(Weight left, Weight right) {
		// TODO Auto-generated method stub
		if (left instanceof IntegerWeight && right instanceof IntegerWeight) {
			IntegerWeight l = (IntegerWeight) left;
			IntegerWeight r = (IntegerWeight) right;

			/*
			 * int l1 = l.getNumber(); int r1 = r.getNumber();
			 * 
			 * for (int i=0; i<l1; i++) ret.addWeight(l.getEnvironment(i));
			 * 
			 * for (int i=0; i<r1; i++) ret.addWeight(r.getEnvironment(i));
			 */
			return new IntegerWeight(Math.min(l.getValue(), r.getValue()));
		}

		if (left instanceof WeightZero && right instanceof IntegerWeight) {
			// EnvWeight l = (EnvWeight)left;
			IntegerWeight r = (IntegerWeight) right;
			// EnvWeight ret = new EnvWeight();
			// int l1 = l.getNumber();
			/*
			 * int r1 = r.getNumber();
			 * 
			 * for (int i=0; i<r1; i++) ret.addWeight(r.getEnvironment(i));
			 */

			return new IntegerWeight(r.getValue());
		}

		if (right instanceof WeightZero && left instanceof IntegerWeight) {
			// EnvWeight l = (EnvWeight)left;
			IntegerWeight l = (IntegerWeight) right;
			// EnvWeight ret = new EnvWeight();
			// int l1 = l.getNumber();
			/*
			 * int l1 = l.getNumber();
			 * 
			 * for (int i=0; i<l1; i++) ret.addWeight(l.getEnvironment(i));
			 */

			return new IntegerWeight(l.getValue());
		}

		if (left instanceof WeightZero && right instanceof WeightZero) {
			return new WeightZero();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.analysis.wpds.semiring.SemiRing#extend(org.analysis.wpds.weight.Weight
	 * , org.analysis.wpds.weight.Weight)
	 * 
	 * The Extend operator is ADDITION
	 */
	@Override
	public Weight extend(Weight left, Weight right) {
		// TODO Auto-generated method stub
		if (left instanceof IntegerWeight && right instanceof IntegerWeight) {
			IntegerWeight l = (IntegerWeight) left;
			IntegerWeight r = (IntegerWeight) right;

			/*
			 * int l1 = l.getNumber(); int r1 = r.getNumber();
			 * 
			 * for (int i=0; i<l1; i++) ret.addWeight(l.getEnvironment(i));
			 * 
			 * for (int i=0; i<r1; i++) ret.addWeight(r.getEnvironment(i));
			 */
			return new IntegerWeight(l.getValue() + r.getValue());
		}

		if (left instanceof WeightZero && right instanceof IntegerWeight) {
			// EnvWeight l = (EnvWeight)left;
			IntegerWeight r = (IntegerWeight) right;
			// EnvWeight ret = new EnvWeight();
			// int l1 = l.getNumber();
			/*
			 * int r1 = r.getNumber();
			 * 
			 * for (int i=0; i<r1; i++) ret.addWeight(r.getEnvironment(i));
			 */

			return new IntegerWeight(r.getValue());
		}

		if (right instanceof WeightZero && left instanceof IntegerWeight) {
			// EnvWeight l = (EnvWeight)left;
			IntegerWeight l = (IntegerWeight) right;
			// EnvWeight ret = new EnvWeight();
			// int l1 = l.getNumber();
			/*
			 * int l1 = l.getNumber();
			 * 
			 * for (int i=0; i<l1; i++) ret.addWeight(l.getEnvironment(i));
			 */

			return new IntegerWeight(l.getValue());
		}

		if (left instanceof WeightZero && right instanceof WeightZero) {
			return new WeightZero();
		}

		return null;
	}

}
