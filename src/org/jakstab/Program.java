/*
 * Program.java - This file is part of the Jakstab project.
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
package org.jakstab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import org.analysis.complement.CFGState;
import org.analysis.complement.PMLocation;
import org.analysis.complement.PMState;
import org.jakstab.analysis.ReachedSet;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.BranchInstruction;
import org.jakstab.asm.DummySymbolFinder;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.MemoryOperand;
import org.jakstab.asm.Operand;
import org.jakstab.asm.SymbolFinder;
import org.jakstab.asm.x86.X86CallInstruction;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jakstab.asm.x86.X86RetInstruction;
import org.jakstab.cfa.CFAEdge;
import org.jakstab.cfa.Location;
import org.jakstab.disasm.Disassembler;
import org.jakstab.disasm.DisassemblyException;
import org.jakstab.disasm.x86.InstructionDecoder;
import org.jakstab.disasm.x86.X86Disassembler;
import org.jakstab.loader.BinaryParseException;
import org.jakstab.loader.ExecutableImage;
import org.jakstab.loader.ExportedSymbol;
import org.jakstab.loader.Harness;
import org.jakstab.loader.LinuxStubLibrary;
import org.jakstab.loader.RawModule;
import org.jakstab.loader.StubProvider;
import org.jakstab.loader.UnresolvedSymbol;
import org.jakstab.loader.Win32StubLibrary;
import org.jakstab.loader.elf.ELFModule;
import org.jakstab.loader.pe.AbstractCOFFModule;
import org.jakstab.loader.pe.COFF_Header;
import org.jakstab.loader.pe.ExportEntry;
import org.jakstab.loader.pe.ImageDataDirectory;
import org.jakstab.loader.pe.ImageExportDirectory;
import org.jakstab.loader.pe.MSDOS_Stub;
import org.jakstab.loader.pe.ObjectFile;
import org.jakstab.loader.pe.PEModule;
import org.jakstab.loader.pe.PE_Header;
import org.jakstab.loader.pe.SectionHeader;
import org.jakstab.rtl.expressions.SetOfVariables;
import org.jakstab.rtl.statements.RTLHalt;
import org.jakstab.rtl.statements.RTLStatement;
import org.jakstab.rtl.statements.StatementSequence;
import org.jakstab.ssl.Architecture;
import org.jakstab.util.BinaryFileInputBuffer;
import org.jakstab.util.FastSet;
import org.jakstab.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.json.JSONObj;
import v2.org.analysis.path.BPState;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.statistics.Logging;
import v2.org.analysis.structure.TripleList;

/**
 * There is one singleton Program object for all modules currently under
 * analysis. It stores all non-analysis information about the analyzed programs,
 * including statements, the current control flow graph, and symbols.
 *
 * @author Johannes Kinder
 */
public final class Program {

	public enum TargetOS {
		WINDOWS, LINUX, UNKNOWN
	}

	// private final static Logger logger = Logger.getLogger(Program.class);
	private final static Logging logger = Logging.getLogger(Program.class);

	private static Program programInstance;

	final static String resultFileTXT = "data/data/Result.txt";

	final static String resultFileTempTXT = "data/data/Result_Temp.txt";
	final static String logFileTXT = "data/data/logResult.txt";
	final static String oepFileTXT = "data/data/OEPAddr.txt";
	// final static String packerResultFileTXT = "data/data/packerResult.txt";
	// final static String packerResultCountFileTXT =
	// "data/data/packerResultCount.txt";
	final static String stopFileTXT = "data/data/stopFile.txt";
	static final int MAX_BYTE_PER_INSTRUCTION = 15;
	public static final String pathLibrary = "data/lib/win32/";
	public static final String libraryFile = "data/data/lib.json";

	/**
	 * Initially creates the Program object.
	 *
	 * @param arch
	 *            An Architecture object with architecture specific information
	 * @return the new singleton instance of the Program class
	 */
	public static Program createProgram(Architecture arch) {
		// assert programInstance == null;
		programInstance = new Program(arch);
		return programInstance;
	}

	/**
	 * Get the singleton Program object.
	 *
	 * @return the singleton instance of the Program class
	 */
	public synchronized static Program getProgram() {
		return programInstance;
	}

	private String technique = "";
	private String fileName = "";
	private String detail_technique = "";
	private String absolutePath = "";
	private String hashFile = "";

	private ReachedSet reached = null;

	private final Architecture arch;
	private Location start;
	private Map<Location, RTLStatement> statementMap;
	private Map<PMLocation, RTLStatement> statementMap1;
	private Map<AbsoluteAddress, List<AbsoluteAddress>> preservedExecutionMap;
	private Map<AbsoluteAddress, Instruction> assemblyMap;
	private List<PMState> pmState;

	private BPCFG bpCFG;

	private List<AbsoluteAddress> smPos;

	private ExecutableImage mainModule;

	private List<ExecutableImage> modules;
	private Set<CFGState> cfg;
	private Set<CFAEdge> cfa;
	private final Map<String, ExportedSymbol> exportedSymbols;
	private final Set<UnresolvedSymbol> unresolvedSymbols;
	private Set<Location> unresolvedBranches;
	private StubProvider stubLibrary;
	private Harness harness;
	Disassembler disassembler;
	private FileProcess resultFile, logResultFile, resultFileTemp, logFile, stopFile, oepFile;
	// private FileProcess packerResultFile, packerResultCountFile;
	private TargetOS targetOS;
	private Instruction analyzedInstruction = null;
	private long analyzingTime;

	;

	// PHONG - 20150724
	// private PackerDetection pDetection;

	private Program(Architecture arch) {
		this.arch = arch;
		this.targetOS = TargetOS.UNKNOWN;

		modules = new LinkedList<>();
		assemblyMap = new TreeMap<>();
		pmState = new ArrayList<>();
		preservedExecutionMap = new TreeMap<>();
		// this.preservedExecutionMap.
		statementMap = new HashMap<>(2000);
		statementMap1 = new HashMap<>(2000);
		cfa = new FastSet<>();
		cfg = new FastSet<>();
		exportedSymbols = new HashMap<>();
		unresolvedSymbols = new FastSet<>();
		unresolvedBranches = new FastSet<>();
		smPos = new ArrayList<>();
		bpCFG = new BPCFG();

		setResultFile(new FileProcess(resultFileTXT));
		setStopFile(new FileProcess(stopFileTXT));
		setLogResultFile(new FileProcess(logFileTXT));
		setResultFileTemp(new FileProcess(resultFileTempTXT));
		setOEPFileTemp(new FileProcess(oepFileTXT));

		// setPackerResultFile(new FileProcess(packerResultFileTXT));
		// this.packerResultFile.appendFile("");
		// setPackerResultCountFile(new FileProcess(packerResultCountFileTXT));
		// this.packerResultCountFile.appendFile("");
	}

	public void setOEPFileTemp(FileProcess file) {
		// TODO Auto-generated method stub
		this.oepFile = file;
	}

	public void addByteSMPos(AbsoluteAddress addr) {
		this.smPos.add(addr);
		this.smPos.add(new AbsoluteAddress(addr.getValue() + 1));
		this.smPos.add(new AbsoluteAddress(addr.getValue() + 2));
		this.smPos.add(new AbsoluteAddress(addr.getValue() + 3));
		this.smPos.add(new AbsoluteAddress(addr.getValue() + 4));
		this.smPos.add(new AbsoluteAddress(addr.getValue() + 5));

		this.smPos.add(new AbsoluteAddress(addr.getValue() - 1));
		this.smPos.add(new AbsoluteAddress(addr.getValue() - 2));
		this.smPos.add(new AbsoluteAddress(addr.getValue() - 3));
		this.smPos.add(new AbsoluteAddress(addr.getValue() - 4));
		this.smPos.add(new AbsoluteAddress(addr.getValue() - 5));
	}

	/*
	 * private void addDoubleWordSMPos(AbsoluteAddress addr) { // TODO
	 * Auto-generated method stub this.smPos.add(addr); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 1)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 2)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 3)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 4)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 5)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 6)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 7)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 8)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 9)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 10));
	 * 
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 1));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 2));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 3));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 4));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 6));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 7));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 8));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 9));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 10));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 5)); }
	 */
	private void addPMState(AbsoluteAddress addr, Instruction instr) {
		PMState temp = new PMState(addr, instr);
		for (PMState s : this.pmState) {
			if (s.compare(temp)) {
				return;
			}
		}

		this.pmState.add(temp);
	}

	/*
	 * private void addWordSMPos(AbsoluteAddress addr) { // TODO Auto-generated
	 * method stub this.smPos.add(addr); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 1)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 2)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 3)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 4)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 5)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 6)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 7)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 8)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 9)); this.smPos.add(new
	 * AbsoluteAddress(addr.getValue() + 10));
	 * 
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 1));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 2));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 3));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 4));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 6));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 7));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 8));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 9));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 10));
	 * this.smPos.add(new AbsoluteAddress(addr.getValue() - 5)); }
	 */
	public void backupState(BPState curState) {
		// TODO Auto-generated method stub
		generageCFG("/asm/cfg/" + getFileName() + "_test");
//		getLogResultFile().appendFile(getFileName() + "\n" + "Location: " + curState.getLocation() + "\n"
//				+ curState.getEnvironement().getRegister().toString() + "\n"
//				+ curState.getEnvironement().getFlag().toString());
		// //////////////////////////////////////////////////
		// Write to packer result file after each 60s
		// if (PackerManager.getInstance().isCheckDetectPacker()) {
		// PackerManager.getInstance().outputToFile(getFileName());
		// }
	}

