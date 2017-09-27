package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.apihandle.winapi.structures.Wingdi;
import v2.org.analysis.apihandle.winapi.structures.Wingdi.LOGPALETTE;
import v2.org.analysis.apihandle.winapi.structures.Wingdi.PALETTEENTRY;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * The CreatePalette function creates a logical palette.
 * 
 * @param lplgpl
 *            A pointer to a LOGPALETTE structure that contains information
 *            about the colors in the logical palette.
 * 
 * @return If the function succeeds, the return value is a handle to a logical
 *         palette. If the function fails, the return value is NULL./**
 * 
 * @author Yen Nguyen
 *
 */
public class CreatePalette extends Gdi32API {

	public CreatePalette() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		
		LOGPALETTE lplgpl = new LOGPALETTE();
		lplgpl.palVersion = new WORD(((LongValue) memory.getWordMemoryValue(t1)).getValue());
		t1 += 2;
		lplgpl.palNumEntries = new WORD(((LongValue) memory.getWordMemoryValue(t1)).getValue());
		t1 += 2;
		
		long pointer = ((LongValue) memory.getDoubleWordMemoryValue(t1)).getValue();
		lplgpl.palPalEntry = new PALETTEENTRY[1];
		lplgpl.palPalEntry[0].peRed = new BYTE(((LongValue) memory.getByteMemoryValue(pointer)).getValue());
		pointer++;
		lplgpl.palPalEntry[0].peGreen = new BYTE(((LongValue) memory.getByteMemoryValue(pointer)).getValue());
		pointer++;
		lplgpl.palPalEntry[0].peBlue = new BYTE(((LongValue) memory.getByteMemoryValue(pointer)).getValue());
		pointer++;
		lplgpl.palPalEntry[0].peFlags = new BYTE(((LongValue) memory.getByteMemoryValue(pointer)).getValue());
		
		HANDLE ret = Gdi32DLL.INSTANCE.CreatePalette(lplgpl);
		
		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}
}
