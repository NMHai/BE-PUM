/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptDecrypt.java
 * Created date: May 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.ptr.ByteByReference;

/**
 * @author Yen Nguyen
 *
 */
public class CryptDecrypt extends Advapi32API {

	public CryptDecrypt() {
		super();
		NUM_OF_PARMS = 6;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		ULONG_PTR hKey = new ULONG_PTR(t1);
		ULONG_PTR hHash = new ULONG_PTR(t2);
		BOOL Final = new BOOL(t3);
		DWORD dwFlags = new DWORD(t4);
		ByteByReference pbData = new ByteByReference();
		DWORDByReference pdwDataLen = new DWORDByReference();

		BOOL ret = Advapi32DLL.INSTANCE.CryptDecrypt(hKey, hHash, Final, dwFlags, pbData, pdwDataLen);

		register.mov("eax", new LongValue(ret.longValue()));
		System.out.println("Return Value: " + ret.booleanValue());

		memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t5), new LongValue(pbData.getValue()));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t6), new LongValue(pdwDataLen.getValue()
				.longValue()));
	}

}
