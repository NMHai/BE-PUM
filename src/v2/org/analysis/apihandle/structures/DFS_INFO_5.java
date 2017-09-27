package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class DFS_INFO_5 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR EntryPath;
    public LPWSTR Comment;
    public DWORD State;
    public ULONG Timeout;
    public GUID Guid;
    public ULONG PropertyFlags;
    public ULONG MetadataSize;
    public DWORD NumberOfStorages;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "EntryPath", "Comment", "State", "Timeout", "Guid", "PropertyFlags", "MetadataSize", "NumberOfStorages" });
    }
}
