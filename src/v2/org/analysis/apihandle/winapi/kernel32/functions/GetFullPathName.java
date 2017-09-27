/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetFullPathName.java
 * Created date: Nov 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

/**
 * Retrieves the full path and file name of the specified file. To perform this
 * operation as a transacted operation, use the GetFullPathNameTransacted
 * function.
 * 
 * @param lpFileName
 *            The name of the file. This parameter can be a short (the 8.3 form)
 *            or long file name. This string can also be a share or volume name.
 *            In the ANSI version of this function, the name is limited to
 *            MAX_PATH characters. To extend this limit to 32,767 wide
 *            characters, call the Unicode version of the function and prepend
 *            "\\?\" to the path. For more information, see Naming a File.
 * 
 * @param nBufferLength
 *            The size of the buffer to receive the null-terminated string for
 *            the drive and path, in TCHARs.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the null-terminated string for
 *            the drive and path.
 * 
 * @param lpFilePart
 *            A pointer to a buffer that receives the address (within lpBuffer)
 *            of the final file name component in the path. This parameter can
 *            be NULL. If lpBuffer refers to a directory and not a file,
 *            lpFilePart receives zero.
 * 
 * @return If the function succeeds, the return value is the length, in TCHARs,
 *         of the string copied to lpBuffer, not including the terminating null
 *         character. If the lpBuffer buffer is too small to contain the path,
 *         the return value is the size, in TCHARs, of the buffer that is
 *         required to hold the path and the terminating null character. If the
 *         function fails for any other reason, the return value is zero. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFullPathName extends Kernel32API {

	public GetFullPathName() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		String lpFileName = memory.getText(this, t1);
		int nBufferLength = (int) t2;
		char[] lpBuffer = new char[nBufferLength];
		Pointer lpFilePart = new Memory(4);
		int ret = Kernel32DLL.INSTANCE.GetFullPathName(lpFileName, nBufferLength, lpBuffer, lpFilePart);

		register.mov("eax", new LongValue(ret));

		if (t3 != 0L) {
			int index = 0;
			int i = 0;
			for (i = 0; i < lpBuffer.length; i++) {
				if (lpBuffer[i] == '\0') {
					break;
				}

				memory.setByteMemoryValue(t3 + i, new LongValue(lpBuffer[i]));

				if (lpBuffer[i] == '\\') {
					index = i;
				}
			}

			// Null-terminated
			memory.setByteMemoryValue(t3 + i + 1, new LongValue(0));

			// Found '/' character and it's not the last character (the path
			// must be the file path
			if (index > 0 && index != i) {
				memory.setDoubleWordMemoryValue(t4, new LongValue(t3 + index));
			}
		}
	}
}
