package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SHARE_INFO_503 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR shi503_netname;
    public DWORD shi503_type;
    public LPWSTR shi503_remark;
    public DWORD shi503_permissions;
    public DWORD shi503_max_uses;
    public DWORD shi503_current_uses;
    public LPWSTR shi503_path;
    public LPWSTR shi503_passwd;
    public LPWSTR shi503_servername;
    public DWORD shi503_reserved;
    public Pointer shi503_security_descriptor;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "shi503_netname", "shi503_type", "shi503_remark", "shi503_permissions", "shi503_max_uses", "shi503_current_uses", "shi503_path", "shi503_passwd", "shi503_servername", "shi503_reserved", "shi503_security_descriptor" });
    }
}
