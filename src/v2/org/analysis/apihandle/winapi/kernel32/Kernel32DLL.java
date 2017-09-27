/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32
 * File name: Kernel32DLL.java
 * Created date: Jan 30, 2015
 * Decription:
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32;

import java.nio.ByteBuffer;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.MEMORYSTATUSEX;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;
import com.sun.jna.platform.win32.WinDef.ATOM;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.BOOLByReference;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.DWORDLONG;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LCID;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.LONGByReference;
import com.sun.jna.platform.win32.WinDef.LONGLONG;
import com.sun.jna.platform.win32.WinDef.LONGLONGByReference;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinDef.PVOID;
import com.sun.jna.platform.win32.WinDef.UCHAR;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.ULONGLONG;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinNT.ACL;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
import com.sun.jna.platform.win32.WinNT.OSVERSIONINFOEX;
import com.sun.jna.platform.win32.WinNT.PSID;
import com.sun.jna.platform.win32.WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.BY_HANDLE_FILE_INFORMATION;
import v2.org.analysis.apihandle.structures.CHAR_INFO;
import v2.org.analysis.apihandle.structures.COMMCONFIG;
import v2.org.analysis.apihandle.structures.COMMPROP;
import v2.org.analysis.apihandle.structures.COMSTAT;
import v2.org.analysis.apihandle.structures.CONSOLE_CURSOR_INFO;
import v2.org.analysis.apihandle.structures.CONSOLE_FONT_INFO;
import v2.org.analysis.apihandle.structures.CONSOLE_FONT_INFOEX;
import v2.org.analysis.apihandle.structures.CONSOLE_HISTORY_INFO;
import v2.org.analysis.apihandle.structures.CONSOLE_SCREEN_BUFFER_INFO;
import v2.org.analysis.apihandle.structures.CONSOLE_SCREEN_BUFFER_INFOEX;
import v2.org.analysis.apihandle.structures.CONSOLE_SELECTION_INFO;
import v2.org.analysis.apihandle.structures.COORD;
import v2.org.analysis.apihandle.structures.CURRENCYFMT;
import v2.org.analysis.apihandle.structures.DYNAMIC_TIME_ZONE_INFORMATION;
import v2.org.analysis.apihandle.structures.FILE_SEGMENT_ELEMENT;
import v2.org.analysis.apihandle.structures.HEAPENTRY32;
import v2.org.analysis.apihandle.structures.HEAPLIST32;
import v2.org.analysis.apihandle.structures.INPUT_RECORD;
import v2.org.analysis.apihandle.structures.IO_COUNTERS;
import v2.org.analysis.apihandle.structures.LDT_ENTRY;
import v2.org.analysis.apihandle.structures.MODULEENTRY32;
import v2.org.analysis.apihandle.structures.MODULEINFO;
import v2.org.analysis.apihandle.structures.NUMBERFMT;
import v2.org.analysis.apihandle.structures.OVERLAPPED_ENTRY;
import v2.org.analysis.apihandle.structures.PERFORMANCE_INFORMATION;
import v2.org.analysis.apihandle.structures.PROCESS_HEAP_ENTRY;
import v2.org.analysis.apihandle.structures.PROCESS_MEMORY_COUNTERS;
import v2.org.analysis.apihandle.structures.PSAPI_WS_WATCH_INFORMATION;
import v2.org.analysis.apihandle.structures.SMALL_RECT;
import v2.org.analysis.apihandle.structures.SYSTEM_POWER_STATUS;
import v2.org.analysis.apihandle.structures.ULARGE_INTEGER;
import v2.org.analysis.apihandle.winapi.structures.COMMTIMEOUTS;
import v2.org.analysis.apihandle.winapi.structures.COMPUTER_NAME_FORMAT;
import v2.org.analysis.apihandle.winapi.structures.DCB;
import v2.org.analysis.apihandle.winapi.structures.TIME_ZONE_INFORMATION;
import v2.org.analysis.apihandle.winapi.structures.WinBase.MEMORYSTATUS;
import v2.org.analysis.apihandle.winapi.structures.WinBase.OFSTRUCT;
import v2.org.analysis.apihandle.winapi.structures.WinBase.THREADENTRY32;
import v2.org.analysis.apihandle.winapi.structures.WinBase.WIN32_FIND_DATA;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.CPINFO;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.EXCEPTION_RECORD;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.MEMORY_BASIC_INFORMATION;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.PRTL_CRITICAL_SECTION;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.RTL_CRITICAL_SECTION;
import v2.org.analysis.apihandle.winapi.structures.Winnls.CPINFOEX;

/**
 * @author Yen Nguyen
 *
 */

public interface Kernel32DLL extends StdCallLibrary {
	Kernel32DLL INSTANCE = (Kernel32DLL) Native.loadLibrary("kernel32", Kernel32DLL.class,
			W32APIOptions.DEFAULT_OPTIONS);
	Kernel32DLL SYNC_INSTANCE = (Kernel32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Runs the specified application.
	 * 
	 * @param lpCmdLine
	 * 
	 *            <p>
	 *            The command line (file name plus optional parameters) for the
	 *            application to be executed. If the name of the executable file
	 *            in the lpCmdLine parameter does not contain a directory path,
	 *            the system searches for the executable file in this sequence:
	 *            </p>
	 * 
	 *            <ol>
	 *            <li>The directory from which the application loaded.
	 *            <li>The current directory.
	 *            <li>The Windows system directory. The GetSystemDirectory
	 *            function retrieves the path of this directory.
	 *            <li>The Windows directory. The GetWindowsDirectory function
	 *            retrieves the path of this directory.
	 *            <li>The directories listed in the PATH environment variable.
	 *            </ol>
	 * 
	 * @param uCmdShow
	 *            <p>
	 *            The display options. For a list of the acceptable values, see
	 *            the description of the nCmdShow parameter of the ShowWindow
	 *            function.
	 *            </p>
	 * @return <p>
	 *         If the function succeeds, the return value is greater than 31.
	 *         </p>
	 *         <p>
	 *         If the function fails, the return value is one of the following
	 *         error values.
	 *         </p>
	 */
	int WinExec(String lpCmdLine, int uCmdShow);

	/**
	 * Retrieves the address of an exported function or variable from the
	 * specified dynamic-link library (DLL).
	 * 
	 * @param hModule
	 *            <p>
	 *            A handle to the DLL module that contains the function or
	 *            variable. The LoadLibrary, LoadLibraryEx, LoadPackagedLibrary,
	 *            or GetModuleHandle function returns this handle.
	 *            </p>
	 *            <p>
	 *            The GetProcAddress function does not retrieve addresses from
	 *            modules that were loaded using the LOAD_LIBRARY_AS_DATAFILE
	 *            flag. For more information, see LoadLibraryEx.
	 *            </p>
	 * @param lpProcName
	 *            <p>
	 *            The function or variable name, or the function's ordinal
	 *            value. If this parameter is an ordinal value, it must be in
	 *            the low-order word; the high-order word must be zero.
	 *            </p>
	 * @return If the function succeeds, the return value is the address of the
	 *         exported function or variable.
	 */
	long GetProcAddress(HMODULE hModule, String lpProcName);

	/**
	 * Loads the specified module into the address space of the calling process.
	 * The specified module may cause other modules to be loaded.
	 * 
	 * @param lpFileName
	 *            : The name of the module. This can be either a library module
	 *            (a .dll file) or an executable module (an .exe file). The name
	 *            specified is the file name of the module and is not related to
	 *            the name stored in the library module itself, as specified by
	 *            the LIBRARY keyword in the module-definition (.def) file.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         module.
	 */
	HMODULE LoadLibrary(String lpFileName);

	/**
	 * Loads the specified module into the address space of the calling process.
	 * The specified module may cause other modules to be loaded.
	 * 
	 * @param lpFileName
	 *            A string that specifies the file name of the module to load.
	 *            This name is not related to the name stored in a library
	 *            module itself, as specified by the LIBRARY keyword in the
	 *            module-definition (.def) file.
	 * 
	 * @param hFile
	 *            This parameter is reserved for future use. It must be NULL.
	 * 
	 * @param dwFlags
	 *            The action to be taken when loading the module. If no flags
	 *            are specified, the behavior of this function is identical to
	 *            that of the LoadLibrary function. This parameter can be one of
	 *            the following values.
	 *            <ul>
	 *            DONT_RESOLVE_DLL_REFERENCES 0x00000001
	 *            <li>LOAD_IGNORE_CODE_AUTHZ_LEVEL 0x00000010
	 *            <li>LOAD_LIBRARY_AS_DATAFILE 0x00000002
	 *            <li>LOAD_LIBRARY_AS_DATAFILE_EXCLUSIVE 0x00000040
	 *            <li>LOAD_LIBRARY_AS_IMAGE_RESOURCE 0x00000020
	 *            <li>LOAD_LIBRARY_SEARCH_APPLICATION_DIR 0x00000200
	 *            <li>LOAD_LIBRARY_SEARCH_DEFAULT_DIRS 0x00001000
	 *            <li>LOAD_LIBRARY_SEARCH_DLL_LOAD_DIR 0x00000100
	 *            <li>LOAD_LIBRARY_SEARCH_SYSTEM32 0x00000800
	 *            <li>LOAD_LIBRARY_SEARCH_USER_DIRS 0x00000400
	 *            <li>LOAD_WITH_ALTERED_SEARCH_PATH 0x00000008
	 *            </ul>
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         loaded module. If the function fails, the return value is NULL.
	 *         To get extended error information, call GetLastError.
	 */
	HMODULE LoadLibraryEx(/* _In_ */String lpFileName, /* _Reserved_ */HANDLE hFile, /* _In_ */DWORD dwFlags);

	/**
	 * Moves the file pointer of the specified file.
	 * 
	 * This function stores the file pointer in two LONG values. To work with
	 * file pointers that are larger than a single LONG value, it is easier to
	 * use the SetFilePointerEx function.
	 * 
	 * @param hFile
	 *            : A handle to the file.
	 * @param lDistanceToMove
	 *            : The low order 32-bits of a signed value that specifies the
	 *            number of bytes to move the file pointer.
	 * @param lpDistanceToMoveHigh
	 *            : A pointer to the high order 32-bits of the signed 64-bit
	 *            distance to move.
	 * @param dwMoveMethod
	 *            : The starting point for the file pointer move.
	 * @return <p>
	 *         If the function succeeds and lpDistanceToMoveHigh is NULL, the
	 *         return value is the low-order DWORD of the new file pointer.
	 *         </p>
	 * 
	 *         <p>
	 *         If function succeeds and lpDistanceToMoveHigh is not NULL, the
	 *         return value is the low-order DWORD of the new file pointer and
	 *         lpDistanceToMoveHigh contains the high order DWORD of the new
	 *         file pointer.
	 *         </p>
	 */
	DWORD SetFilePointer(HANDLE hFile, LONG lDistanceToMove, LONGByReference lpDistanceToMoveHigh, DWORD dwMoveMethod);

	/**
	 * Closes a file search handle opened by the FindFirstFile, FindFirstFileEx,
	 * FindFirstFileNameW, FindFirstFileNameTransactedW,
	 * FindFirstFileTransacted, FindFirstStreamTransactedW, or FindFirstStreamW
	 * functions.
	 * 
	 * @param hFindFile
	 *            : The file search handle.
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL FindClose(HANDLE hFindFile);

	/**
	 * Sets the physical file size for the specified file to the current
	 * position of the file pointer. The physical file size is also referred to
	 * as the end of the file. The SetEndOfFile function can be used to truncate
	 * or extend a file. To set the logical end of a file, use the
	 * SetFileValidData function.
	 * 
	 * @param hFile
	 *            : A handle to the file to be extended or truncated.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL SetEndOfFile(HANDLE hFile);

	/**
	 * Appends one string to another.
	 * 
	 * @param lpString1
	 *            : The first null-terminated string. This buffer must be large
	 *            enough to contain both strings.
	 * 
	 * @param lpString2
	 *            : The null-terminated string to be appended to the string
	 *            specified in the lpString1 parameter.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         buffer.
	 */
	String lstrcat(String lpString1, String lpString2);

	/**
	 * Compares two character strings. The comparison is case-sensitive.
	 * 
	 * @param lpString1
	 *            : The first null-terminated string to be compared.
	 * 
	 * @param lpString2
	 *            : The second null-terminated string to be compared.
	 * 
	 * @return If the string pointed to by lpString1 is less than the string
	 *         pointed to by lpString2, the return value is negative. If the
	 *         string pointed to by lpString1 is greater than the string pointed
	 *         to by lpString2, the return value is positive. If the strings are
	 *         equal, the return value is zero.
	 */
	int lstrcmp(String lpString1, String lpString2);

	/**
	 * Determines the length of the specified string (not including the
	 * terminating null character).
	 * 
	 * @param lpString
	 *            : The null-terminated string to be checked.
	 * 
	 * @return The function returns the length of the string, in characters. If
	 *         lpString is NULL, the function returns 0.
	 */
	int lstrlen(String lpString);

	/**
	 * Copies a string to a buffer.
	 * 
	 * @param lpString1
	 *            : A buffer to receive the contents of the string pointed to by
	 *            the lpString2 parameter. The buffer must be large enough to
	 *            contain the string, including the terminating null character.
	 * 
	 * @param lpString2
	 *            : The null-terminated string to be copied.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         buffer.
	 */
	WString lstrcpy(WString lpString1, WString lpString2);

	WString lstrcpy(char[] lpString1, WString lpString2);

	// HANDLE CreateThread(SECURITY_ATTRIBUTES lpThreadAttributes, SIZE_T
	// dwStackSize,
	// THREAD_START_ROUTINE lpStartAddress, LPVOID lpParameter, DWORD
	// dwCreationFlags, DWORDByReference lpThreadId);

	/**
	 * Ends the calling process and all its threads.
	 * 
	 * @param uExitCode
	 *            : The exit code for the process and all threads.
	 */
	void ExitProcess(int uExitCode);

	/**
	 * Retrieves the environment variables for the current process.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         environment block of the current process. If the function fails,
	 *         the return value is NULL.
	 */
	Pointer GetEnvironmentStrings();

	/**
	 * Frees a block of environment strings.
	 * 
	 * @param lpszEnvironmentBlock
	 *            : A pointer to a block of environment strings. The pointer to
	 *            the block must be obtained by calling the
	 *            GetEnvironmentStrings function.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL FreeEnvironmentStrings(Pointer lpszEnvironmentBlock);

	/**
	 * Retrieves the command-line string for the current process.
	 * 
	 * @return The return value is a pointer to the command-line string for the
	 *         current process.
	 */
	Pointer GetCommandLine();

	/**
	 * Determines whether the calling process is being debugged by a user-mode
	 * debugger.
	 * 
	 * @return If the current process is running in the context of a debugger,
	 *         the return value is nonzero. If the current process is not
	 *         running in the context of a debugger, the return value is zero.
	 */
	boolean IsDebuggerPresent();

	/**
	 * Retrieves the contents of the STARTUPINFO structure that was specified
	 * when the calling process was created.
	 * 
	 * @param lpStartupInfo
	 *            : A pointer to a STARTUPINFO structure that receives the
	 *            startup information.
	 */
//	void GetStartupInfo(STARTUPINFO lpStartupInfo);
	void GetStartupInfo(Pointer lpStartupInfo);

	/**
	 * Retrieves a handle to the specified standard device (standard input,
	 * standard output, or standard error).
	 * 
	 * @param nStdHandle
	 *            : The standard device. This parameter can be one of the
	 *            following values.
	 * @return <p>
	 *         If the function succeeds, the return value is a handle to the
	 *         specified device, or a redirected handle set by a previous call
	 *         to SetStdHandle. The handle has GENERIC_READ and GENERIC_WRITE
	 *         access rights, unless the application has used SetStdHandle to
	 *         set a standard handle with lesser access.
	 *         </p>
	 *         <p>
	 *         If the function fails, the return value is INVALID_HANDLE_VALUE.
	 *         To get extended error information, call GetLastError.
	 *         </p>
	 *         <p>
	 *         If an application does not have associated standard handles, such
	 *         as a service running on an interactive desktop, and has not
	 *         redirected them, the return value is NULL.
	 *         </p>
	 */
	HANDLE GetStdHandle(DWORD nStdHandle);

	HANDLE GetStdHandle(int nStdHandle);

	/**
	 * Allocates a block of memory from a heap. The allocated memory is not
	 * movable.
	 * 
	 * @param hHeap
	 *            : A handle to the heap from which the memory will be
	 *            allocated. This handle is returned by the HeapCreate or
	 *            GetProcessHeap function.
	 * 
	 * @param dwFlags
	 *            : The heap allocation options. Specifying any of these values
	 *            will override the corresponding value specified when the heap
	 *            was created with HeapCreate. This parameter can be one or more
	 *            of the following values.
	 * 
	 * @param dwBytes
	 *            : The number of bytes to be allocated.
	 * 
	 * @return <p>
	 *         If the function succeeds, the return value is a pointer to the
	 *         allocated memory block.
	 *         </p>
	 *         <p>
	 *         If the function fails and you have not specified
	 *         <strong>HEAP_GENERATE_EXCEPTIONS</strong>, the return value is
	 *         <strong>NULL</strong>.
	 *         </p>
	 *         <p>
	 *         If the function fails and you have specified
	 *         <strong>HEAP_GENERATE_EXCEPTIONS</strong>, the function may
	 *         generate either of the exceptions listed in the following table.
	 *         The particular exception depends upon the nature of the heap
	 *         corruption. For more information, see <a href=
	 *         "https://msdn.microsoft.com/en-us/library/windows/desktop/ms679356(v=vs.85).aspx"
	 *         ><strong
	 *         xmlns="http://www.w3.org/1999/xhtml">GetExceptionCode</strong
	 *         ></a>.
	 *         </p>
	 */
	LPVOID HeapAlloc(HANDLE hHeap, DWORD dwFlags, SIZE_T dwBytes);

	/**
	 * Creates a private heap object that can be used by the calling process.
	 * The function reserves space in the virtual address space of the process
	 * and allocates physical storage for a specified initial portion of this
	 * block.
	 * 
	 * @param flOptions
	 *            : The heap allocation options. These options affect subsequent
	 *            access to the new heap through calls to the heap functions.
	 *            This parameter can be 0 or one or more of the following
	 *            values.
	 * 
	 * @param dwInitialSize
	 *            :
	 *            <p>
	 *            The initial size of the heap, in bytes. This value determines
	 *            the initial amount of memory that is committed for the heap.
	 *            The value is rounded up to a multiple of the system page size.
	 *            The value must be smaller than <em>dwMaximumSize</em>.
	 *            </p>
	 *            <p>
	 *            If this parameter is 0, the function commits one page. To
	 *            determine the size of a page on the host computer, use the <a
	 *            href=
	 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/ms724381(v=vs.85).aspx"
	 *            ><strong
	 *            xmlns="http://www.w3.org/1999/xhtml">GetSystemInfo</strong
	 *            ></a> function.
	 *            </p>
	 * 
	 * @param dwMaximumSize
	 *            :
	 *            <p>
	 *            The maximum size of the heap, in bytes. The
	 *            <strong>HeapCreate</strong> function rounds
	 *            <em>dwMaximumSize</em> up to a multiple of the system page
	 *            size and then reserves a block of that size in the process's
	 *            virtual address space for the heap. If allocation requests
	 *            made by the <a href=
	 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366597(v=vs.85).aspx"
	 *            ><strong
	 *            xmlns="http://www.w3.org/1999/xhtml">HeapAlloc</strong></a> or
	 *            <a href=
	 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366704(v=vs.85).aspx"
	 *            ><strong
	 *            xmlns="http://www.w3.org/1999/xhtml">HeapReAlloc</strong></a>
	 *            functions exceed the size specified by <em>dwInitialSize</em>,
	 *            the system commits additional pages of memory for the heap, up
	 *            to the heap's maximum size.
	 *            </p>
	 * 
	 *            <p>
	 *            If <em>dwMaximumSize</em> is not zero, the heap size is fixed
	 *            and cannot grow beyond the maximum size. Also, the largest
	 *            memory block that can be allocated from the heap is slightly
	 *            less than 512 KB for a 32-bit process and slightly less than
	 *            1,024 KB for a 64-bit process. Requests to allocate larger
	 *            blocks fail, even if the maximum size of the heap is large
	 *            enough to contain the block.
	 *            </p>
	 * 
	 *            <p>
	 *            If <em>dwMaximumSize</em> is 0, the heap can grow in size. The
	 *            heap's size is limited only by the available memory. Requests
	 *            to allocate memory blocks larger than the limit for a
	 *            fixed-size heap do not automatically fail; instead, the system
	 *            calls the <a href=
	 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366887(v=vs.85).aspx"
	 *            ><strong
	 *            xmlns="http://www.w3.org/1999/xhtml">VirtualAlloc</strong></a>
	 *            function to obtain the memory that is needed for large blocks.
	 *            Applications that need to allocate large memory blocks should
	 *            set <em>dwMaximumSize</em> to 0.
	 *            </p>
	 * @return <p>
	 *         If the function succeeds, the return value is a handle to the
	 *         newly created heap.
	 *         </p>
	 *         <p>
	 *         If the function fails, the return value is <strong>NULL</strong>.
	 *         To get extended error information, call <a href=
	 *         "https://msdn.microsoft.com/en-us/library/windows/desktop/ms679360(v=vs.85).aspx"
	 *         ><strong
	 *         xmlns="http://www.w3.org/1999/xhtml">GetLastError</strong></a>.
	 *         </p>
	 */
	HANDLE HeapCreate(DWORD flOptions, SIZE_T dwInitialSize, SIZE_T dwMaximumSize);
	
	/*Enables features for a specified heap.
	Parameters

	HeapHandle [in, optional]

	    A handle to the heap where information is to be set. This handle is returned by either the HeapCreate or GetProcessHeap function.
	HeapInformationClass [in]

	    The class of information to be set. This parameter can be one of the following values from the HEAP_INFORMATION_CLASS enumeration type.
	
	HANDLE HeapCreate(DWORD flOptions, SIZE_T dwInitialSize, SIZE_T dwMaximumSize);
	HeapInformation [in]

	    The heap information buffer. The format of this data depends on the value of the HeapInformationClass parameter.

	    If the HeapInformationClass parameter is HeapCompatibilityInformation, the HeapInformation parameter is a pointer to a ULONG variable.

		If the HeapInformationClass parameter is HeapEnableTerminationOnCorruption, the HeapInformation parameter should be NULL and HeapInformationLength should be 0
	HeapInformationLength [in]

		The size of the HeapInformation buffer, in bytes.

	Return value

		If the function succeeds, the return value is nonzero.

		If the function fails, the return value is 0 (zero)	*/
	// HaiNM: implement the HeapSetInformation 
	BOOL HeapSetInformation(HANDLE HeapHandle, int HeapInformationClass, LPVOID HeapInformation, SIZE_T HeapInformationLength);
	
	/**
	 * Destroys the specified heap object. It decommits and releases all the
	 * pages of a private heap object, and it invalidates the handle to the
	 * heap.
	 * 
	 * @param hHeap
	 *            :
	 *            <p>
	 *            A handle to the heap to be destroyed. This handle is returned
	 *            by the <a href=
	 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366599(v=vs.85).aspx"
	 *            ><strong
	 *            xmlns="http://www.w3.org/1999/xhtml">HeapCreate</strong></a>
	 *            function. Do not use the handle to the process heap returned
	 *            by the <a href=
	 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366569(v=vs.85).aspx"
	 *            ><strong
	 *            xmlns="http://www.w3.org/1999/xhtml">GetProcessHeap</strong
	 *            ></a> function.
	 *            </p>
	 * 
	 * @return <p>
	 *         If the function succeeds, the return value is nonzero.
	 *         </p>
	 *         <p>
	 *         If the function fails, the return value is zero. To get extended
	 *         error information, call <a href=
	 *         "https://msdn.microsoft.com/en-us/library/windows/desktop/ms679360(v=vs.85).aspx"
	 *         ><strong
	 *         xmlns="http://www.w3.org/1999/xhtml">GetLastError</strong></a>.
	 *         </p>
	 */
	boolean HeapDestroy(HANDLE hHeap);

	/**
	 * Frees a memory block allocated from a heap by the HeapAlloc or
	 * HeapReAlloc function.
	 * 
	 * @param hHeap
	 *            : A handle to the heap whose memory block is to be freed. This
	 *            handle is returned by either the HeapCreate or GetProcessHeap
	 *            function.
	 * 
	 * @param dwFlags
	 *            : The heap free options. Specifying the following value
	 *            overrides the corresponding value specified in the flOptions
	 *            parameter when the heap was created by using the HeapCreate
	 *            function.
	 * 
	 * @param lpMem
	 *            : A pointer to the memory block to be freed. This pointer is
	 *            returned by the HeapAlloc or HeapReAlloc function. If this
	 *            pointer is NULL, the behavior is undefined.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	boolean HeapFree(HANDLE hHeap, DWORD dwFlags, LPVOID lpMem);

	/**
	 * Reallocates a block of memory from a heap. This function enables you to
	 * resize a memory block and change other memory block properties. The
	 * allocated memory is not movable.
	 * 
	 * @param hHeap
	 *            : A handle to the heap from which the memory is to be
	 *            reallocated. This handle is a returned by either the
	 *            HeapCreate or GetProcessHeap function.
	 * 
	 * @param dwFlags
	 *            : The heap reallocation options. Specifying a value overrides
	 *            the corresponding value specified in the flOptions parameter
	 *            when the heap was created by using the HeapCreate function.
	 *            This parameter can be one or more of the following values.
	 * 
	 * @param lpMem
	 *            : A pointer to the block of memory that the function
	 *            reallocates. This pointer is returned by an earlier call to
	 *            the HeapAlloc or HeapReAlloc function.
	 * 
	 * @param dwBytes
	 *            : The new size of the memory block, in bytes. A memory block's
	 *            size can be increased or decreased by using this function. If
	 *            the heap specified by the hHeap parameter is a "non-growable"
	 *            heap, dwBytes must be less than 0x7FFF8. You create a
	 *            non-growable heap by calling the HeapCreate function with a
	 *            nonzero value.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         reallocated memory block.
	 */
	LPVOID HeapReAlloc(HANDLE hHeap, DWORD dwFlags, LPVOID lpMem, SIZE_T dwBytes);

	/**
	 * The SetHandleCount function sets the number of file handles available to
	 * a process. This function has no effect under Windows NT and Windows 95,
	 * because there is no explicit file handle limit for applications on these
	 * platforms. Under Win32s, there are only 20 file handles available to a
	 * process by default; however you could use SetHandleCount to allow a
	 * process to use up to 255 file handles.
	 * 
	 * @param uNumber
	 *            : Specifies the number of file handles needed by the
	 *            application.
	 * 
	 * @return Under Windows NT and Windows 95, this function simply returns the
	 *         value specified in the uNumber parameter. Under Win32s, the
	 *         return value specifies the number of file handles actually
	 *         available to the application. It may be fewer than the number
	 *         specified by the uNumber parameter.
	 */
	UINT SetHandleCount(UINT uNumber);

	/**
	 * Releases, decommits, or releases and decommits a region of pages within
	 * the virtual address space of the calling process.
	 * 
	 * @param lpAddress
	 *            : A pointer to the base address of the region of pages to be
	 *            freed.
	 * 
	 * @param dwSize
	 *            : The size of the region of memory to be freed, in bytes.
	 * 
	 * @param dwFreeType
	 *            : The type of free operation. This parameter can be one of the
	 *            following values.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL VirtualFree(LPVOID lpAddress, SIZE_T dwSize, DWORD dwFreeType);

	/**
	 * Searches a directory for a file or subdirectory with a name that matches
	 * a specific name (or partial name if wildcards are used).
	 * 
	 * @param lpFileName
	 *            : The directory or path, and the file name, which can include
	 *            wildcard characters, for example, an asterisk (*) or a
	 *            question mark (?).
	 * 
	 * @param lpFindFileData
	 *            : A pointer to the WIN32_FIND_DATA structure that receives
	 *            information about a found file or directory.
	 * 
	 * @return If the function succeeds, the return value is a search handle
	 *         used in a subsequent call to FindNextFile or FindClose, and the
	 *         lpFindFileData parameter contains information about the first
	 *         file or directory found.
	 */
	HANDLE FindFirstFile(WString lpFileName, WIN32_FIND_DATA lpFindFileData);

	/**
	 * Continues a file search from a previous call to the FindFirstFile,
	 * FindFirstFileEx, or FindFirstFileTransacted functions.
	 * 
	 * @param hFindFile
	 *            : The search handle returned by a previous call to the
	 *            FindFirstFile or FindFirstFileEx function.
	 * 
	 * @param lpFindFileData
	 *            : A pointer to the WIN32_FIND_DATA structure that receives
	 *            information about the found file or subdirectory.
	 * 
	 * @return If the function succeeds, the return value is nonzero and the
	 *         lpFindFileData parameter contains information about the next file
	 *         or directory found.
	 */
	BOOL FindNextFile(HANDLE hFindFile, WIN32_FIND_DATA lpFindFileData);

	/**
	 * Changes the current directory for the current process.
	 * 
	 * @param lpPathName
	 *            : The path to the new current directory. This parameter may
	 *            specify a relative path or a full path. In either case, the
	 *            full path of the specified directory is calculated and stored
	 *            as the current directory. For more information, see File
	 *            Names, Paths, and Namespaces.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
//	BOOL SetCurrentDirectory(Pointer lpPathName);
	BOOL SetCurrentDirectory(WString lpPathName);

	/**
	 * Retrieves the current directory for the current process.
	 * 
	 * @param nBufferLength
	 *            : The length of the buffer for the current directory string,
	 *            in TCHARs. The buffer length must include room for a
	 *            terminating null character.
	 * 
	 * @param lpBuffer
	 *            : A pointer to the buffer that receives the current directory
	 *            string. This null-terminated string specifies the absolute
	 *            path to the current directory. To determine the required
	 *            buffer size, set this parameter to NULL and the nBufferLength
	 *            parameter to 0.
	 * 
	 * @return If the function succeeds, the return value specifies the number
	 *         of characters that are written to the buffer, not including the
	 *         terminating null character.
	 */
	int GetCurrentDirectory(/* _In_ */DWORD nBufferLength, /* _Out_ */
			char[] lpBuffer); // char type has been verified
//	DWORD GetCurrentDirectory(/* _In_ */DWORD nBufferLength, /* _Out_ */
//			Pointer lpBuffer); // char type has been verified
//	WORD GetCurrentDirectory(/* _In_ */WORD nBufferLength, /* _Out_ */
//			Pointer lpBuffer); // char type has been verified
	/**
	 * Retrieves the path of the system directory. The system directory contains
	 * system files such as dynamic-link libraries and drivers.
	 * 
	 * @param lpBuffer
	 *            : A pointer to the buffer to receive the path. This path does
	 *            not end with a backslash unless the system directory is the
	 *            root directory. For example, if the system directory is named
	 *            Windows\System32 on drive C, the path of the system directory
	 *            retrieved by this function is C:\Windows\System32.
	 * 
	 * @param uSize
	 *            : The maximum size of the buffer, in TCHARs.
	 * 
	 * @return If the function succeeds, the return value is the length, in
	 *         TCHARs, of the string copied to the buffer, not including the
	 *         terminating null character. If the length is greater than the
	 *         size of the buffer, the return value is the size of the buffer
	 *         required to hold the path, including the terminating null
	 *         character.
	 */
	UINT GetSystemDirectory(
	/* _Out_writes_to_opt_(uSize, return + 1) */char[] lpBuffer, /* _In_ */
			UINT uSize);

	/**
	 * Retrieves the path of the Windows directory.
	 * 
	 * @param lpBuffer
	 *            A pointer to a buffer that receives the path. This path does
	 *            not end with a backslash unless the Windows directory is the
	 *            root directory. For example, if the Windows directory is named
	 *            Windows on drive C, the path of the Windows directory
	 *            retrieved by this function is C:\Windows. If the system was
	 *            installed in the root directory of drive C, the path retrieved
	 *            is C:\.
	 * 
	 * @param uSize
	 *            The maximum size of the buffer specified by the lpBuffer
	 *            parameter, in TCHARs. This value should be set to MAX_PATH.
	 * 
	 * @return If the function succeeds, the return value is the length of the
	 *         string copied to the buffer, in TCHARs, not including the
	 *         terminating null character.
	 */
	UINT GetWindowsDirectory(/* _Out_ */char[] lpBuffer, /* _In_ */UINT uSize);
//	UINT GetWindowsDirectory(/* _Out_ */Pointer lpBuffer, /* _In_ */UINT uSize);

	/**
	 * Retrieves the fully qualified path for the file that contains the
	 * specified module. The module must have been loaded by the current
	 * process.
	 * 
	 * @param hModule
	 *            A handle to the loaded module whose path is being requested.
	 *            If this parameter is NULL, GetModuleFileName retrieves the
	 *            path of the executable file of the current process.
	 * 
	 * @param lpFilename
	 *            A pointer to a buffer that receives the fully qualified path
	 *            of the module. If the length of the path is less than the size
	 *            that the nSize parameter specifies, the function succeeds and
	 *            the path is returned as a null-terminated string.
	 * 
	 * @param nSize
	 *            The size of the lpFilename buffer, in TCHARs.
	 * 
	 * @return If the function succeeds, the return value is the length of the
	 *         string that is copied to the buffer, in characters, not including
	 *         the terminating null character. If the buffer is too small to
	 *         hold the module name, the string is truncated to nSize characters
	 *         including the terminating null character, the function returns
	 *         nSize, and the function sets the last error to
	 *         ERROR_INSUFFICIENT_BUFFER.
	 */
	DWORD GetModuleFileName(/* _In_opt_ */HMODULE hModule, /* _Out_ */
			char[] lpFilename, /* _In_ */DWORD nSize);

	int _lclose(/* _In_ */int hFile);

	int _lcreat(/* _In_ */String lpPathName, /* _In_ */int iAttribute);

	int _lopen(/* _In_ */String lpPathName, /* _In_ */int iReadWrite);

	LONG _llseek(/* _In_ */int hFile, /* _In_ */LONG lOffset, /* _In_ */
			int iOrigin);

	/**
	 * Compares two file times.
	 * 
	 * @param lpFileTime1
	 *            [in] A pointer to a FILETIME structure that specifies the
	 *            first file time.
	 * 
	 * @param lpFileTime2
	 *            [in] A pointer to a FILETIME structure that specifies the
	 *            second file time.
	 * 
	 * @return The return value is one of the following values.
	 */
	LONG CompareFileTime(/* _In_ */FILETIME lpFileTime1, /* _In_ */
			FILETIME lpFileTime2);

	/**
	 * Compares two character strings, for a locale specified by identifier.
	 * 
	 * @param Locale
	 *            [in] Locale identifier of the locale used for the comparison.
	 *            You can use the MAKELCID macro to create a locale identifier
	 *            or use one of the following predefined values.
	 *
	 * @param dwCmpFlags
	 *            [in] Flags that indicate how the function compares the two
	 *            strings. For detailed definitions, see the dwCmpFlags
	 *            parameter of CompareStringEx.
	 * 
	 * @param lpString1
	 *            [in] Pointer to the first string to compare.
	 * 
	 * @param cchCount1
	 *            [in] Length of the string indicated by lpString1, excluding
	 *            the terminating null character. This value represents bytes
	 *            for the ANSI version of the function and wide characters for
	 *            the Unicode version. The application can supply a negative
	 *            value if the string is null-terminated. In this case, the
	 *            function determines the length automatically.
	 * 
	 * @param lpString2
	 *            [in] Pointer to the second string to compare.
	 * 
	 * @param cchCount2
	 *            [in] Length of the string indicated by lpString2, excluding
	 *            the terminating null character. This value represents bytes
	 *            for the ANSI version of the function and wide characters for
	 *            the Unicode version. The application can supply a negative
	 *            value if the string is null-terminated. In this case, the
	 *            function determines the length automatically.
	 * 
	 * @return
	 */
	int CompareString(/* _In_ */LCID Locale, /* _In_ */DWORD dwCmpFlags, WString lpString1, /* _In_ */int cchCount1,
			WString lpString2,/* _In_ */
			int cchCount2);

	/**
	 * Creates a new directory. If the underlying file system supports security
	 * on files and directories, the function applies a specified security
	 * descriptor to the new directory.
	 * 
	 * @param lpPathName
	 *            The path of the directory to be created.
	 * 
	 * @param lpSecurityAttributes
	 *            A pointer to a SECURITY_ATTRIBUTES structure. The
	 *            lpSecurityDescriptor member of the structure specifies a
	 *            security descriptor for the new directory. If
	 *            lpSecurityAttributes is NULL, the directory gets a default
	 *            security descriptor. The ACLs in the default security
	 *            descriptor for a directory are inherited from its parent
	 *            directory.
	 * 
	 *            The target file system must support security on files and
	 *            directories for this parameter to have an effect. (This is
	 *            indicated when GetVolumeInformation returns
	 *            FS_PERSISTENT_ACLS.)
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 * 
	 *         If the function fails, the return value is zero. To get extended
	 *         error information, call GetLastError. Possible errors include the
	 *         following.
	 */
	BOOL CreateDirectory(/* _In_ */String lpPathName, /* _In_opt_ */
			SECURITY_ATTRIBUTES lpSecurityAttributes);

	/**
	 * Creates or opens a named or unnamed event object.
	 * 
	 * @param lpEventAttributes
	 *            [in, optional] A pointer to a SECURITY_ATTRIBUTES structure.
	 *            If this parameter is NULL, the handle cannot be inherited by
	 *            child processes.
	 * 
	 * @param bManualReset
	 *            [in] If this parameter is TRUE, the function creates a
	 *            manual-reset event object, which requires the use of the
	 *            ResetEvent function to set the event state to nonsignaled. If
	 *            this parameter is FALSE, the function creates an auto-reset
	 *            event object, and system automatically resets the event state
	 *            to nonsignaled after a single waiting thread has been
	 *            released.
	 * 
	 * @param bInitialState
	 *            [in] If this parameter is TRUE, the initial state of the event
	 *            object is signaled; otherwise, it is nonsignaled.
	 * 
	 * @param lpName
	 *            [in, optional] The name of the event object. The name is
	 *            limited to MAX_PATH characters. Name comparison is case
	 *            sensitive.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         event object. If the named event object existed before the
	 *         function call, the function returns a handle to the existing
	 *         object and GetLastError returns ERROR_ALREADY_EXISTS.
	 */
	HANDLE CreateEvent(/* _In_opt_ */SECURITY_ATTRIBUTES lpEventAttributes, /* _In_ */
			BOOL bManualReset, /* _In_ */
			BOOL bInitialState, /* _In_opt_ */String lpName);

	/**
	 * Creates or opens a named or unnamed mutex object.
	 * 
	 * @param lpMutexAttributes
	 *            [in, optional] A pointer to a SECURITY_ATTRIBUTES structure.
	 *            If this parameter is NULL, the handle cannot be inherited by
	 *            child processes.
	 * 
	 * @param bInitialOwner
	 *            [in] If this value is TRUE and the caller created the mutex,
	 *            the calling thread obtains initial ownership of the mutex
	 *            object. Otherwise, the calling thread does not obtain
	 *            ownership of the mutex. To determine if the caller created the
	 *            mutex, see the Return Values section.
	 * 
	 * @param lpName
	 *            [in, optional] The name of the mutex object. The name is
	 *            limited to MAX_PATH characters. Name comparison is case
	 *            sensitive.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         newly created mutex object.
	 * 
	 *         If the function fails, the return value is NULL. To get extended
	 *         error information, call GetLastError.
	 */
	HANDLE CreateMutex(/* _In_opt_ */SECURITY_ATTRIBUTES lpMutexAttributes, /* _In_ */
			BOOL bInitialOwner, /* _In_opt_ */
			String lpName);

	/**
	 * Takes a snapshot of the specified processes, as well as the heaps,
	 * modules, and threads used by these processes.
	 * 
	 * @param dwFlags
	 *            [in] The portions of the system to be included in the
	 *            snapshot. This parameter can be one or more of the following
	 *            values.
	 * 
	 * @param th32ProcessID
	 *            [in] The process identifier of the process to be included in
	 *            the snapshot. This parameter can be zero to indicate the
	 *            current process. This parameter is used when the
	 *            TH32CS_SNAPHEAPLIST, TH32CS_SNAPMODULE, TH32CS_SNAPMODULE32,
	 *            or TH32CS_SNAPALL value is specified. Otherwise, it is ignored
	 *            and all processes are included in the snapshot.
	 * 
	 *            If the specified process is the Idle process or one of the
	 *            CSRSS processes, this function fails and the last error code
	 *            is ERROR_ACCESS_DENIED because their access restrictions
	 *            prevent user-level code from opening them.
	 * 
	 *            If the specified process is a 64-bit process and the caller is
	 *            a 32-bit process, this function fails and the last error code
	 *            is ERROR_PARTIAL_COPY (299).
	 * 
	 * @return If the function succeeds, it returns an open handle to the
	 *         specified snapshot.
	 */
	HANDLE CreateToolhelp32Snapshot(/* _In_ */DWORD dwFlags, /* _In_ */
			DWORD th32ProcessID);

	/**
	 * Releases all resources used by an unowned critical section object.
	 * 
	 * @param lpCriticalSection
	 *            [in, out] A pointer to the critical section object. The object
	 *            must have been previously initialized with the
	 *            InitializeCriticalSection function.
	 */
	void DeleteCriticalSection(
	/* _Inout_ */PRTL_CRITICAL_SECTION lpCriticalSection);

	/**
	 * Sends a control code directly to a specified device driver, causing the
	 * corresponding device to perform the corresponding operation.
	 * 
	 * @param hDevice
	 *            [in] A handle to the device on which the operation is to be
	 *            performed. The device is typically a volume, directory, file,
	 *            or stream. To retrieve a device handle, use the CreateFile
	 *            function. For more information, see Remarks.
	 * 
	 * @param dwIoControlCode
	 *            [in] The control code for the operation. This value identifies
	 *            the specific operation to be performed and the type of device
	 *            on which to perform it. For a list of the control codes, see
	 *            Remarks. The documentation for each control code provides
	 *            usage details for the lpInBuffer, nInBufferSize, lpOutBuffer,
	 *            and nOutBufferSize parameters.
	 * 
	 * @param lpInBuffer
	 *            [in, optional] A pointer to the input buffer that contains the
	 *            data required to perform the operation. The format of this
	 *            data depends on the value of the dwIoControlCode parameter.
	 *            This parameter can be NULL if dwIoControlCode specifies an
	 *            operation that does not require input data.
	 * 
	 * @param nInBufferSize
	 *            [in] The size of the input buffer, in bytes.
	 * 
	 * @param lpOutBuffer
	 *            [out, optional] A pointer to the output buffer that is to
	 *            receive the data returned by the operation. The format of this
	 *            data depends on the value of the dwIoControlCode parameter.
	 *            This parameter can be NULL if dwIoControlCode specifies an
	 *            operation that does not return data.
	 * 
	 * @param nOutBufferSize
	 *            [in] The size of the output buffer, in bytes.
	 * 
	 * @param lpBytesReturned
	 *            [out, optional] A pointer to a variable that receives the size
	 *            of the data stored in the output buffer, in bytes.
	 * 
	 * @param lpOverlapped
	 *            [in, out, optional] A pointer to an OVERLAPPED structure.
	 * 
	 * @return If the operation completes successfully, the return value is
	 *         nonzero.
	 * 
	 */
	BOOL DeviceIoControl(/* _In_ */HANDLE hDevice, /* _In_ */
			DWORD dwIoControlCode, /* _In_opt_ */ByteBuffer lpInBuffer, /* _In_ */
			int nInBufferSize, /* _Out_opt_ */ByteBuffer lpOutBuffer, /* _In_ */
			int nOutBufferSize, /* _Out_opt_ */
			DWORDByReference lpBytesReturned, /* _Inout_opt_ */
			OVERLAPPED lpOverlapped);

	/**
	 * Disables the DLL_THREAD_ATTACH and DLL_THREAD_DETACH notifications for
	 * the specified dynamic-link library (DLL). This can reduce the size of the
	 * working set for some applications.
	 * 
	 * @param hModule
	 *            [in] A handle to the DLL module for which the
	 *            DLL_THREAD_ATTACH and DLL_THREAD_DETACH notifications are to
	 *            be disabled. The LoadLibrary, LoadLibraryEx, or
	 *            GetModuleHandle function returns this handle. Note that you
	 *            cannot call GetModuleHandle with NULL because this returns the
	 *            base address of the executable image, not the DLL image.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL DisableThreadLibraryCalls(/* _In_ */HMODULE hModule);

