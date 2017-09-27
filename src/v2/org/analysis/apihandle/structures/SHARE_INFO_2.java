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
 
public  class SHARE_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR shi2_netname;
    public DWORD shi2_type;
    public LPWSTR shi2_remark;
    public DWORD shi2_permissions;
    public DWORD shi2_max_uses;
    public DWORD shi2_current_uses;
    public LPWSTR shi2_path;
    public LPWSTR shi2_passwd;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "shi2_netname", "shi2_type", "shi2_remark", "shi2_permissions", "shi2_max_uses", "shi2_current_uses", "shi2_path", "shi2_passwd" });
    }
}
