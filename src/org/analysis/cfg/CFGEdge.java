package org.analysis.cfg;

import org.analysis.SymbolicState;

public class CFGEdge {
	private CFGVertex source;
	private CFGVertex destination;
	Condition cond;

	SymbolicState symbolicExecution;

	public CFGEdge(CFGVertex source, CFGVertex dest) {
		this.source = source;
		this.destination = dest;
		this.symbolicExecution = null;
		cond = new Condition();
	}

	public CFGEdge(CFGVertex source, CFGVertex dest, Condition cond) {
		this.source = source;
		this.destination = dest;
		this.symbolicExecution = null;
		this.cond = cond;
	}

	public CFGVertex getSource() {
		return source;
	}

	public void setSource(CFGVertex source) {
		this.source = source;
	}

	public CFGVertex getDestination() {
		return destination;
	}

	public void setDestination(CFGVertex destination) {
		this.destination = destination;
	}

	public SymbolicState getSymbolicExecution() {
		return symbolicExecution;
	}

	public void setSymbolicExecution(SymbolicState symbolicExecution) {
		this.symbolicExecution = symbolicExecution;
	}

	public boolean computeSymbolicState() {
		return true;
	}

	public Condition getCond() {
		return cond;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}

	public void printInfo() {
		System.out.println("Edge:");
		System.out.println("From Source Vertex:" + source.getAddr().toString() + " to Dest Vertex:"
				+ destination.getAddr().toString());
		cond.printInfo();
		System.out
				.println("---------------------------------------------------------------------------------------------");
	}

}
