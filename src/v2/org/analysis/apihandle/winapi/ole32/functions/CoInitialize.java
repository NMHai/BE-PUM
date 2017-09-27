/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32.functions
 * File name: CoInitialize.java
 * Created date: Mar 9, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32.functions;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.ole32.Ole32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HRESULT;

/**
 * Initializes the COM library on the current thread and identifies the
 * concurrency model as single-thread apartment (STA). New applications should
 * call CoInitializeEx instead of CoInitialize. If you want to use the Windows
 * Runtime, you must call Windows::Foundation::Initialize instead.
 * 
 * @param pvReserved
 *            This parameter is reserved and must be NULL.
 * 
 * @return This function can return the standard return values E_INVALIDARG,
 *         E_OUTOFMEMORY, and E_UNEXPECTED, as well as the following values.
 * 
 * @author Yen Nguyen
 *
 */
public class CoInitialize extends Ole32API {

	public CoInitialize() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		LPVOID pvReserved = new LPVOID(t1);
		HRESULT ret = Ole32DLL.INSTANCE.CoInitialize(pvReserved);
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
