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
 * JDoc annotations are Copyright 2007-2012 Johannes Kinder
 */

/* 
 * Original code for this class taken from the Java HotSpot VM. 
 * Modified for use with the Jakstab project. All modifications 
 * Copyright 2007-2012 Johannes Kinder <jk@jakstab.org>
 */

package org.jakstab.disasm.x86;

import java.io.IOException;

import org.jakstab.asm.DataType;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operation;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86InstructionFactory;
import org.jakstab.asm.x86.X86InstructionFactoryImpl;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86MoveInstruction;
import org.jakstab.asm.x86.X86Opcodes;
import org.jakstab.asm.x86.X86Register;
import org.jakstab.asm.x86.X86SegmentRegister;
import org.jakstab.disasm.Disassembler;
import org.jakstab.util.BinaryInputBuffer;
import org.jakstab.util.Logger;

public class X86Disassembler implements Disassembler, X86Opcodes {
	private final static Logger logger = Logger.getLogger(X86Disassembler.class);

	protected final X86InstructionFactory factory;
	protected final BinaryInputBuffer code;
	private int byteIndex;

	private X86Disassembler(BinaryInputBuffer code, X86InstructionFactory factory) {
		this.code = code;
		this.factory = factory;
	}

	public BinaryInputBuffer getCode() {
		return code;
	}

	/**
	 * Creates a new disassembler working on the given bytearray.
	 * 
	 * @param code
	 *            Byte array of code to be disassembled.
	 */
	public X86Disassembler(BinaryInputBuffer code) {
		this(code, new X86InstructionFactoryImpl());
	}

	// PHONG: test here
	public final Instruction decodeInstruction() {
		Instruction instr = null;
		InstructionDecoder instrDecoder = null;
		byteIndex = 0;
		int firstByte = InstructionDecoder.readByte(code, byteIndex);		
		instr = specialCase(firstByte);
		if (instr != null) {
			return instr;
		}

		int instrStartIndex = 0;
		int prefixes = 0;
		instrStartIndex = byteIndex;
		try {
			prefixes = getPrefixes();
			int segmentOverride = 1;
			int opcode = (code.getByteAt(byteIndex) & 0xFF) & 0xFF;
			byteIndex++;
			if (opcode == 0x0f) {
				opcode = (code.getByteAt(byteIndex) & 0xFF) & 0xFF;
				byteIndex++;

				// SSE: SSE instructions have reserved use of 0xF2, 0xF3, 0x66
				// prefixes
				if ((prefixes & PREFIX_REPNZ) != 0) {
					instrDecoder = twoBytePrefixF2Table[opcode];
					// Remove the prefix if the instruction is from this table
					if (instrDecoder != null) {
						prefixes = prefixes & (-1 ^ PREFIX_REPNZ);
					}
				} else if ((prefixes & PREFIX_REPZ) != 0) {
					instrDecoder = twoBytePrefixF3Table[opcode];
					if (instrDecoder != null) {
						prefixes = prefixes & (-1 ^ PREFIX_REPZ);
					}
				} else if ((prefixes & PREFIX_DATA) != 0) {
					instrDecoder = twoBytePrefix66Table[opcode];
					if (instrDecoder != null) {
						prefixes = prefixes & (-1 ^ PREFIX_DATA);
					}
				}
				if (instrDecoder == null) {
					instrDecoder = twoByteTable[opcode];
				}

			} else {
				instrDecoder = oneByteTable[opcode];
			}

			if (instrDecoder == null) {
				logger.error("Cannot find decoder for opcode " + Long.toHexString(opcode) + ".");
				return null;
			}

			instr = instrDecoder.decode(code, byteIndex, instrStartIndex, segmentOverride, prefixes, factory);
			if (instr == null) {
				logger.error("Decoder " + instrDecoder.getClass().toString() + " for opcode "
						+ Long.toHexString(opcode) + " returned null instruction!");
				return null;
			}
			byteIndex = instrDecoder.getCurrentIndex();
		} catch (Exception exp) {
			logger.error("Error during disassembly:", exp);
			exp.printStackTrace();
			return null;
		}
		return instr;
	}

