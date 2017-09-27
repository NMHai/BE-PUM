package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.SHORT;
import com.sun.jna.platform.win32.WinDef.WORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class COLORADJUSTMENT extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public WORD caSize;
    public WORD caFlags;
    public WORD caIlluminantIndex;
    public WORD caRedGamma;
    public WORD caGreenGamma;
    public WORD caBlueGamma;
    public WORD caReferenceBlack;
    public WORD caReferenceWhite;
    public SHORT caContrast;
    public SHORT caBrightness;
    public SHORT caColorfulness;
    public SHORT caRedGreenTint;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "caSize", "caFlags", "caIlluminantIndex", "caRedGamma", "caGreenGamma", "caBlueGamma", "caReferenceBlack", "caReferenceWhite", "caContrast", "caBrightness", "caColorfulness", "caRedGreenTint" });
    }
}
