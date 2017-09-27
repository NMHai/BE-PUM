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
 
public  class MACHINE_POWER_POLICY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG Revision;
    public int MinSleepAc;
    public int MinSleepDc;
    public int ReducedLatencySleepAc;
    public int ReducedLatencySleepDc;
    public ULONG DozeTimeoutAc;
    public ULONG DozeTimeoutDc;
    public ULONG DozeS4TimeoutAc;
    public ULONG DozeS4TimeoutDc;
    public UCHAR MinThrottleAc;
    public UCHAR MinThrottleDc;
    public UCHAR[] pad1 = new UCHAR[2];
    public POWER_ACTION_POLICY OverThrottledAc;
    public POWER_ACTION_POLICY OverThrottledDc;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Revision", "MinSleepAc", "MinSleepDc", "ReducedLatencySleepAc", "ReducedLatencySleepDc", "DozeTimeoutAc", "DozeTimeoutDc", "DozeS4TimeoutAc", "DozeS4TimeoutDc", "MinThrottleAc", "MinThrottleDc", "pad1", "OverThrottledAc", "OverThrottledDc" });
    }
}
