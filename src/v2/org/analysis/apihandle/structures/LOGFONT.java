package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class LOGFONT extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LONG lfHeight;
    public LONG lfWidth;
    public LONG lfEscapement;
    public LONG lfOrientation;
    public LONG lfWeight;
    public BYTE lfItalic;
    public BYTE lfUnderline;
    public BYTE lfStrikeOut;
    public BYTE lfCharSet;
    public BYTE lfOutPrecision;
    public BYTE lfClipPrecision;
    public BYTE lfQuality;
    public BYTE lfPitchAndFamily;
    public char[] lfFaceName = new char[32];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "lfHeight", "lfWidth", "lfEscapement", "lfOrientation", "lfWeight", "lfItalic", "lfUnderline", "lfStrikeOut", "lfCharSet", "lfOutPrecision", "lfClipPrecision", "lfQuality", "lfPitchAndFamily", "lfFaceName" });
    }
}
