/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetDiskFreeSpaceEx.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;

/**
 * Retrieves information about the specified disk, including the amount of free
 * space on the disk.
 * 
 * @param lpRootPathName
 *            The root directory of the disk for which information is to be
 *            returned. If this parameter is NULL, the function uses the root of
 *            the current disk. If this parameter is a UNC name, it must include
 *            a trailing backslash (for example, "\\MyServer\MyShare\").
 *            Furthermore, a drive specification must have a trailing backslash
 *            (for example, "C:\"). The calling application must have
 *            FILE_LIST_DIRECTORY access rights for this directory.
 * 
 * @param lpSectorsPerCluster
 *            A pointer to a variable that receives the number of sectors per
 *            cluster.
 * 
 * @param lpBytesPerSector
 *            A pointer to a variable that receives the number of bytes per
 *            sector.
 * 
 * @param lpNumberOfFreeClusters
 *            A pointer to a variable that receives the total number of free
 *            clusters on the disk that are available to the user who is
 *            associated with the calling thread. If per-user disk quotas are in
 *            use, this value may be less than the total number of free clusters
 *            on the disk.
 * 
 * @param lpTotalNumberOfClusters
 *            A pointer to a variable that receives the total number of clusters
 *            on the disk that are available to the user who is associated with
 *            the calling thread. If per-user disk quotas are in use, this value
 *            may be less than the total number of clusters on the disk.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDiskFreeSpace extends Kernel32API {

	public GetDiskFreeSpace() {
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

		WString lpRootPathName = new WString(memory.getText(this, t1));
		DWORDByReference lpSectorsPerCluster = new DWORDByReference(new DWORD(t2));
		DWORDByReference lpBytesPerSector = new DWORDByReference(new DWORD(t3));
		DWORDByReference lpNumberOfFreeClusters = new DWORDByReference(new DWORD(t4));
		DWORDByReference lpTotalNumberOfClusters = new DWORDByReference(new DWORD(t4));
		BOOL ret = Kernel32DLL.INSTANCE.GetDiskFreeSpace(lpRootPathName, lpSectorsPerCluster, lpBytesPerSector,
				lpNumberOfFreeClusters, lpTotalNumberOfClusters);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(t2, new LongValue(lpSectorsPerCluster.getValue().longValue()));
		memory.setDoubleWordMemoryValue(t3, new LongValue(lpBytesPerSector.getValue().longValue()));
		memory.setDoubleWordMemoryValue(t4, new LongValue(lpNumberOfFreeClusters.getValue().longValue()));
		memory.setDoubleWordMemoryValue(t5, new LongValue(lpTotalNumberOfClusters.getValue().longValue()));
	}
}
