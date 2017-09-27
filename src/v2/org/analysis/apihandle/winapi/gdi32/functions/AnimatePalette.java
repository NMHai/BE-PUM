/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: AnimatePalette.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.structures.Wingdi.PALETTEENTRY;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class AnimatePalette extends Kernel32API {

	public AnimatePalette() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		HANDLE hpal = new HANDLE(new Pointer(t1));
		UINT iStartIndex = new UINT(t2);
		UINT cEntries = new UINT(t3);
		PALETTEENTRY pe = new PALETTEENTRY();

		// public BYTE peRed;
		// public BYTE peGreen;
		// public BYTE peBlue;
		// public BYTE peFlags;
		pe.peRed = new BYTE(((LongValue) memory.getByteMemoryValue(t4)).getValue());
		pe.peGreen = new BYTE(((LongValue) memory.getByteMemoryValue(t4 += 1)).getValue());
		pe.peBlue = new BYTE(((LongValue) memory.getByteMemoryValue(t4 += 1)).getValue());
		pe.peFlags = new BYTE(((LongValue) memory.getByteMemoryValue(t4 += 1)).getValue());

		int ret = Gdi32DLL.INSTANCE.AnimatePalette(hpal, iStartIndex, cEntries, pe);

		register.mov("eax", new LongValue(ret));
	}

}
