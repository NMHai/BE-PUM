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

package org.jakstab.disasm.x86;

import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86InstructionFactory;
import org.jakstab.util.BinaryInputBuffer;
import org.jakstab.util.Logger;

public class FloatGRPDecoder extends FPInstructionDecoder {

	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(FloatGRPDecoder.class);

	final private int number;

	// Please refer to IA-32 Intel Architecture Software Developer's Manual
	// Volume 2
	// APPENDIX A - Escape opcodes

	private static final FPInstructionDecoder floatGRPMap[][] = {
			/* d9_2 */
			{ new FPInstructionDecoder("fnop"), null, null, null, null, null, null, null },
			/* d9_4 */
			{ new FPInstructionDecoder("fchs"), new FPInstructionDecoder("fabs"), null, null,
					new FPInstructionDecoder("ftst"), new FPInstructionDecoder("fxam"), null, null },
			/* d9_5 */
			{ new FPInstructionDecoder("fld1"), new FPInstructionDecoder("fldl2t"), new FPInstructionDecoder("fldl2e"),
					new FPInstructionDecoder("fldpi"), new FPInstructionDecoder("fldlg2"),
					new FPInstructionDecoder("fldln2"), new FPInstructionDecoder("fldz"), null },
			/* d9_6 */
			{ new FPInstructionDecoder("f2xm1"), new FPInstructionDecoder("fyl2x"), new FPInstructionDecoder("fptan"),
					new FPInstructionDecoder("fpatan"), new FPInstructionDecoder("fxtract"),
					new FPInstructionDecoder("fprem1"), new FPInstructionDecoder("fdecstp"),
					new FPInstructionDecoder("fincstp") },
			/* d9_7 */
			{ new FPInstructionDecoder("fprem"), new FPInstructionDecoder("fyl2xp1"),
					new FPInstructionDecoder("fsqrt"), new FPInstructionDecoder("fsincos"),
					new FPInstructionDecoder("frndint"), new FPInstructionDecoder("fscale"),
					new FPInstructionDecoder("fsin"), new FPInstructionDecoder("fcos") },
			/* da_5 */
			{ null, new FPInstructionDecoder("fucompp", ADDR_FPREG, 1), null, null, null, null, null, null },
			/* db_4 */
			{ new FPInstructionDecoder("feni(287 only)"), new FPInstructionDecoder("fdisi(287 only)"),
					new FPInstructionDecoder("fNclex"), new FPInstructionDecoder("fNinit"),
					new FPInstructionDecoder("fNsetpm(287 only)"), null, null, null },
			/* de_3 */
			{ null, new FPInstructionDecoder("fcompp"), null, null, null, null, null, null },
			/* df_4 */
			{ new FPInstructionDecoder("fNstsw", ADDR_REG, AX), null, null, null, null, null, null, null } };

	public FloatGRPDecoder(String name, int number) {
		super(name);
		this.number = number;
	}

	public Instruction decode(BinaryInputBuffer bytesArray, int index, int instrStartIndex, int segmentOverride,
			int prefixes, X86InstructionFactory factory) {
		this.byteIndex = index;
		this.instrStartIndex = instrStartIndex;
		this.prefixes = prefixes;

		int ModRM = readByte(bytesArray, byteIndex);
		int rm = ModRM & 7;

		FPInstructionDecoder instrDecoder = null;
		instrDecoder = floatGRPMap[number][rm];

		Instruction instr = null;
		if (instrDecoder != null) {
			instr = instrDecoder.decode(bytesArray, byteIndex, instrStartIndex, segmentOverride, prefixes, factory);
			byteIndex = instrDecoder.getCurrentIndex();
		}
		return instr;
	}
}
