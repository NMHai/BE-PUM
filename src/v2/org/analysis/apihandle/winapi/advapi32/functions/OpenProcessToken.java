/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: OpenProcessToken.java
 * Created date: Sep 21, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The OpenProcessToken function opens the access token associated with a
 * process.
 * 
 * @param ProcessHandle
 *            A handle to the process whose access token is opened. The process
 *            must have the PROCESS_QUERY_INFORMATION access permission.
 * 
 * @param DesiredAccess
 *            dAccess [in] Specifies an access mask that specifies the requested
 *            types of access to the access token. These requested access types
 *            are compared with the discretionary access control list (DACL) of
 *            the token to determine which accesses are granted or denied. For a
 *            list of access rights for access tokens, see Access Rights for
 *            Access-Token Objects.
 * 
 * @param TokenHandle
 *            A pointer to a handle that identifies the newly opened access
 *            token when the function returns.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenProcessToken extends Advapi32API {

	public OpenProcessToken() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HANDLE ProcessHandle = new HANDLE(new Pointer(t1));
		DWORD DesiredAccess = new DWORD(t2);
		HANDLEByReference TokenHandle = new HANDLEByReference();
		BOOL ret = Advapi32DLL.INSTANCE.OpenProcessToken(ProcessHandle, DesiredAccess, TokenHandle);

		register.mov("eax", new LongValue(ret.longValue()));

		if (TokenHandle.getValue() != null) {
			long value = Pointer.nativeValue(TokenHandle.getValue().getPointer());
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(value));
		}
	}

}
