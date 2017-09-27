/**
 * 
 */
package v2.org.analysis.environment;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import v2.org.analysis.environment.stack.FPUStack;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

import com.twmacinta.util.MD5;

import v2.org.analysis.value.HybridValue;

/**
 * @author NMHai
 * 
 */
public class Register {
	private Value eax, ebx, ecx, edx, esi, edi, esp, ebp, eip, ax, ah, al, bx, bh, bl, cx, ch, cl, dx, dh, dl, si, di,
			sp, bp, ds, es, fs, cs, ss, gs;

	private Value dr0, dr1, dr2, dr3, dr4, dr5, dr6, dr7;
	private Value mm0, mm1, mm2, mm3, mm4, mm5, mm6, mm7;
	private FPUStack st;
	private String hashValue = "";
	private boolean isChanged = false;
		
	@Override
	public Register clone() {
		Register ret = new Register();
		ret.setRegisterValue("eax", eax.clone());
		ret.setRegisterValue("ax", ax.clone());
		ret.setRegisterValue("ah", ah.clone());
		ret.setRegisterValue("al", al.clone());

		ret.setRegisterValue("ebx", ebx.clone());
		ret.setRegisterValue("bx", bx.clone());
		ret.setRegisterValue("bh", bh.clone());
		ret.setRegisterValue("bl", bl.clone());

		ret.setRegisterValue("ecx", ecx.clone());
		ret.setRegisterValue("cx", cx.clone());
		ret.setRegisterValue("ch", ch.clone());
		ret.setRegisterValue("cl", cl.clone());

		ret.setRegisterValue("edx", edx.clone());
		ret.setRegisterValue("dx", dx.clone());
		ret.setRegisterValue("dh", dh.clone());
		ret.setRegisterValue("dl", dl.clone());

		ret.setRegisterValue("esi", esi.clone());
		ret.setRegisterValue("si", si.clone());

		ret.setRegisterValue("edi", edi.clone());
		ret.setRegisterValue("di", di.clone());

		if (esp != null) {
			ret.setRegisterValue("esp", esp.clone());
		} else {
			ret.setRegisterValue("esp", new SymbolValue("esp"));
		}
		ret.setRegisterValue("sp", sp.clone());

		if (ebp != null) {
			ret.setRegisterValue("ebp", ebp.clone());
		} else {
			ret.setRegisterValue("ebp", new SymbolValue("ebp"));
		}
		ret.setRegisterValue("bp", bp.clone());

		ret.setRegisterValue("eip", eip.clone());
		ret.setRegisterValue("cs", cs.clone());
		ret.setRegisterValue("ds", ds.clone());
		ret.setRegisterValue("es", es.clone());
		ret.setRegisterValue("fs", fs.clone());
		ret.setRegisterValue("ss", ss.clone());
		ret.setRegisterValue("gs", gs.clone());
		
		ret.setRegisterValue("dr0", dr0.clone());
		ret.setRegisterValue("dr1", dr1.clone());
		ret.setRegisterValue("dr2", dr2.clone());
		ret.setRegisterValue("dr3", dr3.clone());
		ret.setRegisterValue("dr4", dr4.clone());
		ret.setRegisterValue("dr5", dr5.clone());
		ret.setRegisterValue("dr6", dr6.clone());
		ret.setRegisterValue("dr7", dr7.clone());
		
		ret.setRegisterValue("mm0", mm0.clone());
		ret.setRegisterValue("mm1", mm1.clone());
		ret.setRegisterValue("mm2", mm2.clone());
		ret.setRegisterValue("mm3", mm3.clone());
		ret.setRegisterValue("mm4", mm4.clone());
		ret.setRegisterValue("mm5", mm5.clone());
		ret.setRegisterValue("mm6", mm6.clone());
		ret.setRegisterValue("mm7", mm7.clone());
		
		return ret;
	}

	public Register() {
		eax = new SymbolValue("eax");
		ax = new SymbolValue("ax");
		ah = new SymbolValue("ah");
		al = new SymbolValue("al");

		ebx = new SymbolValue("ebx");
		bx = new SymbolValue("bx");
		bh = new SymbolValue("bh");
		bl = new SymbolValue("bl");

		ecx = new SymbolValue("ecx");
		cx = new SymbolValue("cx");
		ch = new SymbolValue("ch");
		cl = new SymbolValue("cl");

		edx = new SymbolValue("edx");
		dx = new SymbolValue("dx");
		dh = new SymbolValue("dh");
		dl = new SymbolValue("dl");

		esi = new SymbolValue("esi");
		si = new SymbolValue("si");

		edi = new SymbolValue("edi");
		di = new SymbolValue("di");

		esp = new SymbolValue("esp");
		sp = new SymbolValue("sp");

		ebp = new SymbolValue("ebp");
		bp = new SymbolValue("bp");

		eip = new SymbolValue("eip");
		cs = new SymbolValue("cs");
		ds = new SymbolValue("ds");
		es = new SymbolValue("es");
		fs = new SymbolValue("fs");
		gs = new SymbolValue("gs");
		ss = new SymbolValue("ss");
		
		dr0 = new SymbolValue("dr0");
		dr1 = new SymbolValue("dr1");
		dr2 = new SymbolValue("dr2");
		dr3 = new SymbolValue("dr3");
		dr4 = new SymbolValue("dr4");
		dr5 = new SymbolValue("dr5");
		dr6 = new SymbolValue("dr6");
		dr7 = new SymbolValue("dr7");
		
		mm0 = new SymbolValue("mm0");
		mm1 = new SymbolValue("mm1");
		mm2 = new SymbolValue("mm2");
		mm3 = new SymbolValue("mm3");
		mm4 = new SymbolValue("mm4");
		mm5 = new SymbolValue("mm5");
		mm6 = new SymbolValue("mm6");
		mm7 = new SymbolValue("mm7");
		
		st = new FPUStack();
	}

