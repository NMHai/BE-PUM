/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetVersion.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * The GetVersion function returns the current version number of the operating
 * system.
 * 
 * @return If the function succeeds, the return value includes the major and
 *         minor version numbers of the operating system in the low order word,
 *         and information about the operating system platform in the high order
 *         word.
 * 
 * @author Yen Nguyen
 *
 */
public class GetVersion extends Kernel32API {

	/**
	 * 
	 */
	public GetVersion() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// Version of Windows XP: 170393861
		long verNum = Kernel32.INSTANCE.GetVersion().longValue();
		System.out.println("Version Number:" + verNum);

		register.mov("eax", new LongValue(verNum));
	}

}
