/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.CPINFO;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about any valid installed or available code page.
 * 
 * @param CodePage
 *            Identifier for the code page for which to retrieve information.
 *            For details, see the CodePage parameter of GetCPInfoEx.
 * 
 * @param lpCPInfo
 *            Pointer to a CPINFO structure that receives information about the
 *            code page. See the Remarks section.
 * 
 * @return Returns 1 if successful, or 0 otherwise. To get extended error
 *         information, the application can call GetLastError, which can return
 *         one of the following error codes: ERROR_INVALID_PARAMETER. Any of the
 *         parameter values was invalid.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCPInfo extends Kernel32API {

	public GetCPInfo() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		UINT CodePage = new UINT(t1);
		CPINFO lpCPInfo = new CPINFO();
		BOOL ret = Kernel32DLL.INSTANCE.GetCPInfo(CodePage, lpCPInfo);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
				new LongValue(lpCPInfo.MaxCharSize.longValue()));
		memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 1),
				new LongValue(lpCPInfo.DefaultChar[0].longValue()));
		memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 1),
				new LongValue(lpCPInfo.DefaultChar[1].longValue()));

		for (int i = 0; i < 12; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 1 + i), new LongValue(
					lpCPInfo.LeadByte[i].longValue()));
		}
	}

}
