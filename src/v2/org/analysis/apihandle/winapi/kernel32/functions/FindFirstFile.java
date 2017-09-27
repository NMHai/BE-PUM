/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.WIN32_FIND_DATA;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Searches a directory for a file or subdirectory with a name that matches a
 * specific name (or partial name if wildcards are used).
 * 
 * @param lpFileName
 *            : The directory or path, and the file name, which can include
 *            wildcard characters, for example, an asterisk (*) or a question
 *            mark (?).
 * 
 * @param lpFindFileData
 *            : A pointer to the WIN32_FIND_DATA structure that receives
 *            information about a found file or directory.
 * 
 * @return If the function succeeds, the return value is a search handle used in
 *         a subsequent call to FindNextFile or FindClose, and the
 *         lpFindFileData parameter contains information about the first file or
 *         directory found.
 * 
 * @author Yen Nguyen
 *
 */
public class FindFirstFile extends Kernel32API {

	public FindFirstFile() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long pFind = this.params.get(1);

		String searchName = memory.getText(this, t1);
		System.out.println("Search File:" + searchName + ", Memory Operand:" + pFind);

		WIN32_FIND_DATA lpFindFileData = new WIN32_FIND_DATA();
		HANDLE ret = Kernel32DLL.INSTANCE.FindFirstFile(new WString(searchName), lpFindFileData);

		System.out.println("Search Handle:" + Pointer.nativeValue(ret.getPointer()));
		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));

		memory.setDoubleWordMemoryValue(pFind, new LongValue(lpFindFileData.dwFileAttributes.longValue()));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftCreationTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftCreationTime.dwHighDateTime));

		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastAccessTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastAccessTime.dwHighDateTime));

		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastWriteTime.dwLowDateTime));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastWriteTime.dwHighDateTime));

		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.nFileSizeHigh.longValue()));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.nFileSizeLow.longValue()));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.dwReserved0.longValue()));
		memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.dwReserved1.longValue()));

		memory.setText(this, pFind += 4, new String(lpFindFileData.cFileName));
		String t = new String(lpFindFileData.cFileName);
		t = Convert.reduceText(t);
		memory.setText(this, pFind += (2 * t.length()), new String(lpFindFileData.cAlternateFileName));

		register.setRegisterValue("edx", new LongValue(0));
	}
}
