package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class JOBOBJECT_BASIC_ACCOUNTING_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LARGE_INTEGER TotalUserTime;
    public LARGE_INTEGER TotalKernelTime;
    public LARGE_INTEGER ThisPeriodTotalUserTime;
    public LARGE_INTEGER ThisPeriodTotalKernelTime;
    public DWORD TotalPageFaultCount;
    public DWORD TotalProcesses;
    public DWORD ActiveProcesses;
    public DWORD TotalTerminatedProcesses;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "TotalUserTime", "TotalKernelTime", "ThisPeriodTotalUserTime", "ThisPeriodTotalKernelTime", "TotalPageFaultCount", "TotalProcesses", "ActiveProcesses", "TotalTerminatedProcesses" });
    }
}
