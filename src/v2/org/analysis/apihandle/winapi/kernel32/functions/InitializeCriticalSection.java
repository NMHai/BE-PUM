/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: InitializeCriticalSection.java
 * Created date: May 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.RTL_CRITICAL_SECTION;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;

import com.sun.jna.Pointer;

/**
 * @author Yen Nguyen
 *
 */
public class InitializeCriticalSection extends Kernel32API {
	public InitializeCriticalSection() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		try {
			long t1 = this.params.get(0);

			RTL_CRITICAL_SECTION lpCriticalSection = new RTL_CRITICAL_SECTION();
			Kernel32DLL.INSTANCE.InitializeCriticalSection(lpCriticalSection);

			// public LPVOID /* PRTL_CRITICAL_SECTION_DEBUG */DebugInfo = null;
			// public LONG LockCount;
			// public LONG RecursionCount;
			// public HANDLE OwningThread; // from the thread's
			// ClientId->UniqueThread
			// public HANDLE LockSemaphore;
			// public ULONG_PTR SpinCount; // force size on 64-bit systems when
			// packed
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(
					lpCriticalSection.LockCount.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(
					lpCriticalSection.RecursionCount.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4),
					new LongValue(Pointer.nativeValue(lpCriticalSection.OwningThread.getPointer())));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4),
					new LongValue(Pointer.nativeValue(lpCriticalSection.LockSemaphore.getPointer())));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(
					lpCriticalSection.SpinCount.longValue()));

			register.mov("eax", new LongValue(0));
		} catch (Exception e) {
			register.mov("eax", new SymbolValue(SymbolValue.generateString(this)));
			e.printStackTrace();
		}
	}

}
