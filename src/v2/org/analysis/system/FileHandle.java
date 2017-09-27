package v2.org.analysis.system;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.analysis.concrete_execution.ConcreteValueMemoryOperand;
import org.analysis.symbolic_execution.SymbolValueMemoryOperand;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.memory.MemoryV1;

/**
 * This class defines the management of files
 * 
 * @author NMHai
 * 
 */

public class FileHandle {
	private List<File> fList;
	private long baseAddress = 2415919104l;
	// private long handle = 0;
	private String path;

	public FileHandle() {
		fList = new ArrayList<File>();
	}

	public boolean containFile(long x) {
		// TODO Auto-generated method stub
		for (File t : fList) {
			if (t.getFileHandle() == x) {
				// t.closeFile();
				return true;
			}
		}
		return false;
	}

	public boolean contain(long addr) {
		return addr > baseAddress;
	}

	public long closeFile(long x) {
		// TODO Auto-generated method stub
		for (File t : fList) {
			if (t.getFileHandle() == x) {
				t.closeFile();
				return 1;
			}
		}

		return 0;
	}

	public long createFile(String fileName, long t2, long t3, long t4, long t5, long t6, long t7) {
		// TODO Auto-generated method stub
		for (File file : fList) {
			if (file.getFileName().equals(fileName.toLowerCase())) {
				return file.getFileHandle();
			}
		}

		long handle = Math.round(Math.random() * Math.pow(10, 7));
		File f = new File(fileName, t2, t3, t4, t5, t6, t7, handle, path);
		fList.add(f);

		return f.getFileHandle();
	}

