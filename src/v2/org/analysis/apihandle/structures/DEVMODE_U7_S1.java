package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class DEVMODE_U7_S1 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public short dmOrientation;
    public short dmPaperSize;
    public short dmPaperLength;
    public short dmPaperWidth;
    public short dmScale;
    public short dmCopies;
    public short dmDefaultSource;
    public short dmPrintQuality;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dmOrientation", "dmPaperSize", "dmPaperLength", "dmPaperWidth", "dmScale", "dmCopies", "dmDefaultSource", "dmPrintQuality" });
    }
}
