package v2.org.analysis.olly;

import v2.org.analysis.environment.Flag;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Value;

public class OllyFlag {

	private static int CF = 0;
	private static int PF = 1;
	private static int AF = 2;
	private static int ZF = 3;
	private static int SF = 4;
	private static int DF = 5;
	private static int OF = 6;

	private long cf, pf, af, zf, sf, df, of;

	public OllyFlag(long[] flags) {
		this.cf = flags[CF];
		this.pf = flags[PF];
		this.af = flags[AF];
		this.zf = flags[ZF];
		this.sf = flags[SF];
		this.df = flags[DF];
		this.of = flags[OF];
	}

	public String compare(Flag flag) {
		String ret = "";
		Value temp = flag.getAFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.af == 1))
			ret += "AF:" + temp + " vs " + (this.af == 1) + "; ";
		temp = flag.getCFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.cf == 1))
			ret += "CF:" + temp + " vs " + (this.cf == 1) + "; ";
		temp = flag.getDFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.df == 1))
			ret += "DF:" + temp + " vs " + (this.df == 1) + "; ";
		temp = flag.getOFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.of == 1))
			ret += "OF:" + temp + " vs " + (this.of == 1) + "; ";
		temp = flag.getPFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.pf == 1))
			ret += "PF:" + temp + " vs " + (this.pf == 1) + "; ";
		temp = flag.getSFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.sf == 1))
			ret += "SF:" + temp + " vs " + (this.sf == 1) + "; ";
		temp = flag.getZFlag();
		if (temp == null || !(temp instanceof BooleanValue) || ((BooleanValue) temp).getValue() != (this.zf == 1))
			ret += "ZF:" + temp + " vs " + (this.zf == 1) + "; ";

		return ret;
	}

	/*
	 * public boolean compare(Flag flags){ long cf_value =
	 * ((LongValue)flags.getCFlag()).getValue(); long pf_value =
	 * ((LongValue)flags.getPFlag()).getValue(); long af_value =
	 * ((LongValue)flags.getAFlag()).getValue(); long zf_value =
	 * ((LongValue)flags.getZFlag()).getValue(); long sf_value =
	 * ((LongValue)flags.getSFlag()).getValue(); long df_value =
	 * ((LongValue)flags.getDFlag()).getValue(); long of_value =
	 * ((LongValue)flags.getOFlag()).getValue(); return (cf_value == this.cf) &&
	 * (pf_value == this.pf) && (af_value == this.af) && (zf_value == this.zf)
	 * && (sf_value == this.sf) && (df_value == this.df) && (of_value ==
	 * this.of); }
	 */
}
