package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.PSID;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class USER_INFO_24 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public BOOL usri24_internet_identity;
    public DWORD usri24_flags;
    public LPWSTR usri24_internet_provider_name;
    public LPWSTR usri24_internet_principal_name;
    public PSID usri24_user_sid;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri24_internet_identity", "usri24_flags", "usri24_internet_provider_name", "usri24_internet_principal_name", "usri24_user_sid" });
    }
}
