/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetLastError.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves a bitmask representing the currently available disk drives.
 * 
 * @return If the function succeeds, the return value is a bitmask representing
 *         the currently available disk drives. Bit position 0 (the
 *         least-significant bit) is drive A, bit position 1 is drive B, bit
 *         position 2 is drive C, and so on.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLogicalDrives extends Kernel32API {

	public GetLogicalDrives() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// This function has no parameters.
		DWORD ret = Kernel32DLL.INSTANCE.GetLogicalDrives();

		register.mov("eax", new LongValue(ret.longValue()));
		System.out.println("Return Value: " + ret.longValue());
	}

}
