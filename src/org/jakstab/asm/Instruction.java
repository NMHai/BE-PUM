/*
 * Copyright 2002 Sun Microsystems, Inc.  All Rights Reserved.
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

package org.jakstab.asm;

import org.analysis.SymbolicState;
import org.analysis.SymbolicValue;

/**
 * Top level interface for all instruction types. Defines a set of methods
 * common for all instructions.
 */
public interface Instruction {

	/**
	 * Returns the name of the instruction, i.e. its mnemonic in AT&T syntax.
	 */
	public String getName();

	public boolean compareInstruction(Instruction instr);

	/**
	 * Total size in bytes (operands + opcode). In sparc it is always 4 (=
	 * 32bits).
	 */
	public int getSize();

	/**
	 * Returns the operand with the given index, where 0 corresponds to the
	 * first operand. If the specified operand does not exist, returns null.
	 * 
	 * @return the operand with the given index or null if it does not exist.
	 */
	public Operand getOperand(int i);

	/**
	 * Returns the number of operands of this instruction.
	 * 
	 * @return the number of operands.
	 */
	public int getOperandCount();

	/**
	 * Return a string representation of the instruction given a program counter
	 * value and a SymbolFinder.
	 */
	public String toString(long currentPc, SymbolFinder symFinder);

	/**
	 * Interprete to create a new state
	 */
	public SymbolicValue interprete(SymbolicState prevState);

	// public void interprete ();

}
