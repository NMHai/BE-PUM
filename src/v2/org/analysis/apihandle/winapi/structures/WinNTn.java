package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 
 * @author Yen Nguyen
 */
public interface WinNTn extends StdCallLibrary, WinNT {
	public static final int EXCEPTION_MAXIMUM_PARAMETERS = 15;
	public static final int MAXIMUM_SUPPORTED_EXTENSION = 512;

	public static class PRTL_CRITICAL_SECTION extends Structure {
		public LONG LockCount;
		public LONG RecursionCount;
		public HANDLE OwningThread; // from the thread's ClientId->UniqueThread
		public HANDLE LockSemaphore;
		public ULONG_PTR SpinCount; // force size on 64-bit systems when packed

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "LockCount", "RecursionCount", "OwningThread", "LockSemaphore",
					"SpinCount" });
		}
	}

	public class LIST_ENTRY extends Structure {
		public LPVOID Flink;
		public LPVOID Blink;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Flink", "Blink" });
		}
	}

	public class RTL_CRITICAL_SECTION_DEBUG extends Structure {
		public WORD Type;
		public WORD CreatorBackTraceIndex;
		public LPVOID CriticalSection; // struct _RTL_CRITICAL_SECTION *
		public LIST_ENTRY ProcessLocksList;
		public DWORD EntryCount;
		public DWORD ContentionCount;
		public DWORD Flags;
		public WORD CreatorBackTraceIndexHigh;
		public WORD SpareWORD;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Type", "CreatorBackTraceIndex", "CriticalSection", "ProcessLocksList",
					"EntryCount", "ContentionCount", "Flags", "CreatorBackTraceIndexHigh", "SpareWORD" });
		}
	}

	public class RTL_CRITICAL_SECTION extends Structure {
		public LPVOID /* PRTL_CRITICAL_SECTION_DEBUG */DebugInfo = null;
		public LONG LockCount;
		public LONG RecursionCount;
		public HANDLE OwningThread; // from the thread's ClientId->UniqueThread
		public HANDLE LockSemaphore;
		public ULONG_PTR SpinCount; // force size on 64-bit systems when packed

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "DebugInfo", "LockCount", "RecursionCount", "OwningThread",
					"LockSemaphore", "SpinCount" });
		}

		public RTL_CRITICAL_SECTION() {
		}

		public static class ByReference extends RTL_CRITICAL_SECTION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public RTL_CRITICAL_SECTION(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class CPINFO extends Structure {
		public UINT MaxCharSize = new UINT(); // max length (in bytes) of a char
		public BYTE DefaultChar[] = new BYTE[2]; // default character
		public BYTE LeadByte[] = new BYTE[12]; // lead byte ranges

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "MaxCharSize", "DefaultChar", "LeadByte" });
		}

		public static class ByReference extends CPINFO implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public CPINFO() {

		}

		public CPINFO(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class MEMORY_BASIC_INFORMATION extends Structure {
		public PVOID BaseAddress;
		public PVOID AllocationBase;
		public DWORD AllocationProtect;
		public SIZE_T RegionSize;
		public DWORD State;
		public DWORD Protect;
		public DWORD Type;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "BaseAddress", "AllocationBase", "AllocationProtect", "RegionSize",
					"State", "Protect", "Type" });
		}

		public static class ByReference extends MEMORY_BASIC_INFORMATION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public MEMORY_BASIC_INFORMATION() {

		}

		public MEMORY_BASIC_INFORMATION(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class PROCESS_BASIC_INFORMATION extends Structure {
		public LONG ExitStatus;
		public PEB PebBaseAddress;
		public ULONG_PTR AffinityMask;
		public LONG BasePriority;
		public ULONG_PTR UniqueProcessId;
		public ULONG_PTR InheritedFromUniqueProcessId;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "ExitStatus", "PebBaseAddress", "AffinityMask", "BasePriority",
					"UniqueProcessId", "InheritedFromUniqueProcessId" });
		}

		public static class ByReference extends PROCESS_BASIC_INFORMATION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public PROCESS_BASIC_INFORMATION() {

		}

		public PROCESS_BASIC_INFORMATION(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class PEB extends Structure {
		public byte Reserved1[] = new byte[2];
		public byte BeingDebugged;
		public byte Reserved2[] = new byte[1];
		public PVOID Reserved3[] = new PVOID[2];
		public/* smPPEB_LDR_DATA */Pointer Ldr;
		public/* smPRTL_USER_PROCESS_PARAMETERS */Pointer ProcessParameters;
		public byte Reserved4[] = new byte[104];
		public PVOID Reserved5[] = new PVOID[52];
		public/* smPPS_POST_PROCESS_INIT_ROUTINE */ULONG PostProcessInitRoutine;
		public byte Reserved6[] = new byte[128];
		public PVOID Reserved7[] = new PVOID[1];
		public ULONG SessionId;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Reserved1", "BeingDebugged", "Reserved2", "Reserved3", "Ldr",
					"ProcessParameters", "Reserved4", "Reserved5", "PostProcessInitRoutine", "Reserved6", "Reserved7",
					"SessionId" });
		}

		public static class ByReference extends PEB implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public PEB() {

		}

		public PEB(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class UNICODE_STRING extends Structure {
		public USHORT Length;
		public USHORT MaximumLength;
		public WString Buffer;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Length", "MaximumLength", "Buffer" });
		}

		public static class ByReference extends UNICODE_STRING implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public UNICODE_STRING() {

		}

		public UNICODE_STRING(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class FLOATING_SAVE_AREA extends Structure {
		public DWORD ControlWord;
		public DWORD StatusWord;
		public DWORD TagWord;
		public DWORD ErrorOffset;
		public DWORD ErrorSelector;
		public DWORD DataOffset;
		public DWORD DataSelector;
		public BYTE RegisterArea[] = new BYTE[80];
		public DWORD Cr0NpxState;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "ControlWord", "StatusWord", "TagWord", "ErrorOffset", "ErrorSelector",
					"DataOffset", "DataSelector", "RegisterArea", "Cr0NpxState" });
		}

		public static class ByReference extends FLOATING_SAVE_AREA implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public FLOATING_SAVE_AREA() {

		}

		public FLOATING_SAVE_AREA(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class CONTEXT extends Structure {
		//
		// The flags values within this flag control the contents of
		// a CONTEXT record.
		//
		// If the context record is used as an input parameter, then
		// for each portion of the context record controlled by a flag
		// whose value is set, it is assumed that that portion of the
		// context record contains valid context. If the context record
		// is being used to modify a threads context, then only that
		// portion of the threads context will be modified.
		//
		// If the context record is used as an IN OUT parameter to capture
		// the context of a thread, then only those portions of the thread's
		// context corresponding to set flags will be returned.
		//
		// The context record is never used as an OUT only parameter.
		//

		public DWORD ContextFlags;

		//
		// This section is specified/returned if CONTEXT_DEBUG_REGISTERS is
		// set in ContextFlags. Note that CONTEXT_DEBUG_REGISTERS is NOT
		// included in CONTEXT_FULL.
		//

		public DWORD Dr0;
		public DWORD Dr1;
		public DWORD Dr2;
		public DWORD Dr3;
		public DWORD Dr6;
		public DWORD Dr7;

		//
		// This section is specified/returned if the
		// ContextFlags word contians the flag CONTEXT_FLOATING_POINT.
		//

		public FLOATING_SAVE_AREA FloatSave;

		//
		// This section is specified/returned if the
		// ContextFlags word contians the flag CONTEXT_SEGMENTS.
		//

		public DWORD SegGs;
		public DWORD SegFs;
		public DWORD SegEs;
		public DWORD SegDs;

		//
		// This section is specified/returned if the
		// ContextFlags word contians the flag CONTEXT_INTEGER.
		//

		public DWORD Edi;
		public DWORD Esi;
		public DWORD Ebx;
		public DWORD Edx;
		public DWORD Ecx;
		public DWORD Eax;

		//
		// This section is specified/returned if the
		// ContextFlags word contians the flag CONTEXT_CONTROL.
		//

		public DWORD Ebp;
		public DWORD Eip;
		public DWORD SegCs; // MUST BE SANITIZED
		public DWORD EFlags; // MUST BE SANITIZED
		public DWORD Esp;
		public DWORD SegSs;

		//
		// This section is specified/returned if the ContextFlags word
		// contains the flag CONTEXT_EXTENDED_REGISTERS.
		// The format and contexts are processor specific
		//

		BYTE ExtendedRegisters[] = new BYTE[MAXIMUM_SUPPORTED_EXTENSION];

		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList(new String[] { "ContextFlags", "Dr0", "Dr1", "Dr2", "Dr3", "Dr6", "Dr7", "FloatSave",
					"SegGs", "SegFs", "SegEs", "SegDs", "Edi", "Esi", "Ebx", "Edx", "Ecx", "Eax", "Ebp", "Eip",
					"SegCs", "EFlags", "Esp", "SegSs", "ExtendedRegisters" });
		}

		public static class ByReference extends CONTEXT implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public CONTEXT() {

		}

		public CONTEXT(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class EXCEPTION_RECORD extends Structure {
		public DWORD ExceptionCode;
		public DWORD ExceptionFlags;
		public EXCEPTION_RECORD ExceptionRecord;
		public PVOID ExceptionAddress;
		public DWORD NumberParameters;
		public ULONG_PTR ExceptionInformation[] = new ULONG_PTR[EXCEPTION_MAXIMUM_PARAMETERS];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "ExceptionCode", "ExceptionFlags", "ExceptionRecord",
					"ExceptionAddress", "NumberParameters", "ExceptionInformation" });
		}

		public static class ByReference extends EXCEPTION_RECORD implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public EXCEPTION_RECORD() {

		}

		public EXCEPTION_RECORD(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class ACL_REVISION_INFORMATION extends Structure {
		public DWORD AclRevision;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "AclRevision" });
		}

		public static class ByReference extends ACL_REVISION_INFORMATION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public ACL_REVISION_INFORMATION() {

		}

		public ACL_REVISION_INFORMATION(Pointer memory) {
			super(memory);
			read();
		}
	}

	public static class ACL_SIZE_INFORMATION extends Structure {
		public DWORD AceCount;
		public DWORD AclBytesInUse;
		public DWORD AclBytesFree;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "AceCount", "AclBytesInUse", "AclBytesFree" });
		}

		public static class ByReference extends ACL_SIZE_INFORMATION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public ACL_SIZE_INFORMATION() {

		}

		public ACL_SIZE_INFORMATION(Pointer memory) {
			super(memory);
			read();
		}
	}
}
