/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptCreateHash.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * 
 * @author HaiNM
 *
 */
public class CryptGetHashParam extends Advapi32API {

	public CryptGetHashParam() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HANDLE hHash = new HANDLE(new Pointer(t1));
		int dwParam = (int) (t2);
		Pointer pbData = memory.getPointer(t3);
		Pointer pdwDataLen = memory.getPointer(t4);
		int dwFlags =  (int) (t5);

		BOOL ret = Advapi32DLL.INSTANCE.CryptGetHashParam(hHash, dwParam, pbData, pdwDataLen, dwFlags);
		System.out.println("Hash Byte:" + ((pbData != null) ? pbData.getByte(0) : "null") + ", Data Length:" + pdwDataLen.getDouble(0));

		long value = ret.booleanValue() ? 1 : 0;
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);

//		memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t3), new LongValue(pbData.getValue()));
//		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(pdwDataLen.getValue()
//				.longValue()));
	}

}
