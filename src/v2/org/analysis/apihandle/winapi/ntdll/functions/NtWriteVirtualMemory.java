package v2.org.analysis.apihandle.winapi.ntdll.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.PVOID;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.ULONGByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.ntdll.NtdllAPI;
import v2.org.analysis.apihandle.winapi.ntdll.NtdllDLL;
import v2.org.analysis.value.LongValue;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class NtWriteVirtualMemory extends NtdllAPI {

	public NtWriteVirtualMemory() {
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
		PVOID BaseAddress = new PVOID(new Pointer(t2));
		byte[] Buffer = new byte[(int) t4];
		ULONG NumberOfBytesToWrite = new ULONG((int) t4);
		ULONGByReference NumberOfBytesWritten = (t5 == 0L) ? null : new ULONGByReference();

		byte[] byteBuffer = memory.getBytesArray(new AbsoluteAddress(t3), NumberOfBytesToWrite.intValue());
		for (int i = 0; i < byteBuffer.length; i++) {
			Buffer[i] = byteBuffer[i];
		}

		LONG ret = NtdllDLL.INSTANCE.NtWriteVirtualMemory(ProcessHandle, BaseAddress, Buffer, NumberOfBytesToWrite,
				NumberOfBytesWritten);

		register.mov("eax", new LongValue(ret.longValue()));

		if (t5 != 0L) {
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(
					NumberOfBytesWritten.getValue().longValue()));
		}
	}

}