	private Instruction specialCase(int firstByte) {
		// TODO Auto-generated method stub
		if (firstByte == 241) {
			return new X86Instruction("int1", 1, 512);
		}
		
		if (firstByte == 255) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 255) {
				return new X86Instruction("nop", 2, 512);
			}
		}

		if (firstByte == 100) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 103
					&& InstructionDecoder.readByte(code, byteIndex + 2) == 255
					&& InstructionDecoder.readByte(code, byteIndex + 3) == 54
					&& InstructionDecoder.readByte(code, byteIndex + 4) == 0
					&& InstructionDecoder.readByte(code, byteIndex + 5) == 0) {
				return new X86Instruction("pushl", new X86MemoryOperand(DataType.INT32,
						new X86SegmentRegister(4, "%fs"), 0), 6, 1152);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 103
					&& InstructionDecoder.readByte(code, byteIndex + 2) == 137
					&& InstructionDecoder.readByte(code, byteIndex + 3) == 38
					&& InstructionDecoder.readByte(code, byteIndex + 4) == 0
					&& InstructionDecoder.readByte(code, byteIndex + 5) == 0) {
				return new X86MoveInstruction("movl", new X86MemoryOperand(DataType.INT32, new X86SegmentRegister(4,
						"%fs"), 0), new X86Register(4, "%esp"), 6, 1152);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 103
					&& InstructionDecoder.readByte(code, byteIndex + 2) == 143
					&& InstructionDecoder.readByte(code, byteIndex + 3) == 6
					&& InstructionDecoder.readByte(code, byteIndex + 4) == 0
					&& InstructionDecoder.readByte(code, byteIndex + 5) == 0) {
				return new X86Instruction("popl", new X86MemoryOperand(DataType.INT32,
						new X86SegmentRegister(4, "%fs"), 0), 6, 1152);
			}
		}
		
		if (firstByte == 103) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 100
					&& InstructionDecoder.readByte(code, byteIndex + 2) == 255
					&& InstructionDecoder.readByte(code, byteIndex + 3) == 54
					&& InstructionDecoder.readByte(code, byteIndex + 4) == 0
					&& InstructionDecoder.readByte(code, byteIndex + 5) == 0) {
				return new X86Instruction("pushl", new X86MemoryOperand(DataType.INT32,
						new X86SegmentRegister(4, "%fs"), 0), 6, 1152);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 100
					&& InstructionDecoder.readByte(code, byteIndex + 2) == 137
					&& InstructionDecoder.readByte(code, byteIndex + 3) == 38
					&& InstructionDecoder.readByte(code, byteIndex + 4) == 0
					&& InstructionDecoder.readByte(code, byteIndex + 5) == 0) {
				return new X86MoveInstruction("movl", new X86MemoryOperand(DataType.INT32, new X86SegmentRegister(4,
						"%fs"), 0), new X86Register(4, "%esp"), 6, 1152);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 100
					&& InstructionDecoder.readByte(code, byteIndex + 2) == 143
					&& InstructionDecoder.readByte(code, byteIndex + 3) == 6
					&& InstructionDecoder.readByte(code, byteIndex + 4) == 0
					&& InstructionDecoder.readByte(code, byteIndex + 5) == 0) {
				return new X86Instruction("popl", new X86MemoryOperand(DataType.INT32,
						new X86SegmentRegister(4, "%fs"), 0), 6, 1152);
			}
		}
		
		if (firstByte == 193) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 240) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%eax"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 241) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ecx"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 243) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ebx"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 242) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%edx"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 246) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%esi"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 247) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%edi"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 244) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%esp"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 245) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ebp"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			}			
		}
		
		if (firstByte == 192) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 240) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%al"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 241) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%cl"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 243) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%bl"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 242) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%dl"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 246) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%dh"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 247) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%bh"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 244) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ah"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 245) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ch"), 
						new Immediate(InstructionDecoder.readByte(code, byteIndex + 2), DataType.INT8), null, 3, 0);
			}			
		}
		
		if (firstByte == 209) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 240) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%eax"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 241) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ecx"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 243) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ebx"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 242) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%edx"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 246) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%esi"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 247) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%edi"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 244) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%esp"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 245) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ebp"), 
						new Immediate(1, DataType.INT8), null, 2, 0);
			}			
		}
		
		if (firstByte == 210) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 240) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%al"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 241) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%cl"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 242) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%dl"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 243) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%bl"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 244) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ah"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 245) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ch"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 246) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%dh"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 247) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%bh"), 
						new X86Register(3, "%cl"), null, 2, 0);
			} 		
		}
		
		if (firstByte == 208) {
			if (InstructionDecoder.readByte(code, byteIndex + 1) == 240) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%al"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 241) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%cl"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 242) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%dl"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 243) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%bl"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 244) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ah"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 245) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%ch"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 246) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%dh"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} else if (InstructionDecoder.readByte(code, byteIndex + 1) == 247) {
				return new X86ArithmeticInstruction("sall", Operation.SLL, new X86Register(3, "%bh"), 
						new Immediate(1, DataType.INT8), null, 3, 0);
			} 		
		}

		if (firstByte == 192 && InstructionDecoder.readByte(code, byteIndex + 1) == 240
				     && InstructionDecoder.readByte(code, byteIndex + 2) == 3) {
			InstructionDecoder.setByte(code, byteIndex + 1, 224);
		}

		if (firstByte == 102 && InstructionDecoder.readByte(code, byteIndex + 1) == 211
				     && InstructionDecoder.readByte(code, byteIndex + 2) == 246) {
			InstructionDecoder.setByte(code, byteIndex + 2, 230);
		}

		if (firstByte == 211 && InstructionDecoder.readByte(code, byteIndex + 1) == 241) {
			InstructionDecoder.setByte(code, byteIndex + 1, 225);
		}

		if (firstByte == 102 && InstructionDecoder.readByte(code, byteIndex + 1) == 152) {
			return new X86Instruction("cbws", 2, 512);
		}
		if (firstByte == 152) {
			return new X86Instruction("cwdel", 1, 512);
		}
		
		if (firstByte == 214) {
			return new X86Instruction("salc", 1, 512);
		}
		
		return null;
	}

	@Override
	public final Instruction decodeInstruction(long index) {
		Instruction instr = null;
		InstructionDecoder instrDecoder = null;
		byteIndex = (int) index; // For 64bit systems, this needs to be fixed
		// int len = byteIndex;

		/*
		 * if (InstructionDecoder.readByte(code, byteIndex) == 209) { if
		 * (InstructionDecoder.readByte(code, byteIndex + 1) == 224) {
		 * System.out.println("Use Capstone for disassembly: "); byte[] opCode =
		 * new byte[15]; for (int i=0; i<15; i++) { byte t =
		 * (byte)InstructionDecoder.readByte(code, byteIndex + i);; opCode[i] =
		 * t; }
		 * 
		 * Capstone cs = new Capstone(Capstone.CS_ARCH_X86,
		 * Capstone.CS_MODE_32); Capstone.CsInsn[] allInsn = cs.disasm(opCode,
		 * 0x00400000); for (int i = 0; i < allInsn.length; i++) {
		 * System.out.printf("%d\t0x%x:\t%s\t%s\n",i+1, allInsn[i].address,
		 * allInsn[i].mnemonic, allInsn[i].opStr); } //allInsn[0].; //instr =
		 * new X86ArithmeticInstruction(allInsn[0].mnemonic, new X86Register(4,
		 * "%esp"), 1, null, 2, 1); //return instr; } }
		 */
		int firstByte = InstructionDecoder.readByte(code, byteIndex);
		instr = specialCase(firstByte);
		if (instr != null) {
			return instr;
		}

		int instrStartIndex = 0;

		int prefixes = 0;
		instrStartIndex = byteIndex;

		try {
			// check if there is any prefix
			prefixes = getPrefixes();

			int segmentOverride = 1; // get segment override prefix

			// Read opcode
			int opcode = InstructionDecoder.readByte(code, byteIndex);
			byteIndex++;

			// Check for escape opcode 0Fh
			if (opcode == 0x0f) {
				opcode = InstructionDecoder.readByte(code, byteIndex);
				byteIndex++;

				// SSE: SSE instructions have reserved use of 0xF2, 0xF3, 0x66
				// prefixes
				if ((prefixes & PREFIX_REPNZ) != 0) {
					instrDecoder = twoBytePrefixF2Table[opcode];
					// Remove the prefix if the instruction is from this table
					if (instrDecoder != null) {
						prefixes = prefixes & (-1 ^ PREFIX_REPNZ);
					}
				} else if ((prefixes & PREFIX_REPZ) != 0) {
					instrDecoder = twoBytePrefixF3Table[opcode];
					if (instrDecoder != null) {
						prefixes = prefixes & (-1 ^ PREFIX_REPZ);
					}
				} else if ((prefixes & PREFIX_DATA) != 0) {
					instrDecoder = twoBytePrefix66Table[opcode];
					if (instrDecoder != null) {
						prefixes = prefixes & (-1 ^ PREFIX_DATA);
					}
				} /*
				 * does not work with prefixed standard 2-Byte instructions!
				 * else { instrDecoder = twoByteTable[opcode]; }
				 */
				if (instrDecoder == null) {
					instrDecoder = twoByteTable[opcode];
				}

			} else {
				instrDecoder = oneByteTable[opcode];
			}

			if (instrDecoder == null) {
				logger.error("Cannot find decoder for opcode " + Long.toHexString(opcode) + ".");

				if (opcode == 241) {
					instr = new X86Instruction("int", 1, 0);
					return instr;
				}
				return null;
			}

			instr = instrDecoder.decode(code, byteIndex, instrStartIndex, segmentOverride, prefixes, factory);
			if (instr == null) {
				logger.error("Decoder " + instrDecoder.getClass().toString() + " for opcode "
						+ Long.toHexString(opcode) + " returned null instruction!");
				return null;
			}
			// len = instrDecoder.getCurrentIndex();
			// byteIndex = len;
			byteIndex = instrDecoder.getCurrentIndex();
		} catch (Exception exp) {
			logger.error("Error during disassembly:", exp);
			exp.printStackTrace();
			return null;
		}

		return instr;
	}

	private final int getPrefixes() {

		int prefixes = 0;
		boolean isPrefix = true;

		while (isPrefix) {
			int prefixByte = InstructionDecoder.readByte(code, byteIndex);

			switch (prefixByte) {
			case 0xf3:
				prefixes |= PREFIX_REPZ;
				break;
			case 0xf2:
				prefixes |= PREFIX_REPNZ;
				break;
			case 0xf0:
				prefixes |= PREFIX_LOCK;
				break;
			case 0x2e:
				prefixes |= PREFIX_CS;
				break;
			case 0x36:
				prefixes |= PREFIX_SS;
				break;
			case 0x3e:
				prefixes |= PREFIX_DS;
				break;
			case 0x26:
				prefixes |= PREFIX_ES;
				break;
			case 0x64:
				prefixes |= PREFIX_FS;
				break;
			case 0x65:
				prefixes |= PREFIX_GS;
				break;
			case 0x66:
				prefixes |= PREFIX_DATA;
				break;
			case 0x67:
				prefixes |= PREFIX_ADR;
				break;
			/*
			 * This had to be removed, since FWAIT is really an instruction and
			 * can appear on its own. Mnemonics like FSTSW are only macros,
			 * anyway, and a jump to the FNSTSW part after 0x9B is ok. To make
			 * this really work, we would need to check for an earlier FWAIT
			 * instruction while parsing instructions like FNSTSW. case 0x9b:
			 * prefixes |= PREFIX_FWAIT; break;
			 */
			default:
				isPrefix = false;
			}
			if (isPrefix) {
				byteIndex++;
			}
		}
		return prefixes;
	}

	// Please refer to IA-32 Intel Architecture Software Developer's Manual
	// Volume 2
	// APPENDIX A - Table A-2. One-byte Opcode Map
	private static final InstructionDecoder oneByteTable[] = {
			/* 00 */
			new ArithmeticDecoder("addb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.ADD),
			new ArithmeticDecoder("addS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.ADD),
			new ArithmeticDecoder("addb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.ADD),
			new ArithmeticDecoder("addS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.ADD),
			new ArithmeticDecoder("addb", ADDR_REG, AL, ADDR_I, b_mode, Operation.ADD),
			new ArithmeticDecoder("addS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.ADD),
			new InstructionDecoder("pushl", ADDR_REG, ES),
			new InstructionDecoder("popl", ADDR_REG, ES),
			/* 08 */
			new ArithmeticDecoder("orb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.OR),
			new ArithmeticDecoder("orS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.OR),
			new ArithmeticDecoder("orb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.OR),
			new ArithmeticDecoder("orS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.OR),
			new ArithmeticDecoder("orb", ADDR_REG, AL, ADDR_I, b_mode, Operation.OR),
			new ArithmeticDecoder("orS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.OR),
			new InstructionDecoder("pushl", ADDR_REG, CS),
			null, /* 0x0f extended opcode escape */
			/* 10 */
			new ArithmeticDecoder("adcb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.ADDC),
			new ArithmeticDecoder("adcS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.ADDC),
			new ArithmeticDecoder("adcb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.ADDC),
			new ArithmeticDecoder("adcS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.ADDC),
			new ArithmeticDecoder("adcb", ADDR_REG, AL, ADDR_I, b_mode, Operation.ADDC),
			new ArithmeticDecoder("adcS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.ADDC),
			new InstructionDecoder("pushl", ADDR_REG, SS),
			new InstructionDecoder("popl", ADDR_REG, SS),
			/* 18 */
			new ArithmeticDecoder("sbbb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.SUBC),
			new ArithmeticDecoder("sbbS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.SUBC),
			new ArithmeticDecoder("sbbb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.SUBC),
			new ArithmeticDecoder("sbbS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.SUBC),
			new ArithmeticDecoder("sbbb", ADDR_REG, AL, ADDR_I, b_mode, Operation.SUBC),
			new ArithmeticDecoder("sbbS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.SUBC),
			new InstructionDecoder("pushl", ADDR_REG, DS),
			new InstructionDecoder("popl", ADDR_REG, DS),
			/* 20 */
			new ArithmeticDecoder("andb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.AND),
			new ArithmeticDecoder("andS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.AND),
			new ArithmeticDecoder("andb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.AND),
			new ArithmeticDecoder("andS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.AND),
			new ArithmeticDecoder("andb", ADDR_REG, AL, ADDR_I, b_mode, Operation.AND),
			new ArithmeticDecoder("andS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.AND),
			null, /* SEG es prefix */
			new InstructionDecoder("daa"),
			/* 28 */
			new ArithmeticDecoder("subb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.SUB),
			new ArithmeticDecoder("subS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.SUB),
			new ArithmeticDecoder("subb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.SUB),
			new ArithmeticDecoder("subS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.SUB),
			new ArithmeticDecoder("subb", ADDR_REG, AL, ADDR_I, b_mode, Operation.SUB),
			new ArithmeticDecoder("subS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.SUB),
			null, /* SEG CS prefix */
			new InstructionDecoder("das"),
			/* 30 */
			new ArithmeticDecoder("xorb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.XOR),
			new ArithmeticDecoder("xorS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.XOR),
			new ArithmeticDecoder("xorb", ADDR_G, b_mode, ADDR_E, b_mode, Operation.XOR),
			new ArithmeticDecoder("xorS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.XOR),
			new ArithmeticDecoder("xorb", ADDR_REG, AL, ADDR_I, b_mode, Operation.XOR),
			new ArithmeticDecoder("xorS", ADDR_REG, EAX, ADDR_I, v_mode, Operation.XOR),
			null, /* SEG SS prefix */
			new InstructionDecoder("aaa"),
			/* 38 */
			new InstructionDecoder("cmpb", ADDR_E, b_mode, ADDR_G, b_mode),
			new InstructionDecoder("cmpS", ADDR_E, v_mode, ADDR_G, v_mode),
			new InstructionDecoder("cmpb", ADDR_G, b_mode, ADDR_E, b_mode),
			new InstructionDecoder("cmpS", ADDR_G, v_mode, ADDR_E, v_mode),
			new InstructionDecoder("cmpb", ADDR_REG, AL, ADDR_I, b_mode),
			new InstructionDecoder("cmpS", ADDR_REG, EAX, ADDR_I, v_mode),
			null, /* SEG DS prefix */
			new InstructionDecoder("aas"),
			/* 40 */
			new ArithmeticDecoder("incS", ADDR_REG, EAX, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, ECX, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, EDX, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, EBX, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, ESP, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, EBP, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, ESI, Operation.ADD),
			new ArithmeticDecoder("incS", ADDR_REG, EDI, Operation.ADD),
			/* 48 */
			new ArithmeticDecoder("decS", ADDR_REG, EAX, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, ECX, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, EDX, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, EBX, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, ESP, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, EBP, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, ESI, Operation.SUB),
			new ArithmeticDecoder("decS", ADDR_REG, EDI, Operation.SUB),
			/* 50 */
			new InstructionDecoder("pushS", ADDR_REG, EAX),
			new InstructionDecoder("pushS", ADDR_REG, ECX),
			new InstructionDecoder("pushS", ADDR_REG, EDX),
			new InstructionDecoder("pushS", ADDR_REG, EBX),
			new InstructionDecoder("pushS", ADDR_REG, ESP),
			new InstructionDecoder("pushS", ADDR_REG, EBP),
			new InstructionDecoder("pushS", ADDR_REG, ESI),
			new InstructionDecoder("pushS", ADDR_REG, EDI),
			/* 58 */
			new InstructionDecoder("popS", ADDR_REG, EAX),
			new InstructionDecoder("popS", ADDR_REG, ECX),
			new InstructionDecoder("popS", ADDR_REG, EDX),
			new InstructionDecoder("popS", ADDR_REG, EBX),
			new InstructionDecoder("popS", ADDR_REG, ESP),
			new InstructionDecoder("popS", ADDR_REG, EBP),
			new InstructionDecoder("popS", ADDR_REG, ESI),
			new InstructionDecoder("popS", ADDR_REG, EDI),
			/* 60 */
			new InstructionDecoder("pusha"),
			new InstructionDecoder("popa"),
			new InstructionDecoder("boundS", ADDR_G, v_mode, ADDR_E, v_mode),
			new InstructionDecoder("arpl", ADDR_E, w_mode, ADDR_G, w_mode),
			null, /* seg fs */
			null, /* seg gs */
			null, /* op size prefix */
			null, /* adr size prefix */
			/* 68 */
			new InstructionDecoder("pushS", ADDR_I, v_mode), /* 386 book wrong */
			new ArithmeticDecoder("imulS", ADDR_G, v_mode, ADDR_E, v_mode, ADDR_I, v_mode, Operation.SMUL),
			new InstructionDecoder("pushl", ADDR_I, b_mode), /*
															 * push of byte
															 * really pushes 4
															 * bytes (or 2 with
															 * data prefix)
															 */
			new ArithmeticDecoder("imulS", ADDR_G, v_mode, ADDR_E, v_mode, ADDR_I, b_mode, Operation.SMUL),
			new InstructionDecoder("insb", ADDR_ESDI, b_mode, INDIR_REG, DX),
			new InstructionDecoder("insS", ADDR_ESDI, v_mode, INDIR_REG, DX),
			new InstructionDecoder("outsb", INDIR_REG, DX, ADDR_DSSI, b_mode),
			new InstructionDecoder("outsS", INDIR_REG, DX, ADDR_DSSI, v_mode),
			/* 70 */
			new ConditionalJmpDecoder("jo", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jno", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jb", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jae", ADDR_J, b_mode),
			new ConditionalJmpDecoder("je", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jne", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jbe", ADDR_J, b_mode),
			new ConditionalJmpDecoder("ja", ADDR_J, b_mode),
			/* 78 */
			new ConditionalJmpDecoder("js", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jns", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jp", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jnp", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jl", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jnl", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jle", ADDR_J, b_mode),
			new ConditionalJmpDecoder("jg", ADDR_J, b_mode),
			/* 80 */
			new GRPDecoder(null, 0),
			new GRPDecoder(null, 1),
			new GRPDecoder(null, 0), // undocumented
			new GRPDecoder(null, 2),
			new InstructionDecoder("testb", ADDR_E, b_mode, ADDR_G, b_mode),
			new InstructionDecoder("testS", ADDR_E, v_mode, ADDR_G, v_mode),
			new MoveDecoder("xchgb", ADDR_E, b_mode, ADDR_G, b_mode),
			new MoveDecoder("xchgS", ADDR_E, v_mode, ADDR_G, v_mode),
			/* 88 */
			new MoveDecoder("movb", ADDR_E, b_mode, ADDR_G, b_mode),
			new MoveDecoder("movS", ADDR_E, v_mode, ADDR_G, v_mode),
			new MoveDecoder("movb", ADDR_G, b_mode, ADDR_E, b_mode),
			new MoveDecoder("movS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("movw", ADDR_E, w_mode, ADDR_SEG, w_mode),
			new InstructionDecoder("leaS", ADDR_G, v_mode, ADDR_E, v_mode), // JK:
																			// was
																			// ("leaS",
																			// ADDR_G,
																			// v_mode,
																			// ADDR_E,
																			// 0);
																			// but
																			// mode
																			// 0
																			// introduced
																			// errors!
			new MoveDecoder("movw", ADDR_SEG, w_mode, ADDR_E, w_mode),
			new InstructionDecoder("popS", ADDR_E, v_mode),
			/* 90 */
			new InstructionDecoder("nop"),
			new MoveDecoder("xchgS", ADDR_REG, ECX, ADDR_REG, EAX),
			new MoveDecoder("xchgS", ADDR_REG, EDX, ADDR_REG, EAX),
			new MoveDecoder("xchgS", ADDR_REG, EBX, ADDR_REG, EAX),
			new MoveDecoder("xchgS", ADDR_REG, ESP, ADDR_REG, EAX),
			new MoveDecoder("xchgS", ADDR_REG, EBP, ADDR_REG, EAX),
			new MoveDecoder("xchgS", ADDR_REG, ESI, ADDR_REG, EAX),
			new MoveDecoder("xchgS", ADDR_REG, EDI, ADDR_REG, EAX),
			/* 98 */
			new InstructionDecoder("cwtl"),
			new InstructionDecoder("cltd"),
			new CallDecoder("lcall", ADDR_DIR, p_mode),
			// Made fwait an instruction, too, to handle multiple fwait
			// prefixes!
			new InstructionDecoder("fwait"),
			// null, /* fwait */
			new InstructionDecoder("pushfS"),
			new InstructionDecoder("popfS"),
			new InstructionDecoder("sahf"),
			new InstructionDecoder("lahf"),
			/* a0 */
			new MoveDecoder("movb", ADDR_REG, AL, ADDR_OFF, b_mode),
			new MoveDecoder("movS", ADDR_REG, EAX, ADDR_OFF, v_mode),
			new MoveDecoder("movb", ADDR_OFF, b_mode, ADDR_REG, AL),
			new MoveDecoder("movS", ADDR_OFF, v_mode, ADDR_REG, EAX),
			new InstructionDecoder("movsb", ADDR_ESDI, b_mode, ADDR_DSSI, b_mode),
			new InstructionDecoder("movsS", ADDR_ESDI, v_mode, ADDR_DSSI, v_mode),
			new InstructionDecoder("cmpsb", ADDR_ESDI, b_mode, ADDR_DSSI, b_mode),
			new InstructionDecoder("cmpsS", ADDR_ESDI, v_mode, ADDR_DSSI, v_mode),
			/* a8 */
			new InstructionDecoder("testb", ADDR_REG, AL, ADDR_I, b_mode),
			new InstructionDecoder("testS", ADDR_REG, EAX, ADDR_I, v_mode),
			new InstructionDecoder("stosb", ADDR_ESDI, b_mode, ADDR_REG, AL),
			new InstructionDecoder("stosS", ADDR_ESDI, v_mode, ADDR_REG, EAX),
			new InstructionDecoder("lodsb", ADDR_REG, AL, ADDR_DSSI, b_mode),
			new InstructionDecoder("lodsS", ADDR_REG, EAX, ADDR_DSSI, v_mode),
			new InstructionDecoder("scasb", ADDR_REG, AL, ADDR_ESDI, b_mode),
			new InstructionDecoder("scasS", ADDR_REG, EAX, ADDR_ESDI, v_mode),
			/* b0 */
			new MoveDecoder("movb", ADDR_REG, AL, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, CL, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, DL, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, BL, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, AH, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, CH, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, DH, ADDR_I, b_mode),
			new MoveDecoder("movb", ADDR_REG, BH, ADDR_I, b_mode),
			/* b8 */
			new MoveDecoder("movS", ADDR_REG, EAX, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, ECX, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, EDX, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, EBX, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, ESP, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, EBP, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, ESI, ADDR_I, v_mode),
			new MoveDecoder("movS", ADDR_REG, EDI, ADDR_I, v_mode),
			/* c0 */
			new GRPDecoder(null, 3),
			new GRPDecoder(null, 4),
			new RetDecoder("ret", ADDR_I, w_mode),
			new RetDecoder("ret"),
			new InstructionDecoder("lesS", ADDR_G, v_mode, ADDR_E, p_mode), // JK:
																			// changed
																			// 0
																			// to
																			// p_mode
																			// according
																			// to
																			// lgs,
																			// lfs
			new InstructionDecoder("ldsS", ADDR_G, v_mode, ADDR_E, p_mode),
			new MoveDecoder("movb", ADDR_E, b_mode, ADDR_I, b_mode),
			new MoveDecoder("movS", ADDR_E, v_mode, ADDR_I, v_mode),
			/* c8 */
			new InstructionDecoder("enter", ADDR_I, w_mode, ADDR_I, b_mode), new InstructionDecoder("leave"),
			new RetDecoder("lret", ADDR_I, w_mode), new RetDecoder("lret"), new InstructionDecoder("int3"),
			new InstructionDecoder("int", ADDR_I, b_mode), new InstructionDecoder("into"), new RetDecoder("iret"),
			/* d0 */
			new GRPDecoder(null, 5), new GRPDecoder(null, 6), new GRPDecoder(null, 7), new GRPDecoder(null, 8),
			new InstructionDecoder("aam", ADDR_I, b_mode), new InstructionDecoder("aad", ADDR_I, b_mode), null,
			new InstructionDecoder("xlat"),
			/* d8 */
			new FloatDecoder(), new FloatDecoder(), new FloatDecoder(), new FloatDecoder(), new FloatDecoder(),
			new FloatDecoder(), new FloatDecoder(), new FloatDecoder(),
			/* e0 */
			new ConditionalJmpDecoder("loopne", ADDR_J, b_mode), new ConditionalJmpDecoder("loope", ADDR_J, b_mode),
			new ConditionalJmpDecoder("loop", ADDR_J, b_mode), new ConditionalJmpDecoder("jCcxz", ADDR_J, b_mode),
			new InstructionDecoder("inb", ADDR_REG, AL, ADDR_I, b_mode),
			new InstructionDecoder("inS", ADDR_REG, EAX, ADDR_I, b_mode),
			new InstructionDecoder("outb", ADDR_I, b_mode, ADDR_REG, AL),
			new InstructionDecoder("outS", ADDR_I, b_mode, ADDR_REG, EAX),
			/* e8 */
			new CallDecoder("call", ADDR_J, v_mode), new JmpDecoder("jmp", ADDR_J, v_mode),
			new JmpDecoder("ljmp", ADDR_DIR, p_mode), new JmpDecoder("jmp", ADDR_J, b_mode),
			new InstructionDecoder("inb", ADDR_REG, AL, INDIR_REG, DX),
			new InstructionDecoder("inS", ADDR_REG, EAX, INDIR_REG, DX),
			new InstructionDecoder("outb", INDIR_REG, DX, ADDR_REG, AL),
			new InstructionDecoder("outS", INDIR_REG, DX, ADDR_REG, EAX),
			/* f0 */
			new InstructionDecoder("lock"), /* lock prefix */
			null, new InstructionDecoder("repne"), /* repne */
			new InstructionDecoder("rep"), /* repz */
			new InstructionDecoder("hlt"), new InstructionDecoder("cmc"), new GRPDecoder(null, 9),
			new GRPDecoder(null, 10),
			/* f8 */
			new InstructionDecoder("clc"), new InstructionDecoder("stc"), new InstructionDecoder("cli"),
			new InstructionDecoder("sti"), new InstructionDecoder("cld"), new InstructionDecoder("std"),
			new GRPDecoder(null, 11), new GRPDecoder(null, 12) };

	// APPENDIX A - Table A-3. Two-byte Opcode Map
	private static final InstructionDecoder twoByteTable[] = {
			/* 00 */
			new GRPDecoder(null, 13),
			new GRPDecoder(null, 14),
			new InstructionDecoder("larS", ADDR_G, v_mode, ADDR_E, w_mode),
			new InstructionDecoder("lslS", ADDR_G, v_mode, ADDR_E, w_mode),
			null,
			null,
			new InstructionDecoder("clts"),
			null,
			/* 08 */
			new InstructionDecoder("invd"),
			new InstructionDecoder("wbinvd"),
			null,
			null,
			null,
			null,
			null,
			null,
			/* 10 */// SSE
			new SSEMoveDecoder("movups", ADDR_V, ps_mode, ADDR_W, ps_mode),
			new SSEMoveDecoder("movups", ADDR_W, ps_mode, ADDR_V, ps_mode),
			new SSEMoveDecoder("movlps", ADDR_W, q_mode, ADDR_V, q_mode),
			new SSEMoveDecoder("movlps", ADDR_V, q_mode, ADDR_W, q_mode),
			new SSEInstructionDecoder("unpcklps", ADDR_V, ps_mode, ADDR_W, q_mode),
			new SSEInstructionDecoder("unpckhps", ADDR_V, ps_mode, ADDR_W, q_mode),
			new SSEMoveDecoder("movhps", ADDR_V, q_mode, ADDR_W, q_mode),
			new SSEMoveDecoder("movhps", ADDR_W, q_mode, ADDR_V, q_mode),
			/* 18 */
			new GRPDecoder(null, 21), null, null, null, null, null, null, null,
			/* 20 */
			/* these are all backward in appendix A of the intel book */
			new MoveDecoder("movl", ADDR_RMR, d_mode, ADDR_C, d_mode),
			new MoveDecoder("movl", ADDR_R, d_mode, ADDR_D, d_mode),
			new MoveDecoder("movl", ADDR_C, d_mode, ADDR_RMR, d_mode),
			new MoveDecoder("movl", ADDR_D, d_mode, ADDR_R, d_mode),
			new MoveDecoder("movl", ADDR_R, d_mode, ADDR_T, d_mode), null,
			new MoveDecoder("movl", ADDR_T, d_mode, ADDR_R, d_mode), null,
			/* 28 */
			new SSEMoveDecoder("movaps", ADDR_V, ps_mode, ADDR_W, ps_mode),
			new SSEMoveDecoder("movaps", ADDR_W, ps_mode, ADDR_V, ps_mode),
			new SSEInstructionDecoder("cvtpi2ps", ADDR_V, ps_mode, ADDR_Q, q_mode),
			new SSEMoveDecoder("movntps", ADDR_W, ps_mode, ADDR_V, ps_mode),
			new SSEInstructionDecoder("cvttps2pi", ADDR_Q, q_mode, ADDR_W, ps_mode),
			new SSEInstructionDecoder("cvtps2pi", ADDR_Q, q_mode, ADDR_W, ps_mode),
			new SSEInstructionDecoder("ucomiss", ADDR_V, ss_mode, ADDR_W, ss_mode),
			new SSEInstructionDecoder("comiss", ADDR_V, ps_mode, ADDR_W, ps_mode),
			/* 30 */
			new SSEInstructionDecoder("wrmsr"), new SSEInstructionDecoder("rdtsc"), new SSEInstructionDecoder("rdmsr"),
			new SSEInstructionDecoder("rdpmc"), new SSEInstructionDecoder("sysenter"),
			new SSEInstructionDecoder("sysexit"), null, null,
			/* 38 */
			null, null, null, null, new SSEMoveDecoder("movnti", ADDR_G, v_mode, ADDR_E, v_mode), null, null, null,
			/* 40 */
			new MoveDecoder("cmovoS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovnoS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovbS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovaeS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmoveS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovneS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovbeS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovaS", ADDR_G, v_mode, ADDR_E, v_mode),
			/* 48 */
			new MoveDecoder("cmovsS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovnsS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovpS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovnpS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovlS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovgeS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovleS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("cmovgS", ADDR_G, v_mode, ADDR_E, v_mode),
			/* 50 */
			new SSEMoveDecoder("movmskps", ADDR_E, d_mode, ADDR_V, ps_mode),
			new SSEInstructionDecoder("sqrtps", ADDR_V, ps_mode, ADDR_W, ps_mode),
			new SSEInstructionDecoder("rsqrtps", ADDR_V, ps_mode, ADDR_W, ps_mode),
			new SSEInstructionDecoder("rcpps", ADDR_V, ps_mode, ADDR_W, ps_mode),
			new SSEArithmeticDecoder("andps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.AND),
			new SSEArithmeticDecoder("andnps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.AND),
			new SSEArithmeticDecoder("orps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.OR),
			new SSEArithmeticDecoder("xorps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.XOR),
			/* 58 */
			new SSEArithmeticDecoder("addps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.ADD),
			new SSEArithmeticDecoder("mulps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.SMUL),
			new SSEInstructionDecoder("cvtps2pd", ADDR_V, pd_mode, ADDR_W, ps_mode),
			new SSEInstructionDecoder("cvtdq2ps", ADDR_V, ps_mode, ADDR_W, dq_mode),
			new SSEArithmeticDecoder("subps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.SUB),
			new SSEInstructionDecoder("minps", ADDR_V, ps_mode, ADDR_W, ps_mode),
			new SSEArithmeticDecoder("divps", ADDR_V, ps_mode, ADDR_W, ps_mode, Operation.SDIV),
			new SSEInstructionDecoder("maxps", ADDR_V, ps_mode, ADDR_W, ps_mode),
			/* 60 */
			new SSEInstructionDecoder("punpcklbw", ADDR_P, q_mode, ADDR_Q, d_mode),
			new SSEInstructionDecoder("punpcklwd", ADDR_P, q_mode, ADDR_Q, d_mode),
			new SSEInstructionDecoder("punpckldq", ADDR_P, q_mode, ADDR_Q, d_mode),
			new SSEInstructionDecoder("packsswb", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("pcmpgtb", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("pcmpgtw", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("pcmpgtd", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("packuswb", ADDR_P, q_mode, ADDR_Q, q_mode),
			/* 68 */
			new SSEInstructionDecoder("punpckhbw", ADDR_P, q_mode, ADDR_Q, d_mode),
			new SSEInstructionDecoder("punpckhwd", ADDR_P, q_mode, ADDR_Q, d_mode),
			new SSEInstructionDecoder("punpckhdq", ADDR_P, q_mode, ADDR_Q, d_mode),
			new SSEInstructionDecoder("packssdw", ADDR_P, q_mode, ADDR_Q, d_mode), null, null,
			new SSEMoveDecoder("movd", ADDR_P, d_mode, ADDR_E, d_mode),
			new SSEMoveDecoder("movq", ADDR_P, q_mode, ADDR_E, q_mode),
			/* 70 */
			new SSEInstructionDecoder("pshufw", ADDR_P, q_mode, ADDR_Q, q_mode, ADDR_I, b_mode),
			new GRPDecoder(null, 17), new GRPDecoder(null, 18), new GRPDecoder(null, 19),
			new SSEInstructionDecoder("pcmpeqb", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("pcmpeqw", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("pcmpeqd", ADDR_P, q_mode, ADDR_Q, q_mode), new SSEInstructionDecoder("emms"),
			/* 78 */
			null, null, null, null, null, null, new SSEMoveDecoder("movd", ADDR_E, d_mode, ADDR_P, d_mode),
			new SSEMoveDecoder("movq", ADDR_Q, q_mode, ADDR_P, q_mode),
			/* 80 */
			new ConditionalJmpDecoder("jo", ADDR_J, v_mode), new ConditionalJmpDecoder("jno", ADDR_J, v_mode),
			new ConditionalJmpDecoder("jb", ADDR_J, v_mode), new ConditionalJmpDecoder("jae", ADDR_J, v_mode),
			new ConditionalJmpDecoder("je", ADDR_J, v_mode), new ConditionalJmpDecoder("jne", ADDR_J, v_mode),
			new ConditionalJmpDecoder("jbe", ADDR_J, v_mode), new ConditionalJmpDecoder("ja", ADDR_J, v_mode),
			/* 88 */
			new ConditionalJmpDecoder("js", ADDR_J, v_mode), new ConditionalJmpDecoder("jns", ADDR_J, v_mode),
			new ConditionalJmpDecoder("jp", ADDR_J, v_mode), new ConditionalJmpDecoder("jnp", ADDR_J, v_mode),
			new ConditionalJmpDecoder("jl", ADDR_J, v_mode), new ConditionalJmpDecoder("jge", ADDR_J, v_mode),
			new ConditionalJmpDecoder("jle", ADDR_J, v_mode), new ConditionalJmpDecoder("jg", ADDR_J, v_mode),
			/* 90 */
			new InstructionDecoder("seto", ADDR_E, b_mode), new InstructionDecoder("setno", ADDR_E, b_mode),
			new InstructionDecoder("setb", ADDR_E, b_mode), new InstructionDecoder("setae", ADDR_E, b_mode),
			new InstructionDecoder("sete", ADDR_E, b_mode), new InstructionDecoder("setne", ADDR_E, b_mode),
			new InstructionDecoder("setbe", ADDR_E, b_mode), new InstructionDecoder("seta", ADDR_E, b_mode),
			/* 98 */
			new InstructionDecoder("sets", ADDR_E, b_mode), new InstructionDecoder("setns", ADDR_E, b_mode),
			new InstructionDecoder("setp", ADDR_E, b_mode), new InstructionDecoder("setnp", ADDR_E, b_mode),
			new InstructionDecoder("setl", ADDR_E, b_mode), new InstructionDecoder("setge", ADDR_E, b_mode),
			new InstructionDecoder("setle", ADDR_E, b_mode), new InstructionDecoder("setg", ADDR_E, b_mode),
			/* a0 */
			new InstructionDecoder("pushl", ADDR_REG, FS), new InstructionDecoder("popl", ADDR_REG, FS),
			new InstructionDecoder("cpuid"), new InstructionDecoder("btS", ADDR_E, v_mode, ADDR_G, v_mode),
			new InstructionDecoder("shldS", ADDR_E, v_mode, ADDR_G, v_mode, ADDR_I, b_mode),
			new InstructionDecoder("shldS", ADDR_E, v_mode, ADDR_G, v_mode, ADDR_REG, CL), null, null,
			/* a8 */
			new InstructionDecoder("pushl", ADDR_REG, GS), new InstructionDecoder("popl", ADDR_REG, GS),
			new SSEInstructionDecoder("rsm"), new InstructionDecoder("btsS", ADDR_E, v_mode, ADDR_G, v_mode),
			new InstructionDecoder("shrdS", ADDR_E, v_mode, ADDR_G, v_mode, ADDR_I, b_mode),
			new InstructionDecoder("shrdS", ADDR_E, v_mode, ADDR_G, v_mode, ADDR_REG, CL), new GRPDecoder(null, 20),
			new ArithmeticDecoder("imulS", ADDR_G, v_mode, ADDR_E, v_mode, Operation.SMUL),
			/* b0 */
			new InstructionDecoder("cmpxchgb", ADDR_E, b_mode, ADDR_G, b_mode),
			new InstructionDecoder("cmpxchgS", ADDR_E, v_mode, ADDR_G, v_mode),
			new InstructionDecoder("lssS", ADDR_G, v_mode, ADDR_M, p_mode),
			new InstructionDecoder("btrS", ADDR_E, v_mode, ADDR_G, v_mode),
			new InstructionDecoder("lfsS", ADDR_G, v_mode, ADDR_M, p_mode),
			new InstructionDecoder("lgsS", ADDR_G, v_mode, ADDR_M, p_mode),
			new MoveDecoder("movzbS", ADDR_G, v_mode, ADDR_E, b_mode),
			new MoveDecoder("movzwS", ADDR_G, v_mode, ADDR_E, w_mode),
			/* b8 */
			null, null, new GRPDecoder(null, 15), new InstructionDecoder("btcS", ADDR_E, v_mode, ADDR_G, v_mode),
			new InstructionDecoder("bsfS", ADDR_G, v_mode, ADDR_E, v_mode),
			new InstructionDecoder("bsrS", ADDR_G, v_mode, ADDR_E, v_mode),
			new MoveDecoder("movsbS", ADDR_G, v_mode, ADDR_E, b_mode),
			new MoveDecoder("movswS", ADDR_G, v_mode, ADDR_E, w_mode),
			/* c0 */
			new ArithmeticDecoder("xaddb", ADDR_E, b_mode, ADDR_G, b_mode, Operation.ADD),
			new ArithmeticDecoder("xaddS", ADDR_E, v_mode, ADDR_G, v_mode, Operation.ADD),
			new SSEInstructionDecoder("cmpps", ADDR_V, ps_mode, ADDR_W, ps_mode, ADDR_I, b_mode),
			new SSEMoveDecoder("movnti", ADDR_E, d_mode, ADDR_G, d_mode),
			new SSEInstructionDecoder("pinsrw", ADDR_P, q_mode, ADDR_E, d_mode, ADDR_I, b_mode),
			new SSEInstructionDecoder("pextrw", ADDR_G, d_mode, ADDR_P, q_mode, ADDR_I, b_mode),
			new SSEInstructionDecoder("shufps", ADDR_V, ps_mode, ADDR_W, ps_mode, ADDR_I, b_mode),
			new GRPDecoder(null, 16),
			/* c8 */
			new InstructionDecoder("bswap", ADDR_REG, EAX), new InstructionDecoder("bswap", ADDR_REG, ECX),
			new InstructionDecoder("bswap", ADDR_REG, EDX), new InstructionDecoder("bswap", ADDR_REG, EBX),
			new InstructionDecoder("bswap", ADDR_REG, ESP), new InstructionDecoder("bswap", ADDR_REG, EBP),
			new InstructionDecoder("bswap", ADDR_REG, ESI), new InstructionDecoder("bswap", ADDR_REG, EDI),
			/* d0 */
			null, new SSEArithmeticDecoder("psrlw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SRL),
			new SSEArithmeticDecoder("psrld", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SRL),
			new SSEArithmeticDecoder("psrlq", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SRL),
			new SSEArithmeticDecoder("paddq", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEArithmeticDecoder("pmullw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SMUL), null,
			new SSEMoveDecoder("pmovmskb", ADDR_G, d_mode, ADDR_P, q_mode),
			/* d8 */
			new SSEArithmeticDecoder("psubusb", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubusw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEInstructionDecoder("pminub", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEArithmeticDecoder("pand", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.AND),
			new SSEArithmeticDecoder("paddusb", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddusw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEInstructionDecoder("pmaxub", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEArithmeticDecoder("pandn", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.AND),
			/* e0 */
			new SSEInstructionDecoder("pavgb", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("psraw", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("psrad", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEInstructionDecoder("pavgw", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEArithmeticDecoder("pmulhuw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.UMUL),
			new SSEArithmeticDecoder("pmulhw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SMUL), null,
			new SSEMoveDecoder("movntq", ADDR_W, q_mode, ADDR_V, q_mode),
			/* e8 */
			new SSEArithmeticDecoder("psubsb", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubsw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEInstructionDecoder("pminsw", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEArithmeticDecoder("por", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.OR),
			new SSEArithmeticDecoder("paddsb", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddsw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEInstructionDecoder("pmaxsw", ADDR_P, q_mode, ADDR_Q, q_mode),
			new SSEArithmeticDecoder("pxor", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.XOR),
			/* f0 */
			null, new SSEArithmeticDecoder("psllw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SLL),
			new SSEArithmeticDecoder("pslld", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SLL),
			new SSEArithmeticDecoder("psllq", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SLL),
			new SSEArithmeticDecoder("pmuludq", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.UMUL),
			new SSEArithmeticDecoder("pmaddwd", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEArithmeticDecoder("psadbw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEMoveDecoder("maskmoveq", ADDR_P, pi_mode, ADDR_Q, pi_mode),
			/* f8 */
			new SSEArithmeticDecoder("psubb", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubd", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubq", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.SUB),
			new SSEArithmeticDecoder("paddb", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddw", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddd", ADDR_P, q_mode, ADDR_Q, q_mode, Operation.ADD), null };

	private static final InstructionDecoder twoBytePrefixF2Table[] = {
	/* 00 */
	null, null, null, null, null, null, null, null,
	/* 08 */
	null, null, null, null, null, null, null, null,
	/* 10 */
	new SSEMoveDecoder("movsd", ADDR_V, sd_mode, ADDR_W, sd_mode),
			new SSEMoveDecoder("movsd", ADDR_V, sd_mode, ADDR_W, sd_mode), null, null, null, null, null, null,
			/* 18 */
			null, null, null, null, null, null, null, null,
			/* 20 */
			null, null, null, null, null, null, null, null,
			/* 28 */
			null, null, new SSEInstructionDecoder("cvtsi2sd", ADDR_V, sd_mode, ADDR_E, d_mode), null,
			new SSEInstructionDecoder("cvttsd2si", ADDR_G, d_mode, ADDR_W, sd_mode),
			new SSEInstructionDecoder("cvtsd2si", ADDR_G, d_mode, ADDR_W, sd_mode), null, null,
			/* 30 */
			null, null, null, null, null, null, null, null,
			/* 38 */
			null, null, null, null, null, null, null, null,
			/* 40 */
			null, null, null, null, null, null, null, null,
			/* 48 */
			null, null, null, null, null, null, null, null,
			/* 50 */
			null, new SSEInstructionDecoder("sqrtsd", ADDR_V, sd_mode, ADDR_W, sd_mode), null, null, null, null, null,
			null,
			/* 58 */
			new SSEArithmeticDecoder("addsd", ADDR_V, sd_mode, ADDR_W, sd_mode, Operation.ADD),
			new SSEArithmeticDecoder("mulsd", ADDR_V, sd_mode, ADDR_W, sd_mode, Operation.SMUL),
			new SSEInstructionDecoder("cvtsd2ss", ADDR_V, sd_mode, ADDR_W, sd_mode), null,
			new SSEArithmeticDecoder("subsd", ADDR_V, sd_mode, ADDR_W, sd_mode, Operation.SUB),
			new SSEInstructionDecoder("minsd", ADDR_V, sd_mode, ADDR_W, sd_mode),
			new SSEArithmeticDecoder("divsd", ADDR_V, sd_mode, ADDR_W, sd_mode, Operation.SDIV),
			new SSEInstructionDecoder("maxsd", ADDR_V, sd_mode, ADDR_W, sd_mode),
			/* 60 */
			null, null, null, null, null, null, null, null,
			/* 68 */
			null, null, null, null, null, null, null, null,
			/* 70 */
			new SSEInstructionDecoder("pshuflw", ADDR_V, dq_mode, ADDR_W, dq_mode, ADDR_I, b_mode), null, null, null,
			null, null, null, null,
			/* 78 */
			null, null, null, null, null, null, null, null,
			/* 80 */
			null, null, null, null, null, null, null, null,
			/* 88 */
			null, null, null, null, null, null, null, null,
			/* 90 */
			null, null, null, null, null, null, null, null,
			/* 98 */
			null, null, null, null, null, null, null, null,
			/* a0 */
			null, null, null, null, null, null, null, null,
			/* a8 */
			null, null, null, null, null, null, null, null,
			/* b0 */
			null, null, null, null, null, null, null, null,
			/* b8 */
			null, null, null, null, null, null, null, null,
			/* c0 */
			null, null, new SSEInstructionDecoder("cmpsd", ADDR_V, sd_mode, ADDR_W, sd_mode, ADDR_I, b_mode), null,
			null, null, null, null,
			/* c8 */
			null, null, null, null, null, null, null, null,
			/* d0 */
			null, null, null, null, null, null, new SSEMoveDecoder("movdq2q", ADDR_P, q_mode, ADDR_W, q_mode), null,
			/* d8 */
			null, null, null, null, null, null, null, null,
			/* e0 */
			null, null, null, null, null, null,
			new SSEInstructionDecoder("cvtpd2dq", ADDR_V, dq_mode, ADDR_W, pd_mode), null,
			/* e8 */
			null, null, null, null, null, null, null, null,
			/* f0 */
			null, null, null, null, null, null, null, null,
			/* f8 */
			null, null, null, null, null, null, null, null };

	private static final InstructionDecoder twoBytePrefixF3Table[] = {
	/* 00 */
	null, null, null, null, null, null, null, null,
	/* 08 */
	null, null, null, null, null, null, null, null,
	/* 10 */
	new SSEMoveDecoder("movss", ADDR_V, ss_mode, ADDR_W, ss_mode),
			new SSEMoveDecoder("movss", ADDR_W, ss_mode, ADDR_V, ss_mode), null, null, null, null, null, null,
			/* 18 */
			null, null, null, null, null, null, null, null,
			/* 20 */
			null, null, null, null, null, null, null, null,
			/* 28 */
			null, null, new SSEInstructionDecoder("cvtsi2ss", ADDR_V, ss_mode, ADDR_E, d_mode), null,
			new SSEInstructionDecoder("cvttss2si", ADDR_G, d_mode, ADDR_W, ss_mode),
			new SSEInstructionDecoder("cvtss2si", ADDR_G, d_mode, ADDR_W, ss_mode), null, null,
			/* 30 */
			null, null, null, null, null, null, null, null,
			/* 38 */
			null, null, null, null, null, null, null, null,
			/* 40 */
			null, null, null, null, null, null, null, null,
			/* 48 */
			null, null, null, null, null, null, null, null,
			/* 50 */
			null, new SSEInstructionDecoder("sqrtss", ADDR_V, ss_mode, ADDR_W, ss_mode),
			new SSEInstructionDecoder("rsqrtss", ADDR_V, ss_mode, ADDR_W, ss_mode),
			new SSEInstructionDecoder("rcpss", ADDR_V, ss_mode, ADDR_W, ss_mode), null, null, null, null,
			/* 58 */
			new SSEArithmeticDecoder("addss", ADDR_V, ss_mode, ADDR_W, ss_mode, Operation.ADD),
			new SSEArithmeticDecoder("mulss", ADDR_V, ss_mode, ADDR_W, ss_mode, Operation.SMUL),
			new SSEInstructionDecoder("cvtss2sd", ADDR_V, ss_mode, ADDR_W, ss_mode),
			new SSEInstructionDecoder("cvttps2dq", ADDR_V, dq_mode, ADDR_W, ps_mode),
			new SSEArithmeticDecoder("subss", ADDR_V, ss_mode, ADDR_W, ss_mode, Operation.SUB),
			new SSEInstructionDecoder("minss", ADDR_V, ss_mode, ADDR_W, ss_mode),
			new SSEArithmeticDecoder("divss", ADDR_V, ss_mode, ADDR_W, ss_mode, Operation.SDIV),
			new SSEInstructionDecoder("maxss", ADDR_V, ss_mode, ADDR_W, ss_mode),
			/* 60 */
			null, null, null, null, null, null, null, null,
			/* 68 */
			null, null, null, null, null, null, null, new SSEMoveDecoder("movdqu", ADDR_V, dq_mode, ADDR_W, dq_mode),
			/* 70 */
			new SSEInstructionDecoder("pshufhw", ADDR_V, dq_mode, ADDR_W, dq_mode, ADDR_I, b_mode), null, null, null,
			null, null, null, null,
			/* 78 */
			null, null, null, null, null, null, new SSEMoveDecoder("movq", ADDR_V, q_mode, ADDR_W, q_mode),
			new SSEMoveDecoder("movdqu", ADDR_W, dq_mode, ADDR_V, dq_mode),
			/* 80 */
			null, null, null, null, null, null, null, null,
			/* 88 */
			null, null, null, null, null, null, null, null,
			/* 90 */
			null, null, null, null, null, null, null, null,
			/* 98 */
			null, null, null, null, null, null, null, null,
			/* a0 */
			null, null, null, null, null, null, null, null,
			/* a8 */
			null, null, null, null, null, null, null, null,
			/* b0 */
			null, null, null, null, null, null, null, null,
			/* b8 */
			null, null, null, null, null, null, null, null,
			/* c0 */
			null, null, new SSEInstructionDecoder("cmpss", ADDR_V, ss_mode, ADDR_W, ss_mode, ADDR_I, b_mode), null,
			null, null, null, null,
			/* c8 */
			null, null, null, null, null, null, null, null,
			/* d0 */
			null, null, null, null, null, null, new SSEMoveDecoder("movq2dq", ADDR_V, dq_mode, ADDR_Q, q_mode), null,
			/* d8 */
			null, null, null, null, null, null, null, null,
			/* e0 */
			null, null, null, null, null, null,
			new SSEInstructionDecoder("cvtdq2pd", ADDR_V, pd_mode, ADDR_W, dq_mode), null,
			/* e8 */
			null, null, null, null, null, null, null, null,
			/* f0 */
			null, null, null, null, null, null, null, null,
			/* f8 */
			null, null, null, null, null, null, null, null };

	private static final InstructionDecoder twoBytePrefix66Table[] = {
	/* 00 */
	null, null, null, null, null, null, null, null,
	/* 08 */
	null, null, null, null, null, null, null, null,
	/* 10 */
	new SSEMoveDecoder("movupd", ADDR_V, pd_mode, ADDR_W, pd_mode),
			new SSEMoveDecoder("movupd", ADDR_W, pd_mode, ADDR_V, pd_mode),
			new SSEMoveDecoder("movlpd", ADDR_W, q_mode, ADDR_V, q_mode),
			new SSEMoveDecoder("movlpd", ADDR_V, q_mode, ADDR_W, q_mode),
			new SSEInstructionDecoder("unpcklpd", ADDR_V, pd_mode, ADDR_W, q_mode),
			new SSEInstructionDecoder("unpckhpd", ADDR_V, pd_mode, ADDR_W, q_mode),
			new SSEMoveDecoder("movhpd", ADDR_V, q_mode, ADDR_W, q_mode),
			new SSEMoveDecoder("movhpd", ADDR_W, q_mode, ADDR_V, q_mode),
			/* 18 */
			null, null, null, null, null, null, null, null,
			/* 20 */
			null, null, null, null, null, null, null, null,
			/* 28 */
			new SSEMoveDecoder("movapd", ADDR_V, pd_mode, ADDR_W, pd_mode),
			new SSEMoveDecoder("movapd", ADDR_W, pd_mode, ADDR_V, pd_mode),
			new SSEInstructionDecoder("cvtpi2pd", ADDR_V, pd_mode, ADDR_Q, dq_mode),
			new SSEMoveDecoder("movntpd", ADDR_W, pd_mode, ADDR_V, pd_mode),
			new SSEInstructionDecoder("cvttpd2pi", ADDR_Q, dq_mode, ADDR_W, pd_mode),
			new SSEInstructionDecoder("cvtpd2pi", ADDR_Q, dq_mode, ADDR_W, pd_mode),
			new SSEInstructionDecoder("ucomisd", ADDR_V, sd_mode, ADDR_W, sd_mode),
			new SSEInstructionDecoder("comisd", ADDR_V, sd_mode, ADDR_W, sd_mode),
			/* 30 */
			null, null, null, null, null, null, null, null,
			/* 38 */
			null, null, null, null, null, null, null, null,
			/* 40 */
			null, null, null, null, null, null, null, null,
			/* 48 */
			null, null, null, null, null, null, null, null,
			/* 50 */
			new SSEMoveDecoder("movmskpd", ADDR_E, d_mode, ADDR_V, pd_mode),
			new SSEInstructionDecoder("sqrtpd", ADDR_V, pd_mode, ADDR_W, pd_mode), null, null,
			new SSEArithmeticDecoder("andpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.AND),
			new SSEArithmeticDecoder("andnpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.AND),
			new SSEArithmeticDecoder("orpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.OR),
			new SSEArithmeticDecoder("xorpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.XOR),
			/* 58 */
			new SSEArithmeticDecoder("addpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.ADD),
			new SSEArithmeticDecoder("mulpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.SMUL),
			new SSEInstructionDecoder("cvtpd2ps", ADDR_V, ps_mode, ADDR_W, pd_mode),
			new SSEInstructionDecoder("cvtps2dq", ADDR_V, dq_mode, ADDR_W, ps_mode),
			new SSEArithmeticDecoder("subpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.SUB),
			new SSEInstructionDecoder("minpd", ADDR_V, pd_mode, ADDR_W, pd_mode),
			new SSEArithmeticDecoder("divpd", ADDR_V, pd_mode, ADDR_W, pd_mode, Operation.SDIV),
			new SSEInstructionDecoder("maxpd", ADDR_V, pd_mode, ADDR_W, pd_mode),
			/* 60 */
			new SSEInstructionDecoder("punpcklbw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("punpcklwd", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("punpckldq", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("packsswb", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("pcmpgtb", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("pcmpgtw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("pcmpgtd", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("packuswb", ADDR_V, dq_mode, ADDR_W, dq_mode),
			/* 68 */
			new SSEInstructionDecoder("punpckhbw", ADDR_P, dq_mode, ADDR_Q, dq_mode),
			new SSEInstructionDecoder("punpckhwd", ADDR_P, dq_mode, ADDR_Q, dq_mode),
			new SSEInstructionDecoder("punpckhdq", ADDR_P, dq_mode, ADDR_Q, dq_mode),
			new SSEInstructionDecoder("packssdw", ADDR_P, dq_mode, ADDR_Q, dq_mode),
			new SSEInstructionDecoder("punpcklqdq", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("punpckhqdq", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEMoveDecoder("movd", ADDR_V, dq_mode, ADDR_E, d_mode),
			new SSEMoveDecoder("movdqa", ADDR_V, dq_mode, ADDR_W, dq_mode),
			/* 70 */
			new SSEInstructionDecoder("pshufd", ADDR_V, dq_mode, ADDR_W, dq_mode, ADDR_I, b_mode),
			new GRPDecoder(null, 22), new GRPDecoder(null, 23), new GRPDecoder(null, 24),
			new SSEInstructionDecoder("pcmpeqb", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("pcmpeqw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("pcmpeqd", ADDR_V, dq_mode, ADDR_W, dq_mode), null,
			/* 78 */
			null, null, null, null, null, null, new SSEMoveDecoder("movd", ADDR_E, d_mode, ADDR_V, dq_mode),
			new SSEMoveDecoder("movdqa", ADDR_W, dq_mode, ADDR_V, dq_mode),
			/* 80 */
			null, null, null, null, null, null, null, null,
			/* 88 */
			null, null, null, null, null, null, null, null,
			/* 90 */
			null, null, null, null, null, null, null, null,
			/* 98 */
			null, null, null, null, null, null, null, null,
			/* a0 */
			null, null, null, null, null, null, null, null,
			/* a8 */
			null, null, null, null, null, null, null, null,
			/* b0 */
			null, null, null, null, null, null, null, null,
			/* b8 */
			null, null, null, null, null, null, null, null,
			/* c0 */
			null, null, new SSEInstructionDecoder("cmppd", ADDR_V, pd_mode, ADDR_W, pd_mode, ADDR_I, b_mode), null,
			new SSEInstructionDecoder("pinsrw", ADDR_V, dq_mode, ADDR_E, d_mode, ADDR_I, b_mode),
			new SSEInstructionDecoder("pextrw", ADDR_G, d_mode, ADDR_V, dq_mode, ADDR_I, b_mode),
			new SSEInstructionDecoder("shufpd", ADDR_V, pd_mode, ADDR_W, pd_mode, ADDR_I, b_mode), null,
			/* c8 */
			null, null, null, null, null, null, null, null,
			/* d0 */
			null, new SSEArithmeticDecoder("psrlw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SRL),
			new SSEArithmeticDecoder("psrld", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SRL),
			new SSEArithmeticDecoder("psrlq", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SRL),
			new SSEArithmeticDecoder("paddq", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEArithmeticDecoder("pmullw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SMUL),
			new SSEMoveDecoder("movq", ADDR_W, q_mode, ADDR_V, q_mode),
			new SSEMoveDecoder("pmovmskb", ADDR_G, d_mode, ADDR_V, dq_mode),
			/* d8 */
			new SSEArithmeticDecoder("psubusb", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubusw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEInstructionDecoder("pminub", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEArithmeticDecoder("pand", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.AND),
			new SSEArithmeticDecoder("paddusb", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddusw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEInstructionDecoder("pmaxub", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEArithmeticDecoder("pandn", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.AND),
			/* e0 */
			new SSEInstructionDecoder("pavgb", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("psraw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("psrad", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEInstructionDecoder("pavgw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEArithmeticDecoder("pmulhuw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.UMUL),
			new SSEArithmeticDecoder("pmulhw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SMUL),
			new SSEInstructionDecoder("cvttpd2dq", ADDR_V, dq_mode, ADDR_W, pd_mode),
			new SSEMoveDecoder("movntdq", ADDR_W, dq_mode, ADDR_V, dq_mode),
			/* e8 */
			new SSEArithmeticDecoder("psubusb", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubusw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEInstructionDecoder("pminsw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEArithmeticDecoder("por", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.OR),
			new SSEArithmeticDecoder("paddsb", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddsw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEInstructionDecoder("pmaxsw", ADDR_V, dq_mode, ADDR_W, dq_mode),
			new SSEArithmeticDecoder("pxor", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.XOR),
			/* f0 */
			null, new SSEArithmeticDecoder("psllw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SLL),
			new SSEArithmeticDecoder("pslld", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SLL),
			new SSEArithmeticDecoder("psllq", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SLL),
			new SSEArithmeticDecoder("pmuludq", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.UMUL),
			new SSEArithmeticDecoder("pmaddwd", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEArithmeticDecoder("psadbw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEMoveDecoder("maskmovdqu", ADDR_V, dq_mode, ADDR_W, dq_mode),
			/* f8 */
			new SSEArithmeticDecoder("psubb", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubd", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEArithmeticDecoder("psubq", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.SUB),
			new SSEArithmeticDecoder("paddb", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddw", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD),
			new SSEArithmeticDecoder("paddd", ADDR_V, dq_mode, ADDR_W, dq_mode, Operation.ADD), null };

	@Override
	public void setMemoryByteValue(int fp, long i) {
		// TODO Auto-generated method stub
		try {
			code.setBYTE(fp, i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Override
	public long getMemoryByteValue(int fp) {
		return code.getByteAt(fp);
	}

	@Override
	public void addMemoryByteValue(int fp, long i) {
		// TODO Auto-generated method stub
		try {
			code.addBYTE(fp, i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setMemoryDoubleWordValue(int fp1, long value) {
		// TODO Auto-generated method stub
		try {
			code.setDoubleWord(fp1, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addMemoryDoubleWordValue(int fp1, long value) {
		// TODO Auto-generated method stub
		try {
			code.addDoubleWord(fp1, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addMemoryWordValue(int fp1, long value) {
		// TODO Auto-generated method stub
		try {
			code.addWord(fp1, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setMemoryWordValue(int fp1, long value) {
		// TODO Auto-generated method stub
		try {
			code.setWord(fp1, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
