/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import org.jakstab.asm.Instruction;

import v2.org.analysis.path.BPPath;

/**
 *
 * @author zunc
 */
public class PetiteHelloWorld extends AnalysisFactory {

	private String strInstLine = "";
	private int n = 32;

	@Override
	public ACTION_OP onInstruction(BPPath path, long address, Instruction ins) {
		String strInst = getInst(address, ins);
		strInstLine = String.format("0x00%s\t%s", Long.toHexString(address), strInst);

		System.out.println(strInstLine);
		System.out.println(env.getRegister().toString());
		//0x00401036	pushl	$0x0<UINT8>
		//0x00401038	leal	-4(%ebp), %eax
		//0x0040103b	pushl	%eax
		//0x0040103c	pushl	$0x32<UINT8>
		//0x0040103e	pushl	$0x402159<UINT32>
		//0x00401043	pushl	-12(%ebp)
		//0x00401046	call	ReadFile@kernel32.dll
		if (address == 0x040aa92) {
			System.out.println(env.getRegister().toString());
		} 
		return ACTION_OP.NOP;
	}
}
