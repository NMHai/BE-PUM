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
 
public  class SESSION_INFO_502 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR sesi502_cname;
    public LPWSTR sesi502_username;
    public DWORD sesi502_num_opens;
    public DWORD sesi502_time;
    public DWORD sesi502_idle_time;
    public DWORD sesi502_user_flags;
    public LPWSTR sesi502_cltype_name;
    public LPWSTR sesi502_transport;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sesi502_cname", "sesi502_username", "sesi502_num_opens", "sesi502_time", "sesi502_idle_time", "sesi502_user_flags", "sesi502_cltype_name", "sesi502_transport" });
    }
}
