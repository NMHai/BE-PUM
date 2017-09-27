package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class THREADENTRY32 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwSize;
    public DWORD cntUsage;
    public DWORD th32ThreadID;
    public DWORD th32OwnerProcessID;
    public LONG tpBasePri;
    public LONG tpDeltaPri;
    public DWORD dwFlags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwSize", "cntUsage", "th32ThreadID", "th32OwnerProcessID", "tpBasePri", "tpDeltaPri", "dwFlags" });
    }
}
