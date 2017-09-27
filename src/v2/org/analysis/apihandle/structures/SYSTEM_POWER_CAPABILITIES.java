package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SYSTEM_POWER_CAPABILITIES extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public byte PowerButtonPresent;
    public byte SleepButtonPresent;
    public byte LidPresent;
    public byte SystemS1;
    public byte SystemS2;
    public byte SystemS3;
    public byte SystemS4;
    public byte SystemS5;
    public byte HiberFilePresent;
    public byte FullWake;
    public byte VideoDimPresent;
    public byte ApmPresent;
    public byte UpsPresent;
    public byte ThermalControl;
    public byte ProcessorThrottle;
    public BYTE ProcessorMinThrottle;
    public BYTE ProcessorMaxThrottle;
    public byte FastSystemS4;
    public byte HiberBoot;
    public byte WakeAlarmPresent;
    public byte AoAc;
    public byte DiskSpinDown;
    public BYTE[] spare3 = new BYTE[8];
    public byte SystemBatteriesPresent;
    public byte BatteriesAreShortTerm;
    public BATTERY_REPORTING_SCALE[] BatteryScale = new BATTERY_REPORTING_SCALE[3];
    public int AcOnLineWake;
    public int SoftLidWake;
    public int RtcWake;
    public int MinDeviceWakeState;
    public int DefaultLowLatencyWake;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "PowerButtonPresent", "SleepButtonPresent", "LidPresent", "SystemS1", "SystemS2", "SystemS3", "SystemS4", "SystemS5", "HiberFilePresent", "FullWake", "VideoDimPresent", "ApmPresent", "UpsPresent", "ThermalControl", "ProcessorThrottle", "ProcessorMinThrottle", "ProcessorMaxThrottle", "FastSystemS4", "HiberBoot", "WakeAlarmPresent", "AoAc", "DiskSpinDown", "spare3", "SystemBatteriesPresent", "BatteriesAreShortTerm", "BatteryScale", "AcOnLineWake", "SoftLidWake", "RtcWake", "MinDeviceWakeState", "DefaultLowLatencyWake" });
    }
}
