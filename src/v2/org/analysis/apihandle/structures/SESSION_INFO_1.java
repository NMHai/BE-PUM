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
 
public  class SESSION_INFO_1 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR sesi1_cname;
    public LPWSTR sesi1_username;
    public DWORD sesi1_num_opens;
    public DWORD sesi1_time;
    public DWORD sesi1_idle_time;
    public DWORD sesi1_user_flags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sesi1_cname", "sesi1_username", "sesi1_num_opens", "sesi1_time", "sesi1_idle_time", "sesi1_user_flags" });
    }
}
