/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.util.Map;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.value.Formula;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SolverResult;
import v2.org.analysis.value.SymbolValue;

/**
 *
 * @author zunc
 */
public class AnalysisDemoSE01 extends AnalysisFactory {
	private String strInstLine = "";

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = getInst(ins);
		strInstLine = String.format("0x00%s\t%s", Long.toHexString(address), strInst);
		System.out.println(strInstLine);

		//.text:00401520 mov     [esp+4], eax
		//.text:00401524 mov     dword ptr [esp], offset aS ; "%s"
		//.text:0040152B call    _scanf
		//.text:00401530 jmp     short loc_401581
		if (address == 0x0040152b) {
			int n = 8;
			long szBuffer = ((LongValue) register.getRegisterValue("eax")).getValue();
			System.err.println(" - SymbolicVar create: 0x" + Long.toHexString(szBuffer));
			for (int i = 0; i < n; i++) {
				SymbolValue symVar = new SymbolValue("var" + i);
				symVar.setType(SymbolValue.Type.BYTE);
				memory.setByteMemoryValue(szBuffer + i, symVar);
			}
			rule.generateNextInstructionForce(ins, path);
			//program.generageCFG("/asm/cfg/" + program.getFileName() + "_test");
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
		System.err.println(" [+] Branch: " + strInstLine);
//		System.err.println(" --- VariableList");
//		for (String varName : condition.getListVariable()) {
//			System.err.println(" - " + varName);
//		}
		System.err.println(" --- Condition");
		for (Formula cond : condition.getListFormula()) {
			cond.evaluate();
			System.err.println(" - " + cond.toString());
		}
		System.err.println(" => " + solver.toString());
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Long> entry : solver.getResult().entrySet()) {
			byte val = entry.getValue().byteValue();
			sb.append((char) val);
		}
		System.err.println(" ===>>> arg: \"" + sb.toString() + "\"");
		System.err.println(String.format(" - kOnSymBranch: %d, kReseuseBranch=%d", 
				_kOnSymBranch, _kReseuseBranch));
	}

}
