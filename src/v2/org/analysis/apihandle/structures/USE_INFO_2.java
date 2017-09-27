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
 
public  class USE_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public char ui2_local;
    public char ui2_remote;
    public char ui2_password;
    public DWORD ui2_status;
    public DWORD ui2_asg_type;
    public DWORD ui2_refcount;
    public DWORD ui2_usecount;
    public LPWSTR ui2_username;
    public char ui2_domainname;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "ui2_local", "ui2_remote", "ui2_password", "ui2_status", "ui2_asg_type", "ui2_refcount", "ui2_usecount", "ui2_username", "ui2_domainname" });
    }
}
