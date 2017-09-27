/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetComputerName.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves only the NetBIOS name of the local computer.
 * 
 * @param buffer
 *            A pointer to a buffer that receives the computer name or the
 *            cluster virtual server name. The buffer size should be large
 *            enough to contain MAX_COMPUTERNAME_LENGTH + 1 characters.
 * 
 * @param lpnSize
 *            On input, specifies the size of the buffer, in TCHARs. On output,
 *            the number of TCHARs copied to the destination buffer, not
 *            including the terminating null character. If the buffer is too
 *            small, the function fails and GetLastError returns
 *            ERROR_BUFFER_OVERFLOW. The lpnSize parameter specifies the size of
 *            the buffer required, including the terminating null character.
 * 
 * @return If the function succeeds, the return value is a nonzero value. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetComputerName extends Kernel32API {

	public GetComputerName() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		int buffSize = (int) t2;
		if (buffSize > 256)
			buffSize = 256;
		char[] lpBuffer = new char[buffSize];
		IntByReference lpnSize = new IntByReference((int) t2);

		boolean ret = Kernel32.INSTANCE.GetComputerName(lpBuffer, lpnSize);

		StringBuilder compName = new StringBuilder();
		for (int i = 0; i < lpBuffer.length; i++) {
			if (lpBuffer[i] != 0) {
				memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t1 + i), new LongValue(lpBuffer[i]));
				compName.append(lpBuffer[i]);
			}
		}
		System.out.println("Computer Name:" + compName.toString());
		register.mov("eax", new LongValue(ret ? 1 : 0));
	}

}
