/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: UnmapViewOfFile.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Unmaps a mapped view of a file from the calling process's address space.
 * 
 * @param lpBaseAddress
 *            A pointer to the base address of the mapped view of a file that is
 *            to be unmapped.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class UnmapViewOfFile extends Kernel32API {

	/**
	 * 
	 */
	public UnmapViewOfFile() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		System.out.println("Argument:" + t1);

		Pointer lpBaseAddress = t1 != 0L ? new Pointer(t1) : Pointer.NULL;
		boolean ret = Kernel32.INSTANCE.UnmapViewOfFile(lpBaseAddress);

		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
