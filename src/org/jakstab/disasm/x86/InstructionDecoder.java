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

import java.io.IOException;

import org.jakstab.asm.DataType;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86AbsoluteAddress;
import org.jakstab.asm.x86.X86ControlRegisters;
import org.jakstab.asm.x86.X86FloatRegisters;
import org.jakstab.asm.x86.X86InstructionFactory;
import org.jakstab.asm.x86.X86MMXRegisters;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86Opcodes;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jakstab.asm.x86.X86Register;
import org.jakstab.asm.x86.X86Registers;
import org.jakstab.asm.x86.X86SegmentRegister;
import org.jakstab.asm.x86.X86SegmentRegisters;
import org.jakstab.asm.x86.X86XMMRegisters;
import org.jakstab.util.BinaryInputBuffer;
import org.jakstab.util.Logger;

public class InstructionDecoder implements /* imports */X86Opcodes {
	private final static Logger logger = Logger.getLogger(InstructionDecoder.class);

	/*
	 * The three read methods are now changed to call into BinaryInputBuffer to
	 * remove the dependency on byte[]. This could be much nicer, i.e.,
	 * integrate all the reading logic with BinaryInputBuffer, but it's probably
	 * not worth the effort. --JK, 8.11.2010
	 */
	public static int readByte(BinaryInputBuffer bytesArray, int index) {
		int ret = 0;
		if (index < bytesArray.getSize()) {
			ret = bytesArray.getByteAt(index);
			ret = ret & 0xff;
		} else {
			return 0;			
		}
		return ret;
	}

	public static int readInt16(BinaryInputBuffer bytesArray, int index) {
		int ret = 0;
		ret = readByte(bytesArray, index);
		ret |= readByte(bytesArray, index + 1) << 8;
		return ret;
	}

	public static int readInt32(BinaryInputBuffer bytesArray, int index) {

		int ret = 0;
		ret = readByte(bytesArray, index);
		ret |= readByte(bytesArray, index + 1) << 8;
		ret |= readByte(bytesArray, index + 2) << 16;
		ret |= readByte(bytesArray, index + 3) << 24;
		return ret;
	}

	// Fixed for every instance
	protected final int addrMode1;
	protected final int addrMode2;
	protected final int addrMode3;
	protected final String nameTemplate;
	protected final int operandType1;
	protected final int operandType2;
	protected final int operandType3;

	// Working variables, change with every decoded instruction
	private int mod;
	private int regOrOpcode;
	private int rm;
	protected int byteIndex;
	protected int instrStartIndex;
	protected String name;
	protected int prefixes;

	public InstructionDecoder(String name) {
		this(name, INVALID_ADDRMODE, INVALID_OPERANDTYPE);
	}

	public InstructionDecoder(String name, int addrMode1, int operandType1) {
		this(name, addrMode1, operandType1, INVALID_ADDRMODE, INVALID_OPERANDTYPE);
	}

	public InstructionDecoder(String name, int addrMode1, int operandType1, int addrMode2, int operandType2) {
		this(name, addrMode1, operandType1, addrMode2, operandType2, INVALID_ADDRMODE, INVALID_OPERANDTYPE);
	}

	public InstructionDecoder(String name, int addrMode1, int operandType1, int addrMode2, int operandType2,
			int addrMode3, int operandType3) {
		this.nameTemplate = name;
		this.operandType1 = operandType1;
		this.operandType2 = operandType2;
		this.operandType3 = operandType3;
		this.addrMode1 = addrMode1;
		this.addrMode2 = addrMode2;
		this.addrMode3 = addrMode3;
	}

	public Instruction decode(BinaryInputBuffer bytesArray, int index, int instrStartIndex, int segmentOverride,
			int prefixes, X86InstructionFactory factory) {
		this.byteIndex = index;
		this.instrStartIndex = instrStartIndex;
		this.prefixes = prefixes;
		boolean operandSize; // operand-size prefix
		boolean addrSize; // address-size prefix
		// segmentoverride is set to 1 in X86Disassembler. Correct for 32bit
		// mode.
		if (((prefixes & PREFIX_DATA) ^ segmentOverride) == 1) {
			operandSize = true; // set 32bit operand mode
		} else {
			operandSize = false;
		}
		if (((prefixes & PREFIX_ADR) ^ segmentOverride) == 1) {
			addrSize = true;
		} else {
			addrSize = false;
		}
		this.name = getCorrectOpcodeName(nameTemplate, prefixes, operandSize, addrSize);

		// Fetch the mod/reg/rm byte only if it is present.
		if (isModRMPresent(addrMode1) || isModRMPresent(addrMode2) || isModRMPresent(addrMode3)) {

			int ModRM = readByte(bytesArray, byteIndex);
			byteIndex++;
			mod = (ModRM >> 6) & 3;
			regOrOpcode = (ModRM >> 3) & 7;
			rm = ModRM & 7;
		}
		// Call instruction specific code
		return decodeInstruction(bytesArray, operandSize, addrSize, factory);
	}