	/**
	 * Waits for ownership of the specified critical section object. The
	 * function returns when the calling thread is granted ownership.
	 * 
	 * @param lpCriticalSection
	 *            A pointer to the critical section object.
	 * 
	 * @return 0
	 */
	void EnterCriticalSection(/* _Inout_ */RTL_CRITICAL_SECTION lpCriticalSection);

	/*
	 * BOOL EnumDateFormats( _In_ DATEFMT_ENUMPROC lpDateFmtEnumProc, _In_ LCID
	 * Locale, _In_ DWORD dwFlags );
	 */

	/**
	 * Converts a file time to system time format. System time is based on
	 * Coordinated Universal Time (UTC).
	 * 
	 * @param lpFileTime
	 *            A pointer to a FILETIME structure containing the file time to
	 *            be converted to system (UTC) date and time format.
	 * 
	 * @param lpSystemTime
	 *            A pointer to a SYSTEMTIME structure to receive the converted
	 *            file time.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL FileTimeToSystemTime(/* _In_ */FILETIME lpFileTime, /* _Out_ */
			SYSTEMTIME lpSystemTime);

	/**
	 * Determines the location of a resource with the specified type and name in
	 * the specified module.
	 * 
	 * @param hModule
	 *            A handle to the module whose portable executable file or an
	 *            accompanying MUI file contains the resource. If this parameter
	 *            is NULL, the function searches the module used to create the
	 *            current process.
	 * 
	 * @param lpName
	 *            The name of the resource. Alternately, rather than a pointer,
	 *            this parameter can be MAKEINTRESOURCE(ID), where ID is the
	 *            integer identifier of the resource. For more information, see
	 *            the Remarks section below.
	 * 
	 * @param lpType
	 *            The resource type. Alternately, rather than a pointer, this
	 *            parameter can be MAKEINTRESOURCE(ID), where ID is the integer
	 *            identifier of the given resource type. For standard resource
	 *            types, see Resource Types. For more information, see the
	 *            Remarks section below.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         specified resource's information block. To obtain a handle to the
	 *         resource, pass this handle to the LoadResource function.
	 */
	HRSRC FindResource(/* _In_opt_ */HMODULE hModule, /* _In_ */ String lpName, /* _In_ */String lpType);
	HRSRC FindResource(/* _In_opt_ */HMODULE hModule, /* _In_ */ Pointer lpName, /* _In_ */String lpType);

	/**
	 * Flushes the instruction cache for the specified process.
	 * 
	 * @param hProcess
	 *            A handle to a process whose instruction cache is to be
	 *            flushed.
	 * 
	 * @param lpBaseAddress
	 *            A pointer to the base of the region to be flushed. This
	 *            parameter can be NULL.
	 * 
	 * @param dwSize
	 *            The size of the region to be flushed if the lpBaseAddress
	 *            parameter is not NULL, in bytes.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL FlushInstructionCache(/* _In_ */HANDLE hProcess, /* _In_ */
			LPVOID lpBaseAddress, /* _In_ */SIZE_T dwSize);

	/**
	 * Frees the loaded dynamic-link library (DLL) module and, if necessary,
	 * decrements its reference count. When the reference count reaches zero,
	 * the module is unloaded from the address space of the calling process and
	 * the handle is no longer valid.
	 * 
	 * @param hModule
	 *            A handle to the loaded library module. The LoadLibrary,
	 *            LoadLibraryEx, GetModuleHandle, or GetModuleHandleEx function
	 *            returns this handle.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL FreeLibrary(/* _In_ */HMODULE hModule);

	/**
	 * Retrieves the current Windows ANSI code page identifier for the operating
	 * system.
	 * 
	 * @return Returns the current Windows ANSI code page (ACP) identifier for
	 *         the operating system. See Code Page Identifiers for a list of
	 *         identifiers for Windows ANSI code pages and other code pages.
	 */
	UINT GetACP();

	/**
	 * Retrieves information about any valid installed or available code page.
	 * 
	 * @param CodePage
	 *            Identifier for the code page for which to retrieve
	 *            information. For details, see the CodePage parameter of
	 *            GetCPInfoEx.
	 * 
	 * @param lpCPInfo
	 *            Pointer to a CPINFO structure that receives information about
	 *            the code page. See the Remarks section.
	 * 
	 * @return Returns 1 if successful, or 0 otherwise. To get extended error
	 *         information, the application can call GetLastError, which can
	 *         return one of the following error codes: ERROR_INVALID_PARAMETER.
	 *         Any of the parameter values was invalid.
	 */
	BOOL GetCPInfo(/* _In_ */UINT CodePage, /* _Out_ */CPINFO lpCPInfo);

	/**
	 * Formats a date as a date string for a locale specified by the locale
	 * identifier. The function formats either a specified date or the local
	 * system date.
	 * 
	 * @param Locale
	 *            Locale identifier that specifies the locale this function
	 *            formats the date string for. You can use the MAKELCID macro to
	 *            create a locale identifier or use one of the following
	 *            predefined values.
	 * 
	 * @param dwFlags
	 *            Flags specifying date format options. For detailed
	 *            definitions, see the dwFlags parameter of GetDateFormatEx.
	 * 
	 * @param lpDate
	 *            Pointer to a SYSTEMTIME structure that contains the date
	 *            information to format. The application sets this parameter to
	 *            NULL if the function is to use the current local system date.
	 * 
	 * @param lpFormat
	 *            Pointer to a format picture string that is used to form the
	 *            date. Possible values for the format picture string are
	 *            defined in Day, Month, Year, and Era Format Pictures.
	 * 
	 * @param lpDateStr
	 *            Pointer to a buffer in which this function retrieves the
	 *            formatted date string.
	 * 
	 * @param cchDate
	 *            Size, in characters, of the lpDateStr buffer. The application
	 *            can set this parameter to 0 to return the buffer size required
	 *            to hold the formatted date string. In this case, the buffer
	 *            indicated by lpDateStr is not used.
	 * 
	 * @return Returns the number of characters written to the lpDateStr buffer
	 *         if successful. If the cchDate parameter is set to 0, the function
	 *         returns the number of characters required to hold the formatted
	 *         date string, including the terminating null character.
	 */
	int GetDateFormatW(/* _In_ */LCID Locale, /* _In_ */DWORD dwFlags, /* _In_opt_ */
			SYSTEMTIME lpDate, /* _In_opt_ */
			WString lpFormat, /* _Out_writes_opt_(cchDate) */char[] lpDateStr, /* _In_ */
			int cchDate);

	/**
	 * Retrieves information about the specified disk, including the amount of
	 * free space on the disk.
	 * 
	 * @param lpRootPathName
	 *            The root directory of the disk for which information is to be
	 *            returned. If this parameter is NULL, the function uses the
	 *            root of the current disk. If this parameter is a UNC name, it
	 *            must include a trailing backslash (for example,
	 *            "\\MyServer\MyShare\"). Furthermore, a drive specification
	 *            must have a trailing backslash (for example, "C:\"). The
	 *            calling application must have FILE_LIST_DIRECTORY access
	 *            rights for this directory.
	 * 
	 * @param lpSectorsPerCluster
	 *            A pointer to a variable that receives the number of sectors
	 *            per cluster.
	 * 
	 * @param lpBytesPerSector
	 *            A pointer to a variable that receives the number of bytes per
	 *            sector.
	 * 
	 * @param lpNumberOfFreeClusters
	 *            A pointer to a variable that receives the total number of free
	 *            clusters on the disk that are available to the user who is
	 *            associated with the calling thread. If per-user disk quotas
	 *            are in use, this value may be less than the total number of
	 *            free clusters on the disk.
	 * 
	 * @param lpTotalNumberOfClusters
	 *            A pointer to a variable that receives the total number of
	 *            clusters on the disk that are available to the user who is
	 *            associated with the calling thread. If per-user disk quotas
	 *            are in use, this value may be less than the total number of
	 *            clusters on the disk.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL GetDiskFreeSpace(/* _In_ */WString lpRootPathName, /* _Out_ */
			DWORDByReference lpSectorsPerCluster, /* _Out_ */
			DWORDByReference lpBytesPerSector, /* _Out_ */
			DWORDByReference lpNumberOfFreeClusters, /* _Out_ */
			DWORDByReference lpTotalNumberOfClusters);

	/**
	 * Retrieves the size of the specified file, in bytes.
	 * 
	 * @param hFile
	 *            A handle to the file.
	 * 
	 * @param lpFileSizeHigh
	 *            A pointer to the variable where the high-order doubleword of
	 *            the file size is returned. This parameter can be NULL if the
	 *            application does not require the high-order doubleword.
	 * 
	 * @return If the function succeeds, the return value is the low-order
	 *         doubleword of the file size, and, if lpFileSizeHigh is non-NULL,
	 *         the function puts the high-order doubleword of the file size into
	 *         the variable pointed to by that parameter.
	 */
	DWORD GetFileSize(/* _In_ */HANDLE hFile, /* _Out_opt_ */
			DWORDByReference lpFileSizeHigh);

	/**
	 * Retrieves the current system date and time. The information is in
	 * Coordinated Universal Time (UTC) format.
	 * 
	 * @param lpSystemTimeAsFileTime
	 *            A pointer to a FILETIME structure to receive the current
	 *            system date and time in UTC format.
	 */
	void GetSystemTimeAsFileTime(/* _Out_ */FILETIME lpSystemTimeAsFileTime);

	/**
	 * Retrieves information about a locale specified by identifier.
	 * 
	 * @param Locale
	 *            Locale identifier for which to retrieve information. You can
	 *            use the MAKELCID macro to create a locale identifier or use
	 *            one of the following predefined values.
	 * 
	 * @param LCType
	 *            The locale information to retrieve. For detailed definitions,
	 *            see the LCType parameter of GetLocaleInfoEx.
	 * 
	 * @param lpLCData
	 *            Pointer to a buffer in which this function retrieves the
	 *            requested locale information. This pointer is not used if
	 *            cchData is set to 0. For more information, see the Remarks
	 *            section.
	 * 
	 * @param cchData
	 *            Size, in TCHAR values, of the data buffer indicated by
	 *            lpLCData. Alternatively, the application can set this
	 *            parameter to 0. In this case, the function does not use the
	 *            lpLCData parameter and returns the required buffer size,
	 *            including the terminating null character.
	 * 
	 * @return Returns the number of characters retrieved in the locale data
	 *         buffer if successful and cchData is a nonzero value. If the
	 *         function succeeds, cchData is nonzero, and LOCALE_RETURN_NUMBER
	 *         is specified, the return value is the size of the integer
	 *         retrieved in the data buffer; that is, 2 for the Unicode version
	 *         of the function or 4 for the ANSI version. If the function
	 *         succeeds and the value of cchData is 0, the return value is the
	 *         required size, in characters including a null character, for the
	 *         locale data buffer.
	 */
	int GetLocaleInfo(/* _In_ */LCID Locale, /* _In_ */DWORD LCType, /* _Out_opt_ */
			char[] lpLCData, /* _In_ */
			int cchData);

	/**
	 * Retrieves a bitmask representing the currently available disk drives.
	 * 
	 * @return If the function succeeds, the return value is a bitmask
	 *         representing the currently available disk drives. Bit position 0
	 *         (the least-significant bit) is drive A, bit position 1 is drive
	 *         B, bit position 2 is drive C, and so on.
	 */
	DWORD GetLogicalDrives();

	/**
	 * Retrieves a handle to the default heap of the calling process. This
	 * handle can then be used in subsequent calls to the heap functions.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         calling process's heap.
	 */
	HANDLE GetProcessHeap();

	/**
	 * Retrieves character type information for the characters in the specified
	 * Unicode source string. For each character in the string, the function
	 * sets one or more bits in the corresponding 16-bit element of the output
	 * array. Each bit identifies a given character type, for example, letter,
	 * digit, or neither.
	 * 
	 * @param dwInfoType
	 *            Flags specifying the character type information to retrieve.
	 *            For possible flag values, see the dwInfoType parameter of
	 *            GetStringTypeW. For detailed information about the character
	 *            type bits, see Remarks for GetStringTypeW.
	 * 
	 * @param lpSrcStr
	 *            Pointer to the ANSI string for which to retrieve the character
	 *            types. The string can be a double-byte character set (DBCS)
	 *            string if the supplied locale is appropriate for DBCS. The
	 *            string is assumed to be null-terminated if cchSrc is set to
	 *            any negative value.
	 * 
	 * @param cchSrc
	 *            Size, in characters, of the string indicated by lpSrcStr. If
	 *            the size includes a terminating null character, the function
	 *            retrieves character type information for that character. If
	 *            the application sets the size to any negative integer, the
	 *            source string is assumed to be null-terminated and the
	 *            function calculates the size automatically with an additional
	 *            character for the null termination.
	 * 
	 * @param lpCharType
	 *            Pointer to an array of 16-bit values. The length of this array
	 *            must be large enough to receive one 16-bit value for each
	 *            character in the source string. If cchSrc is not a negative
	 *            number, lpCharType should be an array of words with cchSrc
	 *            elements. If cchSrc is set to a negative number, lpCharType is
	 *            an array of words with lpSrcStr + 1 elements. When the
	 *            function returns, this array contains one word corresponding
	 *            to each character in the source string.
	 * 
	 * @return Returns a nonzero value if successful, or 0 otherwise. To get
	 *         extended error information, the application can call
	 *         GetLastError, which can return one of the following error codes:
	 *         ERROR_INVALID_FLAGS. The values supplied for flags were not
	 *         valid. ERROR_INVALID_PARAMETER. Any of the parameter values was
	 *         invalid.
	 */
	BOOL GetStringTypeW(
	/* _In_ */DWORD dwInfoType,
	/* _In_NLS_string_(cchSrc) */WString lpSrcStr,
	/* _In_ */int cchSrc,
	/* _Out_ */short[] lpCharType);

	/**
	 * Deprecated. Retrieves character type information for the characters in
	 * the specified source string. For each character in the string, the
	 * function sets one or more bits in the corresponding 16-bit element of the
	 * output array. Each bit identifies a given character type, for example,
	 * letter, digit, or neither.
	 * 
	 * @param Locale
	 *            Locale identifier that specifies the locale. You can use the
	 *            MAKELCID macro to create a locale identifier or use one of the
	 *            following predefined values.
	 * 
	 * @param dwInfoType
	 *            Flags specifying the character type information to retrieve.
	 *            For possible flag values, see the dwInfoType parameter of
	 *            GetStringTypeW. For detailed information about the character
	 *            type bits, see Remarks for GetStringTypeW.
	 * 
	 * @param lpSrcStr
	 *            Pointer to the ANSI string for which to retrieve the character
	 *            types. The string can be a double-byte character set (DBCS)
	 *            string if the supplied locale is appropriate for DBCS. The
	 *            string is assumed to be null-terminated if cchSrc is set to
	 *            any negative value.
	 * 
	 * @param cchSrc
	 *            Size, in characters, of the string indicated by lpSrcStr. If
	 *            the size includes a terminating null character, the function
	 *            retrieves character type information for that character. If
	 *            the application sets the size to any negative integer, the
	 *            source string is assumed to be null-terminated and the
	 *            function calculates the size automatically with an additional
	 *            character for the null termination.
	 * 
	 * @param lpCharType
	 *            Pointer to an array of 16-bit values. The length of this array
	 *            must be large enough to receive one 16-bit value for each
	 *            character in the source string. If cchSrc is not a negative
	 *            number, lpCharType should be an array of words with cchSrc
	 *            elements. If cchSrc is set to a negative number, lpCharType is
	 *            an array of words with lpSrcStr + 1 elements. When the
	 *            function returns, this array contains one word corresponding
	 *            to each character in the source string.
	 * 
	 * @return Returns a nonzero value if successful, or 0 otherwise. To get
	 *         extended error information, the application can call
	 *         GetLastError, which can return one of the following error codes:
	 *         ERROR_INVALID_FLAGS. The values supplied for flags were not
	 *         valid. ERROR_INVALID_PARAMETER. Any of the parameter values was
	 *         invalid.
	 */
	BOOL GetStringTypeA(/* _In_ */LCID Locale, /* _In_ */DWORD dwInfoType, /* _In_reads_ */
			String lpSrcStr, /* _In_ */
			int cchSrc, /* _Out_ */short[] lpCharType);

	/**
	 * Creates a name for a temporary file. If a unique file name is generated,
	 * an empty file is created and the handle to it is released; otherwise,
	 * only a file name is generated.
	 * 
	 * @param lpPathName
	 *            The directory path for the file name. Applications typically
	 *            specify a period (.) for the current directory or the result
	 *            of the GetTempPath function. The string cannot be longer than
	 *            MAX_PATH�14 characters or GetTempFileName will fail. If this
	 *            parameter is NULL, the function fails.
	 * 
	 * @param lpPrefixString
	 *            The null-terminated prefix string. The function uses up to the
	 *            first three characters of this string as the prefix of the
	 *            file name. This string must consist of characters in the
	 *            OEM-defined character set.
	 * 
	 * @param uUnique
	 *            An unsigned integer to be used in creating the temporary file
	 *            name. For more information, see Remarks. If uUnique is zero,
	 *            the function attempts to form a unique file name using the
	 *            current system time. If the file already exists, the number is
	 *            increased by one and the functions tests if this file already
	 *            exists. This continues until a unique filename is found; the
	 *            function creates a file by that name and closes it. Note that
	 *            the function does not attempt to verify the uniqueness of the
	 *            file name when uUnique is nonzero.
	 * 
	 * @param lpTempFileName
	 *            A pointer to the buffer that receives the temporary file name.
	 *            This buffer should be MAX_PATH characters to accommodate the
	 *            path plus the terminating null character.
	 * 
	 * @return If the function succeeds, the return value specifies the unique
	 *         numeric value used in the temporary file name. If the uUnique
	 *         parameter is nonzero, the return value specifies that same
	 *         number.
	 * 
	 */
	UINT GetTempFileName(/* _In_ */String lpPathName, /* _In_ */
			String lpPrefixString, /* _In_ */UINT uUnique, /* _Out_ */
			char[] lpTempFileName);

	/**
	 * Allocates the specified number of bytes from the heap.
	 * 
	 * @param uFlags
	 *            The memory allocation attributes. If zero is specified, the
	 *            default is GMEM_FIXED. This parameter can be one or more of
	 *            the following values, except for the incompatible combinations
	 *            that are specifically noted.
	 * 
	 * @param dwBytes
	 *            The number of bytes to allocate. If this parameter is zero and
	 *            the uFlags parameter specifies GMEM_MOVEABLE, the function
	 *            returns a handle to a memory object that is marked as
	 *            discarded.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         newly allocated memory object.
	 */
	HANDLE GlobalAlloc(/* _In_ */UINT uFlags, /* _In_ */SIZE_T dwBytes);

	/**
	 * Frees the specified global memory object and invalidates its handle.
	 * 
	 * @param hMem
	 *            A handle to the global memory object. This handle is returned
	 *            by either the GlobalAlloc or GlobalReAlloc function. It is not
	 *            safe to free memory allocated with LocalAlloc.
	 * 
	 * @return If the function succeeds, the return value is NULL.
	 */
	HANDLE GlobalFree(/* _In_ */HANDLE hMem);

	/**
	 * Locks a global memory object and returns a pointer to the first byte of
	 * the object's memory block.
	 * 
	 * @param hMem
	 *            A handle to the global memory object. This handle is returned
	 *            by either the GlobalAlloc or GlobalReAlloc function.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         first byte of the memory block.
	 */
	LPVOID GlobalLock(/* _In_ */HANDLE hMem);

	/**
	 * [GlobalMemoryStatus can return incorrect information. Use the
	 * GlobalMemoryStatusEx function instead. ] Retrieves information about the
	 * system's current usage of both physical and virtual memory.
	 * 
	 * @param lpBuffer
	 *            A pointer to a MEMORYSTATUS structure. The GlobalMemoryStatus
	 *            function stores information about current memory availability
	 *            into this structure.
	 */
	void GlobalMemoryStatus(/* _Out_ */MEMORYSTATUS lpBuffer);

	/**
	 * Initializes a critical section object.
	 * 
	 * @param lpCriticalSection
	 *            A pointer to the critical section object.
	 * 
	 * @return 0
	 */
	void InitializeCriticalSection(/* _Out_ */RTL_CRITICAL_SECTION lpCriticalSection);

	LONG InterlockedDecrement(/* _Inout_ */LONGByReference /* volatile * */Addend);

	void RegisterServiceProcess(long dwProcessId, long dwType);

	/**
	 * Verifies that the calling process has write access to the specified range
	 * of memory.
	 * 
	 * @param lp
	 *            A pointer to the first byte of the memory block.
	 * 
	 * @param ucb
	 *            The size of the memory block, in bytes. If this parameter is
	 *            zero, the return value is zero.
	 * @return If the calling process has write access to all bytes in the
	 *         specified memory range, the return value is zero. If the calling
	 *         process does not have write access to all bytes in the specified
	 *         memory range, the return value is nonzero. If the application is
	 *         run under a debugger and the process does not have write access
	 *         to all bytes in the specified memory range, the function causes a
	 *         first chance STATUS_ACCESS_VIOLATION exception. The debugger can
	 *         be configured to break for this condition. After resuming process
	 *         execution in the debugger, the function continues as usual and
	 *         returns a nonzero value This behavior is by design and serves as
	 *         a debugging aid.
	 */
	BOOL IsBadWritePtr(/* _In_ */LPVOID lp, /* _In_ */UINT_PTR ucb);

	/**
	 * For a locale specified by identifier, maps one input character string to
	 * another using a specified transformation, or generates a sort key for the
	 * input string.
	 * 
	 * @param Locale
	 *            Locale identifier that specifies the locale. You can use the
	 *            MAKELCID macro to create a locale identifier or use one of the
	 *            following predefined values.
	 * 
	 * @param dwMapFlags
	 *            Flags specifying the type of transformation to use during
	 *            string mapping or the type of sort key to generate. For
	 *            detailed definitions, see the dwMapFlags parameter of
	 *            LCMapStringEx.
	 * 
	 * @param lpSrcStr
	 *            Pointer to a source string that the function maps or uses for
	 *            sort key generation. This string cannot have a size of 0.
	 * 
	 * @param cchSrc
	 *            ize, in characters, of the source string indicated by
	 *            lpSrcStr. The size of the source string can include the
	 *            terminating null character, but does not have to. If the
	 *            terminating null character is included, the mapping behavior
	 *            of the function is not greatly affected because the
	 *            terminating null character is considered to be unsortable and
	 *            always maps to itself. The application can set the parameter
	 *            to any negative value to specify that the source string is
	 *            null-terminated. In this case, if LCMapString is being used in
	 *            its string-mapping mode, the function calculates the string
	 *            length itself, and null-terminates the mapped string indicated
	 *            by lpDestStr. The application cannot set this parameter to 0.
	 * 
	 * @param lpDestStr
	 *            Pointer to a buffer in which this function retrieves the
	 *            mapped string or a sort key. When the application uses this
	 *            function to generate a sort key, the destination string can
	 *            contain an odd number of bytes. The LCMAP_BYTEREV flag only
	 *            reverses an even number of bytes. The last byte
	 *            (odd-positioned) in the sort key is not reversed.
	 * 
	 * @param cchDest
	 *            Size, in characters, of the destination string indicated by
	 *            lpDestStr. If the application is using the function for string
	 *            mapping, it supplies a character count for this parameter. If
	 *            space for a terminating null character is included in cchSrc,
	 *            cchDest must also include space for a terminating null
	 *            character.
	 * 
	 * @return Returns the number of characters or bytes in the translated
	 *         string or sort key, including a terminating null character, if
	 *         successful. If the function succeeds and the value of cchDest is
	 *         0, the return value is the size of the buffer required to hold
	 *         the translated string or sort key, including a terminating null
	 *         character. This function returns 0 if it does not succeed. To get
	 *         extended error information, the application can call
	 *         GetLastError, which can return one of the following error codes:
	 */
	int LCMapString(/* _In_ */LCID Locale, /* _In_ */DWORD dwMapFlags, /* _In_ */
			WString lpSrcStr, /* _In_ */int cchSrc, /* _Out_opt_ */
			char[] lpDestStr, /* _In_ */int cchDest);

	/**
	 * Retrieves a handle that can be used to obtain a pointer to the first byte
	 * of the specified resource in memory.
	 * 
	 * @param hModule
	 *            A handle to the module whose executable file contains the
	 *            resource. If hModule is NULL, the system loads the resource
	 *            from the module that was used to create the current process.
	 * 
	 * @param hResInfo
	 *            A handle to the resource to be loaded. This handle is returned
	 *            by the FindResource or FindResourceEx function.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         data associated with the resource. If the function fails, the
	 *         return value is NULL. To get extended error information, call
	 *         GetLastError.
	 */
	HANDLE LoadResource(/* _In_opt_ */HMODULE hModule, /* _In_ */HRSRC hResInfo);

	/**
	 * Allocates the specified number of bytes from the heap.
	 * 
	 * @param uFlags
	 *            The memory allocation attributes. The default is the
	 *            LMEM_FIXED value. This parameter can be one or more of the
	 *            following values, except for the incompatible combinations
	 *            that are specifically noted.
	 * 
	 * @param uBytes
	 *            The number of bytes to allocate. If this parameter is zero and
	 *            the uFlags parameter specifies LMEM_MOVEABLE, the function
	 *            returns a handle to a memory object that is marked as
	 *            discarded.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         newly allocated memory object.
	 */
	HANDLE LocalAlloc(/* _In_ */UINT uFlags, /* _In_ */SIZE_T uBytes);

	/**
	 * Frees the specified local memory object and invalidates its handle.
	 * 
	 * @param hMem
	 *            A handle to the local memory object. This handle is returned
	 *            by either the LocalAlloc or LocalReAlloc function. It is not
	 *            safe to free memory allocated with GlobalAlloc.
	 * 
	 * @return If the function succeeds, the return value is NULL. If the
	 *         function fails, the return value is equal to a handle to the
	 *         local memory object. To get extended error information, call
	 *         GetLastError.
	 */
	HANDLE LocalFree(/* _In_ */HANDLE hMem);

	/**
	 * Locks a local memory object and returns a pointer to the first byte of
	 * the object's memory block.
	 * 
	 * @param hMem
	 *            A handle to the local memory object. This handle is returned
	 *            by either the LocalAlloc or LocalReAlloc function.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         first byte of the memory block. If the function fails, the return
	 *         value is NULL. To get extended error information, call
	 *         GetLastError.
	 */
	LPVOID LocalLock(/* _In_ */HANDLE hMem);

	/**
	 * Changes the size or the attributes of a specified local memory object.
	 * The size can increase or decrease.
	 * 
	 * @param hMem
	 *            A handle to the local memory object to be reallocated. This
	 *            handle is returned by either the LocalAlloc or LocalReAlloc
	 *            function.
	 * 
	 * @param uBytes
	 *            The new size of the memory block, in bytes. If uFlags
	 *            specifies LMEM_MODIFY, this parameter is ignored.
	 * 
	 * @param uFlags
	 *            The reallocation options. If LMEM_MODIFY is specified, the
	 *            function modifies the attributes of the memory object only
	 *            (the uBytes parameter is ignored.) Otherwise, the function
	 *            reallocates the memory object.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         reallocated memory object. If the function fails, the return
	 *         value is NULL. To get extended error information, call
	 *         GetLastError.
	 */
	HANDLE LocalReAlloc(/* _In_ */HANDLE hMem, /* _In_ */SIZE_T uBytes, /* _In_ */
			UINT uFlags);

	/**
	 * Retrieves a pointer to the specified resource in memory.
	 * 
	 * @param hResData
	 *            A handle to the resource to be accessed. The LoadResource
	 *            function returns this handle. Note that this parameter is
	 *            listed as an HGLOBAL variable only for backward compatibility.
	 *            Do not pass any value as a parameter other than a successful
	 *            return value from the LoadResource function.
	 * 
	 * @return If the loaded resource is available, the return value is a
	 *         pointer to the first byte of the resource; otherwise, it is NULL.
	 */
	LPVOID LockResource(/* _In_ */HANDLE hResData);

	/**
	 * Compares two character strings. The comparison is not case-sensitive.
	 * 
	 * @param lpString1
	 *            The first null-terminated string to be compared.
	 * 
	 * @param lpString2
	 *            The second null-terminated string to be compared.
	 * 
	 * @return If the string pointed to by lpString1 is less than the string
	 *         pointed to by lpString2, the return value is negative. If the
	 *         string pointed to by lpString1 is greater than the string pointed
	 *         to by lpString2, the return value is positive. If the strings are
	 *         equal, the return value is zero.
	 */
	int lstrcmpi(/* _In_ */WString lpString1, /* _In_ */WString lpString2);

	/**
	 * Copies a specified number of characters from a source string into a
	 * buffer.
	 * 
	 * @param lpString1
	 *            The destination buffer, which receives the copied characters.
	 *            The buffer must be large enough to contain the number of TCHAR
	 *            values specified by iMaxLength, including room for a
	 *            terminating null character.
	 * 
	 * @param lpString2
	 *            The source string from which the function is to copy
	 *            characters.
	 * 
	 * @param iMaxLength
	 *            The number of TCHAR values to be copied from the string
	 *            pointed to by lpString2 into the buffer pointed to by
	 *            lpString1, including a terminating null character.
	 * 
	 * @return If the function succeeds, the return value is a pointer to the
	 *         buffer. The function can succeed even if the source string is
	 *         greater than iMaxLength characters. If the function fails, the
	 *         return value is NULL and lpString1 may not be null-terminated.
	 */
	WString lstrcpyn(/* _Out_ */WString lpString1, /* _In_ */WString lpString2, /* _In_ */
			int iMaxLength);

	/**
	 * Maps a character string to a UTF-16 (wide character) string. The
	 * character string is not necessarily from a multibyte character set.
	 * 
	 * @param CodePage
	 *            Code page to use in performing the conversion. This parameter
	 *            can be set to the value of any code page that is installed or
	 *            available in the operating system. For a list of code pages,
	 *            see Code Page Identifiers.
	 * 
	 * @param dwFlags
	 *            Flags indicating the conversion type. The application can
	 *            specify a combination of the following values, with
	 *            MB_PRECOMPOSED being the default. MB_PRECOMPOSED and
	 *            MB_COMPOSITE are mutually exclusive. MB_USEGLYPHCHARS and
	 *            MB_ERR_INVALID_CHARS can be set regardless of the state of the
	 *            other flags.
	 * 
	 * @param lpMultiByteStr
	 *            Pointer to the character string to convert.
	 * 
	 * @param cbMultiByte
	 *            Size, in bytes, of the string indicated by the lpMultiByteStr
	 *            parameter. Alternatively, this parameter can be set to -1 if
	 *            the string is null-terminated. Note that, if cbMultiByte is 0,
	 *            the function fails.
	 * 
	 * @param lpWideCharStr
	 *            Pointer to a buffer that receives the converted string.
	 * 
	 * @param cchWideChar
	 *            Size, in characters, of the buffer indicated by lpWideCharStr.
	 *            If this value is 0, the function returns the required buffer
	 *            size, in characters, including any terminating null character,
	 *            and makes no use of the lpWideCharStr buffer.
	 * 
	 * @return Returns the number of characters written to the buffer indicated
	 *         by lpWideCharStr if successful. If the function succeeds and
	 *         cchWideChar is 0, the return value is the required size, in
	 *         characters, for the buffer indicated by lpWideCharStr. If the
	 *         input byte/char sequences are invalid, returns U+FFFD for UTF
	 *         encodings. The function returns 0 if it does not succeed.
	 * 
	 */
	int MultiByteToWideChar(/* _In_ */UINT CodePage, /* _In_ */DWORD dwFlags, /* _In_ */
			String lpMultiByteStr, /* _In_ */int cbMultiByte, /* _Out_opt_ */
			char[] lpWideCharStr, /* _In_ */int cchWideChar);

	/**
	 * Opens an existing named event object.
	 * 
	 * @param dwDesiredAccess
	 *            The access to the event object. The function fails if the
	 *            security descriptor of the specified object does not permit
	 *            the requested access for the calling process. For a list of
	 *            access rights, see Synchronization Object Security and Access
	 *            Rights.
	 * 
	 * @param bInheritHandle
	 *            If this value is TRUE, processes created by this process will
	 *            inherit the handle. Otherwise, the processes do not inherit
	 *            this handle.
	 * 
	 * @param lpName
	 *            The name of the event to be opened. Name comparisons are case
	 *            sensitive. This function can open objects in a private
	 *            namespace. For more information, see Object Namespaces.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         event object. If the function fails, the return value is NULL.
	 */
	HANDLE OpenEvent(/* _In_ */DWORD dwDesiredAccess, /* _In_ */
			BOOL bInheritHandle, /* _In_ */WString lpName);

	/**
	 * Creates, opens, reopens, or deletes a file.
	 * 
	 * @param lpFileName
	 *            The name of the file.
	 * 
	 * @param lpReOpenBuff
	 *            A pointer to the OFSTRUCT structure that receives information
	 *            about a file when it is first opened.
	 * 
	 * @param uStyle
	 *            The action to be taken.
	 * 
	 * @return If the function succeeds, the return value specifies a file
	 *         handle to use when performing file I/O. To close the file, call
	 *         the CloseHandle function using this handle.
	 */
	int OpenFile(/* _In_ */String lpFileName, /* _Out_ */OFSTRUCT lpReOpenBuff, /* _In_ */
			UINT uStyle);

	/**
	 * Opens an existing named mutex object.
	 * 
	 * @param dwDesiredAccess
	 *            The access to the mutex object. Only the SYNCHRONIZE access
	 *            right is required to use a mutex; to change the mutex's
	 *            security, specify MUTEX_ALL_ACCESS. The function fails if the
	 *            security descriptor of the specified object does not permit
	 *            the requested access for the calling process. For a list of
	 *            access rights, see Synchronization Object Security and Access
	 *            Rights.
	 * 
	 * @param bInheritHandle
	 *            If this value is TRUE, processes created by this process will
	 *            inherit the handle. Otherwise, the processes do not inherit
	 *            this handle.
	 * 
	 * @param lpName
	 *            The name of the mutex to be opened. Name comparisons are case
	 *            sensitive.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         mutex object. If the function fails, the return value is NULL. To
	 *         get extended error information, call GetLastError.
	 */
	HANDLE OpenMutex(/* _In_ */DWORD dwDesiredAccess, /* _In_ */
			BOOL bInheritHandle, /* _In_ */WString lpName);

	/**
	 * Opens an existing local process object.
	 * 
	 * @param dwDesiredAccess
	 *            The access to the process object. This access right is checked
	 *            against the security descriptor for the process. This
	 *            parameter can be one or more of the process access rights.
	 * 
	 * @param bInheritHandle
	 *            If this value is TRUE, processes created by this process will
	 *            inherit the handle. Otherwise, the processes do not inherit
	 *            this handle.
	 * 
	 * @param dwProcessId
	 *            The identifier of the local process to be opened.
	 * 
	 * @return If the function succeeds, the return value is an open handle to
	 *         the specified process. If the function fails, the return value is
	 *         NULL. To get extended error information, call GetLastError.
	 */
	HANDLE OpenProcess(/* _In_ */DWORD dwDesiredAccess, /* _In_ */
			BOOL bInheritHandle, /* _In_ */DWORD dwProcessId);

	/**
	 * Sends a string to the debugger for display.
	 * 
	 * @param lpOutputString
	 *            The null-terminated string to be displayed.
	 */
	void OutputDebugString(/* _In_opt_ */WString lpOutputString);

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
	 *            information such as the name of the executable file, the
	 *            process identifier, and the process identifier of the parent
	 *            process.
	 * 
	 * @return Returns TRUE if the first entry of the process list has been
	 *         copied to the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES
	 *         error value is returned by the GetLastError function if no
	 *         processes exist or the snapshot does not contain process
	 *         information.
	 */
	BOOL Process32First(/* _In_ */HANDLE hSnapshot, /* _Inout_ */
			PROCESSENTRY32 lppe);

	/**
	 * Retrieves information about the next process recorded in a system
	 * snapshot.
	 * 
	 * @param hSnapshot
	 *            A handle to the snapshot returned from a previous call to the
	 *            CreateToolhelp32Snapshot function.
	 * 
	 * @param lppe
	 *            A pointer to a PROCESSENTRY32 structure. It contains process
	 *            information such as the name of the executable file, the
	 *            process identifier, and the process identifier of the parent
	 *            process.
	 * 
	 * @return Returns TRUE if the first entry of the process list has been
	 *         copied to the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES
	 *         error value is returned by the GetLastError function if no
	 *         processes exist or the snapshot does not contain process
	 *         information.
	 */
	BOOL Process32Next(/* _In_ */HANDLE hSnapshot, /* _Out_ */
			PROCESSENTRY32 lppe);

	/**
	 * Reads data from an area of memory in a specified process. The entire area
	 * to be read must be accessible or the operation fails.
	 * 
	 * @param hProcess
	 *            A handle to the process with memory that is being read. The
	 *            handle must have PROCESS_VM_READ access to the process.
	 * 
	 * @param lpBaseAddress
	 *            A pointer to the base address in the specified process from
	 *            which to read. Before any data transfer occurs, the system
	 *            verifies that all data in the base address and memory of the
	 *            specified size is accessible for read access, and if it is not
	 *            accessible the function fails.
	 * 
	 * @param lpBuffer
	 *            A pointer to a buffer that receives the contents from the
	 *            address space of the specified process.
	 * 
	 * @param nSize
	 *            The number of bytes to be read from the specified process.
	 * 
	 * @param lpNumberOfBytesRead
	 *            A pointer to a variable that receives the number of bytes
	 *            transferred into the specified buffer. If lpNumberOfBytesRead
	 *            is NULL, the parameter is ignored.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is 0 (zero). To get extended
	 *         error information, call GetLastError.
	 */
	BOOL ReadProcessMemory(/* _In_ */HANDLE hProcess, /* _In_ */
			LPVOID lpBaseAddress, /* _Out_ */LPVOID lpBuffer, /* _In_ */
			SIZE_T nSize, /* _Out_ */ULONG_PTRByReference lpNumberOfBytesRead);

	/**
	 * Releases ownership of the specified mutex object.
	 * 
	 * @param hMutex
	 *            A handle to the mutex object. The CreateMutex or OpenMutex
	 *            function returns this handle.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL ReleaseMutex(/* _In_ */HANDLE hMutex);

	/**
	 * The RtlZeroMemory routine fills a block of memory with zeros, given a
	 * pointer to the block and the length, in bytes, to be filled.
	 * 
	 * @param Destination
	 *            A pointer to the memory block to be filled with zeros.
	 * 
	 * @param Length
	 *            The number of bytes to fill with zeros.
	 */
	void RtlZeroMemory(/* _Out_ */Pointer Destination, /* _In_ */SIZE_T Length);

	/**
	 * Searches for a specified file in a specified path.
	 * 
	 * @param lpPath
	 *            The path to be searched for the file. If this parameter is
	 *            NULL, the function searches for a matching file using a
	 *            registry-dependent system search path. For more information,
	 *            see the Remarks section.
	 * 
	 * @param lpFileName
	 *            The name of the file for which to search.
	 * 
	 * @param lpExtension
	 *            The extension to be added to the file name when searching for
	 *            the file. The first character of the file name extension must
	 *            be a period (.). The extension is added only if the specified
	 *            file name does not end with an extension. If a file name
	 *            extension is not required or if the file name contains an
	 *            extension, this parameter can be NULL.
	 * 
	 * @param nBufferLength
	 *            The size of the buffer that receives the valid path and file
	 *            name (including the terminating null character), in TCHARs.
	 * 
	 * @param lpBuffer
	 *            A pointer to the buffer to receive the path and file name of
	 *            the file found. The string is a null-terminated string.
	 * 
	 * @param lpFilePart
	 *            A pointer to the variable to receive the address (within
	 *            lpBuffer) of the last component of the valid path and file
	 *            name, which is the address of the character immediately
	 *            following the final backslash (\) in the path.
	 * 
	 * @return If the function succeeds, the value returned is the length, in
	 *         TCHARs, of the string that is copied to the buffer, not including
	 *         the terminating null character. If the return value is greater
	 *         than nBufferLength, the value returned is the size of the buffer
	 *         that is required to hold the path, including the terminating null
	 *         character. If the function fails, the return value is zero. To
	 *         get extended error information, call GetLastError.
	 */
	DWORD SearchPath(/* _In_opt_ */String lpPath, /* _In_ */String lpFileName, /* _In_opt_ */String lpExtension, /* _In_ */
			DWORD nBufferLength, /* _Out_ */char[] lpBuffer, /* _Out_opt_ */Pointer lpFilePart);

	/**
	 * Adds or removes an application-defined HandlerRoutine function from the
	 * list of handler functions for the calling process.
	 * 
	 * @param HandlerRoutine
	 *            A pointer to the application-defined HandlerRoutine function
	 *            to be added or removed. This parameter can be NULL.
	 * 
	 * @param Add
	 *            If this parameter is TRUE, the handler is added; if it is
	 *            FALSE, the handler is removed. If the HandlerRoutine parameter
	 *            is NULL, a TRUE value causes the calling process to ignore
	 *            CTRL+C input, and a FALSE value restores normal processing of
	 *            CTRL+C input. This attribute of ignoring or processing CTRL+C
	 *            is inherited by child processes.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SetConsoleCtrlHandler(/* _In_opt_PHANDLER_ROUTINE */Callback HandlerRoutine, /* _In_ */BOOL Add);

	/**
	 * Controls whether the system will handle the specified types of serious
	 * errors or whether the process will handle them.
	 * 
	 * @param uMode
	 *            The process error mode. This parameter can be one or more of
	 *            the following values.
	 * 
	 * @return The return value is the previous state of the error-mode bit
	 *         flags.
	 */
	UINT SetErrorMode(/* _In_ */UINT uMode);

	/**
	 * Sets the specified event object to the signaled state.
	 * 
	 * @param hEvent
	 *            A handle to the event object. The CreateEvent or OpenEvent
	 *            function returns this handle. The handle must have the
	 *            EVENT_MODIFY_STATE access right. For more information, see
	 *            Synchronization Object Security and Access Rights.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SetEvent(/* _In_ */HANDLE hEvent);

	/**
	 * Sets the date and time that the specified file or directory was created,
	 * last accessed, or last modified.
	 * 
	 * @param hFile
	 *            A handle to the file or directory. The handle must have been
	 *            created using the CreateFile function with the
	 *            FILE_WRITE_ATTRIBUTES access right. For more information, see
	 *            File Security and Access Rights.
	 * 
	 * @param lpCreationTime
	 *            A pointer to a FILETIME structure that contains the new
	 *            creation date and time for the file or directory. This
	 *            parameter can be NULL if the application does not need to
	 *            change this information.
	 * 
	 * @param lpLastAccessTime
	 *            A pointer to a FILETIME structure that contains the new last
	 *            access date and time for the file or directory. The last
	 *            access time includes the last time the file or directory was
	 *            written to, read from, or (in the case of executable files)
	 *            run. This parameter can be NULL if the application does not
	 *            need to change this information.
	 * 
	 * @param lpLastWriteTime
	 *            A pointer to a FILETIME structure that contains the new last
	 *            modified date and time for the file or directory. This
	 *            parameter can be NULL if the application does not need to
	 *            change this information.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SetFileTime(/* _In_ */HANDLE hFile, /* _In_opt_ */FILETIME lpCreationTime, /* _In_opt_ */
			FILETIME lpLastAccessTime, /* _In_opt_ */FILETIME lpLastWriteTime);

	/**
	 * Sets the priority value for the specified thread. This value, together
	 * with the priority class of the thread's process, determines the thread's
	 * base priority level.
	 * 
	 * @param hThread
	 *            A handle to the thread whose priority value is to be set.
	 * 
	 * @param nPriority
	 *            The priority value for the thread. This parameter can be one
	 *            of the following values.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SetThreadPriority(/* _In_ */HANDLE hThread, /* _In_ */int nPriority);

	/**
	 * Creates a timer with the specified time-out value.
	 * 
	 * @param hWnd
	 *            A handle to the window to be associated with the timer. This
	 *            window must be owned by the calling thread. If a NULL value
	 *            for hWnd is passed in along with an nIDEvent of an existing
	 *            timer, that timer will be replaced in the same way that an
	 *            existing non-NULL hWnd timer will be.
	 * 
	 * @param nIDEvent
	 *            A nonzero timer identifier. If the hWnd parameter is NULL, and
	 *            the nIDEvent does not match an existing timer then it is
	 *            ignored and a new timer ID is generated. If the hWnd parameter
	 *            is not NULL and the window specified by hWnd already has a
	 *            timer with the value nIDEvent, then the existing timer is
	 *            replaced by the new timer. When SetTimer replaces a timer, the
	 *            timer is reset. Therefore, a message will be sent after the
	 *            current time-out value elapses, but the previously set
	 *            time-out value is ignored. If the call is not intended to
	 *            replace an existing timer, nIDEvent should be 0 if the hWnd is
	 *            NULL.
	 * 
	 * @param uElapse
	 *            The time-out value, in milliseconds. If uElapse is less than
	 *            USER_TIMER_MINIMUM (0x0000000A), the timeout is set to
	 *            USER_TIMER_MINIMUM. If uElapse is greater than
	 *            USER_TIMER_MAXIMUM (0x7FFFFFFF), the timeout is set to
	 *            USER_TIMER_MAXIMUM.
	 * 
	 * @param lpTimerFunc
	 *            A pointer to the function to be notified when the time-out
	 *            value elapses. For more information about the function, see
	 *            TimerProc. If lpTimerFunc is NULL, the system posts a WM_TIMER
	 *            message to the application queue. The hwnd member of the
	 *            message's MSG structure contains the value of the hWnd
	 *            parameter.
	 * 
	 * @return If the function succeeds and the hWnd parameter is NULL, the
	 *         return value is an integer identifying the new timer. An
	 *         application can pass this value to the KillTimer function to
	 *         destroy the timer. If the function succeeds and the hWnd
	 *         parameter is not NULL, then the return value is a nonzero
	 *         integer. An application can pass the value of the nIDEvent
	 *         parameter to the KillTimer function to destroy the timer. If the
	 *         function fails to create a timer, the return value is zero. To
	 *         get extended error information, call GetLastError.
	 */
	UINT_PTR SetTimer(/* _In_opt_ */HWND hWnd, /* _In_ */UINT_PTR nIDEvent, /* _In_ */UINT uElapse, /* _In_opt_TIMERPROC */
			Callback lpTimerFunc);

	/**
	 * Enables an application to supersede the top-level exception handler of
	 * each thread of a process. After calling this function, if an exception
	 * occurs in a process that is not being debugged, and the exception makes
	 * it to the unhandled exception filter, that filter will call the exception
	 * filter function specified by the lpTopLevelExceptionFilter parameter.
	 * 
	 * @param lpTopLevelExceptionFilter
	 *            A pointer to a top-level exception filter function that will
	 *            be called whenever the UnhandledExceptionFilter function gets
	 *            control, and the process is not being debugged. A value of
	 *            NULL for this parameter specifies default handling within
	 *            UnhandledExceptionFilter.
	 * 
	 * @return The SetUnhandledExceptionFilter function returns the address of
	 *         the previous exception filter established with the function. A
	 *         NULL return value means that there is no current top-level
	 *         exception handler.
	 */
	/* LPTOP_LEVEL_EXCEPTION_FILTER */Callback SetUnhandledExceptionFilter(/* _In_ */Callback lpTopLevelExceptionFilter);

	/**
	 * Retrieves the size, in bytes, of the specified resource.
	 * 
	 * @param hModule
	 *            A handle to the module whose executable file contains the
	 *            resource.
	 * 
	 * @param hResInfo
	 *            A handle to the resource. This handle must be created by using
	 *            the FindResource or FindResourceEx function.
	 * 
	 * @return If the function succeeds, the return value is the number of bytes
	 *         in the resource. If the function fails, the return value is zero.
	 *         To get extended error information, call GetLastError.
	 */
	DWORD SizeofResource(/* _In_opt_ */HMODULE hModule, /* _In_ */HRSRC hResInfo);

	/**
	 * Suspends the execution of the current thread until the time-out interval
	 * elapses.
	 * 
	 * @param dwMilliseconds
	 *            The time interval for which execution is to be suspended, in
	 *            milliseconds. A value of zero causes the thread to relinquish
	 *            the remainder of its time slice to any other thread that is
	 *            ready to run. If there are no other threads ready to run, the
	 *            function returns immediately, and the thread continues
	 *            execution. Windows XP: A value of zero causes the thread to
	 *            relinquish the remainder of its time slice to any other thread
	 *            of equal priority that is ready to run. If there are no other
	 *            threads of equal priority ready to run, the function returns
	 *            immediately, and the thread continues execution. This behavior
	 *            changed starting with Windows Server 2003. A value of INFINITE
	 *            indicates that the suspension should not time out.
	 * 
	 * @return 0
	 */
	void Sleep(/* _In_ */DWORD dwMilliseconds);

	/**
	 * Converts a system time to file time format. System time is based on
	 * Coordinated Universal Time (UTC).
	 * 
	 * @param lpSystemTime
	 *            A pointer to a SYSTEMTIME structure that contains the system
	 *            time to be converted from UTC to file time format.
	 * 
	 * @param lpFileTime
	 *            A pointer to a FILETIME structure to receive the converted
	 *            system time.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SystemTimeToFileTime(/* _In_ */SYSTEMTIME lpSystemTime, /* _Out_ */FILETIME lpFileTime);

	/**
	 * Terminates the specified process and all of its threads.
	 * 
	 * @param hProcess
	 *            A handle to the process to be terminated.
	 * 
	 * @param uExitCode
	 *            The exit code to be used by the process and threads terminated
	 *            as a result of this call. Use the GetExitCodeProcess function
	 *            to retrieve a process's exit value. Use the GetExitCodeThread
	 *            function to retrieve a thread's exit value.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL TerminateProcess(/* _In_ */HANDLE hProcess, /* _In_ */UINT uExitCode);

	/**
	 * Terminates a thread.
	 * 
	 * @param hThread
	 *            A handle to the thread to be terminated. The handle must have
	 *            the THREAD_TERMINATE access right. For more information, see
	 *            Thread Security and Access Rights.
	 * 
	 * @param dwExitCode
	 *            The exit code for the thread. Use the GetExitCodeThread
	 *            function to retrieve a thread's exit value.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL TerminateThread(/* _Inout_ */HANDLE hThread, /* _In_ */DWORD dwExitCode);

	/**
	 * Allocates a thread local storage (TLS) index. Any thread of the process
	 * can subsequently use this index to store and retrieve values that are
	 * local to the thread, because each thread receives its own slot for the
	 * index.
	 * 
	 * @return If the function succeeds, the return value is a TLS index. The
	 *         slots for the index are initialized to zero. If the function
	 *         fails, the return value is TLS_OUT_OF_INDEXES. To get extended
	 *         error information, call GetLastError.
	 */
	DWORD TlsAlloc();

	/**
	 * Releases a thread local storage (TLS) index, making it available for
	 * reuse.
	 * 
	 * @param dwTlsIndex
	 *            The TLS index that was allocated by the TlsAlloc function.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL TlsFree(/* _In_ */DWORD dwTlsIndex);

	/**
	 * Retrieves the value in the calling thread's thread local storage (TLS)
	 * slot for the specified TLS index. Each thread of a process has its own
	 * slot for each TLS index.
	 * 
	 * @param dwTlsIndex
	 *            The TLS index that was allocated by the TlsAlloc function.
	 * 
	 * @return If the function succeeds, the return value is the value stored in
	 *         the calling thread's TLS slot associated with the specified
	 *         index. If dwTlsIndex is a valid index allocated by a successful
	 *         call to TlsAlloc, this function always succeeds. If the function
	 *         fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	LPVOID TlsGetValue(/* _In_ */DWORD dwTlsIndex);

	/**
	 * Stores a value in the calling thread's thread local storage (TLS) slot
	 * for the specified TLS index. Each thread of a process has its own slot
	 * for each TLS index.
	 * 
	 * @param dwTlsIndex
	 *            The TLS index that was allocated by the TlsAlloc function.
	 * 
	 * @param lpTlsValue
	 *            The value to be stored in the calling thread's TLS slot for
	 *            the index.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL TlsSetValue(/* _In_ */DWORD dwTlsIndex, /* _In_opt_ */LPVOID lpTlsValue);

	/**
	 * Reserves or commits a region of pages in the virtual address space of the
	 * calling process. Memory allocated by this function is automatically
	 * initialized to zero, unless MEM_RESET is specified.
	 * 
	 * @param lpAddress
	 *            : The starting address of the region to allocate. If the
	 *            memory is being reserved, the specified address is rounded
	 *            down to the nearest multiple of the allocation granularity. If
	 *            the memory is already reserved and is being committed, the
	 *            address is rounded down to the next page boundary. To
	 *            determine the size of a page and the allocation granularity on
	 *            the host computer, use the GetSystemInfo function. If this
	 *            parameter is NULL, the system determines where to allocate the
	 *            region.
	 * 
	 * @param dwSize
	 *            : The size of the region, in bytes. If the lpAddress parameter
	 *            is NULL, this value is rounded up to the next page boundary.
	 *            Otherwise, the allocated pages include all pages containing
	 *            one or more bytes in the range from lpAddress to
	 *            lpAddress+dwSize. This means that a 2-byte range straddling a
	 *            page boundary causes both pages to be included in the
	 *            allocated region.
	 * 
	 * @param flAllocationType
	 *            : The type of memory allocation. This parameter must contain
	 *            one of the following values.
	 * 
	 * @param flProtect
	 *            : The memory protection for the region of pages to be
	 *            allocated. If the pages are being committed, you can specify
	 *            any one of the memory protection constants.
	 * 
	 * @return If the function succeeds, the return value is the base address of
	 *         the allocated region of pages.
	 */
	LPVOID VirtualAlloc(LPVOID lpAddress, SIZE_T dwSize, DWORD flAllocationType, DWORD flProtect);

	/**
	 * Reserves or commits a region of memory within the virtual address space
	 * of a specified process. The function initializes the memory it allocates
	 * to zero, unless MEM_RESET is used.
	 * 
	 * @param hProcess
	 *            The handle to a process. The function allocates memory within
	 *            the virtual address space of this process.
	 * 
	 * @param lpAddress
	 *            : The starting address of the region to allocate. If the
	 *            memory is being reserved, the specified address is rounded
	 *            down to the nearest multiple of the allocation granularity. If
	 *            the memory is already reserved and is being committed, the
	 *            address is rounded down to the next page boundary. To
	 *            determine the size of a page and the allocation granularity on
	 *            the host computer, use the GetSystemInfo function. If this
	 *            parameter is NULL, the system determines where to allocate the
	 *            region.
	 * 
	 * @param dwSize
	 *            : The size of the region, in bytes. If the lpAddress parameter
	 *            is NULL, this value is rounded up to the next page boundary.
	 *            Otherwise, the allocated pages include all pages containing
	 *            one or more bytes in the range from lpAddress to
	 *            lpAddress+dwSize. This means that a 2-byte range straddling a
	 *            page boundary causes both pages to be included in the
	 *            allocated region.
	 * 
	 * @param flAllocationType
	 *            : The type of memory allocation. This parameter must contain
	 *            one of the following values.
	 * 
	 * @param flProtect
	 *            : The memory protection for the region of pages to be
	 *            allocated. If the pages are being committed, you can specify
	 *            any one of the memory protection constants.
	 * 
	 * @return If the function succeeds, the return value is the base address of
	 *         the allocated region of pages. If the function fails, the return
	 *         value is NULL. To get extended error information, call
	 *         GetLastError.
	 */
	LPVOID VirtualAllocEx(/* _In_ */HANDLE hProcess, /* _In_opt_ */LPVOID lpAddress, /* _In_ */SIZE_T dwSize, /* _In_ */
			DWORD flAllocationType, /* _In_ */DWORD flProtect);

	/**
	 * Changes the protection on a region of committed pages in the virtual
	 * address space of the calling process.
	 * 
	 * @param lpAddress
	 *            A pointer an address that describes the starting page of the
	 *            region of pages whose access protection attributes are to be
	 *            changed.
	 * 
	 * @param dwSize
	 *            The size of the region whose access protection attributes are
	 *            to be changed, in bytes. The region of affected pages includes
	 *            all pages containing one or more bytes in the range from the
	 *            lpAddress parameter to (lpAddress+dwSize). This means that a
	 *            2-byte range straddling a page boundary causes the protection
	 *            attributes of both pages to be changed.
	 * 
	 * @param flNewProtect
	 *            The memory protection option. This parameter can be one of the
	 *            memory protection constants.
	 * 
	 * @param lpflOldProtect
	 *            A pointer to a variable that receives the previous access
	 *            protection value of the first page in the specified region of
	 *            pages. If this parameter is NULL or does not point to a valid
	 *            variable, the function fails.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL VirtualProtect(/* _In_ */LPVOID lpAddress, /* _In_ */SIZE_T dwSize, /* _In_ */DWORD flNewProtect, /* _Out_ */
			DWORDByReference lpflOldProtect);

	/**
	 * Retrieves information about a range of pages in the virtual address space
	 * of the calling process.
	 * 
	 * @param lpAddress
	 *            A pointer to the base address of the region of pages to be
	 *            queried. This value is rounded down to the next page boundary.
	 *            To determine the size of a page on the host computer, use the
	 *            GetSystemInfo function.
	 * 
	 * @param lpBuffer
	 *            A pointer to a MEMORY_BASIC_INFORMATION structure in which
	 *            information about the specified page range is returned.
	 * 
	 * @param dwLength
	 *            The size of the buffer pointed to by the lpBuffer parameter,
	 *            in bytes.
	 * 
	 * @return The return value is the actual number of bytes returned in the
	 *         information buffer. If the function fails, the return value is
	 *         zero. To get extended error information, call GetLastError.
	 */
	SIZE_T VirtualQuery(/* _In_opt_ */LPVOID lpAddress, /* _Out_ */MEMORY_BASIC_INFORMATION lpBuffer, /* _In_ */
			SIZE_T dwLength);

	/**
	 * Maps a UTF-16 (wide character) string to a new character string. The new
	 * character string is not necessarily from a multibyte character set.
	 * 
	 * @param CodePage
	 *            Code page to use in performing the conversion. This parameter
	 *            can be set to the value of any code page that is installed or
	 *            available in the operating system. For a list of code pages,
	 *            see Code Page Identifiers. Your application can also specify
	 *            one of the values shown in the following table.
	 * 
	 * @param dwFlags
	 *            Flags indicating the conversion type. The application can
	 *            specify a combination of the following values. The function
	 *            performs more quickly when none of these flags is set. The
	 *            application should specify WC_NO_BEST_FIT_CHARS and
	 *            WC_COMPOSITECHECK with the specific value WC_DEFAULTCHAR to
	 *            retrieve all possible conversion results. If all three values
	 *            are not provided, some results will be missing.
	 * 
	 * @param lpWideCharStr
	 *            Pointer to the Unicode string to convert.
	 * 
	 * @param cchWideChar
	 *            Size, in characters, of the string indicated by lpWideCharStr.
	 *            Alternatively, this parameter can be set set to -1 if the
	 *            string is null-terminated. If cchWideChar is set to 0, the
	 *            function fails.
	 * 
	 * @param lpMultiByteStr
	 *            Pointer to a buffer that receives the converted string.
	 * 
	 * @param cbMultiByte
	 *            Size, in bytes, of the buffer indicated by lpMultiByteStr. If
	 *            this parameter is set to 0, the function returns the required
	 *            buffer size for lpMultiByteStr and makes no use of the output
	 *            parameter itself.
	 * 
	 * @param lpDefaultChar
	 *            Pointer to the character to use if a character cannot be
	 *            represented in the specified code page. The application sets
	 *            this parameter to NULL if the function is to use a system
	 *            default value. To obtain the system default character, the
	 *            application can call the GetCPInfo or GetCPInfoEx function.
	 * 
	 * @param lpUsedDefaultChar
	 *            Pointer to a flag that indicates if the function has used a
	 *            default character in the conversion. The flag is set to TRUE
	 *            if one or more characters in the source string cannot be
	 *            represented in the specified code page. Otherwise, the flag is
	 *            set to FALSE. This parameter can be set to NULL.
	 * 
	 * @return Returns the number of bytes written to the buffer pointed to by
	 *         lpMultiByteStr if successful. If the function succeeds and
	 *         cbMultiByte is 0, the return value is the required size, in
	 *         bytes, for the buffer indicated by lpMultiByteStr. If the input
	 *         byte/char sequences are invalid, returns U+FFFD for UTF
	 *         encodings. The function returns 0 if it does not succeed.
	 */
	int WideCharToMultiByte(/* _In_ */UINT CodePage, /* _In_ */DWORD dwFlags, /* _In_ */WString lpWideCharStr, /* _In_ */
			int cchWideChar, /* _Out_opt_ */char[] lpMultiByteStr, /* _In_ */int cbMultiByte, /* _In_opt_ */
			String lpDefaultChar, /* _Out_opt_ */BOOLByReference lpUsedDefaultChar);

	/**
	 * Copies a string into the specified section of an initialization file.
	 * 
	 * @param lpAppNameThe
	 *            name of the section to which the string will be copied. If the
	 *            section does not exist, it is created. The name of the section
	 *            is case-independent; the string can be any combination of
	 *            uppercase and lowercase letters.
	 * 
	 * @param lpKeyName
	 *            The name of the key to be associated with a string. If the key
	 *            does not exist in the specified section, it is created. If
	 *            this parameter is NULL, the entire section, including all
	 *            entries within the section, is deleted.
	 * 
	 * @param lpString
	 *            A null-terminated string to be written to the file. If this
	 *            parameter is NULL, the key pointed to by the lpKeyName
	 *            parameter is deleted.
	 * 
	 * @param lpFileName
	 *            The name of the initialization file.
	 * 
	 * @return If the function successfully copies the string to the
	 *         initialization file, the return value is nonzero. If the function
	 *         fails, or if it flushes the cached version of the most recently
	 *         accessed initialization file, the return value is zero. To get
	 *         extended error information, call GetLastError.
	 */
	BOOL WritePrivateProfileString(/* _In_ */WString lpAppName, /* _In_ */WString lpKeyName, /* _In_ */WString lpString, /* _In_ */
			WString lpFileName);

	/**
	 * Writes data to an area of memory in a specified process. The entire area
	 * to be written to must be accessible or the operation fails.
	 * 
	 * @param hProcess
	 *            A handle to the process memory to be modified. The handle must
	 *            have PROCESS_VM_WRITE and PROCESS_VM_OPERATION access to the
	 *            process.
	 * 
	 * @param lpBaseAddress
	 *            A pointer to the base address in the specified process to
	 *            which data is written. Before data transfer occurs, the system
	 *            verifies that all data in the base address and memory of the
	 *            specified size is accessible for write access, and if it is
	 *            not accessible, the function fails.
	 * 
	 * @param lpBuffer
	 *            A pointer to the buffer that contains data to be written in
	 *            the address space of the specified process.
	 * 
	 * @param nSize
	 *            The number of bytes to be written to the specified process.
	 * 
	 * @param lpNumberOfBytesWritten
	 *            A pointer to a variable that receives the number of bytes
	 *            transferred into the specified process. This parameter is
	 *            optional. If lpNumberOfBytesWritten is NULL, the parameter is
	 *            ignored.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is 0 (zero). To get extended
	 *         error information, call GetLastError. The function fails if the
	 *         requested write operation crosses into an area of the process
	 *         that is inaccessible.
	 */
	BOOL WriteProcessMemory(/* _In_ */HANDLE hProcess, /* _In_ */LPVOID lpBaseAddress, /* _In_ */LPVOID lpBuffer, /* _In_ */
			SIZE_T nSize, /* _Out_ */ULONG_PTRByReference lpNumberOfBytesWritten);

	/**
	 * 
	 * @param lpOut
	 * @param lpFmt
	 * @param args
	 * @return
	 */
	int wsprintf(/* _Out_ */char[] lpOut, /* _In_ */String lpFmt, /* _In_ */long[] args);

	/**
	 * 
	 * @param dwFlags
	 * @param pMessage
	 * @param dwMessageId
	 * @param dwLanguageId
	 * @param lpBuffer
	 * @param nSize
	 * @param pArgs
	 * @return
	 */
	DWORD FormatMessageW(
	/* _In_ */DWORD dwFlags,
	/* _In_opt_ */String pMessage,
	/* _In_ */DWORD dwMessageId,
	/* _In_ */DWORD dwLanguageId,
	/* _Out_ */char[] lpBuffer,
	/* _In_ */DWORD nSize,
	/* _In_opt_ */String[] pArgs);

	/**
	 * Formats a message string. The function requires a message definition as
	 * input. The message definition can come from a buffer passed into the
	 * function. It can come from a message table resource in an already-loaded
	 * module. Or the caller can ask the function to search the system's message
	 * table resource(s) for the message definition. The function finds the
	 * message definition in a message table resource based on a message
	 * identifier and a language identifier. The function copies the formatted
	 * message text to an output buffer, processing any embedded insert
	 * sequences if requested.
	 * 
	 * @param dwFlags
	 *            The formatting options, and how to interpret the lpSource
	 *            parameter. The low-order byte of dwFlags specifies how the
	 *            function handles line breaks in the output buffer. The
	 *            low-order byte can also specify the maximum width of a
	 *            formatted output line.
	 * 
	 * @param lpSource
	 *            The location of the message definition. The type of this
	 *            parameter depends upon the settings in the dwFlags parameter.
	 * 
	 * @param dwMessageId
	 *            The message identifier for the requested message. This
	 *            parameter is ignored if dwFlags includes
	 *            FORMAT_MESSAGE_FROM_STRING.
	 * 
	 * @param dwLanguageId
	 *            The language identifier for the requested message. This
	 *            parameter is ignored if dwFlags includes
	 *            FORMAT_MESSAGE_FROM_STRING.
	 * 
	 *            If you pass a specific LANGID in this parameter, FormatMessage
	 *            will return a message for that LANGID only. If the function
	 *            cannot find a message for that LANGID, it sets Last-Error to
	 *            ERROR_RESOURCE_LANG_NOT_FOUND. If you pass in zero,
	 *            FormatMessage looks for a message for LANGIDs in the following
	 *            order:
	 * 
	 *            <ol>
	 *            <li>Language neutral
	 *            <li>Thread LANGID, based on the thread's locale value
	 *            <li>User default LANGID, based on the user's default locale
	 *            value
	 *            <li>System default LANGID, based on the system default locale
	 *            value
	 *            <li>US English
	 *            </ol>
	 * 
	 *            If FormatMessage does not locate a message for any of the
	 *            preceding LANGIDs, it returns any language message string that
	 *            is present. If that fails, it returns
	 *            ERROR_RESOURCE_LANG_NOT_FOUND.
	 * 
	 * @param lpBuffer
	 *            A pointer to a buffer that receives the null-terminated string
	 *            that specifies the formatted message. If dwFlags includes
	 *            FORMAT_MESSAGE_ALLOCATE_BUFFER, the function allocates a
	 *            buffer using the LocalAlloc function, and places the pointer
	 *            to the buffer at the address specified in lpBuffer.
	 * 
	 * @param nSize
	 *            If the FORMAT_MESSAGE_ALLOCATE_BUFFER flag is not set, this
	 *            parameter specifies the size of the output buffer, in TCHARs.
	 *            If FORMAT_MESSAGE_ALLOCATE_BUFFER is set, this parameter
	 *            specifies the minimum number of TCHARs to allocate for an
	 *            output buffer. The output buffer cannot be larger than 64K
	 *            bytes.
	 * 
	 * @param Arguments
	 *            An array of values that are used as insert values in the
	 *            formatted message. A %1 in the format string indicates the
	 *            first value in the Arguments array; a %2 indicates the second
	 *            argument; and so on.
	 * 
	 * @return If the function succeeds, the return value is the number of
	 *         TCHARs stored in the output buffer, excluding the terminating
	 *         null character.
	 * 
	 */
	DWORD FormatMessage(/* _In_ */DWORD dwFlags, /* _In_opt_ */LPVOID lpSource, /* _In_ */
			DWORD dwMessageId, /* _In_ */
			DWORD dwLanguageId, /* _Out_ */char[] lpBuffer, /* _In_ */
			DWORD nSize, /* _In_opt_ */String[] Arguments);

	/**
	 * Retrieves the priority class for the specified process. This value,
	 * together with the priority value of each thread of the process,
	 * determines each thread's base priority level.
	 * 
	 * @param hProcess
	 *            A handle to the process. The handle must have the
	 *            PROCESS_QUERY_INFORMATION or PROCESS_QUERY_LIMITED_INFORMATION
	 *            access right. For more information, see Process Security and
	 *            Access Rights. Windows Server 2003 and Windows XP: The handle
	 *            must have the PROCESS_QUERY_INFORMATION access right.
	 * 
	 * @return If the function succeeds, the return value is the priority class
	 *         of the specified process. If the function fails, the return value
	 *         is zero. To get extended error information, call GetLastError.
	 */
	DWORD GetPriorityClass(/* _In_ */HANDLE hProcess);

	/**
	 * Sets the priority class for the specified process. This value together
	 * with the priority value of each thread of the process determines each
	 * thread's base priority level.
	 * 
	 * @param hProcess
	 *            A handle to the process. The handle must have the
	 *            PROCESS_SET_INFORMATION access right. For more information,
	 *            see Process Security and Access Rights.
	 * 
	 * @param dwPriorityClass
	 *            The priority class for the process.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SetPriorityClass(/* _In_ */HANDLE hProcess, /* _In_ */DWORD dwPriorityClass);

	/**
	 * Retrieves information about the first thread of any process encountered
	 * in a system snapshot.
	 * 
	 * @param hSnapshot
	 *            A handle to the snapshot returned from a previous call to the
	 *            CreateToolhelp32Snapshot function.
	 * 
	 * @param lpte
	 *            A pointer to a THREADENTRY32 structure.
	 * 
	 * @return Returns TRUE if the first entry of the thread list has been
	 *         copied to the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES
	 *         error value is returned by the GetLastError function if no
	 *         threads exist or the snapshot does not contain thread
	 *         information.
	 */
	BOOL Thread32First(/* _In_ */HANDLE hSnapshot, /* _Inout_ */THREADENTRY32 lpte);

	/**
	 * Retrieves information about the next thread of any process encountered in
	 * the system memory snapshot.
	 * 
	 * @param hSnapshot
	 *            A handle to the snapshot returned from a previous call to the
	 *            CreateToolhelp32Snapshot function.
	 * 
	 * @param lpte
	 *            A pointer to a THREADENTRY32 structure.
	 * 
	 * @return Returns TRUE if the next entry of the thread list has been copied
	 *         to the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES error
	 *         value is returned by the GetLastError function if no threads
	 *         exist or the snapshot does not contain thread information.
	 */
	BOOL Thread32Next(/* _In_ */HANDLE hSnapshot, /* _Out_ */THREADENTRY32 lpte);

	/**
	 * Opens an existing thread object.
	 * 
	 * @param dwDesiredAccess
	 *            The access to the thread object. This access right is checked
	 *            against the security descriptor for the thread. This parameter
	 *            can be one or more of the thread access rights.
	 * 
	 * @param bInheritHandle
	 *            If this value is TRUE, processes created by this process will
	 *            inherit the handle. Otherwise, the processes do not inherit
	 *            this handle.
	 * 
	 * @param dwThreadId
	 *            The identifier of the thread to be opened.
	 * 
	 * @return If the function succeeds, the return value is an open handle to
	 *         the specified thread. If the function fails, the return value is
	 *         NULL. To get extended error information, call GetLastError.
	 */
	HANDLE OpenThread(/* _In_ */DWORD dwDesiredAccess, /* _In_ */BOOL bInheritHandle, /* _In_ */DWORD dwThreadId);

	/**
	 * Suspends the specified thread.
	 * 
	 * @param hThread
	 *            A handle to the thread that is to be suspended.
	 * 
	 * @return If the function succeeds, the return value is the thread's
	 *         previous suspend count; otherwise, it is (DWORD) -1. To get
	 *         extended error information, use the GetLastError function.
	 */
	DWORD SuspendThread(/* _In_ */HANDLE hThread);

	/**
	 * Decrements a thread's suspend count. When the suspend count is
	 * decremented to zero, the execution of the thread is resumed.
	 * 
	 * @param hThread
	 *            A handle to the thread to be restarted.
	 * 
	 * @return If the function succeeds, the return value is the thread's
	 *         previous suspend count.
	 */
	DWORD ResumeThread(/* _In_ */HANDLE hThread);

	/**
	 * Ends the calling thread.
	 * 
	 * @param dwExitCode
	 *            The exit code for the thread.
	 */
	void ExitThread(/* _In_ */DWORD dwExitCode);

	/**
	 * Generates simple tones on the speaker. The function is synchronous; it
	 * performs an alertable wait and does not return control to its caller
	 * until the sound finishes.
	 * 
	 * @param dwFreq
	 *            The frequency of the sound, in hertz. This parameter must be
	 *            in the range 37 through 32,767 (0x25 through 0x7FFF).
	 * 
	 * @param dwDuration
	 *            The duration of the sound, in milliseconds.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL Beep(/* _In_ */DWORD dwFreq, /* _In_ */DWORD dwDuration);

	/**
	 * Changes the protection on a region of committed pages in the virtual
	 * address space of a specified process.
	 * 
	 * @param hProcess
	 *            A handle to the process whose memory protection is to be
	 *            changed. The handle must have the PROCESS_VM_OPERATION access
	 *            right. For more information, see Process Security and Access
	 *            Rights.
	 * 
	 * @param lpAddress
	 *            A pointer to the base address of the region of pages whose
	 *            access protection attributes are to be changed.
	 * 
	 * @param dwSize
	 *            The size of the region whose access protection attributes are
	 *            changed, in bytes. The region of affected pages includes all
	 *            pages containing one or more bytes in the range from the
	 *            lpAddress parameter to (lpAddress+dwSize). This means that a
	 *            2-byte range straddling a page boundary causes the protection
	 *            attributes of both pages to be changed.
	 * 
	 * @param flNewProtect
	 *            The memory protection option. This parameter can be one of the
	 *            memory protection constants.
	 * 
	 * @param lpflOldProtect
	 *            A pointer to a variable that receives the previous access
	 *            protection of the first page in the specified region of pages.
	 *            If this parameter is NULL or does not point to a valid
	 *            variable, the function fails.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL VirtualProtectEx(/* _In_ */HANDLE hProcess, /* _In_ */LPVOID lpAddress, /* _In_ */SIZE_T dwSize, /* _In_ */
			DWORD flNewProtect, /* _Out_ */DWORDByReference lpflOldProtect);

	/**
	 * 
	 * @param ExceptionInfo
	 * @return
	 */
	LONG UnhandledExceptionFilter(/* _In_ struct *//* _EXCEPTION_POINTERS */Pointer ExceptionInfo);

	/**
	 * Retrieves information about the file system and volume associated with
	 * the specified root directory.
	 * 
	 * @param lpRootPathName
	 * @param lpVolumeNameBuffer
	 * @param nVolumeNameSize
	 * @param lpVolumeSerialNumber
	 * @param lpMaximumComponentLength
	 * @param lpFileSystemFlags
	 * @param lpFileSystemNameBuffer
	 * @param nFileSystemNameSize
	 * @return
	 */
	BOOL GetVolumeInformation(
	/* _In_opt_ */WString lpRootPathName,
	/* _Out_opt_ */char[] lpVolumeNameBuffer,
	/* _In_ */DWORD nVolumeNameSize,
	/* _Out_opt_ */DWORDByReference lpVolumeSerialNumber,
	/* _Out_opt_ */DWORDByReference lpMaximumComponentLength,
	/* _Out_opt_ */DWORDByReference lpFileSystemFlags,
	/* _Out_opt_ */char[] lpFileSystemNameBuffer,
	/* _In_ */DWORD nFileSystemNameSize);

	/**
	 * Determines whether the file I/O functions are using the ANSI or OEM
	 * character set code page. This function is useful for 8-bit console input
	 * and output operations.
	 * 
	 * @return If the set of file I/O functions is using the ANSI code page, the
	 *         return value is nonzero. If the set of file I/O functions is
	 *         using the OEM code page, the return value is zero.
	 */
	BOOL AreFileApisANSI();

	/**
	 * Multiplies two 32-bit values and then divides the 64-bit result by a
	 * third 32-bit value. The final result is rounded to the nearest integer.
	 * 
	 * @param nNumber
	 *            The multiplicand.
	 * 
	 * @param nNumerator
	 *            The multiplier.
	 * 
	 * @param nDenominator
	 *            The number by which the result of the multiplication operation
	 *            is to be divided.
	 * 
	 * @return If the function succeeds, the return value is the result of the
	 *         multiplication and division, rounded to the nearest integer. If
	 *         the result is a positive half integer (ends in .5), it is rounded
	 *         up. If the result is a negative half integer, it is rounded down.
	 *         If either an overflow occurred or nDenominator was 0, the return
	 *         value is -1.
	 */
	int MulDiv(
	/* _In_ */int nNumber,
	/* _In_ */int nNumerator,
	/* _In_ */int nDenominator);

	/**
	 * Returns the locale identifier of the current locale for the calling
	 * thread.
	 * 
	 * @return Returns the locale identifier of the locale associated with the
	 *         current thread.
	 */
	LCID GetThreadLocale();

	/**
	 * Releases ownership of the specified critical section object.
	 * 
	 * @param lpCriticalSection
	 *            A pointer to the critical section object.
	 * 
	 * @return 0
	 */
	void LeaveCriticalSection(/* _Inout_ */RTL_CRITICAL_SECTION lpCriticalSection);

	/**
	 * Retrieves a copy of the character string associated with the specified
	 * global atom.
	 * 
	 * @param nAtom
	 *            The global atom associated with the character string to be
	 *            retrieved.
	 * 
	 * @param lpBuffer
	 *            The buffer for the character string.
	 * 
	 * @param nSize
	 *            The size, in characters, of the buffer.
	 * 
	 * @return If the function succeeds, the return value is the length of the
	 *         string copied to the buffer, in characters, not including the
	 *         terminating null character. If the function fails, the return
	 *         value is zero. To get extended error information, call
	 *         GetLastError.
	 */
	UINT GlobalGetAtomName(
	/* _In_ */ATOM nAtom,
	/* _Out_ */char[] lpBuffer,
	/* _In_ */int nSize);

	/**
	 * Waits until either a time-out interval elapses or an instance of the
	 * specified named pipe is available for connection (that is, the pipe's
	 * server process has a pending ConnectNamedPipe operation on the pipe).
	 * 
	 * @param lpNamedPipeName
	 *            The name of the named pipe. The string must include the name
	 *            of the computer on which the server process is executing. A
	 *            period may be used for the servername if the pipe is local.
	 *            The following pipe name format is used:
	 *            \\servername\pipe\pipename
	 * 
	 * @param nTimeOut
	 *            The number of milliseconds that the function will wait for an
	 *            instance of the named pipe to be available. You can used one
	 *            of the following values instead of specifying a number of
	 *            milliseconds.
	 * 
	 * @return If an instance of the pipe is available before the time-out
	 *         interval elapses, the return value is nonzero. If an instance of
	 *         the pipe is not available before the time-out interval elapses,
	 *         the return value is zero. To get extended error information, call
	 *         GetLastError.
	 */
	BOOL WaitNamedPipe(
	/* _In_ */String lpNamedPipeName,
	/* _In_ */DWORD nTimeOut);

	/**
	 * Initiates an unwind of procedure call frames.
	 * 
	 * @param TargetFrame
	 *            A pointer to the call frame that is the target of the unwind.
	 *            If this parameter is NULL, the function performs an exit
	 *            unwind.
	 * 
	 * @param TargetIp
	 *            The continuation address of the unwind. This parameter is
	 *            ignored if TargetFrame is NULL.
	 * 
	 * @param ExceptionRecord
	 *            A pointer to an EXCEPTION_RECORD structure.
	 * 
	 * @param ReturnValue
	 *            A value to be placed in the integer function return register
	 *            before continuing execution.
	 */
	void RtlUnwind(
	/* _In_opt_ */PVOID TargetFrame,
	/* _In_opt_ */PVOID TargetIp,
	/* _In_opt_ */EXCEPTION_RECORD ExceptionRecord,
	/* _In_ */PVOID ReturnValue);

	/**
	 * Initializes a critical section object and sets the spin count for the
	 * critical section. When a thread tries to acquire a critical section that
	 * is locked, the thread spins: it enters a loop which iterates spin count
	 * times, checking to see if the lock is released. If the lock is not
	 * released before the loop finishes, the thread goes to sleep to wait for
	 * the lock to be released.
	 * 
	 * @param lpCriticalSection
	 *            A pointer to the critical section object.
	 * 
	 * @param dwSpinCount
	 *            The spin count for the critical section object. On
	 *            single-processor systems, the spin count is ignored and the
	 *            critical section spin count is set to 0 (zero). On
	 *            multiprocessor systems, if the critical section is
	 *            unavailable, the calling thread spins dwSpinCount times before
	 *            performing a wait operation on a semaphore associated with the
	 *            critical section. If the critical section becomes free during
	 *            the spin operation, the calling thread avoids the wait
	 *            operation.
	 * 
	 * @return This function always returns a nonzero value.
	 */
	BOOL InitializeCriticalSectionAndSpinCount(
	/* _Out_ */RTL_CRITICAL_SECTION lpCriticalSection,
	/* _In_ */DWORD dwSpinCount);

	/**
	 * Stores a value in the calling fiber's fiber local storage (FLS) slot for
	 * the specified FLS index. Each fiber has its own slot for each FLS index.
	 * 
	 * @param dwFlsIndex
	 *            The FLS index that was allocated by the FlsAlloc function.
	 * 
	 * @param lpFlsData
	 *            The value to be stored in the FLS slot for the calling fiber.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError. The following errors can be
	 *         returned.
	 */
	BOOL FlsSetValue(
	/* _In_ */DWORD dwFlsIndex,
	/* _In_opt_ */PVOID lpFlsData);

	/**
	 * Retrieves the value in the calling fiber's fiber local storage (FLS) slot
	 * for the specified FLS index. Each fiber has its own slot for each FLS
	 * index.
	 * 
	 * @param dwFlsIndex
	 *            The FLS index that was allocated by the FlsAlloc function.
	 * 
	 * @return If the function succeeds, the return value is the value stored in
	 *         the calling fiber's FLS slot associated with the specified index.
	 *         If the function fails, the return value is NULL. To get extended
	 *         error information, call GetLastError.
	 */
	PVOID FlsGetValue(/* _In_ */DWORD dwFlsIndex);

	/**
	 * Allocates a fiber local storage (FLS) index. Any fiber in the process can
	 * subsequently use this index to store and retrieve values that are local
	 * to the fiber.
	 * 
	 * @param lpCallback
	 *            A pointer to the application-defined callback function of type
	 *            PFLS_CALLBACK_FUNCTION. This parameter is optional. For more
	 *            information, see FlsCallback.
	 * 
	 *            An application-defined function. If the FLS slot is in use,
	 *            FlsCallback is called on fiber deletion, thread exit, and when
	 *            an FLS index is freed. Specify this function when calling the
	 *            FlsAlloc function. The PFLS_CALLBACK_FUNCTION type defines a
	 *            pointer to this callback function. FlsCallback is a
	 *            placeholder for the application-defined function name.
	 * 
	 * @return If the function succeeds, the return value is an FLS index
	 *         initialized to zero. If the function fails, the return value is
	 *         FLS_OUT_OF_INDEXES. To get extended error information, call
	 *         GetLastError.
	 */
	DWORD FlsAlloc(
	/* _In_ *//* PFLS_CALLBACK_FUNCTION */Callback lpCallback);

	/**
	 * Retrieves the size of a memory block allocated from a heap by the
	 * HeapAlloc or HeapReAlloc function.
	 * 
	 * @param hHeap
	 *            A handle to the heap in which the memory block resides. This
	 *            handle is returned by either the HeapCreate or GetProcessHeap
	 *            function.
	 * 
	 * @param dwFlags
	 *            The heap size options. Specifying the following value
	 *            overrides the corresponding value specified in the flOptions
	 *            parameter when the heap was created by using the HeapCreate
	 *            function.
	 * 
	 * @param lpMem
	 *            A pointer to the memory block whose size the function will
	 *            obtain. This is a pointer returned by the HeapAlloc or
	 *            HeapReAlloc function. The memory block must be from the heap
	 *            specified by the hHeap parameter.
	 * 
	 * @return If the function succeeds, the return value is the requested size
	 *         of the allocated memory block, in bytes. If the function fails,
	 *         the return value is (SIZE_T)-1. The function does not call
	 *         SetLastError. An application cannot call GetLastError for
	 *         extended error information. If the lpMem parameter refers to a
	 *         heap allocation that is not in the heap specified by the hHeap
	 *         parameter, the behavior of the HeapSize function is undefined.
	 */
	SIZE_T HeapSize(
	/* _In_ */HANDLE hHeap,
	/* _In_ */DWORD dwFlags,
	/* _In_ */Pointer lpMem);

	/**
	 * Retrieves the current value of the performance counter, which is a high
	 * resolution (<1us) time stamp that can be used for time-interval
	 * measurements.
	 * 
	 * @param lpPerformanceCount
	 *            A pointer to a variable that receives the current
	 *            performance-counter value, in counts.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError. On systems that run Windows XP or
	 *         later, the function will always succeed and will thus never
	 *         return zero.
	 */
	BOOL QueryPerformanceCounter(
	/* _Out_ */LONGLONGByReference lpPerformanceCount);

	/**
	 * Determines if a specified character is a lead byte for the system default
	 * Windows ANSI code page (CP_ACP). A lead byte is the first byte of a
	 * two-byte character in a double-byte character set (DBCS) for the code
	 * page.
	 * 
	 * @param TestChar
	 *            The character to test.
	 * 
	 * @return Returns a nonzero value if the test character is potentially a
	 *         lead byte. The function returns 0 if the test character is not a
	 *         lead byte or if it is a single-byte character. To get extended
	 *         error information, the application can call GetLastError.
	 */
	BOOL IsDBCSLeadByte(
	/* _In_ */BYTE TestChar);

	/**
	 * Encodes the specified pointer. Encoded pointers can be used to provide
	 * another layer of protection for pointer values.
	 * 
	 * @param Ptr
	 *            The pointer to be encoded.
	 * 
	 * @return The function returns the encoded pointer.
	 */
	PVOID EncodePointer(/* _In_ */PVOID Ptr);

	/**
	 * Decodes a pointer that was previously encoded with EncodePointer.
	 * 
	 * @param Ptr
	 *            The pointer to be decoded.
	 * 
	 * @return The function returns the decoded pointer.
	 */
	PVOID DecodePointer(/* _In_ */PVOID Ptr);

	/**
	 * Searches the local atom table for the specified character string and
	 * retrieves the atom associated with that string.
	 * 
	 * @param lpString
	 *            The character string for which to search. Alternatively, you
	 *            can use an integer atom that has been converted using the
	 *            MAKEINTATOM macro. See Remarks for more information.
	 * 
	 * @return If the function succeeds, the return value is the atom associated
	 *         with the given string. If the function fails, the return value is
	 *         zero. To get extended error information, call GetLastError.
	 */
	ATOM FindAtom(/* _In_ */String lpString);

	/**
	 * Sets a 32-bit variable to the specified value as an atomic operation.
	 * 
	 * @param Target
	 *            A pointer to the value to be exchanged. The function sets this
	 *            variable to Value, and returns its prior value.
	 * 
	 * @param Value
	 *            The value to be exchanged with the value pointed to by Target.
	 * 
	 * @return The function returns the initial value of the Target parameter.
	 */
	LONG InterlockedExchange(/* _Inout_ */LONGByReference Target,/* _In_ */LONG Value);

	/**
	 * Retrieves the input code page used by the console associated with the
	 * calling process. A console uses its input code page to translate keyboard
	 * input into the corresponding character value.
	 * 
	 * @return The return value is a code that identifies the code page. For a
	 *         list of identifiers, see Code Page Identifiers.
	 */
	UINT GetConsoleCP();

	/**
	 * Returns the language identifier of the Region Format setting for the
	 * current user.
	 * 
	 * @return Returns the language identifier for the current user as set under
	 *         Control Panel > Clock, Language, and Region > Change date, time,
	 *         or number formats > Formats tab > Format dropdown.
	 */
	WORD GetUserDefaultLangID();

	/**
	 * Retrieves the priority value for the specified thread. This value,
	 * together with the priority class of the thread's process, determines the
	 * thread's base-priority level.
	 * 
	 * @param hThread
	 *            A handle to the thread. The handle must have the
	 *            THREAD_QUERY_INFORMATION or THREAD_QUERY_LIMITED_INFORMATION
	 *            access right. For more information, see Thread Security and
	 *            Access Rights.
	 * 
	 * @return If the function succeeds, the return value is the thread's
	 *         priority level. If the function fails, the return value is
	 *         THREAD_PRIORITY_ERROR_RETURN. To get extended error information,
	 *         call GetLastError.
	 */
	int GetThreadPriority(/* _In_ */HANDLE hThread);

	/**
	 * Opens a named file mapping object.
	 * 
	 * @param dwDesiredAccess
	 *            The access to the file mapping object. This access is checked
	 *            against any security descriptor on the target file mapping
	 *            object. For a list of values, see File Mapping Security and
	 *            Access Rights.
	 * 
	 * @param bInheritHandle
	 *            If this parameter is TRUE, a process created by the
	 *            CreateProcess function can inherit the handle; otherwise, the
	 *            handle cannot be inherited.
	 * 
	 * @param lpName
	 *            The name of the file mapping object to be opened. If there is
	 *            an open handle to a file mapping object by this name and the
	 *            security descriptor on the mapping object does not conflict
	 *            with the dwDesiredAccess parameter, the open operation
	 *            succeeds. The name can have a "Global\" or "Local\" prefix to
	 *            explicitly open an object in the global or session namespace.
	 *            The remainder of the name can contain any character except the
	 *            backslash character (\). For more information, see Kernel
	 *            Object Namespaces. Fast user switching is implemented using
	 *            Terminal Services sessions. The first user to log on uses
	 *            session 0, the next user to log on uses session 1, and so on.
	 *            Kernel object names must follow the guidelines outlined for
	 *            Terminal Services so that applications can support multiple
	 *            users.
	 * 
	 * @return If the function succeeds, the return value is an open handle to
	 *         the specified file mapping object. If the function fails, the
	 *         return value is NULL. To get extended error information, call
	 *         GetLastError.
	 */
	HANDLE OpenFileMapping(
	/* _In_ */DWORD dwDesiredAccess,
	/* _In_ */BOOL bInheritHandle,
	/* _In_ */String lpName);

	/**
	 * Returns the current original equipment manufacturer (OEM) code page
	 * identifier for the operating system.
	 * 
	 * @return Returns the current OEM code page identifier for the operating
	 *         system.
	 */
	UINT GetOEMCP();

	/**
	 * The GetAclInformation function retrieves information about an access
	 * control list (ACL).
	 * 
	 * @param pAcl
	 *            A pointer to an ACL. The function retrieves information about
	 *            this ACL. If a null value is passed, the function causes an
	 *            access violation.
	 * 
	 * @param pAclInformation
	 *            A pointer to a buffer to receive the requested information.
	 *            The structure that is placed into the buffer depends on the
	 *            information class requested in the dwAclInformationClass
	 *            parameter.
	 * 
	 * @param nAclInformationLength
	 *            The size, in bytes, of the buffer pointed to by the
	 *            pAclInformation parameter.
	 * 
	 * @param dwAclInformationClass
	 *            A value of the ACL_INFORMATION_CLASS enumeration that
	 *            indicates the class of information requested.
	 * 
	 *            This parameter can be one of two values from this enumeration:
	 * 
	 *            If the value is AclRevisionInformation, the function fills the
	 *            buffer pointed to by the pAclInformation parameter with an
	 *            ACL_REVISION_INFORMATION structure.
	 * 
	 *            If the value is AclSizeInformation, the function fills the
	 *            buffer pointed to by the pAclInformation parameter with an
	 *            ACL_SIZE_INFORMATION structure.
	 * 
	 * @return If the function succeeds, the function returns nonzero. If the
	 *         function fails, it returns zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL GetAclInformation(
	/* _In_ */ACL pAcl,
	/* _Out_ */Structure pAclInformation,
	/* _In_ */DWORD nAclInformationLength,
	/* _In_ */int dwAclInformationClass);

	/**
	 * Retrieves the size of the largest possible console window, based on the
	 * current font and the size of the display.
	 * 
	 * @param hConsoleOutput
	 *            A handle to the console screen buffer.
	 * 
	 * @return If the function succeeds, the return value is a COORD structure
	 *         that specifies the number of character cell rows (X member) and
	 *         columns (Y member) in the largest possible console window.
	 *         Otherwise, the members of the structure are zero. To get extended
	 *         error information, call GetLastError.
	 */
	int GetLargestConsoleWindowSize(HANDLE hConsoleOutput);

	/**
	 * Searches the global atom table for the specified character string and
	 * retrieves the global atom associated with that string.
	 * 
	 * @param lpString
	 *            The null-terminated character string for which to search.
	 *            Alternatively, you can use an integer atom that has been
	 *            converted using the MAKEINTATOM macro. See the Remarks for
	 *            more information.
	 * 
	 * @return If the function succeeds, the return value is the global atom
	 *         associated with the given string. If the function fails, the
	 *         return value is zero. To get extended error information, call
	 *         GetLastError.
	 */
	ATOM GlobalFindAtom(String lpString);

	/**
	 * Deletes an existing empty directory.
	 * 
	 * To perform this operation as a transacted operation, use the
	 * RemoveDirectoryTransacted function.
	 * 
	 * @param lpPathName
	 *            he path of the directory to be removed. This path must specify
	 *            an empty directory, and the calling process must have delete
	 *            access to the directory. In the ANSI version of this function,
	 *            the name is limited to MAX_PATH characters. To extend this
	 *            limit to 32,767 wide characters, call the Unicode version of
	 *            the function and prepend "\\?\" to the path. For more
	 *            information, see Naming a File.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL RemoveDirectory(String lpPathName);

	/**
	 * Increments (increases by one) the value of the specified 32-bit variable
	 * as an atomic operation. To operate on 64-bit values, use the
	 * InterlockedIncrement64 function.
	 * 
	 * @param Addend
	 *            A pointer to the variable to be incremented.
	 * 
	 * @return The function returns the resulting incremented value.
	 */
	LONG InterlockedIncrement(LONG Addend);

	/**
	 * Moves an existing file or directory, including its children, with various
	 * move options. The MoveFileWithProgress function is equivalent to the
	 * MoveFileEx function, except that MoveFileWithProgress allows you to
	 * provide a callback function that receives progress notifications. To
	 * perform this operation as a transacted operation, use the
	 * MoveFileTransacted function.
	 * 
	 * @param lpExistingFileName
	 *            The current name of the file or directory on the local
	 *            computer.
	 * 
	 * @param lpNewFileName
	 *            The new name of the file or directory on the local computer.
	 * 
	 * @param dwFlags
	 *            This parameter can be one or more of the following values.
	 * 
	 *            MOVEFILE_COPY_ALLOWED 2 (0x2)
	 * 
	 *            MOVEFILE_CREATE_HARDLINK 16 (0x10)
	 * 
	 *            MOVEFILE_DELAY_UNTIL_REBOOT 4 (0x4)
	 * 
	 *            MOVEFILE_FAIL_IF_NOT_TRACKABLE 32 (0x20)
	 * 
	 *            MOVEFILE_REPLACE_EXISTING 1 (0x1)
	 * 
	 *            MOVEFILE_WRITE_THROUGH 8 (0x8)
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero (0). To get extended
	 *         error information, call GetLastError.
	 */
	BOOL MoveFileEx(
	/* _In_ */String lpExistingFileName,
	/* _In_opt_ */String lpNewFileName,
	/* _In_ */DWORD dwFlags);

	/**
	 * Retrieves a copy of the character string associated with the specified
	 * local atom.
	 * 
	 * @param nAtom
	 *            The local atom that identifies the character string to be
	 *            retrieved.
	 * 
	 * @param lpBuffer
	 *            The character string.
	 * 
	 * @param nSize
	 *            The size, in characters, of the buffer.
	 * 
	 * @return If the function succeeds, the return value is the length of the
	 *         string copied to the buffer, in characters, not including the
	 *         terminating null character. If the function fails, the return
	 *         value is zero. To get extended error information, call
	 *         GetLastError.
	 */
	UINT GetAtomName(
	/* _In_ */ATOM nAtom,
	/* _Out_ */char[] lpBuffer,
	/* _In_ */int nSize);

	/**
	 * Retrieves information about any valid installed or available code page.
	 * 
	 * @param CodePage
	 *            Identifier for the code page for which to retrieve
	 *            information. The application can specify the code page
	 *            identifier for any installed or available code page, or one of
	 *            the following predefined values. See Code Page Identifiers for
	 *            a list of identifiers for ANSI and other code pages.
	 * 
	 * @param dwFlags
	 *            Reserved; must be 0.
	 * 
	 * @param lpCPInfoEx
	 *            Pointer to a CPINFOEX structure that receives information
	 *            about the code page.
	 * 
	 * @return Returns a nonzero value if successful, or 0 otherwise. To get
	 *         extended error information, the application can call
	 *         GetLastError, which can return one of the following error codes:
	 * 
	 *         ERROR_INVALID_PARAMETER. Any of the parameter values was invalid.
	 */
	BOOL GetCPInfoEx(
	/* _In_ */UINT CodePage,
	/* _In_ */DWORD dwFlags,
	/* _Out_ */CPINFOEX lpCPInfoEx);

	BOOL RegisterWowExec(DWORD Unknown);

	/**
	 * Retrieves the language identifier for the system default UI language of
	 * the operating system, also known as the "install language" on Windows
	 * Vista and later. For more information, see User Interface Language
	 * Management.
	 * 
	 * @return Returns the language identifier for the system default UI
	 *         language of the operating system. For more information, see the
	 *         Remarks section.
	 */
	WORD GetSystemDefaultUILanguage();

	/**
	 * Retrieves the current input mode of a console's input buffer or the
	 * current output mode of a console screen buffer.
	 * 
	 * @param hConsoleHandle
	 *            A handle to the console input buffer or the console screen
	 *            buffer. The handle must have the GENERIC_READ access right.
	 *            For more information, see Console Buffer Security and Access
	 *            Rights.
	 * 
	 * @param lpMode
	 *            A pointer to a variable that receives the current mode of the
	 *            specified buffer.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL GetConsoleMode(
	/* _In_ */HANDLE hConsoleHandle,
	/* _Out_ */DWORDByReference lpMode);

	/**
	 * Verifies that the calling process has read access to the specified range
	 * of memory.
	 * 
	 * @param lp
	 *            A pointer to the first byte of the memory block.
	 * 
	 * @param ucb
	 *            The size of the memory block, in bytes. If this parameter is
	 *            zero, the return value is zero.
	 * 
	 * @return If the calling process has read access to all bytes in the
	 *         specified memory range, the return value is zero. If the calling
	 *         process does not have read access to all bytes in the specified
	 *         memory range, the return value is nonzero. If the application is
	 *         compiled as a debugging version, and the process does not have
	 *         read access to all bytes in the specified memory range, the
	 *         function causes an assertion and breaks into the debugger.
	 *         Leaving the debugger, the function continues as usual, and
	 *         returns a nonzero value. This behavior is by design, as a
	 *         debugging aid.
	 */
	BOOL IsBadReadPtr(
	/* _In_ *//* const VOID * */LPVOID lp,
	/* _In_ */UINT_PTR ucb);

	/**
	 * Returns the locale identifier for the user default locale.
	 * 
	 * @return Returns the locale identifier for the user default locale,
	 *         represented as LOCALE_USER_DEFAULT. If the user default locale is
	 *         a custom locale, this function always returns
	 *         LOCALE_CUSTOM_DEFAULT, regardless of the custom locale that is
	 *         selected. For example, whether the user locale is Hawaiian (US),
	 *         value.
	 */
	LCID GetUserDefaultLCID();

	/**
	 * Retrieves the frequency of the performance counter. The frequency of the
	 * performance counter is fixed at system boot and is consistent across all
	 * processors. Therefore, the frequency need only be queried upon
	 * application initialization, and the result can be cached.
	 * 
	 * @param lpFrequency
	 *            A pointer to a variable that receives the current
	 *            performance-counter frequency, in counts per second. If the
	 *            installed hardware doesn't support a high-resolution
	 *            performance counter, this parameter can be zero (this will not
	 *            occur on systems that run Windows XP or later).
	 * 
	 * @return If the installed hardware supports a high-resolution performance
	 *         counter, the return value is nonzero. If the function fails, the
	 *         return value is zero. To get extended error information, call
	 *         GetLastError. On systems that run Windows XP or later, the
	 *         function will always succeed and will thus never return zero.
	 */
	BOOL QueryPerformanceFrequency(/* _Out_ */LARGE_INTEGER lpFrequency);

	/**
	 * Sets the current locale of the calling thread.
	 * 
	 * @param Locale
	 *            Locale identifier that specifies the locale. You can use the
	 *            MAKELCID macro to create a locale identifier or use one of the
	 *            following predefined values. LOCALE_CUSTOM_DEFAULT
	 * 
	 *            LOCALE_CUSTOM_UI_DEFAULT
	 * 
	 *            LOCALE_CUSTOM_UNSPECIFIED
	 * 
	 *            LOCALE_INVARIANT
	 * 
	 *            LOCALE_SYSTEM_DEFAULT
	 * 
	 *            LOCALE_USER_DEFAULT
	 * 
	 * @return The function should return an LCID on success. This is the LCID
	 *         of the previous thread locale.
	 */
	BOOL SetThreadLocale(/* _In_ */LCID Locale);

	/**
	 * Retrieves the full path and file name of the specified file. To perform
	 * this operation as a transacted operation, use the
	 * GetFullPathNameTransacted function.
	 * 
	 * @param lpFileName
	 *            The name of the file. This parameter can be a short (the 8.3
	 *            form) or long file name. This string can also be a share or
	 *            volume name. In the ANSI version of this function, the name is
	 *            limited to MAX_PATH characters. To extend this limit to 32,767
	 *            wide characters, call the Unicode version of the function and
	 *            prepend "\\?\" to the path. For more information, see Naming a
	 *            File.
	 * 
	 * @param nBufferLength
	 *            The size of the buffer to receive the null-terminated string
	 *            for the drive and path, in TCHARs.
	 * 
	 * @param lpBuffer
	 *            A pointer to a buffer that receives the null-terminated string
	 *            for the drive and path.
	 * 
	 * @param lpFilePart
	 *            A pointer to a buffer that receives the address (within
	 *            lpBuffer) of the final file name component in the path. This
	 *            parameter can be NULL. If lpBuffer refers to a directory and
	 *            not a file, lpFilePart receives zero.
	 * 
	 * @return If the function succeeds, the return value is the length, in
	 *         TCHARs, of the string copied to lpBuffer, not including the
	 *         terminating null character. If the lpBuffer buffer is too small
	 *         to contain the path, the return value is the size, in TCHARs, of
	 *         the buffer that is required to hold the path and the terminating
	 *         null character. If the function fails for any other reason, the
	 *         return value is zero. To get extended error information, call
	 *         GetLastError.
	 */
	int GetFullPathName(
	/* _In_ */String lpFileName,
	/* _In_ */int nBufferLength,
	/* _Out_ */char[] lpBuffer,
	/* _Out_ */Pointer lpFilePart);

	/**
	 * Determines whether the specified processor feature is supported by the
	 * current computer.
	 * 
	 * @param ProcessorFeature
	 *            The processor feature to be tested. This parameter can be one
	 *            of the following values.
	 * 
	 * @return If the feature is supported, the return value is a nonzero value.
	 *         If the feature is not supported, the return value is zero. If the
	 *         HAL does not support detection of the feature, whether or not the
	 *         hardware supports the feature, the return value is also zero.
	 */
	BOOL IsProcessorFeaturePresent(/* _In_ */int ProcessorFeature);

	/**
	 * Determines if a specified code page is valid.
	 * 
	 * @param CodePage
	 *            Code page identifier for the code page to check.
	 * 
	 * @return Returns a nonzero value if the code page is valid, or 0 if the
	 *         code page is invalid.
	 */
	BOOL IsValidCodePage(/* _In_ */UINT CodePage);

	/**
	 * Retrieves a module handle for the specified module and increments the
	 * module's reference count unless
	 * GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT is specified. The module
	 * must have been loaded by the calling process.
	 * 
	 * @param dwFlags
	 *            This parameter can be zero or one or more of the following
	 *            values. If the module's reference count is incremented, the
	 *            caller must use the FreeLibrary function to decrement the
	 *            reference count when the module handle is no longer needed.
	 * 
	 * @param lpModuleName
	 *            The name of the loaded module (either a .dll or .exe file), or
	 *            an address in the module (if dwFlags is
	 *            GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS).
	 * 
	 * @param phModule
	 *            A handle to the specified module. If the function fails, this
	 *            parameter is NULL.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, see GetLastError.
	 */
	BOOL GetModuleHandleEx(
	/* _In_ */int dwFlags,
	/* _In_opt_ */String lpModuleName,
	/* _Out_ */HANDLEByReference phModule);

	/**
	 * Writes a character string to a console screen buffer beginning at the
	 * current cursor location.
	 * 
	 * @param hConsoleOutput
	 *            A handle to the console screen buffer. The handle must have
	 *            the GENERIC_WRITE access right. For more information, see
	 *            Console Buffer Security and Access Rights.
	 * 
	 * @param lpBuffer
	 *            A pointer to a buffer that contains characters to be written
	 *            to the console screen buffer. The storage for this buffer is
	 *            allocated from a shared heap for the process that is 64 KB in
	 *            size. The maximum size of the buffer will depend on heap
	 *            usage.
	 * 
	 * @param nNumberOfCharsToWrite
	 *            The number of characters to be written. If the total size of
	 *            the specified number of characters exceeds the available heap,
	 *            the function fails with ERROR_NOT_ENOUGH_MEMORY.
	 * 
	 * @param lpNumberOfCharsWritten
	 *            A pointer to a variable that receives the number of characters
	 *            actually written.
	 * 
	 * @param lpReserved
	 *            Reserved; must be NULL.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL WriteConsole(
	/* _In_ */HANDLE hConsoleOutput,
	/* _In_ */String lpBuffer,
	/* _In_ */DWORD nNumberOfCharsToWrite,
	/* _Out_ */DWORDByReference lpNumberOfCharsWritten,
	/* _Reserved_ */LPVOID lpReserved);

	/**
	 * Adds a character string to the local atom table and returns a unique
	 * value (an atom) identifying the string.
	 * 
	 * @param lpString
	 *            The null-terminated string to be added. The string can have a
	 *            maximum size of 255 bytes. Strings differing only in case are
	 *            considered identical. The case of the first string added is
	 *            preserved and returned by the GetAtomName function.
	 *            Alternatively, you can use an integer atom that has been
	 *            converted using the MAKEINTATOM macro. See the Remarks for
	 *            more information.
	 * 
	 * @return If the function succeeds, the return value is the newly created
	 *         atom. If the function fails, the return value is zero. To get
	 *         extended error information, call GetLastError.
	 */
	int AddAtom(String lpString);
	
	int AddSIDToBoundaryDescriptor ( HANDLEByReference BoundaryDescriptor, PSID RequiredSid );

	int AllocateUserPhysicalPages ( HANDLE hProcess, IntByReference NumberOfPages, int[] UserPfnArray );

	int AllocateUserPhysicalPagesNuma ( HANDLE hProcess, IntByReference NumberOfPages, int[] PageArray, DWORD nndPreferred );

	int AllocConsole ( );

	void ApplicationRecoveryFinished ( BOOL bSuccess );

	int ApplicationRecoveryInProgress ( IntByReference pbCanceled );

