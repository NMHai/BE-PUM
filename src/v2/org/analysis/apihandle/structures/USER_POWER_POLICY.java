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
 
public  class USER_POWER_POLICY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG Revision;
    public POWER_ACTION_POLICY IdleAc;
    public POWER_ACTION_POLICY IdleDc;
    public ULONG IdleTimeoutAc;
    public ULONG IdleTimeoutDc;
    public UCHAR IdleSensitivityAc;
    public UCHAR IdleSensitivityDc;
    public UCHAR ThrottlePolicyAc;
    public UCHAR ThrottlePolicyDc;
    public int MaxSleepAc;
    public int MaxSleepDc;
    public ULONG[] Reserved = new ULONG[2];
    public ULONG VideoTimeoutAc;
    public ULONG VideoTimeoutDc;
    public ULONG SpindownTimeoutAc;
    public ULONG SpindownTimeoutDc;
    public byte OptimizeForPowerAc;
    public byte OptimizeForPowerDc;
    public UCHAR FanThrottleToleranceAc;
    public UCHAR FanThrottleToleranceDc;
    public UCHAR ForcedThrottleAc;
    public UCHAR ForcedThrottleDc;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Revision", "IdleAc", "IdleDc", "IdleTimeoutAc", "IdleTimeoutDc", "IdleSensitivityAc", "IdleSensitivityDc", "ThrottlePolicyAc", "ThrottlePolicyDc", "MaxSleepAc", "MaxSleepDc", "Reserved", "VideoTimeoutAc", "VideoTimeoutDc", "SpindownTimeoutAc", "SpindownTimeoutDc", "OptimizeForPowerAc", "OptimizeForPowerDc", "FanThrottleToleranceAc", "FanThrottleToleranceDc", "ForcedThrottleAc", "ForcedThrottleDc" });
    }
}
