package v2.org.analysis.file;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.structure.Convert;

import java.io.File;
import java.io.FileInputStream;

public class FileImage {
	private long baseAddress; // 7C800000
	public String path = "asm/api/Virus.Win32.Aztec.01";
	private PEHandler peHandler;

	public PEHandler getPeHandler() {
		return peHandler;
	}

	public void setPeHandler(PEHandler peHandler) {
		this.peHandler = peHandler;
	}

	// Map <Integer, ByteClass> image;
	private int length;
	private byte[] value;

	public FileImage() {
		createImage();
	}

	public long getReturnRandomValue() {
		return baseAddress + 2 * Convert.hexToLong("50000") + (int) Math.random() * 5000;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public static void main(String[] args) {
		FileImage k = new FileImage();

		System.out.println("Base address: " + k.baseAddress + " Hex:" + Convert.longToHex(k.baseAddress));
		System.out.println("Length: " + k.getLength());
		String index = "3c";
		int offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index + " Value:" + offset);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "168";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "600";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		// index = "2648";
		// offset = (int) (k.baseAddress +
		// k.getPeHandler().getCodeBaseAddress());
		offset = (int) k.getPeHandler().getEntryPoint().getValue();
		System.out.println("Offset:" + offset);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		System.out.println("Read Instruction at:" + k.getPeHandler().getEntryPoint().toString());
		Instruction inst = k.readInstruction(k.getPeHandler().getEntryPoint());
		System.out.println("Name: " + inst.getName());
		for (int i = 0; i < inst.getOperandCount(); i++)
			System.out.println(inst.getOperand(i).toString());
	}

	public Instruction readInstruction(AbsoluteAddress addr) {
		return null;
	}

	private void createImage() {
		// TODO Auto-generated method stub
		// image = new HashMap <Integer, ByteClass>();
		FileInputStream fileInputStream = null;

		File file = new File(this.path);

		length = (int) file.length();
		value = new byte[this.length];

		try {
			// Create PE Handler
			peHandler = new PEHandler(file);
			this.baseAddress = peHandler.getBaseAddress();
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(value);
			fileInputStream.close();

			// for (int i = 0; i < value.length; i++) {
			// System.out.print((char)value[i]);
			// }

			// System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// offset = new byte[(int)f.length()];
	}

	public FileImage(String path) {
		this.path = path;
		createImage();
	}

	private long getFilePointerFromRVAAddress(long address) {
		// long t = peHandler.getRVA(address);
		long t = peHandler.getFilePointerFromRVA(address);
		if (t < 0)
			return address;
		else
			return t;
		// return in.getValueOperand();
	}

	public int readByte(int offset) {
		int off = (int) getFilePointerFromRVAAddress(offset - this.baseAddress);

		return value[off];
	}

	public int readByteOffset(int offset) {
		// int off = (int) getRVAAddress(offset - this.baseAddress);

		return value[offset];
	}

	public long readWord(int index) {
		/*
		 * int ret = 0; ret = readByte(index + 1); ret |= readByte(index + 1) <<
		 * 8; return ret;
		 */
		// int off = (int) getRVAAddress(index - this.baseAddress);

		String temp = Convert.longToHex(readByte(index + 1), 8);
		temp += Convert.longToHex(readByte(index), 8);
		return Convert.hexToLong(temp);
	}

	public long readWordOffset(int index) {
		/*
		 * int ret = 0; ret = readByte(index + 1); ret |= readByte(index + 1) <<
		 * 8; return ret;
		 */
		// int off = (int) getRVAAddress(index - this.baseAddress);

		String temp = Convert.longToHex(readByteOffset(index + 1), 8);
		temp += Convert.longToHex(readByteOffset(index), 8);
		return Convert.hexToLong(temp);
	}

	public long readDoubleWord(int index) {
		// int ret = 0;
		/*
		 * ret = readByte(index); ret |= readByte(index + 1) << 8; ret |=
		 * readByte(index + 2) << 16; ret |= readByte(index + 3) << 24;
		 */
		// int off = (int) getRVAAddress(index - this.baseAddress);

		String temp = Convert.longToHex(readByte(index + 3), 8);
		temp += Convert.longToHex(readByte(index + 2), 8);
		temp += Convert.longToHex(readByte(index + 1), 8);
		temp += Convert.longToHex(readByte(index), 8);
		// int t = readByte(index + 3);
		return Convert.hexToLong(temp);
	}

	public long readDoubleWordOffset(int index) {
		// int ret = 0;
		/*
		 * ret = readByte(index); ret |= readByte(index + 1) << 8; ret |=
		 * readByte(index + 2) << 16; ret |= readByte(index + 3) << 24;
		 */
		// int off = (int) getRVAAddress(index - this.baseAddress);

		String temp = Convert.longToHex(readByteOffset(index + 3), 8);
		temp += Convert.longToHex(readByteOffset(index + 2), 8);
		temp += Convert.longToHex(readByteOffset(index + 1), 8);
		temp += Convert.longToHex(readByteOffset(index), 8);
		// int t = readByte(index + 3);
		return Convert.hexToLong(temp);
	}

	public long getBaseAddress() {
		// TODO Auto-generated method stub
		return this.baseAddress;
	}

}
