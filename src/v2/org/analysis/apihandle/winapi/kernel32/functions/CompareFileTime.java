/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.value.LongValue;

/**
 * Compares two file times.
 * 
 * @param lpFileTime1
 *            [in] A pointer to a FILETIME structure that specifies the first
 *            file time.
 * 
 * @param lpFileTime2
 *            [in] A pointer to a FILETIME structure that specifies the second
 *            file time.
 * 
 * @return The return value is one of the following values.
 * 
 * @author Yen Nguyen
 *
 */
public class CompareFileTime extends Kernel32API {

	public CompareFileTime() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		FILETIME lpFileTime1 = new FILETIME();
		FILETIME lpFileTime2 = new FILETIME();

		lpFileTime1.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1))).getValue();
		lpFileTime1.dwHighDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t1 + 4))).getValue();
		lpFileTime2.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t2))).getValue();
		lpFileTime2.dwHighDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
				DataType.INT32, t2 + 4))).getValue();

		LONG ret = Kernel32DLL.INSTANCE.CompareFileTime(lpFileTime1, lpFileTime2);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
