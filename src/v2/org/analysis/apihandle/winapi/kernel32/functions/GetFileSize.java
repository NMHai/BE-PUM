/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves the size of the specified file, in bytes.
 * 
 * @param hFile
 *            A handle to the file.
 * 
 * @param lpFileSizeHigh
 *            A pointer to the variable where the high-order doubleword of the
 *            file size is returned. This parameter can be NULL if the
 *            application does not require the high-order doubleword.
 * 
 * @return If the function succeeds, the return value is the low-order
 *         doubleword of the file size, and, if lpFileSizeHigh is non-NULL, the
 *         function puts the high-order doubleword of the file size into the
 *         variable pointed to by that parameter.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileSize extends Kernel32API {

	public GetFileSize() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		HANDLE hFile = new HANDLE(new Pointer(t1));
		DWORDByReference lpFileSizeHigh = (t2 == 0L) ? null : new DWORDByReference();

		DWORD ret = Kernel32DLL.INSTANCE.GetFileSize(hFile, lpFileSizeHigh);

		register.mov("eax", new LongValue(ret.longValue()));
		System.out.println("Return Value: " + ret.intValue());

		if (t2 != 0)
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpFileSizeHigh
					.getValue().longValue()));
	}

}
