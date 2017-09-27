package v2.org.analysis.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jakstab.asm.AbsoluteAddress;

public class Library extends LibAbstract{
	private String libraryName;
	private long baseAddress;
	
	@Override
	public Map<AbsoluteAddress, String> getExportTable() {
		return exportTable;
	}

	private Map<AbsoluteAddress, String> exportTable;

	/**
	 * @return the libraryName
	 */
	@Override
	public String getLibraryName() {
		return libraryName;
	}

	/**
	 * @param libraryName
	 *            the libraryName to set
	 */
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	/**
	 * @return the baseAddress
	 */
	@Override
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

	public Library(String name) {
		libraryName = name;
		baseAddress = (int) (Math.random() * Math.pow(10, 6));
		exportTable = new HashMap<AbsoluteAddress, String>();
		initializeExportTable();
	}

	private void initializeExportTable() {
		// TODO Auto-generated method stub
		if (this.libraryName.equals("mapistub.dll")) {
			exportTable.put(new AbsoluteAddress((long) (baseAddress + Math.random() * Math.pow(10, 2))), "fixmapi");
		}
	}

	public void insertAPI(String api, long address) {
		exportTable.put(new AbsoluteAddress(address), api);
	}

	@Override
	public long getProcAddress(String api) {
		for (Entry<AbsoluteAddress, String> entry : exportTable.entrySet()) {
			if (entry.getValue().toLowerCase().equals(api.toLowerCase())) {
				return entry.getKey().getValue();
			}
		}
		long ret = (long) (getBaseAddress() + Math.random() * getBaseAddress());
		exportTable.put(new AbsoluteAddress(ret), api);

		return ret;
	}

	@Override
	public String getProcName(long addr) {
		for (Entry<AbsoluteAddress, String> entry : exportTable.entrySet()) {
			if (entry.getKey().getValue() == addr) {
				return entry.getValue();
			}
		}

		return "";
	}
	
	@Override
	public String toString() {
		return libraryName;		
	}

	@Override
	public int readByte(int address) {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public long readWord(int index) {
		// TODO Auto-generated method stub
		return Long.MIN_VALUE;
	}

	@Override
	public long readDoubleWord(int index) {
		// TODO Auto-generated method stub
		return Long.MIN_VALUE;
	}
}
