/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: LookupPrivilegeValue.java
 * Created date: Mar 4, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.LUID;

/**
 * The LookupPrivilegeValue function retrieves the locally unique identifier
 * (LUID) used on a specified system to locally represent the specified
 * privilege name.
 * 
 * @param lpSystemName
 *            A pointer to a null-terminated string that specifies the name of
 *            the system on which the privilege name is retrieved. If a null
 *            string is specified, the function attempts to find the privilege
 *            name on the local system.
 * 
 * @param lpName
 *            A pointer to a null-terminated string that specifies the name of
 *            the privilege, as defined in the Winnt.h header file. For example,
 *            this parameter could specify the constant, SE_SECURITY_NAME, or
 *            its corresponding string, "SeSecurityPrivilege".
 * 
 * @param lpLuid
 *            A pointer to a variable that receives the LUID by which the
 *            privilege is known on the system specified by the lpSystemName
 *            parameter.
 * 
 * @return If the function succeeds, the function returns nonzero. If the
 *         function fails, it returns zero. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 */
public class LookupPrivilegeValue extends Advapi32API {

	public LookupPrivilegeValue() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpSystemName = (t1 != 0L) ? memory.getText(this, t1) : null;
		String lpName = (t2 != 0L) ? memory.getText(this, t2) : null;
		LUID lpLuid = new LUID();

		BOOL ret = Advapi32DLL.INSTANCE.LookupPrivilegeValue(lpSystemName, lpName, lpLuid);
		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(t3, new LongValue(lpLuid.LowPart));
		memory.setDoubleWordMemoryValue(t3 + 4, new LongValue(lpLuid.HighPart));
	}

}
