/*
 * Copyright 2002-2003 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
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
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 *  
 */

/* 
 * Original code for this class taken from the Java HotSpot VM. 
 * Modified for use with the Jakstab project. All modifications 
 * Copyright 2007-2012 Johannes Kinder <jk@jakstab.org>
 */

package org.jakstab.asm.x86;

import org.analysis.SymbolicExecution;
import org.analysis.SymbolicState;
import org.analysis.SymbolicValue;
import org.analysis.X86Interpretation;
import org.jakstab.asm.Address;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.ReturnInstruction;
import org.jakstab.asm.SymbolFinder;

public class X86RetInstruction extends X86Instruction implements ReturnInstruction {

	public X86RetInstruction(String name, Immediate op1, int size, int prefixes) {
		super(name, op1, size, prefixes);
		if (SymbolicExecution.INSTRUCTION_DEBUG_MODE) {
			System.out.print("RetInstruction<" + name);
			if (op1 != null) {
				System.out.print(", " + op1.toString());
			}
			System.out.print(", " + size);
			System.out.print(", " + prefixes);
			System.out.println(">");
		}
	}

	public X86RetInstruction(String name, int size, int prefixes) {
		super(name, size, prefixes);
		if (SymbolicExecution.INSTRUCTION_DEBUG_MODE) {
			System.out.print("RetInstruction<" + name);
			System.out.print(", " + size);
			System.out.print(", " + prefixes);
			System.out.println(">");
		}
	}

	public Immediate getBytesToRelease() {
		return (Immediate) getOperand1();
	}

	@Override
	public String toString(long currentPc, SymbolFinder symFinder) {
		StringBuffer buf = new StringBuffer();
		buf.append(getPrefixString());
		buf.append(getName());
		if (getBytesToRelease() != null) {
			buf.append(spaces);
			buf.append(getBytesToRelease());
		}
		return buf.toString();
	}

	@Override
	public Address getBranchDestination() {
		return null;
	}

	@Override
	public boolean isConditional() {
		return false;
	}

	@Override
	public boolean isIndirect() {
		return false;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public SymbolicValue interprete(SymbolicState prevState) {
		return X86Interpretation.X86ReturnInterprete(this, prevState);
	}
}
