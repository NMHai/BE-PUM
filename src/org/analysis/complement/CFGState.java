package org.analysis.complement;

import org.jakstab.asm.Instruction;
import org.jakstab.cfa.CFAEdge;
import org.jakstab.cfa.CFAEdge.Kind;
import org.jakstab.cfa.Location;
import org.jakstab.cfa.StateTransformer;
import org.jakstab.util.Logger;

public class CFGState implements Comparable<CFGState> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CFAEdge.class);

	/**
	 * The kind of a CFAEdge, i.e., it's value in a multi-valued logic.
	 */
	/*
	 * public enum Kind implements LatticeElement {
	 * 
	 * MAY, MUST;
	 * 
	 * @Override public LatticeElement join(LatticeElement l) { if
	 * (l.equals(MAY)) return this; else return MUST; }
	 * 
	 * @Override public boolean lessOrEqual(LatticeElement l) { return
	 * this.equals(MAY) || l.equals(MUST); }
	 * 
	 * @Override public boolean isTop() { return equals(MUST); }
	 * 
	 * @Override public boolean isBot() { return equals(MAY); } }
	 */

	private Location source;
	private Location target;
	private Instruction instSource, instDest;
	private StateTransformer transformer;
	private Kind kind;

	public CFGState(Location source, Location target, StateTransformer transformer, Kind kind2, Instruction instr) {
		this(source, target, transformer, kind2);
		this.instSource = instr;
		this.instDest = null;
	}

	public CFGState(Location source, Location target, StateTransformer transformer, Kind kind2, Instruction instr,
			Instruction ins) {
		this(source, target, transformer, kind2);
		this.instSource = instr;
		this.instDest = ins;
	}

	public CFGState(Location source, Location target, StateTransformer transformer, Kind kind) {
		super();
		assert (source != null && target != null) : "Cannot create edge with dangling edges: " + source + " -> "
				+ target;
		assert transformer != null : "Need to specify transformer for edge " + source + " -> " + target;
		assert kind != null : "Need to specify an edge kind";
		this.source = source;
		this.target = target;
		this.transformer = transformer;
		this.kind = kind;
	}

	public CFGState(Location source, Location target, StateTransformer transformer) {
		this(source, target, transformer, Kind.MAY);
	}

	/**
	 * @return the source
	 */
	public Location getSource() {
		return source;
	}

	/**
	 * @return the target
	 */
	public Location getTarget() {
		return target;
	}

	/**
	 * @return the state transformer
	 */
	public StateTransformer getTransformer() {
		return transformer;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public void setTransformer(StateTransformer t) {
		transformer = t;
		assert transformer != null : "Need to specify transformer for edge " + source + " -> " + target;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public void setTarget(Location target) {
		this.target = target;
	}

	@Override
	public String toString() {
		if (instSource != null && instDest != null)
			return source + ":" + instSource.getName() + " -" + kind + "-> " + target + ":" + instDest.getName();
		else if (instSource != null)
			return source + ":" + instSource.getName() + " -" + kind + "-> " + target;
		else
			return source + " -" + kind + "-> " + target;
	}

	@Override
	public int compareTo(CFGState o) {
		int c = source.compareTo(o.source);
		if (c != 0)
			return c;
		c = target.compareTo(o.target);
		if (c != 0)
			return c;
		if (kind.ordinal() < o.kind.ordinal())
			return -1;
		if (kind.ordinal() > o.kind.ordinal())
			return 1;
		// Source, target, and kind are the same, so compare transformers
		// somehow
		if (this.transformer.hashCode() < o.transformer.hashCode())
			return -1;
		if (this.transformer.equals(o.transformer))
			return 0;
		if (!this.instSource.compareInstruction(o.getSourceInstruction()))
			return 0;
		if (!this.instDest.compareInstruction(o.getDestInstruction()))
			return 0;

		return 1;
	}

	/**
	 * @return the instruction
	 */
	public Instruction getSourceInstruction() {
		return instSource;
	}

	/**
	 * @param instruction
	 *            the instruction to set
	 */
	public void setSourceInstruction(Instruction instruction) {
		this.instSource = instruction;
	}

	/**
	 * @return the instruction
	 */
	public Instruction getDestInstruction() {
		return instDest;
	}

	/**
	 * @param instruction
	 *            the instruction to set
	 */
	public void setDestInstruction(Instruction instruction) {
		this.instDest = instruction;
	}

}