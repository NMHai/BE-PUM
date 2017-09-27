package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class EMRALPHABLEND extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public EMR emr;
    public RECTL rclBounds;
    public LONG xDest;
    public LONG yDest;
    public LONG cxDest;
    public LONG cyDest;
    public DWORD dwRop;
    public LONG xSrc;
    public LONG ySrc;
    public XFORM xformSrc;
    public int crBkColorSrc;
    public DWORD iUsageSrc;
    public DWORD offBmiSrc;
    public DWORD cbBmiSrc;
    public DWORD offBitsSrc;
    public DWORD cbBitsSrc;
    public LONG cxSrc;
    public LONG cySrc;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "emr", "rclBounds", "xDest", "yDest", "cxDest", "cyDest", "dwRop", "xSrc", "ySrc", "xformSrc", "crBkColorSrc", "iUsageSrc", "offBmiSrc", "cbBmiSrc", "offBitsSrc", "cbBitsSrc", "cxSrc", "cySrc" });
    }
}
