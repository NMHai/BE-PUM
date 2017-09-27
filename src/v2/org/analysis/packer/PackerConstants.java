package v2.org.analysis.packer;

import java.util.HashSet;
import java.util.Set;


public final class PackerConstants {
	public static final int ANTI_DEBUGGING 		= 0;
	public static final int CHECKSUMMING		= ANTI_DEBUGGING 	+ 1;
	public static final int CODE_CHUNKING		= CHECKSUMMING 		+ 1;
	public static final int INDIRECT_JUMP		= CODE_CHUNKING 	+ 1;
	public static final int OBFUSCATED_CONST	= INDIRECT_JUMP		+ 1;
	public static final int OVERLAPPING_BLOCK	= OBFUSCATED_CONST 	+ 1;
	public static final int OVERLAPPING_FUNC	= OVERLAPPING_BLOCK	+ 1;
	public static final int OVERWRITING			= OVERLAPPING_FUNC	+ 1;
	public static final int PACKING_UNPACKING	= OVERWRITING		+ 1;
	public static final int SEH					= PACKING_UNPACKING + 1;
	public static final int STOLEN_BYTES		= SEH 				+ 1;
	public static final int TIMING_CHECK		= STOLEN_BYTES		+ 1;
	public static final int TWO_APIS			= TIMING_CHECK		+ 1;
	public static final int HARDWARE_BPX		= TWO_APIS			+ 1;
	
//	public static final int ANTI_DEBUGGING 		= 11;
//	public static final int CHECKSUMMING		= 9;
//	public static final int CODE_CHUNKING		= 2;
//	public static final int INDIRECT_JUMP		= 5;
//	public static final int OBFUSCATED_CONST	= 8;
//	public static final int OVERLAPPING_BLOCK	= 1;
//	public static final int OVERLAPPING_FUNC	= 0;
//	public static final int OVERWRITING			= 3;
//	public static final int PACKING_UNPACKING	= 4;
//	public static final int SEH					= 6;
//	public static final int STOLEN_BYTES		= 12;
//	public static final int TIMING_CHECK		= 10;
//	public static final int TWO_APIS			= 7;
//	public static final int HARDWARE_BPX		= 13;
	
	public final static String[] ANTIDEBUGGING_APIs = {"IsDebuggerPresent"
			, "CheckRemoteDebuggerPresent"
			, "NtQueryInformationProcess"
			, "NtQuerySystemInformation"
			, "NtQueryObject"};
	public static final String[] STOLENBYTE_APIs = {"VirtualAlloc", "VirtualAllocEx", "ZwAllocateVirtualMemory"};
	public static final String[] TIMINGCHECK_APIs = {"GetTickCount"};
	public static final String[] TWOSPECIAL_APIs_Main = {"GetProcAddress"};
	public static final String[] TWOSPECIAL_APIs_SetUp = {"LoadLibrary", "GetModuleHandle"};
	public static Set<String> specialInstList = null;
	public static final String[] instList = {"adc", "add", "mov", "and", "stos", 
		"or", "xor", "xchg", "movs", "sub", "movd", "movsb", "movsx", "movzb", "movzw", "lods"}; 
//	public static final String[] instList = {"add", "sub"};
	
	public static boolean isSpecialInstruction(String instName) {
		if (specialInstList == null) {
			specialInstList = new HashSet<> ();
			for (String name: instList) {
				specialInstList.add(name);
				specialInstList.add(name + "s");
				specialInstList.add(name + "b");
				specialInstList.add(name + "w");
				specialInstList.add(name + "l");
			}			
		}
		
		return specialInstList.contains(instName);
	}
	
//	private static final String ASPACK 			= "01111111101000";
//	private static final String FSG 			= "01011111100010";
//	private static final String MPRESS 			= "01011101100000";
//	private static final String NPACK 			= "01011111101010";
//	private static final String PECOMPACT 		= "01011111111010";
//	private static final String PETITE 			= "01011111110000";
//	private static final String UPX 			= "01011101100010";
//	private static final String YODA 			= "11111111110010";
//	private static final String TELOCK			= "01111111111001";
//	
//	public static final TechniqueSignature[] pTechSign 		= {new TechniqueSignature("ASPACK", 	ASPACK),
//													   new TechniqueSignature("FSG", 		FSG),
//													   new TechniqueSignature("MPRESS", 	MPRESS),
//													   new TechniqueSignature("NPACK", 		NPACK),
//													   new TechniqueSignature("PECOMPACT", 	PECOMPACT),
//													   new TechniqueSignature("PETITE", 	PETITE),
//													   new TechniqueSignature("UPX", 		UPX),
//													   new TechniqueSignature("YODA", 		YODA),
//													   new TechniqueSignature("TELOCK",     TELOCK)};
//												
//	
//	private static final String cASPACK				= "0:1:1:12:63:5:3:45:1:0:4:0:0:0";
//	private static final String cFSG				= "0:1:0:12:2:1:1:4:1:0:0:0:2:0";
//	private static final String cMPRESS				= "0:1:0:4:34:6:0:14:1:0:0:0:0:0";
//	private static final String cNPACK				= "0:1:0:9:24:3:2:11:1:0:4:0:3:0";
//	private static final String cPECOMPACT			= "0:1:0:16:60:7:4:28:1:3:2:0:2:0";
//	private static final String cPETITE				= "0:1:0:8:115:12:1:51:1:4:0:0:0:0";
//	private static final String cUPX				= "0:1:0:4:25:1:0:7:1:0:0:0:2:0";
//	private static final String cYODA				= "1:1:1:15:141:6:4:120:1:9:0:0:3:0";
//	private static final String cTELOCK				= "0:1:1:22:167:20:2:136:12:35:4:0:0:1";
//	
//	public static final TechniqueSignature[] pTechCountSign		= {new TechniqueSignature("ASPACK", 	cASPACK),
//														   new TechniqueSignature("FSG", 		cFSG),
//														   new TechniqueSignature("MPRESS", 	cMPRESS),
//			   											   new TechniqueSignature("NPACK", 		cNPACK),
//			   											   new TechniqueSignature("PECOMPACT", 	cPECOMPACT),
//			   											   new TechniqueSignature("PETITE", 	cPETITE),
//			   											   new TechniqueSignature("UPX", 		cUPX),
//			   											   new TechniqueSignature("YODA", 		cYODA),
//			   											   new TechniqueSignature("TELOCK", 	cTELOCK)};	
}
