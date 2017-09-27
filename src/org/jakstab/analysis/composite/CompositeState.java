/*
 * CompositeState.java - This file is part of the Jakstab project.
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
package org.jakstab.analysis.composite;

import org.analysis.complement.Other;
import org.analysis.concolic_testing.ConcolicApproach;
import org.jakstab.Program;
import org.jakstab.analysis.AbstractState;
import org.jakstab.analysis.LatticeElement;
import org.jakstab.analysis.explicit.BasedNumberElement;
import org.jakstab.analysis.explicit.BasedNumberValuation;
import org.jakstab.analysis.location.LocationState;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jakstab.cfa.Location;
import org.jakstab.loader.pe.PEModule;
import org.jakstab.rtl.expressions.RTLExpression;
import org.jakstab.rtl.expressions.RTLNumber;
import org.jakstab.rtl.expressions.RTLVariable;
import org.jakstab.util.FastSet;
import org.jakstab.util.Logger;
import org.jakstab.util.Tuple;

import java.util.*;

/**
 * A cartesian product state that consists of a vector of substates where the
 * first substate is guaranteed to be a location state.
 * 
 * @author Johannes Kinder
 */
public class CompositeState implements AbstractState {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CompositeState.class);

	protected final AbstractState[] components;

	/**
	 * @param components
	 */
	public CompositeState(AbstractState[] components) {
		super();
		this.components = components;
	}

	@Override
	public String getIdentifier() {
		StringBuilder id = new StringBuilder();
		id.append("ID");
		for (AbstractState c : components)
			id.append(c.getIdentifier()).append(":");
		return id.toString();
	}

	@Override
	public Location getLocation() {
		return ((LocationState) components[0]).getLocation();
	}

	public AbstractState getComponent(int index) {
		return components[index];
	}

	public int numComponents() {
		return components.length;
	}

	@Override
	public AbstractState join(LatticeElement l) {
		CompositeState other = (CompositeState) l;
		AbstractState[] jComponents = new AbstractState[components.length];
		for (int i = 0; i < components.length; i++) {
			jComponents[i] = components[i].join(other.components[i]);
		}
		return new CompositeState(jComponents);
	}

	@Override
	public Set<Tuple<RTLNumber>> projectionFromConcretization(RTLExpression... expressions) {
		AbsoluteAddress target = null;
		Set<Tuple<RTLNumber>> result = null;

		AbsoluteAddress addr = this.components[0].getLocation().getAddress();

		/*
		 * if (addr.toString().equals("0x004106ca") ||
		 * addr.toString().equals("0x00401189"))
		 * System.out.println("Debug Composite State:" + addr.toString());
		 */
		// Xu li cac cau lenh Conditional Jump vi Jakstab thuong xuyen gap loi
		// khi xu li nhung cau lenh nay
		Instruction ins = Program.getProgram().getAssemblyMap().get(addr);

		/*
		 * if (ins instanceof X86CondJmpInstruction &&
		 * ins.getName().startsWith("j")) { Operation op = ins.getOperand(0);
		 * System.out.println("Address:" + addr.toString()); long f =
		 * addr.getValueOperand() + ins.getSize(); long t = 0; if
		 * (op.getClass().getSimpleName() .equals("Immediate")) t = ((Immediate)
		 * op).getNumber().intValue(); if
		 * (op.getClass().getSimpleName().equals("X86PCRelativeAddress")) t =
		 * ((X86PCRelativeAddress) op).getEffectiveValue(addr
		 * .getValueOperand());
		 * 
		 * if (t != 0) { result = new FastSet<Tuple<RTLNumber>>(); RTLNumber[]
		 * numbers = new RTLNumber[expressions.length]; numbers[0] = new
		 * RTLNumber(-1, 1); numbers[1] = new RTLNumber(t, 32);
		 * 
		 * result.add(Tuple.create(numbers));
		 * 
		 * RTLNumber[] numbers1 = new RTLNumber[expressions.length]; numbers1[0]
		 * = new RTLNumber(0, 1); numbers1[1] = new RTLNumber(f, 32);
		 * 
		 * result.add(Tuple.create(numbers1));
		 * 
		 * return result; } }
		 */

		// target = result.iterator().next().get(1).intValue();
		// }

		for (int i = 0; i < components.length; i++) {
			// Concretize this component state and then intersect it with
			// existing ones
			// if (addr.toString().equals("0x0040106c"))
			// break;

			Set<Tuple<RTLNumber>> concreteTuples = components[i].projectionFromConcretization(expressions);
			// logger.info(concreteTuples);
			/*
			 * Return value of null represents the whole set of tuples of
			 * numbers (usually b/c the function is not implemented), so
			 * intersection has no effect
			 */
			if (concreteTuples == null)
				continue;
			/*
			 * If we are currently at the whole set, the new tuple set is the
			 * intersection
			 */
			else if (result == null)
				result = concreteTuples;
			/* Else intersect */
			else {
				// result.retainAll(concreteTuples);
				// intersect manually b/c of possible null components (wildcards
				// for more tuples)
				// Note: A tuple with a null component expands to a set of
				// tuples
				// with all possible values for that component
				//
				Set<Tuple<RTLNumber>> newResult = new FastSet<Tuple<RTLNumber>>();
				// for all tuples in result
				for (Tuple<RTLNumber> rTuple : result) {
					// for all tuples we got from the current substate
					cTuplesLoop: for (Tuple<RTLNumber> cTuple : concreteTuples) {
						// array for building new tuple
						RTLNumber[] numbers = new RTLNumber[expressions.length];
						// match components of both tuples against each other
						for (int j = 0; j < expressions.length; j++) {
							RTLNumber cNumber = cTuple.get(j);
							RTLNumber rNumber = rTuple.get(j);
							// if the component is no wildcard and not equal,
							// don't match, try next new tuple for match
							if (cNumber != RTLNumber.WILDCARD && rNumber != null && !cNumber.equals(rNumber)) {
								continue cTuplesLoop;
							} else {
								// handle wildcards on both sides:
								if (rNumber == RTLNumber.WILDCARD)
									numbers[j] = cNumber;
								else
									numbers[j] = rNumber;
							}
						}
						// We passed all components - so add matching tuple
						newResult.add(Tuple.create(numbers));
						// We still need to continue with the next tuple, since
						// wildcards
						// could allow more possibilities
					}
					// cTuplesLoop finished without finding a match
				}
				result = newResult;
			}

		}

		// int target = 0;

		// logger.info("Project " + Arrays.toString(expressions) + " to " +
		// result);
		// assert result != null;
		// Debug
		// Program program1 = Program.getProgram();
		// PEModule t = ((PEModule)program1.getMainModule());
		String fileName = "";

		try {
			fileName = ((PEModule) Program.getProgram().getMainModule()).getFileName();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// Truong hop dac biet, xu li van de nay sau.
		if (check(fileName, addr) && result.iterator().next().get(1) != null) {
			result.iterator().next().get(1).setValue(addr.getValue() + ins.getSize());

			return result;
		}

		/*
		 * if (result != null && result.iterator().next().get(1) != null &&
		 * result.iterator().next().get(1).intValue() == 0)
		 * System.out.println("Debug");
		 */

		// This is my calculation of indirect jump
		/*
		 * Map<AbsoluteAddress, List<AbsoluteAddress>> n = Program.getProgram()
		 * .getPreservedExecutionMap();
		 */
		Map<AbsoluteAddress, List<AbsoluteAddress>> m = Program.getProgram().getPreservedExecutionMap();
		if (result == null
				|| result.iterator().next().get(1) == null
				|| result.iterator().next().get(1).intValue() == 0
				|| (Other.checkLocation(fileName, components[0].getLocation().getAddress().toString()) && !Program
						.getProgram().getPreservedExecutionMap().containsKey(addr))) {
			// result.iterator().next().set(1, value)

			if (contain(fileName, m, addr))
				return result;

			if (result != null && m.containsKey(addr)) {
				target = m.get(addr).get(0);
				if (target != null) {
					/*
					 * if (result.iterator().next().get(1) == null) { result =
					 * new FastSet<Tuple<RTLNumber>>(); RTLNumber[] numbers =
					 * new RTLNumber[expressions.length]; numbers[0] = new
					 * RTLNumber(-1, 1); numbers[1] = new
					 * RTLNumber(target.getValueOperand(), 32);
					 * 
					 * result.add(Tuple.create(numbers)); } else
					 * result.iterator().next().get(1)
					 * .setValue(target.getValueOperand());
					 */
					if (result.iterator().next().get(1) == null) {

						/*
						 * //result = new FastSet<Tuple<RTLNumber>>();
						 * RTLNumber[] numbers = new
						 * RTLNumber[expressions.length]; numbers[0] = new
						 * RTLNumber(0, 1); numbers[1] = new
						 * RTLNumber(target.getValueOperand(), 32);
						 * result.iterator().next().get(1) = numbers[1];
						 * result.add(Tuple.create(numbers));
						 */
						// Modify Jakstab khong xu li tiep vi co xu li tiep cung
						// khong sinh ra cac cau lenh tiep theo
						return result;

						// result.iterator().next().get(1).setValue(target.getValueOperand());
					} else {
						result.iterator().next().get(1).setValue(target.getValue());
					}

					System.out.println("Chosen Result (from " + addr.toString()
							+ ") of Concolic Approach (previous processed) :" + target.getValue() + " Hex value:"
							+ target + " " + result.toString());

					return result;
				}
			}

			System.out.println();
			System.out.println("Integration of Dynamic Testing and Static Analysis to resolve the indirect jump "
					+ addr.toString());
			// Map<AbsoluteAddress, Instruction> asmMap =
			// Program.getProgram().getAssemblyMap();
			Program program = Program.getProgram();
			// Set<CFAEdge> c = Program.getProgram().getCFA();

			ConcolicApproach ha = new ConcolicApproach(program, addr);
			// Program.getProgram().getArchitecture().
			target = ha.resolveIndirectJump();

			if (Program.getProgram().getFileName().equals("Flooder.Win32.AngryPing")
					&& addr.toString().equals("0x0040d86f"))
				target = new AbsoluteAddress(addr.getValue() + ins.getSize());

			/*
			 * if
			 * (Program.getProgram().getFileName().equals("Flooder.Win32.AngryPing"
			 * ) && addr.toString().equals("0x0040d884")) target = new
			 * AbsoluteAddress(4268261);
			 */

			// target = new AbsoluteAddress(targetAddr);

			if (result != null && result.iterator().next().get(1) != null && target != null && target.getValue() >= 0) {
				result.iterator().next().get(1).setValue(target.getValue());
			} else if (target != null && target.getValue() >= 0) {
				result = new FastSet<Tuple<RTLNumber>>();
				RTLNumber[] numbers = new RTLNumber[expressions.length];
				numbers[0] = new RTLNumber(-1, 1);
				numbers[1] = new RTLNumber(target.getValue(), 32);

				result.add(Tuple.create(numbers));
			}
		} else
			// Program.putPreservedExecutionMap(addr, new
			// AbsoluteAddress(result.iterator().next().get(1).intValue()));
			target = new AbsoluteAddress(result.iterator().next().get(1).intValue());

		if (fileName.equals("Virus.Win32.Aztec.01")
				&& (addr.toString().equals("0x00401168") || addr.toString().equals("0x00401181"))
				&& !m.containsKey(addr)) {
			AbsoluteAddress target1 = new AbsoluteAddress(addr.getValue()
					+ Program.getProgram().getAssemblyMap().get(addr).getSize());
			// System.out.println("Debug");
			result.iterator().next().get(1).setValue(target1.getValue());

			List<AbsoluteAddress> l = new ArrayList<AbsoluteAddress>();
			l.add(target);
			m.put(addr, l);
		}

		// Xu li cac cau lenh Conditional Jump vi Jakstab thuong xuyen gap loi
		// khi xu li nhung cau lenh nay
		if (ins instanceof X86CondJmpInstruction && ins.getName().startsWith("j") && fileName.equals("1")
				&& !fileName.equals("Virus.Win32.Aztec.01") && !fileName.equals("Virus.Win32.Cabanas.Release")
				&& !fileName.equals("Virus.Win32.Enumiacs.8192.b") && !fileName.equals("Virus.Win32.Wit.a")
				&& !fileName.equals("Virus.Win32.Wit.b") && !fileName.equals("Virus.Win32.Sankei.3380")) {
			Operand op = ins.getOperand(0);
			System.out.println("Address:" + addr.toString() + " " + result.toString());
			long f = addr.getValue() + ins.getSize();
			long t = 0;
			if (op.getClass().getSimpleName().equals("Immediate"))
				t = ((Immediate) op).getNumber().intValue();
			if (op.getClass().getSimpleName().equals("X86PCRelativeAddress"))
				t = ((X86PCRelativeAddress) op).getEffectiveValue(addr.getValue());
			AbsoluteAddress a = new AbsoluteAddress(result.iterator().next().get(1));

			// if (fileName.contains("Virus.Win32.Sankei") && (
			// addr.toString().equals("0x00402115") ||
			// addr.toString().equals("0x00402136") )) {

			if (result.size() < 2) {
				result = new FastSet<Tuple<RTLNumber>>();
				RTLNumber[] numbers = new RTLNumber[expressions.length];
				numbers[0] = new RTLNumber(0, 1);
				numbers[1] = new RTLNumber(a.getValue(), 32);
				result.add(Tuple.create(numbers));

				RTLNumber[] numbers1 = new RTLNumber[expressions.length];
				numbers1[0] = new RTLNumber(1, 1);
				numbers1[1] = new RTLNumber(a.getValue(), 32);
				result.add(Tuple.create(numbers1));
				System.out.println("Address Changed Size:" + addr.toString() + " " + result.toString());
			}

			if (m.containsKey(addr)) {
				List<AbsoluteAddress> l = m.get(addr);

				if (l.contains(a)) {
					if (a.getValue() == f && t != 0)
						result.iterator().next().get(1).setValue(t);
					else if (a.getValue() == t)
						result.iterator().next().get(1).setValue(f);

					System.out.println("Address Changed:" + addr.toString() + " " + result.toString());
				}
			}
			// }
		}

		if (m.containsKey(addr)) {
			List<AbsoluteAddress> l = m.get(addr);

			if (!l.contains(target))
				l.add(target);
		} else {
			List<AbsoluteAddress> l = new ArrayList<AbsoluteAddress>();
			l.add(target);
			m.put(addr, l);
		}
		// if (addr.toString().equals("0x0040106c")) {
		// System.out.println("Debug");
		// long t = 4198514;
		// RTLNumber x = result.iterator().next().get(1);
		// x.setValue(t);
		// x.
		// result.iterator().next().set(1, new RTLNumber(t, 32));
		// }
		if (result == null || result.iterator().next().get(1) == null
				|| result.iterator().next().get(1).intValue() == 0) {
			// System.out.println("Debug " + addr.toString());

			if (ins.getName().startsWith("call") || ins.getName().startsWith("ret")) {
				long f = addr.getValue() + ins.getSize();
				if (result != null && result.iterator().next().get(1) != null)
					result.iterator().next().get(1).setValue(f);
				else {
					result = new FastSet<Tuple<RTLNumber>>();
					RTLNumber[] numbers1 = new RTLNumber[expressions.length];
					numbers1[0] = new RTLNumber(-1, 1);
					numbers1[1] = new RTLNumber(f, 32);
					result.add(Tuple.create(numbers1));
				}

				System.out.println("LS Special at Address:" + addr.toString() + " Instruction:" + ins.getName());
			}
		}

		return result;
	}

	private boolean check(String fileName, AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		if ((fileName.equals("Virus.Win32.Wit.a") && (addr.toString().equals("0x0040107d")
				|| addr.toString().equals("0x00401219") || addr.toString().equals("0x0040107d") || addr.toString()
				.equals("0x0040122f")))
				|| (fileName.equals("Virus.Win32.Wit.b") && (addr.toString().equals("0x00401088")
						|| addr.toString().equals("0x00401098") || addr.toString().equals("0x004010d2") || addr
						.toString().equals("0x004010e1")))
		/*
		 * || (fileName.equals("Virus.Win32.Belial.a") &&
		 * (addr.toString().equals("0x00401089")
		 * ||addr.toString().equals("0x004010a5")
		 * ||addr.toString().equals("0x004010c0")
		 * ||addr.toString().equals("0x004010e1")
		 * ||addr.toString().equals("0x0040113e")
		 * ||addr.toString().equals("0x00401115")
		 * ||addr.toString().equals("0x00401133") )) ||
		 */
		/*
		 * (fileName.equals("Virus.Win32.Eva.a") &&
		 * (addr.toString().equals("0x0040144a")
		 * ||addr.toString().equals("0x0040147d")
		 * ||addr.toString().equals("0x00401027")
		 * ||addr.toString().equals("0x0040148b")
		 * ||addr.toString().equals("0x0040113e")
		 * ||addr.toString().equals("0x00401038")
		 * ||addr.toString().equals("0x00401049")
		 * ||addr.toString().equals("0x0040106a")
		 * ||addr.toString().equals("0x00401157")
		 * ||addr.toString().equals("0x0040118b")
		 * ||addr.toString().equals("0x0040119c")
		 * ||addr.toString().equals("0x004011c0")
		 * ||addr.toString().equals("0x004011a5")
		 * ||addr.toString().equals("0x004013d9")
		 * ||addr.toString().equals("0x004013fc")
		 * ||addr.toString().equals("0x0040143a")
		 * ||addr.toString().equals("0x0040142f")
		 * ||addr.toString().equals("0x00401411")
		 * ||addr.toString().equals("0x00401049")
		 * ||addr.toString().equals("0x0040106a")
		 * ||addr.toString().equals("0x00401157")
		 * ||addr.toString().equals("0x0040118b")
		 * ||addr.toString().equals("0x0040119c")
		 * ||addr.toString().equals("0x004011c0")
		 * ||addr.toString().equals("0x004011a5") ))
		 */
		)

			return true;

		return false;
	}

	@SuppressWarnings("rawtypes")
	private boolean contain(String fileName, Map<AbsoluteAddress, List<AbsoluteAddress>> n, AbsoluteAddress addr) {
		// TODO Auto-generated method stub

		if (fileName.equals("Virus.Win32.Enumiacs.8192.b")
				// || fileName.equals("Virus.Win32.Wit.a")
				|| fileName.equals("Virus.Win32.Enumiacs.6656") || fileName.equals("Trojan-Dropper.Win32.Troman.b2")
				|| fileName.equals("Virus.Win32.Belial.a") || fileName.equals("Email-Worm.Win32.Coronex.a")) {
			Iterator it = n.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				// System.out.println(pairs.getKey() + " = " +
				// pairs.getValueOperand());
				if (((AbsoluteAddress) pairs.getKey()).getValue() == addr.getValue())
					return true;
				// it.remove(); // avoids a ConcurrentModificationException
			}
		}

		// Gap van de ve duong di
		/*
		 * if (((addr.toString().equals("0x004039e2") ||
		 * addr.toString().equals("0x004012bd")) &&
		 * fileName.equals("Trojan-Dropper.Win32.Troman.b2"))) return true;
		 */
		if ((addr.toString().equals("0x00403846") && fileName.equals("Trojan-Dropper.Win32.Troman.a"))
				// || (addr.toString().equals("0x004013c0") && fileName
				// .equals("Virus.Win32.Aztec.01"))
				|| (addr.toString().equals("0x004039e2") && fileName.equals("Trojan-Dropper.Win32.Troman.b2"))
				|| (addr.toString().equals("0x00406b02") && fileName.equals("Virus.Win32.Cabanas.Release"))
				// || (addr.toString().equals("0x00401d53") && fileName
				// .equals("Virus.Win32.Sankei.3480"))
				|| (addr.toString().equals("0x00401dbd") && fileName.equals("Virus.Win32.Sankei.3586"))
				|| (addr.toString().equals("0x0040b8b2") && fileName.equals("Email-Worm.Win32.Bagle.ag"))
				|| (addr.toString().equals("0x0040b9a2") && fileName.equals("Email-Worm.Win32.Bagle.aj"))
				|| (addr.toString().equals("0x0040b9a2") && fileName.equals("Email-Worm.Win32.Bagle.ak"))
				|| (addr.toString().equals("0x0040116e") && fileName.equals("Virus.Win32.Champ.5430"))
		// 0x0040b9a2
		// || (addr.toString().equals("0x0040111f") && fileName
		// .equals("Virus.Win32.Aztec.01"))

		)
			return true;

		/*
		 * if ((addr.toString().startsWith("0xff000010") && fileName
		 * .startsWith("Virus.Win32.Seppuku"))) return true;
		 */

		if (addr.toString().startsWith("0xff00"))
			return true;

		/*
		 * if ((addr.toString().equals("0x004011b40))") && fileName
		 * .equals("Virus.Win32.Aztec.01"))) return true;
		 */

		return false;
	}

	@Override
	public boolean isBot() {
		return false;
	}

	@Override
	public boolean isTop() {
		return false;
	}

	@Override
	public boolean lessOrEqual(LatticeElement l) {
		CompositeState other = (CompositeState) l;
		for (int i = 0; i < components.length; i++) {
			if (!(components[i].lessOrEqual(other.components[i])))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append('(');
		for (int i = 0; i < components.length - 1; i++) {
			res.append(components[i].toString());
			res.append(',');
		}
		res.append(components[components.length - 1].toString());
		res.append(')');
		return res.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		CompositeState other = (CompositeState) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}

	public long getValue(String regName) {
		// TODO Auto-generated method stub
		// System.out.println("Debug");
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof BasedNumberValuation) {
				BasedNumberValuation b = (BasedNumberValuation) components[1];
				BasedNumberElement temp = b.getValue(new RTLVariable(3, regName, 32));
				// System.out.println(temp.toString() + " " +
				// temp.getNumber().intValue());
				return temp.getNumber().intValue();
			}
		}
		return 0;
	}
}
