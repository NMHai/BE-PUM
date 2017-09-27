/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about the next process recorded in a system snapshot.
 * 
 * @param hSnapshot
 *            A handle to the snapshot returned from a previous call to the
 *            CreateToolhelp32Snapshot function.
 * 
 * @param lppe
 *            A pointer to a PROCESSENTRY32 structure. It contains process
 *            information such as the name of the executable file, the process
 *            identifier, and the process identifier of the parent process.
 * 
 * @return Returns TRUE if the first entry of the process list has been copied
 *         to the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES error value
 *         is returned by the GetLastError function if no processes exist or the
 *         snapshot does not contain process information.
 * 
 * @author Yen Nguyen
 *
 */
public class Process32Next extends Kernel32API {

	public Process32Next() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hSnapshot = new HANDLE(new Pointer(t1));
		PROCESSENTRY32 lppe = new PROCESSENTRY32();

		BOOL ret = Kernel32DLL.INSTANCE.Process32Next(hSnapshot, lppe);

		register.mov("eax", new LongValue(ret.longValue()));

		System.out.println("Return value: " + ret.booleanValue());
		if (APIHandle.isDebug && ret.booleanValue()) {
			System.out.println(lppe.toString(false));
			System.out.println("lppe.szExeFile: " + new String(lppe.szExeFile));
		}

		// DWORD dwSize;
		// DWORD cntUsage;
		// DWORD th32ProcessID;
		// ULONG_PTR th32DefaultHeapID;
		// DWORD th32ModuleID;
		// DWORD cntThreads;
		// DWORD th32ParentProcessID;
		// LONG pcPriClassBase;
		// DWORD dwFlags;
		// TCHAR szExeFile[MAX_PATH];
		// memory.setDoubleWordMemoryValue(new X86MemoryOperand(
		// DataType.INT32, t2), new LongValue(lppe.dwSize.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lppe.cntUsage.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lppe.th32ProcessID.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(
				lppe.th32DefaultHeapID.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lppe.th32ModuleID.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lppe.cntThreads.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(
				lppe.th32ParentProcessID.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(
				lppe.pcPriClassBase.longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
				new LongValue(lppe.dwFlags.longValue()));
		for (int i = 0; i < lppe.szExeFile.length; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t2 + i), new LongValue(lppe.szExeFile[i]));
		}
	}
}
