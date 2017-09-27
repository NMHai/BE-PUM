package v2.org.analysis.apihandle.winapi.ntdll.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.ULONGByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.PointerByReference;

import v2.org.analysis.apihandle.winapi.ntdll.NtdllAPI;
import v2.org.analysis.apihandle.winapi.ntdll.NtdllDLL;
import v2.org.analysis.value.LongValue;

public class NtProtectVirtualMemory extends NtdllAPI {

	public NtProtectVirtualMemory() {
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
		LongValue address = (LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2));
		PointerByReference BaseAddress = new PointerByReference(new Pointer(address.getValue()));
		
		LongValue numOfBytes = (LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3));
		ULONGByReference NumberOfBytesToProtect = new ULONGByReference(new ULONG((int) numOfBytes.getValue()));
		
		ULONG NewAccessProtection = new ULONG((int) t4);
		ULONGByReference OldAccessProtection = new ULONGByReference(new ULONG());

		LONG ret = NtdllDLL.INSTANCE.NtProtectVirtualMemory(ProcessHandle, BaseAddress, NumberOfBytesToProtect,
				NewAccessProtection, OldAccessProtection);
		
		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
				new LongValue(Pointer.nativeValue(BaseAddress.getValue().getPointer(0))));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3),
				new LongValue(NumberOfBytesToProtect.getValue().longValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5),
				new LongValue(OldAccessProtection.getValue().longValue()));
	}
}
