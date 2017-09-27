/*
 * ProgramGraphWriter.java - This file is part of the Jakstab project.
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
package org.jakstab;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.analysis.complement.CFGState;
import org.analysis.complement.PMLocation;
import org.jakstab.analysis.AbstractReachabilityTree;
import org.jakstab.analysis.AbstractState;
import org.jakstab.analysis.ReachedSet;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.BranchInstruction;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.ReturnInstruction;
import org.jakstab.asm.SymbolFinder;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86CallInstruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MoveInstruction;
import org.jakstab.cfa.CFAEdge;
import org.jakstab.cfa.CFAEdge.Kind;
import org.jakstab.cfa.Location;
import org.jakstab.loader.ExecutableImage;
import org.jakstab.rtl.statements.RTLHalt;
import org.jakstab.rtl.statements.RTLStatement;
import org.jakstab.util.Characters;
import org.jakstab.util.GraphMLWriter;
import org.jakstab.util.GraphWriter;
import org.jakstab.util.GraphvizWriter;
import org.jakstab.util.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;

/**
 * Writes various graphs from a program structure.
 * 
 * @author Johannes Kinder
 */
public class ProgramGraphWriter {

	private static final Logger logger = Logger.getLogger(ProgramGraphWriter.class);
	private Program program;

	private Set<Location> mustLeaves;
	private Set<Location> locations;
	private SetMultimap<Location, CFAEdge> inEdges;
	private SetMultimap<Location, CFAEdge> outEdges;
	private int numNodes;
	private int numEdges;

	public int getNodesCount() {
		return numNodes;
	}

	public int getEdgesCount() {
		return numEdges;
	}

	public ProgramGraphWriter(Program program) {
		this.program = program;

		numNodes = 0;
		numEdges = 0;

		// TODO: Make functions in this class use these pre-initialized data
		// structures

		locations = new HashSet<>();
		mustLeaves = new HashSet<>();
		inEdges = HashMultimap.create();
		outEdges = HashMultimap.create();

		for (CFAEdge e : program.getCFA()) {
			inEdges.put(e.getTarget(), e);
			outEdges.put(e.getSource(), e);
			locations.add(e.getSource());
			locations.add(e.getTarget());
		}

		// Find locations which have an incoming MUST edge, but no outgoing one
		for (Location l : locations) {
			boolean foundMust = false;
			for (CFAEdge e : inEdges.get(l)) {
				foundMust |= e.getKind() == Kind.MUST;
			}

			if (!foundMust) {
				continue;
			}

			foundMust = false;
			for (CFAEdge e : outEdges.get(l)) {
				foundMust |= e.getKind() == Kind.MUST;
			}

			if (!foundMust) {
				mustLeaves.add(l);
			}

		}

		if (!mustLeaves.isEmpty()) {
			logger.debug("Leaves of MUST-analysis: " + mustLeaves);
		}

	}

	private GraphWriter createGraphWriter(String filename) {
		try {
			if (Options.graphML.getValue()) {
				return new GraphMLWriter(filename);
			} else {
				return new GraphvizWriter(filename);
			}
		} catch (IOException e) {
			logger.error("Cannot open output file!", e);
			return null;
		}
	}

	private Map<String, String> getNodeProperties(Location loc) {
		RTLStatement curStmt = program.getStatement1(loc);
		Map<String, String> properties = new HashMap<>();

		if (curStmt != null) {
			if (curStmt.getLabel().getAddress().getValue() >= 0xFACE0000L) {
				properties.put("color", "lightgrey");
				properties.put("fillcolor", "lightgrey");
			}

			if (program.getUnresolvedBranches().contains(curStmt.getLabel())) {
				properties.put("fillcolor", "red");
			}

			if (mustLeaves.contains(loc)) {
				properties.put("fillcolor", "green");
			}

			if (curStmt.getLabel().equals(program.getStart())) {
				properties.put("color", "green");
				properties.put("style", "filled,bold");
			} else if (curStmt instanceof RTLHalt) {
				properties.put("color", "orange");
				properties.put("style", "filled,bold");
			}
		} else {
			logger.info("No real statement for location " + loc);
		}

		return properties;
	}