	// Check address inside file area
	public boolean checkAddress(AbsoluteAddress address) {
		return mainModule.insideFileArea(address);
	}

	public String checkAPI(long value, Environment env) {
		// TODO Auto-generated method stub
		String api = "";
		if (stubLibrary != null && stubLibrary instanceof Win32StubLibrary) {
			api = ((Win32StubLibrary) stubLibrary).getAPIName(value);
		}

		if (api == "") {
			api = env.getSystem().getLibraryHandle().getAPIName(value);
		}
		return api;
	}

	/*
	 * private boolean checkSEH(Instruction instr) { // TODO Auto-generated
	 * method stub if (instr.getOperandCount() >= 2) { Operand dest =
	 * instr.getOperand(0); Operand src = instr.getOperand(1);
	 * 
	 * return (dest.toString().contains("fs:") &&
	 * src.toString().contains("esp")); }
	 * 
	 * return false; }
	 */

	/*
	 * private boolean checkSelfModify(AbsoluteAddress address, Instruction
	 * instr) { // TODO Auto-generated method stub ExecutableImage module =
	 * getModule(address);
	 * 
	 * 
	 * if (address.toString().equals("0x0040d82a") ||
	 * address.toString().equals("0x0040d830") ||
	 * address.toString().equals("0x0040d85a") ||
	 * address.toString().equals("0x0040d839") ||
	 * address.toString().equals("0x0040d86c") ||
	 * address.toString().equals("0x004047df") ) {
	 * System.out.println("Debug Self Modify Code: " + address.toString()) ; }
	 * 
	 * 
	 * if (!address.toString().equals("0x00404819") &&
	 * this.getFileName().equals("Virus.Win32.Cabanas.2999")) { return false; }
	 * 
	 * if (instr instanceof X86CondJmpInstruction) { if
	 * (instr.getName().startsWith("loop")) {
	 * System.out.println("Debug Decryption Method:" + address.toString());
	 * String decryptionString = ""; String curAPI = ""; if
	 * (address.toString().equals("0x00404819") &&
	 * this.getFileName().equals("Virus.Win32.Cabanas.2999")) {
	 * System.out.println("Debug Special Decryption Method " +
	 * address.toString()); long ecx = getRegVal(address, "ecx"); long esi =
	 * getRegVal(address, "esi"); long edi = getRegVal(address, "edi");
	 * 
	 * while (ecx > 0) { int al = (int) getByteValueMemory(new
	 * AbsoluteAddress(esi)); esi++;
	 * 
	 * int cl = (int) (ecx & 0xFF); // al = Integer.rotateLeft((int) al,
	 * (int)cl) & 0xFF; al = (int) OldBitVector.rl8(al, cl); al = al ^ 0xB5;
	 * long fp1 = module.getFilePointer(new AbsoluteAddress(edi)); if (fp1 >= 0)
	 * { module.getDisassembler().setMemoryByteValue((int) fp1, al); } else if
	 * (al > 177) { al += 128; al = al & 0xFF; String temp = (char) al + "File";
	 * 
	 * decryptionString += temp; curAPI += temp; edi += 5; ecx--; } else
	 * 
	 * if (al == 238) { decryptionString += "nFile"; curAPI += "nFile"; edi +=
	 * 5; ecx--; } else if (al == 249) { decryptionString += "yFile"; curAPI +=
	 * "yFile"; edi += 5; ecx--; } else if (al == 244) { decryptionString +=
	 * "tFile"; curAPI += "tFile"; edi += 5; ecx--; } else if (al == 229) {
	 * decryptionString += "eFile"; curAPI += "eFile"; edi += 5; ecx--; } else
	 * 
	 * if (al != 128) { decryptionString += (char) al; if (al == 0) { curAPI =
	 * ""; } else { curAPI += (char) al; }
	 * 
	 * edi++; ecx--; } else { // char t = (char) //
	 * (()curAPI.charAt(curAPI.length()-1) - 0xEA); decryptionString += (char) 0
	 * + curAPI.substring(0, curAPI.length() - 1) + "W" + (char) 0; edi +=
	 * curAPI.length() + 2; ecx--; esi++; curAPI = ""; } this.addByteSMPos(new
	 * AbsoluteAddress(esi));
	 * 
	 * if (decryptionString.contains("GetProcAddress")) { //
	 * System.out.println("Debug") ; } } }
	 * 
	 * System.out.println("Decryption String:" + decryptionString); } } else if
	 * (instr instanceof X86MoveInstruction) { if
	 * (instr.getName().startsWith("mov")) { Operand dest =
	 * ((X86MoveInstruction) instr).getOperand1(); Operand src =
	 * ((X86MoveInstruction) instr).getOperand2();
	 * 
	 * if (!dest.getClass().getSimpleName().equals("X86MemoryOperand")) { return
	 * false; }
	 * 
	 * if (dest.toString().startsWith("%fs")) { return false; }
	 * 
	 * System.out.println("Debug Self Modify Code: " + address.toString());
	 * 
	 * long pos = getOperandValue(address, instr, dest);
	 * 
	 * // if (module.isCodeArea(new AbsoluteAddress(pos))) { long value =
	 * getOperandValue(address, instr, src); long fp1 =
	 * module.getFilePointer(new AbsoluteAddress(pos)); if
	 * (instr.getName().endsWith("b")) {
	 * module.getDisassembler().setMemoryByteValue((int) fp1, value);
	 * this.addByteSMPos(new AbsoluteAddress(pos)); } else if
	 * (instr.getName().endsWith("w") || instr.getName().endsWith("s")) {
	 * module.getDisassembler().setMemoryWordValue((int) fp1, value);
	 * this.addWordSMPos(new AbsoluteAddress(pos)); } else if
	 * (instr.getName().endsWith("l")) {
	 * 
	 * module.getDisassembler().setMemoryDoubleWordValue((int) fp1, value);
	 * this.addDoubleWordSMPos(new AbsoluteAddress(pos)); }
	 * 
	 * return true; // } }
	 * 
	 * } else if (instr instanceof X86ArithmeticInstruction) { if
	 * (instr.getName().startsWith("inc")) { Operand dest =
	 * ((X86ArithmeticInstruction) instr).getOperand1(); // Operand src =
	 * ((X86MoveInstruction)instr).getOperand2();
	 * 
	 * if (!dest.getClass().getSimpleName().equals("X86MemoryOperand")) { return
	 * false; }
	 * 
	 * System.out.println("Debug Self Modify Code: " + address.toString());
	 * 
	 * long pos = getOperandValue(address, instr, dest);
	 * 
	 * // if (module.isCodeArea(new AbsoluteAddress(pos))) { long value = 1;
	 * long fp1 = module.getFilePointer(new AbsoluteAddress(pos)); if
	 * (instr.getName().endsWith("b")) {
	 * module.getDisassembler().addMemoryByteValue((int) fp1, value);
	 * this.addByteSMPos(new AbsoluteAddress(pos)); }
	 * 
	 * return true; // } } else if (instr.getName().startsWith("add")) { //
	 * instr.get Operand dest = ((X86ArithmeticInstruction)
	 * instr).getOperand1(); Operand src = ((X86ArithmeticInstruction)
	 * instr).getOperand2();
	 * 
	 * if (!dest.getClass().getSimpleName().equals("X86MemoryOperand")) { return
	 * false; }
	 * 
	 * System.out.println("Debug Self Modify Code: " + address.toString());
	 * 
	 * long pos = getOperandValue(address, instr, dest);
	 * 
	 * // if (module.isCodeArea(new AbsoluteAddress(pos))) { long value =
	 * getOperandValue(address, instr, src); long fp1 =
	 * module.getFilePointer(new AbsoluteAddress(pos)); if
	 * (instr.getName().endsWith("b")) {
	 * module.getDisassembler().addMemoryByteValue((int) fp1, value);
	 * this.addByteSMPos(new AbsoluteAddress(pos)); } else if
	 * (instr.getName().endsWith("l")) {
	 * module.getDisassembler().addMemoryDoubleWordValue((int) fp1, value);
	 * this.addDoubleWordSMPos(new AbsoluteAddress(pos)); } else if
	 * (instr.getName().endsWith("s") || instr.getName().endsWith("w")) {
	 * module.getDisassembler().addMemoryWordValue((int) fp1, value);
	 * this.addDoubleWordSMPos(new AbsoluteAddress(pos)); }
	 * 
	 * return true; // } }
	 * 
	 * } else if (instr instanceof X86Instruction) { if
	 * (instr.getName().startsWith("movs")) { Operand dest =
	 * instr.getOperand(0); Operand src = instr.getOperand(1);
	 * 
	 * if (Program.getProgram().getFileName().toString().equals(
	 * "Flooder.Win32.AngryPing")) { return false; }
	 * 
	 * if (Program.getProgram().getFileName().toString().equals(
	 * "Virus.Win32.Cabanas.2999")) { return false; }
	 * 
	 * // System.out.println("Debug SMC MOVS:" + address.toString());
	 * System.out.println("Debug Self Modify Code: " + address.toString());
	 * 
	 * if (!dest.getClass().getSimpleName().equals("X86MemoryOperand") ||
	 * !src.getClass().getSimpleName().equals("X86MemoryOperand")) { return
	 * false; }
	 * 
	 * long pos1 = getRegVal(address, "edi"); long pos2 = getRegVal(address,
	 * "esi");
	 * 
	 * if (((X86Instruction) instr).hasPrefixREPZ()) { long ecx =
	 * getRegVal(address, "ecx");
	 * 
	 * for (long i = ecx; i > 0; i++) { if (instr.getName().endsWith("b")) {
	 * 
	 * } else if (instr.getName().endsWith("l")) { long value =
	 * this.getDoubleWordValueMemory(new AbsoluteAddress(pos2)); // long value2
	 * = this.get32Value(new // AbsoluteAddress(pos1)); long fp1 =
	 * module.getFilePointer(new AbsoluteAddress(pos1)); // long fp2 =
	 * module.getFilePointer(new // AbsoluteAddress(pos2)); pos2 += 4;
	 * module.getDisassembler().setMemoryDoubleWordValue((int) fp1, value); pos1
	 * += 4; } else if (instr.getName().endsWith("s") ||
	 * instr.getName().endsWith("w")) {
	 * 
	 * } } }
	 * 
	 * } else if (instr.getName().startsWith("stos")) { Operand dest =
	 * instr.getOperand(0); Operand src = instr.getOperand(1);
	 * 
	 * if (Program.getProgram().getFileName().toString().equals(
	 * "Virus.Win32.Seppuku.1606") && address.toString().equals("0x0040106c")) {
	 * return false; }
	 * 
	 * if (address.toString().equals("0x004013ca")) {
	 * System.out.println("Debug"); }
	 * 
	 * if (!dest.getClass().getSimpleName().equals("X86MemoryOperand")) { return
	 * false; }
	 * 
	 * System.out.println("Debug Self Modify Code: " + address.toString());
	 * 
	 * long pos = getOperandValue(address, instr, dest);
	 * 
	 * // if (module.isCodeArea(new AbsoluteAddress(pos))) { long value =
	 * getOperandValue(address, instr, src); long fp1 =
	 * module.getFilePointer(new AbsoluteAddress(pos)); if
	 * (instr.getName().endsWith("b")) {
	 * module.getDisassembler().setMemoryByteValue((int) fp1, value);
	 * this.addByteSMPos(new AbsoluteAddress(pos)); } else if
	 * (instr.getName().endsWith("l")) {
	 * module.getDisassembler().setMemoryDoubleWordValue((int) fp1, value);
	 * this.addDoubleWordSMPos(new AbsoluteAddress(pos)); } else if
	 * (instr.getName().endsWith("s") || instr.getName().endsWith("w")) {
	 * module.getDisassembler().setMemoryWordValue((int) fp1, value);
	 * this.addWordSMPos(new AbsoluteAddress(pos)); }
	 * 
	 * return true; // } } }
	 * 
	 * if (checkSelfModify(address, instr)) {
	 * System.out.println("Self-modifying Code"); long value = 12; long pos =
	 * 4198409; long fp1 = module.getFilePointer(new AbsoluteAddress(pos));
	 * module.getDisassembler().setMemoryByteValue((int) fp1, value); }
	 * 
	 * return false; }
	 */
	public boolean checkSMPos(AbsoluteAddress addr) {
		for (AbsoluteAddress a : this.smPos) {
			if (a.getValue() == addr.getValue()) {
				return true;
			}
		}

		return false;
	}

