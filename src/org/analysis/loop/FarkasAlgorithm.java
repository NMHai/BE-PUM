package org.analysis.loop;

import org.analysis.formula.FormulaSet;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import v2.org.analysis.cfg.AddressList;

import java.util.Map;

public class FarkasAlgorithm implements LoopInvariantSolver {
	AbsoluteAddress target;
	Map<AbsoluteAddress, Instruction> assemblyMap;
	AddressList traceList;

	public FarkasAlgorithm(AbsoluteAddress loopInst, Map<AbsoluteAddress, Instruction> assemblyMap,
			AddressList traceList) {
		// TODO Auto-generated constructor stub
		this.target = loopInst;
		this.assemblyMap = assemblyMap;
		this.traceList = traceList;
	}

	@Override
	public FormulaSet resolve() {
		FormulaSet result = new FormulaSet();
		AddressList first = new AddressList(), second = new AddressList();
		Instruction inst = assemblyMap.get(target);
		Long temp = ((X86PCRelativeAddress) inst.getOperand(0)).getEffectiveValue(target.getValue());
		AbsoluteAddress cutPoint = new AbsoluteAddress(temp);
		// inst.
		if (cut(first, second, cutPoint)) {
			first.printInfo();
			second.printInfo();
			System.out.print("Invariant of this loop:");
			SymbolicExecutionFarkas sf = new SymbolicExecutionFarkas(first, assemblyMap);
			SymbolicExecutionFarkas ss = new SymbolicExecutionFarkas(second, assemblyMap);
			result = resolveFarkasAlgo(sf.getFormulas(), ss.getFormulas());
			result.printInfo();
		}
		return result;

	}

	private FormulaSet resolveFarkasAlgo(FormulaSet formulas, FormulaSet formulas2) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean cut(AddressList first, AddressList second, AbsoluteAddress cutPoint) {
		// TODO Auto-generated method stub
		if (!(traceList.contain(target) || traceList.contain(cutPoint)))
			return false;
		boolean c = true;
		for (int i = 0; i < traceList.length(); i++) {
			AbsoluteAddress t = traceList.get(i);
			if (t.getValue() == target.getValue())
				break;
			if (t.getValue() == cutPoint.getValue())
				c = false;
			if (c)
				first.add(t);
			else
				second.add(t);
		}
		return true;
	}

}