	// Does not write a real graph, but still fits best into this class
	public void writeDisassembly(BPCFG l, String filename) {
		// System.out.println("Writing assembly file to " + filename);
		try {
			FileWriter out = new FileWriter(filename);
			for (BPVertex vertex : l.getVertecesList()) {
				AbsoluteAddress pc = vertex.getAddress();
				// SymbolicExecution.setStartAddress(pc);
				Instruction instr = vertex.getInstruction();
				StringBuilder sb = new StringBuilder();

				if (vertex.getType() == BPVertex.APINode) {
					sb.append(vertex.getProperty() + ": API Node").append("\t");
					sb.append(Characters.NEWLINE);
				} else if (vertex.getType() == BPVertex.UnknownNode) {
					sb.append(vertex.getProperty() + ": Unknown Node").append("\t");
					sb.append(Characters.NEWLINE);
				} else if (vertex.getType() == BPVertex.ExitNode) {
					sb.append(vertex.getProperty() + ": Exit Node").append("\t");
					sb.append(Characters.NEWLINE);
				} else {
					sb.append(pc).append(":\t");
					sb.append(generateString(instr, vertex));
					sb.append(Characters.NEWLINE);
					if (instr instanceof ReturnInstruction) {
						sb.append(Characters.NEWLINE);
					}
				}
				out.write(sb.toString());
			}
			out.close();

		} catch (IOException e) {
			logger.fatal(e);
			return;
		}
	}

	private String generateString(Instruction instr, BPVertex vertex) {
		// TODO Auto-generated method stub
		if (instr == null) {
			return "null";
		}

		String r = "";
		if (instr instanceof X86Instruction) {
			X86Instruction i = (X86Instruction) instr;
			if (i.hasPrefixREPZ()) {
				r += "rep ";
			} else if (i.hasPrefixREPNZ()) {
				r += "repn ";
			}
		} else if (instr instanceof X86ArithmeticInstruction) {
			X86ArithmeticInstruction i = (X86ArithmeticInstruction) instr;
			if (i.hasPrefixREPZ()) {
				r += "rep ";
			} else if (i.hasPrefixREPNZ()) {
				r += "repn ";
			}
		} else if (instr instanceof X86MoveInstruction) {
			X86MoveInstruction i = (X86MoveInstruction) instr;
			if (i.hasPrefixREPZ()) {
				r += "rep ";
			} else if (i.hasPrefixREPNZ()) {
				r += "repn ";
			}
		} else if (instr instanceof X86CallInstruction) {
			X86CallInstruction i = (X86CallInstruction) instr;
			if (i.hasPrefixREPZ()) {
				r += "rep ";
			} else if (i.hasPrefixREPNZ()) {
				r += "repn ";
			}
		} else if (instr instanceof X86CondJmpInstruction) {
			X86CondJmpInstruction i = (X86CondJmpInstruction) instr;
			if (i.hasPrefixREPZ()) {
				r += "rep ";
			} else if (i.hasPrefixREPNZ()) {
				r += "repn ";
			}
		}

		r += instr.getName();
		if (vertex.getProperty().equals("")) {
			int i = instr.getOperandCount();
			if (i == 1) {
				r += " " + instr.getOperand(0).toString();
			} else if (i == 2) {
				r += " " + instr.getOperand(0).toString() + ", " + instr.getOperand(1).toString();
			} else if (i == 3) {
				r += " " + instr.getOperand(0).toString() + ", " + instr.getOperand(1).toString() + ", "
						+ instr.getOperand(2).toString();
			}

		} else {
			r += " " + vertex.getProperty();
		}

		return r;
	}