	public Value getRegisterValue(String registerName) {
		String reg = checkRegisterName(registerName);

		if (reg.equals("eax")) {
			return eax;
		}
		if (reg.equals("ax")) {
			return ax;
		}
		if (reg.equals("ah")) {
			return ah;
		}
		if (reg.equals("al")) {
			return al;
		}

		if (reg.equals("ebx")) {
			return ebx;
		}
		if (reg.equals("bx")) {
			return bx;
		}
		if (reg.equals("bh")) {
			return bh;
		}
		if (reg.equals("bl")) {
			return bl;
		}

		if (reg.equals("ecx")) {
			return ecx;
		}
		if (reg.equals("cx")) {
			return cx;
		}
		if (reg.equals("ch")) {
			return ch;
		}
		if (reg.equals("cl")) {
			return cl;
		}

		if (reg.equals("edx")) {
			return edx;
		}
		if (reg.equals("dx")) {
			return dx;
		}
		if (reg.equals("dh")) {
			return dh;
		}
		if (reg.equals("dl")) {
			return dl;
		}

		if (reg.equals("esi")) {
			return esi;
		}
		if (reg.equals("si")) {
			return si;
		}

		if (reg.equals("edi")) {
			return edi;
		}
		if (reg.equals("di")) {
			return di;
		}

		if (reg.equals("esp")) {
			return esp;
		}
		if (reg.equals("sp")) {
			return sp;
		}

		if (reg.equals("ebp")) {
			return ebp;
		}
		if (reg.equals("bp")) {
			return bp;
		}

		if (reg.equals("eip")) {
			return eip;
		}

		if (reg.equals("cs")) {
			return cs;
		}
		
		if (reg.equals("ds")) {
			return ds;
		}
		
		if (reg.equals("es")) {
			return es;
		}
		
		if (reg.equals("fs")) {
			return fs;
		}
		
		if (reg.equals("gs")) {
			return gs;
		}
		
		if (reg.equals("ss")) {
			return ss;
		}

		if (reg.equals("dr0")) {
			return dr0;
		}
		
		if (reg.equals("dr1")) {
			return dr1;
		}
		
		if (reg.equals("dr2")) {
			return dr2;
		}
		
		if (reg.equals("dr3")) {
			return dr3;
		}
		
		if (reg.equals("dr4")) {
			return dr4;
		}
		
		if (reg.equals("dr5")) {
			return dr5;
		}
		
		if (reg.equals("dr6")) {
			return dr6;
		}
		
		if (reg.equals("dr7")) {
			return dr7;
		}
		
		if (reg.equals("mm0")) {
			return mm0;
		}
		
		if (reg.equals("mm1")) {
			return mm1;
		}
		
		if (reg.equals("mm2")) {
			return mm2;
		}
		
		if (reg.equals("mm3")) {
			return mm3;
		}
		
		if (reg.equals("mm4")) {
			return mm4;
		}
		
		if (reg.equals("mm5")) {
			return mm5;
		}
		
		if (reg.equals("mm6")) {
			return mm6;
		}
		
		if (reg.equals("mm7")) {
			return mm7;
		}
		
		if (reg.startsWith("st")) {
			return st.getByName(reg);
		}
		return null;
	}

