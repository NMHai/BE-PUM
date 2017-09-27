/**
 * 
 */
package org.analysis.wpds.weight;

import v2.org.analysis.structure.BitVector;

/**
 * @author NMHai
 * 
 */
public class LongWeightValue extends WeightValue {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Value#toString()
	 */
	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Value#add(org.analysis.wpds.Value)
	 */
	@Override
	public WeightValue add(WeightValue v) {
		// TODO Auto-generated method stub
		if (v == null)
			return null;

		if (v instanceof LongWeightValue) {
			long temp = ((LongWeightValue) v).getValue();
			value = BitVector.add(value, temp);
			return this;
		}

		if (v instanceof TopWeightValue) {
			return v;
		}

		if (v instanceof BottomValue) {
			return v;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Value#sub(org.analysis.wpds.Value)
	 */
	@Override
	public WeightValue sub(WeightValue v) {
		// TODO Auto-generated method stub
		if (v == null)
			return null;

		if (v instanceof LongWeightValue) {
			long temp = ((LongWeightValue) v).getValue();
			value = BitVector.sub(value, temp);
			return this;
		}

		if (v instanceof TopWeightValue) {
			return v;
		}

		if (v instanceof BottomValue) {
			return v;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Value#unsignedMul(org.analysis.wpds.Value)
	 */
	@Override
	public WeightValue mul(WeightValue v) {
		// TODO Auto-generated method stub
		if (v == null)
			return null;

		if (v instanceof LongWeightValue) {
			long temp = ((LongWeightValue) v).getValue();
			value = BitVector.signedMul(value, temp);
			return this;
		}

		if (v instanceof TopWeightValue) {
			return v;
		}

		if (v instanceof BottomValue) {
			return v;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Value#unsignedDiv(org.analysis.wpds.Value)
	 */
	@Override
	public WeightValue div(WeightValue v) {
		// TODO Auto-generated method stub
		if (v == null)
			return null;

		if (v instanceof LongWeightValue) {
			long temp = ((LongWeightValue) v).getValue();
			value = BitVector.unsignedDiv(value, temp);
			return this;
		}

		if (v instanceof TopWeightValue) {
			return v;
		}

		if (v instanceof BottomValue) {
			return v;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.Value#equal(org.analysis.wpds.Value)
	 */
	@Override
	public boolean equal(WeightValue v) {
		// TODO Auto-generated method stub
		if (v instanceof LongWeightValue) {
			long temp = ((LongWeightValue) v).getValue();
			return value == temp;
		}
		return false;
	}

}