	public void writeAssemblyCFG1(String filename) {
		Set<CFGState> edges = new HashSet<>();
		Set<PMLocation> nodes = new HashSet<>();
		Set<Long> processedNodes = new HashSet<>();
		for (CFGState e : program.getCFGState()) {
			AbsoluteAddress sourceAddr = e.getSource().getAddress();
			AbsoluteAddress targetAddr = e.getTarget().getAddress();
			if (!sourceAddr.equals(targetAddr)) {
				edges.add(e);
				if (!processedNodes.contains(sourceAddr.getValue())) {
					numNodes++;
					processedNodes.add(sourceAddr.getValue());
				}

				if (!processedNodes.contains(targetAddr.getValue())) {
					numNodes++;
					processedNodes.add(targetAddr.getValue());
				}

				nodes.add(new PMLocation(e.getSource().getAddress(), e.getSource().getIndex(), e.getSourceInstruction()));
				// nodes.add(e.getTarget());
			}
		}

		// numNodes = nodes.size();
		numEdges = edges.size();

		// Create dot file
		GraphWriter gwriter = createGraphWriter(filename);
		if (gwriter == null) {
			return;
		}

		logger.info("Writing assembly CFG to " + gwriter.getFilename());
		try {
			for (PMLocation node : nodes) {
				AbsoluteAddress nodeAddr = node.getAddress();
				Instruction instr = node.getInstruction();
				// program.getInstruction1(nodeAddr);
				String nodeName = nodeAddr.toString();
				String nodeLabel = program.getSymbolFor(nodeAddr);
				if (nodeAddr.toString().equals("0xff000020") || nodeAddr.toString().equals("0xff000010")) {
					System.out.println("Debug" + nodeAddr.toString());
				}

				// numNodes ++;
				if (instr != null && program.getAssemblyMap().containsKey(nodeAddr)) {
					ExecutableImage ext = program.getModule(nodeAddr);
					if (ext == null) {
						System.out.println("Debug");
					}

					String instrString = instr.toString(nodeAddr.getValue(), program.getModule(nodeAddr)
							.getSymbolFinder());
					instrString = instrString.replace("\t", " ");
					gwriter.writeNode(nodeName, nodeLabel + "\\n" + instrString, getNodeProperties(node));
				} else {
					gwriter.writeNode(nodeName, nodeLabel, getNodeProperties(node));
				}
			}

			for (CFGState e : edges) {
				if (e.getKind() == null) {
					logger.error("Null kind? " + e);
				}
				AbsoluteAddress sourceAddr = e.getSource().getAddress();
				AbsoluteAddress targetAddr = e.getTarget().getAddress();

				String label = null;
				Instruction instr = e.getSourceInstruction();

				if (instr instanceof BranchInstruction) {
					BranchInstruction bi = (BranchInstruction) instr;
					if (bi.isConditional()) {
						// Get the original goto from the program (not the
						// converted assume)
						RTLStatement rtlGoto = program.getStatement(e.getSource());

						// If this is the fall-through edge, output F, otherwise
						// T
						label = targetAddr.equals(rtlGoto.getNextLabel().getAddress()) ? "F" : "T";
					}
				}

				if (label != null) {
					gwriter.writeLabeledEdge(sourceAddr.toString(), targetAddr.toString(), label,
							e.getKind().equals(CFAEdge.Kind.MAY) ? Color.BLACK : Color.GREEN);
				} else {
					gwriter.writeEdge(sourceAddr.toString(), targetAddr.toString(), e.getKind()
							.equals(CFAEdge.Kind.MAY) ? Color.BLACK : Color.GREEN);
				}
			}

			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}

	}

	private Map<String, String> getNodeProperties(PMLocation loc) {
		// TODO Auto-generated method stub
		RTLStatement curStmt = program.getStatement1(loc);
		Map<String, String> properties = new HashMap<>();

		if (curStmt != null) {
			if (curStmt.getLabel().getAddress().getValue() >= 0xFACE0000L) {
				properties.put("color", "lightgrey");
				properties.put("fillcolor", "lightgrey");
			}

			if (program.getUnresolvedBranches().contains(curStmt.getLabel())) {
				properties.put("fillcolor", "red");
			}

			if (mustLeaves.contains(loc)) {
				properties.put("fillcolor", "green");
			}

			if (curStmt.getLabel().equals(program.getStart())) {
				properties.put("color", "green");
				properties.put("style", "filled,bold");
			} else if (curStmt instanceof RTLHalt) {
				properties.put("color", "orange");
				properties.put("style", "filled,bold");
			}
		} else {
			logger.info("No real statement for location " + loc);
		}

		return properties;
	}

