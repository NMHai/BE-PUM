/*
 * BinaryFileInputBuffer.java - This file is part of the Jakstab project.
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
package org.jakstab.util;

import java.io.IOException;

/**
 * @author Johannes Kinder
 */
public class BinaryFileInputBuffer extends BinaryInputBuffer {

	private byte[] data;
	private int size;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(BinaryFileInputBuffer.class);

	/**
	 * Creates a BinaryInputBuffer from the given InputStream and buffers all
	 * the available data. Note: if input is a network stream, this constructor
	 * will only buffer the data that is available at the time the constructor
	 * is called (i.e. as much as is reported by InputStream.available()).
	 */
	public BinaryFileInputBuffer(java.io.InputStream input) throws java.io.IOException {
		// reads in an entire input stream and buffers it
		current = 0;
		data = new byte[input.available()];
		size = input.read(data);
		input.close();
	}

	// PHONG: insert additional constructor here
	public BinaryFileInputBuffer(byte[] opcodes) {
		// reads in an entire input stream and buffers it
		current = 0;
		size = opcodes.length;
		data = opcodes;
	}

	@Override
	public int readBYTE() throws java.io.IOException {
		// throws java.io.IOException if EOF
		return (data[current++] & 0xFF) & 0xFF;
	}

	@Override
	public void setBYTE(int pos, long value) throws java.io.IOException {

		if (pos < 0) {
			System.out.println("Set Byte Fail");
			return;
		}
		// System.out.println("Before Set Byte:" + data[pos]);
		data[pos] = (byte) ((value & 0xFF) & 0xFF);
		// System.out.println("After Set Byte:" + data[pos]);
	}

	@Override
	public byte getByteAt(int fp) {
		return data[fp];
	}

	@Override
	public long getSize() {
		return size;
	}

	public byte[] getByteArray() {
		return data;
	}

	@Override
	public void addBYTE(int pos, long value) throws IOException {
		// TODO Auto-generated method stub
		if (pos < 0) {
			System.out.println("Add Byte Fail");
			return;
		}

		data[pos] = (byte) (((value + data[pos]) & 0xFF) & 0xFF);
	}

	@Override
	public void addDoubleWord(int pos, long value) throws IOException {
		// TODO Auto-generated method stub

		if (pos < 0) {
			System.out.println("AddDoubleWord Fail");
			return;
		}

		// System.out.println("Before Add:" + data[pos] + " " + data[pos + 1]
		// + " " + data[pos + 2] + " " + data[pos + 3]);
		int ret = ((data[pos] & 0xFF) & 0xFF);
		ret |= ((data[pos + 1] & 0xFF) & 0xFF) << 8;
		ret |= ((data[pos + 2] & 0xFF) & 0xFF) << 16;
		ret |= ((data[pos + 3] & 0xFF) & 0xFF) << 24;

		ret += value;
		/*
		 * data[pos] = (byte) ((((byte) (ret % Math.pow(2,8))) & 0xFF) & 0xFF);
		 * long x = (long) (ret % Math.pow(2, 16)); data[pos+1] = (byte)
		 * ((((byte) (x / Math.pow(2,8))) & 0xFF) & 0xFF); x = (long) (ret /
		 * Math.pow(2, 16)); data[pos+2] = (byte) ((((byte) (x % Math.pow(2,8)))
		 * & 0xFF) & 0xFF); data[pos+3] = (byte) ((((byte) (x / Math.pow(2,8)))
		 * & 0xFF) & 0xFF);
		 */

		data[pos] = (byte) ((byte) ret & 0xFF);
		;
		data[pos + 1] = (byte) ((ret >> 8) & 0xFF);
		data[pos + 2] = (byte) ((ret >> 16) & 0xFF);
		data[pos + 3] = (byte) ((ret >> 24) & 0xFF);
		// System.out.println("After Add: " + data[pos] + " " + data[pos + 1]
		// + " " + data[pos + 2] + " " + data[pos + 3]);
		// long y = value mod Math.pow(2,8);
	}

	@Override
	public void addWord(int pos, long value) throws IOException {
		// TODO Auto-generated method stub
		if (pos < 0) {
			System.out.println("AddWord Fail");
			return;
		}
		// System.out.println("Before Add:" + data[pos] + " " + data[pos + 1]);
		int ret = ((data[pos] & 0xFF) & 0xFF);
		ret |= ((data[pos + 1] & 0xFF) & 0xFF) << 8;

		ret += value;

		// data[pos+1] = (byte) ((((byte) (ret / Math.pow(2,8))) & 0xFF) &
		// 0xFF);
		// data[pos] = (byte) ((((byte) (ret % Math.pow(2,8))) & 0xFF) & 0xFF);

		data[pos] = (byte) ((byte) ret & 0xFF);
		data[pos + 1] = (byte) ((ret >> 8) & 0xFF);
		// System.out.println("After Add:" + data[pos] + " " + data[pos + 1]);
	}

	@Override
	public void setWord(int pos, long ret) throws IOException {
		// TODO Auto-generated method stub

		if (pos < 0) {
			System.out.println("SetWord Fail");
			return;
		}
		// System.out.println("Before Set:" + data[pos] + " " + data[pos + 1]);
		// data[pos+1] = (byte) ((((byte) (ret / Math.pow(2,8))) & 0xFF) &
		// 0xFF);
		// data[pos] = (byte) ((((byte) (ret % Math.pow(2,8))) & 0xFF) & 0xFF);

		data[pos] = (byte) ((byte) ret & 0xFF);
		data[pos + 1] = (byte) ((ret >> 8) & 0xFF);
		// System.out.println("After Set: " + data[pos] + " " + data[pos + 1]);
	}

	@Override
	public void setDoubleWord(int pos, long ret) throws IOException {
		// TODO Auto-generated method stub
		if (pos < 0) {
			System.out.println("SetDoubleWord Fail");
			return;
		}

		// System.out.println("Before Set:" + data[pos] + " " + data[pos + 1]
		// + " " + data[pos + 2] + " " + data[pos + 3]);

		/*
		 * data[pos] = (byte) ((((byte) (ret % Math.pow(2,8))) & 0xFF) & 0xFF);
		 * long x = (long) (ret % Math.pow(2, 16)); data[pos+1] = (byte)
		 * ((((byte) (x / Math.pow(2,8))) & 0xFF) & 0xFF); x = (long) (ret /
		 * Math.pow(2, 16)); data[pos+2] = (byte) ((((byte) (x % Math.pow(2,8)))
		 * & 0xFF) & 0xFF); data[pos+3] = (byte) ((((byte) (x / Math.pow(2,8)))
		 * & 0xFF) & 0xFF);
		 */
		data[pos] = (byte) ((byte) ret & 0xFF);
		;
		data[pos + 1] = (byte) ((ret >> 8) & 0xFF);
		data[pos + 2] = (byte) ((ret >> 16) & 0xFF);
		data[pos + 3] = (byte) ((ret >> 24) & 0xFF);

		// System.out.println("After Set: " + data[pos] + " " + data[pos + 1]
		// + " " + data[pos + 2] + " " + data[pos + 3]);
	}

}
