package v2.org.analysis.loop;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import java.util.ArrayList;
import java.util.List;

public class LoopHandle {
	private int loop_num = 0;
	private List<Long> loopTrace;
	// private Instruction entryInst = null;
	private BPVertex loopHead = null;
	private boolean check = false;
	private boolean stop = false;

	public LoopHandle() {
		setLoopTrace(new ArrayList<Long>());
	}

	public int getNumLoop() {
		return loop_num;
	}

	public List<Long> getLoopTrace() {
		return loopTrace;
	}

	public void setLoopTrace(List<Long> loopTrace) {
		this.loopTrace = loopTrace;
	}

	public void addLoopNode(Long l) {
		if (!containLoopNode(l))
			loopTrace.add(l);
	}

	public boolean containLoopNode(Long node) {
		for (Long t : loopTrace) {
			if (t.longValue() == node.longValue())
				return true;
		}

		return false;
	}

	public BPVertex getLoopHead() {
		return loopHead;
	}

	public void setLoopHead(BPVertex loopHead) {
		this.loopHead = loopHead;
	}

	public void setNumLoop(int i) {
		// TODO Auto-generated method stub
		this.loop_num = i;
	}

	public LoopHandle clone() {
		LoopHandle ret = new LoopHandle();
		ret.setNumLoop(loop_num);
		ret.setLoopHead(loopHead);
		ret.setCheck(check);
		ret.setStop(stop);
		List<Long> ltrace = new ArrayList<Long>();

		if (loopTrace == null)
			return ret;

		for (Long t : loopTrace)
			ltrace.add(t);

		ret.setLoopTrace(ltrace);

		return ret;
	}

	@Override
	public String toString() {
		String ret = "Trace Loop: ";
		for (Long t : loopTrace)
			ret += new AbsoluteAddress(t.longValue()) + ", ";

		ret += "Loop Num:" + loop_num + ", " + "Loop Head: " + loopHead.getAddress();

		return ret;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean run) {
		this.check = run;
	}

	public void clear() {
		// TODO Auto-generated method stub
		setLoopHead(null);
		setNumLoop(0);
		setLoopTrace(null);
		setCheck(false);
		setStop(false);
	}

	public void setStop(boolean b) {
		// TODO Auto-generated method stub
		stop = b;
	}

	public boolean isStop() {
		return stop;
	}

	public boolean checkFormulas(String instName, BPState curState) {
		// TODO Auto-generated method stub
		Environment env = curState.getEnvironement();
		if (instName.equals("loop")) {
			// Dang xu li, chua hoan hao
			// Se check sau voi 1 so truong hop cu the
			Value ecx = env.getRegister().getRegisterValue("ecx");
			if (ecx instanceof LongValue)
				return false;
		} else if (instName.equals("ja") || instName.equals("jnbe")) {
			// if
			// ((CF) = 0) or ((ZF) = 0)
			// Not finished
			if (env.getFlag().getCFlag() instanceof BooleanValue || env.getFlag().getZFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jae") || instName.equals("jnb")) {
			// if
			// ((CF) = 0)
			if (env.getFlag().getCFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jb") || instName.equals("jnae")) {
			// if
			// ((CF) = 1)
			if (env.getFlag().getCFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jbe") || instName.equals("jna")) {
			// if
			// ((CF) = 1) or ((ZF) = 1)
			// Not finished
			if (env.getFlag().getCFlag() instanceof BooleanValue || env.getFlag().getZFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jc")) {
			// if
			// ((CF) = 1)
			if (env.getFlag().getCFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jcxz")) {
			// if
			// ((CF) = 0)
			if (env.getFlag().getCFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("je") || instName.equals("jz")) {
			// if
			// ((ZF) = 1)
			if (env.getFlag().getZFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jg") || instName.equals("jnle")) {
			// if
			// ((SF) = (OF)) and ((ZF) = 0)
			if (env.getFlag().getSFlag() instanceof BooleanValue && env.getFlag().getOFlag() instanceof BooleanValue
					&& env.getFlag().getZFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jge") || instName.equals("jnl")) {
			// if
			// (SF) = (OF)
			if (env.getFlag().getSFlag() instanceof BooleanValue && env.getFlag().getOFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jl") || instName.equals("jnge")) {
			// if
			// (SF) ≠ (OF)
			// formulas.add(new Formula(env.getFlag().getsFlag(),
			// env.getFlag().getoFlag(), "!="));
			if (env.getFlag().getSFlag() instanceof BooleanValue && env.getFlag().getOFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jle") || instName.equals("jng")) {
			// if
			// ((SF) ≠ (OF)) or ((ZF) = 1)
			// Focus on ZF = 1
			// not finished
			// Change due to Demo1
			if (env.getFlag().getSFlag() instanceof BooleanValue && env.getFlag().getOFlag() instanceof BooleanValue
					|| (env.getFlag().getZFlag() instanceof BooleanValue))
				return false;
		} else if (instName.equals("jnc")) {
			// if
			// ((CF) = 0)
			if (env.getFlag().getCFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jne") || instName.equals("jnz")) {
			if (env.getFlag().getZFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jno")) {
			// if
			// ((OF) = 0)
			if (env.getFlag().getOFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jns")) {
			// if
			// ((SF) = 0)
			if (env.getFlag().getSFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jnp") || instName.equals("jpo")) {
			// if
			// ((PF) = 0)
			if (env.getFlag().getPFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jo")) {
			// if
			// ((OF) = 1)
			if (env.getFlag().getOFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jp") || instName.equals("jpe")) {
			// if
			// ((PF) = 1)
			if (env.getFlag().getPFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("js")) {
			// if
			// ((SF) = 1)
			// var.add("sFlag", 0);
			if (env.getFlag().getSFlag() instanceof BooleanValue)
				return false;
		} else if (instName.equals("jecxz")) {
			// if
			// (ECX = 0)
			// var.add("sFlag", 0);
			Value ecx = env.getRegister().getRegisterValue("ecx");
			if (ecx instanceof LongValue)
				return false;
		}

		Program.getProgram().getLog()
				.infoString("LoopHandle: Stop path True with " + instName + " at " + curState.getLocation() + "\n");
		return true;
	}
}
