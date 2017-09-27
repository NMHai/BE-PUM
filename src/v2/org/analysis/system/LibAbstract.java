package v2.org.analysis.system;

import java.util.Map;

import org.jakstab.asm.AbsoluteAddress;

public abstract class LibAbstract {
	public abstract String getLibraryName();
	public abstract long getBaseAddress();
	public abstract long getProcAddress(String functionName);
	public abstract int readByte(int address);
	public abstract long readWord(int index);
	public abstract long readDoubleWord(int index);
	public abstract String getProcName(long value);
	public abstract Map<AbsoluteAddress, String> getExportTable();
}
