/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.util.List;
import org.jakstab.asm.Instruction;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Flag;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 *
 * @author zunc
 */
public class WannaCryAnalysis extends AnalysisBase {

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		// log
		String strInst = getInst(address, ins);
		String strInstLine = String.format("0x00%s\t%s", Long.toHexString(address), strInst);
//		System.out.println(strInstLine);

		//--- local stuff
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		Flag flag = env.getFlag();
		MemoryV2 memory = env.getMemory();
		Stack stack = env.getStack();
		Register register = env.getRegister();
		
		// .text:00401DF9	call    sub_4075AD
		if (address == 0x0401df9) {
			Value st1 = stack.pop(); // hFile
			Value st2 = stack.pop(); // int
			Value st3 = stack.pop(); // StrFile
			String strExtFile = memory.getText(1, ((LongValue) st3).getValue());
			System.out.println("[+] WannaCry_Proc: Extract resource by password \"" + strExtFile + "\"");
			rule.generateNextInstructionForce(ins, path);
			return ACTION_OP.PASS;
		} else if (address == 0x04013ff
				|| address == 0X040141f) {
			// .text:004013FF	jnz     short loc_4013FA
			// .text:0040141F	jnz     short loc_40141A
			flag.setZFlag(new BooleanValue(true));
		} else if (strInst.contains("0x004016d0")) {
			System.out.println("[+] WannaCry_Proc: Bypass error trap at: " + String.format("0x00%s", Long.toHexString(address)));
			rule.generateNextInstructionForce(ins, path);
			return ACTION_OP.PASS;
		} else if (address == 0x0401661) {
			// .text:00401661	call    sub_402A76
			for (int i=0; i < 5; i++) {
				stack.pop();
			}
			rule.generateNextInstructionForce(ins, path);
			return ACTION_OP.PASS;
		}
		return ACTION_OP.NOP;
	}

	@Override
	public Value onAPI(BPState state, long address, Instruction inst, String apiName, List<Long> params) {
		if (apiName.startsWith("fread")) {
			return _genSymbolicAPI(apiName + "_Ret");
		} else if (apiName.startsWith("fclose")) {
			return _genSymbolicAPI(apiName + "_Ret");
		}
		return null;
	}

}
