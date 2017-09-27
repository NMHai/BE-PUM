/*
 * AbstractCOFFModule.java - This file is part of the Jakstab project.
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
package org.jakstab.loader.pe;

import java.io.IOException;
import java.util.Iterator;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.disasm.Disassembler;
import org.jakstab.disasm.x86.X86Disassembler;
import org.jakstab.loader.ExecutableImage;
import org.jakstab.rtl.expressions.ExpressionFactory;
import org.jakstab.rtl.expressions.RTLMemoryLocation;
import org.jakstab.rtl.expressions.RTLNumber;
import org.jakstab.util.BinaryFileInputBuffer;
import org.jakstab.util.Logger;

import v2.org.analysis.structure.Convert;

/**
 * An abstract class that encapsulates the common features of MS COFF and PE
 * files.
 * 
 * @author Johannes Kinder
 */
public abstract class AbstractCOFFModule implements ExecutableImage {

	private static final Logger logger = Logger.getLogger(AbstractCOFFModule.class);

	protected BinaryFileInputBuffer inBuf;
	protected COFF_Header coff_header;
	protected SectionHeader[] section_headers;
	protected Disassembler disassembler;

	public long getByteValue(long va) {
		long t = va - getBaseAddress();
		if (t >= 0 && t < inBuf.getSize()) {
			return inBuf.getByteAt((int) t);
		}

		return 0;
	}

	public boolean isInsideHeader(long va) {
//		long z = getBaseAddress();
//		long t = (va & 0xFFFFFFFF) - z;
//		return t >= 0 && t < inBuf.getSize() * 8;
		long z = getBaseAddress();
		long t = (va & 0xFFFFFFFF) - z;		
//		boolean x = t >= 0 && t < inBuf.getSize() * 8;
		boolean y = true;
		
		if (t < 0) {
			y = false; 
//			return false;
		}
		for (int i=0; i < section_headers.length; i++) {
			if (t > getSectionHeader(i).VirtualAddress) {
				y = false;
			}
		}
		
//		if (x != y) {
//			System.out.println("Debug");
//		}
		
		return y;
	}
	
	public boolean isBetweenSections(long va) {
//		long z = getBaseAddress();
//		long t = (va & 0xFFFFFFFF) - z;
//		return t >= 0 && t < inBuf.getSize() * 8;
		long z = getBaseAddress();
		long t = (va & 0xFFFFFFFF) - z;		
		
		if (t < 0 || section_headers.length < 2 || t < getSectionHeader(0). VirtualAddress) {
			return false;
		}
		for (int i=1; i < section_headers.length; i++) {
			if (t <= getSectionHeader(i).VirtualAddress) {
				return true;
			}
		}
		
//		if (x != y) {
//			System.out.println("Debug");
//		}
		
		return false;
	}

	public long getWordValue(long va) {
		String temp = Convert.longToHex(getByteValue(va + 1), 8);
		temp += Convert.longToHex(getByteValue(va), 8);
		return Convert.hexToLong(temp);
	}

	public long getDoubleWordValue(long index) {
		String temp = Convert.longToHex(getByteValue(index + 3), 8);
		temp += Convert.longToHex(getByteValue(index + 2), 8);
		temp += Convert.longToHex(getByteValue(index + 1), 8);
		temp += Convert.longToHex(getByteValue(index), 8);
		// int t = readByte(index + 3);
		return Convert.hexToLong(temp);
	}

	@Override
	public final long getFilePointer(AbsoluteAddress va) {
		// long t = va.getValueOperand() - getBaseAddress();
		long fp = getFilePointerFromRVA(va.getValue() - getBaseAddress());
		if (fp >= 0) {
			return fp;
		} else {
			return -1;
		}
	}

	public int get32ValueMemory(AbsoluteAddress address) {
		return 0;
	}

	@Override
	public final boolean isCodeArea(AbsoluteAddress va) {
		int section = getSectionNumber(va);
		if (section < 0) {
			return false;
		} else {
//			return section_headers[section].getName().toLowerCase().contains("code");
			return isCodeSection(section);
		}
	}

	/**
	 * Returns the file pointer equivalent of the given RVA.
	 */

	public void setMemoryValue(long rva, int value) {

	}

	public long getFilePointerFromRVA(long rva) {
		int sct = getSectionNumberByRVA(rva);

		/*
		 * if
		 * (Program.getProgram().getFileName().equals("Flooder.Win32.AngryPing"
		 * )) return (rva - getSectionHeader(sct).VirtualAddress) +
		 * getSectionHeader(sct).PointerToRawData;
		 */
		if (sct < 0) {
			return -1;
		}

		if (rva - getSectionHeader(sct).VirtualAddress > getSectionHeader(sct).SizeOfRawData) {
			return -1;
		}
		return (rva - getSectionHeader(sct).VirtualAddress) + getSectionHeader(sct).PointerToRawData;
	}

	/**
	 * Returns the RVA for a given file pointer.
	 */
	protected final long getRVAFromFilePointer(long filePointer) {
		int sct = getSectionNumber(filePointer);
		if (sct < 0) {
			return -1;
		}
		return ((filePointer - getSectionHeader(sct).PointerToRawData) + getSectionHeader(sct).VirtualAddress);
	}

