/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: ReadFile.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.nio.ByteBuffer;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

/**
 * Reads data from the specified file or input/output (I/O) device. Reads occur
 * at the position specified by the file pointer if supported by the device.
 * 
 * This function is designed for both synchronous and asynchronous operations.
 * For a similar function designed solely for asynchronous operation, see
 * ReadFileEx
 * 
 * @param hFile
 *            A handle to the device (for example, a file, file stream, physical
 *            disk, volume, console buffer, tape drive, socket, communications
 *            resource, mailslot, or pipe).
 * 
 * @param lpBuffer
 *            A pointer to the buffer that receives the data read from a file or
 *            device.
 * 
 * @param nNumberOfBytesToRead
 *            The maximum number of bytes to be read.
 * 
 * @param lpNumberOfBytesRead
 *            A pointer to the variable that receives the number of bytes read
 *            when using a synchronous hFile parameter
 * 
 * @param lpOverlapped
 *            A pointer to an OVERLAPPED structure is required if the hFile
 *            parameter was opened with FILE_FLAG_OVERLAPPED, otherwise it can
 *            be NULL.
 * 
 * @return If the function succeeds, the return value is nonzero (TRUE). If the
 *         function fails, or is completing asynchronously, the return value is
 *         zero (FALSE). To get extended error information, call the
 *         GetLastError function.
 * 
 *         Note The GetLastError code ERROR_IO_PENDING is not a failure; it
 *         designates the read operation is pending completion asynchronously.
 *         For more information, see Remarks.
 * 
 * @author Yen Nguyen
 *
 */
public class ReadFile extends Kernel32API {
	public ReadFile() {
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

		System.out.println("Handle File:" + t1 + ", Address of Buffer:" + t2 + ", Number of Byte:" + t3
				+ ", Number of Actual Read Bytes:" + t4 + ", Overlapped:" + t5);

		HANDLE hFile = new HANDLE(t1 != 0L ? new Pointer(t1) : Pointer.NULL);
		// Pointer lpBuffer = new Pointer(t2);
		int nNumberOfBytesToRead = (int) t3;
		ByteBuffer lpBuffer = ByteBuffer.allocate(nNumberOfBytesToRead + 1);
		IntByReference lpNumberOfBytesRead = new IntByReference((int) t4);

		OVERLAPPED lpOverlapped = null;

		if (t5 != 0L) {
			lpOverlapped = new OVERLAPPED();
			lpOverlapped.Internal = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue());
			lpOverlapped.InternalHigh = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue());
			lpOverlapped.Offset = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue());
			lpOverlapped.OffsetHigh = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue());
			lpOverlapped.hEvent = new HANDLE(new Pointer(
					((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue()));
			t5 = this.params.get(4);
		}

		boolean ret = Kernel32.INSTANCE.ReadFile(hFile, lpBuffer, nNumberOfBytesToRead, lpNumberOfBytesRead,
				lpOverlapped);

		register.mov("eax", new LongValue(ret ? 1 : 0));

		try {
			byte[] bufferArray = lpBuffer.array();
			for (int i = 0; i < bufferArray.length; i++) {
				memory.setByteMemoryValue(t2 + i, new LongValue(bufferArray[i]));
			}
			// System.out.println("Data: " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("ReadFile is done");

		if (t4 != 0L) {
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4),
					new LongValue(lpNumberOfBytesRead.getValue()));
		}

		if (t5 != 0L) {
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(
					lpOverlapped.Internal.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4), new LongValue(
					lpOverlapped.InternalHigh.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4), new LongValue(
					lpOverlapped.Offset));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4), new LongValue(
					lpOverlapped.OffsetHigh));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4),
					new LongValue(Pointer.nativeValue(lpOverlapped.hEvent.getPointer())));
		}
	}
}
