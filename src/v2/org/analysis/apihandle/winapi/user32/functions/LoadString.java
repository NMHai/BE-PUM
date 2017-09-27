/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.value.LongValue;

/**
 * Loads a string resource from the executable file associated with a specified
 * module, copies the string into a buffer, and appends a terminating null
 * character.
 * 
 * @param hInstance
 *            A handle to an instance of the module whose executable file
 *            contains the string resource. To get the handle to the application
 *            itself, call the GetModuleHandle function with NULL.
 * 
 * @param uID
 *            The identifier of the string to be loaded.
 * 
 * @param lpBuffer
 *            The buffer is to receive the string. Must be of sufficient length
 *            to hold a pointer (8 bytes).
 * 
 * @param nBufferMax
 *            The size of the buffer, in characters. The string is truncated and
 *            null-terminated if it is longer than the number of characters
 *            specified. If this parameter is 0, then lpBuffer receives a
 *            read-only pointer to the resource itself.
 * 
 * @return If the function succeeds, the return value is the number of
 *         characters copied into the buffer, not including the terminating null
 *         character, or zero if the string resource does not exist. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadString extends User32API {

	public LoadString() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HINSTANCE hInstance = null;
		if (t1 != 0L) {
			hInstance = new HINSTANCE();
			hInstance.setPointer(new Pointer(t1));
		}
		UINT uID = new UINT(t2);
		char[] lpBuffer = new char[(int) t4];
		int nBufferMax = (int) t4;
		int ret = User32DLL.INSTANCE.LoadString(hInstance, uID, lpBuffer, nBufferMax);

		register.mov("eax", new LongValue(ret));

		if (ret != 0L) {
			for (int i = 0; i < ret; i++) {
				memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t3 + i), new LongValue(lpBuffer[i]));
			}
		}
	}

}
