package v2.org.analysis.statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.loader.BinaryParseException;
import org.jakstab.loader.pe.COFF_Header;
import org.jakstab.loader.pe.ExportEntry;
import org.jakstab.loader.pe.ImageDataDirectory;
import org.jakstab.loader.pe.ImageExportDirectory;
import org.jakstab.loader.pe.MSDOS_Stub;
import org.jakstab.loader.pe.PE_Header;
import org.jakstab.loader.pe.SectionHeader;
import org.jakstab.util.BinaryFileInputBuffer;

public class LibraryToJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LibraryToJSON().extractOrdinalName();
	}
	
	public void extractOrdinalName() {
		FileProcess fp = new FileProcess(Program.libraryFile);
		fp.clearContentFile();
		fp.appendFile("[");
		File f = new File(Program.pathLibrary);
		File[] listFile = f.listFiles();

		for (File temp: listFile) {
			outputOrdinalName(temp);
		}
		fp.appendFile("]");
	}

	private void outputOrdinalName(File file) {
		// TODO Auto-generated method stub
		if (file != null) {
			try {
				InputStream inStream = new FileInputStream(file);
				BinaryFileInputBuffer buf = new BinaryFileInputBuffer(inStream);
				try {
					MSDOS_Stub msdos = new MSDOS_Stub(buf);
					buf.seek(msdos.getHeaderAddress());
				} catch (Exception e) {
					System.out.println("MS-DOS executables are not supported.");
					buf.seek(getPEHeaderAddress(buf));
				}

				// Verify PE signature ///////////
				if (!buf.match(PE_Header.PE_TAG)) {
					throw new BinaryParseException("PEModule: Missing PE signature");
				}
				// ////////////////////////////////
				COFF_Header coff = new COFF_Header(buf);
				// long optionalHeaderPos = inBuf.getCurrent();
				// long sectionPos = optionalHeaderPos +
				// coff_header.getSizeOfOptionalHeader();
				long posOptionalHeader = buf.getCurrent();
				PE_Header pe = new PE_Header(buf);
				long posSectionHeader = posOptionalHeader + coff.getSizeOfOptionalHeader();
				// /// Parse Section Headers and sections /////////////////////////
				// if (sectionPos != tempSectionPos)
				buf.seek(posSectionHeader);
				long alignment = pe.getFileAlignment();
				SectionHeader[] section = new SectionHeader[coff.getNumberOfSections()];
				for (int i = 0; i < coff.getNumberOfSections(); i++) {
					section[i] = new SectionHeader(buf, alignment);
				}
				// if (sectionPos != tempSectionPos)
				// inBuf.seek(tempSectionPos);
				// ///////////////////////////////////////////////////////////////

				long expTableRVA = pe.getDataDirectory()[ImageDataDirectory.EXPORT_TABLE_INDEX].VirtualAddress;
				if (expTableRVA > 0) { // We have an export table
//					logger.debug("-- Reading export table...");
					buf.seek(getFilePointerRVA(expTableRVA, section));
					ImageExportDirectory imageExportDirectory = new ImageExportDirectory(buf);

					buf.seek(getFilePointerRVA(imageExportDirectory.AddressOfFunctions, section));
					// Parse EAT
					ExportEntry[] tmpEntries = new ExportEntry[(int) imageExportDirectory.NumberOfFunctions];
					int eatEntries = 0;
					for (int i = 0; i < tmpEntries.length; i++) {
						long rva = buf.readDWORD();
						if (rva > 0) {
							tmpEntries[i] = new ExportEntry((int) (i + imageExportDirectory.Base), new AbsoluteAddress(rva
									+ pe.getImageBase()));
							eatEntries++;
						}
					}

					long namePtr = getFilePointerRVA(imageExportDirectory.AddressOfNames, section);
					long ordPtr = getFilePointerRVA(imageExportDirectory.AddressOfNameOrdinals, section);
					for (int i = 0; i < imageExportDirectory.NumberOfNames; i++) {
						// read next ENT entry
						buf.seek(namePtr);
						long rva = buf.readDWORD();
						namePtr = buf.getCurrent();
						// read export name
						buf.seek(getFilePointerRVA(rva, section));
						String expName = buf.readASCII();
						// read next EOT entry
						buf.seek(ordPtr);
						int ord = buf.readWORD();
						ordPtr = buf.getCurrent();
						tmpEntries[ord].setName(expName);
						outputJson(file.getName(), tmpEntries[ord].getOrdinal(), expName);						
					}
//					exportEntries = new ExportEntry[eatEntries];
//					int j = 0;
//					for (int i = 0; i < tmpEntries.length; i++) {
//						if (tmpEntries[i] != null) {
//							exportEntries[j++] = tmpEntries[i];
//						}
//					}
//					logger.debug("-- Got " + exportEntries.length + " exported symbols.");
				} 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BinaryParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void outputJson(String name, int ordinal, String expName) {
		// TODO Auto-generated method stub
		FileProcess fp = new FileProcess(Program.libraryFile);
		fp.appendFile("{\n\"LIBRARY_NAME\"\t:\"" + name + "\"\n"
				+ "\"ORDINAL\"\t:\"" + ordinal + "\"\n"
				+ "\"FUNCTION_NAME\"\t:\"" + expName + "\"\n"
				+ "},");
	}
	
	private long getFilePointerRVA(long rva, SectionHeader[] section) {
		// TODO Auto-generated method stub
		int sct = getSectionNumberByRVA(rva, section);

		/*
		 * if
		 * (Program.getProgram().getFileName().equals("Flooder.Win32.AngryPing"
		 * )) return (rva - getSectionHeader(sct).VirtualAddress) +
		 * getSectionHeader(sct).PointerToRawData;
		 */
		if (sct < 0) {
//			return -1;
			return rva;
		}

		if (rva - section[sct].VirtualAddress > section[sct].SizeOfRawData) {
//			return -1;
			return rva;
		}
		return (rva - section[sct].VirtualAddress) + section[sct].PointerToRawData;
	}
	
	private long getPEHeaderAddress(BinaryFileInputBuffer inBuf) {
		try {
			inBuf.seek(60);
			return inBuf.readDWORD();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	
	private int getSectionNumberByRVA(long rva, SectionHeader[] section) {
		// TODO Auto-generated method stub
		if (rva < 0) {
			return -1;
		}
		for (int i = 0; i < section.length; i++) {
			if (section[i].VirtualAddress <= rva
					&& (section[i].VirtualAddress + section[i].SizeOfRawData) > rva) {
				return i;
				/*
				 * if (fileName.equals("Flooder.Win32.AngryPing")) { for (int i
				 * = getNumberOfSections() - 1; i >=0; i--) if
				 * (getSectionHeader(i).VirtualAddress <= rva) return i; }
				 */
			}
		}

		return -1;
	}
}