	// Luu lai de dung sau
	// Thay doi khi co Self-modified Code
	public void writeAssemblyCFG(String filename) {
		Set<CFAEdge> edges = new HashSet<>();
		Set<Location> nodes = new HashSet<>();
		Set<Long> processedNodes = new HashSet<>();
		for (CFAEdge e : program.getCFA()) {
			AbsoluteAddress sourceAddr = e.getSource().getAddress();
			AbsoluteAddress targetAddr = e.getTarget().getAddress();
			if (!sourceAddr.equals(targetAddr)) {
				edges.add(e);
				if (!processedNodes.contains(sourceAddr.getValue())) {
					numNodes++;
					processedNodes.add(sourceAddr.getValue());
				}

				if (!processedNodes.contains(targetAddr.getValue())) {
					numNodes++;
					processedNodes.add(targetAddr.getValue());
				}

				nodes.add(e.getSource());
				nodes.add(e.getTarget());
			}
		}

		// numNodes = nodes.size();
		numEdges = edges.size();

		// Create dot file
		GraphWriter gwriter = createGraphWriter(filename);
		if (gwriter == null) {
			return;
		}

		logger.info("Writing assembly CFG to " + gwriter.getFilename());
		try {
			for (Location node : nodes) {
				AbsoluteAddress nodeAddr = node.getAddress();
				Instruction instr = program.getInstructionResult(nodeAddr);
				String nodeName = nodeAddr.toString();
				String nodeLabel = program.getSymbolFor(nodeAddr);
				if (nodeLabel.startsWith("GetModuleHandle")) {
					System.out.println("Debug");
				}
				// numNodes ++;
				if (instr != null) {
					// ExecutableImage ext = program.getModule(nodeAddr);

					String instrString = instr.toString(nodeAddr.getValue(), program.getModule(nodeAddr)
							.getSymbolFinder());
					instrString = instrString.replace("\t", " ");
					gwriter.writeNode(nodeName, nodeLabel + "\\n" + instrString, getNodeProperties(node));
				} else {
					gwriter.writeNode(nodeName, nodeLabel, getNodeProperties(node));
				}
			}

			for (CFAEdge e : edges) {
				if (e.getKind() == null) {
					logger.error("Null kind? " + e);
				}
				AbsoluteAddress sourceAddr = e.getSource().getAddress();
				AbsoluteAddress targetAddr = e.getTarget().getAddress();

				String label = null;
				Instruction instr = program.getInstructionResult(sourceAddr);

				if (instr instanceof BranchInstruction) {
					BranchInstruction bi = (BranchInstruction) instr;
					if (bi.isConditional()) {
						// Get the original goto from the program (not the
						// converted assume)
						RTLStatement rtlGoto = program.getStatement(e.getSource());

						// If this is the fall-through edge, output F, otherwise
						// T
						label = targetAddr.equals(rtlGoto.getNextLabel().getAddress()) ? "F" : "T";
					}
				}

				if (label != null) {
					gwriter.writeLabeledEdge(sourceAddr.toString(), targetAddr.toString(), label,
							e.getKind().equals(CFAEdge.Kind.MAY) ? Color.BLACK : Color.GREEN);
				} else {
					gwriter.writeEdge(sourceAddr.toString(), targetAddr.toString(), e.getKind()
							.equals(CFAEdge.Kind.MAY) ? Color.BLACK : Color.GREEN);
				}
			}

			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}

	}

	public void writeControlFlowAutomaton(String filename) {
		writeControlFlowAutomaton(filename, (ReachedSet) null);
	}

	public void writeControlFlowAutomaton(String filename, ReachedSet reached) {
		Set<Location> nodes = new HashSet<>();
		for (CFAEdge e : program.getCFA()) {
			nodes.add(e.getTarget());
			nodes.add(e.getSource());
		}

		// Create dot file
		GraphWriter gwriter = createGraphWriter(filename);
		if (gwriter == null) {
			return;
		}

		logger.info("Writing CFA to " + gwriter.getFilename());
		try {
			for (Location node : nodes) {
				String nodeName = node.toString();
				StringBuilder labelBuilder = new StringBuilder();
				labelBuilder.append(nodeName);
				if (reached != null) {
					labelBuilder.append("\n");
					if (reached.where(node).isEmpty()) {
						logger.warn("No reached states for location " + node);
					}
					for (AbstractState a : reached.where(node)) {
						labelBuilder.append(a.toString());
						labelBuilder.append("\n");
					}
				}
				gwriter.writeNode(nodeName, labelBuilder.toString(), getNodeProperties(node));
			}

			for (CFAEdge e : program.getCFA()) {
				if (e.getKind() == null) {
					logger.error("Null kind? " + e);
				}
				gwriter.writeLabeledEdge(e.getSource().toString(), e.getTarget().toString(), e.getTransformer()
						.toString(), e.getKind().equals(CFAEdge.Kind.MAY) ? Color.BLACK : Color.GREEN);
			}

			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}
	}

