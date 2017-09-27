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
 
public  class SYSTEM_POWER_POLICY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG Revision;
    public POWER_ACTION_POLICY PowerButton;
    public POWER_ACTION_POLICY SleepButton;
    public POWER_ACTION_POLICY LidClose;
    public int LidOpenWake;
    public ULONG Reserved;
    public POWER_ACTION_POLICY Idle;
    public ULONG IdleTimeout;
    public UCHAR IdleSensitivity;
    public UCHAR DynamicThrottle;
    public UCHAR[] Spare2 = new UCHAR[2];
    public int MinSleep;
    public int MaxSleep;
    public int ReducedLatencySleep;
    public ULONG WinLogonFlags;
    public ULONG Spare3;
    public ULONG DozeS4Timeout;
    public ULONG BroadcastCapacityResolution;
    public SYSTEM_POWER_LEVEL[] DischargePolicy = new SYSTEM_POWER_LEVEL[14];
    public ULONG VideoTimeout;
    public byte VideoDimDisplay;
    public ULONG[] VideoReserved = new ULONG[3];
    public ULONG SpindownTimeout;
    public byte OptimizeForPower;
    public UCHAR FanThrottleTolerance;
    public UCHAR ForcedThrottle;
    public UCHAR MinThrottle;
    public POWER_ACTION_POLICY OverThrottled;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Revision", "PowerButton", "SleepButton", "LidClose", "LidOpenWake", "Reserved", "Idle", "IdleTimeout", "IdleSensitivity", "DynamicThrottle", "Spare2", "MinSleep", "MaxSleep", "ReducedLatencySleep", "WinLogonFlags", "Spare3", "DozeS4Timeout", "BroadcastCapacityResolution", "DischargePolicy", "VideoTimeout", "VideoDimDisplay", "VideoReserved", "SpindownTimeout", "OptimizeForPower", "FanThrottleTolerance", "ForcedThrottle", "MinThrottle", "OverThrottled" });
    }
}