//	int AreFileApisANSI ( );

	int AssignProcessToJobObject ( HANDLE hJob, HANDLE hProcess );

	int AttachConsole ( DWORD dwProcessId );

//	int Beep ( DWORD dwFreq, DWORD dwDuration );

	int BeginUpdateResource ( String pFileName, BOOL bDeleteExistingResources );

	int BindIoCompletionCallback ( HANDLE FileHandle, WinNT.OVERLAPPED_COMPLETION_ROUTINE Function, ULONG Flags );

	int BuildCommDCB ( String lpDef, DCB lpDCB );

	int BuildCommDCBAndTimeouts ( String lpDef, DCB lpDCB, COMMTIMEOUTS lpCommTimeouts );

	int CancelIo ( HANDLE hFile );

	int CancelIoEx ( HANDLE hFile, OVERLAPPED lpOverlapped );

	int CancelSynchronousIo ( HANDLE hThread );

	int CancelWaitableTimer ( HANDLE hTimer );

	int ChangeTimerQueueTimer ( HANDLE TimerQueue, HANDLE Timer, ULONG DueTime, ULONG Period );

	int CheckNameLegalDOS8Dot3 ( String lpName, byte[] lpOemName, DWORD OemNameSize, IntByReference pbNameContainsSpaces, IntByReference pbNameLegal );

	int CheckRemoteDebuggerPresent ( HANDLE hProcess, IntByReference pbDebuggerPresent );

	int ClearCommBreak ( HANDLE hFile );

	int ClearCommError ( HANDLE hFile, IntByReference lpErrors, COMSTAT lpStat );

	int CloseHandle ( HANDLE hObject );

	int ClosePrivateNamespace ( HANDLE Handle, ULONG Flags );

	int CommConfigDialog ( String lpszName, HWND hWnd, COMMCONFIG lpCC );