	public void writeControlFlowAutomaton(String filename, Map<Location, Object> reached) {
		Set<Location> nodes = new HashSet<>();
		for (CFAEdge e : program.getCFA()) {
			nodes.add(e.getTarget());
			nodes.add(e.getSource());
		}

		// Create dot file
		GraphWriter gwriter = createGraphWriter(filename);
		if (gwriter == null) {
			return;
		}

		logger.info("Writing CFA to " + gwriter.getFilename());
		try {
			for (Location node : nodes) {
				String nodeName = node.toString();
				StringBuilder labelBuilder = new StringBuilder();
				labelBuilder.append(nodeName);
				if (reached != null) {
					labelBuilder.append("\n");
					Object info = reached.get(node);
					if (info == null) {
						logger.warn("No information for location " + node);
					} else {
						labelBuilder.append(info.toString());
					}
				}
				gwriter.writeNode(nodeName, labelBuilder.toString(), getNodeProperties(node));
			}

			for (CFAEdge e : program.getCFA()) {
				if (e.getKind() == null) {
					logger.error("Null kind? " + e);
				}
				gwriter.writeLabeledEdge(e.getSource().toString(), e.getTarget().toString(), e.getTransformer()
						.toString(), e.getKind().equals(CFAEdge.Kind.MAY) ? Color.BLACK : Color.GREEN);
			}

			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}
	}

	public void writeCallGraph(String filename, SetMultimap<Location, Location> callGraph) {
		// Create dot file
		GraphWriter gwriter = createGraphWriter(filename);
		if (gwriter == null) {
			return;
		}

		Set<Location> nodes = new HashSet<>();

		logger.info("Writing callgraph to " + gwriter.getFilename());
		try {
			for (Map.Entry<Location, Location> e : callGraph.entries()) {
				nodes.add(e.getKey());
				nodes.add(e.getValue());
				gwriter.writeEdge(e.getKey().toString(), e.getValue().toString());
			}

			for (Location node : nodes) {
				gwriter.writeNode(node.toString(), node.toString(), getNodeProperties(node));
			}

			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}
	}

	public void writeART(String filename, AbstractReachabilityTree art) {
		Map<String, String> startNode = new HashMap<>();
		Map<String, String> endNode = new HashMap<>();
		startNode.put("color", "green");
		startNode.put("style", "filled,bold");
		endNode.put("color", "red");
		endNode.put("style", "filled,bold");

		// Create dot file
		GraphWriter gwriter = createGraphWriter(filename);
		if (gwriter == null) {
			return;
		}

		logger.info("Writing ART to " + gwriter.getFilename());
		try {
			Deque<AbstractState> worklist = new LinkedList<>();
			// Set<AbstractState> visited = new HashSet<AbstractState>();
			worklist.add(art.getRoot());
			// visited.addAll(worklist);
			while (!worklist.isEmpty()) {
				AbstractState curState = worklist.removeFirst();

				String nodeName = curState.getIdentifier();
				Map<String, String> properties = null;
				if (curState == art.getRoot()) {
					properties = startNode;
				}
				if (Program.getProgram().getStatement(curState.getLocation()) instanceof RTLHalt) {
					properties = endNode;
				}
				StringBuilder nodeLabel = new StringBuilder();
				nodeLabel.append(curState.getIdentifier());
				// nodeLabel.append("\\n");
				// nodeLabel.append(curState);
				for (AbstractState coverState : art.getCoveringStates(curState)) {
					nodeLabel.append("Covered by ").append(coverState.getIdentifier()).append("\\n");
				}

				gwriter.writeNode(nodeName, nodeLabel.toString(), properties);

				for (AbstractState nextState : art.getChildren(curState)) {
					// if (!visited.contains(nextState)) {
					worklist.add(nextState);
					// visited.add(nextState);
					gwriter.writeEdge(nodeName, nextState.getIdentifier());
					// }
				}
			}
			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}

	}

