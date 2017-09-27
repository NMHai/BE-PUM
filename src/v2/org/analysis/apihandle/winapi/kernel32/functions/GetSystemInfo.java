/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetSystemInfo.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

/**
 * The GetSystemInfo function returns information about the current system.
 * 
 * @param lpSystemInfo
 *            Pointer to a SYSTEM_INFO structure that receives the information.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemInfo extends Kernel32API {

	public GetSystemInfo() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		SYSTEM_INFO lpSystemInfo = new SYSTEM_INFO();
		Kernel32.INSTANCE.GetSystemInfo(lpSystemInfo );

//	    DWORD dwPageSize;
//	    LPVOID lpMinimumApplicationAddress;
//	    LPVOID lpMaximumApplicationAddress;
//	    DWORD_PTR dwActiveProcessorMask;
//	    DWORD dwNumberOfProcessors;
//	    DWORD dwProcessorType;
//	    DWORD dwAllocationGranularity;
//	    WORD wProcessorLevel;
//	    WORD wProcessorRevision;
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1), 
				new LongValue(lpSystemInfo.processorArchitecture.dwOemID.longValue()));
		
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(lpSystemInfo.dwPageSize.longValue()));
		
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(Pointer.nativeValue(lpSystemInfo.lpMinimumApplicationAddress)));

		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(Pointer.nativeValue(lpSystemInfo.lpMaximumApplicationAddress)));
		
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(lpSystemInfo.dwActiveProcessorMask.longValue()));
		
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(lpSystemInfo.dwNumberOfProcessors.longValue()));
		
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(lpSystemInfo.dwProcessorType.longValue()));
		
		memory.setDoubleWordMemoryValue(
				new X86MemoryOperand(DataType.INT32, t1 += 4), 
				new LongValue(lpSystemInfo.dwAllocationGranularity.longValue()));
		
		memory.setWordMemoryValue(
				new X86MemoryOperand(DataType.INT16, t1 += 4), 
				new LongValue(lpSystemInfo.wProcessorLevel.longValue()));
		
		memory.setWordMemoryValue(
				new X86MemoryOperand(DataType.INT16, t1 += 2), 
				new LongValue(lpSystemInfo.wProcessorRevision.longValue()));
	}

}
