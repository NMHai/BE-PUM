/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Thread32First.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.THREADENTRY32;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves information about the first thread of any process encountered in a
 * system snapshot.
 * 
 * @param hSnapshot
 *            A handle to the snapshot returned from a previous call to the
 *            CreateToolhelp32Snapshot function.
 * 
 * @param lpte
 *            A pointer to a THREADENTRY32 structure.
 * 
 * @return Returns TRUE if the first entry of the thread list has been copied to
 *         the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES error value is
 *         returned by the GetLastError function if no threads exist or the
 *         snapshot does not contain thread information.
 * 
 * @author Yen Nguyen
 *
 */
public class Thread32First extends Kernel32API {

	public Thread32First() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hSnapshot = new HANDLE(new Pointer(t1));
		THREADENTRY32 lpte = new THREADENTRY32();
		// public DWORD dwSize;
		// public DWORD cntUsage;
		// public DWORD th32ThreadID;
		// public DWORD th32OwnerProcessID;
		// public LONG tpBasePri;
		// public LONG tpDeltaPri;
		// public DWORD dwFlags;
		long index = t2;
		lpte.dwSize = new DWORD(
				((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index))).getValue());
		lpte.cntUsage = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				index += 4))).getValue());
		index += 4;
		lpte.th32ThreadID = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				index += 4))).getValue());
		index += 4;
		lpte.th32OwnerProcessID = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, index += 4))).getValue());
		index += 4;
		lpte.tpBasePri = new LONG(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				index += 4))).getValue());
		index += 4;
		lpte.tpDeltaPri = new LONG(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				index += 4))).getValue());
		index += 4;
		lpte.dwFlags = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
				index += 4))).getValue());

		System.out.println(lpte.toString(false));

		BOOL ret = Kernel32DLL.INSTANCE.Thread32First(hSnapshot, lpte);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
		if (APIHandle.isDebug) {
			System.out.println(lpte.toString(false));
		}

		index = t2;
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index),
				new LongValue(lpte.dwSize.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index += 4),
				new LongValue(lpte.cntUsage.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index += 4), new LongValue(
				lpte.th32ThreadID.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index += 4), new LongValue(
				lpte.th32OwnerProcessID.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index += 4),
				new LongValue(lpte.tpBasePri.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index += 4),
				new LongValue(lpte.tpDeltaPri.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index += 4),
				new LongValue(lpte.dwFlags.longValue()));
	}

}
