package v2.org.analysis.apihandle.winapi.ntdll;

import v2.org.analysis.apihandle.winapi.structures.Winternl.OBJECT_ATTRIBUTES;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.PVOID;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.ULONGByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface NtdllDLL extends StdCallLibrary {
	NtdllDLL INSTANCE = (NtdllDLL) Native.loadLibrary("NtDll", NtdllDLL.class);

	/**
	 * Retrieves information about the specified process.
	 * 
	 * @param ProcessHandle
	 *            A handle to the process for which information is to be
	 *            retrieved.
	 * 
	 * @param ProcessInformationClass
	 *            The type of process information to be retrieved. This
	 *            parameter can be one of the following values from the
	 *            PROCESSINFOCLASS enumeration.
	 * 
	 * @param ProcessInformation
	 *            A pointer to a buffer supplied by the calling application into
	 *            which the function writes the requested information. The size
	 *            of the information written varies depending on the data type
	 *            of the ProcessInformationClass parameter:
	 * 
	 * @param ProcessInformationLength
	 *            The size of the buffer pointed to by the ProcessInformation
	 *            parameter, in bytes.
	 * 
	 * @param ReturnLength
	 *            A pointer to a variable in which the function returns the size
	 *            of the requested information. If the function was successful,
	 *            this is the size of the information written to the buffer
	 *            pointed to by the ProcessInformation parameter, but if the
	 *            buffer was too small, this is the minimum size of buffer
	 *            needed to receive the information successfully.
	 * 
	 * @return The function returns an NTSTATUS success or error code.
	 */
	/* NTSTATUS */int /* WINAPI */NtQueryInformationProcess(/* _In_ */HANDLE ProcessHandle, /* _In_ *//* PROCESSINFOCLASS */
			int ProcessInformationClass, /* _Out_ */Structure.ByReference ProcessInformation, /* _In_ */
			ULONG ProcessInformationLength, /* _Out_opt_ */
			ULONGByReference ReturnLength);

	/* NTSTATUS */int /* WINAPI */NtQueryInformationProcess(/* _In_ */HANDLE ProcessHandle, /* _In_ *//* PROCESSINFOCLASS */
			int ProcessInformationClass, /* _Out_ */com.sun.jna.ptr.ByReference ProcessInformation, /* _In_ */
			ULONG ProcessInformationLength, /* _Out_opt_ */
			ULONGByReference ReturnLength);

	int NtQueryInformation();

	/**
	 * Undocumented function
	 * 
	 * @param ProcessHandle
	 * @param BaseAddress
	 * @param NumberOfBytesToProtect
	 * @param NewAccessProtection
	 * @param OldAccessProtection
	 */
	LONG NtProtectVirtualMemory(
	/* IN */HANDLE ProcessHandle,
	/* IN_OUT */PointerByReference BaseAddress,
	/* IN_OUT */ULONGByReference NumberOfBytesToProtect,
	/* IN */ULONG NewAccessProtection,
	/* OUT */ULONGByReference OldAccessProtection);

	/**
	 * Undocumented function
	 * 
	 * @param ProcessHandle
	 * @param BaseAddress
	 * @param Buffer
	 * @param NumberOfBytesToWrite
	 * @param NumberOfBytesWritten
	 * @return
	 */
	LONG NtWriteVirtualMemory(
	/* IN */HANDLE ProcessHandle,
	/* IN */PVOID BaseAddress,
	/* IN */byte[] Buffer,
	/* IN */ULONG NumberOfBytesToWrite,
	/* OUT */ULONGByReference NumberOfBytesWritten /* OPTIONAL */);

	/**
	 * 
	 * @param SectionHandle
	 * @param DesiredAccess
	 * @param ObjectAttributes
	 * @param MaximumSize
	 * @param SectionPageProtection
	 * @param AllocationAttributes
	 * @param FileHandle
	 * @return
	 */
	LONG NtCreateSection(
	/* _Out_ */HANDLEByReference SectionHandle,
	/* _In_ *//* ACCESS_MASK */DWORD DesiredAccess,
	/* _In_opt_ */OBJECT_ATTRIBUTES ObjectAttributes,
	/* _In_opt_ */LARGE_INTEGER MaximumSize,
	/* _In_ */ULONG SectionPageProtection,
	/* _In_ */ULONG AllocationAttributes,
	/* _In_opt_ */HANDLE FileHandle);
}
