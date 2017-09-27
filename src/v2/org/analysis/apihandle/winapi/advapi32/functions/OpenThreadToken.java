/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: OpenThreadToken.java
 * Created date: Aug 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.value.LongValue;

/**
 * The OpenThreadToken function opens the access token associated with a thread.
 *
 * @param ThreadHandle
 *            Handle to the thread whose access token is opened.
 * 
 * @param DesiredAccess
 *            Specifies an access mask that specifies the requested types of
 *            access to the access token. These requested access types are
 *            reconciled against the token's discretionary access control list
 *            (DACL) to determine which accesses are granted or denied.
 * 
 * @param OpenAsSelf
 *            Indicates whether the access check is to be made against the
 *            security context of the thread calling the OpenThreadToken
 *            function or against the security context of the process for the
 *            calling thread.
 * 
 * @param TokenHandle
 *            Pointer to a variable that receives the handle to the newly opened
 *            access token.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenThreadToken extends Advapi32API {

	public OpenThreadToken() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HANDLE ThreadHandle = new HANDLE(new Pointer(t1));
		int DesiredAccess = (int) t2;
		boolean OpenAsSelf = (t3 == 0L) ? false : true;
		HANDLEByReference TokenHandle = new HANDLEByReference();
		boolean ret = Advapi32.INSTANCE.OpenThreadToken(ThreadHandle, DesiredAccess, OpenAsSelf, TokenHandle);

		System.out.println("Return value:" + (ret ? 1 : 0));
		register.mov("eax", new LongValue(ret ? 1 : 0));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4),
				new LongValue(Pointer.nativeValue(TokenHandle.getValue().getPointer())));
	}

}
