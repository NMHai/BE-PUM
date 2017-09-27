package v2.org.analysis.system.dll.abstracts;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.environment.memory.ExternalMemory;
import v2.org.analysis.environment.memory.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.system.LibAbstract;
import v2.org.analysis.system.LibraryHandle;

public class LibImage extends LibAbstract{
	// YenNguyen
	private long baseAddress; // 7C800000
	private Map<AbsoluteAddress, String> exportTable;
	private LibPEHandler peHandler;
	// Map <Integer, ByteClass> image;
	private int length;
	private String libName;
	private byte[] value;

	@Override
	public long getBaseAddress() {
		return baseAddress;
	}

	public void setBaseAddress(long baseAddress) {
		this.baseAddress = baseAddress;
	}

	private String path = null; // YenNguyen

	public String getPath() {
		return path;
	}

	public LibImage() {
		createImage();
		exportTable = new HashMap<AbsoluteAddress, String>();
		initExportTable();
	}

	public LibImage(String path) {
		this.path = path;
		createImage();
		exportTable = new HashMap<AbsoluteAddress, String>();
		initExportTable();
	}

	private void initExportTable() {
		if (this.path.equals(LibraryHandle.KERNEL32PATH)) {
			exportTable.put(new AbsoluteAddress(2088899403), "GetWindowsDirectoryA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088849274), "GetSystemDirectoryA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088980502), "GetCurrentDirectoryA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088984821), "SetCurrentDirectoryA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088843369), "FindFirstFileA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088839186), "SetFileAttributesA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088770088), "CreateFileA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088801518), "CreateFileMappingA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088803287), "CloseHandle@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088980169), "FindNextFileA@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088810901), "MapViewOfFile@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088811012), "UnmapViewOfFile@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088832030), "SetFilePointer@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088968286), "SetEndOfFile@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088824423), "FindClose@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088833642), "GetVersion@kernel32.dll");
			exportTable.put(new AbsoluteAddress(2088841507), "IsDebuggerPresent@kernel32.dll");
		}
	}

	public long getReturnRandomValue() {
		return baseAddress + Convert.hexToLong("300") + (int) Math.random() * 30;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public static void main(String[] args) {
		LibImage k = new LibImage();

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

		index = "262c";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "2648";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "264c";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "2650";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));
	}

	private void createImage() {
		// TODO Auto-generated method stub
		// image = new HashMap <Integer, ByteClass>();
		FileInputStream fileInputStream = null;

		File file = new File(this.path);
		libName = file.getName();

		length = (int) file.length();
		value = new byte[this.length];

		try {
			// Create PE Handler
			peHandler = new LibPEHandler(file);
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

	private long getFilePointerFromRVA(long address) {
		long t = peHandler.getFilePointerFromRVA(address);
		if (t < 0) {
			return -1;
		}
		else {
			return t;
		// return in.getValueOperand();
		}
	}
	@Override
	public int readByte(int address) {
		int off = (int) getFilePointerFromRVA(address - this.baseAddress);
		// int off = (int) (offset - this.baseAddress);
		if (off < 0 || off >= value.length) {
			if (address != 0) {
				ExternalMemoryReturnData ret = ExternalMemory.getByte(address);
				if (ret != null && ret.isValidAddress) {
					return (int) ret.value.getValue();
				}
			}
			return Integer.MIN_VALUE;
		}
		return value[off];
	}
	@Override
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
	@Override
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

	@Override
	public long getProcAddress(String functionName) {
		// TODO Auto-generated method stub
		/*
		 * for (Entry<AbsoluteAddress, String> entry: exportTable.entrySet()) {
		 * String t[] = entry.getValueOperand().split("@"); if
		 * (t[0].equals(functionName)) return entry.getKey().getValueOperand();
		 * }
		 * 
		 * return 0;
		 */

		return peHandler.getExportAddress(functionName);
	}

	public boolean isInside(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		return addr.getValue() >= this.baseAddress && 
				addr.getValue() < (this.baseAddress + getLength());
	}

	@Override
	public String getProcName(long v) {
		// TODO Auto-generated method stub
		/*
		 * String s=""; for (Entry<AbsoluteAddress, String> entry :
		 * exportTable.entrySet()) { if (entry.getKey().getValueOperand() == v)
		 * s = entry.getValueOperand(); } String d = peHandler.getExportName(v);
		 * if (!s.equals(d)) System.out.println("Debug");
		 */
		return peHandler.getExportName(v);
	}

	@Override
	public String getLibraryName() {
		return libName;
	}

	public void setLibraryName(String libName) {
		this.libName = libName;
	}

	@Override
	public Map<AbsoluteAddress, String> getExportTable() {
		// TODO Auto-generated method stub
		return exportTable;
	}
}
