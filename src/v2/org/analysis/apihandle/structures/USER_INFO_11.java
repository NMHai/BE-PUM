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
 
public  class USER_INFO_11 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri11_name;
    public LPWSTR usri11_comment;
    public LPWSTR usri11_usr_comment;
    public LPWSTR usri11_full_name;
    public DWORD usri11_priv;
    public DWORD usri11_auth_flags;
    public DWORD usri11_password_age;
    public LPWSTR usri11_home_dir;
    public LPWSTR usri11_parms;
    public DWORD usri11_last_logon;
    public DWORD usri11_last_logoff;
    public DWORD usri11_bad_pw_count;
    public DWORD usri11_num_logons;
    public LPWSTR usri11_logon_server;
    public DWORD usri11_country_code;
    public LPWSTR usri11_workstations;
    public DWORD usri11_max_storage;
    public DWORD usri11_units_per_week;
    public byte usri11_logon_hours;
    public DWORD usri11_code_page;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri11_name", "usri11_comment", "usri11_usr_comment", "usri11_full_name", "usri11_priv", "usri11_auth_flags", "usri11_password_age", "usri11_home_dir", "usri11_parms", "usri11_last_logon", "usri11_last_logoff", "usri11_bad_pw_count", "usri11_num_logons", "usri11_logon_server", "usri11_country_code", "usri11_workstations", "usri11_max_storage", "usri11_units_per_week", "usri11_logon_hours", "usri11_code_page" });
    }
}
