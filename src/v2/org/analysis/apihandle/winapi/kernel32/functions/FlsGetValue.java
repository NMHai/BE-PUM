package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.PVOID;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

public class FlsGetValue extends Kernel32API {

	public FlsGetValue() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		DWORD dwFlsIndex = new DWORD(t1);
		PVOID ret = Kernel32DLL.INSTANCE.FlsGetValue(dwFlsIndex);

		long value = ret == null ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));

		if (value > 0) {
			long v = ret.getPointer().getLong(0);
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, value), new LongValue(v));
		}
	}

}
