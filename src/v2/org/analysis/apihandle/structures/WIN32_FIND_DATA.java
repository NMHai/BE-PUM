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
 
public  class WIN32_FIND_DATA extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwFileAttributes;
    public FILETIME ftCreationTime;
    public FILETIME ftLastAccessTime;
    public FILETIME ftLastWriteTime;
    public DWORD nFileSizeHigh;
    public DWORD nFileSizeLow;
    public DWORD dwReserved0;
    public DWORD dwReserved1;
    public char[] cFileName = new char[260];
    public char[] cAlternateFileName = new char[14];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwFileAttributes", "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime", "nFileSizeHigh", "nFileSizeLow", "dwReserved0", "dwReserved1", "cFileName", "cAlternateFileName" });
    }
}
