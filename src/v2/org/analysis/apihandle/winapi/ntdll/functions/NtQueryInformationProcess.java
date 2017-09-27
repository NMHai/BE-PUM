/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ntdll.functions
 * File name: NtQueryInformationProcess.java
 * Created date: Jul 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ntdll.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.ntdll.NtdllAPI;
import v2.org.analysis.apihandle.winapi.ntdll.NtdllDLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.PROCESS_BASIC_INFORMATION;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.UNICODE_STRING;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.ULONGByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Retrieves information about the specified process.
 * 
 * @param ProcessHandle
 *            A handle to the process for which information is to be retrieved.
 * 
 * @param ProcessInformationClass
 *            The type of process information to be retrieved. This parameter
 *            can be one of the following values from the PROCESSINFOCLASS
 *            enumeration.
 * 
 * @param ProcessInformation
 *            A pointer to a buffer supplied by the calling application into
 *            which the function writes the requested information. The size of
 *            the information written varies depending on the data type of the
 *            ProcessInformationClass parameter:
 * 
 * @param ProcessInformationLength
 *            The size of the buffer pointed to by the ProcessInformation
 *            parameter, in bytes.
 * 
 * @param ReturnLength
 *            A pointer to a variable in which the function returns the size of
 *            the requested information. If the function was successful, this is
 *            the size of the information written to the buffer pointed to by
 *            the ProcessInformation parameter, but if the buffer was too small,
 *            this is the minimum size of buffer needed to receive the
 *            information successfully.
 * 
 * @return The function returns an NTSTATUS success or error code.
 * 
 * @author Yen Nguyen
 *
 */
public class NtQueryInformationProcess extends NtdllAPI {
	public NtQueryInformationProcess() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HANDLE ProcessHandle = new HANDLE(new Pointer(t1));
		int ProcessInformationClass = (int) t2;
		ULONG ProcessInformationLength = new ULONG((int) t4);
		ULONGByReference ReturnLength = new ULONGByReference();

		DWORD explorerPID = this.findPIDofAnyProcess("explorer");

		int ret = 0;

		switch (ProcessInformationClass) {
		case 0: {
			PROCESS_BASIC_INFORMATION.ByReference ProcessInformation = new PROCESS_BASIC_INFORMATION.ByReference();

			ret = NtdllDLL.INSTANCE.NtQueryInformationProcess(ProcessHandle, ProcessInformationClass,
					ProcessInformation, ProcessInformationLength, ReturnLength);

			if (ProcessInformation != null) {
				if (explorerPID != null) {
					// Users typically execute applications by clicking on
					// an icon which is displayed by the shell process
					// (Explorer.exe). As a result, the parent process of
					// the executed process will be Explorer.exe. Of
					// course, if the application is executed from the
					// command-line, then the parent process of the
					// executed process will be the command window process.
					// Executing an application by debugging it will cause
					// the parent process of the executed process to be the
					// debugger process.
					ProcessInformation.InheritedFromUniqueProcessId.setValue(explorerPID.longValue());
				}

				memory.setDoubleWordMemoryValue(t3, new LongValue(ProcessInformation.ExitStatus.longValue()));
				memory.setDoubleWordMemoryValue(t3 += 4, new LongValue(
				// Pointer.nativeValue(ProcessInformation.PebBaseAddress)
						0));
				memory.setDoubleWordMemoryValue(t3 += 4, new LongValue(ProcessInformation.AffinityMask.longValue()));
				memory.setDoubleWordMemoryValue(t3 += 4, new LongValue(ProcessInformation.BasePriority.longValue()));
				memory.setDoubleWordMemoryValue(t3 += 4, new LongValue(ProcessInformation.UniqueProcessId.longValue()));
				memory.setDoubleWordMemoryValue(t3 += 4,
						new LongValue(ProcessInformation.InheritedFromUniqueProcessId.longValue()));
			}
			break;
		}
		case 7: {
			// http://www.symantec.com/connect/articles/windows-anti-debug-reference
			// When called with ProcessInformationClass set to 7
			// (ProcessDebugPort constant), the system will set
			// ProcessInformation to -1 if the process is debugged.
			DWORDByReference ProcessInformation = new DWORDByReference();
			ret = NtdllDLL.INSTANCE.NtQueryInformationProcess(ProcessHandle, ProcessInformationClass,
					ProcessInformation, ProcessInformationLength, ReturnLength);

			if (ProcessInformation != null) {
				memory.setDoubleWordMemoryValue(t3, new LongValue(ProcessInformation.getValue().longValue()));
			}
		}
		case 27: {
			UNICODE_STRING.ByReference ProcessInformation = new UNICODE_STRING.ByReference();
			ret = NtdllDLL.INSTANCE.NtQueryInformationProcess(ProcessHandle, ProcessInformationClass,
					ProcessInformation, ProcessInformationLength, ReturnLength);

			if (ProcessInformation != null) {
				memory.setWordMemoryValue(t3, new LongValue(ProcessInformation.Length.longValue()));
				memory.setWordMemoryValue(t3 += 2, new LongValue(ProcessInformation.MaximumLength.longValue()));
				memory.setText(this, t3 += 2, ProcessInformation.Buffer.toString());
			}
		}
		case 29:
		default: {
			ULONGByReference ProcessInformation = new ULONGByReference();
			ret = NtdllDLL.INSTANCE.NtQueryInformationProcess(ProcessHandle, ProcessInformationClass,
					ProcessInformation, ProcessInformationLength, ReturnLength);

			if (ProcessInformation != null) {
				memory.setDoubleWordMemoryValue(t3, new LongValue(ProcessInformation.getValue().longValue()));
			}
		}
		}

		System.out.println("Return value:" + ret);
		register.mov("eax", new LongValue(ret));

		if (ReturnLength != null) {
			memory.setDoubleWordMemoryValue(t5, new LongValue(ReturnLength.getValue().longValue()));
		}

	}

	/**
	 * Find Process ID of the process that has the name is equal to input
	 * 
	 * @param processName
	 *            Process Name Input
	 * @return PID of expected process
	 */
	private DWORD findPIDofAnyProcess(String processName) {
		HANDLE processesSnapshot = Kernel32DLL.INSTANCE.CreateToolhelp32Snapshot(new DWORD(2), null);
		PROCESSENTRY32 processInfo = new PROCESSENTRY32();
		Kernel32DLL.INSTANCE.Process32First(processesSnapshot, processInfo);
		if ((new String(processInfo.szExeFile)).startsWith("explorer")) {
			Kernel32.INSTANCE.CloseHandle(processesSnapshot);
			// System.out.println(processInfo);
			return processInfo.th32ProcessID;
		} else {
			while (Kernel32DLL.INSTANCE.Process32Next(processesSnapshot, processInfo).booleanValue()) {
				if ((new String(processInfo.szExeFile)).startsWith("explorer")) {
					Kernel32.INSTANCE.CloseHandle(processesSnapshot);
					// System.out.println(processInfo);
					return processInfo.th32ProcessID;
				}
			}
		}

		return null;
	}
}
