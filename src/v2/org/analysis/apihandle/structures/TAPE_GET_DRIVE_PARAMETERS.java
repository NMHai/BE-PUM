package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class TAPE_GET_DRIVE_PARAMETERS extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public byte ECC;
    public byte Compression;
    public byte DataPadding;
    public byte ReportSetmarks;
    public DWORD DefaultBlockSize;
    public DWORD MaximumBlockSize;
    public DWORD MinimumBlockSize;
    public DWORD MaximumPartitionCount;
    public DWORD FeaturesLow;
    public DWORD FeaturesHigh;
    public DWORD EOTWarningZoneSize;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "ECC", "Compression", "DataPadding", "ReportSetmarks", "DefaultBlockSize", "MaximumBlockSize", "MinimumBlockSize", "MaximumPartitionCount", "FeaturesLow", "FeaturesHigh", "EOTWarningZoneSize" });
    }
}
