package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.PSID;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class USER_INFO_4 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri4_name;
    public LPWSTR usri4_password;
    public DWORD usri4_password_age;
    public DWORD usri4_priv;
    public LPWSTR usri4_home_dir;
    public LPWSTR usri4_comment;
    public DWORD usri4_flags;
    public LPWSTR usri4_script_path;
    public DWORD usri4_auth_flags;
    public LPWSTR usri4_full_name;
    public LPWSTR usri4_usr_comment;
    public LPWSTR usri4_parms;
    public LPWSTR usri4_workstations;
    public DWORD usri4_last_logon;
    public DWORD usri4_last_logoff;
    public DWORD usri4_acct_expires;
    public DWORD usri4_max_storage;
    public DWORD usri4_units_per_week;
    public byte usri4_logon_hours;
    public DWORD usri4_bad_pw_count;
    public DWORD usri4_num_logons;
    public LPWSTR usri4_logon_server;
    public DWORD usri4_country_code;
    public DWORD usri4_code_page;
    public PSID usri4_user_sid;
    public DWORD usri4_primary_group_id;
    public LPWSTR usri4_profile;
    public LPWSTR usri4_home_dir_drive;
    public DWORD usri4_password_expired;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri4_name", "usri4_password", "usri4_password_age", "usri4_priv", "usri4_home_dir", "usri4_comment", "usri4_flags", "usri4_script_path", "usri4_auth_flags", "usri4_full_name", "usri4_usr_comment", "usri4_parms", "usri4_workstations", "usri4_last_logon", "usri4_last_logoff", "usri4_acct_expires", "usri4_max_storage", "usri4_units_per_week", "usri4_logon_hours", "usri4_bad_pw_count", "usri4_num_logons", "usri4_logon_server", "usri4_country_code", "usri4_code_page", "usri4_user_sid", "usri4_primary_group_id", "usri4_profile", "usri4_home_dir_drive", "usri4_password_expired" });
    }
}
