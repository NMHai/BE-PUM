package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SYSTEM_BATTERY_STATE extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public byte AcOnLine;
    public byte BatteryPresent;
    public byte Charging;
    public byte Discharging;
    public byte[] Spare1 = new byte[4];
    public DWORD MaxCapacity;
    public DWORD RemainingCapacity;
    public DWORD Rate;
    public DWORD EstimatedTime;
    public DWORD DefaultAlert1;
    public DWORD DefaultAlert2;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "AcOnLine", "BatteryPresent", "Charging", "Discharging", "Spare1", "MaxCapacity", "RemainingCapacity", "Rate", "EstimatedTime", "DefaultAlert1", "DefaultAlert2" });
    }
}
