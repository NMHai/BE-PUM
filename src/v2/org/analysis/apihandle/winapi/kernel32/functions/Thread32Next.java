/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: Thread32Next.java
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
 * The CryptDestroyHash function destroys the hash object referenced by the
 * hHash parameter. After a hash object has been destroyed, it can no longer be
 * used.
 * 
 * @param hHash
 *            The handle of the hash object to be destroyed.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class Thread32Next extends Kernel32API {

	public Thread32Next() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hSnapshot = new HANDLE(new Pointer(t1));
		THREADENTRY32 lpte = new THREADENTRY32();
		lpte.dwSize.setValue(lpte.size());

		BOOL ret = Kernel32DLL.INSTANCE.Thread32Next(hSnapshot, lpte);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
		if (APIHandle.isDebug) {
			System.out.println(lpte.toString(false));
		}

		// public DWORD dwSize;
		// public DWORD cntUsage;
		// public DWORD th32ThreadID;
		// public DWORD th32OwnerProcessID;
		// public LONG tpBasePri;
		// public LONG tpDeltaPri;
		// public DWORD dwFlags;
		long index = t2;
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
