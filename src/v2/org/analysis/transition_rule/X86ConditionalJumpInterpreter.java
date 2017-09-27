package v2.org.analysis.transition_rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Formula;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.HybridBooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SolverResult;
import v2.org.analysis.value.Value;

public class X86ConditionalJumpInterpreter {
	
	private SolverResult _solverResult = new SolverResult(false);
	
	public SolverResult getSolverResult() {
		return _solverResult;
	}
	
	public BPState execute(X86CondJmpInstruction ins, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	public void execute(X86CondJmpInstruction inst, BPPath path, List<BPPath> pathList, boolean condition,
			X86TransitionRule rule) {
		Formulas formulas = path.getPathCondition();
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		BPCFG cfg = Program.getProgram().getBPCFG();
		Instruction ins = curState.getInstruction();
		BPVertex src = cfg.getVertex(curState.getLocation(), ins);

		boolean reverseCond = !condition;
		String instName = inst.getName();

		// structure: create new work associated with condition-false case
		// jump to condition-true case
		// add state condition into each trace
		// SymbolicCondition compareStatus = prevState.getCompareStatus();
		// we accept loop not to be constrained by compareStatus
		if (inst.getName().equals("loop")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Program.getProgram().setTechnique("Encrypt/Decrypt-May");
			Program.getProgram().setDetailTechnique("Encrypt/Decrypt-May:" + curState.getLocation() + " ");
			env.getRegister().sub("%ecx", new LongValue(1));
			Value ecx = env.getRegister().getRegisterValue("%ecx");

			if (reverseCond) {
				if (ecx instanceof LongValue) {
					long e = ((LongValue) ecx).getValue();

					if (e == 0) {
						// System.out.println("Loop Right")
						;
					} else {
						curState.setFeasiblePath(false);
					}
				} else {
					formulas.add((new Formula(ecx, new LongValue(0), "==")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue) {
					long e = ((LongValue) ecx).getValue();

					if (e != 0) {
						// System.out.println("Loop Right")
						// curState.setFeasiblePath(true);
						// return
						;
					} else {
						// System.out.println("Debug Loop");
						curState.setFeasiblePath(false);
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "==")).evaluate();
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			}
		} else if (inst.getName().equals("loope") || inst.getName().equals("loopz")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Program.getProgram().setTechnique("Encrypt/Decrypt-May");
			Program.getProgram().setDetailTechnique("Encrypt/Decrypt-May:" + curState.getLocation() + " ");

			Value ecx = env.getRegister().getRegisterValue("%ecx");
			Value zf = env.getFlag().getZFlag();
			if (reverseCond) {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e == 0 || !z) {
						// System.out.println("Loop Right")
						;
					} else {
						curState.setFeasiblePath(false);
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "==")).evaluate();
					Value l2 = (new HybridBooleanValue(zf, new BooleanValue(false), "==")).evaluate();
					formulas.add((new Formula(l1, l2, "or")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e != 0 && z) {
						// System.out.println("Loop Right")
						// curState.setFeasiblePath(true);
						// return
						;
					} else {
						// System.out.println("Debug Loop");
						curState.setFeasiblePath(false);
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "!=")).evaluate();
					// Value l2 = new HybridBooleanValue(l1, "not", null);
					formulas.add((new Formula(l1, zf, "and")).evaluate());
				}
			}

		} else if (inst.getName().equals("loopne") || inst.getName().equals("loopnz")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Program.getProgram().setTechnique("Encrypt/Decrypt-May");
			Program.getProgram().setDetailTechnique("Encrypt/Decrypt-May:" + curState.getLocation() + " ");

			Value ecx = env.getRegister().getRegisterValue("%ecx");
			Value zf = env.getFlag().getZFlag();
			if (reverseCond) {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e == 0 || z) {
						// System.out.println("Loop Right")
						;
					} else {
						curState.setFeasiblePath(false);
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "==")).evaluate();

					formulas.add((new Formula(l1, zf, "or")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e != 0 && !z) {
						// System.out.println("Loop Right")
						// curState.setFeasiblePath(true);
						// return
						;
					} else {
						// System.out.println("Debug Loop");
						curState.setFeasiblePath(false);
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "!=")).evaluate();
					Value l2 = (new HybridBooleanValue(zf, new BooleanValue(false), "==")).evaluate();
					formulas.add((new Formula(l1, l2, "and")).evaluate());
				}
			}

		} else if (instName.equals("ja") || instName.equals("jnbe")) {
			// if
			// ((CF) = 0) or ((ZF) = 0)
			// Not finished
			Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), "==", new BooleanValue(0))).evaluate();
			Value l2 = (new HybridBooleanValue(env.getFlag().getZFlag(), "==", new BooleanValue(0))).evaluate();

			if (reverseCond) {
				// Exp l3 = new OtherBooleanValue(l1, l2, "or");
				// formulas.add(new Formula(l3, "not"));
				l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), "==", new BooleanValue(1))).evaluate();
				l2 = (new HybridBooleanValue(env.getFlag().getZFlag(), "==", new BooleanValue(1))).evaluate();
				// Exp l3 = new OtherBooleanValue(l1, l2, "and");
				// formulas.add(new Formula(l1, l2, "or"));

				// PHONG: fix here
				formulas.add((new Formula(l1, l2, "or")).evaluate());
			} else {
				// formulas.add(new Formula(l1, l2, "or"));
				formulas.add((new Formula(l1, l2, "and")).evaluate());
			}
		} else if (instName.equals("jae") || instName.equals("jnb")) {
			// if
			// ((CF) = 0)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}
					else {
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate();
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else
						// System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
		} else if (instName.equals("jb") || instName.equals("jnae")) {
			// if
			// ((CF) = 1)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
		} else if (instName.equals("jbe") || instName.equals("jna")) {
			// if
			// ((CF) = 1) or ((ZF) = 1)
			// Not finished
			Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), "==", new BooleanValue(1)).evaluate());
			Value l2 = (new HybridBooleanValue(env.getFlag().getZFlag(), "==", new BooleanValue(1)).evaluate());

			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue
						&& env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					boolean c = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (z || c) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");

				} else {
					Value l3 = (new HybridBooleanValue(l1, l2, "or").evaluate());
					formulas.add((new Formula(l3, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue
						&& env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					boolean c = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!(z || c)) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");

				} else {
					formulas.add((new Formula(l1, l2, "or")).evaluate());
				}
			}
			/*
			 * formulas.add(new Formula(env.getFlag().getcFlag(), new
			 * ConstantBooleanValue(1), "=="));
			 */
		} else if (instName.equals("jc")) {
			// if
			// ((CF) = 1)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
		} else if (instName.equals("jcxz")) {
			// if
			// ((CF) = 0)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");

					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate());
				}
			}
		} else if (instName.equals("je") || instName.equals("jz")) {
			// if
			// ((ZF) = 1)

			// Truong hop dac biet, se xu li sau
			/*
			 * if
			 * (Program.getProgram().getFileName().equals("Virus.Win32.Aztec.01"
			 * ) && ( // (this.targetIndirect.toString().equals("0x00401135") &&
			 * // this.targetTemp.toString().equals("0x004010ce")) // ||
			 * (this.targetIndirect.toString().equals("0x004012d0") // &&
			 * this.targetTemp.toString().equals("0x0040114e")) // ||
			 * (this.targetIndirect.toString().equals("0x004012bd") // &&
			 * this.targetTemp.toString().equals("0x00401168")) // ||
			 * (this.targetIndirect.toString().equals("0x004012d6") // &&
			 * this.targetTemp.toString().equals("0x00401168"))
			 * 
			 * // || (this.targetIndirect.toString().equals("0x004010b1") // &&
			 * this.targetTemp.toString().equals("0x004010ce"))
			 * (this.targetIndirect.toString().equals("0x0040134b") &&
			 * this.targetTemp .toString().equals("0x00401198")) ||
			 * (this.targetIndirect.toString().equals( "0x0040134b") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012bd") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012dc") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012d0") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012d6") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012d6") &&
			 * this.targetTemp.toString() .equals("0x0040114e")) ||
			 * (this.targetIndirect .toString().equals("0x0040111f") &&
			 * this.targetTemp .toString().equals("0x0040114e")))) return;
			 */

			if (reverseCond) {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}
					else {
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					/*
					 * Value l1 = new
					 * HybridBooleanValue(env.getFlag().getZFlag(), new
					 * BooleanValue(1), "=="); formulas.add(new Formula(l1,
					 * "not"));
					 */
					formulas.add((new Formula(env.getFlag().getZFlag(), new BooleanValue(0), "==")).evaluate());
				}
			} else {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien

					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}
					else {
						// else
						// this.curState.setFeasiblePath(false);
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					formulas.add((new Formula(env.getFlag().getZFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
		} else if (instName.equals("jg") || instName.equals("jnle")) {
			// if
			// ((SF) = (OF)) and ((ZF) = 0)
			Value l1 = (new HybridBooleanValue(env.getFlag().getZFlag(), new BooleanValue(0), "==").evaluate());
			Value l2 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "==").evaluate());

			if (reverseCond) {
				Value l3 = (new HybridBooleanValue(l1, l2, "and").evaluate());
				formulas.add((new Formula(l3, "not")).evaluate());
			} else {
				formulas.add((new Formula(l1, l2, "and")).evaluate());
			}

			// formulas.add(new Formula(env.getFlag().getzFlag(), new
			// ConstantBooleanValue(0), "=="));
			// formulas.add(new Formula(env.getFlag().getsFlag(),
			// env.getFlag().getoFlag(), "=="));
		} else if (instName.equals("jge") || instName.equals("jnl")) {
			// if
			// (SF) = (OF)

			if (reverseCond) {
				Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "==").evaluate());
				formulas.add((new Formula(l1, "not")).evaluate());
			} else {
				formulas.add((new Formula(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "==")).evaluate());
			}
		} else if (instName.equals("jl") || instName.equals("jnge")) {
			// if
			// (SF) ≠ (OF)
			// formulas.add(new Formula(env.getFlag().getsFlag(),
			// env.getFlag().getoFlag(), "!="));
			if (reverseCond) {
				Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "!=").evaluate());
				formulas.add((new Formula(l1, "not")).evaluate());
			} else {
				formulas.add((new Formula(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "!=")).evaluate());
			}
		} else if (instName.equals("jle") || instName.equals("jng")) {
			// if
			// ((SF) ≠ (OF)) or ((ZF) = 1)
			// Focus on ZF = 1
			// not finished
			// Change due to Demo1
			// OtherExp l1 = new OtherExp(env.getFlag().getzFlag(), new
			// ConstantBooleanValue(1), "=");
			Value l1 = env.getFlag().getZFlag();
			Value l2 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "xor")).evaluate();
			// OtherExp l3 = new OtherExp(l2, new ConstantLongExp(0), "!=");

			if (reverseCond) {
				Value l3 = (new HybridBooleanValue(l1, l2, "or")).evaluate();
				formulas.add((new Formula(l3, "not")).evaluate());
			} else {
				formulas.add((new Formula(l1, l2, "or")).evaluate());
			}

			// formulas.add(new Formula(env.getFlag().getzFlag(), new
			// ConstantBooleanValue(1), "="));

			/*
			 * OtherExp l1 = new OtherExp(env.getFlag().getcFlag(), "!=", new
			 * ConstantBooleanValue(0)); OtherExp l2 = new
			 * OtherExp(env.getFlag().getzFlag(), "!=", new
			 * ConstantBooleanValue(0)); formulas.add(new Formula(l1, l2,
			 * "and"));
			 */
		} else if (instName.equals("jnc")) {
			// if
			// ((CF) = 0)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate());
				}
			}
		} else if (instName.equals("jne") || instName.equals("jnz")) {
			if (reverseCond) {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}
					else {
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getZFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}
					else {
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					formulas.add((new Formula(env.getFlag().getZFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
		} else if (instName.equals("jno")) {
			// if
			// ((OF) = 0)
			if (reverseCond) {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getOFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getOFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getoFlag(), new
			 * BooleanValue(0), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getoFlag(), new
			 * BooleanValue(0), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getoFlag(), new
			// ConstantBooleanValue(0), "=="));
		} else if (instName.equals("jns")) {
			// if
			// ((SF) = 0)
			if (reverseCond) {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getSFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
		} else if (instName.equals("jnp") || instName.equals("jpo")) {
			// if
			// ((PF) = 0)
			if (reverseCond) {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getPFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getPFlag(), new BooleanValue(0), "==")).evaluate());
				}
			}
		} else if (instName.equals("jo")) {
			// if
			// ((OF) = 1)
			if (reverseCond) {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getOFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getOFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getoFlag(), new
			 * BooleanValue(1), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getoFlag(), new
			 * BooleanValue(1), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getoFlag(), new
			// ConstantBooleanValue(1), "=="));
		} else if (instName.equals("jp") || instName.equals("jpe")) {
			// if
			// ((PF) = 1)
			if (reverseCond) {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getPFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getPFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getpFlag(), new
			 * BooleanValue(1), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getpFlag(), new
			 * BooleanValue(1), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getpFlag(), new
			// ConstantBooleanValue(0), "=="));
		} else if (inst.getName().equals("js")) {
			// if
			// ((SF) = 1)
			// var.add("sFlag", 0);
			if (reverseCond) {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);
					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getSFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getsFlag(), new
			 * BooleanValue(1), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getsFlag(), new
			 * BooleanValue(1), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getsFlag(), new
			// ConstantBooleanValue(1), "=="));
		} else if (inst.getName().equals("jecxz")) {
			// if
			// (ECX = 0)
			// var.add("sFlag", 0);
			Value ecx = env.getRegister().getRegisterValue("ecx");
			if (reverseCond) {
				if (ecx instanceof LongValue) {
					long t = ((LongValue) ecx).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (t == 0) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getRegister().getRegisterValue("ecx"), new LongValue(0),
							"==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue) {
					long z = ((LongValue) ecx).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (z != 0) {
						curState.setFeasiblePath(false);
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getRegister().getRegisterValue("ecx"), new LongValue(0), "=="))
							.evaluate());
				}
			}
		}

		if (path.getLoopHandle().isCheck()
		// && path.getLoopHandle().isStop()
		) {
			path.getLoopHandle().setStop(path.getLoopHandle().checkFormulas(inst.getName(), curState));
		}

		if (curState.checkFeasiblePath()) {
			_solverResult = rule.checkZ3(formulas, curState);
			formulas.setResult(_solverResult);
			curState.setFeasiblePath(_solverResult.isSAT());
		}
		rule.generateNextInstruction(inst, path, pathList, condition);
		// Remove later
		// curState.setFeasiblePath(true);

		if (curState.checkFeasiblePath()) {
			BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
			dest = cfg.insertVertex(dest);
			BPEdge edge = new BPEdge(src, dest);
			cfg.insertEdge(edge);
		}
		// return curState;
	}

	public boolean execute(X86CondJmpInstruction inst, Formulas formulas, BPPath path, boolean condition,
			X86TransitionRule rule) {
		// Formulas formulas = path.getPathCondition();
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();

		boolean reverseCond = !condition;
		String instName = inst.getName();
		boolean isFeasible = true;

		// structure: create new work associated with condition-false case
		// jump to condition-true case
		// add state condition into each trace
		// SymbolicCondition compareStatus = prevState.getCompareStatus();
		// we accept loop not to be constrained by compareStatus
		if (inst.getName().equals("loop")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Program.getProgram().setTechnique("Encrypt/Decrypt-May");
			Program.getProgram().setDetailTechnique("Encrypt/Decrypt-May:" + curState.getLocation() + " ");
			// env.getRegister().sub("%ecx", new LongValue(1));
			Value ecx = env.getRegister().getRegisterValue("%ecx");

			if (reverseCond) {
				if (ecx instanceof LongValue) {
					long e = ((LongValue) ecx).getValue();

					if (e <= 0) {
						// System.out.println("Loop Right")
						;
					} else {
						// curState.setFeasiblePath(false);
						isFeasible = false;
					}
				} else {
					// ecx = ecx.subFunction(new LongValue(1));
					formulas.add((new Formula(ecx, new LongValue(0), "==")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue) {
					long e = ((LongValue) ecx).getValue();

					if (e > 0) {
						// System.out.println("Loop Right")
						// curState.setFeasiblePath(true);
						// return
						;
					} else {
						// System.out.println("Debug Loop");
						// curState.setFeasiblePath(false);
						isFeasible = false;
					}
				} else {
					// ecx = ecx.subFunction(new LongValue(1));
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "==")).evaluate();
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			}
		} else if (inst.getName().equals("loope") || inst.getName().equals("loopz")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Program.getProgram().setTechnique("Encrypt/Decrypt-May");
			Program.getProgram().setDetailTechnique("Encrypt/Decrypt-May:" + curState.getLocation() + " ");

			Value ecx = env.getRegister().getRegisterValue("%ecx");
			Value zf = env.getFlag().getZFlag();
			if (reverseCond) {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e == 0 || !z) {
						// System.out.println("Loop Right")
						;
					} else {
						curState.setFeasiblePath(false);
						isFeasible = false;
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "==")).evaluate();
					Value l2 = (new HybridBooleanValue(zf, new BooleanValue(false), "==")).evaluate();
					formulas.add((new Formula(l1, l2, "or")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e != 0 && z) {
						// System.out.println("Loop Right")
						// curState.setFeasiblePath(true);
						// return
						;
					} else {
						// System.out.println("Debug Loop");
						curState.setFeasiblePath(false);
						isFeasible = false;
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "!=")).evaluate();
					// Value l2 = new HybridBooleanValue(l1, "not", null);
					formulas.add((new Formula(l1, zf, "and")).evaluate());
				}
			}

		} else if (inst.getName().equals("loopne") || inst.getName().equals("loopnz")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Program.getProgram().setTechnique("Encrypt/Decrypt-May");
			Program.getProgram().setDetailTechnique("Encrypt/Decrypt-May:" + curState.getLocation() + " ");

			Value ecx = env.getRegister().getRegisterValue("%ecx");
			Value zf = env.getFlag().getZFlag();
			if (reverseCond) {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e == 0 || z) {
						// System.out.println("Loop Right")
						;
					} else {
						curState.setFeasiblePath(false);
						isFeasible = false;
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "==")).evaluate();

					formulas.add((new Formula(l1, zf, "or")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue && zf instanceof BooleanValue) {
					long e = ((LongValue) ecx).getValue();
					boolean z = ((BooleanValue) zf).getValue();

					if (e != 0 && !z) {
						// System.out.println("Loop Right")
						// curState.setFeasiblePath(true);
						// return
						;
					} else {
						// System.out.println("Debug Loop");
						curState.setFeasiblePath(false);
						isFeasible = false;
					}
				} else {
					Value l1 = (new HybridBooleanValue(ecx, new LongValue(0), "!=")).evaluate();
					Value l2 = (new HybridBooleanValue(zf, new BooleanValue(false), "==")).evaluate();
					formulas.add((new Formula(l1, l2, "and")).evaluate());
				}
			}

		} else if (instName.equals("ja") || instName.equals("jnbe")) {
			// if
			// ((CF) = 0) or ((ZF) = 0)
			// Not finished
			Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), "==", new BooleanValue(0))).evaluate();
			Value l2 = (new HybridBooleanValue(env.getFlag().getZFlag(), "==", new BooleanValue(0))).evaluate();

			if (reverseCond) {
				// Exp l3 = new OtherBooleanValue(l1, l2, "or");
				// formulas.add(new Formula(l3, "not"));
				l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), "==", new BooleanValue(1))).evaluate();
				l2 = (new HybridBooleanValue(env.getFlag().getZFlag(), "==", new BooleanValue(1))).evaluate();
				// Exp l3 = new OtherBooleanValue(l1, l2, "and");
				// formulas.add(new Formula(l1, l2, "or"));

				// PHONG: fix here
				formulas.add((new Formula(l1, l2, "or")).evaluate());
			} else {
				// formulas.add(new Formula(l1, l2, "or"));
				formulas.add((new Formula(l1, l2, "and")).evaluate());
			}
		} else if (instName.equals("jae") || instName.equals("jnb")) {
			// if
			// ((CF) = 0)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						// curState.setFeasiblePath(false);
						isFeasible = false;
						// System.out.println("Debug");
					}
					else {
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate();
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						// curState.setFeasiblePath(false);
						isFeasible = false;
						// System.out.println("Debug");
					} // else
						// System.out.println("Right");
					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
		} else if (instName.equals("jb") || instName.equals("jnae")) {
			// if
			// ((CF) = 1)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
		} else if (instName.equals("jbe") || instName.equals("jna")) {
			// if
			// ((CF) = 1) or ((ZF) = 1)
			// Not finished
			Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), "==", new BooleanValue(1)).evaluate());
			Value l2 = (new HybridBooleanValue(env.getFlag().getZFlag(), "==", new BooleanValue(1)).evaluate());

			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue
						&& env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					boolean c = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (z || c) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");

				} else {
					Value l3 = (new HybridBooleanValue(l1, l2, "or").evaluate());
					formulas.add((new Formula(l3, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue
						&& env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					boolean c = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!(z || c)) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");

				} else {
					formulas.add((new Formula(l1, l2, "or")).evaluate());
				}
			}
			/*
			 * formulas.add(new Formula(env.getFlag().getcFlag(), new
			 * ConstantBooleanValue(1), "=="));
			 */
		} else if (instName.equals("jc")) {
			// if
			// ((CF) = 1)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
		} else if (instName.equals("jcxz")) {
			// if
			// ((CF) = 0)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");

					// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate());
				}
			}
		} else if (instName.equals("je") || instName.equals("jz")) {
			// if
			// ((ZF) = 1)

			// Truong hop dac biet, se xu li sau
			/*
			 * if
			 * (Program.getProgram().getFileName().equals("Virus.Win32.Aztec.01"
			 * ) && ( // (this.targetIndirect.toString().equals("0x00401135") &&
			 * // this.targetTemp.toString().equals("0x004010ce")) // ||
			 * (this.targetIndirect.toString().equals("0x004012d0") // &&
			 * this.targetTemp.toString().equals("0x0040114e")) // ||
			 * (this.targetIndirect.toString().equals("0x004012bd") // &&
			 * this.targetTemp.toString().equals("0x00401168")) // ||
			 * (this.targetIndirect.toString().equals("0x004012d6") // &&
			 * this.targetTemp.toString().equals("0x00401168"))
			 * 
			 * // || (this.targetIndirect.toString().equals("0x004010b1") // &&
			 * this.targetTemp.toString().equals("0x004010ce"))
			 * (this.targetIndirect.toString().equals("0x0040134b") &&
			 * this.targetTemp .toString().equals("0x00401198")) ||
			 * (this.targetIndirect.toString().equals( "0x0040134b") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012bd") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012dc") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012d0") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012d6") &&
			 * this.targetTemp.toString() .equals("0x004011a5")) ||
			 * (this.targetIndirect.toString().equals( "0x004012d6") &&
			 * this.targetTemp.toString() .equals("0x0040114e")) ||
			 * (this.targetIndirect .toString().equals("0x0040111f") &&
			 * this.targetTemp .toString().equals("0x0040114e")))) return;
			 */

			if (reverseCond) {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}
					else {
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					/*
					 * Value l1 = new
					 * HybridBooleanValue(env.getFlag().getZFlag(), new
					 * BooleanValue(1), "=="); formulas.add(new Formula(l1,
					 * "not"));
					 */
					formulas.add((new Formula(env.getFlag().getZFlag(), new BooleanValue(0), "==")).evaluate());
				}
			} else {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien

					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					}
					else {
						// else
						// this.curState.setFeasiblePath(false);
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					formulas.add((new Formula(env.getFlag().getZFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
		} else if (instName.equals("jg") || instName.equals("jnle")) {
			// if
			// ((SF) = (OF)) and ((ZF) = 0)
			Value l1 = (new HybridBooleanValue(env.getFlag().getZFlag(), new BooleanValue(0), "==").evaluate());
			Value l2 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "==").evaluate());

			if (reverseCond) {
				Value l3 = (new HybridBooleanValue(l1, l2, "and").evaluate());
				formulas.add((new Formula(l3, "not")).evaluate());
			} else {
				formulas.add((new Formula(l1, l2, "and")).evaluate());
			}

			// formulas.add(new Formula(env.getFlag().getzFlag(), new
			// ConstantBooleanValue(0), "=="));
			// formulas.add(new Formula(env.getFlag().getsFlag(),
			// env.getFlag().getoFlag(), "=="));
		} else if (instName.equals("jge") || instName.equals("jnl")) {
			// if
			// (SF) = (OF)

			if (reverseCond) {
				Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "==").evaluate());
				formulas.add((new Formula(l1, "not")).evaluate());
			} else {
				formulas.add((new Formula(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "==")).evaluate());
			}
		} else if (instName.equals("jl") || instName.equals("jnge")) {
			// if
			// (SF) ≠ (OF)
			// formulas.add(new Formula(env.getFlag().getsFlag(),
			// env.getFlag().getoFlag(), "!="));
			if (reverseCond) {
				Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "!=").evaluate());
				formulas.add((new Formula(l1, "not")).evaluate());
			} else {
				formulas.add((new Formula(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "!=")).evaluate());
			}
		} else if (instName.equals("jle") || instName.equals("jng")) {
			// if
			// ((SF) ≠ (OF)) or ((ZF) = 1)
			// Focus on ZF = 1
			// not finished
			// Change due to Demo1
			// OtherExp l1 = new OtherExp(env.getFlag().getzFlag(), new
			// ConstantBooleanValue(1), "=");
			Value l1 = env.getFlag().getZFlag();
			Value l2 = (new HybridBooleanValue(env.getFlag().getSFlag(), env.getFlag().getOFlag(), "xor")).evaluate();
			// OtherExp l3 = new OtherExp(l2, new ConstantLongExp(0), "!=");

			if (reverseCond) {
				Value l3 = (new HybridBooleanValue(l1, l2, "or")).evaluate();
				formulas.add((new Formula(l3, "not")).evaluate());
			} else {
				formulas.add((new Formula(l1, l2, "or")).evaluate());
			}

			// formulas.add(new Formula(env.getFlag().getzFlag(), new
			// ConstantBooleanValue(1), "="));

			/*
			 * OtherExp l1 = new OtherExp(env.getFlag().getcFlag(), "!=", new
			 * ConstantBooleanValue(0)); OtherExp l2 = new
			 * OtherExp(env.getFlag().getzFlag(), "!=", new
			 * ConstantBooleanValue(0)); formulas.add(new Formula(l1, l2,
			 * "and"));
			 */
		} else if (instName.equals("jnc")) {
			// if
			// ((CF) = 0)
			if (reverseCond) {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getCFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getCFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getCFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getCFlag(), new BooleanValue(0), "==")).evaluate());
				}
			}
		} else if (instName.equals("jne") || instName.equals("jnz")) {
			if (reverseCond) {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					}
					else {
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getZFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getZFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getZFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}
					else {
						// System.out.println("Right")
						;
					// Truong hop dung, khong lam gi ca
					}
				} else {
					formulas.add((new Formula(env.getFlag().getZFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
		} else if (instName.equals("jno")) {
			// if
			// ((OF) = 0)
			if (reverseCond) {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getOFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getOFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getoFlag(), new
			 * BooleanValue(0), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getoFlag(), new
			 * BooleanValue(0), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getoFlag(), new
			// ConstantBooleanValue(0), "=="));
		} else if (instName.equals("jns")) {
			// if
			// ((SF) = 0)
			if (reverseCond) {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getSFlag(), new BooleanValue(0), "==")).evaluate());
				}
				// formulas.add(new Formula(env.getFlag().getzFlag(), new
				// ConstantBooleanValue(0), "=="));
			}
		} else if (instName.equals("jnp") || instName.equals("jpo")) {
			// if
			// ((PF) = 0)
			if (reverseCond) {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getPFlag(), new BooleanValue(0), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getPFlag(), new BooleanValue(0), "==")).evaluate());
				}
			}
		} else if (instName.equals("jo")) {
			// if
			// ((OF) = 1)
			if (reverseCond) {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getOFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getOFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getOFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getOFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getoFlag(), new
			 * BooleanValue(1), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getoFlag(), new
			 * BooleanValue(1), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getoFlag(), new
			// ConstantBooleanValue(1), "=="));
		} else if (instName.equals("jp") || instName.equals("jpe")) {
			// if
			// ((PF) = 1)
			if (reverseCond) {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getPFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getPFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getPFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					}// else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);

					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getPFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getpFlag(), new
			 * BooleanValue(1), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getpFlag(), new
			 * BooleanValue(1), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getpFlag(), new
			// ConstantBooleanValue(0), "=="));
		} else if (inst.getName().equals("js")) {
			// if
			// ((SF) = 1)
			// var.add("sFlag", 0);
			if (reverseCond) {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getFlag().getSFlag(), new BooleanValue(1), "==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (env.getFlag().getSFlag() instanceof BooleanValue) {
					boolean z = ((BooleanValue) env.getFlag().getSFlag()).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (!z) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// else
						// this.curState.setFeasiblePath(false);
					// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getFlag().getSFlag(), new BooleanValue(1), "==")).evaluate());
				}
			}
			/*
			 * if (reCond) { Exp l1 = new
			 * AdditionBooleanValue(env.getFlag().getsFlag(), new
			 * BooleanValue(1), "=="); formulas.add(new Formula(l1, "not")); }
			 * else formulas.add(new Formula(env.getFlag().getsFlag(), new
			 * BooleanValue(1), "=="));
			 */
			// formulas.add(new Formula(env.getFlag().getsFlag(), new
			// ConstantBooleanValue(1), "=="));
		} else if (inst.getName().equals("jecxz")) {
			// if
			// (ECX = 0)
			// var.add("sFlag", 0);
			Value ecx = env.getRegister().getRegisterValue("ecx");
			if (reverseCond) {
				if (ecx instanceof LongValue) {
					long t = ((LongValue) ecx).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					// Can phai chinh sua them
					if (t == 0) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					Value l1 = (new HybridBooleanValue(env.getRegister().getRegisterValue("ecx"), new LongValue(0),
							"==").evaluate());
					formulas.add((new Formula(l1, "not")).evaluate());
				}
			} else {
				if (ecx instanceof LongValue) {
					long z = ((LongValue) ecx).getValue();
					// Truong hop sai
					// Je khong dung, nghia la khong the thuc hien lenh nhay
					// duoc, do dieu kien dang bi sai
					// phai tiep tuc thuc hien
					if (z != 0) {
						isFeasible = false;
						// System.out.println("Debug");
					} // else System.out.println("Right");
						// Truong hop dung, khong lam gi ca
				} else {
					formulas.add((new Formula(env.getRegister().getRegisterValue("ecx"), new LongValue(0), "=="))
							.evaluate());
				}
			}
		}

		if (isFeasible) {
			_solverResult = rule.checkZ3(formulas, curState);
			formulas.setResult(_solverResult);
			isFeasible = _solverResult.isSAT();
		// rule.generateNextInstruction(inst, path, pathList, condition);
		// Remove later
		// curState.setFeasiblePath(true);
		}

		return isFeasible;
	}

}
