/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Takes a snapshot of the specified processes, as well as the heaps, modules,
 * and threads used by these processes.
 * 
 * @param dwFlags
 *            [in] The portions of the system to be included in the snapshot.
 *            This parameter can be one or more of the following values.
 * 
 * @param th32ProcessID
 *            [in] The process identifier of the process to be included in the
 *            snapshot. This parameter can be zero to indicate the current
 *            process. This parameter is used when the TH32CS_SNAPHEAPLIST,
 *            TH32CS_SNAPMODULE, TH32CS_SNAPMODULE32, or TH32CS_SNAPALL value is
 *            specified. Otherwise, it is ignored and all processes are included
 *            in the snapshot.
 * 
 *            If the specified process is the Idle process or one of the CSRSS
 *            processes, this function fails and the last error code is
 *            ERROR_ACCESS_DENIED because their access restrictions prevent
 *            user-level code from opening them.
 * 
 *            If the specified process is a 64-bit process and the caller is a
 *            32-bit process, this function fails and the last error code is
 *            ERROR_PARTIAL_COPY (299).
 * 
 * @return If the function succeeds, it returns an open handle to the specified
 *         snapshot.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateToolhelp32Snapshot extends Kernel32API {

	public CreateToolhelp32Snapshot() {
		super();
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		DWORD dwFlags = new DWORD(t1);
		DWORD th32ProcessID = new DWORD(t2);
		HANDLE ret = Kernel32DLL.INSTANCE.CreateToolhelp32Snapshot(dwFlags, th32ProcessID);

		LongValue storeValue = new LongValue(Pointer.nativeValue(ret.getPointer()));
		register.mov("eax", storeValue);
		System.out.println("Return value:" + Pointer.nativeValue(ret.getPointer()) + " " + storeValue + " "
				+ storeValue.getValue());
		System.out.println("Stored return value:" + register.getRegisterValue("eax"));
	}
}