	public long getFileAttribute(String fName) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileName().equals(fName)) {
				return f.getAttribute();
			}
		}

		return 4294967295l;
	}

	public long setFileAttribute(String fileName, long t2) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileName().equals(fileName.toLowerCase())) {
				f.setAttribute(t2);
				return 1;
			}
		}

		return 0;
	}

	public long deleteFile(String fName) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileName().equals(fName)) {
				fList.remove(f);
				return 1;
			}
		}

		return 0;
	}

	public long findFirstFile(String search, SymbolValueMemoryOperand symbolValueMemoryOperand, long t2) {
		// TODO Auto-generated method stub
		long sH = 0;
		for (File f : fList) {
			if (f.getFileName().equals(search)) {
				sH = Math.round(Math.random() * Math.pow(10, 6));
				f.setSearchHandle(sH);
				String t = "...Dz����Dz����s./'��............../.." + f.getFileName();
				symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, t2), t);

				return sH;
			}
		}

		sH = Math.round(Math.random() * Math.pow(10, 6));
		File f = new File();
		final String re = Pattern.quote("*");
		// expression = expression.replaceFirst(re, "");
		String fileName = search.replaceAll(re, "demo");
		f.setFileHandle(Math.round(Math.random() * Math.pow(10, 6)));
		f.setSearchHandle(sH);
		f.setFileName(fileName);
		f.readFile();
		fList.add(f);
		String t = "...Dz����Dz����s./'��............../.." + fileName;
		symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, t2), t);

		return sH;
	}

	public long findNextFile(long sHandle, SymbolValueMemoryOperand symbolValueMemoryOperand, long t2) {
		return 1;
	}

	public long findNextFile(long sHandle, MemoryV1 memory, long t2) {
		return 0;
	}

	public long findNextFile(long sHandle, ConcreteValueMemoryOperand c, long t2) {
		return 0;
	}

	public long findFirstFile(String search, ConcreteValueMemoryOperand s, long t2) {
		// TODO Auto-generated method stub
		long sH = 0;
		for (File f : fList) {
			if (f.getFileName().equals(search)) {
				sH = Math.round(Math.random() * Math.pow(10, 6));
				f.setSearchHandle(sH);
				String t = "...Dz����Dz����s./'��............../.." + f.getFileName();
				s.setText(new X86MemoryOperand(DataType.INT32, t2), t);
			}
		}

		sH = Math.round(Math.random() * Math.pow(10, 6));
		File f = new File();
		final String re = Pattern.quote("*");
		// expression = expression.replaceFirst(re, "");
		String fileName = search.replaceAll(re, "demo");
		f.setFileHandle(Math.round(Math.random() * Math.pow(10, 6)));
		f.setSearchHandle(sH);
		f.setFileName(fileName);
		f.readFile();
		fList.add(f);
		String t = "...Dz����Dz����s./'��............../.." + fileName;
		s.setText(new X86MemoryOperand(DataType.INT32, t2), t);

		return sH;
	}

	public long copyFile(String src, String dest, long t3) {
		File s = getFile(src);
		File d = getFile(dest);

		if (s == null) {
			return 0;
		}
		if (d == null) {
			d = new File(dest, s.getAccess(), s.getShareMode(), s.getpSecurity(), s.getMode(), s.getAttribute(),
					s.gethTemplateFile(), 0);
			d.setPathFile(path);
			fList.add(d);
		}
		d.copyContent(s.getContent());

		return 1;
	}

	public File getFile(String name) {
		for (File f : fList) {
			if (f.getFileName().equals(name)) {
				return f;
			}
		}

		return null;
	}

	public int closeFind(long t) {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public long writeFile(long t1, String str, long t3, long t4, long t5) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileHandle() == t1) {
				return f.writeContent(str, t3, t4, t5);
				// return 1;
			}
		}

		return 0;
	}

	public String readFile(long t1, long t3, long t4, long t5) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			// String ret = "";
			if (f.getFileHandle() == t1) {
				return f.getContent(t3, t4);
			}
		}

		return null;
	}

	public long moveFile(String fileNameOld, String fileNameNew) {
		// TODO Auto-generated method stub
		File f = getFile(fileNameOld);

		if (f != null) {
			f.setFileName(fileNameNew);
			return 1;
		}

		return 0;
	}

	public long setFilePointer(long handle, long numberByte, long address, long moveType) {
		for (File f : fList) {
			if (f.getFileHandle() == handle) {
				FilePointer fPointer = new FilePointer(numberByte, address, moveType);
				f.setFilePointer(fPointer);
				return f.getFilePointer().getLowOrderAddress();
			}
		}

		return 0;
	}

	public long createFileMapping(long t1, long t2, long t3, long t4, long t5, String fileMapName) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileHandle() == t1) {
				return f.createFileMapping(t2, t3, t4, t5, fileMapName);
			}
		}

		return 0;
	}

	public long setEndOfFile(long t1) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileHandle() == t1) {
				return f.setEndOfFile();
			}
		}

		return 0;
	}

	public long mapViewOfFile(long t1, long t3, long t4, long t5, long x5) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f != null && f.getMapView() != null && f.getMapView().getMapViewHandle() == t1) {
				return f.getFileImage().getBaseAddress() + baseAddress;
			}
		}

		return 0;
	}

	public String readFile(long x1, long x2, long x3, long x4, long x5) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			// String ret = "";
			if (f.getFileHandle() == x1) {
				return f.getContent(x2, x3, x4);
			}
		}

		return null;
	}

	public long createFileMapping(long t1, long t2, long t3, long t4, long t5, long t6) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getFileHandle() == t1) {
				return f.createFileMapping(t2, t3, t4, t5, t6);
			}
		}

		return 0;
	}

	/**
	 * @return the baseAddress
	 */
	public long getBaseAddress() {
		return baseAddress;
	}

	/**
	 * @param baseAddress
	 *            the baseAddress to set
	 */
	public void setBaseAddress(long baseAddress) {
		this.baseAddress = baseAddress;
	}

	public long readByte(int ad) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getStatus()) {
				return f.getFileImage().readByte((int) (ad - baseAddress));
			}
		}

		return 0;
	}

	public long readWord(int ad) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getStatus()) {
				return f.getFileImage().readWord((int) (ad - baseAddress));
			}
		}

		return 0;
	}

	public long readDoubleWord(int ad) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f.getStatus()) {
				return f.getFileImage().readDoubleWord((int) (ad - baseAddress));
			}
		}

		return 0;
	}

	public boolean containFileMappingHandle(long x) {
		// TODO Auto-generated method stub
		for (File f : fList) {
			if (f != null && f.getMapView() != null && f.getMapView().getMapViewHandle() == x) {
				return true;
			}
		}

		return false;
	}

	public long closeFileMappingHandle(long x) {
		// TODO Auto-generated method stub
		return 1;
	}

	public boolean isInsideFile(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		if (addr.getValue() < this.baseAddress) {
			return false;
		}

		for (File f : fList) {
			if (f.getStatus()) {
				if (f.getFileImage().getLength() > (addr.getValue() - f.getFileImage().getBaseAddress() - this.baseAddress)) {
					return true;
				}
			}
		}

		return false;
	}

}
