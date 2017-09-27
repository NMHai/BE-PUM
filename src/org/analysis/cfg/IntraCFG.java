package org.analysis.cfg;

import org.analysis.concrete_execution.ConcreteStack;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jakstab.loader.Win32StubLibrary;
import v2.org.analysis.cfg.AddressList;
import v2.org.analysis.system.SystemHandle;

import java.util.*;
import java.util.Map.Entry;

public class IntraCFG {
	private CFGEdges edges;
	private CFGVertices vertices;
	private Program program;
	private Map<AbsoluteAddress, AbsoluteAddress> negCondition;
	SystemHandle systemHandle;
	String funcName = "";

	public Map<AbsoluteAddress, AbsoluteAddress> getNegConditionList() {
		return negCondition;
	}

	private String refine(Operand op) {
		String result = "";
		if (op.getClass().getSimpleName().equals("X86Register")
				|| op.getClass().getSimpleName().equals("X86RegisterPart"))
			result = op.toString().substring(1);
		else if (op.getClass().getSimpleName().equals("Immediate"))
			result = ((Immediate) op).getNumber().intValue() + "";
		else if (op.getClass().getSimpleName().equals("X86PCRelativeAddress"))
			result = ((X86PCRelativeAddress) op).toString();
		else if (op.getClass().getSimpleName().equals("X86MemoryOperand"))
			result = ((X86MemoryOperand) op).toString();

		// if (result.equals("0x403000"))
		// result = "counter";
		return result;
	}

	public IntraCFG(Program program) {
		this.program = program;
		this.edges = new CFGEdges();
		this.vertices = new CFGVertices();
		negCondition = new TreeMap<AbsoluteAddress, AbsoluteAddress>();
		this.constructCFG();
	}

	public IntraCFG(Program program2, SystemHandle system) {
		// TODO Auto-generated constructor stub
		this(program2);
		this.systemHandle = system;
	}

	public List<AddressList> getTraceList(AbsoluteAddress addr) {
		if (!vertices.contain(addr))
			return null;
		// return vertices.getVertex(addr).getAddrList_IndirectJump(0);
		return vertices.getVertex(addr).getTraceList();
		// return null;
	}

	public void test() {
		Map<AbsoluteAddress, Instruction> assemblyMap = this.program.getAssemblyMap();

		// List <AbsoluteAddress> addrProcessed= new ArrayList <AbsoluteAddress>
		// ();

		Map.Entry<AbsoluteAddress, Instruction> start = assemblyMap.entrySet().iterator().next();
		AbsoluteAddress address = start.getKey();

		while (true) {
			Instruction ins = assemblyMap.get(address);
			if (ins == null)
				break;
			System.out.println("The address of program:" + address.toString());
			System.out.println("Name of instruction " + ins.getName());
			for (int j = 0; j < ins.getOperandCount(); j++) {
				if (ins.getName().startsWith("j")) {
					X86PCRelativeAddress operand = (X86PCRelativeAddress) ins.getOperand(0);
					AbsoluteAddress a = new AbsoluteAddress(operand.getEffectiveValue(address.getValue()));
					System.out.println("Operand number " + j + " is " + a.toString());
				} else {
					System.out.println("Operand number " + j + " is " + ins.getOperand(j).toString());
				}
			}
			System.out.println("-----------------------------------------------");
			address = new AbsoluteAddress(address.getValue() + ins.getSize());

		}
	}

	public boolean setTypeIndirectJump(AbsoluteAddress addr) {
		if (!vertices.contain(addr))
			return false;
		// return vertices.getVertex(addr).getAddrList_IndirectJump(0);
		vertices.getVertex(addr).setType(7);
		// return null;
		return true;
	}

