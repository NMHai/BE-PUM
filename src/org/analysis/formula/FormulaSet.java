package org.analysis.formula;

import java.util.ArrayList;
import java.util.List;

public class FormulaSet {
	private List<Formula> listFormula;
	private int num = 0;

	public FormulaSet() {
		listFormula = new ArrayList<Formula>();
	}

	public List<Formula> getListFormula() {
		return listFormula;
	}

	public Formula getTop() {
		return this.listFormula.get(num - 1);
	}

	public void setListFormula(List<Formula> listFormula) {
		this.listFormula = listFormula;
	}

	public boolean contain(Formula sf) {
		for (Formula x : this.listFormula)
			if (x.equal(sf))
				return true;
		return false;
	}

	public boolean add(Formula sf) {
		if (this.contain(sf))
			return false;
		this.listFormula.add(sf);
		this.num++;
		return true;
	}

	public boolean add(FormulaSet fs) {
		List<Formula> l = fs.getListFormula();
		if (l.size() == 0)
			return false;

		for (Formula i : l)
			add(i);
		return true;
	}

	public void printInfo() {
		System.out.println("Expression:");
		System.out.println(toStringPrefix());
		System.out.println();
	}

	public String toStringPrefix() {
		String result = "";

		if (listFormula.size() > 1) {
			result = "(and ";
			for (Formula f : listFormula)
				result += f.toStringPrefix();
			// System.out.println();
			result += " )";
		} else if (listFormula.size() > 0)
			result = listFormula.get(0).toStringPrefix();
		return result;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return listFormula.isEmpty();
	}
}
