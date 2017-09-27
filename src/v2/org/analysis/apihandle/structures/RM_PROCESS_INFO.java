package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class RM_PROCESS_INFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public RM_UNIQUE_PROCESS Process;
    public char[] strAppName = new char[256];
    public char[] strServiceShortName = new char[64];
    public int ApplicationType;
    public ULONG AppStatus;
    public DWORD TSSessionId;
    public BOOL bRestartable;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Process", "strAppName", "strServiceShortName", "ApplicationType", "AppStatus", "TSSessionId", "bRestartable" });
    }
}
