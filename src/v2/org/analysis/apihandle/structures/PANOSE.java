package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PANOSE extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public BYTE bFamilyType;
    public BYTE bSerifStyle;
    public BYTE bWeight;
    public BYTE bProportion;
    public BYTE bContrast;
    public BYTE bStrokeVariation;
    public BYTE bArmStyle;
    public BYTE bLetterform;
    public BYTE bMidline;
    public BYTE bXHeight;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "bFamilyType", "bSerifStyle", "bWeight", "bProportion", "bContrast", "bStrokeVariation", "bArmStyle", "bLetterform", "bMidline", "bXHeight" });
    }
}
