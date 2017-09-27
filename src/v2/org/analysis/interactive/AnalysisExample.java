/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPPath;

/**
 *
 * @author zunc
 */
public class AnalysisExample extends AnalysisFactory {

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = String.format("0x00%s\t%s",
				Long.toHexString(address), program.getInstructionString(new AbsoluteAddress(address), env));
		System.out.println(strInst);
		return ACTION_OP.NOP;
	}

	@Override
	public void onMemoryRead(MEM_OP op, long address, Object readData) {
		String strInst = String.format(" - mem_read: 0x0%s\t %s", Long.toHexString(address), readData);
		if (op == MEM_OP.STRING) {
			System.out.println(strInst);
		}
	}

	@Override
	public void onMemoryWrite(MEM_OP op, long address, Object writeData) {
		String strInst = String.format(" - mem_write: 0x0%s", Long.toHexString(address));
		System.out.println(strInst);
	}

}
