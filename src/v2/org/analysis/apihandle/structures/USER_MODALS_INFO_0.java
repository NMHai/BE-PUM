package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class USER_MODALS_INFO_0 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD usrmod0_min_passwd_len;
    public DWORD usrmod0_max_passwd_age;
    public DWORD usrmod0_min_passwd_age;
    public DWORD usrmod0_force_logoff;
    public DWORD usrmod0_password_hist_len;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usrmod0_min_passwd_len", "usrmod0_max_passwd_age", "usrmod0_min_passwd_age", "usrmod0_force_logoff", "usrmod0_password_hist_len" });
    }
}
