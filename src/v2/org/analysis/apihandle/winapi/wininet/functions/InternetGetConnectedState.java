/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetReadFile.java
 * Created date: Mar 11, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * @author Yen Nguyen
 *
 */
public class InternetGetConnectedState extends WininetAPI {

	public InternetGetConnectedState() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		Pointer lpdwFlags = memory.getPointer(t1);
		int dwReserved = (int)t2;
		BOOL ret = WininetDLL.INSTANCE.InternetGetConnectedState(lpdwFlags, dwReserved);
		System.out.println("State: " + (ret.longValue() == 1) + ", Flags:" + memory.getText(this, t1));
		register.mov("eax", new LongValue(ret.longValue()));		
	}

}
