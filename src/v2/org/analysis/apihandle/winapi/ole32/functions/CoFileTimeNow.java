/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32.functions
 * File name: CoFileTimeNow.java
 * Created date: Sep 17, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT.HRESULT;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.ole32.Ole32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Returns the current time as a FILETIME structure.
 * 
 * @param lpFileTime
 *            A pointer to the FILETIME structure that receives the current
 *            time.
 * 
 * @return This function returns S_OK to indicate success.
 * 
 * @author Yen Nguyen
 *
 */
public class CoFileTimeNow extends Ole32API {

	public CoFileTimeNow() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);

		FILETIME lpFileTime = new FILETIME();
		HRESULT ret = Ole32DLL.INSTANCE.CoFileTimeNow(lpFileTime);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(
				lpFileTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 + 4), new LongValue(
				lpFileTime.dwHighDateTime));
	}

}
