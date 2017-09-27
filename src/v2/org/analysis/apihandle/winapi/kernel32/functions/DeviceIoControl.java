/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: DeviceIoControl.java
 * Created date: Mar 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Sends a control code directly to a specified device driver, causing the
 * corresponding device to perform the corresponding operation.
 * 
 * @param hDevice
 *            [in] A handle to the device on which the operation is to be
 *            performed. The device is typically a volume, directory, file, or
 *            stream. To retrieve a device handle, use the CreateFile function.
 *            For more information, see Remarks.
 * 
 * @param dwIoControlCode
 *            [in] The control code for the operation. This value identifies the
 *            specific operation to be performed and the type of device on which
 *            to perform it. For a list of the control codes, see Remarks. The
 *            documentation for each control code provides usage details for the
 *            lpInBuffer, nInBufferSize, lpOutBuffer, and nOutBufferSize
 *            parameters.
 * 
 * @param lpInBuffer
 *            [in, optional] A pointer to the input buffer that contains the
 *            data required to perform the operation. The format of this data
 *            depends on the value of the dwIoControlCode parameter. This
 *            parameter can be NULL if dwIoControlCode specifies an operation
 *            that does not require input data.
 * 
 * @param nInBufferSize
 *            [in] The size of the input buffer, in bytes.
 * 
 * @param lpOutBuffer
 *            [out, optional] A pointer to the output buffer that is to receive
 *            the data returned by the operation. The format of this data
 *            depends on the value of the dwIoControlCode parameter. This
 *            parameter can be NULL if dwIoControlCode specifies an operation
 *            that does not return data.
 * 
 * @param nOutBufferSize
 *            [in] The size of the output buffer, in bytes.
 * 
 * @param lpBytesReturned
 *            [out, optional] A pointer to a variable that receives the size of
 *            the data stored in the output buffer, in bytes.
 * 
 * @param lpOverlapped
 *            [in, out, optional] A pointer to an OVERLAPPED structure.
 * 
 * @return If the operation completes successfully, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class DeviceIoControl extends Kernel32API {

	public DeviceIoControl() {
		super();
		NUM_OF_PARMS = 8;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);
		long t8 = this.params.get(7);

		HANDLE hDevice = new HANDLE(new Pointer(t1));
		DWORD dwIoControlCode = new DWORD(t2);
		ByteBuffer lpInBuffer = (t4 == 0L) ? null : ByteBuffer.allocate((int) t4 + 1);
		int nInBufferSize = (int) t4;
		ByteBuffer lpOutBuffer = (t6 == 0L) ? null : ByteBuffer.allocate((int) t6 + 1);
		int nOutBufferSize = (int) t6;
		DWORDByReference lpBytesReturned = new DWORDByReference();
		OVERLAPPED lpOverlapped = null;

		BOOL ret = Kernel32DLL.INSTANCE.DeviceIoControl(hDevice, dwIoControlCode, lpInBuffer, nInBufferSize,
				lpOutBuffer, nOutBufferSize, lpBytesReturned, lpOverlapped);

		if (t8 != 0L) {
			lpOverlapped = new OVERLAPPED();
			lpOverlapped.Internal = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t8)).getValue());
			lpOverlapped.InternalHigh = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t8 += 4)).getValue());
			lpOverlapped.Offset = (int) (((LongValue) memory.getDoubleWordMemoryValue(t8 += 4)).getValue());
			lpOverlapped.OffsetHigh = (int) (((LongValue) memory.getDoubleWordMemoryValue(t8 += 4)).getValue());
			lpOverlapped.hEvent = new HANDLE(new Pointer(
					((LongValue) memory.getDoubleWordMemoryValue(t8 += 4)).getValue()));
			t8 = this.params.get(4);
		}

		register.mov("eax", new LongValue(ret.longValue()));

		if (lpInBuffer != null) {
			try {
				String str = new String(lpInBuffer.array(), "UTF-8");
				memory.setText(this, t3, str);
				System.out.println("Data: " + str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (lpOutBuffer != null) {
			try {
				String str = new String(lpOutBuffer.array(), "UTF-8");
				memory.setText(this, t5, str);
				System.out.println("Data: " + str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (t7 != 0L) {
			memory.setDoubleWordMemoryValue(t7, new LongValue(lpBytesReturned
					.getValue().longValue()));
		}

		if (t8 != 0L) {
			memory.setDoubleWordMemoryValue(t8, new LongValue(
					lpOverlapped.Internal.longValue()));
			memory.setDoubleWordMemoryValue(t8 += 4, new LongValue(
					lpOverlapped.InternalHigh.longValue()));
			memory.setDoubleWordMemoryValue(t8 += 4, new LongValue(
					lpOverlapped.Offset));
			memory.setDoubleWordMemoryValue(t8 += 4, new LongValue(
					lpOverlapped.OffsetHigh));
			memory.setDoubleWordMemoryValue(t8 += 4,
					new LongValue(Pointer.nativeValue(lpOverlapped.hEvent.getPointer())));
		}
	}
}