	public Iterator<AbsoluteAddress> codeAddressIterator() {
		return getMainModule().codeBytesIterator();
	}

	public boolean containsLabel(Location label) {
		return statementMap.containsKey(label);
	}

	public int countIndirectBranches() {
		int res = 0;
		for (Map.Entry<AbsoluteAddress, Instruction> entry : assemblyMap.entrySet()) {
			Instruction instr = entry.getValue();

			if (instr instanceof BranchInstruction) {
				BranchInstruction branch = (BranchInstruction) instr;
				if (branch.isIndirect()) {
					// if branch target is not a memory operand pointing into a
					// static data area of the binary (imports)
					if (branch.getBranchDestination() instanceof MemoryOperand) {
						MemoryOperand memOp = (MemoryOperand) branch.getBranchDestination();
						// Import calls have only displacement
						if (memOp.getBase() == null && memOp.getIndex() == null) {
							AbsoluteAddress disp = new AbsoluteAddress(memOp.getDisplacement());
							// Check whether displacement points into import
							// table
							ExecutableImage module = getModule(disp);
							if (module instanceof PEModule && ((PEModule) module).getImportTable().containsKey(disp)) {
								continue;
							}
						}
					}
					res++;
					// logger.verbose(entry.getKey() + "\t" +
					// getInstructionString(entry.getKey()));
				}
			}
		}
		return res;
	}

