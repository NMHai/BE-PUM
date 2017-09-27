package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class BY_HANDLE_FILE_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwFileAttributes;
    public FILETIME ftCreationTime;
    public FILETIME ftLastAccessTime;
    public FILETIME ftLastWriteTime;
    public DWORD dwVolumeSerialNumber;
    public DWORD nFileSizeHigh;
    public DWORD nFileSizeLow;
    public DWORD nNumberOfLinks;
    public DWORD nFileIndexHigh;
    public DWORD nFileIndexLow;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwFileAttributes", "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime", "dwVolumeSerialNumber", "nFileSizeHigh", "nFileSizeLow", "nNumberOfLinks", "nFileIndexHigh", "nFileIndexLow" });
    }
}
