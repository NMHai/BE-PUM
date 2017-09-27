/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: MapViewOfFile.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.value.LongValue;

/**
 * Maps a view of a file mapping into the address space of a calling process.
 * 
 * @param hFileMappingObject
 *            A handle to a file mapping object. The CreateFileMapping and
 *            OpenFileMapping functions return this handle.
 * 
 * @param dwDesiredAccess
 *            The type of access to a file mapping object, which determines the
 *            protection of the pages.
 * 
 * @param dwFileOffsetHigh
 *            A high-order DWORD of the file offset where the view begins.
 * 
 * @param dwFileOffsetLow
 *            A low-order DWORD of the file offset where the view is to begin.
 * 
 * @param dwNumberOfBytesToMap
 *            The number of bytes of a file mapping to map to the view.
 * 
 * @return If the function succeeds, the return value is the starting address of
 *         the mapped view. If the function fails, the return value is NULL. To
 *         get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class MapViewOfFile extends Kernel32API {

	/**
	 * 
	 */
	public MapViewOfFile() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {

		long t1 = this.params.get(0);
		HANDLE hFileMappingObject = new HANDLE(t1 != 0L ? new Pointer(t1) : Pointer.NULL);
		int dwDesiredAccess = this.params.get(1).intValue();
		int dwFileOffsetHigh = this.params.get(2).intValue();
		int dwFileOffsetLow = this.params.get(3).intValue();
		int dwNumberOfBytesToMap = this.params.get(4).intValue();

		System.out.println("Handle File:" + t1 + ", Access Mode:" + dwDesiredAccess
				+ ", High-order 32 bits of file offset:" + dwFileOffsetHigh + ", Low-order 32 bits of file offset :"
				+ dwFileOffsetLow + ", Number of bytes to map:" + dwNumberOfBytesToMap);

		Pointer ret = Kernel32.INSTANCE.MapViewOfFile(hFileMappingObject, dwDesiredAccess, dwFileOffsetHigh,
				dwFileOffsetLow, dwNumberOfBytesToMap);

		System.out.println("Base Address: " + Pointer.nativeValue(ret));
		register.mov("eax", new LongValue(Pointer.nativeValue(ret)));
	}

}