//	int CompareFileTime ( FILETIME lpFileTime1, FILETIME lpFileTime2 );

	int CompareString ( LCID Locale, DWORD dwCmpFlags, String lpString1, int cchCount1, String lpString2, int cchCount2 );

	int ConnectNamedPipe ( HANDLE hNamedPipe, OVERLAPPED lpOverlapped );

	int ContinueDebugEvent ( DWORD dwProcessId, DWORD dwThreadId, DWORD dwContinueStatus );

	int ConvertDefaultLocale ( LCID Locale );

	int ConvertFiberToThread ( );

	int CopyFile ( String lpExistingFileName, String lpNewFileName, BOOL bFailIfExists );

	int CreateBoundaryDescriptor ( String Name, ULONG Flags );

//	int CreateDirectory ( String lpPathName, SECURITY_ATTRIBUTES lpSecurityAttributes );

	int CreateDirectoryEx ( String lpTemplateDirectory, String lpNewDirectory, SECURITY_ATTRIBUTES lpSecurityAttributes );

//	int CreateEvent ( SECURITY_ATTRIBUTES lpEventAttributes, BOOL bManualReset, BOOL bInitialState, String lpName );

	int CreateEventEx ( SECURITY_ATTRIBUTES lpEventAttributes, String lpName, DWORD dwFlags, DWORD dwDesiredAccess );

	int CreateFile ( String lpFileName, DWORD dwDesiredAccess, DWORD dwShareMode, SECURITY_ATTRIBUTES lpSecurityAttributes, DWORD dwCreationDisposition, DWORD dwFlagsAndAttributes, HANDLE hTemplateFile );

	int CreateFileMapping ( HANDLE hFile, SECURITY_ATTRIBUTES lpAttributes, DWORD flProtect, DWORD dwMaximumSizeHigh, DWORD dwMaximumSizeLow, String lpName );

	int CreateFileMappingNuma ( HANDLE hFile, SECURITY_ATTRIBUTES lpFileMappingAttributes, DWORD flProtect, DWORD dwMaximumSizeHigh, DWORD dwMaximumSizeLow, String lpName, DWORD nndPreferred );

	int CreateHardLink ( String lpFileName, String lpExistingFileName, SECURITY_ATTRIBUTES lpSecurityAttributes );

	int CreateIoCompletionPort ( HANDLE FileHandle, HANDLE ExistingCompletionPort, ULONG_PTR CompletionKey, DWORD NumberOfConcurrentThreads );

	int CreateJobObject ( SECURITY_ATTRIBUTES lpJobAttributes, String lpName );

	int CreateMailslot ( String lpName, DWORD nMaxMessageSize, DWORD lReadTimeout, SECURITY_ATTRIBUTES lpSecurityAttributes );

