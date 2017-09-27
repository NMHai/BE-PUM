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
 
public  class USER_INFO_3 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri3_name;
    public LPWSTR usri3_password;
    public DWORD usri3_password_age;
    public DWORD usri3_priv;
    public LPWSTR usri3_home_dir;
    public LPWSTR usri3_comment;
    public DWORD usri3_flags;
    public LPWSTR usri3_script_path;
    public DWORD usri3_auth_flags;
    public LPWSTR usri3_full_name;
    public LPWSTR usri3_usr_comment;
    public LPWSTR usri3_parms;
    public LPWSTR usri3_workstations;
    public DWORD usri3_last_logon;
    public DWORD usri3_last_logoff;
    public DWORD usri3_acct_expires;
    public DWORD usri3_max_storage;
    public DWORD usri3_units_per_week;
    public byte usri3_logon_hours;
    public DWORD usri3_bad_pw_count;
    public DWORD usri3_num_logons;
    public LPWSTR usri3_logon_server;
    public DWORD usri3_country_code;
    public DWORD usri3_code_page;
    public DWORD usri3_user_id;
    public DWORD usri3_primary_group_id;
    public LPWSTR usri3_profile;
    public LPWSTR usri3_home_dir_drive;
    public DWORD usri3_password_expired;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri3_name", "usri3_password", "usri3_password_age", "usri3_priv", "usri3_home_dir", "usri3_comment", "usri3_flags", "usri3_script_path", "usri3_auth_flags", "usri3_full_name", "usri3_usr_comment", "usri3_parms", "usri3_workstations", "usri3_last_logon", "usri3_last_logoff", "usri3_acct_expires", "usri3_max_storage", "usri3_units_per_week", "usri3_logon_hours", "usri3_bad_pw_count", "usri3_num_logons", "usri3_logon_server", "usri3_country_code", "usri3_code_page", "usri3_user_id", "usri3_primary_group_id", "usri3_profile", "usri3_home_dir_drive", "usri3_password_expired" });
    }
}
