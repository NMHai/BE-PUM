/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetVolumeInformation.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;

/**
 * @author Yen Nguyen
 *
 */
public class GetVolumeInformation extends Kernel32API {

	public GetVolumeInformation() {
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

		WString lpRootPathName = (t1 == 0L) ? null : new WString(memory.getText(this, t1));
		char[] lpVolumeNameBuffer = (t2 == 0L) ? null : new char[(int) t3];
		DWORD nVolumeNameSize = new DWORD(t3);
		DWORDByReference lpVolumeSerialNumber = (t4 == 0L) ? null : new DWORDByReference();
		DWORDByReference lpMaximumComponentLength = (t5 == 0L) ? null : new DWORDByReference();
		DWORDByReference lpFileSystemFlags = (t6 == 0L) ? null : new DWORDByReference();
		char[] lpFileSystemNameBuffer = (t7 == 0L) ? null : new char[(int) t8];
		DWORD nFileSystemNameSize = new DWORD(t8);

		BOOL ret = Kernel32DLL.INSTANCE.GetVolumeInformation(lpRootPathName, lpVolumeNameBuffer, nVolumeNameSize,
				lpVolumeSerialNumber, lpMaximumComponentLength, lpFileSystemFlags, lpFileSystemNameBuffer,
				nFileSystemNameSize);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);

		if (t2 != 0) {
			String volumeNameBuffer = Convert.reduceText(lpVolumeNameBuffer);
			System.out.println(String.format("lpVolumeNameBuffer: %s", volumeNameBuffer));
			memory.setText(this, t2, volumeNameBuffer);
		}

		if (t7 != 0) {
			String fileSystemNameBuffer = Convert.reduceText(lpFileSystemNameBuffer);
			System.out.println(String.format("lpFileSystemNameBuffer: %s", fileSystemNameBuffer));
			memory.setText(this, t7, fileSystemNameBuffer);
		}

		if (t4 != 0) {
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpVolumeSerialNumber.getValue().longValue()));
		}

		if (t5 != 0) {
			memory.setDoubleWordMemoryValue(t5, new LongValue(lpMaximumComponentLength.getValue().longValue()));
		}

		if (t6 != 0) {
			memory.setDoubleWordMemoryValue(t6, new LongValue(lpFileSystemFlags.getValue().longValue()));
		}
	}

}