	public int getCurrentIndex() {
		return byteIndex;
	}

	/**
	 * This is the instruction specific decoder. Gets overridden by the
	 * subclasses of InstructionDecoder. This is the fallback implementation for
	 * X86Instruction Objects. Note that 8bit operands are encoded implicitly in
	 * the opcode.
	 * 
	 * @param bytesArray
	 *            The array of bytes representing the binary.
	 * @param operandSize
	 *            True for 32bit, false for 16bit operands.
	 * @param addrSize
	 *            True for 32bit addresses, false for 16bit.
	 * @param factory
	 *            The instruction factory to use.
	 * @return A new object representing the instruction at the current
	 *         byteIndex.
	 */
	protected Instruction decodeInstruction(BinaryInputBuffer bytesArray, boolean operandSize, boolean addrSize,
			X86InstructionFactory factory) {
		Operand op1 = getOperand1(bytesArray, operandSize, addrSize);
		Operand op2 = getOperand2(bytesArray, operandSize, addrSize);
		Operand op3 = getOperand3(bytesArray, operandSize, addrSize);
		int size = byteIndex - instrStartIndex;
		return factory.newGeneralInstruction(name, op1, op2, op3, size, prefixes);
	}

	protected Operand getOperand1(BinaryInputBuffer bytesArray, boolean operandSize, boolean addrSize) {
		if ((addrMode1 != INVALID_ADDRMODE) && (operandType1 != INVALID_OPERANDTYPE)) {
			return getOperand(bytesArray, addrMode1, operandType1, operandSize, addrSize);
		} else {
			return null;
		}
	}

	protected Operand getOperand2(BinaryInputBuffer bytesArray, boolean operandSize, boolean addrSize) {
		if ((addrMode2 != INVALID_ADDRMODE) && (operandType2 != INVALID_OPERANDTYPE)) {
			return getOperand(bytesArray, addrMode2, operandType2, operandSize, addrSize);
		} else {
			return null;
		}
	}

	protected Operand getOperand3(BinaryInputBuffer bytesArray, boolean operandSize, boolean addrSize) {
		if ((addrMode3 != INVALID_ADDRMODE) && (operandType3 != INVALID_OPERANDTYPE)) {
			return getOperand(bytesArray, addrMode3, operandType3, operandSize, addrSize);
		} else {
			return null;
		}
	}

	/**
	 * Instantiates the name template for the current instruction by replacing
	 * capital letters with their correct counterparts depending on various
	 * prefixes.
	 * 
	 * @param oldName
	 *            the instruction template string
	 * @param prefixes
	 *            instruction prefixes
	 * @param operandSize
	 *            true for 32bit operands, false for 16bit.
	 * @param addrSize
	 *            true for 32bit addressing, false for 16bit.
	 * @return a new string with the correct AT&T name.
	 */
	private String getCorrectOpcodeName(String oldName, int prefixes, boolean operandSize, boolean addrSize) {
		StringBuffer newName = new StringBuffer(oldName.length());
		int index = 0;
		for (index = 0; index < oldName.length(); index++) {
			switch (oldName.charAt(index)) {
			case 'C': /* For jcxz/jecxz */
				if (addrSize) {
					newName.append('e');
				}
				break;
			case 'N':
				if ((prefixes & PREFIX_FWAIT) == 0) {
					newName.append('n');
				}
				break;
			case 'S':
				/* operand size flag */
				if (operandSize == true) {
					newName.append('l');
				} else {
					newName.append('w');
				}
				break;
			default:
				newName.append(oldName.charAt(index));
			}
		}
		return newName.toString();
	}

