package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.UCHAR;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PROCESSOR_POWER_POLICY_INFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG TimeCheck;
    public ULONG DemoteLimit;
    public ULONG PromoteLimit;
    public UCHAR DemotePercent;
    public UCHAR PromotePercent;
    public UCHAR[] Spare = new UCHAR[2];
    public ULONG AllowDemotion;
    public ULONG AllowPromotion;
    public ULONG Reserved;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "TimeCheck", "DemoteLimit", "PromoteLimit", "DemotePercent", "PromotePercent", "Spare", "AllowDemotion", "AllowPromotion", "Reserved" });
    }
}
