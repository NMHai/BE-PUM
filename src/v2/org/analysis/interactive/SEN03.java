/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.util.Map;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.value.Formula;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SolverResult;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 *
 * @author zunc
 */
public class SEN03 extends AnalysisFactory {

	private String strInstLine = "";
	private long addrInput;
	private boolean _isShowBranch = true;
	private int n = 12;

	private void printFomula(Formulas condition) {
		System.err.println(" --- Condition");
		for (Formula cond : condition.getListFormula()) {
			cond.evaluate();
			System.err.println(" - " + cond.toString());
		}
	}

	private String _getSymbolicString(SolverResult solver) {
		String sbResult = "";
		Map<String, Long> var = solver.getResult();
		for (int i = 0; i < n; i++) {
			String varName = String.format("k%d", i);
			if (!var.containsKey(varName)) {
				break;
			}
			sbResult += (char) var.get(varName).byteValue();
		}
		return sbResult;
	}

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = getInst(address, ins);
		strInstLine = String.format("0x00%s\t%s", Long.toHexString(address), strInst);

		//System.out.println(strInstLine);
		//0040151D                 call    _scanf
		if (address == 0x040151d) {
			long szBuffer = ((LongValue) register.getRegisterValue("eax")).getValue();
			addrInput = szBuffer;
			System.err.println(" - SymbolicVar create: 0x" + Long.toHexString(szBuffer));
			for (int i = 0; i < n; i++) {
				String varName = String.format("k%d", i);
				SymbolValue symVar = new SymbolValue(varName);
				symVar.setType(SymbolValue.Type.CHAR);
				memory.setByteMemoryValue(szBuffer + i, symVar);
			}
			rule.generateNextInstructionForce(ins, path);
		} else if (address == 0x04017ad) {
			// puts
			Formulas pathCond = path.getPathCondition();
			//printFomula(pathCond);
			SolverResult solver = pathCond.getResult();
			System.err.println(" =>> FLAG: \"" + _getSymbolicString(solver) + "\"");
		}
		return ACTION_OP.NOP;
	}

	private int _kOnSymBranch = 0;
	private int _kReseuseBranch = 0;

	@Override
	public void onSymbolicBranch(X86CondJmpInstruction inst, Formulas condition, SolverResult solver) {
		_kOnSymBranch++;
		if (solver.isReseuse()) {
			_kReseuseBranch++;
			return;
		}
		if (_isShowBranch) {
			System.err.println(String.format(" - Branch[%d] arg=\"%s\"",
					_kOnSymBranch, _getSymbolicString(solver)));
		}
	}

}
