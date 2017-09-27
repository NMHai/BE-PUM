/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: LeaveCriticalSection.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.RTL_CRITICAL_SECTION;
import v2.org.analysis.value.LongValue;

/**
 * Releases ownership of the specified critical section object.
 * 
 * @param lpCriticalSection
 *            A pointer to the critical section object.
 * 
 * @author Yen Nguyen
 *
 */
public class LeaveCriticalSection extends Kernel32API {

	public LeaveCriticalSection() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		RTL_CRITICAL_SECTION lpCriticalSection = new RTL_CRITICAL_SECTION();

		lpCriticalSection.DebugInfo = null;
		lpCriticalSection.LockCount = new LONG(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1 += 4))).getValue());
		lpCriticalSection.RecursionCount = new LONG(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1 += 4))).getValue());
		lpCriticalSection.OwningThread = new HANDLE(
				new Pointer(
						((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4)))
								.getValue()));
		lpCriticalSection.LockSemaphore = new HANDLE(
				new Pointer(
						((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4)))
								.getValue()));
		lpCriticalSection.SpinCount = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1 += 4))).getValue());

		Kernel32DLL.INSTANCE.LeaveCriticalSection(lpCriticalSection);
		
		register.mov("eax", new LongValue(0));

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
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4),
				new LongValue((lpCriticalSection.OwningThread == null) ? 0 : Pointer
						.nativeValue(lpCriticalSection.OwningThread.getPointer())));
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4),
				new LongValue((lpCriticalSection.LockSemaphore == null) ? 0 : Pointer
						.nativeValue(lpCriticalSection.LockSemaphore.getPointer())));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 += 4), new LongValue(
				lpCriticalSection.SpinCount.longValue()));
	}

}
