package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.UCHAR;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class VIDOEPARAMETERS extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public GUID guid;
    public ULONG dwOffset;
    public ULONG dwCommand;
    public ULONG dwFlags;
    public ULONG dwMode;
    public ULONG dwTVStandard;
    public ULONG dwAvailableModes;
    public ULONG dwAvailableTVStandard;
    public ULONG dwFlickerFilter;
    public ULONG dwOverScanX;
    public ULONG dwOverScanY;
    public ULONG dwMaxUnscaledX;
    public ULONG dwMaxUnscaledY;
    public ULONG dwPositionX;
    public ULONG dwPositionY;
    public ULONG dwBrightness;
    public ULONG dwContrast;
    public ULONG dwCPType;
    public ULONG dwCPCommand;
    public ULONG dwCPStandard;
    public ULONG dwCPKey;
    public ULONG bCP_APSTriggerBits;
    public UCHAR[] bOEMCopyProtection = new UCHAR[256];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "guid", "dwOffset", "dwCommand", "dwFlags", "dwMode", "dwTVStandard", "dwAvailableModes", "dwAvailableTVStandard", "dwFlickerFilter", "dwOverScanX", "dwOverScanY", "dwMaxUnscaledX", "dwMaxUnscaledY", "dwPositionX", "dwPositionY", "dwBrightness", "dwContrast", "dwCPType", "dwCPCommand", "dwCPStandard", "dwCPKey", "bCP_APSTriggerBits", "bOEMCopyProtection" });
    }
}