	private String extractOrdinalName(File file, long ordinal) {
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
				// /// Parse Section Headers and sections
				// /////////////////////////
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
					logger.debug("-- Reading export table...");
					buf.seek(getFilePointerRVA(expTableRVA, section));
					ImageExportDirectory imageExportDirectory = new ImageExportDirectory(buf);

					buf.seek(getFilePointerRVA(imageExportDirectory.AddressOfFunctions, section));
					// Parse EAT
					ExportEntry[] tmpEntries = new ExportEntry[(int) imageExportDirectory.NumberOfFunctions];
					int eatEntries = 0;
					for (int i = 0; i < tmpEntries.length; i++) {
						long rva = buf.readDWORD();
						if (rva > 0) {
							tmpEntries[i] = new ExportEntry((int) (i + imageExportDirectory.Base),
									new AbsoluteAddress(rva + pe.getImageBase()));
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
						if (tmpEntries[ord].getOrdinal() == ordinal) {
							return expName;
						}
					}
					// exportEntries = new ExportEntry[eatEntries];
					// int j = 0;
					// for (int i = 0; i < tmpEntries.length; i++) {
					// if (tmpEntries[i] != null) {
					// exportEntries[j++] = tmpEntries[i];
					// }
					// }
					// logger.debug("-- Got " + exportEntries.length +
					// " exported symbols.");
				} else {
					logger.debug("-- File contains no exports");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BinaryParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void generageCFG(String baseFileName) {
		// TODO Auto-generated method stub
		ProgramGraphWriter graphWriter = new ProgramGraphWriter(this);
		graphWriter.writeDisassembly(getBPCFG(), generatePathFileName(baseFileName) + "_code.asm");
		graphWriter.writeAssemblyCFG(getBPCFG(), generatePathFileName(baseFileName) + "_model");
	}

	private String generateHashFile(String hash) {
		// TODO Auto-generated method stub
		try {
			MessageDigest md = MessageDigest.getInstance(hash);
			md.update(Files.readAllBytes(Paths.get(getAbsolutePathFile())));
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toLowerCase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public String generatePathFileName(String baseFileName) {
		// TODO Auto-generated method stub
		String ret = baseFileName;

		// Get the file name in input path to save in the same folder of BE-PUM
		// jar file.
		if (!Program.class.getResource("Program.class").toString().startsWith("file")) {
			int index = baseFileName.lastIndexOf('/');
			if (index == -1) {
				index = baseFileName.lastIndexOf('\\');
			}
			if (index > -1) {
				ret = baseFileName.substring(index, baseFileName.length());
			}
		}

		return System.getProperty("user.dir") + ret;
	}

	/**
	 * @return the absolutePath
	 */
	public String getAbsolutePathFile() {
		return absolutePath;
	}

	public Instruction getAnalyzedInstruction() {
		return analyzedInstruction;
	}

	public long GetAnalyzingTime() {
		return this.analyzingTime;
	}

	public Architecture getArchitecture() {
		return arch;
	}

	/**
	 * @return the assemblyMap
	 */
	public final Map<AbsoluteAddress, Instruction> getAssemblyMap() {
		return assemblyMap;
	}

	public BPCFG getBPCFG() {
		// TODO Auto-generated method stub
		return bpCFG;
	}

	public int getByteIndex(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		long value = address.getValue();

		// return (int)(value - 4194304) / 8;
		return (int) (value) / 8;
		// return 514;
	}

	public final long getByteValueMemory(AbsoluteAddress address) {
		if (insideHeader(address.getValue())) {
			if (mainModule instanceof PEModule) {
				return (int) ((PEModule) mainModule).getByteValue(address.getValue());
			}
		}

		long result = 0;
		try {
			if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
				return 0;
			}

			ExecutableImage module = getModule(address);
			long fp = -1;
			if (module == null) {
				result = 0;
			} else {
				fp = module.getFilePointer(address);

				X86Disassembler dis = (X86Disassembler) module.getDisassembler();
				int byteIndex = (int) fp;
				result = InstructionDecoder.readByte(dis.getCode(), byteIndex);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	// PHONG: debug here
	public final long getByteValueMemoryPhong(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
				return 0;
			}

			ExecutableImage module = getModule(address);

			long fp = -1;
			if (module == null) {
				// logger.error("No module for address " + address
				// + ". Cannot disassemble instruction!")
				;
				// PHONG: 20150501 ------------------------------------------
				if (mainModule instanceof PEModule) {
					if (((PEModule) mainModule).isInsideHeader(address.getValue())) {
						result = (int) ((PEModule) mainModule).getByteValue(address.getValue());
					}
				}
				// ------------------------------------------------------------
			} else {
				fp = module.getFilePointer(address);
				// Also check whether fp is out of the int range, since the
				// X86Disassembler actually
				// performs this cast in its implementation.
				if (fp < 0 || (int) fp < 0) {
					logger.error("Requested instruction outside of file area: " + address);
				}
				// else {
				// if (!module.isCodeArea(address)) {
				// logger.error("Requested instruction outside code section: "
				// + address);
				// return 0;
				// }
				// Disassembler dis = module.getDisassembler();
				// instr =
				X86Disassembler dis = (X86Disassembler) module.getDisassembler();
				int byteIndex = (int) fp;
				result = InstructionDecoder.readByte(dis.getCode(), byteIndex);
				/*
				 * if (instr == null) {
				 * logger.error("Instruction could not be disassembled at: " +
				 * address); }
				 */
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	public Set<CFAEdge> getCFA() {
		return Collections.unmodifiableSet(cfa);
	}

	public Set<CFGState> getCFGState() {
		return cfg;
	}

	public String getDetailTechnique() {
		return detail_technique;
	}

	public final int getDoubleWordValueMemory(AbsoluteAddress address) {
		int result = 0;

		try {
			if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
				return 0;
			}

			ExecutableImage module = getModule(address);
			long fp = -1;
			if (module == null) {
				// logger.error("No module for address " + address
				// + ". Cannot disassemble instruction!");
				if (mainModule instanceof PEModule) {
					if (((PEModule) mainModule).isInsideHeader(address.getValue())) {
						result = (int) ((PEModule) mainModule).getDoubleWordValue(address.getValue());
					}
				}
				//
			} else {
				fp = module.getFilePointer(address);
				// Also check whether fp is out of the int range, since the
				// X86Disassembler actually
				// performs this cast in its implementation.
				if (fp < 0 || (int) fp < 0) {
					logger.error("Requested instruction outside of file area " + address);
				}
				// else {
				// if (!module.isCodeArea(address)) {
				// logger.error("Requested instruction outside code section: "
				// + address);
				// return 0;
				// }
				// Disassembler dis = module.getDisassembler();
				// instr =
				X86Disassembler dis = (X86Disassembler) module.getDisassembler();
				int byteIndex = (int) fp;
				result = InstructionDecoder.readInt32(dis.getCode(), byteIndex);
				/*
				 * if (instr == null) {
				 * logger.error("Instruction could not be disassembled at: " +
				 * address); }
				 */
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public final int getEdgesCount() {
		int num = 0;
		// Set<CFAEdge> edges = new HashSet<CFAEdge>();
		// Set<Location> nodes = new HashSet<Location>();
		for (CFAEdge e : getCFA()) {
			AbsoluteAddress sourceAddr = e.getSource().getAddress();
			AbsoluteAddress targetAddr = e.getTarget().getAddress();
			if (!sourceAddr.equals(targetAddr)) {
				num++;
			}
		}
		return num;
	}

	public final int getEdgesCount1() {
		int num = 0;
		// Set<CFAEdge> edges = new HashSet<CFAEdge>();
		// Set<Location> nodes = new HashSet<Location>();
		for (CFGState e : this.cfg) {
			AbsoluteAddress sourceAddr = e.getSource().getAddress();
			AbsoluteAddress targetAddr = e.getTarget().getAddress();
			if (!sourceAddr.equals(targetAddr)) {
				num++;
			}
		}
		return num;
	}

	public AbsoluteAddress getEntryPoint() {
		// TODO Auto-generated method stub
		long v = specialCase();
		if (v != 0) {
			return new AbsoluteAddress(v);
		}

		return this.mainModule.getEntryPoint();
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return fileName;
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
			// return -1;
			return rva;
		}

		if (rva - section[sct].VirtualAddress > section[sct].SizeOfRawData) {
			// return -1;
			return rva;
		}
		return (rva - section[sct].VirtualAddress) + section[sct].PointerToRawData;
	}

	public Harness getHarness() {
		return harness;
	}

	public String getHashFile() {
		// TODO Auto-generated method stub
		if (hashFile == "") {
			hashFile = generateHashFile("MD5");
		}

		return hashFile;
	}

	public long getImageBase() {
		/*
		 * long retVal;
		 * 
		 * retVal = ((PEModule) modules.get(0)).getImageBase();
		 */
		if (mainModule instanceof PEModule) {
			return ((PEModule) mainModule).getImageBase();
		}

		return 0;
	}

	public long getImageSize() {
		if (mainModule instanceof PEModule) {
			return ((PEModule) mainModule).getImageSize();
		}

		return 0;
	}

	public final Instruction getInstruction(AbsoluteAddress address) {
		// Instruction instr = assemblyMap.get(address);
		Instruction instr = null;

		if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
			return null;
		}

		ExecutableImage module = getModule(address);

		long fp = -1;
		if (module == null) {
			logger.error("Instruction is Null. No module for address " + address + ". Cannot disassemble instruction!");
		} else {
			fp = module.getFilePointer(address);
			if (fp < 0 || (int) fp < 0) {
				logger.error("Requested instruction outside of file area: " + address);
			} else {
				instr = module.getDisassembler().decodeInstruction(fp);
				if (instr == null) {
					logger.error("Instruction could not be disassembled at: " + address);
				}
			}
		}

		if (instr != null) {
			putInstruction(address, instr);

		}

		return instr;
	}

	public final Instruction getInstruction(AbsoluteAddress address, Environment env) {
		Instruction instr = null;

		byte[] opcodes = getOpcode(address, env, MAX_BYTE_PER_INSTRUCTION);
		if (opcodes != null) {
			instr = getInstruction(opcodes, env);
		}

		if (instr == null) {
			logger.error("Instr is Null. No module for address " + address + ". Cannot disassemble instruction!");
		}

		/*
		 * MemoryV2 m = env.getMemory(); if (harness.contains(address) ||
		 * address.getValue() >= StubProvider.STUB_BASE) { return null; }
		 * 
		 * ExecutableImage module = getModule(address);
		 * 
		 * long fp = -1; if (module == null) { byte[] opcodes =
		 * getOpcode(address, env, MAX_BYTE_PER_INSTRUCTION); if (opcodes !=
		 * null) { instr = getInstruction(opcodes, env); }
		 * 
		 * if (instr == null) {
		 * logger.error("Instr is Null. No module for address " + address +
		 * ". Cannot disassemble instruction!"); }
		 * 
		 * } else { fp = module.getFilePointer(address); if (fp < 0 || (int) fp
		 * < 0) { logger.error("Requested instruction outside of file area: " +
		 * address); } else { byte[] opcodes = getOpcode(address, env,
		 * MAX_BYTE_PER_INSTRUCTION); if (opcodes != null) { instr =
		 * getInstruction(opcodes, env); }
		 * 
		 * if (instr == null) { // m.changeValue(address); // instr =
		 * module.getDisassembler().decodeInstruction(fp); //
		 * m.resetValue(address, instr); // if (instr != null) { // FileProcess
		 * fileProcess = new FileProcess("data/data/error.txt"); //
		 * fileProcess.appendFile("FileName:" + fileName +
		 * " decode Instruction Error at " + address); // }
		 * generageCFG("/asm/cfg/" + getFileName() + "_test");
		 * getLogResultFile().appendFile (getFileName() +
		 * ": Instruction could not be disassembled at: " + address);
		 * logger.error("Instruction could not be disassembled at: " + address);
		 * } } }
		 */
		return instr;
	}

	// PHONG: get instruction here
	public synchronized Instruction getInstruction(byte[] opcodes, Environment env) {
		Instruction instr = null;
		BinaryFileInputBuffer binBuff = new BinaryFileInputBuffer(opcodes);
		X86Disassembler dis = new X86Disassembler(binBuff);
		instr = dis.decodeInstruction();
		return instr;
	}

	public final int getInstructionCount() {
		return assemblyMap.size();
	}

	public synchronized final Instruction getInstructionResult(AbsoluteAddress address) {
		Instruction instr = assemblyMap.get(address);
		// Instruction instr = null;
		if (instr != null) {
			return instr;
		} else {
			// No real instructions in prologue/epilogue
			if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
				return null;
			}

			ExecutableImage module = getModule(address);

			long fp = -1;
			if (module == null) {
				;
			} else {
				fp = module.getFilePointer(address);
				if (fp < 0 || (int) fp < 0) {
					logger.error("Requested instruction outside of file area: " + address);
				} else {
					if (!module.isCodeArea(address)) {
						logger.error("Requested instruction outside code section: " + address);
						return null;
					}

					instr = module.getDisassembler().decodeInstruction(fp);
					if (instr == null) {
						logger.error("Instruction could not be disassembled at: " + address);
					}
				}
			}

			if (instr != null) {
				putInstruction(address, instr);
			}
			return instr;
		}
	}

	/**
	 * Get the string representation of the assembly instruction at the given
	 * address.
	 *
	 * @param addr
	 *            a virtual address
	 * @param env
	 * @return a string representation of the assembly code at the given address
	 */
	public String getInstructionString(AbsoluteAddress addr, Environment env) {
		Instruction instr = getInstruction(addr, env);
		if (instr == null) {
			return "NON_EXISTENT";
		}
		return instr.toString(addr.getValue(), symbolFinder(addr));
	}

	public Logging getLog() {
		return logger;
	}

	/**
	 * @return the fullResultFile
	 */
	public FileProcess getLogResultFile() {
		return logResultFile;
	}
	
	/**
	 * @return the OEPFile
	 */
	public FileProcess getOEPFile() {
		return oepFile;
	}

	/**
	 * Get the main module.
	 *
	 * @return the main module
	 */
	public ExecutableImage getMainModule() {
		return mainModule;
	}

	/**
	 * Get the module that contains the specified virtual address at runtime.
	 *
	 * @param a
	 *            a virtual address
	 * @return the module to which the given virtual address belongs.
	 */
	public ExecutableImage getModule(AbsoluteAddress a) {
		for (ExecutableImage module : modules) {
			if (module.getFilePointer(a) >= 0) {
				return module;
			}
		}
		return null;
	}
	
	@Deprecated
	private String getNameFromOrdinalNumber_Old(String libraryFileName, long ord) {
		// TODO Auto-generated method stub
		File f = new File(Program.pathLibrary);
		final String t = libraryFileName.toLowerCase();
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().equals(t);
			}
		});

		if (matchingFiles.length > 0) {
			return extractOrdinalName(matchingFiles[0], ord);
		}

		return "";
	}

	private ArrayList<OrdinalName> ordinalList = null;

	// Read the result from JSON file
	public String getNameFromOrdinalNumber(String libName, long ord) {
		// TODO Auto-generated method stub
		if (ordinalList == null) {
			ordinalList = extractOrdinalList();
		}

		for (OrdinalName t : ordinalList) {
			if (t.getLibName().equals(libName.toLowerCase()) && t.getOrdinal() == ord) {
				return t.getFuncName();
			}
		}

		return "";
	}

	class OrdinalName {
		private String libName, funcName;
		int ordinal;

		public OrdinalName(String libName, int ordinal, String funcName) {
			this.setLibName(libName);
			this.ordinal = ordinal;
			this.setFuncName(funcName);
		}

		public int getOrdinal() {
			return ordinal;
		}

		public void setOrdinal(int ordinal) {
			this.ordinal = ordinal;
		}

		public String getLibName() {
			return libName;
		}

		public void setLibName(String libName) {
			this.libName = libName;
		}

		public String getFuncName() {
			return funcName;
		}

		public void setFuncName(String funcName) {
			this.funcName = funcName;
		}
	}

	private ArrayList<OrdinalName> extractOrdinalList() {
		ArrayList<OrdinalName> hOrdinal = new ArrayList<>();

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(new FileReader(libraryFile));
			for (Object o : jArray) {

				JSONObject item = (JSONObject) o;

				String libName = (String) item.get("LIBRARY_NAME");
				String funcName = (String) item.get("FUNCTION_NAME");
				int ordinal = Integer.parseInt((String) item.get("ORDINAL"));

				OrdinalName pHeader = new OrdinalName(libName, ordinal, funcName);
				hOrdinal.add(pHeader);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hOrdinal;
	}

	public final int getNodesCount() {
		int num = 0;
		// Set<CFAEdge> edges = new HashSet<CFAEdge>();
		// Set<Location> nodes = new HashSet<Location>();
		for (CFAEdge e : getCFA()) {
			AbsoluteAddress sourceAddr = e.getSource().getAddress();
			AbsoluteAddress targetAddr = e.getTarget().getAddress();
			if (!sourceAddr.equals(targetAddr)) {
				num++;
			}
		}
		return num;
	}

	private byte[] getOpcode(AbsoluteAddress address, Environment env, int n) {
		return env.getMemory().getBytesArray(address, n);

		/*
		 * for (int i = 0; i < opcodes.length; i++) { num++; if (opcodes[i] ==
		 * Long.MIN_VALUE) { long temp = readByte(new AbsoluteAddress(addr +
		 * i));
		 * 
		 * if (temp == Long.MIN_VALUE) { ExternalMemoryReturnData ret =
		 * ExternalMemory.getByte(address.getValue() + i); if (ret != null &&
		 * ret.isValidAddress) { opcodes[i] = ret.value.getValue(); } else { num
		 * = i; break; } } else { opcodes[i] = temp; } } }
		 * 
		 * if (num > 0) { byte[] ret = new byte[num]; for (int i = 0; i < num;
		 * i++) { ret[i] = (byte) opcodes[i]; }
		 * 
		 * return ret; }
		 * 
		 * return null;
		 */
	}

	/*
	 * private long getOperandValue(AbsoluteAddress address, Instruction instr,
	 * Operand src) { // TODO Auto-generated method stub // Operand src =
	 * ((X86MoveInstruction)instr).getOperand2(); if
	 * (src.getClass().getSimpleName().equals("Immediate")) { return
	 * ((Immediate) src).getNumber().intValue(); } if
	 * (src.getClass().getSimpleName().equals("X86Register")) { return
	 * getRegVal(address, ((X86Register) src).toString()); } else if
	 * (src.getClass().getSimpleName().equals("X86RegisterPart")) { ; } else if
	 * (src.getClass().getSimpleName().equals("X86SegmentRegister")) { ; } else
	 * if (src.getClass().getSimpleName().equals("X86MemoryOperand")) { ; }
	 * 
	 * return 0; }
	 */
	private long getPEHeaderAddress(BinaryFileInputBuffer inBuf) {
		try {
			inBuf.seek(60);
			return inBuf.readDWORD();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}

	public List<PMState> getPmState() {
		return pmState;
	}

	public final Map<AbsoluteAddress, List<AbsoluteAddress>> getPreservedExecutionMap() {
		return preservedExecutionMap;
	}

	/**
	 * Returns the address of the given procedure within the given library.
	 * Procedures present within the analyzed modules are given precedence over
	 * stub functions.
	 *
	 * @param library
	 * @param procedure
	 * @return the virtual address of the procedure
	 */
	public AbsoluteAddress getProcAddress(String library, String procedure) {
		ExportedSymbol expSymbol = exportedSymbols.get(procedure);
		if (expSymbol != null) {
			return expSymbol.getAddress();
		} else {
			return stubLibrary.resolveSymbol(library, procedure);
		}
	}

	public ReachedSet getReached() {
		return reached;
	}

	/*
	 * private long getRegVal(AbsoluteAddress address, String regName) { // TODO
	 * Auto-generated method stub if
	 * (getFileName().equals("Virus.Win32.Cabanas.2999") &&
	 * address.toString().equals("0x00404819")) { if (regName.contains("ecx")) {
	 * return 417; } else if (regName.contains("esi")) { return 4212242; } else
	 * if (regName.contains("edi")) { return 4215362; } } else if
	 * (getFileName().equals("Flooder.Win32.AngryPing") &&
	 * address.toString().equals("0x0040d86c")) { if (regName.contains("esi")) {
	 * return 4249712; } else if (regName.contains("edi")) { return 4256195; }
	 * else if (regName.contains("ecx")) { return 1109; } } else if
	 * (getFileName().equals("Flooder.Win32.AngryPing") &&
	 * (address.toString().equals("0x0040d82a") ||
	 * address.toString().equals("0x0040d830") || address
	 * .toString().equals("0x0040d839")
	 * 
	 * )) { if (regName.contains("ebp")) { return 14336; } else if
	 * (regName.contains("eax")) { return 4194304; } else if
	 * (regName.contains("ecx")) { return 1109; } } else if
	 * (getFileName().equals("Flooder.Win32.AngryPing") &&
	 * address.toString().equals("0x0040d85a")) { if (regName.contains("ebp")) {
	 * return 14336; } else if (regName.contains("eax")) { return 20819; } else
	 * if (regName.contains("ecx")) { return 1109; } }
	 * 
	 * long v = 0; // Set<Entry<Location, RTLStatement>> entries = //
	 * this.statementMap.entrySet(); // this.statementMap. // entries. // int i
	 * = 0; // this.reached.where(l); for (Iterator<AbstractState> iterator =
	 * this.reached.iterator(); iterator.hasNext();) { AbstractState state =
	 * iterator.next(); if (state.getLocation().getAddress().getValue() ==
	 * address.getValue()) { // System.out.println("Debug"); if (state
	 * instanceof CompositeState) { CompositeState cState = (CompositeState)
	 * state; // RTLStatement stmt = Program.getProgram().getStatement(new //
	 * Location(address, 0)); // cState.projectionFromConcretization(stmt.g, //
	 * t.getTargetExpression()); return cState.getValue(regName); } }
	 * 
	 * } for (RTLStatement value : this.statementMap.values()) { // i++; // if
	 * (i == entries.size()) { // value.get if (value instanceof
	 * RTLVariableAssignment) {
	 * 
	 * if (((RTLVariableAssignment)
	 * value).getLeftHandSide().toString().equals(regName.replace("%", ""))) {
	 * RTLExpression right = ((RTLVariableAssignment) value).getRightHandSide();
	 * if (right instanceof RTLNumber) { v = ((RTLNumber) right).intValue(); }
	 * else { v = 0; }
	 * 
	 * 
	 * v = Long.parseLong (((RTLVariableAssignment)
	 * value).getRightHandSide().toHexString());
	 * 
	 * }
	 * 
	 * // return v; } // } }
	 * 
	 * return v; }
	 */
	/**
	 * @return the resultFile
	 */
	public FileProcess getResultFile() {
		return resultFile;
	}

	public FileProcess getResultFileTemp() {
		return resultFileTemp;
	}

	private int getSectionNumberByRVA(long rva, SectionHeader[] section) {
		// TODO Auto-generated method stub
		if (rva < 0) {
			return -1;
		}
		for (int i = 0; i < section.length; i++) {
			if (section[i].VirtualAddress <= rva && (section[i].VirtualAddress + section[i].SizeOfRawData) > rva) {
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

	/**
	 * @return the smPos
	 */
	public List<AbsoluteAddress> getSelfModifyPosition() {
		return smPos;
	}

	public Location getStart() {
		return start;
	}

	/**
	 * Get the statement at a specific label. If there is no statement stored,
	 * attempts to disassemble the instruction at the label's virtual address.
	 * If the address is outside of the file area, logs an error and returns a
	 * Halt statement by default.
	 *
	 * @param label
	 *            The label for which to get the statement
	 * @return The statement object at label.
	 */
	public final RTLStatement getStatement(Location label) {
		if (!statementMap.containsKey(label) || this.checkSMPos(label.getAddress()) // ||
																					// label.getAddress().toString().equals("0x00401003")
		) {
			/*
			 * if (this.checkSMPos(label.getAddress()))
			 * System.out.println("Debug");
			 */

			AbsoluteAddress address = label.getAddress();
			Instruction instr;
			if (label.toString().endsWith("0")) {
				instr = getInstruction(address);
			} else {
				instr = getInstructionResult(address);
			}

			this.setAnalyzedInstruction(instr);
			// If we did not get an instruction, add an artificial Halt for
			// recovery
			if (instr == null) {
				RTLHalt halt = new RTLHalt();
				halt.setLabel(label);
				putStatement(halt);
				logger.error("ERROR: Replacing unknown instruction with HALT.");
				if (Options.debug.getValue()) {
					throw new DisassemblyException("Disassembly failed at " + address);
				}
			} else {
				StatementSequence seq = arch.getRTLEquivalent(address, instr);
				for (RTLStatement s : seq) {
					putStatement(s, instr);
				}
				assert statementMap.containsKey(label) : "Disassembly did not produce label: " + label;
			}
		}
		return statementMap.get(label);
	}

	public final RTLStatement getStatement1(Location label) {
		if (!statementMap.containsKey(label) || label.getAddress().toString().equals("0x00401003")) {
			AbsoluteAddress address = label.getAddress();
			Instruction instr = getInstructionResult(address);
			// If we did not get an instruction, add an artificial Halt for
			// recovery
			if (instr == null) {
				RTLHalt halt = new RTLHalt();
				halt.setLabel(label);
				putStatement(halt);
				logger.error("ERROR: Replacing unknown instruction with HALT.");
				if (Options.debug.getValue()) {
					throw new DisassemblyException("Disassembly failed at " + address);
				}
			} else {
				StatementSequence seq = arch.getRTLEquivalent(address, instr);
				for (RTLStatement s : seq) {
					putStatement(s);
				}
				assert statementMap.containsKey(label) : "Disassembly did not produce label: " + label;
			}
		}
		return statementMap.get(label);
	}

	public final RTLStatement getStatement1(PMLocation label) {
		if (!statementMap1.containsKey(label) || label.getAddress().toString().equals("0x00401003")) {
			AbsoluteAddress address = label.getAddress();
			Instruction instr = getInstructionResult(address);
			// If we did not get an instruction, add an artificial Halt for
			// recovery
			if (instr == null) {
				RTLHalt halt = new RTLHalt();
				halt.setLabel(new Location(label.getAddress(), label.getIndex()));
				putStatement(halt);
				logger.error("ERROR: Replacing unknown instruction with HALT.");
				if (Options.debug.getValue()) {
					throw new DisassemblyException("Disassembly failed at " + address);
				}
			} else {
				StatementSequence seq = arch.getRTLEquivalent(address, instr);
				for (RTLStatement s : seq) {
					putStatement(s);
				}
				assert statementMap.containsKey(label) : "Disassembly did not produce label: " + label;
			}
		}
		return statementMap1.get(label);
	}

	public final int getStatementCount() {
		return statementMap.size();
	}

	/**
	 * Get all statements in the Program.
	 *
	 * @return a collection of all statements in all loaded modules.
	 */
	public Collection<RTLStatement> getStatements() {
		return statementMap.values();
	}

	public Collection<RTLStatement> getStatements1() {
		return statementMap1.values();
	}

	public FileProcess getStopFile() {
		return stopFile;
	}

	public StubProvider getStubLibrary() {
		return this.stubLibrary;
	}

	public String getSymbolFor(AbsoluteAddress addr) {
		return symbolFinder(addr).getSymbolFor(addr);
	}

	public String getSymbolFor(Location label) {
		SymbolFinder symFinder = symbolFinder(label.getAddress());
		if (symFinder.hasSymbolFor(label.getAddress())) {
			return symFinder.getSymbolFor(label.getAddress());
		} else {
			return label.toString();
		}
	}

	public Collection<ExportedSymbol> getSymbols() {
		return exportedSymbols.values();
	}

	public TargetOS getTargetOS() {
		return targetOS;
	}

	public String getTechnique() {
		// TODO Auto-generated method stub
		if (technique.equals("")) {
			return "Unknown";
		}
		return technique;
	}

	public Set<Location> getUnresolvedBranches() {
		return unresolvedBranches;
	}

	/**
	 * Returns all variables used in the program. At the current state of the
	 * implementation, this includes only registers and flags.
	 *
	 * @return A set containing all variables used in this program.
	 */
	public SetOfVariables getUsedVariables() {
		SetOfVariables result = new SetOfVariables();
		for (CFAEdge edge : cfa) {
			result.addAll(((RTLStatement) edge.getTransformer()).getUsedVariables());
		}
		return result;
	}

	/**
	 * Gets the assembly instruction at the specified virtual address.
	 *
	 * @param address
	 *            a virtual address
	 * @return the assembly instruction at the specified address
	 */
	public long getWordValueMemory(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
				return 0;
			}

			ExecutableImage module = getModule(address);

			long fp = -1;
			if (module == null) {
				// logger.error("No module for address " + address
				// + ". Cannot disassemble instruction!");
				if (mainModule instanceof PEModule) {
					PEModule pe = (PEModule) mainModule;
					long t = pe.getWordValue(address.getValue());

					if (t != Long.MIN_VALUE) {
						return t;
					} else {
						return 0;
					}

				}
			} else {
				fp = module.getFilePointer(address);
				// Also check whether fp is out of the int range, since the
				// X86Disassembler actually
				// performs this cast in its implementation.
				if (fp < 0 || (int) fp < 0) {
					logger.error("Requested instruction outside of file area: " + address);
				}
				// else {
				// if (!module.isCodeArea(address)) {
				// logger.error("Requested instruction outside code section: "
				// + address);
				// return 0;
				// }
				// Disassembler dis = module.getDisassembler();
				// instr =
				X86Disassembler dis = (X86Disassembler) module.getDisassembler();
				int byteIndex = (int) fp;
				result = InstructionDecoder.readInt16(dis.getCode(), byteIndex);
				/*
				 * if (instr == null) {
				 * logger.error("Instruction could not be disassembled at: " +
				 * address); }
				 */
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	/**
	 * Install a harness that sets up the symbolic environment before calling
	 * main and provides a return point with a termination statement.
	 *
	 * @param harness
	 *            the harness object to install
	 */
	public void installHarness(Harness harness) {
		this.harness = harness;
		harness.install(this);
	}

	/**
	 * For all unresolved symbols, install simple stubs.
	 */
	public void installStubs() {
		if (mainModule instanceof AbstractCOFFModule) {
			stubLibrary = new Win32StubLibrary(arch);
		} else if (mainModule instanceof ELFModule) {
			stubLibrary = new LinuxStubLibrary(arch);
		}

		Iterator<UnresolvedSymbol> sIter = unresolvedSymbols.iterator();
		while (sIter.hasNext()) {
			UnresolvedSymbol unresolvedSymbol = sIter.next();
			AbsoluteAddress address = stubLibrary.resolveSymbol(unresolvedSymbol.getFromLibrary(),
					unresolvedSymbol.getName());
			if (address != null) {
				// logger.debug("Installing stack height stub for " +
				// unresolvedSymbol.getName());
				unresolvedSymbol.resolve(address);
				sIter.remove();
			}
		}

		if (!unresolvedSymbols.isEmpty()) {
			logger.warn("Unresolved symbols remaining: " + unresolvedSymbols);
		}
	}

	public boolean isBetweenSection(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		if (mainModule != null && mainModule instanceof PEModule) {
			return ((PEModule) mainModule).isBetweenSections(address.getValue());
		}

		return false;
	}

	public boolean isInside(AbsoluteAddress address) {
		// if (address.getValue() == 4198400) {
		// System.out.println("Debug");
		// }

		boolean result = false;
		try {
			if (harness.contains(address) || address.getValue() >= StubProvider.STUB_BASE) {
				return false;
			}

			ExecutableImage module = getModule(address);
			long fp = -1;
			if (module == null) {
				// logger.error("No module for address " + address
				// + ". Cannot disassemble instruction!");
				if (mainModule instanceof PEModule) {
					return ((PEModule) mainModule).isInsideHeader(address.getValue());
				}
				//
			} else {
				fp = module.getFilePointer(address);
				// Also check whether fp is out of the int range, since the
				// X86Disassembler actually
				// performs this cast in its implementation.
				if (fp < 0 || (int) fp < 0) {
					logger.error("Requested instruction outside of file area " + address);
				}
				// else {
				// if (!module.isCodeArea(address)) {
				// logger.error("Requested instruction outside code section: "
				// + address);
				// return 0;
				// }
				// Disassembler dis = module.getDisassembler();
				// instr =
				result = true;
				/*
				 * if (instr == null) {
				 * logger.error("Instruction could not be disassembled at: " +
				 * address); }
				 */
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	/**
	 * Loads the module containing the main function. This function should be
	 * called last for correct symbol resolution.
	 *
	 * @param moduleFile
	 *            the file to load
	 * @return the ExecutableImage class for the loaded module
	 * @throws IOException
	 * @throws BinaryParseException
	 */
	public ExecutableImage loadMainModule(File moduleFile) throws IOException, BinaryParseException {
		ExecutableImage module = loadModule(moduleFile);
		mainModule = module;
		setEntryAddress(module.getEntryPoint());
		fileName = moduleFile.getName();
		setLogFile(fileName);
		installStubs();
		fileSize = moduleFile.length();
		return module;
	}

	/**
	 * Loads a secondary (library or stub) module for analysis. Automatically
	 * determines the correct file type.
	 *
	 * @param moduleFile
	 *            the file to load
	 * @return the ExecutableImage class for the loaded module
	 * @throws IOException
	 * @throws BinaryParseException
	 */
	public ExecutableImage loadModule(File moduleFile) throws IOException, BinaryParseException {
		// First try to load it as a PE file, then object file, ELF and finally
		// raw binary code
		// The right thing to do would be some smart IDing of the file type, but
		// this exception chaining works for now...
		ExecutableImage module = null;
		try {
			module = new PEModule(moduleFile, getArchitecture());
			targetOS = TargetOS.WINDOWS;
		} catch (BinaryParseException e) {
			try {
				module = new ObjectFile(moduleFile, getArchitecture());
			} catch (BinaryParseException e2) {
				try {
					module = new ELFModule(moduleFile, getArchitecture());
					targetOS = TargetOS.LINUX;
				} catch (BinaryParseException e3) {
					module = new RawModule(moduleFile, getArchitecture());
				}
			}
		}

		for (ExecutableImage existingModule : modules) {
			if (existingModule.getMaxAddress().getValue() >= module.getMinAddress().getValue()
					&& existingModule.getMinAddress().getValue() <= module.getMaxAddress().getValue()) {
				throw new RuntimeException("Virtual addresses of modules overlap!");
			}
		}

		modules.add(module);
		unresolvedSymbols.addAll(module.getUnresolvedSymbols());
		for (ExportedSymbol symbol : module.getExportedSymbols()) {
			exportedSymbols.put(removeDecoration(symbol.getName()), symbol);
		}
		resolveSymbols();
		return module;
	}

	/**
	 * Stores an assembly instruction at the given address, overwriting any
	 * existing instruction.
	 *
	 * @param addr
	 *            the virtual address to save the instruction at
	 * @param instr
	 *            the assembly instruction
	 * @return true if there was no instruction stored for that address before,
	 *         false otherwise.
	 */
	public final boolean putInstruction(AbsoluteAddress addr, Instruction instr) {
		// logger.info(addr + " " + instr.toString(addr.getValueOperand(), new
		// DummySymbolFinder()));
		/*
		 * if (assemblyMap.containsKey(addr)) { Instruction i =
		 * assemblyMap.get(addr); if (!i.equals(instr)) { AbsoluteAddress nAddr
		 * = new AbsoluteAddress(addr.getValueOperand()); return
		 * assemblyMap.put(nAddr, instr) == null; } }
		 */
		this.addPMState(addr, instr);

		return assemblyMap.put(addr, instr) == null;
	}

	/**
	 * Stores a statement in the program. If a statement already exists with the
	 * same label, it is replaced.
	 *
	 * @param stmt
	 *            The statement to be stored. Has to contain a proper label.
	 */
	public final void putStatement(RTLStatement stmt) {
		RTLStatement existing = statementMap.get(stmt.getLabel());
		if (existing != null) {
			if (existing.equals(stmt)) {
				return;
			}
			logger.debug("Replacing statement at " + stmt.getLabel());
		}

		this.statementMap1.put(new PMLocation(stmt.getLabel().getAddress(), stmt.getLabel().getIndex()), stmt);

		statementMap.put(stmt.getLabel(), stmt);
	}

	public void putStatement(RTLStatement stmt, Instruction instr) {
		// TODO Auto-generated method stub
		RTLStatement existing = statementMap.get(stmt.getLabel());
		if (existing != null) {
			if (existing.equals(stmt)) {
				return;
			}
			logger.debug("Replacing statement at " + stmt.getLabel());
		}

		this.statementMap1.put(new PMLocation(stmt.getLabel().getAddress(), stmt.getLabel().getIndex(), instr), stmt);

		statementMap.put(stmt.getLabel(), stmt);
	}

	public final void putStatement1(RTLStatement stmt) {
		RTLStatement existing = statementMap.get(stmt.getLabel());
		if (existing != null) {
			if (existing.equals(stmt)) {
				return;
			}
			logger.debug("Replacing statement at " + stmt.getLabel());
		}
		statementMap.put(stmt.getLabel(), stmt);
	}
	
	@Deprecated
	private String readAllFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	/*
	 * private long readByte(AbsoluteAddress address) { long result =
	 * Long.MIN_VALUE;
	 * 
	 * try { ExecutableImage module = getModule(address);
	 * 
	 * long fp = -1; if (module == null) { // result = Long.MIN_VALUE; if
	 * (mainModule instanceof PEModule) { PEModule pe = (PEModule) mainModule;
	 * result = pe.getByteValue(address.getValue()); } } else { fp =
	 * module.getFilePointer(address); // Also check whether fp is out of the
	 * int range, since the // X86Disassembler actually // performs this cast in
	 * its implementation. if (fp < 0 || (int) fp < 0) {
	 * logger.error("Requested instruction outside of file area: " + address); }
	 * 
	 * X86Disassembler dis = (X86Disassembler) module.getDisassembler(); int
	 * byteIndex = (int) fp; result = InstructionDecoder.readByte(dis.getCode(),
	 * byteIndex); }
	 * 
	 * } catch (Exception e) { System.out.println(e.toString()); }
	 * 
	 * return result; }
	 */
	private String removeDecoration(String s) {
		if (s.charAt(0) == '@' || s.charAt(0) == '_') {
			s = s.substring(1);
		}
		int i = s.indexOf('@');
		if (i >= 0) {
			s = s.substring(0, i);
		}
		return s;
	}

	/**
	 * Resolves symbols between the loaded modules.
	 */
	private void resolveSymbols() {
		Iterator<UnresolvedSymbol> sIter = unresolvedSymbols.iterator();
		while (sIter.hasNext()) {
			UnresolvedSymbol unresolvedSymbol = sIter.next();
			ExportedSymbol symbol = exportedSymbols.get(removeDecoration(unresolvedSymbol.getName()));

			if (symbol != null) {
				logger.debug("Resolving symbol " + unresolvedSymbol.getName());
				unresolvedSymbol.resolve(symbol.getAddress());
				sIter.remove();
			}
		}
	}

	/**
	 * @param absolutePath
	 *            the absolutePath to set
	 */
	public void setAbsolutePathFile(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public void setAnalyzedInstruction(Instruction analyzedInstruction) {
		this.analyzedInstruction = analyzedInstruction;
	}

	public void SetAnalyzingTime(long time) {
		this.analyzingTime = time;
	}

	public void setCFA(Set<CFAEdge> cfa) {
		this.cfa = cfa;
	}

	public void setCFGState(Set<CFGState> cfg) {
		this.cfg = cfg;
	}

	public void setDebugLevel(int i) {
		// TODO Auto-generated method stub
		logger.setDebugLevel(i);
	}

	public void setDetailTechnique(String detail_tech) {
		if (!this.detail_technique.contains(detail_tech)) {
			this.detail_technique += " " + detail_tech;
			// resultFile_Temp.appendInLine(detail_tech + " Nodes:" +
			// getBPCFG().getVertexCount() + " ");
		}
	}

	/**
	 * Set the program entry point to the given address.
	 *
	 * @param entryAddress
	 *            the new entry address
	 */
	public void setEntryAddress(AbsoluteAddress entryAddress) {
		setStart(new Location(entryAddress));
	}

	public void setLog(String str) {
		// TODO Auto-generated method stub
		getLogResultFile().appendFile(str);
	}

	private void setLogFile(String name) {
		// TODO Auto-generated method stub
		logFile = new FileProcess("data/log/" + name + ".log");
		logFile.clearContentFile();
	}

	/**
	 * @param logResultFile
	 *            the fullResultFile to set
	 */
	public void setLogResultFile(FileProcess logResultFile) {
		this.logResultFile = logResultFile;
	}

	public void setPmState(List<PMState> pmState) {
		this.pmState = pmState;
	}

	public void setReachedSet(ReachedSet r) {
		this.reached = r;
	}

	/**
	 * @param resultFile
	 *            the resultFile to set
	 */
	public void setResultFile(FileProcess resultFile) {
		this.resultFile = resultFile;
	}

	public void setResultFileTemp(FileProcess resultFile_Temp) {
		this.resultFileTemp = resultFile_Temp;
	}

	/**
	 * @param smPos
	 *            the smPos to set
	 */
	public void setSelfModifyPosition(List<AbsoluteAddress> smPos) {
		this.smPos = smPos;
	}

	/**
	 * Set the program entry point to the given label.
	 *
	 * @param label
	 *            the new entry point
	 */
	public void setStart(Location label) {
		this.start = label;
	}

	public void setStopFile(FileProcess stopFile) {
		this.stopFile = stopFile;
	}

	public void setTechnique(String str) {
		if (!technique.contains(str)) {
			technique += " " + str;
		}
	}

	public void setUnresolvedBranches(Set<Location> unresolvedBranches) {
		this.unresolvedBranches = unresolvedBranches;
	}

	private long specialCase() {
		// TODO Auto-generated method stub
		if (this.getFileName().equals("smc.obj") || this.absolutePath.contains("smc.obj")) {
			return 192;
		}

		return 0;
	}

	private SymbolFinder symbolFinder(AbsoluteAddress addr) {
		if (addr.getValue() >= StubProvider.STUB_BASE) {
			return stubLibrary.getSymbolFinder();
		}

		ExecutableImage module = getModule(addr);
		return (module == null) ? new DummySymbolFinder() : module.getSymbolFinder();
	}

	// Check if this address is inside the header of file
	public boolean insideHeader(long addr) {
		for (ExecutableImage ei : modules) {
			if (ei.getMinAddress().getValue() <= addr) {
				return false;
			}
		}
		return addr >= getImageBase();
	}

	private String fileType = "PE32 executable (DLL) (GUI) Intel 80386, for MS Windows";

	public String getFileType() {
		// TODO Auto-generated method stub
		return fileType;
	}

	public void setFileType(String type) {
		fileType = type;
	}

	private long startDate = 0, endDate = 0;
	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public String getStartDate() {
		return sdf.format(startDate);
	}

	public void setStartdate() {
		startDate = new Date().getTime();
	}

	public String getEndDate() {
		endDate = new Date().getTime();
		return sdf.format(endDate);
	}

	public long getDuration() {
		return (endDate - startDate) / 1000 % 60;
	}

	private long fileSize = 0;

	public long getFileSize() {
		// TODO Auto-generated method stub
		return fileSize;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return "FILE";
	}

	private String fileMD5 = "";

	public String getFileMD5() {
		// TODO Auto-generated method stub
		if (fileMD5 == "") {
			fileMD5 = generateHashFile("MD5");
		}

		return fileMD5;
	}

	private String fileSHA1 = "";

	public String getFileSHA1() {
		// TODO Auto-generated method stub
		if (fileSHA1 == "") {
			fileSHA1 = generateHashFile("SHA-1");
		}

		return fileSHA1;
	}

	public String getPEHash() {
		// TODO Auto-generated method stub
		return "";
	}

	public int getSectionNum() {
		// TODO Auto-generated method stub
		if (mainModule != null && mainModule instanceof PEModule) {
			return ((PEModule) mainModule).getNumberOfSection();
		}

		return 0;
	}

	public SectionHeader getSection(int i) {
		// TODO Auto-generated method stub
		if (mainModule != null && mainModule instanceof PEModule) {
			if (i < ((PEModule) mainModule).getNumberOfSection()) {
				return ((PEModule) mainModule).getHeaderSection(i);
			}
		}

		return null;
	}

	public String getFileCRC32() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getFileSSDeep() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getFileYara() {
		// TODO Auto-generated method stub
		return "";
	}

	public TripleList getImportTableTriple() {
		// TODO Auto-generated method stub
		ExecutableImage m = getMainModule();

		if (m != null && m instanceof PEModule) {
			TripleList list = new TripleList();
			Map<AbsoluteAddress, Pair<String, String>> importTable = ((PEModule) m).getImportTable();

			for (Map.Entry<AbsoluteAddress, Pair<String, String>> entry : importTable.entrySet()) {
				list.addTriple(entry.getValue().getLeft(), entry.getValue().getRight(), entry.getKey().getValue());
			}
			return list;
		}

		return null;
	}
	
	public List<JSONObj> getResourceJSONObj() {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<Long, Integer> instList = null;

	public Map<Long, Integer> extractStaticInst() {
		// TODO Auto-generated method stub
		List<Long> queue = new ArrayList<>();
		List<Long> queueRemove = new ArrayList<>();
		queue.add(Program.getProgram().getEntryPoint().getValue());
		
		if (instList == null) {
			instList = new HashMap<>();
			while (!queue.isEmpty()) {
				long location = queue.remove(0);
//				queueRemove.add(location);
				try {					
					Instruction inst = Program.getProgram().getInstruction(new AbsoluteAddress(location));
					while (inst != null) {
//						System.out.println(new AbsoluteAddress(location).toString() + "-" + inst.getSize());
						if (queueRemove.contains(location)) {
							break;							
						}
						
						instList.put(location, inst.getSize());
						queueRemove.add(location);
						if (inst instanceof X86CallInstruction) {
							Operand dest = ((X86CallInstruction)inst).getOperand1();
							if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
								if (!queueRemove.contains(((AbsoluteAddress) dest).getValue())) {
									queue.add(((AbsoluteAddress) dest).getValue());
								}
							} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
								if (!queueRemove.contains(((X86PCRelativeAddress) dest).getEffectiveValue(location))) {
									queue.add(((X86PCRelativeAddress) dest).getEffectiveValue(location));
								}
							}
//						} else if (inst instanceof X86CondJmpInstruction) {
//							Operand dest = ((X86CondJmpInstruction) inst).getOperand1();
//							if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
//								if (!queueRemove.contains(((AbsoluteAddress) dest).getValue())) {
//									queue.add(((AbsoluteAddress) dest).getValue());
//								}
//							} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
//								if (!queueRemove.contains(((X86PCRelativeAddress) dest).getEffectiveValue(location))) {
//									queue.add(((X86PCRelativeAddress) dest).getEffectiveValue(location));
//								}
//							}
//						} else if (inst instanceof X86JmpInstruction) {
//							Operand dest = ((X86JmpInstruction)inst).getOperand1();
//							if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
//								if (!queueRemove.contains(((AbsoluteAddress) dest).getValue())) {
//									queue.add(((AbsoluteAddress) dest).getValue());
//								}
//							} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
//								if (!queueRemove.contains(((X86PCRelativeAddress) dest).getEffectiveValue(location))) {
//									queue.add(((X86PCRelativeAddress) dest).getEffectiveValue(location));
//								}
//							}
						} else if (inst instanceof X86RetInstruction) {
							break;
						}
						
						location += inst.getSize();
						inst = Program.getProgram().getInstruction(new AbsoluteAddress(location));
					}
					
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
			System.out.println("Number of static instruction:" + instList.size());
		}		
		return instList;
	}

	public Map<String, String> readOEP() {
		// TODO Auto-generated method stub
		Map<String, String> t = new HashMap<>();
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(oepFileTXT));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {			
				String temp[] = line.split(" ");
				if (temp.length > 1) {
					for (int i = 1; i < temp.length; i++) {
						t.put(temp[0], temp[i]);
					}
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return t;
	}
}
