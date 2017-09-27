package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class LDT_ENTRY_U3_S2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD BaseMid;
    public DWORD Type;
    public DWORD Dpl;
    public DWORD Pres;
    public DWORD LimitHi;
    public DWORD Sys;
    public DWORD Reserved_0;
    public DWORD Default_Big;
    public DWORD Granularity;
    public DWORD BaseHi;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "BaseMid", "Type", "Dpl", "Pres", "LimitHi", "Sys", "Reserved_0", "Default_Big", "Granularity", "BaseHi" });
    }
}
