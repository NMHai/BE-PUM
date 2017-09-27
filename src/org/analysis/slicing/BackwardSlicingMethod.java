package org.analysis.slicing;

import org.analysis.concolic_testing.TestCaseValue;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.cfg.AddressList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BackwardSlicingMethod {
	/*
	 * public static boolean slicingAlgorithm(AddressList trace, AbsoluteAddress
	 * indirectTarget, Map<AbsoluteAddress, Instruction> map) { // List<String>
	 * relevant int pos = trace.getPos(indirectTarget); List<String> relevant =
	 * new ArrayList<String>(); Instruction ins = map.get(indirectTarget); if
	 * (ins.getOperand(0).getClass().getSimpleName().equals("X86Register"))
	 * relevant.add(ins.getOperand(0).toString().substring(1)); else if
	 * (ins.getOperand(0).getClass().getSimpleName()
	 * .equals("X86MemoryOperand")) relevant.add(ins.getOperand(0).toString());
	 * else return false;
	 * 
	 * if (pos < 0) return false; else { for (int i = pos - 1; i >= 0; i--) {
	 * ins = map.get(trace.get(i)); if (ins.getOperandCount() == 2) { Operand
	 * op0 = ins.getOperand(0); Operand op1 = ins.getOperand(1); if
	 * (contain(op0, relevant)) { if (!contain(op1, relevant)) add(op1,
	 * relevant); } else if (contain(op1, relevant)) add(op0, relevant); else
	 * trace.remove(i); } else if (ins.getOperandCount() == 1) { if (!(ins
	 * instanceof X86CondJmpInstruction)) { Operand op0 = ins.getOperand(0); if
	 * (!contain(op0, relevant)) trace.remove(i); } } } } return true; }
	 */

	private static void add(Operand op, List<String> relevant) {
		String target = "";
		if (op.getClass().getSimpleName().equals("X86Register"))
			target = op.toString().substring(1);
		else if (op.getClass().getSimpleName().equals("X86MemoryOperand"))
			target = ((X86MemoryOperand) op).toString();
		if (target != "")
			relevant.add(target);
	}

	private static boolean contain(Operand op, List<String> relevant) {
		// TODO Auto-generated method stub
		String target = "";
		if (op.getClass().getSimpleName().equals("X86Register"))
			target = op.toString().substring(1);
		else if (op.getClass().getSimpleName().equals("X86MemoryOperand"))
			target = ((X86MemoryOperand) op).toString();
		for (String str : relevant)
			if (str.equals(target))
				return true;
		return false;
	}

	public static boolean slicingAlgorithm(AddressList trace, AbsoluteAddress indirectTarget,
			Map<AbsoluteAddress, Instruction> map, TestCaseValue sv) {
		int pos = trace.getPos(indirectTarget);
		List<String> relevant = new ArrayList<String>();
		Instruction ins = map.get(indirectTarget);
		if (ins.getOperand(0).getClass().getSimpleName().equals("X86Register")) {
			relevant.add(ins.getOperand(0).toString().substring(1));
			sv.add(ins.getOperand(0).toString().substring(1), 0);
		} else if (ins.getOperand(0).getClass().getSimpleName().equals("X86MemoryOperand")) {
			relevant.add(ins.getOperand(0).toString());
			sv.add("op_addr_" + ((X86MemoryOperand) ins.getOperand(0)).toString(), 0);
		} else
			return false;

		if (pos < 0)
			return false;
		else {
			for (int i = pos - 1; i >= 0; i--) {
				ins = map.get(trace.get(i));
				for (int j = 0; j < ins.getOperandCount(); j++)
					if (ins.getOperand(j).getClass().getSimpleName().equals("X86Register"))
						sv.add(ins.getOperand(j).toString().substring(1), 0);
					else if (ins.getOperand(j).getClass().getSimpleName().equals("X86MemoryOperand"))
						sv.add("op_addr_" + ((X86MemoryOperand) ins.getOperand(j)).toString(), 0);

				if (ins.getOperandCount() == 2) {
					Operand op0 = ins.getOperand(0);
					Operand op1 = ins.getOperand(1);
					if (ins.getName().startsWith("cmp") || ins.getName().startsWith("test")) {
						add(op0, relevant);
						add(op1, relevant);
					} else {
						if (contain(op0, relevant)) {
							if (!contain(op1, relevant))
								add(op1, relevant);
						} else if (contain(op1, relevant))
							add(op0, relevant);
						else
							trace.remove(i);
					}
				} else if (ins.getOperandCount() == 1) {
					Operand op0 = ins.getOperand(0);
					if (!contain(op0, relevant))
						if (!(ins instanceof X86CondJmpInstruction)) {
							trace.remove(i);
						}
				}
			}
		}
		return true;

	}

}
