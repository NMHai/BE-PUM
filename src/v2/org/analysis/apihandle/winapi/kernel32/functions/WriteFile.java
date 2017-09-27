/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WriteFile.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

/**
 * Writes data to the specified file or input/output (I/O) device.
 * 
 * @param hFile
 *            A handle to the file or I/O device (for example, a file, file
 *            stream, physical disk, volume, console buffer, tape drive, socket,
 *            communications resource, mailslot, or pipe).
 * 
 * @param lpBuffer
 *            A pointer to the buffer containing the data to be written to the
 *            file or device.
 * 
 * @param nNumberOfBytesToWrite
 *            The number of bytes to be written to the file or device.
 * 
 * @param lpNumberOfBytesWritten
 *            A pointer to the variable that receives the number of bytes
 *            written when using a synchronous hFile parameter.
 * 
 * @param lpOverlapped
 *            A pointer to an OVERLAPPED structure is required if the hFile
 *            parameter was opened with FILE_FLAG_OVERLAPPED, otherwise this
 *            parameter can be NULL.
 * 
 * @return If the function succeeds, the return value is nonzero (TRUE). If the
 *         function fails, or is completing asynchronously, the return value is
 *         zero (FALSE). To get extended error information, call the
 *         GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class WriteFile extends Kernel32API {

	/**
	 * 
	 */
	public WriteFile() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		/*
		 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
		 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
		 */
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		String str = memory.getText(this, t2);

		System.out.println("Handle File:" + t1 + ", String written:" + str + ", Number of Byte:" + t3 + ", Pointer:"
				+ t4 + ", Overlapped:" + t5);

		HANDLE hFile = new HANDLE(t1 != 0L ? new Pointer(t1) : Pointer.NULL);
		byte[] lpBuffer = str.getBytes();
		int nNumberOfBytesToWrite = (int) t3;
		IntByReference lpNumberOfBytesWritten = new IntByReference((int) t4);

		OVERLAPPED lpOverlapped = new OVERLAPPED();
		if (t5 != 0L) {
			lpOverlapped.Internal = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue());
			lpOverlapped.InternalHigh = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t5 + 4)).getValue());
			lpOverlapped.Offset = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 + 8)).getValue());
			lpOverlapped.OffsetHigh = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 + 12)).getValue());
			lpOverlapped.hEvent = new HANDLE(new Pointer(
					((LongValue) memory.getDoubleWordMemoryValue(t5 + 16)).getValue()));
		}

		boolean ret = Kernel32.INSTANCE.WriteFile(hFile, lpBuffer, nNumberOfBytesToWrite, lpNumberOfBytesWritten,
				lpOverlapped);

		register.mov("eax", new LongValue(ret ? 1 : 0));
	}
}
