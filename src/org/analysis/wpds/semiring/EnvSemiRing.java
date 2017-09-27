/**
 * 
 */
package org.analysis.wpds.semiring;

import org.analysis.wpds.weight.EnvWeight;
import org.analysis.wpds.weight.Weight;
import org.analysis.wpds.weight.WeightZero;

/**
 * This class defines the description of Environment Semiring
 * 
 * @author NMHai
 * 
 */
public class EnvSemiRing extends SemiRing {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.SemiRing#compose(org.analysis.wpds.Weight,
	 * org.analysis.wpds.Weight)
	 */
	private EnvWeight ew;

	public EnvWeight getEnvWeight() {
		return ew;
	}

	public void setEnvWeight(EnvWeight ew) {
		this.ew = ew;
	}

	public EnvSemiRing() {
		super();
		ew = new EnvWeight();
	}

	@Override
	public Weight compose(Weight left, Weight right) {
		// TODO Auto-generated method stub
		if (left instanceof EnvWeight && right instanceof EnvWeight) {
			// EnvWeight l = (EnvWeight) left;
			// EnvWeight r = (EnvWeight) right;
			EnvWeight ret = new EnvWeight();
			/*
			 * int l1 = l.getNumber(); int r1 = r.getNumber();
			 * 
			 * for (int i=0; i<l1; i++) ret.addWeight(l.getEnvironment(i));
			 * 
			 * for (int i=0; i<r1; i++) ret.addWeight(r.getEnvironment(i));
			 */
			return ret;
		}

		if (left instanceof WeightZero && right instanceof EnvWeight) {
			// EnvWeight l = (EnvWeight)left;
			// EnvWeight r = (EnvWeight) right;
			EnvWeight ret = new EnvWeight();
			// int l1 = l.getNumber();
			/*
			 * int r1 = r.getNumber();
			 * 
			 * for (int i=0; i<r1; i++) ret.addWeight(r.getEnvironment(i));
			 */

			return ret;
		}

		if (right instanceof WeightZero && left instanceof EnvWeight) {
			// EnvWeight l = (EnvWeight)left;
			// EnvWeight l = (EnvWeight) right;
			EnvWeight ret = new EnvWeight();
			// int l1 = l.getNumber();
			/*
			 * int l1 = l.getNumber();
			 * 
			 * for (int i=0; i<l1; i++) ret.addWeight(l.getEnvironment(i));
			 */

			return ret;
		}

		if (left instanceof WeightZero && right instanceof WeightZero) {
			return new WeightZero();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.SemiRing#extend(org.analysis.wpds.Weight,
	 * org.analysis.wpds.Weight)
	 */
	@Override
	public Weight extend(Weight left, Weight right) {
		// TODO Auto-generated method stub
		return null;
	}

	public void printOutput() {
		// System.out.println("Address:" + addr.toString() + " Instruction:" +
		// inst.getName());
		System.out.println("[");
		ew.printInfo();
		System.out.println("]");
	}

	public String output() {
		return "";
	}
}
