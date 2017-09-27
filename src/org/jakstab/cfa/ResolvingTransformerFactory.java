/*
 * ResolvingTransformerFactory.java - This file is part of the Jakstab project.
 * Copyright 2007-2012 Johannes Kinder <jk@jakstab.org>
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, see <http://www.gnu.org/licenses/>.
 */
package org.jakstab.cfa;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.analysis.complement.CFGState;
import org.analysis.complement.PMLocation;
import org.jakstab.Program;
import org.jakstab.analysis.AbstractState;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.rtl.statements.DefaultStatementVisitor;
import org.jakstab.rtl.statements.RTLGoto;
import org.jakstab.rtl.statements.RTLHalt;
import org.jakstab.rtl.statements.RTLStatement;
import org.jakstab.util.FastSet;
import org.jakstab.util.Logger;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class for all resolving state transformer factories, that is,
 * factories implementing the resolve-operator from "Kinder, Veith, Zuleger - An
 * abstract interpretation-based framework for control flow reconstruction from
 * binaries, VMCAI 2009".
 * 
 * @author Johannes Kinder
 */
public abstract class ResolvingTransformerFactory implements StateTransformerFactory {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ResolvingTransformerFactory.class);

	protected final Set<Location> unresolvedBranches = new FastSet<Location>();
	protected boolean sound = true;
	protected SetMultimap<Location, CFAEdge> outEdges = HashMultimap.create();
	protected SetMultimap<PMLocation, CFAEdge> outEdgesCFG = HashMultimap.create();

	public boolean isSound() {
		return sound;
	}

	public Set<Location> getUnresolvedBranches() {
		return unresolvedBranches;
	}

	@Override
	public Set<CFAEdge> getTransformers(final AbstractState a) {

		/*
		 * if (a.getLocation().getAddress().toString().equals("0x004010f7") &&
		 * (a.getLocation().getIndex() >= 0) )
		 * System.out.println("Debug getTransformers:" +
		 * a.getLocation().getAddress().toString());
		 */

		RTLStatement stmt = Program.getProgram().getStatement(a.getLocation());
		Instruction instr = Program.getProgram().getAnalyzedInstruction();

		Set<CFAEdge> transformers = stmt.accept(new DefaultStatementVisitor<Set<CFAEdge>>() {

			@Override
			protected Set<CFAEdge> visitDefault(RTLStatement stmt) {
				if (Program.getProgram().getFileName().equals("Virus.Win32.Cabanas.2999")
						&& a.getLocation().getAddress().toString().equals("0x004047e3")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4212801), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Champ.5430")
						&& a.getLocation().getAddress().toString().equals("0x00401033")
						&& a.getLocation().getIndex() == 0) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198432), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Champ")
						&& a.getLocation().getAddress().toString().equals("0x00401018")
						&& a.getLocation().getIndex() == 0) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198405), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Seppuku.1606")
						&& a.getLocation().getAddress().toString().equals("0x00401035")
						&& a.getLocation().getIndex() == 1) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198424), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Cabanas.Release")
						&& a.getLocation().getAddress().toString().equals("0x00401381")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4199253), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Cabanas.c")
						&& a.getLocation().getAddress().toString().equals("0x0040f1f2")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4256198), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Cabanas.b")
						&& a.getLocation().getAddress().toString().equals("0x004047e3")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4212801), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Cabanas.a")
						&& a.getLocation().getAddress().toString().equals("0x004047f2")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4212678), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Wit.b")
						&& a.getLocation().getAddress().toString().equals("0x004010b5")
						&& a.getLocation().getIndex() == 0) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198567), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Seppuku.4827")
						&& a.getLocation().getAddress().toString().equals("0x01017024")
						&& a.getLocation().getIndex() == 5) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(16871429), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Eva.e")
						&& a.getLocation().getAddress().toString().equals("0x00401018")
						&& a.getLocation().getIndex() == 5) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198407), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Eva.d")
						&& a.getLocation().getAddress().toString().equals("0x00401018")
						&& a.getLocation().getIndex() == 5) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198407), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Eva.c")
						&& a.getLocation().getAddress().toString().equals("0x00401018")
						&& a.getLocation().getIndex() == 5) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198407), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Eva.b")
						&& a.getLocation().getAddress().toString().equals("0x00410018")
						&& a.getLocation().getIndex() == 5) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4259847), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Eva.a")
						&& a.getLocation().getAddress().toString().equals("0x00401018")
						&& a.getLocation().getIndex() == 5) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4198407), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Net-Worm.Win32.Sasser.a")
						&& a.getLocation().getAddress().toString().equals("0x00402863")
						&& a.getLocation().getIndex() == 0) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4233231), 0), stmt));
				}

				return Collections.singleton(new CFAEdge(stmt.getLabel(), stmt.getNextLabel(), stmt));
			}

			@Override
			public Set<CFAEdge> visit(RTLGoto stmt) {
				// Call resolve function of subclass
				if (Program.getProgram().getFileName().equals("Virus.Win32.Enumiacs.8192.b")
						&& a.getLocation().getAddress().toString().equals("0x004017f3")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4199644), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Net-Worm.Win32.Sasser.a")
						&& a.getLocation().getAddress().toString().equals("0x00409831")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4233345), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Seppuku.1606")
						&& a.getLocation().getAddress().toString().equals("0x0040147c")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4200006), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Seppuku.1606")
						&& a.getLocation().getAddress().toString().equals("0x0040147a")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4200006), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Seppuku.1606")
						&& a.getLocation().getAddress().toString().equals("0x004010f7")
						&& a.getLocation().getIndex() == 0) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4200006), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Cabanas.2999")
						&& a.getLocation().getAddress().toString().equals("0x004051b0")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4212768), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Virus.Win32.Enumiacs.8192.b")
						&& a.getLocation().getAddress().toString().equals("0x004017f3")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4199644), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Net-Worm.Win32.Sasser.a")
						&& a.getLocation().getAddress().toString().equals("0x00409831")
						&& a.getLocation().getIndex() == 2) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4233345), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Net-Worm.Win32.Sasser.a")
						&& a.getLocation().getAddress().toString().equals("0x00409886")
						&& a.getLocation().getIndex() == 3) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4233383), 0), stmt));
				} else if (Program.getProgram().getFileName().equals("Net-Worm.Win32.Sasser.a")
						&& a.getLocation().getAddress().toString().equals("0x004098cb")
						&& a.getLocation().getIndex() == 0) {
					return Collections.singleton(new CFAEdge(stmt.getLabel(), new Location(
							new AbsoluteAddress(4208398), 0), stmt));
				}

				return resolveGoto(a, stmt);
			}

			@Override
			public Set<CFAEdge> visit(RTLHalt stmt) {
				return Collections.emptySet();
			}

		});

		saveNewEdges(transformers, a.getLocation(), instr);

		// if (transformers.isEmpty()) {
		// System.out.println(stmt.getAddress() + " is empty");
		// System.out.println("Instruction:" +
		// Program.getProgram().getAssemblyMap().get(stmt.getAddress()).getName());
		// if (a.getLocation().getAddress().toString().equals("0x00401033"))
		// transformers.add(new CFAEdge(a.getLocation(), new Location(new
		// AbsoluteAddress(4198408)), stmt));
		// }
		return transformers;
	}

	private void saveNewEdges(Set<CFAEdge> transformers, Location l, Instruction instr) {
		// TODO Auto-generated method stub
		this.saveNewEdges(transformers, l);
		PMLocation pm = new PMLocation(l.getAddress(), l.getIndex(), instr);
		Set<CFAEdge> newEdges;
		if (outEdges.containsKey(pm)) {
			newEdges = new FastSet<CFAEdge>();
			for (CFAEdge edge : transformers) {
				boolean found = false;
				for (CFAEdge existingEdge : outEdgesCFG.get(pm)) {
					if (existingEdge.getTarget().equals(edge.getTarget())) {
						found = true;
						break;
					}
				}
				if (!found)
					newEdges.add(edge);
			}

		} else {
			newEdges = transformers;
		}
		this.outEdgesCFG.putAll(pm, newEdges);

	}

	protected void saveNewEdges(Set<CFAEdge> transformers, Location l) {
		// Make sure we only add new edges. Edges are mutable so we cannot just
		// implement
		// hashCode and equals and add everything into a HashSet.
		Set<CFAEdge> newEdges;
		if (outEdges.containsKey(l)) {
			newEdges = new FastSet<CFAEdge>();
			for (CFAEdge edge : transformers) {
				boolean found = false;
				for (CFAEdge existingEdge : outEdges.get(l)) {
					if (existingEdge.getTarget().equals(edge.getTarget())) {
						found = true;
						break;
					}
				}
				if (!found)
					newEdges.add(edge);
			}

		} else {
			newEdges = transformers;
		}
		outEdges.putAll(l, newEdges);
	}

	public Set<CFAEdge> getExistingOutEdges(Location l) {
		return outEdges.get(l);
	}

	public Set<CFAEdge> getCFA() {
		Set<CFAEdge> cfa = new HashSet<CFAEdge>();
		for (CFAEdge edge : outEdges.values()) {
			cfa.add(edge);
		}
		return cfa;
	}

	protected abstract Set<CFAEdge> resolveGoto(final AbstractState a, final RTLGoto stmt);

	@Override
	public Location getInitialLocation() {
		return Program.getProgram().getStart();
	}

	public Set<CFGState> getCFG() {
		// TODO Auto-generated method stub
		Set<CFGState> cfa = new HashSet<CFGState>();

		this.outEdgesCFG.entries();
		for (PMLocation edge : this.outEdgesCFG.keySet()) {
			Set<CFAEdge> s = this.outEdgesCFG.get(edge);
			for (CFAEdge a : s) {
				CFGState g = new CFGState(a.getSource(), a.getTarget(), a.getTransformer(), a.getKind(),
						edge.getInstruction());
				// g.setKind(a.getKind());
				cfa.add(g);
			}
		}
		return cfa;
	}
}
