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
 
public  class USER_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri2_name;
    public LPWSTR usri2_password;
    public DWORD usri2_password_age;
    public DWORD usri2_priv;
    public LPWSTR usri2_home_dir;
    public LPWSTR usri2_comment;
    public DWORD usri2_flags;
    public LPWSTR usri2_script_path;
    public DWORD usri2_auth_flags;
    public LPWSTR usri2_full_name;
    public LPWSTR usri2_usr_comment;
    public LPWSTR usri2_parms;
    public LPWSTR usri2_workstations;
    public DWORD usri2_last_logon;
    public DWORD usri2_last_logoff;
    public DWORD usri2_acct_expires;
    public DWORD usri2_max_storage;
    public DWORD usri2_units_per_week;
    public byte usri2_logon_hours;
    public DWORD usri2_bad_pw_count;
    public DWORD usri2_num_logons;
    public LPWSTR usri2_logon_server;
    public DWORD usri2_country_code;
    public DWORD usri2_code_page;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri2_name", "usri2_password", "usri2_password_age", "usri2_priv", "usri2_home_dir", "usri2_comment", "usri2_flags", "usri2_script_path", "usri2_auth_flags", "usri2_full_name", "usri2_usr_comment", "usri2_parms", "usri2_workstations", "usri2_last_logon", "usri2_last_logoff", "usri2_acct_expires", "usri2_max_storage", "usri2_units_per_week", "usri2_logon_hours", "usri2_bad_pw_count", "usri2_num_logons", "usri2_logon_server", "usri2_country_code", "usri2_code_page" });
    }
}