	private DataType getDataType(int operandType, boolean operandSize) {

		switch (operandType) {
		case d_mode:
			return DataType.INT32;
		case v_mode:
			if (operandSize) {
				return DataType.INT32;
			} else {
				return DataType.INT16;
			}
		case w_mode:
			return DataType.INT16;
		case b_mode:
			return DataType.INT8;
		case q_mode:
			return DataType.INT64;
		case dq_mode:
			return DataType.INT128;
		case fs_mode:
		case ss_mode: // SSE: scalar single precision : 32 bit
			return DataType.FL_SINGLE;
		case fd_mode:
		case sd_mode: // SSE: scalar double precision : 64 bit
			return DataType.FL_DOUBLE;
		case fe_mode:
			return DataType.FL_EXT_DOUBLE;
		case fq_mode:
			return DataType.FL_QUAD;
		case ps_mode: // SSE: packed single precision : 128 bit
		case pd_mode: // SSE: packed double precision : 128 bit
			return DataType.FL_QUAD;
		default:
			// This should only be the case for SSA-instructions and maybe
			// segment-load instructions?
			logger.error("Unknown data type for operand type: " + operandType + "!");
			throw new RuntimeException();
			// return DataType.UNKNOWN;
		}
	}

	/**
	 * Get correct Operand object from address type and operand type
	 * 
	 * @param bytesArray
	 *            the code array
	 * @param addrMode
	 *            addressing mode constant
	 * @param operandType
	 *            operand type constant
	 * @param operandSize
	 *            true for 32bit, false for 16bit operands
	 * @param addrSize
	 *            true for 32bit, false for 16bit addresses
	 * @return a new operand object
	 */
	private Operand getOperand(BinaryInputBuffer bytesArray, int addrMode, int operandType, boolean operandSize,
			boolean addrSize) {
		Operand op = null;
		X86SegmentRegister segReg = getSegmentRegisterFromPrefix(prefixes);
		switch (addrMode) {
		case ADDR_E:
		case ADDR_W: // SSE: ModR/M byte specifies either 128 bit XMM register
						// or memory
		case ADDR_Q: // SSE: ModR/M byte specifies either 128 bit MMX register
						// or memory
			// X86SegmentRegister segReg =
			// getSegmentRegisterFromPrefix(prefixes);

			if (mod == 3) { // Register operand, no SIB follows
				if (addrMode == ADDR_E) {
					switch (operandType) {
					case b_mode:
						op = X86Registers.getRegister8(rm);
						break;
					case w_mode:
						op = X86Registers.getRegister16(rm);
						break;
					case v_mode:
						if (operandSize == true) {
							// present
							op = X86Registers.getRegister32(rm);
						} else {
							op = X86Registers.getRegister16(rm);
						}
						break;
					case p_mode:
						X86Register reg;
						if (operandSize == true) {
							// present
							reg = X86Registers.getRegister32(rm);
						} else {
							reg = X86Registers.getRegister16(rm);
						}

						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg, reg, null, 0);
						break;
					case d_mode:
						op = X86Registers.getRegister32(rm);
						break;
					default:
						break;
					}
				} else if (addrMode == ADDR_W) {
					op = X86XMMRegisters.getRegister(rm);
				} else if (addrMode == ADDR_Q) {
					op = X86MMXRegisters.getRegister(rm);
				}

			} else { // mod != 3
				// SIB follows for (rm==4), SIB gives scale, index and base in
				// this case
				// disp32 is present for (mod==0 && rm==5) || (mod==2)
				// disp8 is present for (mod==1)
				// for (rm!=4) base is register at rm.
				int scale = 0;
				int index = 0;
				int base = 0;
				long disp = 0;
				if (rm == 4) {
					int sib = readByte(bytesArray, byteIndex);
					byteIndex++;
					scale = (sib >> 6) & 3;
					index = (sib >> 3) & 7;
					base = sib & 7;
				}

				switch (mod) {
				case 0:
					switch (rm) {
					case 4:
						if (base == 5) {
							disp = readInt32(bytesArray, byteIndex);
							byteIndex += 4;
							if (index != 4) {
								op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg, null,
										X86Registers.getRegister32(index), disp, scale);
							} else {
								op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg, null, null,
										disp, scale);
							}
						} else {
							if (index != 4) {
								op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
										X86Registers.getRegister32(base), X86Registers.getRegister32(index), 0, scale);
							} else {
								op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
										X86Registers.getRegister32(base), null, 0, scale);
							}
						}
						break;
					case 5:
						disp = readInt32(bytesArray, byteIndex);
						byteIndex += 4;
						// Create an Address object only with displacement
						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg, null, null, disp);
						break;
					default:
						base = rm;
						// Create an Address object only with base
						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
								X86Registers.getRegister32(base), null, 0);
						break;
					}
					break;
				case 1:
					disp = (byte) readByte(bytesArray, byteIndex);
					byteIndex++;
					if (rm != 4) {
						base = rm;
						// Address with base and disp only
						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
								X86Registers.getRegister32(base), null, disp);
					} else {
						if (index != 4) {
							op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
									X86Registers.getRegister32(base), X86Registers.getRegister32(index), disp, scale);
						} else {
							op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
									X86Registers.getRegister32(base), null, disp, scale);
						}
					}
					break;
				case 2:
					disp = readInt32(bytesArray, byteIndex);
					byteIndex += 4;
					if (rm != 4) {
						base = rm;
						// Address with base and disp
						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
								X86Registers.getRegister32(base), null, disp);
					} else if (index != 4) {
						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
								X86Registers.getRegister32(base), X86Registers.getRegister32(index), disp, scale);
					} else {
						op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg,
								X86Registers.getRegister32(base), null, disp, scale);
					}
					break;
				}
			}
			break;

		case ADDR_I:
			switch (operandType) {
			case b_mode:
				op = new Immediate(new Byte((byte) readByte(bytesArray, byteIndex)), DataType.UINT8);
				byteIndex++;
				break;
			case w_mode:
				op = new Immediate(new Short((short) readInt16(bytesArray, byteIndex)), DataType.UINT16);
				byteIndex += 2;
				break;
			case v_mode:
				if (operandSize == true) { // Operand size prefix is present
					op = new Immediate(new Integer(readInt32(bytesArray, byteIndex)), DataType.UINT32);
					byteIndex += 4;
				} else {
					op = new Immediate(new Short((short) readInt16(bytesArray, byteIndex)), DataType.UINT16);
					byteIndex += 2;
				}
				break;
			default:
				break;
			}
			break;
		case ADDR_REG: // registers
			switch (operandType) {
			case EAX:
			case ECX:
			case EDX:
			case EBX:
			case ESP:
			case EBP:
			case ESI:
			case EDI:
				if (operandSize == true) {
					op = X86Registers.getRegister32(operandType - EAX);
				} else {
					op = X86Registers.getRegister16(operandType - EAX);
				}
				break;
			case AX:
			case CX:
			case DX:
			case BX:
			case SP:
			case BP:
			case SI:
			case DI:
				op = X86Registers.getRegister16(operandType - AX);
				break;
			case AL:
			case CL:
			case DL:
			case BL:
			case AH:
			case CH:
			case DH:
			case BH:
				op = X86Registers.getRegister8(operandType - AL);
				break;
			case ES: // ES, CS, SS, DS, FS, GS
			case CS:
			case SS:
			case DS:
			case FS:
			case GS:
				op = X86SegmentRegisters.getSegmentRegister(operandType - ES);
				break;
			}
			break;
		case ADDR_DIR: // segment and offset
			long segment = 0;
			long offset = 0;
			switch (operandType) {
			case p_mode:
				if (addrSize == true) {
					offset = readInt32(bytesArray, byteIndex);
					byteIndex += 4;
					segment = readInt16(bytesArray, byteIndex);
					byteIndex += 2;
				} else {
					offset = readInt16(bytesArray, byteIndex);
					byteIndex += 2;
					segment = readInt16(bytesArray, byteIndex);
					byteIndex += 2;
				}
				op = new X86AbsoluteAddress(segment, offset); // with offset
				break;
			case v_mode:
				if (addrSize == true) {
					offset = readInt32(bytesArray, byteIndex);
					byteIndex += 4;
				} else {
					offset = readInt16(bytesArray, byteIndex);
					byteIndex += 2;
				}
				op = new X86AbsoluteAddress(offset); // with offset
				break;
			default:
				break;
			}
			break;
		case ADDR_G:
			switch (operandType) {
			case b_mode:
				op = X86Registers.getRegister8(regOrOpcode);
				break;
			case w_mode:
				op = X86Registers.getRegister16(regOrOpcode);
				break;
			case d_mode:
				op = X86Registers.getRegister32(regOrOpcode);
				break;
			case v_mode:
				if (operandSize == true) {
					op = X86Registers.getRegister32(regOrOpcode);
				} else {
					op = X86Registers.getRegister16(regOrOpcode);
				}
				break;
			default:
				break;
			}
			break;
		case ADDR_SEG:
			op = X86SegmentRegisters.getSegmentRegister(regOrOpcode);
			break;
		case ADDR_OFF:
			int off = 0;
			if (addrSize == true) {
				off = readInt32(bytesArray, byteIndex);
				byteIndex += 4;
			} else {
				off = readInt16(bytesArray, byteIndex);
				byteIndex += 2;
			}
			// op = new X86AbsoluteAddress((long)off);
			// --JK: This is actually a memory operand with constant address
			// used by MOV.
			// Absolute Addresses are now used only for far calls and far jumps.
			op = new X86MemoryOperand(getDataType(operandType, operandSize), segReg, off); // JK-
																							// Added
																							// segReg
																							// for
																							// mov
																							// fs:0,
																							// ecx
			break;
		case ADDR_J:
			long disp = 0;
			// The effective address is Instruction pointer + relative offset
			switch (operandType) {
			case b_mode:
				disp = (byte) readByte(bytesArray, byteIndex);
				byteIndex++;
				break;
			case v_mode:
				if (operandSize == true) {
					disp = readInt32(bytesArray, byteIndex);
					byteIndex += 4;
				} else {
					disp = readInt16(bytesArray, byteIndex);
					byteIndex += 2;
				}
				// disp = disp + (byteIndex-instrStartIndex);
				break;
			}
			op = new X86PCRelativeAddress(disp);
			break;
		case ADDR_ESDI:
			op = new X86MemoryOperand(getDataType(operandType, operandSize), X86SegmentRegisters.ES, X86Registers.EDI);
			break;
		case ADDR_DSSI:
			op = new X86MemoryOperand(getDataType(operandType, operandSize), X86SegmentRegisters.DS, X86Registers.ESI);
			break;
		case ADDR_R:
			switch (operandType) {
			case b_mode:
				op = X86Registers.getRegister8(mod);
				break;
			case w_mode:
				op = X86Registers.getRegister16(mod);
				break;
			case d_mode:
				op = X86Registers.getRegister32(mod);
				break;
			case v_mode:
				if (operandSize == true) {
					op = X86Registers.getRegister32(mod);
				} else {
					op = X86Registers.getRegister16(mod);
				}
				break;
			default:
				break;
			}
			break;
		case ADDR_FPREG:
			switch (operandType) {
			case 0:
				op = X86FloatRegisters.getRegister(0);
				break;
			case 1:
				op = X86FloatRegisters.getRegister(rm);
				break;
			}
			break;

		// SSE: reg field of ModR/M byte selects a 128-bit XMM register
		case ADDR_V:
			op = X86XMMRegisters.getRegister(regOrOpcode);
			break;

		// SSE: reg field of ModR/M byte selects a 64-bit MMX register
		case ADDR_P:
			op = X86MMXRegisters.getRegister(regOrOpcode);
			break;
		case ADDR_C:
			op = X86ControlRegisters.getRegister(regOrOpcode);
			break;
		case ADDR_RMR:
			op = X86Registers.getRegister32(rm);
			break;
		case ADDR_D:
			logger.error("Debug registers not supported!");
		case INDIR_REG:
			if (operandType != DX) {
				logger.warn("Operand type for I/O port addressing should be DX");
			}
			op = X86Registers.DX;
			break;
		default:
			logger.error("Error decoding operand: Unsupported addressing mode: " + addrMode + "\n Register code: "
					+ regOrOpcode);
		}
		if (op == null) {
			throw new RuntimeException("Unable to decode instruction operand for addressing mode " + addrMode
					+ ", operand type " + operandType + ", and operand size " + operandSize);
		}
		return op;
	}

	private X86SegmentRegister getSegmentRegisterFromPrefix(int prefixes) {
		X86SegmentRegister segRegister = null;

		if ((prefixes & PREFIX_CS) != 0) {
			segRegister = X86SegmentRegisters.CS;
		}
		if ((prefixes & PREFIX_DS) != 0) {
			segRegister = X86SegmentRegisters.DS;
		}
		if ((prefixes & PREFIX_ES) != 0) {
			segRegister = X86SegmentRegisters.ES;
		}
		if ((prefixes & PREFIX_FS) != 0) {
			segRegister = X86SegmentRegisters.FS;
		}
		if ((prefixes & PREFIX_SS) != 0) {
			segRegister = X86SegmentRegisters.SS;
		}
		if ((prefixes & PREFIX_GS) != 0) {
			segRegister = X86SegmentRegisters.GS;
		}

		return segRegister;
	}

	private boolean isModRMPresent(int addrMode) {
		if ((addrMode == ADDR_E) || (addrMode == ADDR_G) || (addrMode == ADDR_FPREG) || (addrMode == ADDR_Q)
				|| (addrMode == ADDR_W) || (addrMode == ADDR_C) || (addrMode == ADDR_D)) {
			return true;
		} else {
			return false;
		}
	}

	public static void setByte(BinaryInputBuffer bytesArray, int index, int value) {
		int ret = 0;
		if (index < bytesArray.getSize()) {
			try {
				bytesArray.setBYTE(index, value);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new ArrayIndexOutOfBoundsException("Disassembler requested byte outside of file area: 0x"
					+ Long.toHexString(index));
		}
	}
}
