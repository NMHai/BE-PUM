/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: HeapCreate.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * @author HaiNM
 *
 */
public class SetTokenInformation extends Kernel32API {

	/**
	 * 
	 */
	public SetTokenInformation() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		// String fileNameOld = symbolValueMemoryOperand.getText(this, new
		// X86MemoryOperand(DataType.INT32, t1));
		// String fileNameNew = symbolValueMemoryOperand.getText(this, new
		// X86MemoryOperand(DataType.INT32, t2));
		System.out.println("Token Handle:" + t1 + ", TokenInformationClass:" + t2 + ", TokenInformation:" + t3 + ", TokenInformationLength:" + t4);
				
		BOOL ret = Advapi32DLL.INSTANCE.SetTokenInformation(new HANDLE(new Pointer(t1)), (int)t2, new LPVOID(t3), new DWORD(t4));

		long value = (ret.booleanValue()) ? 1 : 0;
		register.mov("eax", new LongValue(value));
	}

}
