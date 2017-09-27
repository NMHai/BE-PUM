/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: IsWow64Process.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The IsWow64Process function determines whether the specified process is
 * running under WOW64.
 * 
 * @param hProcess
 *            Handle to a process.
 * 
 * @param Wow64Process
 *            Pointer to a value that is set to TRUE if the process is running
 *            under WOW64. Otherwise, the value is set to FALSE.
 * 
 * @return If the function succeeds, the return value is a nonzero value. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class IsWow64Process extends Kernel32API {

	public IsWow64Process() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hProcess = new HANDLE(new Pointer(t1));
		IntByReference Wow64Process = new IntByReference();
		boolean ret = Kernel32.INSTANCE.IsWow64Process(hProcess, Wow64Process);

		register.mov("eax", new LongValue(ret ? 1 : 0));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
				new LongValue(Wow64Process.getValue()));
	}

}
