package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.TOKEN_GROUPS;
import com.sun.jna.platform.win32.WinNT.TOKEN_PRIVILEGES;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class JOBOBJECT_SECURITY_LIMIT_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD SecurityLimitFlags;
    public HANDLE JobToken;
    public TOKEN_GROUPS SidsToDisable;
    public TOKEN_PRIVILEGES PrivilegesToDelete;
    public TOKEN_GROUPS RestrictedSids;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "SecurityLimitFlags", "JobToken", "SidsToDisable", "PrivilegesToDelete", "RestrictedSids" });
    }
}
