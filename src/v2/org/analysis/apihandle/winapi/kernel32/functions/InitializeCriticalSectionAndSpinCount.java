/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: InitializeCriticalSectionAndSpinCount.java
 * Created date: Aug 23, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.RTL_CRITICAL_SECTION;
import v2.org.analysis.value.LongValue;

/**
 * Initializes a critical section object and sets the spin count for the
 * critical section. When a thread tries to acquire a critical section that is
 * locked, the thread spins: it enters a loop which iterates spin count times,
 * checking to see if the lock is released. If the lock is not released before
 * the loop finishes, the thread goes to sleep to wait for the lock to be
 * released.
 * 
 * @param lpCriticalSection
 *            A pointer to the critical section object.
 * 
 * @param dwSpinCount
 *            The spin count for the critical section object. On
 *            single-processor systems, the spin count is ignored and the
 *            critical section spin count is set to 0 (zero). On multiprocessor
 *            systems, if the critical section is unavailable, the calling
 *            thread spins dwSpinCount times before performing a wait operation
 *            on a semaphore associated with the critical section. If the
 *            critical section becomes free during the spin operation, the
 *            calling thread avoids the wait operation.
 * 
 * @return This function always returns a nonzero value.
 * 
 * @author Yen Nguyen
 *
 */
public class InitializeCriticalSectionAndSpinCount extends Kernel32API {

	public InitializeCriticalSectionAndSpinCount() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		RTL_CRITICAL_SECTION lpCriticalSection = new RTL_CRITICAL_SECTION();
		DWORD dwSpinCount = new DWORD(t2);

		BOOL ret = Kernel32DLL.INSTANCE.InitializeCriticalSectionAndSpinCount(lpCriticalSection, dwSpinCount);

		System.out.println("Return Value:" + ret.longValue());
		register.mov("eax", new LongValue(ret.longValue()));

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
