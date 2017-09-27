package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class GLOBAL_USER_POWER_POLICY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG Revision;
    public POWER_ACTION_POLICY PowerButtonAc;
    public POWER_ACTION_POLICY PowerButtonDc;
    public POWER_ACTION_POLICY SleepButtonAc;
    public POWER_ACTION_POLICY SleepButtonDc;
    public POWER_ACTION_POLICY LidCloseAc;
    public POWER_ACTION_POLICY LidCloseDc;
    public SYSTEM_POWER_LEVEL[] DischargePolicy = new SYSTEM_POWER_LEVEL[14];
    public ULONG GlobalFlags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Revision", "PowerButtonAc", "PowerButtonDc", "SleepButtonAc", "SleepButtonDc", "LidCloseAc", "LidCloseDc", "DischargePolicy", "GlobalFlags" });
    }
}
