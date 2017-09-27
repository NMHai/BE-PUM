package v2.org.analysis.environment.processthread;

import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import com.sun.jna.platform.win32.Kernel32;

public class TIB {
	private static boolean beUpdated;
	private static final long TIB_Base_Address = 0x7EFDD000;
	private static long FS_0; // SEH frame *
	private static long FS_4; // EBP *
	private static long FS_8; // ESP *
	private static long FS_C; // SubSystemTib
	private static long FS_10; // FiberData
	private static long FS_14; // Arbitrary data slot
	private static final long FS_18 = TIB_Base_Address; // Address of TIB *
	private static long FS_1C; // Environment Pointer *
	private static long FS_20; // Process ID *
	private static long FS_24; // Current Thread ID *
	private static long FS_28; // Active RPC Handle
	private static long FS_2C; // Address of the TLS
	private static final long FS_30 = PEB.getPEB_Base_Address(); // Address of
																	// the PEB *
	private static long FS_34; // Last error number
	private static long FS_38; // Count of owned critical sections
	private static long FS_3C; // Address of CSR Client Thread
	private static long FS_40; // Win32 Thread Information
	private static long FS_44[]; // Win32 client information, user32 private
									// data
	private static long FS_CO; // Pointer to FastSysCall in Wow64
	private static long FS_C4; // Current locale
	private static long FS_C8; // FP Software Status Register
	private static long FS_CC[]; // Reserved for OS, kernel32 private data
	private static long FS_1A4; // Exception code
	private static long FS_1A8; // Activation context stack
	private static long FS_1BC[]; // Spare bytes, ntdll private data
	private static long FS_1D4[]; // Reserved for OS, ntdll private data
	private static long FS_1FC[]; // GDI TEB Batch, vm86 private data
	private static long FS_6DC; // GDI Region
	private static long FS_6E0; // GDI Pen
	private static long FS_6E4; // GDI Brush
	private static long FS_6E8; // Real Process ID
	private static long FS_6EC; // Real Thread ID
	private static long FS_6F4; // GDI client process ID (PID)
	private static long FS_6F8; // GDI client thread ID (TID)
	private static long FS_6FC; // GDI thread locale information
	private static long FS_700[]; // Reserved for user application
	private static long FS_714[]; // Reserved for GL
	private static long FS_BF4; // Last Status Value
	private static long FS_BF8[]; // Static UNICODE_STRING buffer
	private static long FS_E0C; // Pointer to de-allocation stack
	private static long FS_E10[]; // TLS slots, 4 byte per slot
	private static long FS_F10[]; // LIST_ENTRY structure
	private static long FS_F18; // VDM
	private static long FS_F1C; // Reserved for RPC
	private static long FS_F28; // RtlSetThreadErrorMode

	// Initialization
	static {
		beUpdated = false;
	}

	public static void setBeUpdated(boolean updated){
		beUpdated = updated;
	}
	
	public static void updateChecking(BPState curState) {
		Instruction ins = curState.getInstruction();
		if (ins == null) {
			return;
		}
		int i = 0;
		while (i < ins.getOperandCount()) {
			Operand op = ins.getOperand(i);
			if (op.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand mop = (X86MemoryOperand) op;
				if (mop.getSegmentRegister() != null
						&& mop.getSegmentRegister().toString() == "%fs") {
					beUpdated = true;
					break;
				}
			}
			i++;
		}
	}

	public static void updateTIB(BPState curState) {
		if (!beUpdated){
			return;
		}
		// Update SEH frame
		FS_0 = curState.getEnvironement().getSystem().getSEHHandler().getStart().getAddrSEHRecord();
		curState.getEnvironement().getMemory().createMemory(TIB_Base_Address, 0x100, "Initialization of TIB");
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address, new LongValue(FS_0));
		// Update EBP
		Value ebp = curState.getEnvironement().getRegister().getRegisterValue("ebp");
		if (ebp != null && ebp instanceof LongValue) {
			FS_4 = ((LongValue) ebp).getValue();
		}
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x4, new LongValue(FS_4));
		// Update ESP
		Value esp = curState.getEnvironement().getRegister().getRegisterValue("esp");
		if (esp != null && esp instanceof LongValue) {
			FS_8 = ((LongValue) esp).getValue();
		}
		//FS_8 = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("esp")).getValue();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x8, new LongValue(FS_8));
		// More: Update Environment pointer FS_1C
		FS_1C = 0;
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x1C, new LongValue(FS_1C));
		// Update Address of TIB
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x18, new LongValue(FS_18));
		// Update Process ID
		FS_20 = Kernel32.INSTANCE.GetCurrentProcessId();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x20, new LongValue(FS_20));
		// Update Thread ID
		FS_24 = Kernel32.INSTANCE.GetCurrentThreadId();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x24, new LongValue(FS_24));
		// Update Address of PEB
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x30, new LongValue(FS_30));
		// //////////////////////////////////////////////////////////
		// Update PEB
		// /////////////////////////////////////////////////////////
		PEB.updatePEB(curState);
		beUpdated = false;
	}

	public static long getTIB_Base_Address() {
		return TIB_Base_Address;
	}
}