	// Does not write a real graph, but still fits best into this class
	public void writeDisassembly(Program program, String filename) {
		logger.info("Writing assembly file to " + filename);

		SetMultimap<AbsoluteAddress, CFAEdge> branchEdges = HashMultimap.create();
		SetMultimap<AbsoluteAddress, CFAEdge> branchEdgesRev = HashMultimap.create();
		if (!Options.noGraphs.getValue()) {
			for (CFAEdge e : program.getCFA()) {
				AbsoluteAddress sourceAddr = e.getSource().getAddress();
				AbsoluteAddress targetAddr = e.getTarget().getAddress();
				if (program.getInstruction(sourceAddr) instanceof BranchInstruction && !sourceAddr.equals(targetAddr)) {
					branchEdges.put(sourceAddr, e);
					branchEdgesRev.put(targetAddr, e);
				}
			}
		}

		try {
			FileWriter out = new FileWriter(filename);
			for (Map.Entry<AbsoluteAddress, Instruction> entry : program.getAssemblyMap().entrySet()) {
				AbsoluteAddress pc = entry.getKey();
				// SymbolicExecution.setStartAddress(pc);
				Instruction instr = entry.getValue();
				StringBuilder sb = new StringBuilder();
				SymbolFinder symFinder = program.getModule(pc).getSymbolFinder();
				if (symFinder.hasSymbolFor(pc)) {
					sb.append(Characters.NEWLINE);
					sb.append(symFinder.getSymbolFor(pc));
					sb.append(":").append(Characters.NEWLINE);
				}
				sb.append(pc).append(":\t");
				sb.append(instr.toString(pc.getValue(), symFinder));

				// if (instr instanceof BranchInstruction) {
				// Set<CFAEdge> targets = branchEdges.get(pc);
				// sb.append("\t; targets: ");
				// if (targets.isEmpty()) {
				// sb.append("unresolved");
				// } else {
				// boolean first = true;
				// for (CFAEdge e : targets) {
				// if (first) first = false;
				// else sb.append(", ");
				// sb.append(e.getTarget().getAddress());
				// sb.append('(').append(e.getKind()).append(')');
				// }
				// }
				// }
				//
				// if (branchEdgesRev.containsKey(pc)) {
				// Set<CFAEdge> referers = branchEdgesRev.get(pc);
				// sb.append("\t; from: ");
				// boolean first = true;
				// for (CFAEdge e : referers) {
				// if (first) first = false;
				// else sb.append(", ");
				// sb.append(e.getSource().getAddress());
				// sb.append('(').append(e.getKind()).append(')');
				// }
				// }

				sb.append(Characters.NEWLINE);
				if (instr instanceof ReturnInstruction) {
					sb.append(Characters.NEWLINE);
				}
				out.write(sb.toString());
			}
			out.close();

		} catch (IOException e) {
			logger.fatal(e);
			return;
		}
	}

	private String getInstructionString(Instruction instr, AbsoluteAddress nodeAddr) {
		String instrString = "";
		if (instr != null) {
			ExecutableImage ext = program.getModule(nodeAddr);

			if (ext != null) {
				instrString = instr.toString(nodeAddr.getValue(), ext.getSymbolFinder());
			} else {
				// PHONG: change here for Virtual memory
				// instrString = nodeAddr.toString();
				// instrString = instr.getName()+" ";
				// for (int i = 0; i < instr.getOperandCount(); i++){
				// if (i == instr.getOperandCount() - 1)
				// instrString = instrString + instr.getOperand(i);
				// else instrString = instrString + instr.getOperand(i) + ", ";
				// }
				instrString = instr.toString(nodeAddr.getValue(), null);
			}
			instrString = instrString.replace("\t", " ");
		}
		return instrString;
	}