//	int CreateMutex ( SECURITY_ATTRIBUTES lpMutexAttributes, BOOL bInitialOwner, String lpName );

	int CreateMutexEx ( SECURITY_ATTRIBUTES lpMutexAttributes, String lpName, DWORD dwFlags, DWORD dwDesiredAccess );

	int CreateNamedPipe ( String lpName, DWORD dwOpenMode, DWORD dwPipeMode, DWORD nMaxInstances, DWORD nOutBufferSize, DWORD nInBufferSize, DWORD nDefaultTimeOut, SECURITY_ATTRIBUTES lpSecurityAttributes );

	int CreatePipe ( HANDLEByReference hReadPipe, HANDLEByReference hWritePipe, SECURITY_ATTRIBUTES lpPipeAttributes, DWORD nSize );

	int CreateSemaphore ( SECURITY_ATTRIBUTES lpSemaphoreAttributes, LONG lInitialCount, LONG lMaximumCount, String lpName );

	int CreateSemaphoreEx ( SECURITY_ATTRIBUTES lpSemaphoreAttributes, LONG lInitialCount, LONG lMaximumCount, String lpName, DWORD dwFlags, DWORD dwDesiredAccess );

	int CreateSymbolicLink ( String lpSymlinkFileName, String lpTargetFileName, DWORD dwFlags );

	int CreateTapePartition ( HANDLE hDevice, DWORD dwPartitionMethod, DWORD dwCount, DWORD dwSize );

	int CreateTimerQueue ( );

