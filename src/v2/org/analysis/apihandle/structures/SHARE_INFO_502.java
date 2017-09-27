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
 
public  class SHARE_INFO_502 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR shi502_netname;
    public DWORD shi502_type;
    public LPWSTR shi502_remark;
    public DWORD shi502_permissions;
    public DWORD shi502_max_uses;
    public DWORD shi502_current_uses;
    public LPWSTR shi502_path;
    public LPWSTR shi502_passwd;
    public DWORD shi502_reserved;
    public Pointer shi502_security_descriptor;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "shi502_netname", "shi502_type", "shi502_remark", "shi502_permissions", "shi502_max_uses", "shi502_current_uses", "shi502_path", "shi502_passwd", "shi502_reserved", "shi502_security_descriptor" });
    }
}
