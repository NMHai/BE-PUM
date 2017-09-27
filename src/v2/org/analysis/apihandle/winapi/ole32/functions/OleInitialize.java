/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32.functions
 * File name: OleInitialize.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32.functions;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.ole32.Ole32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HRESULT;

/**
 * @author Yen Nguyen
 *
 */
public class OleInitialize extends Ole32API {
	public OleInitialize() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		LPVOID pvReserved = new LPVOID(t1);
		HRESULT ret = Ole32DLL.INSTANCE.OleInitialize(pvReserved );
		
		long value = ret.longValue();
		
		register.mov("eax", new LongValue(value));
	}

}
