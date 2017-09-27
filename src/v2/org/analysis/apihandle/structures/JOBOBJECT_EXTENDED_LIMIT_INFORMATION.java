package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class JOBOBJECT_EXTENDED_LIMIT_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public JOBOBJECT_BASIC_LIMIT_INFORMATION BasicLimitInformation;
    public IO_COUNTERS IoInfo;
    public SIZE_T ProcessMemoryLimit;
    public SIZE_T JobMemoryLimit;
    public SIZE_T PeakProcessMemoryUsed;
    public SIZE_T PeakJobMemoryUsed;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "BasicLimitInformation", "IoInfo", "ProcessMemoryLimit", "JobMemoryLimit", "PeakProcessMemoryUsed", "PeakJobMemoryUsed" });
    }
}
