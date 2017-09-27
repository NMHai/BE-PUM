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
 
public  class SESSION_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR sesi2_cname;
    public LPWSTR sesi2_username;
    public DWORD sesi2_num_opens;
    public DWORD sesi2_time;
    public DWORD sesi2_idle_time;
    public DWORD sesi2_user_flags;
    public LPWSTR sesi2_cltype_name;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sesi2_cname", "sesi2_username", "sesi2_num_opens", "sesi2_time", "sesi2_idle_time", "sesi2_user_flags", "sesi2_cltype_name" });
    }
}
