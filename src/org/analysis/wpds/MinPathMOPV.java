/**
 * 
 */
package org.analysis.wpds;

import org.analysis.wpds.semiring.MinPathSemiRing;
import org.analysis.wpds.weight.IntegerWeight;
import org.analysis.wpds.weight.Weight;
import org.jakstab.asm.AbsoluteAddress;
import v2.org.analysis.cfg.AddressList;

import java.util.List;

/**
 * @author NMHai
 * 
 */
public class MinPathMOPV extends MOPV {

	public MinPathMOPV(IntegerWeight w, MinPathSemiRing sr) {
		super();
		setInitialWeight(w);
		setSemiRing(sr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.analysis.wpds.MOPV#computeMOPV()
	 */
	@Override
	public Weight computeMOPV() {
		// TODO Auto-generated method stub
		List<AddressList> trace = this.getTrace();
		State target = this.getTarget();
		List<WConf> conf = this.getConfigurationList();
		IntegerWeight initial = (IntegerWeight) this.getInitialWeight();
		MinPathSemiRing sr = (MinPathSemiRing) this.getSemiRing();

		IntegerWeight ret = null;

		for (int i = 0; i < trace.size(); i++) {
			AddressList l = trace.get(i);
			if (l == null)
				break;
			IntegerWeight r = new IntegerWeight(initial.getValue());

			while (!l.isEmpty()) {
				AbsoluteAddress addr = l.pop();

				Weight w = getWeight(addr, conf);
				r = (IntegerWeight) sr.extend(r, w);
			}
			if (ret != null)
				ret = (IntegerWeight) sr.compose(ret, r);
			else
				ret = r;
		}

		System.out.println(target.printInfo());
		System.out.println("MinPath MOPV:" + ret.getValue());

		return ret;
	}

	private Weight getWeight(AbsoluteAddress addr, List<WConf> conf) {
		// TODO Auto-generated method stub
		for (int i = 0; i < conf.size(); i++) {
			WConf temp = conf.get(i);
			if (temp.getAddress().getValue() == addr.getValue())
				return temp.getWeight();
		}

		return null;
	}

}
