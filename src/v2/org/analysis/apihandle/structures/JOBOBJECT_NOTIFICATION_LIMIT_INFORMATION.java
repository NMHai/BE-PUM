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
 
public  class JOBOBJECT_NOTIFICATION_LIMIT_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public long IoReadBytesLimit;
    public long IoWriteBytesLimit;
    public LARGE_INTEGER PerJobUserTimeLimit;
    public long JobMemoryLimit;
    public int RateControlTolerance;
    public int RateControlToleranceInterval;
    public DWORD LimitFlags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "IoReadBytesLimit", "IoWriteBytesLimit", "PerJobUserTimeLimit", "JobMemoryLimit", "RateControlTolerance", "RateControlToleranceInterval", "LimitFlags" });
    }
}
