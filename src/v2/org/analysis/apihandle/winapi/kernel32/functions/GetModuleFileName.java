/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetModuleFileName.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.io.File;

import org.jakstab.Program;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the fully qualified path for the file that contains the specified
 * module. The module must have been loaded by the current process.
 * 
 * @param hModule
 *            A handle to the loaded module whose path is being requested. If
 *            this parameter is NULL, GetModuleFileName retrieves the path of
 *            the executable file of the current process.
 * 
 * @param lpFilename
 *            A pointer to a buffer that receives the fully qualified path of
 *            the module. If the length of the path is less than the size that
 *            the nSize parameter specifies, the function succeeds and the path
 *            is returned as a null-terminated string.
 * 
 * @param nSize
 *            The size of the lpFilename buffer, in TCHARs.
 * 
 * @return If the function succeeds, the return value is the length of the
 *         string that is copied to the buffer, in characters, not including the
 *         terminating null character. If the buffer is too small to hold the
 *         module name, the string is truncated to nSize characters including
 *         the terminating null character, the function returns nSize, and the
 *         function sets the last error to ERROR_INSUFFICIENT_BUFFER.
 * 
 * @author Yen Nguyen
 * 
 */
public class GetModuleFileName extends Kernel32API {

	public GetModuleFileName() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		System.out.println("Argument: Module: " + t1 + " lpFilename Address: " + t2 + " Array Size:" + t3);

		char Filename[] = new char[(int) t3];
		HMODULE module = new HMODULE();
		module.setPointer(new Pointer(t1));

		String output = null;
		DWORD ret = null;

		if (t1 == 0L) {
			output = Program.getProgram().getAbsolutePathFile();
			ret = new DWORD(output.length());
			Kernel32.INSTANCE.SetLastError(0);
		} else {
			ret = Kernel32DLL.INSTANCE.GetModuleFileName(module, Filename, new DWORD(t3));
			output = new String(Filename, 0, ret.intValue());
		}

		String jre_location = System.getProperties().getProperty("java.home") + File.separator + "bin" + File.separator
				+ "java";
		String jdk_location = System.getProperties().getProperty("java.home") + File.separator + "java";
		if (jdk_location.contains("jre")) {
			jdk_location = jdk_location.replace("jre", "bin");
		}

		if (output.startsWith(jre_location) || output.startsWith(jdk_location) || output.contains("java.exe")) {
			output = Program.getProgram().getAbsolutePathFile();
			ret = new DWORD(output.length());
			Kernel32.INSTANCE.SetLastError(0);
		}

		System.out.println("Filename:" + output + " \r\nReturn: " + ret);

		memory.setText(this, t2, output);
		// Null-terminated string
		int numOfBytes = (is64bit()) ? 2 : 1;
		memory.setByteMemoryValue(t2 + ret.longValue() * numOfBytes, new LongValue(0));

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
