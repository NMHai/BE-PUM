/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ntdll.functions
 * File name: NtCreateSection.java
 * Created date: Sep 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ntdll.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.PVOID;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.USHORT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
import v2.org.analysis.apihandle.winapi.ntdll.NtdllAPI;
import v2.org.analysis.apihandle.winapi.ntdll.NtdllDLL;
import v2.org.analysis.apihandle.winapi.structures.Winternl.OBJECT_ATTRIBUTES;
import v2.org.analysis.apihandle.winapi.structures.Winternl.UNICODE_STRING;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class NtCreateSection extends NtdllAPI {

	public NtCreateSection() {
		super();
		NUM_OF_PARMS = 7;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);

		HANDLEByReference SectionHandle = new HANDLEByReference();
		DWORD DesiredAccess = new DWORD(t2);
		OBJECT_ATTRIBUTES ObjectAttributes = null;
		if (t3 != 0L) {
			ObjectAttributes = new OBJECT_ATTRIBUTES();
			// public ULONG Length;
			// public HANDLE RootDirectory;
			// public UNICODE_STRING ObjectName;
			ObjectAttributes.Length = new ULONG((int) ((LongValue) memory.getDoubleWordMemoryValue(t3)).getValue());
			ObjectAttributes.RootDirectory = new HANDLE(new Pointer(
					((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue()));

			// public USHORT Length;
			// public USHORT MaximumLength;
			// public WString Buffer;
			long pObjectName = ((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue();
			UNICODE_STRING objectName = new UNICODE_STRING();
			objectName.Length = new USHORT(((LongValue) memory.getWordMemoryValue(pObjectName)).getValue());
			objectName.MaximumLength = new USHORT(((LongValue) memory.getWordMemoryValue(pObjectName += 2)).getValue());
			objectName.Buffer = new WString(memory.getText(this, pObjectName += 2));
			ObjectAttributes.ObjectName = objectName;

			// public ULONG Attributes;
			// public PVOID SecurityDescriptor;
			// public PVOID SecurityQualityOfService;
			ObjectAttributes.Attributes = new ULONG(
					(int) ((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue());
			ObjectAttributes.SecurityDescriptor = new PVOID(new Pointer(
					(int) ((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue()));
			ObjectAttributes.SecurityQualityOfService = new PVOID(new Pointer(
					(int) ((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue()));
		}

		LARGE_INTEGER MaximumSize = null;
		if (t4 != 0L) {
			MaximumSize = new LARGE_INTEGER();
			MaximumSize.u = new LARGE_INTEGER.UNION();
			MaximumSize.u.lh = new LARGE_INTEGER.LowHigh();
			MaximumSize.u.lh.LowPart = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue());
			MaximumSize.u.lh.HighPart = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
		}

		ULONG SectionPageProtection = new ULONG((int) t5);
		ULONG AllocationAttributes = new ULONG((int) t6);
		HANDLE FileHandle = (t7 == 0L) ? null : new HANDLE(new Pointer(t7));

		LONG ret = NtdllDLL.INSTANCE.NtCreateSection(SectionHandle, DesiredAccess, ObjectAttributes, MaximumSize,
				SectionPageProtection, AllocationAttributes, FileHandle);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1),
				new LongValue(Pointer.nativeValue(SectionHandle.getValue().getPointer())));
	}

}
