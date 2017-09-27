/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.OFSTRUCT;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Creates, opens, reopens, or deletes a file.
 * 
 * @param lpFileName
 *            The name of the file.
 * 
 * @param lpReOpenBuff
 *            A pointer to the OFSTRUCT structure that receives information
 *            about a file when it is first opened.
 * 
 * @param uStyle
 *            The action to be taken.
 * 
 * @return If the function succeeds, the return value specifies a file handle to
 *         use when performing file I/O. To close the file, call the CloseHandle
 *         function using this handle.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenFile extends Kernel32API {

	public OpenFile() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String lpFileName = memory.getText(this, t1);
		lpFileName = Storage.getMappingPath(lpFileName);
		OFSTRUCT lpReOpenBuff = new OFSTRUCT();
		UINT uStyle = new UINT(t3);
		int ret = Kernel32DLL.INSTANCE.OpenFile(lpFileName, lpReOpenBuff, uStyle);

		register.mov("eax", new LongValue(ret));

		// public BYTE cBytes;
		// public BYTE fFixedDisk;
		// public WORD nErrCode;
		// public WORD Reserved1;
		// public WORD Reserved2;
		// public char szPathName[] = new char[128];

		memory.setByteMemoryValue(t2, new LongValue(lpReOpenBuff.cBytes.longValue()));
		memory.setByteMemoryValue(t2 += 1, new LongValue(lpReOpenBuff.fFixedDisk.longValue()));
		memory.setWordMemoryValue(t2 += 1, new LongValue(lpReOpenBuff.nErrCode.longValue()));
		memory.setWordMemoryValue(t2 += 2,
				new LongValue(lpReOpenBuff.Reserved1 == null ? 0 : lpReOpenBuff.Reserved1.longValue()));
		memory.setWordMemoryValue(t2 += 2,
				new LongValue(lpReOpenBuff.Reserved2 == null ? 0 : lpReOpenBuff.Reserved2.longValue()));
		memory.setText(this, t2 += 2, new String(lpReOpenBuff.szPathName));
	}
}
