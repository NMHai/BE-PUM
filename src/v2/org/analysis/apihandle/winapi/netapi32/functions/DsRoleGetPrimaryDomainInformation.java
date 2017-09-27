/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetReadFile.java
 * Created date: Mar 11, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.netapi32.functions;

import v2.org.analysis.apihandle.winapi.netapi32.Netapi32DLL;
import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * @author Yen Nguyen
 *
 */
public class DsRoleGetPrimaryDomainInformation extends WininetAPI {

	public DsRoleGetPrimaryDomainInformation() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		Pointer lpServer = memory.getPointer(t1);
		int InfoLevel = (int) t2;
		Pointer buffer = memory.getPointer(t3);

		System.out.println("lpServer:" + memory.getText(this, t1) + ", InforLevel:" + InfoLevel);

		DWORD ret = Netapi32DLL.INSTANCE.DsRoleGetPrimaryDomainInformation(lpServer, InfoLevel, buffer);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
