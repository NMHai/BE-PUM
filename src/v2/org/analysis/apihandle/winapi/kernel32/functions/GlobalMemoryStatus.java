/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.MEMORYSTATUS;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * [GlobalMemoryStatus can return incorrect information. Use the
 * GlobalMemoryStatusEx function instead. ] Retrieves information about the
 * system's current usage of both physical and virtual memory.
 * 
 * @param lpBuffer
 *            A pointer to a MEMORYSTATUS structure. The GlobalMemoryStatus
 *            function stores information about current memory availability into
 *            this structure.
 * 
 * @author Yen Nguyen
 *
 */
public class GlobalMemoryStatus extends Kernel32API {

	public GlobalMemoryStatus() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);

		MEMORYSTATUS lpBuffer = new MEMORYSTATUS();
		Kernel32DLL.INSTANCE.GlobalMemoryStatus(lpBuffer);

		// DWORD dwLength;
		// DWORD dwMemoryLoad;
		// SIZE_T dwTotalPhys;
		// SIZE_T dwAvailPhys;
		// SIZE_T dwTotalPageFile;
		// SIZE_T dwAvailPageFile;
		// SIZE_T dwTotalVirtual;
		// SIZE_T dwAvailVirtual;

		memory.setDoubleWordMemoryValue(t, new LongValue(lpBuffer.dwLength.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwMemoryLoad.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwTotalPhys.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwAvailPhys.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwTotalPageFile.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwAvailPageFile.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwTotalVirtual.longValue()));
		memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwAvailVirtual.longValue()));
	}

}
