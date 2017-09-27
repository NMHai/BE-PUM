package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.UINT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class NEWTEXTMETRIC extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LONG tmHeight;
    public LONG tmAscent;
    public LONG tmDescent;
    public LONG tmInternalLeading;
    public LONG tmExternalLeading;
    public LONG tmAveCharWidth;
    public LONG tmMaxCharWidth;
    public LONG tmWeight;
    public LONG tmOverhang;
    public LONG tmDigitizedAspectX;
    public LONG tmDigitizedAspectY;
    public char tmFirstChar;
    public char tmLastChar;
    public char tmDefaultChar;
    public char tmBreakChar;
    public BYTE tmItalic;
    public BYTE tmUnderlined;
    public BYTE tmStruckOut;
    public BYTE tmPitchAndFamily;
    public BYTE tmCharSet;
    public DWORD ntmFlags;
    public UINT ntmSizeEM;
    public UINT ntmCellHeight;
    public UINT ntmAvgWidth;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "tmHeight", "tmAscent", "tmDescent", "tmInternalLeading", "tmExternalLeading", "tmAveCharWidth", "tmMaxCharWidth", "tmWeight", "tmOverhang", "tmDigitizedAspectX", "tmDigitizedAspectY", "tmFirstChar", "tmLastChar", "tmDefaultChar", "tmBreakChar", "tmItalic", "tmUnderlined", "tmStruckOut", "tmPitchAndFamily", "tmCharSet", "ntmFlags", "ntmSizeEM", "ntmCellHeight", "ntmAvgWidth" });
    }
}
