/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindNextFile.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.WIN32_FIND_DATA;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Continues a file search from a previous call to the FindFirstFile,
 * FindFirstFileEx, or FindFirstFileTransacted functions.
 * 
 * @param hFindFile
 *            : The search handle returned by a previous call to the
 *            FindFirstFile or FindFirstFileEx function.
 * 
 * @param lpFindFileData
 *            : A pointer to the WIN32_FIND_DATA structure that receives
 *            information about the found file or subdirectory.
 * 
 * @return If the function succeeds, the return value is nonzero and the
 *         lpFindFileData parameter contains information about the next file or
 *         directory found.
 * 
 * @author Yen Nguyen
 *
 */
public class FindNextFile extends Kernel32API {

	/**
	 * 
	 */
	public FindNextFile() {
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
		HANDLE hFindFile = new HANDLE(new Pointer(t1));
		BOOL ret = Kernel32DLL.INSTANCE.FindNextFile(hFindFile, lpFindFileData);

		System.out.println("Return value:" + ret.longValue());
		register.mov("eax", new LongValue(ret.longValue()));

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

		// memory.setText(this, pFind += 4), new
		// String(lpFindFileData.cFileName));

		pFind += 4; // Passed lpFindFileData.dwReserved1
		System.out.println("File name result: " + Convert.reduceText(new String(lpFindFileData.cFileName)));

		// Store byte by byte of lpFindFileData.cFileName array
		for (char c : lpFindFileData.cFileName) {
			memory.setByteMemoryValue(pFind, new LongValue(c));
			pFind++;
		}

		// The size of lpFindFileData.cFileName array is fixed, length is 260.
		// After lpFindFileData.cFileName has been stored by above loop,
		// pFind pointer reaches to address of lpFindFileData.cAlternateFileName
		memory.setText(this, pFind, new String(lpFindFileData.cAlternateFileName));
	}
}
