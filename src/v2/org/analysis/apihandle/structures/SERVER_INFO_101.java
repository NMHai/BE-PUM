package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_INFO_101 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD sv101_platform_id;
    public LPWSTR sv101_name;
    public DWORD sv101_version_major;
    public DWORD sv101_version_minor;
    public DWORD sv101_type;
    public LPWSTR sv101_comment;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sv101_platform_id", "sv101_name", "sv101_version_major", "sv101_version_minor", "sv101_type", "sv101_comment" });
    }
}