	public long getRVA(long filePointer) {

		int sct = getSectionNumber(filePointer);
		if (sct < 0)
		 {
			return -1;
		// long x = getSectionHeader(sct).PointerToRawData;
		// long y = getSectionHeader(sct).VirtualAddress;
		// long z = filePointer - y + x;
		}

		return ((filePointer + getSectionHeader(sct).PointerToRawData) - getSectionHeader(sct).VirtualAddress);
	}

	/**
	 * Returns the number of the section the given virtual address is in.
	 * 
	 * @param va
	 *            the virtual address
	 * @return the section number
	 */
	protected final int getSectionNumber(AbsoluteAddress va) {
		return getSectionNumberByRVA(va.getValue() - getBaseAddress());
	}

	/**
	 * Returns the number of the section a given file pointer lies in.
	 * 
	 * @param fp
	 *            the file pointer
	 * @return the section number
	 */
	protected final int getSectionNumber(long fp) {
		for (int i = 0; i < getNumberOfSections(); i++) {
			if (getSectionHeader(i).PointerToRawData <= fp
					&& (getSectionHeader(i).PointerToRawData + getSectionHeader(i).SizeOfRawData) > fp) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the number of the section a given RVA lies in.
	 * 
	 * @param rva
	 *            the Relative Virtual Address
	 * @return the section number
	 */
	protected abstract int getSectionNumberByRVA(long rva);

	/**
	 * Returns the virtual address that corresponds to a given file pointer
	 * 
	 * @param fp
	 *            the file pointer
	 * @return the virtual address
	 */
	@Override
	public final AbsoluteAddress getVirtualAddress(long fp) {
		long rva = getRVAFromFilePointer(fp);
		if (rva >= 0) {
			return new AbsoluteAddress(rva + getBaseAddress());
		} else {
			return null;
		}
	}

	protected final int getNumberOfSections() {
		return section_headers.length;
	}

	@Override
	public AbsoluteAddress getMaxAddress() {
		long highAddress = Long.MIN_VALUE;
		for (int i = 0; i < getNumberOfSections(); i++) {
			highAddress = Math.max(getSectionHeader(i).VirtualAddress + getSectionHeader(i).SizeOfRawData, highAddress);
		}
		highAddress += getBaseAddress();
		return new AbsoluteAddress(highAddress);
	}

	@Override
	public AbsoluteAddress getMinAddress() {
		long lowAddress = Long.MAX_VALUE;
		for (int i = 0; i < getNumberOfSections(); i++) {
			lowAddress = Math.min(getSectionHeader(i).VirtualAddress, lowAddress);
		}
		lowAddress += getBaseAddress();
		return new AbsoluteAddress(lowAddress);
	}

	protected final SectionHeader getSectionHeader(int index) {
		return section_headers[index];
	}

	protected final boolean isCodeSection(int section) {
		return getSectionHeader(section).isCodeSection();
	}

	protected abstract long getBaseAddress();

	@Override
	public RTLNumber readMemoryLocation(RTLMemoryLocation m) throws IOException {
		if (!(m.getAddress() instanceof RTLNumber)) {
			return null;
		}
		AbsoluteAddress va = new AbsoluteAddress((RTLNumber) m.getAddress());
		long fp = getFilePointer(va);
		if (getSectionNumber(fp) >= 0) {
			assert m.getBitWidth() % 8 == 0 : "Non-byte-aligned memory reference";
			long val = 0;
			int bytes = m.getBitWidth() / 8;
			// OR together the least significant bytes
			inBuf.seek(fp);
			for (int i = 0; i < bytes - 1; i++) {
				val = val | ((long) inBuf.readBYTE()) << (i * 8);
			}
			// do not mask the MSB with 0xFF, so we get sign extension for free
			val = val | (((long) inBuf.readINT8()) << (bytes - 1) * 8);
			// logger.debug("Read constant value " + val + " from address " + m
			// + " (file offset: " + Long.toHexString(fp) + ") in image.");
			return ExpressionFactory.createNumber(val, m.getBitWidth());
		}
		logger.debug("No value can be read from image for address " + m);
		return null;
	}

	@Override
	public byte[] getByteArray() {
		return inBuf.getByteArray();
	}

	@Override
	public Iterator<AbsoluteAddress> codeBytesIterator() {
		return new Iterator<AbsoluteAddress>() {

			long fp = 0;
			int sec = -1;

			{
				moveToNextCodeSection();
			}

			private void moveToNextCodeSection() {
				sec++;
				while (sec < getNumberOfSections() && !isCodeSection(sec)) {
					sec++;
				}
				if (sec >= getNumberOfSections()) {
					fp = -1;
					sec = -1;
				} else {
					fp = getSectionHeader(sec).PointerToRawData;
				}
			}

			private void moveToNextCodeByte() {

				fp++;

				if (fp >= getSectionHeader(sec).PointerToRawData + getSectionHeader(sec).SizeOfRawData) {
					moveToNextCodeSection();
					if (sec < 0) {
						return;
					}
				}

			}

			@Override
			public boolean hasNext() {
				return (fp >= 0);
			}

			@Override
			public AbsoluteAddress next() {
				if (!hasNext()) {
					throw new IndexOutOfBoundsException();
				}
				AbsoluteAddress res = getVirtualAddress(fp);
				moveToNextCodeByte();
				return res;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public Disassembler getDisassembler() {
		if (disassembler == null) {
			disassembler = new X86Disassembler(inBuf);
		}
		return disassembler;
	}
}