//	int CreateToolhelp32Snapshot ( DWORD dwFlags, DWORD th32ProcessID );

	int CreateWaitableTimer ( SECURITY_ATTRIBUTES lpTimerAttributes, BOOL bManualReset, String lpTimerName );

	int CreateWaitableTimerEx ( SECURITY_ATTRIBUTES lpTimerAttributes, String lpTimerName, DWORD dwFlags, DWORD dwDesiredAccess );

	int DebugActiveProcess ( DWORD dwProcessId );

	int DebugActiveProcessStop ( DWORD dwProcessId );

	void DebugBreak ( );

	int DebugBreakProcess ( HANDLE Process );

	int DebugSetProcessKillOnExit ( BOOL KillOnExit );

	int DefineDosDevice ( DWORD dwFlags, String lpDeviceName, String lpTargetPath );

	int DeleteAtom ( ATOM nAtom );

	void DeleteBoundaryDescriptor ( HANDLE BoundaryDescriptor );

	int DeleteFile ( String lpFileName );

	int DeleteTimerQueue ( HANDLE TimerQueue );

	int DeleteTimerQueueEx ( HANDLE TimerQueue, HANDLE CompletionEvent );

	int DeleteTimerQueueTimer ( HANDLE TimerQueue, HANDLE Timer, HANDLE CompletionEvent );

	int DeleteVolumeMountPoint ( String lpszVolumeMountPoint );

