package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WNODE_HEADER extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG BufferSize;
    public ULONG ProviderId;
    public WNODE_HEADER_U3 tmp3;
    public WNODE_HEADER_U4 tmp4;
    public GUID Guid;
    public ULONG ClientContext;
    public ULONG Flags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "BufferSize", "ProviderId", "tmp3", "tmp4", "Guid", "ClientContext", "Flags" });
    }
}