	public void writeAssemblyCFG(BPCFG cfg, String filePath) {
		// Create dot file
		List<BPEdge> edges = cfg.getEdgesList();
		List<BPVertex> verteces = cfg.getVertecesList();
		GraphWriter gwriter = createGraphWriter(filePath);
		if (gwriter == null) {
			return;
		}

		// System.out.println("Writing assembly CFG to " +
		// gwriter.getFilename());
		try {
			for (BPVertex node : verteces) {
				// if (node.getType() != 4) {
				// break;

				AbsoluteAddress nodeAddr = node.getAddress();
				Instruction instr = node.getInstruction();
				String nodeName = "";
				String nodeLabel = "";
				if (nodeAddr == null) {
					nodeName = node.getProperty();
					nodeLabel = node.getProperty();
				} else {
					nodeName = nodeAddr.toString();
					nodeLabel = program.getSymbolFor(nodeAddr);
				}
				/*
				 * if (nodeLabel.startsWith("GetModuleHandle"))
				 * System.out.println("Debug");
				 */
				// PHONG: debug here
				// if (nodeLabel.startsWith("0x0033014a"))
				// System.out.println("Debug");

				// numNodes ++;
				String instrString = getInstructionString(instr, nodeAddr);
				if (instrString != "") {
					gwriter.writeNode(normalizeString(nodeName + instrString), nodeLabel + "\\n" + instrString,
							getNodeProperties(node));
				}
				else {
					gwriter.writeNode(normalizeString(nodeName), nodeLabel, getNodeProperties(node));
				// }
				}
			}

			for (BPEdge e : edges) {
				/*
				 * if (e.getKind() == null) logger.error("Null kind? " + e);
				 */
				// if (e.getSourceVertex().getType() != 4 &&
				// e.getDestVertex().getType() != 4) {
				// break;
				if (e.getSourceVertex() == null || e.getDestVertex() == null) {
					continue;
				}
				
				AbsoluteAddress sourceAddr = e.getSourceVertex().getAddress();
				String sNode = sourceAddr == null ? e.getSourceVertex().getProperty() : sourceAddr.toString();
				AbsoluteAddress targetAddr = e.getDestVertex().getAddress();
				String dNode = targetAddr == null ? e.getDestVertex().getProperty() : targetAddr.toString();

				String sInst = getInstructionString(e.getSourceVertex().getInstruction(), e.getSourceVertex()
						.getAddress());
				String dInst = getInstructionString(e.getDestVertex().getInstruction(), e.getDestVertex().getAddress());

				String label = null;
				Instruction instr = e.getSourceVertex().getInstruction();
				// Instruction instrD = e.getDestVertex().getInstruction();

				if (instr instanceof BranchInstruction) {
					BranchInstruction bi = (BranchInstruction) instr;
					if (bi.isConditional()) {
						// Get the original goto from the program (not the
						// converted assume)
						/*
						 * RTLStatement rtlGoto = program.getStatement(e
						 * .getSource());
						 */

						// If this is the fall-through edge, output F, otherwise
						// T
						String t = e.getSourceVertex().getProperty();
						label = t.startsWith(dNode) ? "T" : "F";
					}
				}

				if (label != null) {
					gwriter.writeLabeledEdge(normalizeString(sNode + sInst), normalizeString(dNode + dInst), label,
							Color.BLACK);
				}
				else {
					gwriter.writeEdge(normalizeString(sNode + sInst), normalizeString(dNode + dInst), Color.BLACK);
				// }
				}
			}

			gwriter.close();
		} catch (IOException e) {
			logger.error("Cannot write to output file", e);
			return;
		}
	}

	private String normalizeString(String s) {
		// TODO Auto-generated method stub
		String s1 = s.replace("(", "");
		s1 = s1.replace(')', '_');
		s1 = s1.replaceAll("\\s+", "_");
		s1 = s1.replaceAll("%", "");
		s1 = s1.replaceAll("$", "");
		s1 = s1.replaceAll("<", "");
		s1 = s1.replaceAll(">", "");
		s1 = s1.replace("$", "");
		s1 = s1.replace(",", "");
		return s1;
	}

	private Map<String, String> getNodeProperties(BPVertex node) {
		// TODO Auto-generated method stub
		// RTLStatement curStmt = program.getStatement1(loc);
		Map<String, String> properties = new HashMap<>();

		if (node.getAddress() == null || node.getInstruction() == null) {
			properties.put("color", "lightgrey");
			properties.put("fillcolor", "lightgrey");
		} else if (program.getSymbolFor(node.getAddress()).startsWith("start")) {
			properties.put("color", "lightgrey");
			properties.put("fillcolor", "orange");
		}

		/*
		 * if (curStmt != null) { if
		 * (curStmt.getLabel().getAddress().getValueOperand() >= 0xFACE0000L) {
		 * properties.put("color", "lightgrey"); properties.put("fillcolor",
		 * "lightgrey"); }
		 * 
		 * if (program.getUnresolvedBranches().contains(curStmt.getLabel())) {
		 * properties.put("fillcolor", "red"); }
		 * 
		 * if (mustLeaves.contains(loc)) { properties.put("fillcolor", "green");
		 * }
		 * 
		 * if (curStmt.getLabel().equals(program.getStart())) {
		 * properties.put("color", "green"); properties.put("style",
		 * "filled,bold"); } else if (curStmt instanceof RTLHalt) {
		 * properties.put("color", "orange"); properties.put("style",
		 * "filled,bold"); } } else {
		 * logger.info("No real statement for location " + loc); }
		 */

		return properties;
	}

}
