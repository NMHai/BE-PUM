package v2.org.analysis.apihandle.winapi.user32.functions;

import java.nio.ByteBuffer;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about the specified window station or desktop object.
 * 
 * @param hObj
 *            A handle to the window station or desktop object. This handle is
 *            returned by the CreateWindowStation, OpenWindowStation,
 *            CreateDesktop, or OpenDesktop function.
 * 
 * @param nIndex
 *            The information to be retrieved.
 * 
 * @param pvInfo
 *            A pointer to a buffer to receive the object information.
 * 
 * @param nLength
 *            The size of the buffer pointed to by the pvInfo parameter, in
 *            bytes.
 * 
 * @param lpnLengthNeeded
 *            A pointer to a variable receiving the number of bytes required to
 *            store the requested information. If this variable's value is
 *            greater than the value of the nLength parameter when the function
 *            returns, the function returns FALSE, and none of the information
 *            is copied to the pvInfo buffer. If the value of the variable
 *            pointed to by lpnLengthNeeded is less than or equal to the value
 *            of nLength, the entire information block is copied.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetUserObjectInformation extends User32API {

	public GetUserObjectInformation() {
		super();
		NUM_OF_PARMS = 5;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		HANDLE hObj = new HANDLE(new Pointer(t1));
		int nIndex = (int) t2;
		ByteBuffer pvInfo = (t3 == 0L) ? null : ByteBuffer.allocate((int) t4);
		DWORD nLength = new DWORD(t4);
		DWORDByReference lpnLengthNeeded = (t5 == 0L) ? null : new DWORDByReference();

		BOOL ret = User32DLL.INSTANCE.GetUserObjectInformation(hObj, nIndex, pvInfo, nLength, lpnLengthNeeded);

		register.mov("eax", new LongValue(ret.longValue()));

		if (ret.booleanValue()) {
			// If the function returns FALSE, and none of the information is
			// copied to the pvInfo buffer.
			if (t3 != 0L && t4 > 0L) {
				try {
					byte[] bufferArray = pvInfo.array();
					for (int i = 0; i < lpnLengthNeeded.getValue().intValue(); i++) {
						memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t3 + i), //
								new LongValue((long) bufferArray[i]));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (t5 != 0L) {
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), //
						new LongValue(lpnLengthNeeded.getValue().longValue()));
			}
		}
	}
}