//	int DisableThreadLibraryCalls ( HMODULE hModule );

	int DisconnectNamedPipe ( HANDLE hNamedPipe );

	int DnsHostnameToComputerName ( String Hostname, char[] ComputerName, IntByReference nSize );

	int DosDateTimeToFileTime ( WORD wFatDate, WORD wFatTime, FILETIME lpFileTime );

	int DuplicateHandle ( HANDLE hSourceProcessHandle, HANDLE hSourceHandle, HANDLE hTargetProcessHandle, HANDLE lpTargetHandle, DWORD dwDesiredAccess, BOOL bInheritHandle, DWORD dwOptions );

	int EmptyWorkingSet ( HANDLE hProcess );

	int EndUpdateResource ( HANDLE hUpdate, BOOL fDiscard );

	int EnumProcesses ( int[] pProcessIds, DWORD cb, IntByReference pBytesReturned );

	int EnumProcessModules ( HANDLE hProcess, HANDLEByReference lphModule, DWORD cb, IntByReference lpcbNeeded );

	int EnumProcessModulesEx ( HANDLE hProcess, HANDLEByReference lphModule, DWORD cb, IntByReference lpcbNeeded, DWORD dwFilterFlag );

	int EraseTape ( HANDLE hDevice, DWORD dwEraseType, BOOL bImmediate );

	int EscapeCommFunction ( HANDLE hFile, DWORD dwFunc );

	void ExitProcess ( UINT uExitCode );

//	void ExitThread ( DWORD dwExitCode );

	int ExpandEnvironmentStrings ( String lpSrc, char[] lpDst, DWORD nSize );

	void FatalAppExit ( UINT uAction, String lpMessageText );

	void FatalExit ( int ExitCode );

	int FileTimeToDosDateTime ( FILETIME lpFileTime, ShortByReference lpFatDate, ShortByReference lpFatTime );

	int FileTimeToLocalFileTime ( FILETIME lpFileTime, FILETIME lpLocalFileTime );

//	int FileTimeToSystemTime ( FILETIME lpFileTime, SYSTEMTIME lpSystemTime );

	int FillConsoleOutputAttribute ( HANDLE hConsoleOutput, WORD wAttribute, DWORD nLength, COORD dwWriteCoord, IntByReference lpNumberOfAttrsWritten );

	int FillConsoleOutputCharacter ( HANDLE hConsoleOutput, char cCharacter, DWORD nLength, COORD dwWriteCoord, int[] lpNumberOfCharsWritten );

//	int FindAtom ( String lpString );

//	int FindClose ( HANDLE hFindFile );

	int FindCloseChangeNotification ( HANDLE hChangeHandle );

	int FindFirstChangeNotification ( String lpPathName, BOOL bWatchSubtree, DWORD dwNotifyFilter );

//	int FindFirstFile ( WString wString, v2.org.analysis.apihandle.winapi.structures.WinBase.WIN32_FIND_DATA lpFindFileData );

	int FindFirstFileNameW ( CHARByReference lpFileName, DWORD dwFlags, IntByReference StringLength, char[] LinkName );

	int FindFirstVolume ( char[] lpszVolumeName, DWORD cchBufferLength );

	int FindFirstVolumeMountPoint ( String lpszRootPathName, char[] lpszVolumeMountPoint, DWORD cchBufferLength );

	int FindNextChangeNotification ( HANDLE hChangeHandle );

//	int FindNextFile ( HANDLE hFindFile, WIN32_FIND_DATA lpFindFileData );

	int FindNextFileNameW ( HANDLE hFindStream, IntByReference StringLength, char LinkName );

	int FindNextVolume ( HANDLE hFindVolume, String lpszVolumeName, DWORD cchBufferLength );

	int FindNextVolumeMountPoint ( HANDLE hFindVolumeMountPoint, char[] lpszVolumeMountPoint, DWORD cchBufferLength );

//	int FindResource ( HMODULE hModule, String lpName, String lpType );

	int FindResourceEx ( HMODULE hModule, String lpType, String lpName, WORD wLanguage );

	int FindVolumeClose ( HANDLE hFindVolume );

	int FindVolumeMountPointClose ( HANDLE hFindVolumeMountPoint );

	int FlsFree ( DWORD dwFlsIndex );

	int FlushConsoleInputBuffer ( HANDLE hConsoleInput );

	int FlushFileBuffers ( HANDLE hFile );

	int FlushInstructionCache ( HANDLE hProcess, Pointer lpBaseAddress, SIZE_T dwSize );

	int FlushViewOfFile ( Pointer lpBaseAddress, SIZE_T dwNumberOfBytesToFlush );

	int FoldString ( DWORD dwMapFlags, String lpSrcStr, int cchSrc, char[] lpDestStr, int cchDest );

	int FormatMessage ( DWORD dwFlags, Pointer lpSource, DWORD dwMessageId, DWORD dwLanguageId, char[] lpBuffer, DWORD nSize, Pointer Arguments );

	int FreeConsole ( );

//	int FreeEnvironmentStrings ( Pointer lpszEnvironmentBlock );

//	int FreeLibrary ( HMODULE hModule );

	void FreeLibraryAndExitThread ( HMODULE hModule, DWORD dwExitCode );

	int FreeUserPhysicalPages ( HANDLE hProcess, IntByReference NumberOfPages, int[] UserPfnArray );

	int GenerateConsoleCtrlEvent ( DWORD dwCtrlEvent, DWORD dwProcessGroupId );

//	int GetACP ( );

	int GetApplicationRestartSettings ( HANDLE hProcess, char[] pwzCommandline, IntByReference pcchSize, IntByReference pdwFlags );

	int GetAtomName ( ATOM nAtom, String lpBuffer, int nSize );

	int GetBinaryType ( String lpApplicationName, IntByReference lpBinaryType );

	int GetCalendarInfo ( LCID Locale, int Calendar, int CalType, char[] lpCalData, int cchData, IntByReference lpValue );

//	int GetCommandLine ( );

	int GetCommConfig ( HANDLE hCommDev, COMMCONFIG lpCC, IntByReference lpdwSize );

	int GetCommMask ( HANDLE hFile, IntByReference lpEvtMask );

	int GetCommModemStatus ( HANDLE hFile, IntByReference lpModemStat );

	int GetCommProperties ( HANDLE hFile, COMMPROP lpCommProp );

	int GetCommState ( HANDLE hFile, DCB lpDCB );

	int GetCommTimeouts ( HANDLE hFile, COMMTIMEOUTS lpCommTimeouts );

	int GetCompressedFileSize ( String lpFileName, IntByReference lpFileSizeHigh );

	int GetComputerName ( char[] lpBuffer, IntByReference lpnSize );

	int GetComputerNameEx ( COMPUTER_NAME_FORMAT NameType, char[] lpBuffer, IntByReference lpnSize );

//	int GetConsoleCP ( );

	int GetConsoleCursorInfo ( HANDLE hConsoleOutput, CONSOLE_CURSOR_INFO lpConsoleCursorInfo );

	int GetConsoleDisplayMode ( IntByReference lpModeFlags );

	int GetConsoleFontSize ( HANDLE hConsoleOutput, DWORD nFont );

	int GetConsoleHistoryInfo ( CONSOLE_HISTORY_INFO lpConsoleHistoryInfo );

	int GetConsoleMode ( HANDLE hConsoleHandle, IntByReference lpMode );

	int GetConsoleOriginalTitle ( char[] lpConsoleTitle, DWORD nSize );

	int GetConsoleOutputCP ( );

	int GetConsoleProcessList ( int[] lpdwProcessList, DWORD dwProcessCount );

	int GetConsoleScreenBufferInfo ( HANDLE hConsoleOutput, CONSOLE_SCREEN_BUFFER_INFO lpConsoleScreenBufferInfo );

	int GetConsoleScreenBufferInfoEx ( HANDLE hConsoleOutput, CONSOLE_SCREEN_BUFFER_INFOEX lpConsoleScreenBufferInfoEx );

	int GetConsoleSelectionInfo ( CONSOLE_SELECTION_INFO lpConsoleSelectionInfo );

	int GetConsoleTitle ( char[] lpConsoleTitle, DWORD nSize );

	int GetConsoleWindow ( );

//	int GetCPInfo1 ( UINT CodePage, CPINFO lpCPInfo );

//	int GetCPInfoEx1 ( UINT CodePage, DWORD dwFlags, CPINFOEX lpCPInfoEx );

	int GetCurrencyFormat ( LCID Locale, DWORD dwFlags, String lpValue, CURRENCYFMT lpFormat, char[] lpCurrencyStr, int cchCurrency );

	int GetCurrentConsoleFont ( HANDLE hConsoleOutput, BOOL bMaximumWindow, CONSOLE_FONT_INFO lpConsoleCurrentFont );

	int GetCurrentConsoleFontEx ( HANDLE hConsoleOutput, BOOL bMaximumWindow, CONSOLE_FONT_INFOEX lpConsoleCurrentFontEx );

//	int GetCurrentDirectory ( DWORD nBufferLength, char[] lpBuffer );

	int GetCurrentProcess ( );

	int GetCurrentProcessId ( );

	int GetCurrentProcessorNumber ( );

	int GetCurrentThread ( );

	int GetCurrentThreadId ( );

	int GetDateFormat ( LCID Locale, DWORD dwFlags, SYSTEMTIME lpDate, String lpFormat, char[] lpDateStr, int cchDate );

	int GetDefaultCommConfig ( String lpszName, COMMCONFIG lpCC, IntByReference lpdwSize );

	int GetDevicePowerState ( HANDLE hDevice, IntByReference pfOn );

	int GetDiskFreeSpace ( String lpRootPathName, IntByReference lpSectorsPerCluster, IntByReference lpBytesPerSector, IntByReference lpNumberOfFreeClusters, IntByReference lpTotalNumberOfClusters );
	
//	int GetDiskFreeSpaceEx ( String lpRootPathName, IntByReference lpSectorsPerCluster, IntByReference lpBytesPerSector, IntByReference lpNumberOfFreeClusters, IntByReference lpTotalNumberOfClusters );

	int GetDiskFreeSpaceEx ( String lpDirectoryName, ULARGE_INTEGER lpFreeBytesAvailable, ULARGE_INTEGER lpTotalNumberOfBytes, ULARGE_INTEGER lpTotalNumberOfFreeBytes );

	int GetDllDirectory ( DWORD nBufferLength, char[] lpBuffer );

	int GetDriveType ( String lpRootPathName );

	int GetDynamicTimeZoneInformation ( DYNAMIC_TIME_ZONE_INFORMATION pTimeZoneInformation );

//	int GetEnvironmentStrings ( );

	int GetEnvironmentVariable ( String lpName, char[] lpBuffer, DWORD nSize );

	int GetErrorMode ( );

	int GetExitCodeProcess ( HANDLE hProcess, IntByReference lpExitCode );

	int GetExitCodeThread ( HANDLE hThread, IntByReference lpExitCode );

	int GetFileAttributes ( String lpFileName );

	int GetFileBandwidthReservation ( HANDLE hFile, IntByReference lpPeriodMilliseconds, IntByReference lpBytesPerPeriod, IntByReference pDiscardable, IntByReference lpTransferSize, IntByReference lpNumOutstandingRequests );

	int GetFileInformationByHandle ( HANDLE hFile, BY_HANDLE_FILE_INFORMATION lpFileInformation );

	int GetFileSize ( HANDLE hFile, IntByReference lpFileSizeHigh );

	int GetFileSizeEx ( HANDLE hFile, LARGE_INTEGER lpFileSize );

	int GetFileTime ( HANDLE hFile, FILETIME lpCreationTime, FILETIME lpLastAccessTime, FILETIME lpLastWriteTime );

	int GetFileType ( HANDLE hFile );

	int GetFinalPathNameByHandle ( HANDLE hFile, char[] lpszFilePath, DWORD cchFilePath, DWORD dwFlags );

	int GetFullPathName ( String lpFileName, DWORD nBufferLength, char[] lpBuffer, PointerByReference lpFilePart );

	int GetGeoInfo ( int Location, int GeoType, char[] lpGeoData, int cchData, short LangId );

	int GetLargePageMinimum ( );

//	int GetLargestConsoleWindowSize ( HANDLE hConsoleOutput );

	int GetLastError ( );

	int GetLocaleInfo ( LCID Locale, int LCType, char[] lpLCData, int cchData );

	int GetLocaleInfoEx ( CHARByReference lpLocaleName, int LCType, char[] lpLCData, int cchData );

	void GetLocalTime ( SYSTEMTIME lpSystemTime );

//	int GetLogicalDrives ( );

	int GetLogicalDriveStrings ( DWORD nBufferLength, char[] lpBuffer );

	int GetLogicalProcessorInformation ( SYSTEM_LOGICAL_PROCESSOR_INFORMATION Buffer, IntByReference ReturnLength );

	int GetLongPathName ( String lpszShortPath, char[] lpszLongPath, DWORD cchBuffer );

	int GetMailslotInfo ( HANDLE hMailslot, IntByReference lpMaxMessageSize, IntByReference lpNextSize, IntByReference lpMessageCount, IntByReference lpReadTimeout );

	int GetModuleBaseName ( HANDLE hProcess, HMODULE hModule, char[] lpBaseName, int nSize );

//	int GetModuleFileName ( HMODULE hModule, char[] lpFilename, DWORD nSize );

	int GetModuleFileNameEx ( HANDLE hProcess, HMODULE hModule, char[] lpFilename, DWORD nSize );

	int GetModuleHandle ( String lpModuleName );

	int GetModuleHandleEx ( DWORD dwFlags, String lpModuleName, HANDLEByReference phModule );

	int GetModuleInformation ( HANDLE hProcess, HMODULE hModule, MODULEINFO lpmodinfo, DWORD cb );

	int GetNamedPipeClientComputerName ( HANDLE Pipe, String ClientComputerName, ULONG ClientComputerNameLength );

	int GetNamedPipeClientProcessId ( HANDLE Pipe, IntByReference ClientProcessId );

	int GetNamedPipeClientSessionId ( HANDLE Pipe, IntByReference ClientSessionId );

	int GetNamedPipeHandleState ( HANDLE hNamedPipe, IntByReference lpState, IntByReference lpCurInstances, IntByReference lpMaxCollectionCount, IntByReference lpCollectDataTimeout, char[] lpUserName, DWORD nMaxUserNameSize );

	int GetNamedPipeInfo ( HANDLE hNamedPipe, IntByReference lpFlags, int[] lpOutBufferSize, int[] lpInBufferSize, IntByReference lpMaxInstances );

	int GetNamedPipeServerProcessId ( HANDLE Pipe, IntByReference ServerProcessId );

	int GetNamedPipeServerSessionId ( HANDLE Pipe, IntByReference ServerSessionId );

	void GetNativeSystemInfo ( SYSTEM_INFO lpSystemInfo );

	int GetNumaAvailableMemoryNode ( UCHAR Node, DoubleByReference AvailableBytes );

	int GetNumaHighestNodeNumber ( IntByReference HighestNodeNumber );

	int GetNumaNodeProcessorMask ( UCHAR Node, DoubleByReference ProcessorMask );

	int GetNumaProcessorNode ( UCHAR Processor, byte NodeNumber );

	int GetNumaProximityNode ( ULONG ProximityId, byte NodeNumber );

	int GetNumberFormat ( LCID Locale, DWORD dwFlags, String lpValue, NUMBERFMT lpFormat, char[] lpNumberStr, int cchNumber );

	int GetNumberFormatEx ( CHARByReference lpLocaleName, DWORD dwFlags, CHARByReference lpValue, NUMBERFMT lpFormat, char[] lpNumberStr, int cchNumber );

	int GetNumberOfConsoleInputEvents ( HANDLE hConsoleInput, IntByReference lpcNumberOfEvents );

	int GetNumberOfConsoleMouseButtons ( IntByReference lpNumberOfMouseButtons );

//	int GetOEMCP ( );

	int GetOverlappedResult ( HANDLE hFile, OVERLAPPED lpOverlapped, IntByReference lpNumberOfBytesTransferred, BOOL bWait );

	int GetPerformanceInfo ( PERFORMANCE_INFORMATION pPerformanceInformation, DWORD cb );

//	int GetPriorityClass ( HANDLE hProcess );

	int GetPrivateProfileInt ( String lpAppName, String lpKeyName, int nDefault, String lpFileName );

	int GetPrivateProfileSection ( String lpAppName, char[] lpReturnedString, DWORD nSize, String lpFileName );

	int GetPrivateProfileSectionNames ( char[] lpszReturnBuffer, DWORD nSize, String lpFileName );

	int GetPrivateProfileString ( String lpAppName, String lpKeyName, String lpDefault, char[] lpReturnedString, DWORD nSize, String lpFileName );

	int GetProcessAffinityMask ( HANDLE hProcess, IntByReference lpProcessAffinityMask, IntByReference lpSystemAffinityMask );

	int GetProcessHandleCount ( HANDLE hProcess, IntByReference pdwHandleCount );

//	int GetProcessHeap ( );

	int GetProcessHeaps ( DWORD NumberOfHeaps, HANDLEByReference ProcessHeaps );

	int GetProcessId ( HANDLE Process );

	int GetProcessIdOfThread ( HANDLE Thread );

	int GetProcessIoCounters ( HANDLE hProcess, IO_COUNTERS lpIoCounters );

	int GetProcessMemoryInfo ( HANDLE Process, PROCESS_MEMORY_COUNTERS ppsmemCounters, DWORD cb );

	int GetProcessPriorityBoost ( HANDLE hProcess, IntByReference pDisablePriorityBoost );

	int GetProcessShutdownParameters ( IntByReference lpdwLevel, IntByReference lpdwFlags );

	int GetProcessTimes ( HANDLE hProcess, FILETIME lpCreationTime, FILETIME lpExitTime, FILETIME lpKernelTime, FILETIME lpUserTime );

	int GetProcessVersion ( DWORD ProcessId );

	int GetProcessWorkingSetSize ( HANDLE hProcess, IntByReference lpMinimumWorkingSetSize, IntByReference lpMaximumWorkingSetSize );

	int GetProcessWorkingSetSizeEx ( HANDLE hProcess, IntByReference lpMinimumWorkingSetSize, IntByReference lpMaximumWorkingSetSize, IntByReference Flags );

	int GetProductInfo ( DWORD dwOSMajorVersion, DWORD dwOSMinorVersion, DWORD dwSpMajorVersion, DWORD dwSpMinorVersion, IntByReference pdwReturnedProductType );

	int GetProfileInt ( String lpAppName, String lpKeyName, int nDefault );

	int GetProfileSection ( String lpAppName, char[] lpReturnedString, DWORD nSize );

	int GetProfileString ( String lpAppName, String lpKeyName, String lpDefault, char[] lpReturnedString, DWORD nSize );

	int GetQueuedCompletionStatus ( HANDLE CompletionPort, IntByReference lpNumberOfBytes, IntByReference lpCompletionKey, PointerByReference lpOverlapped, DWORD dwMilliseconds );

	int GetQueuedCompletionStatusEx ( HANDLE CompletionPort, OVERLAPPED_ENTRY lpCompletionPortEntries, ULONG ulCount, IntByReference ulNumEntriesRemoved, DWORD dwMilliseconds, BOOL fAlertable );

	int GetShortPathName ( String lpszLongPath, char[] lpszShortPath, DWORD cchBuffer );

//	void GetStartupInfo ( STARTUPINFO lpStartupInfo );

//	int GetStdHandle ( DWORD nStdHandle );

	int GetStringTypeA ( LCID Locale, DWORD dwInfoType, ByteByReference lpSrcStr, int cchSrc, ShortByReference lpCharType );

	int GetStringTypeEx ( LCID Locale, DWORD dwInfoType, String lpSrcStr, int cchSrc, ShortByReference lpCharType );

	int GetStringTypeW ( DWORD dwInfoType, CHARByReference lpSrcStr, int cchSrc, ShortByReference lpCharType );

	int GetSystemDefaultLangID ( );

	int GetSystemDefaultLCID ( );

//	int GetSystemDefaultUILanguage ( );

//	int GetSystemDirectory ( char[] lpBuffer, UINT uSize );

	int GetSystemFileCacheSize ( IntByReference lpMinimumFileCacheSize, IntByReference lpMaximumFileCacheSize, IntByReference lpFlags );

	void GetSystemInfo ( SYSTEM_INFO lpSystemInfo );

	int GetSystemPowerStatus ( SYSTEM_POWER_STATUS lpSystemPowerStatus );

	int GetSystemRegistryQuota ( IntByReference pdwQuotaAllowed, IntByReference pdwQuotaUsed );

	void GetSystemTime ( SYSTEMTIME lpSystemTime );

	int GetSystemTimeAdjustment ( IntByReference lpTimeAdjustment, IntByReference lpTimeIncrement, IntByReference lpTimeAdjustmentDisabled );

//	void GetSystemTimeAsFileTime ( FILETIME lpSystemTimeAsFileTime );

	int GetSystemTimes ( FILETIME lpIdleTime, FILETIME lpKernelTime, FILETIME lpUserTime );

	int GetSystemWindowsDirectory ( char[] lpBuffer, UINT uSize );

	int GetSystemWow64Directory ( char[] lpBuffer, UINT uSize );

	int GetTapePosition ( HANDLE hDevice, DWORD dwPositionType, IntByReference lpdwPartition, IntByReference lpdwOffsetLow, IntByReference lpdwOffsetHigh );

	int GetTapeStatus ( HANDLE hDevice );

	int GetTempFileName ( String lpPathName, String lpPrefixString, UINT uUnique, String lpTempFileName );

	int GetTempPath ( DWORD nBufferLength, char[] lpBuffer );

	int GetThreadId ( HANDLE Thread );

	int GetThreadIOPendingFlag ( HANDLE hThread, IntByReference lpIOIsPending );

//	int GetThreadLocale ( );

	int GetThreadPreferredUILanguages ( DWORD dwFlags, IntByReference pulNumLanguages, CHARByReference pwszLanguagesBuffer, IntByReference pcchLanguagesBuffer );

