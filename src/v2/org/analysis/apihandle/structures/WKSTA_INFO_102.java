package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WKSTA_INFO_102 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD wki102_platform_id;
    public char wki102_computername;
    public char wki102_langroup;
    public DWORD wki102_ver_major;
    public DWORD wki102_ver_minor;
    public char wki102_lanroot;
    public DWORD wki102_logged_on_users;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wki102_platform_id", "wki102_computername", "wki102_langroup", "wki102_ver_major", "wki102_ver_minor", "wki102_lanroot", "wki102_logged_on_users" });
    }
}
