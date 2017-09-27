/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHGetFileInfo.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.apihandle.winapi.shell32.Shell32DLL;
import v2.org.analysis.apihandle.winapi.structures.ShellApi.SHFILEINFO;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * @author Yen Nguyen
 *
 */
public class SHGetFileInfo extends Shell32API {
	public SHGetFileInfo() {
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

		String pszPath = memory.getText(this, t1);
		DWORD dwFileAttributes = new DWORD(t2);
		SHFILEINFO psfi = new SHFILEINFO();
		UINT cbFileInfo = new UINT(t4);
		UINT uFlags = new UINT(t5);

		// public HICON hIcon; // out: icon
		// public int iIcon; // out: icon index
		// public DWORD dwAttributes; // out: SFGAO_ flags
		// public char[] szDisplayName = new char[260]; // out: display name (or
		// path)
		// public char[] szTypeName = new char[80]; // out: type name
		psfi.hIcon = new HICON(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(t3)).getValue()));
		psfi.iIcon = (int) ((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue();
		psfi.dwAttributes = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t3 += 4)).getValue());

		String fileInfo = memory.getText(this, t3 += 4);
		int count = 0;
		for (char c : fileInfo.toCharArray()) {
			psfi.szDisplayName[count] = c;
			count++;
		}

		String typeName = memory.getText(this, t3 += 4);
		count = 0;
		for (char c : typeName.toCharArray()) {
			psfi.szTypeName[count] = c;
			count++;
		}
		DWORD_PTR ret = Shell32DLL.INSTANCE.SHGetFileInfo(pszPath, dwFileAttributes, psfi, cbFileInfo, uFlags);

		long value = ret.longValue();

		register.mov("eax", new LongValue(value));

		t3 = this.params.get(2);
		memory.setDoubleWordMemoryValue(t3,
				new LongValue((psfi.hIcon == null) ? 0 : Pointer.nativeValue(psfi.hIcon.getPointer())));
		memory.setDoubleWordMemoryValue(t3 += 4, new LongValue(psfi.iIcon));
		memory.setDoubleWordMemoryValue(t3 += 4, new LongValue(psfi.dwAttributes.longValue()));
		memory.setText(this, t3, Convert.reduceText(psfi.szDisplayName));
		t3 += 260;
		memory.setText(this, t3, Convert.reduceText(psfi.szTypeName));
	}

}
