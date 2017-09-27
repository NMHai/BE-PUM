/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.io.FileWriter;
import java.io.IOException;
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
public class AnalysisKeygen extends AnalysisFactory {

	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}

	private long _curIP;
	private String strInstLine = "";
	private long szBuffer = 0;
	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = program.getInstructionString(new AbsoluteAddress(address), env);
		strInstLine = String.format("0x00%s\t%s", Long.toHexString(address), strInst);
//		System.out.println(strInstLine);
//		ins.getOperand(0); // dest
//		ins.getOperand(1); // src
		if (address == 0x0040109a) {
			// .text:0040109A	call    _sprintf
			//System.err.println(" --- debug");
		}

		_curIP = address;
		if (address == 0x004010e6) {
			//lea     esi, [esp+13Ch+szSerial]
			//lea     eax, [esp+13Ch+szBuffer]
			long esi = ((LongValue) register.getRegisterValue("esi")).getValue();
			long eax = ((LongValue) register.getRegisterValue("eax")).getValue();
			String strSerial = memory.getText(1, esi);
			System.err.println("Key is: " + strSerial);
//			memory.setText(1, eax, strSerial);
//			rule.generateNextInstructionForce(ins, path);
//			return ACTION_OP.PASS;
		}

//		//.text:0040107E movsx   ecx, [esp+esi+13Ch+szKey]
//		//.text:00401083 movsx   edx, [esp+ebp+13Ch+szBuffer]
//		//.text:00401088 xor     ecx, edx
//		//.text:0040108A lea     eax, [esp+13Ch+szSerial]
//		//.text:0040108E push    ecx
//		if (address == 0x00401088) {
//			long ecx = ((LongValue) register.getRegisterValue("ecx")).getValue();
//			int n = 4;
//			for (int i = 0; i < n; i++) {
//				memory.setByteMemoryValue(ecx + i, new SymbolValue("var0_" + i));
//			}
//		} 
		//.text:00401053 push    eax
		//.text:00401054 push    offset aS                       ; "%s"
		//.text:00401059 call    _scanf
		//.text:0040105E lea     edi, [esp+144h+szBuffer]
		//.text:00401062 or      ecx, 0FFFFFFFFh
		//if (address == 0x00401059) {
		
		if (address == 0x00401059) {
			szBuffer = ((LongValue) register.getRegisterValue("eax")).getValue();
		} else if (address == 0x0040105e) {
			int n = 4;
			long offset = szBuffer ;
			for (int i = 0; i < n; i++) {
				memory.setByteMemoryValue(offset + i, new SymbolValue("szName_" + i));
			}

//			rule.generateNextInstructionForce(ins, path);
//			return ACTION_OP.PASS;
		} else if (address == 0x00401083) {
			System.err.println(strInstLine);
		} else if (address == 0x4010e6) {
			//.text:004010E2 lea     esi, [esp+13Ch+szSerial]
			//.text:004010E6 lea     eax, [esp+13Ch+szBuffer] ; strcmp
			
			int n = 8;
			long esi = ((LongValue) register.getRegisterValue("esi")).getValue();
			for (int i = 0; i < n; i++) {
				Value var = memory.getByteMemoryValue(esi + i);
				System.err.println("var: " + var);
			}
		} else if (address == 0x00401090) {
			Value ecx = register.getRegisterValue("ecx");
			System.err.println(" - ecx: " + ecx);
		} else if (address == 0x00401bd5) {
			System.err.println(strInstLine);
			System.out.println("Debug");
		}
		return ACTION_OP.NOP;
	}

	@Override
	public void onSymbolicBranch(X86CondJmpInstruction inst, Formulas condition, SolverResult solver) {
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
		//System.out.println(" ===>>> arg: \"" + sb.toString() + "\"");
	}
	
}