//	int GetThreadPriority ( HANDLE hThread );

	int GetThreadPriorityBoost ( HANDLE hThread, IntByReference pDisablePriorityBoost );

	int GetThreadSelectorEntry ( HANDLE hThread, DWORD dwSelector, LDT_ENTRY lpSelectorEntry );

	int GetThreadTimes ( HANDLE hThread, FILETIME lpCreationTime, FILETIME lpExitTime, FILETIME lpKernelTime, FILETIME lpUserTime );

	int GetThreadUILanguage ( );

	int GetTickCount ( );

	int GetTickCount64 ( );

	int GetTimeFormat ( LCID Locale, DWORD dwFlags, SYSTEMTIME lpTime, String lpFormat, char[] lpTimeStr, int cchTime );

	int GetTimeFormatEx ( CHARByReference lpLocaleName, DWORD dwFlags, SYSTEMTIME lpTime, CHARByReference lpFormat, char[] lpTimeStr, int cchTime );

	int GetTimeZoneInformation ( TIME_ZONE_INFORMATION lpTimeZoneInformation );

//	int GetUserDefaultLangID ( );

//	int GetUserDefaultLCID ( );

	int GetUserDefaultUILanguage ( );

	int GetUserGeoID ( int GeoClass );

	int GetVersion ( );

	int GetVersionEx ( WinNT.OSVERSIONINFOEX lpVersionInfo );

	int GetVolumeInformation ( String lpRootPathName, char[] lpVolumeNameBuffer, DWORD nVolumeNameSize, IntByReference lpVolumeSerialNumber, IntByReference lpMaximumComponentLength, IntByReference lpFileSystemFlags, char[] lpFileSystemNameBuffer, DWORD nFileSystemNameSize );

	int GetVolumeNameForVolumeMountPoint ( String lpszVolumeMountPoint, String lpszVolumeName, DWORD cchBufferLength );

	int GetVolumePathName ( String lpszFileName, String lpszVolumePathName, DWORD cchBufferLength );

	int GetVolumePathNamesForVolumeName ( String lpszVolumeName, char[] lpszVolumePathNames, DWORD cchBufferLength, IntByReference lpcchReturnLength );

//	int GetWindowsDirectory ( char[] lpBuffer, UINT uSize );

	int GetWsChanges ( HANDLE hProcess, PSAPI_WS_WATCH_INFORMATION lpWatchInfo, DWORD cb );

	int GlobalAddAtom ( String lpString );

//	int GlobalAlloc ( UINT uFlags, SIZE_T dwBytes );

	int GlobalDeleteAtom ( ATOM nAtom );

//	int GlobalFindAtom ( String lpString );

	int GlobalFlags ( HANDLE hMem );

//	int GlobalFree ( HANDLE hMem );

	int GlobalGetAtomName ( ATOM nAtom, String lpBuffer, int nSize );

	int GlobalHandle ( Pointer pMem );

//	void GlobalMemoryStatus ( MEMORYSTATUS lpBuffer );

	int GlobalMemoryStatusEx ( MEMORYSTATUSEX lpBuffer );

	int GlobalReAlloc ( HANDLE hMem, SIZE_T dwBytes, UINT uFlags );

	int GlobalSize ( HANDLE hMem );

	int GlobalUnlock ( HANDLE hMem );

	int Heap32First ( HEAPENTRY32 lphe, DWORD th32ProcessID, ULONG_PTR th32HeapID );

	int Heap32ListFirst ( HANDLE hSnapshot, HEAPLIST32 lphl );

	int Heap32ListNext ( HANDLE hSnapshot, HEAPLIST32 lphl );

	int Heap32Next ( HEAPENTRY32 lphe );

	int HeapCompact ( HANDLE hHeap, DWORD dwFlags );

//	int HeapCreate ( DWORD flOptions, SIZE_T dwInitialSize, SIZE_T dwMaximumSize );

//	int HeapDestroy ( HANDLE hHeap );

	int HeapLock ( HANDLE hHeap );

//	int HeapSize ( HANDLE hHeap, DWORD dwFlags, Pointer lpMem );

	int HeapUnlock ( HANDLE hHeap );

	int HeapValidate ( HANDLE hHeap, DWORD dwFlags, Pointer lpMem );

	int HeapWalk ( HANDLE hHeap, PROCESS_HEAP_ENTRY lpEntry );

	int InitAtomTable ( DWORD nSize );

	int InitializeProcessForWsWatch ( HANDLE hProcess );

	int InterlockedCompareExchange ( IntByReference Destination, LONG Exchange, LONG Comparand );

	int InterlockedDecrement ( IntByReference Addend );

	int InterlockedExchange ( IntByReference Target, LONG Value );

	int InterlockedExchangeAdd ( IntByReference Addend, LONG Value );

	int InterlockedIncrement ( IntByReference Addend );

	int IsBadStringPtr ( String lpsz, UINT_PTR ucchMax );

//	int IsDBCSLeadByte ( BYTE TestChar );

//	int IsDebuggerPresent ( );

	int IsProcessInJob ( HANDLE ProcessHandle, HANDLE JobHandle, IntByReference Result );

	int IsProcessorFeaturePresent ( DWORD ProcessorFeature );

	int IsSystemResumeAutomatic ( );

	int IsThreadAFiber ( );

//	int IsValidCodePage ( UINT CodePage );

	int IsValidLanguageGroup ( int LanguageGroup, DWORD dwFlags );

	int IsValidLocale ( LCID Locale, DWORD dwFlags );

	int IsValidLocaleName ( CHARByReference lpLocaleName );

	int IsWow64Process ( HANDLE hProcess, IntByReference Wow64Process );

	int LCMapString ( LCID Locale, DWORD dwMapFlags, String lpSrcStr, int cchSrc, char[] lpDestStr, int cchDest );

//	int LoadLibrary ( String lpFileName );
//
//	int LoadLibraryEx ( String lpFileName, HANDLE hFile, DWORD dwFlags );
//
//	int LoadResource ( HMODULE hModule, HRSRC hResInfo );
//
//	int LocalAlloc ( UINT uFlags, SIZE_T uBytes );

	int LocalFileTimeToFileTime ( FILETIME lpLocalFileTime, FILETIME lpFileTime );

	int LocalFlags ( HANDLE hMem );

//	int LocalFree ( HANDLE hMem );

	int LocalHandle ( Pointer pMem );

//	int LocalReAlloc ( HANDLE hMem, SIZE_T uBytes, UINT uFlags );

	int LocalSize ( HANDLE hMem );

	int LocalUnlock ( HANDLE hMem );

	int LockFile ( HANDLE hFile, DWORD dwFileOffsetLow, DWORD dwFileOffsetHigh, DWORD nNumberOfBytesToLockLow, DWORD nNumberOfBytesToLockHigh );

	int LockFileEx ( HANDLE hFile, DWORD dwFlags, DWORD dwReserved, DWORD nNumberOfBytesToLockLow, DWORD nNumberOfBytesToLockHigh, OVERLAPPED lpOverlapped );

//	int lstrcat ( String lpString1, String lpString2 );
//
//	int lstrcmp ( String lpString1, String lpString2 );

	int lstrcmpi ( String lpString1, String lpString2 );

	int lstrcpy ( String lpString1, String lpString2 );

	int lstrcpyn ( String lpString1, String lpString2, int iMaxLength );

//	int lstrlen ( String lpString );

	int MapUserPhysicalPagesScatter ( PointerByReference VirtualAddresses, ULONG_PTR NumberOfPages, IntByReference PageArray );

	int Module32First ( HANDLE hSnapshot, MODULEENTRY32 lpme );

	int Module32Next ( HANDLE hSnapshot, MODULEENTRY32 lpme );

	int MoveFile ( String lpExistingFileName, String lpNewFileName );

//	int MoveFileEx ( String lpExistingFileName, String lpNewFileName, DWORD dwFlags );
//
//	int MulDiv ( int nNumber, int nNumerator, int nDenominator );

	int MultiByteToWideChar ( UINT CodePage, DWORD dwFlags, ByteByReference lpMultiByteStr, int cbMultiByte, char[] lpWideCharStr, int cchWideChar );

	int OpenEvent ( DWORD dwDesiredAccess, BOOL bInheritHandle, String lpName );

//	int OpenFile ( String lpFileName, v2.org.analysis.apihandle.winapi.structures.WinBase.OFSTRUCT lpReOpenBuff, UINT uStyle );

//	int OpenFileMapping ( DWORD dwDesiredAccess, BOOL bInheritHandle, String lpName );

	int OpenJobObject ( DWORD dwDesiredAccess, BOOL bInheritHandles, String lpName );

	int OpenMutex ( DWORD dwDesiredAccess, BOOL bInheritHandle, String lpName );

//	int OpenProcess ( DWORD dwDesiredAccess, BOOL bInheritHandle, DWORD dwProcessId );

	int OpenSemaphore ( DWORD dwDesiredAccess, BOOL bInheritHandle, String lpName );

//	int OpenThread ( DWORD dwDesiredAccess, BOOL bInheritHandle, DWORD dwThreadId );

	int OpenWaitableTimer ( DWORD dwDesiredAccess, BOOL bInheritHandle, String lpTimerName );

	void OutputDebugString ( String lpOutputString );

	int PeekConsoleInput ( HANDLE hConsoleInput, INPUT_RECORD lpBuffer, DWORD nLength, IntByReference lpNumberOfEventsRead );

	int PostQueuedCompletionStatus ( HANDLE CompletionPort, DWORD dwNumberOfBytesTransferred, ULONG_PTR dwCompletionKey, OVERLAPPED lpOverlapped );

	int PrepareTape ( HANDLE hDevice, DWORD dwOperation, BOOL bImmediate );

//	int Process32First ( HANDLE hSnapshot, PROCESSENTRY32 lppe );

//	int Process32Next ( HANDLE hSnapshot, PROCESSENTRY32 lppe );

	int PulseEvent ( HANDLE hEvent );

	int PurgeComm ( HANDLE hFile, DWORD dwFlags );

	int QueryDosDevice ( String lpDeviceName, char[] lpTargetPath, DWORD ucchMax );

	int QueryFullProcessImageName ( HANDLE hProcess, DWORD dwFlags, String lpExeName, IntByReference lpdwSize );

	int QueryIdleProcessorCycleTime ( IntByReference BufferLength, LongByReference ProcessorIdleCycleTime );

	int QueryPerformanceCounter ( LARGE_INTEGER lpPerformanceCount );

//	int QueryPerformanceFrequency ( LARGE_INTEGER lpFrequency );

	int QueryProcessCycleTime ( HANDLE ProcessHandle, LongByReference CycleTime );

	int QueryThreadCycleTime ( HANDLE ThreadHandle, LongByReference CycleTime );

	void RaiseException ( DWORD dwExceptionCode, DWORD dwExceptionFlags, DWORD nNumberOfArguments, IntByReference lpArguments );

	int ReadConsoleInput ( HANDLE hConsoleInput, INPUT_RECORD lpBuffer, DWORD nLength, IntByReference lpNumberOfEventsRead );

	int ReadConsoleOutput ( HANDLE hConsoleOutput, CHAR_INFO lpBuffer, COORD dwBufferSize, COORD dwBufferCoord, SMALL_RECT lpReadRegion );

	int ReadConsoleOutputAttribute ( HANDLE hConsoleOutput, short[] lpAttribute, DWORD nLength, COORD dwReadCoord, IntByReference lpNumberOfAttrsRead );

	int ReadConsoleOutputCharacter ( HANDLE hConsoleOutput, char[] lpCharacter, DWORD nLength, COORD dwReadCoord, IntByReference lpNumberOfCharsRead );

	int ReadFileScatter ( HANDLE hFile, FILE_SEGMENT_ELEMENT aSegmentArray[], DWORD nNumberOfBytesToRead, IntByReference lpReserved, OVERLAPPED lpOverlapped );

	int RegisterApplicationRestart ( CHARByReference pwzCommandline, DWORD dwFlags );

//	int ReleaseMutex ( HANDLE hMutex );

	int ReleaseSemaphore ( HANDLE hSemaphore, LONG lReleaseCount, IntByReference lpPreviousCount );

//	int RemoveDirectory ( String lpPathName );

	int ReOpenFile ( HANDLE hOriginalFile, DWORD dwDesiredAccess, DWORD dwShareMode, DWORD dwFlags );

	int RequestWakeupLatency ( int latency );

	int ResetEvent ( HANDLE hEvent );

//	int ResumeThread ( HANDLE hThread );

	int ScrollConsoleScreenBuffer ( HANDLE hConsoleOutput, SMALL_RECT lpScrollRectangle, SMALL_RECT lpClipRectangle, COORD dwDestinationOrigin, CHAR_INFO lpFill );

	int SearchPath ( String lpPath, String lpFileName, String lpExtension, DWORD nBufferLength, char[] lpBuffer, PointerByReference lpFilePart );

	int SetCalendarInfo ( LCID Locale, int Calendar, int CalType, String lpCalData );

	int SetCommBreak ( HANDLE hFile );

	int SetCommConfig ( HANDLE hCommDev, COMMCONFIG lpCC, DWORD dwSize );

	int SetCommMask ( HANDLE hFile, DWORD dwEvtMask );

	int SetCommState ( HANDLE hFile, DCB lpDCB );

	int SetCommTimeouts ( HANDLE hFile, COMMTIMEOUTS lpCommTimeouts );

	int SetComputerName ( String lpComputerName );

	int SetComputerNameEx ( COMPUTER_NAME_FORMAT NameType, String lpBuffer );

	int SetConsoleActiveScreenBuffer ( HANDLE hConsoleOutput );

	int SetConsoleCP ( UINT wCodePageID );

	int SetConsoleCursorInfo ( HANDLE hConsoleOutput, CONSOLE_CURSOR_INFO lpConsoleCursorInfo );

	int SetConsoleCursorPosition ( HANDLE hConsoleOutput, COORD dwCursorPosition );

	int SetConsoleHistoryInfo ( CONSOLE_HISTORY_INFO lpConsoleHistoryInfo );

	int SetConsoleMode ( HANDLE hConsoleHandle, DWORD dwMode );

	int SetConsoleOutputCP ( UINT wCodePageID );

	int SetConsoleScreenBufferInfoEx ( HANDLE hConsoleOutput, CONSOLE_SCREEN_BUFFER_INFOEX lpConsoleScreenBufferInfoEx );

	int SetConsoleScreenBufferSize ( HANDLE hConsoleOutput, COORD dwSize );

	int SetConsoleTextAttribute ( HANDLE hConsoleOutput, WORD wAttributes );

	int SetConsoleTitle ( String lpConsoleTitle );

	int SetConsoleWindowInfo ( HANDLE hConsoleOutput, BOOL bAbsolute, SMALL_RECT lpConsoleWindow );

	int SetCurrentConsoleFontEx ( HANDLE hConsoleOutput, BOOL bMaximumWindow, CONSOLE_FONT_INFOEX lpConsoleCurrentFontEx );

	int SetCurrentDirectory ( String lpPathName );

	int SetDefaultCommConfig ( String lpszName, COMMCONFIG lpCC, DWORD dwSize );

	int SetDllDirectory ( String lpPathName );

	int SetDynamicTimeZoneInformation ( DYNAMIC_TIME_ZONE_INFORMATION lpTimeZoneInformation );

//	int SetEndOfFile ( HANDLE hFile );

	int SetEnvironmentVariable ( String lpName, String lpValue );

//	int SetErrorMode ( UINT uMode );

//	int SetEvent ( HANDLE hEvent );

	void SetFileApisToANSI ( );

	void SetFileApisToOEM ( );

	int SetFileAttributes ( String lpFileName, DWORD dwFileAttributes );

	int SetFileBandwidthReservation ( HANDLE hFile, DWORD nPeriodMilliseconds, DWORD nBytesPerPeriod, BOOL bDiscardable, IntByReference lpTransferSize, IntByReference lpNumOutstandingRequests );

	int SetFileCompletionNotificationModes ( HANDLE FileHandle, UCHAR Flags );

	int SetFileIoOverlappedRange ( HANDLE FileHandle, byte OverlappedRangeStart, ULONG Length );

	int SetFilePointer ( HANDLE hFile, LONG lDistanceToMove, IntByReference lpDistanceToMoveHigh, DWORD dwMoveMethod );

	int SetFilePointerEx ( HANDLE hFile, LARGE_INTEGER liDistanceToMove, LARGE_INTEGER lpNewFilePointer, DWORD dwMoveMethod );

	int SetFileShortName ( HANDLE hFile, String lpShortName );

//	int SetFileTime ( HANDLE hFile, FILETIME lpCreationTime, FILETIME lpLastAccessTime, FILETIME lpLastWriteTime );

	int SetFileValidData ( HANDLE hFile, LONGLONG ValidDataLength );

	int SetHandleInformation ( HANDLE hObject, DWORD dwMask, DWORD dwFlags );

	void SetLastError ( DWORD dwErrCode );

	int SetLocaleInfo ( LCID Locale, int LCType, String lpLCData );

	int SetLocalTime ( SYSTEMTIME lpSystemTime );

	int SetMailslotInfo ( HANDLE hMailslot, DWORD lReadTimeout );

	int SetNamedPipeHandleState ( HANDLE hNamedPipe, IntByReference lpMode, IntByReference lpMaxCollectionCount, IntByReference lpCollectDataTimeout );

//	int SetPriorityClass ( HANDLE hProcess, DWORD dwPriorityClass );

	int SetProcessAffinityMask ( HANDLE hProcess, DWORD_PTR dwProcessAffinityMask );

	int SetProcessPriorityBoost ( HANDLE hProcess, BOOL DisablePriorityBoost );

	int SetProcessShutdownParameters ( DWORD dwLevel, DWORD dwFlags );

	int SetProcessWorkingSetSize ( HANDLE hProcess, SIZE_T dwMinimumWorkingSetSize, SIZE_T dwMaximumWorkingSetSize );

	int SetProcessWorkingSetSizeEx ( HANDLE hProcess, SIZE_T dwMinimumWorkingSetSize, SIZE_T dwMaximumWorkingSetSize, DWORD Flags );

	int SetStdHandle ( DWORD nStdHandle, HANDLE hHandle );

	int SetSystemFileCacheSize ( SIZE_T MinimumFileCacheSize, SIZE_T MaximumFileCacheSize, DWORD Flags );

	int SetSystemPowerState ( BOOL fSuspend, BOOL fForce );

	int SetSystemTime ( SYSTEMTIME lpSystemTime );

	int SetSystemTimeAdjustment ( DWORD dwTimeAdjustment, BOOL bTimeAdjustmentDisabled );

	int SetTapePosition ( HANDLE hDevice, DWORD dwPositionMethod, DWORD dwPartition, DWORD dwOffsetLow, DWORD dwOffsetHigh, BOOL bImmediate );

	int SetThreadAffinityMask ( HANDLE hThread, DWORD_PTR dwThreadAffinityMask );

	int SetThreadExecutionState ( int esFlags );

	int SetThreadIdealProcessor ( HANDLE hThread, DWORD dwIdealProcessor );

//	int SetThreadLocale ( LCID Locale );

//	int SetThreadPriority ( HANDLE hThread, int nPriority );

	int SetThreadPriorityBoost ( HANDLE hThread, BOOL DisablePriorityBoost );

	int SetThreadStackGuarantee ( IntByReference StackSizeInBytes );

	int SetTimeZoneInformation ( TIME_ZONE_INFORMATION lpTimeZoneInformation );

	int SetupComm ( HANDLE hFile, DWORD dwInQueue, DWORD dwOutQueue );

	int SetUserGeoID ( int GeoId );

	int SetVolumeLabel ( String lpRootPathName, String lpVolumeName );

	int SetVolumeMountPoint ( String lpszVolumeMountPoint, String lpszVolumeName );

	int SignalObjectAndWait ( HANDLE hObjectToSignal, HANDLE hObjectToWaitOn, DWORD dwMilliseconds, BOOL bAlertable );

//	int SizeofResource ( HMODULE hModule, HRSRC hResInfo );
//
//	void Sleep ( DWORD dwMilliseconds );

	int SleepEx ( DWORD dwMilliseconds, BOOL bAlertable );

//	int SuspendThread ( HANDLE hThread );

	int SwitchToThread ( );

//	int SystemTimeToFileTime ( SYSTEMTIME lpSystemTime, FILETIME lpFileTime );

	int SystemTimeToTzSpecificLocalTime ( TIME_ZONE_INFORMATION lpTimeZone, SYSTEMTIME lpUniversalTime, SYSTEMTIME lpLocalTime );

	int TerminateJobObject ( HANDLE hJob, UINT uExitCode );

//	int TerminateProcess ( HANDLE hProcess, UINT uExitCode );

//	int TerminateThread ( HANDLE hThread, DWORD dwExitCode );

//	int Thread32First ( HANDLE hSnapshot, THREADENTRY32 lpte );

//	int Thread32Next ( HANDLE hSnapshot, THREADENTRY32 lpte );

//	int TlsAlloc ( );

//	int TlsFree ( DWORD dwTlsIndex );

	int TransmitCommChar ( HANDLE hFile, char cChar );

	int TzSpecificLocalTimeToSystemTime ( TIME_ZONE_INFORMATION lpTimeZoneInformation, SYSTEMTIME lpLocalTime, SYSTEMTIME lpUniversalTime );

//	int UnhandledExceptionFilter ( Pointer ExceptionInfo );

	int UnlockFile ( HANDLE hFile, DWORD dwFileOffsetLow, DWORD dwFileOffsetHigh, DWORD nNumberOfBytesToUnlockLow, DWORD nNumberOfBytesToUnlockHigh );

	int UnlockFileEx ( HANDLE hFile, DWORD dwReserved, DWORD nNumberOfBytesToUnlockLow, DWORD nNumberOfBytesToUnlockHigh, OVERLAPPED lpOverlapped );

	int UnmapViewOfFile ( Pointer lpBaseAddress );

	int UnregisterApplicationRecoveryCallback ( );

	int UnregisterApplicationRestart ( );

	int UnregisterWait ( HANDLE WaitHandle );

	int UnregisterWaitEx ( HANDLE WaitHandle, HANDLE CompletionEvent );

	int VerifyVersionInfo ( OSVERSIONINFOEX lpVersionInfo, DWORD dwTypeMask, DWORDLONG dwlConditionMask );

	int VerSetConditionMask ( ULONGLONG dwlConditionMask, DWORD dwTypeBitMask, BYTE dwConditionMask );

//	int VirtualQuery ( LPVOID lpAddress, v2.org.analysis.apihandle.winapi.structures.WinNTn.MEMORY_BASIC_INFORMATION lpBuffer, SIZE_T dwLength );

	int VirtualQueryEx ( HANDLE hProcess, Pointer lpAddress, MEMORY_BASIC_INFORMATION lpBuffer, SIZE_T dwLength );

	int WaitCommEvent ( HANDLE hFile, IntByReference lpEvtMask, OVERLAPPED lpOverlapped );

	int WaitForMultipleObjects ( DWORD nCount, HANDLEByReference lpHandles, BOOL bWaitAll, DWORD dwMilliseconds );

	int WaitForMultipleObjectsEx ( DWORD nCount, HANDLEByReference lpHandles, BOOL bWaitAll, DWORD dwMilliseconds, BOOL bAlertable );

	int WaitForSingleObject ( HANDLE hHandle, DWORD dwMilliseconds );

	int WaitForSingleObjectEx ( HANDLE hHandle, DWORD dwMilliseconds, BOOL bAlertable );

//	int WaitNamedPipe ( String lpNamedPipeName, DWORD nTimeOut );

	int WerGetFlags ( HANDLE hProcess, IntByReference pdwFlags );

	int WerRegisterFile ( CHARByReference pwzFile, int regFileType, DWORD dwFlags );

	int WerSetFlags ( DWORD dwFlags );

	int WerUnregisterFile ( CHARByReference pwzFilePath );

	int WideCharToMultiByte ( UINT CodePage, DWORD dwFlags, CHARByReference lpWideCharStr, int cchWideChar, byte[] lpMultiByteStr, int cbMultiByte, ByteByReference lpDefaultChar, IntByReference lpUsedDefaultChar );

	int WinExec ( ByteByReference lpCmdLine, UINT uCmdShow );

	int Wow64DisableWow64FsRedirection ( PointerByReference OldValue );

	int Wow64EnableWow64FsRedirection ( byte Wow64FsEnableRedirection );

	int Wow64SuspendThread ( HANDLE hThread );

	int WriteConsoleInput ( HANDLE hConsoleInput, INPUT_RECORD lpBuffer, DWORD nLength, IntByReference lpNumberOfEventsWritten );

	int WriteConsoleOutput ( HANDLE hConsoleOutput, CHAR_INFO lpBuffer, COORD dwBufferSize, COORD dwBufferCoord, SMALL_RECT lpWriteRegion );

	int WriteConsoleOutputAttribute ( HANDLE hConsoleOutput, ShortByReference lpAttribute, DWORD nLength, COORD dwWriteCoord, int[] lpNumberOfAttrsWritten );

	int WriteConsoleOutputCharacter ( HANDLE hConsoleOutput, String lpCharacter, DWORD nLength, COORD dwWriteCoord, IntByReference lpNumberOfCharsWritten );

	int WriteFile ( HANDLE hFile, Pointer lpBuffer, DWORD nNumberOfBytesToWrite, IntByReference lpNumberOfBytesWritten, OVERLAPPED lpOverlapped );

	int WriteFileEx ( HANDLE hFile, Pointer lpBuffer, DWORD nNumberOfBytesToWrite, OVERLAPPED lpOverlapped, WinNT.OVERLAPPED_COMPLETION_ROUTINE lpCompletionRoutine );

	int WriteFileGather ( HANDLE hFile, FILE_SEGMENT_ELEMENT aSegmentArray[], DWORD nNumberOfBytesToWrite, IntByReference lpReserved, OVERLAPPED lpOverlapped );

	int WritePrivateProfileSection ( String lpAppName, String lpString, String lpFileName );

	int WritePrivateProfileString ( String lpAppName, String lpKeyName, String lpString, String lpFileName );

	int WriteProfileSection ( String lpAppName, String lpString );

	int WriteProfileString ( String lpAppName, String lpKeyName, String lpString );

	int WriteTapemark ( HANDLE hDevice, DWORD dwTapemarkType, DWORD dwTapemarkCount, BOOL bImmediate );
	
	// HaiNM
	BOOL VirtualLock(LPVOID lpvoid, SIZE_T size_T);
}