	public boolean constructCFG() {
		// Map<AbsoluteAddress, Pair<String, String>> importTable = ((PEModule)
		// Program
		// .getProgram().getMainModule()).getImportTable();
		Win32StubLibrary stubLibrary = (Win32StubLibrary) Program.getProgram().getStubLibrary();
		Map<AbsoluteAddress, String> stubMap;
		if (stubLibrary != null)
			stubMap = stubLibrary.getAddressMap();
		else
			stubMap = new HashMap<AbsoluteAddress, String>();
		ConcreteStack cStack = new ConcreteStack();
		Map<AbsoluteAddress, Instruction> assemblyMap = this.program.getAssemblyMap();
		AddressList processedTotalAddrList = new AddressList();
		// AddressList neg = new AddressList();
		// List<AbsoluteAddress> startAddrList = new
		// ArrayList<AbsoluteAddress>();
		AbsoluteAddress addr = assemblyMap.entrySet().iterator().next().getKey();
		// Get the Start vertex of CFG
		CFGVertex startNode = new CFGVertex(addr, assemblyMap.get(addr), 0);
		vertices.add(startNode);

		// Initiation of CFG Construction
		TraceTracker trace = new TraceTracker();
		// AddressList processedList = new AddressList();
		trace.add(addr, new AddressList(), new Condition());

		// Begin the CFG construct
		// The main idea is to get the next address
		// Check the type of each vertex and add a new vertex to the list of
		// vertices
		// then loop this progress
		int num_loop = 0;

		while (!trace.isEmpty()) {
			AbsoluteAddress address = trace.removeFirstStartAddress();
			AddressList processedList = trace.removeFirstProcessedList();
			Condition cond = trace.removeFirstCondition();
			if (!processedList.add(address)) {
				// System.out.println("Processes List contains this address:"
				// + address.toString());
			}

			if (!processedTotalAddrList.add(address)) {
				// System.out.println("Total Addr List contains this address:" +
				// address.toString());
			}

			CFGVertex source;
			source = vertices.getVertex(address);
			if (source == null) {
				System.out.println("Vertex with address " + address.toString() + " is not added to the list");
				if (assemblyMap.get(address) == null) {
					System.out.println("Address " + address.toString() + " out of bound");
					continue;
				}
				source = new CFGVertex(address, assemblyMap.get(address));
				vertices.add(source);
			}

			while (true) {
				Instruction ins = source.getIns();
				// System.out.println(ins.toString());

				// Exit the program

				/*
				 * if (source.getAddr().toString().equals("0x0040106c"))
				 * System.out.println("Debug");
				 */
				// CFGVertex v =
				// cfg.getCFGVertices().getVertex(this.indirectTarget);
				// source.printAddrList_IndirectJump(); }

				if (ins == null) {
					// if (!address.toString().equals("0x00401035"))
					System.out.println("Address out of bound " + address.toString());
					vertices.remove(source);
					break;
				}
				CFGVertex dest;
				int t = X86InstructionType.checkType(ins);

				source.setTraceList(processedList.clone());

				if (t == 1) {
					vertices.getVertex(address).setType(1);
					if (ins.getName().startsWith("ret")) {
						source.setTraceList(processedList.clone());
						long temp = 0;

						if (this.program.getPreservedExecutionMap().containsKey(address)) {
							address = this.program.getPreservedExecutionMap().get(address).get(0);
							if (address == null)
								break;
							ins = assemblyMap.get(address);
						} else {

							if (cStack.isEmpty())
								break;

							temp = cStack.pop();
							address = new AbsoluteAddress(temp);
							ins = assemblyMap.get(address);
						}
						// source.setType(6);
						if (ins != null) {
							if (!processedList.add(address)) {
								// System.out
								// .println("Processes List contains this address:"
								// + address.toString());
								dest = vertices.getVertex(address);
								dest.setType(4);
								dest.setTraceLoop(processedList.clone());
								createListLoop(processedList.clone());
								// CFGEdge e = new CFGEdge(source, dest,
								// cond.clone());
								CFGEdge e = new CFGEdge(source, dest);
								// source.
								edges.add(e);
								source.addEdgeOut(e);
								dest.addEdgeIn(e);
								break;
							}

							if (!processedTotalAddrList.add(address)) {
								// System.out.println("Addr List contains this address:"
								// + address.toString() + " Join Type");
								vertices.getVertex(address).setType(3);
								dest = vertices.getVertex(address);
							} else {
								dest = new CFGVertex(address, ins);
								vertices.add(dest);
							}
							// CFGEdge e = new CFGEdge(source, dest,
							// cond.clone());
							CFGEdge e = new CFGEdge(source, dest);
							edges.add(e);
							source.addEdgeOut(e);
							dest.addEdgeIn(e);
							source = dest;
						} else
							break;
					} else
						break;
				} else if (t == 2) {
					address = new AbsoluteAddress(address.getValue() + ins.getSize());
					ins = assemblyMap.get(address);
					source.setType(6);
					if (ins != null) {

						if (!processedList.add(address)) {
							// System.out
							// .println("Processes List contains this address:"
							// + address.toString());
							dest = vertices.getVertex(address);
							dest.setType(4);
							dest.setTraceLoop(processedList.clone());
							createListLoop(processedList.clone());
							// CFGEdge e = new CFGEdge(source, dest,
							// cond.clone());
							CFGEdge e = new CFGEdge(source, dest);
							// source.
							edges.add(e);
							source.addEdgeOut(e);
							dest.addEdgeIn(e);
							break;
						}

						if (!processedTotalAddrList.add(address)) {
							// System.out.println("Addr List contains this address:"
							// + address.toString() + " Join Type");
							vertices.getVertex(address).setType(3);
							dest = vertices.getVertex(address);
						} else {
							dest = new CFGVertex(address, ins);
							vertices.add(dest);
						}
						// CFGEdge e = new CFGEdge(source, dest, cond.clone());
						CFGEdge e = new CFGEdge(source, dest);
						edges.add(e);
						source.addEdgeOut(e);
						dest.addEdgeIn(e);
						source = dest;
					} else
						break;
				} else if (t == 3) {
					// Condition c = cond.clone();
					// if (address.toString().equals("0x00401008"))
					// System.out.println("Debug");
					cond.setLeftC(this.refine(ins.getOperand(0)));
					cond.setRightC(this.refine(ins.getOperand(1)));
					// System.out.println("LeftC= " +
					// ins.getOperand(0).toString()
					// + " RightC= " + ins.getOperand(1).toString());
					// neg.add(address);
					address = new AbsoluteAddress(address.getValue() + ins.getSize());
					ins = assemblyMap.get(address);
					source.setType(6);
					if (ins != null) {
						if (!processedList.add(address)) {
							// System.out
							// .println("Processes List contains this address:"
							// + address.toString());
							// vertices.getVertex(address).setType(4);
							dest = vertices.getVertex(address);
							dest.setType(4);
							dest.setTraceLoop(processedList.clone());
							createListLoop(processedList.clone());
							// CFGEdge e = new CFGEdge(source, dest, c.clone());
							CFGEdge e = new CFGEdge(source, dest);
							source.addEdgeOut(e);
							dest.addEdgeIn(e);
							edges.add(e);
							break;
						}

						if (!processedTotalAddrList.add(address)) {
							// System.out
							// .println("Addr List contains this loop address:"
							// + address.toString() + " Join Type");
							vertices.getVertex(address).setType(3);
							dest = vertices.getVertex(address);
						} else {
							dest = new CFGVertex(address, ins);
							vertices.add(dest);
						}
						// CFGEdge e = new CFGEdge(source, dest, cond.clone());
						CFGEdge e = new CFGEdge(source, dest);
						source.addEdgeOut(e);
						dest.addEdgeIn(e);
						edges.add(e);
						source = dest;
					} else
						break;

				} else if (t == 4) {
					// This is the jump to API functions which are stored in
					// import tables
					AbsoluteAddress oldAddr = address;
					if (ins.getName().startsWith("call"))
						cStack.push(address.getValue() + ins.getSize());

					// source.setTraceList(processedList.clone());
					List<AbsoluteAddress> lAddr = getJumpAddress(ins, address);
					if (lAddr == null || lAddr.size() == 0) {
						source.setType(7);
						break;
					}
					address = lAddr.get(0);
					if (lAddr.size() > 1) {
						for (int i = 1; i < lAddr.size(); i++)
							trace.add(lAddr.get(i), processedList.clone(), cond.clone());
					}

					if (address == null) {
						source.setType(7);
						break;
						// System.out.println("Indirect Branch:" +
						// source.getAddr().toString());
						// SymbolicExecution se = new SymbolicExecution(
						// source.getAddr(), processedList.clone(),
						// neg.clone(), assemblyMap);
						// AbsoluteAddress newAddr = se.execute();
						// if (newAddr != null)
						// System.out.println("Result of the method SE:" +
						// newAddr.toString());

						// source.printInfo();

					}
					ins = assemblyMap.get(address);
					source.setType(5);
					if (ins == null) {
						// System.out.println(address.toString());
						address = reduceValue(address.getValue());
						if (this.program.getPreservedExecutionMap().containsKey(address)) {
							address = this.program.getPreservedExecutionMap().get(address).get(0);
							ins = assemblyMap.get(address);
						} else if (contain(stubMap, address)) {
							// HashMap<AbsoluteAddress, String> h =
							// (HashMap<AbsoluteAddress, String>)
							// stubLibrary.getAddressStubMap();
							stubLibrary.getAddressStubMap().put(oldAddr, funcName);

							address = new AbsoluteAddress(cStack.pop());
							ins = assemblyMap.get(address);
						}
					}
					if (ins != null) {
						if (!processedList.add(address)) {
							// System.out
							// .println("Processes List contains this loop address:"
							// + address.toString());
							// vertices.getVertex(address).setType(4);

							dest = vertices.getVertex(address);
							// Check if dest is not a loop in the case that dest
							// is jump to API System Call
							// if (contain(stubMap, dest.getAddr())) {

							// } else {
							// This is the case of loop
							dest.setType(4);
							dest.setTraceLoop(processedList.clone());
							createListLoop(processedList.clone());
							// dest.setAddressList_Loop(processedList.clone());
							// CFGEdge e = new CFGEdge(source, dest,
							// cond.clone());
							CFGEdge e = new CFGEdge(source, dest);
							source.addEdgeOut(e);
							dest.addEdgeIn(e);
							edges.add(e);
							num_loop++;
							if (num_loop > 5) {
								num_loop = 0;
								break;
							}
							// }
						}

						if (!processedTotalAddrList.add(address)) {
							// System.out.println("Addr List contains this address:"
							// + address.toString() + " Join Type");
							vertices.getVertex(address).setType(3);
							dest = vertices.getVertex(address);
						} else {
							dest = new CFGVertex(address, ins);
							vertices.add(dest);
						}
						// CFGEdge e = new CFGEdge(source, dest, cond.clone());
						CFGEdge e = new CFGEdge(source, dest);
						source.addEdgeOut(e);
						dest.addEdgeIn(e);
						edges.add(e);
						source = dest;
					} else
						break;
				} else if (t == 5) {
					Condition nc = cond.clone();
					cond.setConnector(CondJump.getCondJump(ins.getName()));
					nc.setConnector(CondJump.getReserCondJump(ins.getName()));
					// if (ins.getName().startsWith("je") ||
					// ins.getName().startsWith("jz")) {
					// cond.setConnector("=");
					// nc.setConnector("!=");
					// } else if (ins.getName().startsWith("jne") ||
					// ins.getName().startsWith("jnz")) {
					// cond.setConnector("!=");
					// nc.setConnector("=");
					// } else if (ins.getName().startsWith("jb") ||
					// ins.getName().startsWith("jnae")
					// || ins.getName().startsWith("jc")) {
					// cond.setConnector("<");
					// nc.setConnector(">=");
					// } else if (ins.getName().startsWith("jnb") ||
					// ins.getName().startsWith("jae")
					// || ins.getName().startsWith("jnc")) {
					// cond.setConnector(">=");
					// nc.setConnector("<");
					// } else if (ins.getName().startsWith("jbe") ||
					// ins.getName().startsWith("jna")) {
					// cond.setConnector("<=");
					// nc.setConnector(">");
					// } else if (ins.getName().startsWith("jle") ||
					// ins.getName().startsWith("jng")) {
					// cond.setConnector("<=");
					// nc.setConnector(">");
					// } else if (ins.getName().startsWith("jl") ||
					// ins.getName().startsWith("jnge")) {
					// cond.setConnector("<");
					// nc.setConnector(">=");
					// } else if (ins.getName().startsWith("ja") ||
					// ins.getName().startsWith("jnbe")) {
					// cond.setConnector(">");
					// nc.setConnector("<=");
					// } else if (ins.getName().startsWith("jge") ||
					// ins.getName().startsWith("jnl")) {
					// cond.setConnector(">=");
					// nc.setConnector("<");
					// }else if (ins.getName().startsWith("jg") ||
					// ins.getName().startsWith("jnle")) {
					// cond.setConnector(">");
					// nc.setConnector("<=");
					// }

					// System.out.println("LeftC=" +
					// ins.getOperand(0).toString() + " Connector=" +
					// " RightC= " + ins.getOperand(1).toString());
					AbsoluteAddress next = new AbsoluteAddress(address.getValue() + ins.getSize());
					// ins = assemblyMap.get(next);
					if (assemblyMap.get(next) != null) {
						this.negCondition.put(address, next);
						trace.add(next, processedList.clone(), nc);
					}
					source.setType(2);
					// dest = new CFGVertex(next, ins);

					if (processedTotalAddrList.contain(next)) {
						// System.out.println("Addr List contains this address:"
						// + next.toString() + " Join Type");
						dest = vertices.getVertex(next);
					} else {
						dest = new CFGVertex(next, assemblyMap.get(next));
						vertices.add(dest);
					}
					// CFGEdge e = new CFGEdge(source, dest, nc.clone());
					CFGEdge e = new CFGEdge(source, dest, nc.clone().getTop());
					source.addEdgeOut(e);
					dest.addEdgeIn(e);
					edges.add(e);

					// address = getJumpAddress(ins, address);
					List<AbsoluteAddress> lAddr = getJumpAddress(ins, address);
					if (lAddr == null || lAddr.size() == 0) {
						source.setType(7);
						break;
					}
					address = lAddr.get(0);
					if (lAddr.size() > 1) {
						for (int i = 1; i < lAddr.size(); i++)
							trace.add(lAddr.get(i), processedList.clone(), cond.clone());
					}
					source.setTraceList(processedList.clone());
					if (address == null) {
						// System.out.println("Indirect Branch:" +
						// source.getAddr().toString());
						source.setType(7);
						// SymbolicExecution se = new SymbolicExecution(
						// source.getAddr(), processedList.clone(),
						// neg.clone(), assemblyMap);
						// AbsoluteAddress newAddr = se.execute();
						// if (newAddr != null)
						// System.out.println("Result of the method SE:" +
						// newAddr.toString());
						// source.printInfo();
						break;
					}
					ins = assemblyMap.get(address);
					if (ins != null) {
						if (!processedList.add(address)) {
							// System.out
							// .println("Processes List contains this loop address:"
							// + address.toString());
							dest = vertices.getVertex(address);
							dest.setType(4);
							dest.setTraceLoop(processedList.clone());
							createListLoop(processedList.clone());
							// e = new CFGEdge(source, dest, cond.clone());
							e = new CFGEdge(source, dest, cond.clone().getTop());
							source.addEdgeOut(e);
							dest.addEdgeIn(e);
							edges.add(e);
							break;
						}

						if (!processedTotalAddrList.add(address)) {
							// System.out.println("Addr List contains this address:"
							// + address.toString() + " Join Type");
							vertices.getVertex(address).setType(3);
							dest = vertices.getVertex(address);
						} else {
							dest = new CFGVertex(address, ins);
							vertices.add(dest);
						}
						// e = new CFGEdge(source, dest, cond.clone());
						e = new CFGEdge(source, dest, cond.clone().getTop());
						source.addEdgeOut(e);
						dest.addEdgeIn(e);
						edges.add(e);
						source = dest;
					} else
						break;
				} else if (t == 6) {
					// this is the loop node
					/*
					 * address = new AbsoluteAddress(address.getValueOperand() +
					 * ins.getSize()); ins = assemblyMap.get(address);
					 * source.setType(4);
					 * source.setAddressList_Loop(processedList.clone()); if
					 * (ins != null) {
					 * 
					 * if (!processedList.add(address)) { // System.out //
					 * .println("Processes List contains this address:" // +
					 * address.toString()); dest = vertices.getVertex(address);
					 * dest.setType(4);
					 * dest.setAddressList_Loop(processedList.clone()); CFGEdge
					 * e = new CFGEdge(source, dest, cond.clone()); // source.
					 * edges.add(e); source.addEdgeOut(e); dest.addEdgeIn(e);
					 * break; }
					 * 
					 * if (!processedTotalAddrList.add(address)) { //
					 * System.out.println("Addr List contains this address:" //
					 * + address.toString() + " Join Type");
					 * vertices.getVertex(address).setType(3); dest =
					 * vertices.getVertex(address); } else { dest = new
					 * CFGVertex(address, ins); vertices.add(dest); } CFGEdge e
					 * = new CFGEdge(source, dest, cond.clone()); edges.add(e);
					 * source.addEdgeOut(e); dest.addEdgeIn(e); source = dest; }
					 * else break;
					 */
					Condition nc = cond.clone();
					cond.setLeftC("ecx");
					cond.setRightC("0");
					cond.setConnector(">=");
					nc.setLeftC("ecx");
					nc.setRightC("0");
					nc.setConnector("<");

					AbsoluteAddress next = new AbsoluteAddress(address.getValue() + ins.getSize());
					// ins = assemblyMap.get(next);
					if (assemblyMap.get(next) != null) {
						this.negCondition.put(address, next);
						trace.add(next, processedList.clone(), nc);
					}
					source.setType(2);
					// dest = new CFGVertex(next, ins);

					if (processedTotalAddrList.contain(next)) {
						// System.out.println("Addr List contains this address:"
						// + next.toString() + " Join Type");
						dest = vertices.getVertex(next);
					} else {
						dest = new CFGVertex(next, assemblyMap.get(next));
						vertices.add(dest);
					}
					// CFGEdge e = new CFGEdge(source, dest, nc.clone());
					CFGEdge e = new CFGEdge(source, dest, nc.clone().getTop());
					source.addEdgeOut(e);
					dest.addEdgeIn(e);
					edges.add(e);

					// address = getJumpAddress(ins, address);
					List<AbsoluteAddress> lAddr = getJumpAddress(ins, address);
					if (lAddr == null || lAddr.size() == 0) {
						source.setType(7);
						break;
					}
					address = lAddr.get(0);
					if (lAddr.size() > 1) {
						for (int i = 1; i < lAddr.size(); i++)
							trace.add(lAddr.get(i), processedList.clone(), cond.clone());
					}
					source.setTraceList(processedList.clone());
					if (address == null) {
						// System.out.println("Indirect Branch:" +
						// source.getAddr().toString());
						source.setType(7);
						// SymbolicExecution se = new SymbolicExecution(
						// source.getAddr(), processedList.clone(),
						// neg.clone(), assemblyMap);
						// AbsoluteAddress newAddr = se.execute();
						// if (newAddr != null)
						// System.out.println("Result of the method SE:" +
						// newAddr.toString());
						// source.printInfo();
						break;
					}
					ins = assemblyMap.get(address);
					if (ins != null) {
						if (!processedList.add(address)) {
							// System.out
							// .println("Processes List contains this loop address:"
							// + address.toString());
							dest = vertices.getVertex(address);
							dest.setType(4);
							dest.setTraceLoop(processedList.clone());
							createListLoop(processedList.clone());
							// e = new CFGEdge(source, dest, cond.clone());
							e = new CFGEdge(source, dest, cond.clone().getTop());
							source.addEdgeOut(e);
							dest.addEdgeIn(e);
							edges.add(e);
							break;
						}

						if (!processedTotalAddrList.add(address)) {
							// System.out.println("Addr List contains this address:"
							// + address.toString() + " Join Type");
							vertices.getVertex(address).setType(3);
							dest = vertices.getVertex(address);
						} else {
							dest = new CFGVertex(address, ins);
							vertices.add(dest);
						}
						// e = new CFGEdge(source, dest, cond.clone());
						e = new CFGEdge(source, dest, cond.clone().getTop());
						source.addEdgeOut(e);
						dest.addEdgeIn(e);
						edges.add(e);
						source = dest;
					} else
						break;
				} else
					break;

			}
		}

		return true;
	}

