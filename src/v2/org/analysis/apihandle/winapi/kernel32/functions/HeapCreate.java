/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: HeapCreate.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Creates a private heap object that can be used by the calling process. The
 * function reserves space in the virtual address space of the process and
 * allocates physical storage for a specified initial portion of this block.
 * 
 * @param flOptions
 *            :
 *            <p>
 *            The heap allocation options. These options affect subsequent
 *            access to the new heap through calls to the heap functions. This
 *            parameter can be 0 or one or more of the following values.
 *            </p>
 * 
 * @param dwInitialSize
 *            :
 *            <p>
 *            The initial size of the heap, in bytes. This value determines the
 *            initial amount of memory that is committed for the heap. The value
 *            is rounded up to a multiple of the system page size. The value
 *            must be smaller than <em>dwMaximumSize</em>.
 *            </p>
 *            <p>
 *            If this parameter is 0, the function commits one page. To
 *            determine the size of a page on the host computer, use the <a
 *            href=
 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/ms724381(v=vs.85).aspx"
 *            ><strong
 *            xmlns="http://www.w3.org/1999/xhtml">GetSystemInfo</strong ></a>
 *            function.
 *            </p>
 * 
 * @param dwMaximumSize
 *            :
 *            <p>
 *            The maximum size of the heap, in bytes. The
 *            <strong>HeapCreate</strong> function rounds <em>dwMaximumSize</em>
 *            up to a multiple of the system page size and then reserves a block
 *            of that size in the process's virtual address space for the heap.
 *            If allocation requests made by the <a href=
 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366597(v=vs.85).aspx"
 *            ><strong
 *            xmlns="http://www.w3.org/1999/xhtml">HeapAlloc</strong></a> or <a
 *            href=
 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366704(v=vs.85).aspx"
 *            ><strong
 *            xmlns="http://www.w3.org/1999/xhtml">HeapReAlloc</strong></a>
 *            functions exceed the size specified by <em>dwInitialSize</em>, the
 *            system commits additional pages of memory for the heap, up to the
 *            heap's maximum size.
 *            </p>
 * 
 *            <p>
 *            If <em>dwMaximumSize</em> is not zero, the heap size is fixed and
 *            cannot grow beyond the maximum size. Also, the largest memory
 *            block that can be allocated from the heap is slightly less than
 *            512 KB for a 32-bit process and slightly less than 1,024 KB for a
 *            64-bit process. Requests to allocate larger blocks fail, even if
 *            the maximum size of the heap is large enough to contain the block.
 *            </p>
 * 
 *            <p>
 *            If <em>dwMaximumSize</em> is 0, the heap can grow in size. The
 *            heap's size is limited only by the available memory. Requests to
 *            allocate memory blocks larger than the limit for a fixed-size heap
 *            do not automatically fail; instead, the system calls the <a href=
 *            "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366887(v=vs.85).aspx"
 *            ><strong
 *            xmlns="http://www.w3.org/1999/xhtml">VirtualAlloc</strong></a>
 *            function to obtain the memory that is needed for large blocks.
 *            Applications that need to allocate large memory blocks should set
 *            <em>dwMaximumSize</em> to 0.
 *            </p>
 * @return <p>
 *         If the function succeeds, the return value is a handle to the newly
 *         created heap.
 *         </p>
 *         <p>
 *         If the function fails, the return value is <strong>NULL</strong>. To
 *         get extended error information, call <a href=
 *         "https://msdn.microsoft.com/en-us/library/windows/desktop/ms679360(v=vs.85).aspx"
 *         ><strong
 *         xmlns="http://www.w3.org/1999/xhtml">GetLastError</strong></a>.
 *         </p>
 * 
 * @author Yen Nguyen
 *
 */
public class HeapCreate extends Kernel32API {

	/**
	 * 
	 */
	public HeapCreate() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		// String fileNameOld = symbolValueMemoryOperand.getText(this, new
		// X86MemoryOperand(DataType.INT32, t1));
		// String fileNameNew = symbolValueMemoryOperand.getText(this, new
		// X86MemoryOperand(DataType.INT32, t2));
		System.out.println("fOption:" + t1 + ", Initial Size:" + t2 + ", Maximum Size:" + t3);

		HANDLE ret = Kernel32DLL.INSTANCE.HeapCreate(new DWORD(t1), new SIZE_T(t2), new SIZE_T(t3));

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
