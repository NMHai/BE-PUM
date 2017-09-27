/*******************************************************************************
 * Copyright (c) 2004, 2006 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Intel Corporation - Initial API and implementation
 *     Mark Mitchell, CodeSourcery - Bug 136896: View variables in binary format
 *******************************************************************************/
package org.jakstab.loader.elf;

import java.math.BigInteger;

public class Addr32 implements IAddress {

	private static final long MAX_ADDR = 0xffffffffL;

	public static final Addr32 ZERO = new Addr32(0);
	public static final Addr32 MAX = new Addr32(MAX_ADDR);

	public static final BigInteger MAX_OFFSET = BigInteger.valueOf(MAX_ADDR);

	private static final int BYTES_NUM = 4;
	private static final int DIGITS_NUM = BYTES_NUM * 2;
	private static final int CHARS_NUM = DIGITS_NUM + 2;
	private static final int BINARY_DIGITS_NUM = BYTES_NUM * 8;
	private static final int BINARY_CHARS_NUM = BINARY_DIGITS_NUM + 2;

	private final long address;

	/*
	 * addrBytes should be 4 bytes length
	 */
	public Addr32(byte[] addrBytes) {
		if (addrBytes.length != 4)
			throw (new NumberFormatException("Invalid address array")); //$NON-NLS-1$
		/* We should mask out sign bits to have correct value */
		this.address = ((((long) addrBytes[0]) << 24) & 0xFF000000L) + ((((long) addrBytes[1]) << 16) & 0x00FF0000L)
				+ ((((long) addrBytes[2]) << 8) & 0x0000FF00L) + (addrBytes[3] & 0x000000FFL);
	}

	public Addr32(long rawaddress) {
		this(rawaddress, true);
	}

	public Addr32(long rawaddress, boolean truncate) {
		if (rawaddress > MAX_ADDR || rawaddress < 0) {
			if (truncate) {
				rawaddress &= MAX_ADDR; // truncate
			} else {
				throw (new NumberFormatException("Value out of range"));
			}
		}
		this.address = rawaddress;
	}

	public Addr32(String addr) {
		this(addr, true);
	}

	public Addr32(String addr, boolean truncate) {
		this(Long.decode(addr).longValue(), truncate);
	}

	public Addr32(String addr, int radix) {
		this(addr, radix, true);
	}

	public Addr32(String addr, int radix, boolean truncate) {
		this(Long.parseLong(addr, radix), truncate);
	}

	public IAddress add(BigInteger offset) {
		return new Addr32(this.address + offset.longValue());
	}

	public IAddress add(long offset) {
		return new Addr32(this.address + offset);
	}

	public BigInteger getMaxOffset() {
		return MAX_OFFSET;
	}

	public BigInteger getValue() {
		return BigInteger.valueOf(address);
	}

	public BigInteger distanceTo(IAddress other) {
		if (!(other instanceof Addr32)) {
			throw new IllegalArgumentException();
		}
		return BigInteger.valueOf(((Addr32) other).address - address);
	}

	public int compareTo(Object other) {
		if (!(other instanceof Addr32)) {
			throw new IllegalArgumentException();
		}
		if (address > ((Addr32) other).address) {
			return 1;
		}
		if (address < ((Addr32) other).address) {
			return -1;
		}
		return 0;
	}

	public boolean isMax() {
		return address == MAX.address;
	}

	public boolean isZero() {
		return address == ZERO.address;
	}

	@Override
	public String toString() {
		return toString(10);
	}

	public String toString(int radix) {
		return Long.toString(address, radix);
	}

	@Override
	public boolean equals(Object x) {
		if (x == this)
			return true;
		if (!(x instanceof Addr32))
			return false;
		return this.address == ((Addr32) x).address;
	}

	@Override
	public int hashCode() {
		return (int) (address ^ (address >> 32));
	}

	public String toHexAddressString() {
		String addressString = Long.toString(address, 16);
		StringBuffer sb = new StringBuffer(CHARS_NUM);
		int count = DIGITS_NUM - addressString.length();
		sb.append("0x"); //$NON-NLS-1$
		for (int i = 0; i < count; ++i) {
			sb.append('0');
		}
		sb.append(addressString);
		return sb.toString();
	}

	public String toBinaryAddressString() {
		String addressString = Long.toString(address, 2);
		StringBuffer sb = new StringBuffer(BINARY_CHARS_NUM);
		int count = BINARY_DIGITS_NUM - addressString.length();
		sb.append("0b"); //$NON-NLS-1$
		for (int i = 0; i < count; ++i) {
			sb.append('0');
		}
		sb.append(addressString);
		return sb.toString();
	}

	public int getCharsNum() {
		return CHARS_NUM;
	}

	public int getSize() {
		return BYTES_NUM;
	}
}
