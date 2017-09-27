package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class DYNAMIC_TIME_ZONE_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LONG Bias;
    public char[] StandardName = new char[32];
    public SYSTEMTIME StandardDate;
    public LONG StandardBias;
    public char[] DaylightName = new char[32];
    public SYSTEMTIME DaylightDate;
    public LONG DaylightBias;
    public char[] TimeZoneKeyName = new char[128];
    public byte DynamicDaylightTimeDisabled;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Bias", "StandardName", "StandardDate", "StandardBias", "DaylightName", "DaylightDate", "DaylightBias", "TimeZoneKeyName", "DynamicDaylightTimeDisabled" });
    }
}
