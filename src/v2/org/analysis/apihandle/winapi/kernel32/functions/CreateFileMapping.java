/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFileMapping.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Creates or opens a named or unnamed file mapping object for a specified file.
 * 
 * @param hFile
 *            A handle to the file from which to create a file mapping object.
 * 
 * @param lpAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
 *            whether a returned handle can be inherited by child processes. The
 *            lpSecurityDescriptor member of the SECURITY_ATTRIBUTES structure
 *            specifies a security descriptor for a new file mapping object.
 * 
 * @param flProtect
 *            Specifies the page protection of the file mapping object. All
 *            mapped views of the object must be compatible with this
 *            protection.
 * 
 * @param dwMaximumSizeHigh
 *            The high-order DWORD of the maximum size of the file mapping
 *            object.
 * 
 * @param dwMaximumSizeLow
 *            The low-order DWORD of the maximum size of the file mapping
 *            object.
 * 
 * @param lpName
 *            The name of the file mapping object.
 * 
 * @return If the function succeeds, the return value is a handle to the newly
 *         created file mapping object. If the object exists before the function
 *         call, the function returns a handle to the existing object (with its
 *         current size, not the specified size), and GetLastError returns
 *         ERROR_ALREADY_EXISTS. If the function fails, the return value is
 *         NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateFileMapping extends Kernel32API {

	/**
	 * 
	 */
	public CreateFileMapping() {
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

		HANDLE hFile = new HANDLE(new Pointer(t1));
		// TODO: Implement this
		SECURITY_ATTRIBUTES lpAttributes = null;
		int flProtect = (int) t3;
		int dwMaximumSizeHigh = (int) t4;
		int dwMaximumSizeLow = (int) t5;
		String lpName = (t6 != 0L) ? memory.getText(this, t6) : null;

		System.out.println("Handle File:" + t1 + ", Security Attribute:" + t2 + ", Object Protection:" + t3
				+ ", Maximum Size High:" + t4 + ", Maximum Size Low:" + t5 + ", File Mapping Name Address:" + lpName);

		HANDLE ret = Kernel32.INSTANCE.CreateFileMapping(hFile, lpAttributes, flProtect, dwMaximumSizeHigh,
				dwMaximumSizeLow, lpName);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
