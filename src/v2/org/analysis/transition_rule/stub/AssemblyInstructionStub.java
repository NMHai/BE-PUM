/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.transition_rule
 * File name: AssemblyInstructionStub.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.transition_rule.stub;

import java.util.List;

import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.Value;

/**
 * This stub abstract class is used for describe some primary attributes &
 * methods
 * 
 * @author Yen Nguyen
 * 
 */
public abstract class AssemblyInstructionStub {
	protected String groupName;

	protected X86Instruction inst;
	protected BPPath path;
	protected List<BPPath> pathList;
	protected X86TransitionRule rule;

	protected BPState curState;
	protected Operand dest;
	protected Operand src;
	protected Environment env;

	protected Value d = null;
	protected Value s = null;
	protected int opSize;

	protected List<Long> params = null;

	public BPState run(X86Instruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		this.inst = inst;
		this.path = path;
		this.pathList = pathList;
		this.rule = rule;

		this.initAttributes();

		BPState ret = null;
		try {
			if ((ret = this.preExecute()) != null) {
				return ret;
			}

			if ((ret = this.execute()) != null) {
				return ret;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public String getASMName() {
		return getClass().getSimpleName();
	}

	public String getFullName() {
		return String.format("%s@%s.ASM", this.getASMName(), this.getGroupName());
	}

	protected void initAttributes() {
		curState = path.getCurrentState();
		dest = inst.getOperand1();
		src = inst.getOperand2();
		env = curState.getEnvironement();
		opSize = rule.getBitCount(inst);
	}

	protected abstract BPState preExecute();

	protected abstract void postExecute();

	public abstract BPState execute();
}
