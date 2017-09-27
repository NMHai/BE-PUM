package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.ULONGLONG;
import com.sun.jna.platform.win32.WinDef.USHORT;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class EVENT_INSTANCE_HEADER extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public USHORT Size;
    public EVENT_INSTANCE_HEADER_U2 tmp2;
    public EVENT_INSTANCE_HEADER_U3 tmp3;
    public ULONG ThreadId;
    public ULONG ProcessId;
    public LARGE_INTEGER TimeStamp;
    public ULONGLONG RegHandle;
    public ULONG InstanceId;
    public ULONG ParentInstanceId;
    public EVENT_INSTANCE_HEADER_U10 tmp10;
    public ULONGLONG ParentRegHandle;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Size", "tmp2", "tmp3", "ThreadId", "ProcessId", "TimeStamp", "RegHandle", "InstanceId", "ParentInstanceId", "tmp10", "ParentRegHandle" });
    }
}
