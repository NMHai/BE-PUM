package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class USER_INFO_22 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri22_name;
    public BYTE[] usri22_password = new BYTE[16];
    public DWORD usri22_password_age;
    public DWORD usri22_priv;
    public LPWSTR usri22_home_dir;
    public LPWSTR usri22_comment;
    public DWORD usri22_flags;
    public LPWSTR usri22_script_path;
    public DWORD usri22_auth_flags;
    public LPWSTR usri22_full_name;
    public LPWSTR usri22_usr_comment;
    public LPWSTR usri22_parms;
    public LPWSTR usri22_workstations;
    public DWORD usri22_last_logon;
    public DWORD usri22_last_logoff;
    public DWORD usri22_acct_expires;
    public DWORD usri22_max_storage;
    public DWORD usri22_units_per_week;
    public byte usri22_logon_hours;
    public DWORD usri22_bad_pw_count;
    public DWORD usri22_num_logons;
    public LPWSTR usri22_logon_server;
    public DWORD usri22_country_code;
    public DWORD usri22_code_page;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri22_name", "usri22_password", "usri22_password_age", "usri22_priv", "usri22_home_dir", "usri22_comment", "usri22_flags", "usri22_script_path", "usri22_auth_flags", "usri22_full_name", "usri22_usr_comment", "usri22_parms", "usri22_workstations", "usri22_last_logon", "usri22_last_logoff", "usri22_acct_expires", "usri22_max_storage", "usri22_units_per_week", "usri22_logon_hours", "usri22_bad_pw_count", "usri22_num_logons", "usri22_logon_server", "usri22_country_code", "usri22_code_page" });
    }
}
