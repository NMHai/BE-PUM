package org.analysis.cfg;

import org.jakstab.asm.Instruction;

public class X86InstructionType {
	public static int checkType(Instruction ins) {
		// Instructions stop the program
		if (ins.getName().startsWith("ret") || ins.getName().startsWith("halt"))
			return 1;
		else // Instructions other
		if (ins.getName().startsWith("mov") || ins.getName().startsWith("sub") || ins.getName().startsWith("inc")
				|| ins.getName().startsWith("dec") || ins.getName().startsWith("sh") || ins.getName().startsWith("add"))
			return 2;
		else // Instruction compare value or change the value of flat
		if (ins.getName().startsWith("cmp"))
			return 3;
		else // Instruction Jump
		if (ins.getName().startsWith("jmp") || ins.getName().startsWith("cal") || ins.getName().startsWith("lret"))
			return 4;
		else // Instruction jump with condition
		if (ins.getName().startsWith("j") && (!ins.getName().startsWith("jmp")))
			return 5;
		else if (ins.getName().startsWith("loop"))
			return 6;
		return 2;
	}
}
