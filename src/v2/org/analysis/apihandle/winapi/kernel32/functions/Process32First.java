/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Retrieves information about the first process encountered in a system
 * snapshot.
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
public class Process32First extends Kernel32API {

	public Process32First() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		System.out.println("hSnapshot: " + t1 + " ,lppe's address: " + t2);

		HANDLE hSnapshot = new HANDLE(new Pointer(t1));
		PROCESSENTRY32 lppe = new PROCESSENTRY32();

		// lppe.dwSize = new DWORD(((LongValue)
		// memory.getDoubleWordMemoryValue(new
		// X86MemoryOperand(DataType.INT32,
		// t2))).getValue());
		lppe.cntUsage = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.th32ProcessID = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.th32DefaultHeapID = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.th32ModuleID = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.cntThreads = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.th32ParentProcessID = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.pcPriClassBase = new LONG(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());
		lppe.dwFlags = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t2 += 4)).getValue());

		char[] szExeFile = memory.getText(this, t2 += 4).toCharArray();
		for (int i = 0; i < szExeFile.length; i++) {
			lppe.szExeFile[i] = szExeFile[i];
			if (APIHandle.isDebug) {
				System.out.println("[" + i + "]: " + lppe.szExeFile[i]);
			}
		}

		if (APIHandle.isDebug) {
			System.out.println("szExeFile (" + Long.toHexString(t2) + ") size = " + szExeFile.length);
			System.out.println(lppe.toString(false));
		}

		BOOL ret = Kernel32DLL.INSTANCE.Process32First(hSnapshot, lppe);

		register.mov("eax", new LongValue(ret.booleanValue() ? 1 : 0));

		System.out.println("Return value: " + ret.booleanValue());
		if (APIHandle.isDebug && ret.booleanValue()) {
			System.out.println(lppe.toString(false));
			System.out.println("lppe.szExeFile: " + new String(lppe.szExeFile));
		}

		t2 = this.params.get(1);
		// memory.setDoubleWordMemoryValue(new
		// X86MemoryOperand(DataType.INT32, t2),
		// new LongValue(lppe.dwSize.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.cntUsage.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.th32ProcessID.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.th32DefaultHeapID.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.th32ModuleID.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.cntThreads.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.th32ParentProcessID.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.pcPriClassBase.longValue()));
		memory.setDoubleWordMemoryValue(t2 += 4, new LongValue(lppe.dwFlags.longValue()));
		for (int i = 0; i < lppe.szExeFile.length; i++) {
			memory.setByteMemoryValue(t2 + i, new LongValue(lppe.szExeFile[i]));
		}
	}

}