	public void setRegisterValue(String registerName, Value value) {
		isChanged = true;
		String reg = checkRegisterName(registerName);
		Value v = this.normalizeValue(value, registerName);
		
		if (reg.equals("mm0")){
			mm0 = v;
		}else if (reg.equals("mm1")){
			mm1 = v;
		}else if (reg.equals("mm2")){
			mm2 = v;
		}else if (reg.equals("mm3")){
			mm3 = v;
		}else if (reg.equals("mm4")){
			mm4 = v;
		}else if (reg.equals("mm5")){
			mm5 = v;
		}else if (reg.equals("mm6")){
			mm6 = v;
		}else if (reg.equals("mm7")){
			mm7 = v;
		}else if (reg.equals("dr0")){
			dr0 = v;
		}else if (reg.equals("dr1")){
			dr1 = v;
		}else if (reg.equals("dr2")){
			dr2 = v;
		}else if (reg.equals("dr3")){
			dr3 = v;
		}else if (reg.equals("dr4")){
			dr4 = v;
		}else if (reg.equals("dr5")){
			dr5 = v;
		}else if (reg.equals("dr6")){
			dr6 = v;
		}else if (reg.equals("dr7")){
			dr7 = v;
		}else if (reg.equals("cs")) {
			cs = v;
		} else if (reg.equals("ds")) {
			ds = v;
		} else if (reg.equals("es")) {
			es = v;
		} else if (reg.equals("fs")) {
			fs = v;
		} else if (reg.equals("gs")) {
			gs = v;
		} else if (reg.equals("ss")) {
			ss = v;
		} else if (reg.startsWith("st")) {
			st.setByName(reg, v);
		}

		if (v instanceof LongValue) {
			long p = ((LongValue) v).getValue();
			if (reg.equals("bp")) {
				bp = v;
				Value t = getRegisterValue("ebp");
				if (t instanceof LongValue) {
					long t1 = ((LongValue) t).getValue();
					long t2 = (long) (t1 / Math.pow(2, 16));
					ebp = new LongValue((long) (t2 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("sp")) {
				sp = v;
				Value t = getRegisterValue("esp");
				if (t instanceof LongValue) {
					long t1 = ((LongValue) t).getValue();
					long t2 = (long) (t1 / Math.pow(2, 16));
					esp = new LongValue((long) (t2 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("si")) {
				si = v;
				Value t = getRegisterValue("esi");
				if (t instanceof LongValue) {
					long t1 = ((LongValue) t).getValue();
					long t2 = (long) (t1 / Math.pow(2, 16));
					esi = new LongValue((long) (t2 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("di")) {
				di = v;
				Value t = getRegisterValue("edi");
				if (t instanceof LongValue) {
					long t1 = ((LongValue) t).getValue();
					long t2 = (long) (t1 / Math.pow(2, 16));
					edi = new LongValue((long) (t2 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("ax")) {
				ax = v;
				Value t = getRegisterValue("eax");
				ah = new LongValue((long) (p / Math.pow(2, 8)));
				al = new LongValue((long) (p % Math.pow(2, 8)));

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					eax = new LongValue((long) (t1 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("ah")) {
				ah = v;
				Value t = getRegisterValue("eax");

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					long t2 = h % (long) Math.pow(2, 16);
					long t3 = t2 % (long) Math.pow(2, 8);
					ax = new LongValue((long) (p * Math.pow(2, 8) + t3));
					// al = new LongValue(t3);
					eax = new LongValue((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
				} else {
					Value g = getRegisterValue("al");

					if (g instanceof LongValue) {
						long al = ((LongValue) g).getValue();
						ax = new LongValue((long) (p * Math.pow(2, 8) + al));
					}
				}
			} else if (reg.equals("al")) {
				p = p & 0xFF;
				al = new LongValue(p);
				Value t = getRegisterValue("eax");
				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 8));
					long t2 = (long) (t1 % Math.pow(2, 8));
					ax = new LongValue((long) (t2 * Math.pow(2, 8) + p));
					eax = new LongValue((long) (t1 * Math.pow(2, 8) + p));
				} else {
					Value g = getRegisterValue("ah");

					if (g instanceof LongValue) {
						long ah = ((LongValue) g).getValue();
						ax = new LongValue((long) (ah * Math.pow(2, 8) + p));
					}
				}
			} else if (reg.equals("bx")) {
				bx = v;
				Value t = getRegisterValue("ebx");
				bh = new LongValue((long) (p / Math.pow(2, 8)));
				bl = new LongValue((long) (p % Math.pow(2, 8)));

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					ebx = new LongValue((long) (t1 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("bh")) {
				bh = v;
				Value t = getRegisterValue("ebx");

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					long t2 = h % (long) Math.pow(2, 16);
					long t3 = t2 % (long) Math.pow(2, 8);
					bx = new LongValue((long) (p * Math.pow(2, 8) + t3));
					// al = new LongValue(t3);
					ebx = new LongValue((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
				} else {
					Value g = getRegisterValue("bl");

					if (g instanceof LongValue) {
						long bl = ((LongValue) g).getValue();
						bx = new LongValue((long) (p * Math.pow(2, 8) + bl));
					}
				}
			} else if (reg.equals("bl")) {
				bl = v;
				Value t = getRegisterValue("ebx");
				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 8));
					long t2 = (long) (t1 % Math.pow(2, 8));
					bx = new LongValue((long) (t2 * Math.pow(2, 8) + p));
					ebx = new LongValue((long) (t1 * Math.pow(2, 8) + p));
				} else {
					Value g = getRegisterValue("bh");

					if (g instanceof LongValue) {
						long bh = ((LongValue) g).getValue();
						bx = new LongValue((long) (bh * Math.pow(2, 8) + p));
					}
				}
			} else if (reg.equals("cx")) {
				cx = v;
				Value t = getRegisterValue("ecx");
				ch = new LongValue((long) (p / Math.pow(2, 8)));
				cl = new LongValue((long) (p % Math.pow(2, 8)));

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					ecx = new LongValue((long) (t1 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("ch")) {
				ch = v;
				Value t = getRegisterValue("ecx");

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					long t2 = h % (long) Math.pow(2, 16);
					long t3 = t2 % (long) Math.pow(2, 8);
					cx = new LongValue((long) (p * Math.pow(2, 8) + t3));
					// al = new LongValue(t3);
					ecx = new LongValue((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
				} else {
					Value g = getRegisterValue("cl");

					if (g instanceof LongValue) {
						long cl = ((LongValue) g).getValue();
						cx = new LongValue((long) (p * Math.pow(2, 8) + cl));
					}
				}
			} else if (reg.equals("cl")) {
				cl = v;
				Value t = getRegisterValue("ecx");
				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 8));
					long t2 = (long) (t1 % Math.pow(2, 8));
					cx = new LongValue((long) (t2 * Math.pow(2, 8) + p));
					ecx = new LongValue((long) (t1 * Math.pow(2, 8) + p));
				} else {
					Value g = getRegisterValue("ch");

					if (g instanceof LongValue) {
						long ch = ((LongValue) g).getValue();
						cx = new LongValue((long) (ch * Math.pow(2, 8) + p));
					}
				}
			} else if (reg.equals("dx")) {
				dx = v;
				Value t = getRegisterValue("edx");
				dh = new LongValue((long) (p / Math.pow(2, 8)));
				dl = new LongValue((long) (p % Math.pow(2, 8)));

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					edx = new LongValue((long) (t1 * Math.pow(2, 16) + p));
				}
			} else if (reg.equals("dh")) {
				dh = v;
				Value t = getRegisterValue("edx");

				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 16));
					long t2 = h % (long) Math.pow(2, 16);
					long t3 = t2 % (long) Math.pow(2, 8);
					dx = new LongValue((long) (p * Math.pow(2, 8) + t3));
					// al = new LongValue(t3);
					edx = new LongValue((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
				} else {
					Value g = getRegisterValue("dl");

					if (g instanceof LongValue) {
						long dl = ((LongValue) g).getValue();
						dx = new LongValue((long) (p * Math.pow(2, 8) + dl));
					}
				}
			} else if (reg.equals("dl")) {
				dl = v;
				Value t = getRegisterValue("edx");
				if (t instanceof LongValue) {
					long h = ((LongValue) t).getValue();
					long t1 = (long) (h / Math.pow(2, 8));
					long t2 = (long) (t1 % Math.pow(2, 8));
					dx = new LongValue((long) (t2 * Math.pow(2, 8) + p));
					edx = new LongValue((long) (t1 * Math.pow(2, 8) + p));
				} else {
					Value g = getRegisterValue("dh");

					if (g instanceof LongValue) {
						long dh = ((LongValue) g).getValue();
						dx = new LongValue((long) (dh * Math.pow(2, 8) + p));
					}
				}
			} else if (reg.equals("esi")) {
				si = new LongValue(p % (long) Math.pow(2, 16));
				esi = v;
			} else if (reg.equals("edi")) {
				di = new LongValue(p % (long) Math.pow(2, 16));
				edi = v;
			} else if (reg.equals("ebp")) {
				bp = new LongValue(p % (long) Math.pow(2, 16));
				ebp = v;
			} else if (reg.equals("esp")) {
				sp = new LongValue(p % (long) Math.pow(2, 16));
				esp = v;
			} else if (reg.equals("eax")) {
				long t = p % (long) Math.pow(2, 16);
				ax = new LongValue(t);
				ah = new LongValue(t / (long) Math.pow(2, 8));
				al = new LongValue(t % (long) Math.pow(2, 8));
				eax = v;
			} else if (reg.equals("ebx")) {
				long t = p % (long) Math.pow(2, 16);
				bx = new LongValue(t);
				bh = new LongValue(t / (long) Math.pow(2, 8));
				bl = new LongValue(t % (long) Math.pow(2, 8));
				ebx = v;
			} else if (reg.equals("ecx")) {
				long t = p % (long) Math.pow(2, 16);
				cx = new LongValue(t);
				ch = new LongValue(t / (long) Math.pow(2, 8));
				cl = new LongValue(t % (long) Math.pow(2, 8));
				ecx = v;
			} else if (reg.equals("edx")) {
				long t = p % (long) Math.pow(2, 16);
				dx = new LongValue(t);
				dh = new LongValue(t / (long) Math.pow(2, 8));
				dl = new LongValue(t % (long) Math.pow(2, 8));
				edx = v;
			}
		} else {
			// Other Cases
			LongValue WORD = new LongValue((long) Math.pow(2, 16));
			LongValue BYTE = new LongValue((long) Math.pow(2, 8));
			if (reg.equals("eax")) {
				eax = v;
//				al = v;
//				ah = v;
//				ax = v;
				al = new HybridValue(v, "mod", BYTE);
				ah = new HybridValue(new HybridValue(v, "/", BYTE), "mod", BYTE);
				ax = new HybridValue(v, "mod", WORD);
				// ax = v.modFunction(new LongValue((long) Math.pow(2, 16)));
				// al = ax.modFunction(new LongValue((long) Math.pow(2, 8)));
				// ah = ax.unsignedDivFunction(new LongValue((long) Math.pow(2,
				// 8)));
			} else if (reg.equals("ax")) {
				ax = v;
				// al = ax.modFunction(new LongValue((long) Math.pow(2, 8)));
				// ah = ax.unsignedDivFunction(new LongValue((long) Math.pow(2,
				// 8)));
			} else if (reg.equals("ah")) {
				ah = v;
			} else if (reg.equals("al")) {
				al = v;
			} else if (reg.equals("ebx")) {
				ebx = v;
				bl = new HybridValue(v, "mod", BYTE);
				bh = new HybridValue(new HybridValue(v, "/", BYTE), "mod", BYTE);
				bx = new HybridValue(v, "mod", WORD);
			} else if (reg.equals("bx")) {
				bx = v;
				// bl = bx.modFunction(new LongValue((long) Math.pow(2, 8)));
				// bh = bx.unsignedDivFunction(new LongValue((long) Math.pow(2,
				// 8)));
			} else if (reg.equals("bh")) {
				bh = v;
			} else if (reg.equals("bl")) {
				bl = v;
			} else if (reg.equals("ecx")) {
				ecx = v;
//				cl = v;
//				ch = v;
//				cx = v;
				cl = new HybridValue(v, "mod", BYTE);
				ch = new HybridValue(new HybridValue(v, "/", BYTE), "mod", BYTE);
				cx = new HybridValue(v, "mod", WORD);
			} else if (reg.equals("cx")) {
				cx = v;
				// cl = cx.modFunction(new LongValue((long) Math.pow(2, 8)));
				// ch = cx.unsignedDivFunction(new LongValue((long) Math.pow(2,
				// 8)));
			} else if (reg.equals("ch")) {
				ch = v;
			} else if (reg.equals("cl")) {
				cl = v;
			} else if (reg.equals("edx")) {
				edx = v;
//				dl = v;
//				dh = v;
//				dx = v;
				dl = new HybridValue(v, "mod", BYTE);
				dh = new HybridValue(new HybridValue(v, "/", BYTE), "mod", BYTE);
				dx = new HybridValue(v, "mod", WORD);
			} else if (reg.equals("dx")) {
				dx = v;
				// dl = dx.modFunction(new LongValue((long) Math.pow(2, 8)));
				// dh = dx.unsignedDivFunction(new LongValue((long) Math.pow(2,
				// 8)));
			} else if (reg.equals("dh")) {
				dh = v;
			} else if (reg.equals("dl")) {
				dl = v;
			} else if (reg.equals("esi")) {
				esi = v;
				// si = v.modFunction(new LongValue((long) Math.pow(2, 16)));
			} else if (reg.equals("si")) {
				si = v;
			} else if (reg.equals("edi")) {
				edi = v;
				// di = v.modFunction(new LongValue((long) Math.pow(2, 16)));
			} else if (reg.equals("di")) {
				di = v;
			} else if (reg.equals("esp")) {
				esp = v;
				// sp = v.modFunction(new LongValue((long) Math.pow(2, 16)));
			} else if (reg.equals("sp")) {
				sp = v;
			} else if (reg.equals("ebp")) {
				ebp = v;
				// bp = v.modFunction(new LongValue((long) Math.pow(2, 16)));
			} else if (reg.equals("bp")) {
				bp = v;
			} else if (reg.equals("eip")) {
				eip = v;
			}			
		}
	}

	private String checkRegisterName(String registerName) {
		// TODO Auto-generated method stub
		String ret = "";
		if (registerName.startsWith("%")) {
			ret = registerName.substring(1).toLowerCase();
		} else {
			ret = registerName.toLowerCase();
		}

		return ret;
	}

	// Kiem tra cac gia tri set cho register co vuot qua domain.
	// Nhu gia tri cua thanh ghi ah khong the vuot qua so 256
	private Value checkProperValue(String reg, Value v) {
		// TODO Auto-generated method stub
		return v.clone();
	}

	public void add(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);

		if (!(d instanceof LongValue) && dest.contains("ecx")) {
			add("cl", val);
		} else if (!(d instanceof LongValue) && dest.contains("edx")) {
			add("dl", val);
		}
		if (!(d instanceof LongValue) && dest.contains("eax")) {
			add("al", val);
		} else if (!(d instanceof LongValue) && dest.contains("ebx")) {
			add("bl", val);
		}

		if (d == null) {
			System.out.println("Add Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.addFunction(val);
		setRegisterValue(dest, d);
	}

	public void add(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		if (d == null) {
			System.out.println("Add Function Fail:" + dest + " Value" + val);
			return;
		}

		val = normalizeValue(val, dest);
		d = d.addFunction(new LongValue(val));
		setRegisterValue(dest, d);
	}

	public void add(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		Value s = getRegisterValue(src);
		// s = normalizeValue(s, dest);

		if (d == null || s == null) {
			System.out.println("Add Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.addFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void and(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		if (d == null) {
			System.out.println("And Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.andFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void and(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		if (d == null) {
			System.out.println("And Function Fail:" + dest + " Value" + val);
			return;
		}
		val = normalizeValue(val, dest);
		d = d.andFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void and(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		Value s = getRegisterValue(src);
		s = normalizeValue(s, dest);
		if (d == null || s == null) {
			System.out.println("And Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.andFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	@Deprecated
	public void div(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		if (d == null) {
			System.out.println("Div Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.unsignedDivFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	@Deprecated
	public void div(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Div Function Fail:" + dest + " Value" + val);
			return;
		}
		val = normalizeValue(val, dest);
		d = d.unsignedDivFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	@Deprecated
	public void div(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Div Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.unsignedDivFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public boolean isValue(String reg) {
		Value t = getRegisterValue(reg);

		return (t != null && t instanceof LongValue);
	}

	public void mov(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);

		if (d == null) {
			System.out.println("Mov Function Fail:" + dest + " Value:" + val.toString());
			return;
		}

		d = d.movFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void mov(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Mov Function Fail:" + dest + " Value" + val);
			return;
		}

		d = d.movFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void mov(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Mov Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.movFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void mul(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Mul Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.unsignedMulFunction(val);

		/*
		 * if (d instanceof LongValue) { long m =
		 * ((LongValue)d).getValueOperand(); if (dest.contains("eax")) {
		 * setRegisterValue("eax", new LongValue((long) (m % Math.pow(2, 32))));
		 * setRegisterValue("edx", new LongValue((long) (m / Math.pow(2, 32))));
		 * } else if (dest.contains("ax")) { setRegisterValue("ax", new
		 * LongValue((long) (m % Math.pow(2, 16)))); setRegisterValue("dx", new
		 * LongValue((long) (m / Math.pow(2, 16)))); } else if
		 * (dest.contains("al")) { setRegisterValue("al", new LongValue((long)
		 * (m % Math.pow(2, 8)))); setRegisterValue("ah", new LongValue((long)
		 * (m / Math.pow(2, 8)))); } return; }
		 */
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void mul(String dest, long val) {
		// TODO Auto-generated method stub
		/*
		 * Value d = getRegisterValue(dest); if (d == null) {
		 * System.out.println("Mul Function Fail:" + dest + " Value" + val);
		 * return; }
		 * 
		 * val = normalizeValue(val, dest); d = d.unsignedMulFunction(new
		 * LongValue(val)); // d = normalizeValue(d, dest);
		 * setRegisterValue(dest, d);
		 */
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Mul Function Fail:" + dest + " Value:" + val);
			return;
		}
		val = normalizeValue(val, dest);
		d = d.unsignedMulFunction(new LongValue(val));

		/*
		 * if (d instanceof LongValue) { long m =
		 * ((LongValue)d).getValueOperand(); if (dest.contains("eax")) {
		 * setRegisterValue("eax", new LongValue((long) (m % Math.pow(2, 32))));
		 * setRegisterValue("edx", new LongValue((long) (m / Math.pow(2, 32))));
		 * } else if (dest.contains("ax")) { setRegisterValue("ax", new
		 * LongValue((long) (m % Math.pow(2, 16)))); setRegisterValue("dx", new
		 * LongValue((long) (m / Math.pow(2, 16)))); } else if
		 * (dest.contains("al")) { setRegisterValue("al", new LongValue((long)
		 * (m % Math.pow(2, 8)))); setRegisterValue("ah", new LongValue((long)
		 * (m / Math.pow(2, 8)))); } return; }
		 */
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void mul(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Mul Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.unsignedMulFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void or(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);

		if (d == null) {
			System.out.println("Or Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.orFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void or(String dest, int val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Or Function Fail:" + dest + " Value" + val);
			return;
		}

		d = d.orFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void or(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Or Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.orFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void printInfor() {
		// TODO Auto-generated method stub
		System.out.println("Information of Register:");
		// String []reg = new String[] {"eax", "ebx", "ecx", "edx"};
		System.out.println("eax:" + eax.toString() + " ax:" + ax.toString() + " ah:" + ah.toString() + " al:"
				+ al.toString());
		System.out.println("ebx:" + ebx.toString() + " bx:" + bx.toString() + " bh:" + bh.toString() + " bl:"
				+ bl.toString());
		System.out.println("ecx:" + ecx.toString() + " cx:" + cx.toString() + " ch:" + ch.toString() + " cl:"
				+ cl.toString());
		System.out.println("edx:" + edx.toString() + " dx:" + dx.toString() + " dh:" + dh.toString() + " dl:"
				+ dl.toString());

		System.out.println("esi:" + esi.toString() + " si:" + si.toString());
		System.out.println("edi:" + edi.toString() + " di:" + di.toString());
		System.out.println("esp:" + esp.toString() + " sp:" + sp.toString());
		System.out.println("ebp:" + ebp.toString() + " bp:" + bp.toString());

		// System.out.println("+++++++++++++");
		// System.out.println("Information of Memory Operand");
		// this.memoOperandSymbol.printInfo();
	}

	public void sub(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);

		if (d == null) {
			System.out.println("Sub Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.subFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void sub(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Sub Function Fail:" + dest + " Value" + val);
			return;
		}
		val = normalizeValue(val, dest);
		d = d.subFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void sub(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("sub Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.subFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void xor(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);

		if (d == null) {
			System.out.println("Xor Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);
		d = d.xorFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void xor(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Xor Function Fail:" + dest + " Value" + val);
			return;
		}
		val = normalizeValue(val, dest);
		d = d.xorFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void xor(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Xor Function Fail:" + dest + " Src:" + src);
			return;
		}

		d = d.xorFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void rr(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);

		if (d == null) {
			System.out.println("RR Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		switch (checkBitCount(dest)) {
		case 32:
			d = d.rrFunction(val);
			break;
		case 16:
			d = d.rr16Function(val);
			break;
		default:
			d = d.rr8Function(val);
			break;
		}
		// d = d.rrFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void rr(String dest, int val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("RR Function Fail:" + dest + " Value" + val);
			return;
		}

		switch (checkBitCount(dest)) {
		case 32:
			d = d.rrFunction(new LongValue(val));
			break;
		case 16:
			d = d.rr16Function(new LongValue(val));
			break;
		default:
			d = d.rr8Function(new LongValue(val));
			break;
		}
		// d = d.rrFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void rr(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("RR Function Fail:" + dest + " Src:" + src);
			return;
		}

		switch (checkBitCount(dest)) {
		case 32:
			d = d.rrFunction(s);
			break;
		case 16:
			d = d.rr16Function(s);
			break;
		default:
			d = d.rr8Function(s);
			break;
		}
		// d = d.rrFunction(s);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void rl(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);

		if (d == null) {
			System.out.println("RL Function Fail:" + dest + " Value:" + val.toString());
			return;
		}

		switch (checkBitCount(dest)) {
		case 32:
			d = d.rlFunction(val);
			break;
		case 16:
			d = d.rl16Function(val);
			break;
		default:
			d = d.rl8Function(val);
			break;
		}
		// d = d.rlFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void rl(String dest, int val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("RL Function Fail:" + dest + " Value" + val);
			return;
		}

		switch (checkBitCount(dest)) {
		case 32:
			d = d.rlFunction(new LongValue(val));
			break;
		case 16:
			d = d.rl16Function(new LongValue(val));
			break;
		default:
			d = d.rl8Function(new LongValue(val));
			break;
		}

		// d = d.rlFunction(new LongValue(val));
		d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	public void rl(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("RL Function Fail:" + dest + " Src:" + src);
			return;
		}
		if (dest.startsWith("e")) {
			d = d.rlFunction(s);
		} else {
			d = d.rl8Function(s);
		}
		setRegisterValue(dest, d);
	}

	public boolean equals(Register r) {
		return eax.equal(r.getRegisterValue("eax")) && ebx.equal(r.getRegisterValue("ebx"))
				&& ecx.equal(r.getRegisterValue("ecx")) && edx.equal(r.getRegisterValue("edx"))
				&& esi.equal(r.getRegisterValue("esi")) && edi.equal(r.getRegisterValue("edi"))
				&& esp.equal(r.getRegisterValue("esp")) && ebp.equal(r.getRegisterValue("ebp"));
	}

	private int checkBitCount(String regName) {
		if (regName.contains("eax") || regName.contains("ebx") || regName.contains("ecx") || regName.contains("edx")
				|| regName.contains("esi") || regName.contains("edi") || regName.contains("esp")
				|| regName.contains("ebp")) {
			return 32;
		} else if (regName.contains("ax") || regName.contains("bx") || regName.contains("cx") || regName.contains("dx")
				|| regName.contains("si") || regName.contains("di") || regName.contains("sp") || regName.contains("bp")) {
			return 16;
		}
		return 8;
	}

	private Value normalizeValue(Value v, String regName) {
		if (v instanceof LongValue) {
			long t = ((LongValue) v).getValue();

			/*
			 * long t1 = 0; if (t == -3076) { System.out.println("Debug"); t1 =
			 * t & 0xFFFFFFFFl; } System.out.println("t1:" + t1);
			 */

			/*
			 * switch (checkBitCount(regName)) { case 32: return new LongValue(t
			 * & 0xFFFFFFFFl); case 16: return new LongValue(t & 0xFFFF);
			 * default: return new LongValue(t & 0xFF); }
			 */

			return new LongValue(Convert.convetUnsignedValue(t, Convert.getBitCount(regName)));
		}

		return v;
	}

	private long normalizeValue(long v, String dest) {
		// TODO Auto-generated method stub
		/*
		 * switch (checkBitCount(dest)) { case 32: return v; case 16: return (v
		 * & 0xFFFF); default: return (v & 0xFF); }
		 */

		return Convert.convetUnsignedValue(v, Convert.getBitCount(dest));
	}

	@Override
	public String toString() {
		String ret = "eax=" + eax.toString() + ", " + "ebx=" + ebx.toString() + ", " + "ecx=" + ecx.toString() + ", "
				+ "edx=" + edx.toString() + ", " + "esp=" + esp.toString() + ", " + "ebp=" + ebp.toString() + ", "
				+ "esi=" + esi.toString() + ", " + "edi=" + edi.toString();
		// fp.appendFile(ret);
		/*
		 * "eax=" + eax.toString() + ", al=" + al.toString() + ", " + "ebx=" +
		 * ebx.toString() + ", " + "bl=" + bl.toString() + ", " + "ecx=" +
		 * ecx.toString() + ", cl=" + cl.toString() + ", " + "edx=" +
		 * edx.toString() + ", dl=" + dl.toString() + " " + "esp=" +
		 * esp.toString() + ", " + "ebp=" + ebp.toString() + ", " + "esi=" +
		 * esi.toString() + ", " + "edi=" + edi.toString();
		 */
		return ret;
	}

	@Deprecated
	// Signed DIV
	public void sDiv(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value t = val.powFunction(2);
		// d = normalizeValue(d, dest);
		if (d == null) {
			System.out.println("Div Function Fail:" + dest + " Value:" + val.toString());
			return;
		}

		if (d instanceof LongValue && val instanceof LongValue) {
			long c = ((LongValue) d).getValue();
			long l = ((LongValue) val).getValue();
			int i = Convert.getSignBit(c, checkBitCount(dest));
			int b = checkBitCount(dest);
			for (int j = 1; j <= l; j++) {
				c = c / 2;
			}

			for (int j = 1; j <= l; j++) {
				c += i * Math.pow(2, b - j);
			}
			setRegisterValue(dest, new LongValue(c));
			return;
		}

		t = normalizeValue(t, dest);
		d = d.unsignedDivFunction(t);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	// private int getSignBit(String dest, long d) {
	// // TODO Auto-generated method stub
	// int num = checkBitCount(dest);
	// d = Convert.convetUnsignedValue(d, num);
	//
	// if ((num == 8 && d >= Math.pow(2, 7))
	// || (num == 16 && d >= Math.pow(2, 15))
	// || (num == 32 && d >= Math.pow(2, 31)))
	// return 1;
	//
	// return 0;
	// }

	public void sDiv(String dest, long val) {
		// TODO Auto-generated method stub
		/*
		 * Value d = getRegisterValue(dest); if (d == null) {
		 * System.out.println("Div Function Fail:" + dest + " Value" + val);
		 * return; } val = normalizeValue(val, dest); d =
		 * d.unsignedDivFunction(new LongValue(val)); // d = normalizeValue(d,
		 * dest); setRegisterValue(dest, d);
		 */
		sDiv(dest, new LongValue(val));
	}

	@Deprecated
	public void sDiv(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Div Function Fail:" + dest + " Src:" + src);
			return;
		}
		sDiv(dest, s);
	}

	@Deprecated
	public void uDiv(String dest, Value val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		// d = normalizeValue(d, dest);
		if (d == null) {
			System.out.println("Div Function Fail:" + dest + " Value:" + val.toString());
			return;
		}
		val = normalizeValue(val, dest);

		if (val instanceof LongValue) {
			uDiv(dest, ((LongValue) val).getValue());
			return;
		}

		d = d.unsignedDivFunction(val);
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	@Deprecated
	public void uDiv(String dest, long val) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		if (d == null) {
			System.out.println("Div Function Fail:" + dest + " Value" + val);
			return;
		}

		if (d instanceof LongValue) {
			long divident = ((LongValue) d).getValue();

			if (dest.contains("al")) {
				Value t = getRegisterValue("ax");

				if (t instanceof LongValue) {
					divident = ((LongValue) t).getValue();
				}

				long quotient = divident / val;
				if (quotient > 255) {
					System.out.println("Divide Error");
					return;
				}
				long remainder = divident % val;
				setRegisterValue("al", new LongValue(quotient));
				setRegisterValue("ah", new LongValue(remainder));
			} else if (dest.contains("eax")) {
				Value t = getRegisterValue("edx");

				if (t instanceof LongValue) {
					divident += (long) (((LongValue) t).getValue() * Math.pow(2, 32));
				} else {
					t = getRegisterValue("dx");
					if (t instanceof LongValue) {
						divident += (long) (((LongValue) t).getValue() * Math.pow(2, 32));
					}
				}
				long quotient = divident / val;
				if (quotient > 4294967295l) {
					System.out.println("Divide Error");
					return;
				}
				long remainder = divident % val;
				setRegisterValue("eax", new LongValue(quotient));
				setRegisterValue("edx", new LongValue(remainder));

			} else if (dest.contains("ax")) {
				Value t = getRegisterValue("dx");

				if (t instanceof LongValue) {
					divident += (long) (((LongValue) t).getValue() * Math.pow(2, 16));
				}

				long quotient = divident / val;
				if (quotient > 65535) {
					System.out.println("Divide Error");
					return;
				}
				long remainder = divident % val;
				setRegisterValue("ax", new LongValue(quotient));
				setRegisterValue("dx", new LongValue(remainder));
			}
			return;
		}

		val = normalizeValue(val, dest);
		d = d.unsignedDivFunction(new LongValue(val));
		// d = normalizeValue(d, dest);
		setRegisterValue(dest, d);
	}

	@Deprecated
	public void uDiv(String dest, String src) {
		// TODO Auto-generated method stub
		Value d = getRegisterValue(dest);
		Value s = getRegisterValue(src);

		if (d == null || s == null) {
			System.out.println("Div Function Fail:" + dest + " Src:" + src);
			return;
		}

		uDiv(dest, s);
	}

	public void resetValue(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		setRegisterValue("eax", eax.evaluate(z3Value));
		setRegisterValue("ebx", ebx.evaluate(z3Value));
		setRegisterValue("ecx", ecx.evaluate(z3Value));
		setRegisterValue("edx", edx.evaluate(z3Value));
		setRegisterValue("esi", esi.evaluate(z3Value));
		setRegisterValue("edi", edi.evaluate(z3Value));
		setRegisterValue("esp", esp.evaluate(z3Value));
		setRegisterValue("ebp", ebp.evaluate(z3Value));		
	}

	public void reset() {
		// TODO Auto-generated method stub
		eax = new SymbolValue("eax");
		ax = new SymbolValue("ax");
		ah = new SymbolValue("ah");
		al = new SymbolValue("al");

		ebx = new SymbolValue("ebx");
		bx = new SymbolValue("bx");
		bh = new SymbolValue("bh");
		bl = new SymbolValue("bl");

		ecx = new SymbolValue("ecx");
		cx = new SymbolValue("cx");
		ch = new SymbolValue("ch");
		cl = new SymbolValue("cl");

		edx = new SymbolValue("edx");
		dx = new SymbolValue("dx");
		dh = new SymbolValue("dh");
		dl = new SymbolValue("dl");

		esi = new SymbolValue("esi");
		si = new SymbolValue("si");

		edi = new SymbolValue("edi");
		di = new SymbolValue("di");

		//esp = new SymbolValue("esp");
		//sp = new SymbolValue("sp");

		ebp = new SymbolValue("ebp");
		bp = new SymbolValue("bp");
	}

	public String toHashString() {
		// TODO Auto-generated method stub
		if (isChanged) {
			MD5 md5 = new MD5();
//			String memoryStr = getOrderedStringContent();
			try {
				md5.Update(toString(), null);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hashValue = md5.asHex();
			isChanged = false;
		}
		
		return this.hashValue;
	}

	public void pushFPU(Value val) {
		st.push(val);
	}

	public Value popFPU() {
		return st.pop();
	}

	public Value getSt() {
		return st.getSt();
	}

	public void setSt(Value val) {
		st.setSt(val);
	}

	public FPUStack getFPUStack() {
		return st;
	}
}
