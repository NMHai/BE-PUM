/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;

import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 *
 * @author zunc
 */
public class AnalysisEmdivi extends AnalysisFactory {

	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
//		String strInst = String.format("0x00%s\t%s",
//				Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address)));
//		System.out.println(strInst);

		boolean isHit = false;

//		if (address == 0x00427513) {
//			memory.toString();
//			System.out.println("debug");
//		}

		if ((address > 0x412044)
				&& (address <= 0x412096)) {
			rule.generateNextInstructionForce(ins, path);
			isHit = true;
		} else if (address == 0x41be1a) { // by pass loop trap
			System.out.println(" -> PASS");
			//0x41be14	pushl	%ebx
			//0x41be15	pushl	%ebx
			//0x41be16	pushl	$0x1<UINT8>
			//0x41be18	pushl	%ebx
			//0x41be19	pushl	%ebx
			//0x41be1a	call	0x0041bfd0			//<!> wtf proc
			// protect environement - ba?o ve^. mo^i truong
			for (int i = 1; i <= 5; i++) {
				stack.pop();
			}
			rule.generateNextInstructionForce(ins, path);
			register.setRegisterValue("eax", new LongValue(1));
			isHit = true;
		} else if (address == 0x4129f0) {
			// .text:004129F0 call    _rand
			Random rnd = new Random();
			int randVal = rnd.nextInt();
			randVal = randVal >= 0 ? randVal : -randVal;
			randVal = randVal % 32767;
			System.out.println(" -> randVal: " + randVal);
			register.setRegisterValue("eax", new LongValue(randVal));
			rule.generateNextInstructionForce(ins, path);
			isHit = true;
		} else if (address == 0x412014
				|| address == 0x412034) {
			//.text:00412013                 push    eax
			//.text:00412014                 call    procLoop01

			//.text:00412027                 push    ecx             ; void *
			//.text:00412028                 lea     edi, [ebp+var_1B4]
			//.text:0041202E                 lea     ecx, [ebp+var_BC]
			//.text:00412034                 call    sub_403210      ; xxxx pass
			System.out.println(" -> FORCE BY PASS");
			stack.pop();
			rule.generateNextInstructionForce(ins, path);
			register.setRegisterValue("eax", new LongValue(1));
			isHit = true;
		}
		/*else if (address == 0x41168F
				|| address == 0x4116C0) {
			System.out.println(" -> PASS");
			stack.pop();
			rule.generateNextInstructionForce(ins, path);
			register.setRegisterValue("eax", new LongValue(1));
			isHit = true;
		} */ /* else if (address == 0x41BB90 
				|| address == 0x41BD5F) {
			//.text:0041BD35 lea     eax, [esp+444h+hObject]
			//.text:0041BD3C push    eax
			//.text:0041BD3D lea     ecx, [esp+448h+var_424]
			//.text:0041BD41 push    ecx
			//.text:0041BD42 lea     edx, [esp+44Ch+var_414]
			//.text:0041BD46 push    edx
			//.text:0041BD47 lea     eax, [esp+450h+lpString2]
			//.text:0041BD4B push    eax
			//.text:0041BD4C lea     ecx, [esp+454h+var_3D4]
			//.text:0041BD53 lea     edx, [esp+454h+var_404]
			//.text:0041BD57 mov     byte ptr [esp+454h+var_4], 12h
			//.text:0041BD5F call    callCom
			System.out.println(" -> PASS");
			for (int i = 1; i <= 4; i++) {
				stack.pop();
			}
			rule.generateNextInstructionForce(ins, path);
			register.setRegisterValue("eax", new LongValue(0));
		} */

		return isHit ? ACTION_OP.PASS : ACTION_OP.NOP;
	}

	@Override
	public Value onAPI(BPState state, long address, Instruction inst, String apiName, List<Long> params) {
		if (file == null) {
			return null;
		}
		if (apiName.equals("HeapFree")) {
			long hHeap = params.get(0);
			long dwFlags = params.get(1);
			long lpMem = params.get(2);
			if (lpMem != 0) {
				String strPrintable = memory.getText(1, lpMem); //only get ascii string
				if (strPrintable.isEmpty()) {
					return null;
				}
				String strLog = String.format("0x00%s\t%s\n", Long.toHexString(address), strPrintable);
				try {
					file.write(strLog);
					file.flush();
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
		}
		return null;
	}

}
