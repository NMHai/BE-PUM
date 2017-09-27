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
 
public  class PERFORMANCE_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD cb;
    public SIZE_T CommitTotal;
    public SIZE_T CommitLimit;
    public SIZE_T CommitPeak;
    public SIZE_T PhysicalTotal;
    public SIZE_T PhysicalAvailable;
    public SIZE_T SystemCache;
    public SIZE_T KernelTotal;
    public SIZE_T KernelPaged;
    public SIZE_T KernelNonpaged;
    public SIZE_T PageSize;
    public DWORD HandleCount;
    public DWORD ProcessCount;
    public DWORD ThreadCount;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cb", "CommitTotal", "CommitLimit", "CommitPeak", "PhysicalTotal", "PhysicalAvailable", "SystemCache", "KernelTotal", "KernelPaged", "KernelNonpaged", "PageSize", "HandleCount", "ProcessCount", "ThreadCount" });
    }
}
