package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_INFO_102 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD sv102_platform_id;
    public LPWSTR sv102_name;
    public DWORD sv102_version_major;
    public DWORD sv102_version_minor;
    public DWORD sv102_type;
    public LPWSTR sv102_comment;
    public DWORD sv102_users;
    public LONG sv102_disc;
    public BOOL sv102_hidden;
    public DWORD sv102_announce;
    public DWORD sv102_anndelta;
    public DWORD sv102_licenses;
    public LPWSTR sv102_userpath;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sv102_platform_id", "sv102_name", "sv102_version_major", "sv102_version_minor", "sv102_type", "sv102_comment", "sv102_users", "sv102_disc", "sv102_hidden", "sv102_announce", "sv102_anndelta", "sv102_licenses", "sv102_userpath" });
    }
}
