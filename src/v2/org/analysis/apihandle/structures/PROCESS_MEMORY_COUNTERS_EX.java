package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PROCESS_MEMORY_COUNTERS_EX extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD cb;
    public DWORD PageFaultCount;
    public SIZE_T PeakWorkingSetSize;
    public SIZE_T WorkingSetSize;
    public SIZE_T QuotaPeakPagedPoolUsage;
    public SIZE_T QuotaPagedPoolUsage;
    public SIZE_T QuotaPeakNonPagedPoolUsage;
    public SIZE_T QuotaNonPagedPoolUsage;
    public SIZE_T PagefileUsage;
    public SIZE_T PeakPagefileUsage;
    public SIZE_T PrivateUsage;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cb", "PageFaultCount", "PeakWorkingSetSize", "WorkingSetSize", "QuotaPeakPagedPoolUsage", "QuotaPagedPoolUsage", "QuotaPeakNonPagedPoolUsage", "QuotaNonPagedPoolUsage", "PagefileUsage", "PeakPagefileUsage", "PrivateUsage" });
    }
}
