/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: HeapSize.java
 * Created date: Aug 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the size of a memory block allocated from a heap by the HeapAlloc
 * or HeapReAlloc function.
 * 
 * @param hHeap
 *            A handle to the heap in which the memory block resides. This
 *            handle is returned by either the HeapCreate or GetProcessHeap
 *            function.
 * 
 * @param dwFlags
 *            The heap size options. Specifying the following value overrides
 *            the corresponding value specified in the flOptions parameter when
 *            the heap was created by using the HeapCreate function.
 * 
 * @param lpMem
 *            A pointer to the memory block whose size the function will obtain.
 *            This is a pointer returned by the HeapAlloc or HeapReAlloc
 *            function. The memory block must be from the heap specified by the
 *            hHeap parameter.
 * 
 * @return If the function succeeds, the return value is the requested size of
 *         the allocated memory block, in bytes. If the function fails, the
 *         return value is (SIZE_T)-1. The function does not call SetLastError.
 *         An application cannot call GetLastError for extended error
 *         information. If the lpMem parameter refers to a heap allocation that
 *         is not in the heap specified by the hHeap parameter, the behavior of
 *         the HeapSize function is undefined.
 * 
 * @author Yen Nguyen
 *
 */
public class HeapSize extends Kernel32API {

	public HeapSize() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		
		HANDLE hHeap = new HANDLE(new Pointer(t1));
		DWORD dwFlags = new DWORD(t2);
		Pointer lpMem = new Pointer(t3);
		
		SIZE_T ret = Kernel32DLL.INSTANCE.HeapSize(hHeap, dwFlags, lpMem);
		
		long value = (ret == null) ? 0 : ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
