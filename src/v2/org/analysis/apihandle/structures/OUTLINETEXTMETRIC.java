package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.ByteByReference;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class OUTLINETEXTMETRIC extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public UINT otmSize;
    public TEXTMETRIC otmTextMetrics;
    public BYTE otmFiller;
    public PANOSE otmPanoseNumber;
    public UINT otmfsSelection;
    public UINT otmfsType;
    public int otmsCharSlopeRise;
    public int otmsCharSlopeRun;
    public int otmItalicAngle;
    public UINT otmEMSquare;
    public int otmAscent;
    public int otmDescent;
    public UINT otmLineGap;
    public UINT otmsCapEmHeight;
    public UINT otmsXHeight;
    public RECT otmrcFontBox;
    public int otmMacAscent;
    public int otmMacDescent;
    public UINT otmMacLineGap;
    public UINT otmusMinimumPPEM;
    public POINT otmptSubscriptSize;
    public POINT otmptSubscriptOffset;
    public POINT otmptSuperscriptSize;
    public POINT otmptSuperscriptOffset;
    public UINT otmsStrikeoutSize;
    public int otmsStrikeoutPosition;
    public int otmsUnderscoreSize;
    public int otmsUnderscorePosition;
    public ByteByReference otmpFamilyName;
    public ByteByReference otmpFaceName;
    public ByteByReference otmpStyleName;
    public ByteByReference otmpFullName;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "otmSize", "otmTextMetrics", "otmFiller", "otmPanoseNumber", "otmfsSelection", "otmfsType", "otmsCharSlopeRise", "otmsCharSlopeRun", "otmItalicAngle", "otmEMSquare", "otmAscent", "otmDescent", "otmLineGap", "otmsCapEmHeight", "otmsXHeight", "otmrcFontBox", "otmMacAscent", "otmMacDescent", "otmMacLineGap", "otmusMinimumPPEM", "otmptSubscriptSize", "otmptSubscriptOffset", "otmptSuperscriptSize", "otmptSuperscriptOffset", "otmsStrikeoutSize", "otmsStrikeoutPosition", "otmsUnderscoreSize", "otmsUnderscorePosition", "otmpFamilyName", "otmpFaceName", "otmpStyleName", "otmpFullName" });
    }
}
