/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetReadFile.java
 * Created date: Mar 11, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import java.nio.ByteBuffer;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * @author Yen Nguyen
 *
 */
public class InternetReadFile extends WininetAPI {

	public InternetReadFile() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HANDLE hFile = new HANDLE(new Pointer(t1));
		ByteBuffer lpBuffer = ByteBuffer.allocate((int) (t3 + 1));
		int dwNumberOfBytesToRead = (int) t3;
		DWORDByReference lpdwNumberOfBytesRead = new DWORDByReference();

		BOOL ret = WininetDLL.INSTANCE.InternetReadFile(hFile, lpBuffer, dwNumberOfBytesToRead, lpdwNumberOfBytesRead);

		register.mov("eax", new LongValue(ret.longValue()));

		try {
			byte[] bufferArray = lpBuffer.array();
			for (int i = 0; i < bufferArray.length; i++) {
				memory.setByteMemoryValue(t2 + i, new LongValue(bufferArray[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (t4 != 0L) {
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpdwNumberOfBytesRead.getValue().longValue()));
		}
	}

}