	private void createListLoop(AddressList clone) {
		// TODO Auto-generated method stub
		AbsoluteAddress top = clone.get(clone.length() - 1);
		CFGVertex t = vertices.getVertex(top);
		t.setLoop(true);
		clone = clone.cut(top);
		clone.pop();
		t.setListLoop(clone.clone());
		int s = clone.length();

		for (int i = 0; i < s - 1; i++) {
			AbsoluteAddress x = clone.pop();
			CFGVertex y = vertices.getVertex(x);
			y.setLoop(true);
			clone.add(x);
			y.setListLoop(clone.clone());
		}
		// System.out.println("Debug Create List Loop");
	}

	private boolean contain(Map<AbsoluteAddress, String> stubMap, AbsoluteAddress address) {
		// TODO Auto-generated method stub
		Iterator<Entry<AbsoluteAddress, String>> it = stubMap.entrySet().iterator();
		long t = 0xFFFFFFFFL & address.getValue();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pairs = (Map.Entry) it.next();
			// if
			// (((String)pairs.getValueOperand()).contains("GetModuleHandleA"))
			// System.out.println("Debug");
			AbsoluteAddress addr = ((AbsoluteAddress) pairs.getKey());
			if (addr.getValue() == t) {
				funcName = (String) pairs.getValue();
				return true;
			}
		}
		return false;
	}

	private AbsoluteAddress reduceValue(long value) {
		// TODO Auto-generated method stub
		return new AbsoluteAddress((long) (value % Math.pow(2, 32)));
	}

	public List<AbsoluteAddress> getJumpAddress(Instruction ins, AbsoluteAddress addr) {
		Operand operand = ins.getOperand(0);
		List<AbsoluteAddress> l = new ArrayList<AbsoluteAddress>();
		if (this.program.getPreservedExecutionMap().containsKey(addr))
			return this.program.getPreservedExecutionMap().get(addr);

		if (operand instanceof X86PCRelativeAddress) {

			l.add(new AbsoluteAddress(((X86PCRelativeAddress) ins.getOperand(0)).getEffectiveValue(addr.getValue())));
			// if (addr.toString().equals("0x00401041"))
			// System.out.println("Debug");
			return l;
		} else if (operand instanceof X86MemoryOperand) {
			// List<AbsoluteAddress> l = new ArrayList<AbsoluteAddress>();
			// l.add(new AbsoluteAddress(
			// ((X86MemoryOperand) ins.getOperand(0)).getDisplacement()));
			// if (addr.toString().equals("0x00401041"))
			// System.out.println("Debug");
			// return l;
			long disp = ((X86MemoryOperand) operand).getDisplacement();
			long t = program.getDoubleWordValueMemory(new AbsoluteAddress(disp));
			l.add(new AbsoluteAddress(t));
			return l;
		}
		return this.program.getPreservedExecutionMap().get(addr);

		// return null;
	}

	// This method is to get next address in the case there is loop
	public AbsoluteAddress nextLoopAddress() {
		return null;
	}

	// This method is to print the CFG
	public void prinCFG() {
		System.out.println("Print the information of CFG.");
		System.out.println("****************************************************");
		this.vertices.printInfo();
		System.out.println("****************************************************");
		this.edges.printInfo();
	}

	public CFGVertices getCFGVertices() {
		return vertices;
	}
}
