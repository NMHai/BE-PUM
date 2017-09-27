package v2.org.analysis.environment.processthread;

import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

public class PEB {

	private static final long PEB_Base_Address = 0x7EFDE000;
	private static byte InheritedAddressSpace; // PEB_0
	private static byte ReadImageFileExecOptions; // PEB_1
	private static byte BeingDebugged; // PEB_2
	private static byte SpareBool; // PEB_3
	private static long Mutant; // PEB_4
	private static long ImageBaseAddress; // PEB_8
	private static long Ldr; // PEB_C

	static void updatePEB(BPState curState) {
		// Update later
		// Update PEB_0
		InheritedAddressSpace = 0;
		curState.getEnvironement().getMemory().createMemory(PEB_Base_Address, 0x20, "Initialization of PEB");
		curState.getEnvironement().getMemory()
				.setByteMemoryValue(PEB_Base_Address, new LongValue(InheritedAddressSpace));
		// Update PEB_1
		ReadImageFileExecOptions = 0;
		curState.getEnvironement().getMemory()
				.setByteMemoryValue(PEB_Base_Address + 1, new LongValue(ReadImageFileExecOptions));
		// Update PEB_2
//		BeingDebugged = 1;
		BeingDebugged = 0;
		curState.getEnvironement().getMemory().setByteMemoryValue(PEB_Base_Address + 2, new LongValue(BeingDebugged));
		// Update PEB_3
		SpareBool = 0;
		curState.getEnvironement().getMemory().setByteMemoryValue(PEB_Base_Address + 3, new LongValue(SpareBool));
		// Update PEB_4
		Mutant = 0xFFFFFFFF;
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(PEB_Base_Address + 4, new LongValue(Mutant));
		// Update PEB_8
		ImageBaseAddress = 0x00400000;
		curState.getEnvironement().getMemory()
				.setDoubleWordMemoryValue(PEB_Base_Address + 8, new LongValue(ImageBaseAddress));
		// Update PEB_C
		Ldr = LDRData.getLDR_Base_Address();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(PEB_Base_Address + 0xC, new LongValue(Ldr));
		LDRData.updateLDR(curState);
	}

	public static long getPEB_Base_Address() {
		return PEB_Base_Address;
	}

}
