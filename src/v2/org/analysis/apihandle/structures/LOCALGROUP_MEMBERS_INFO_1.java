package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinNT.PSID;
import com.sun.jna.platform.win32.WinNT.SID_NAME_USE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class LOCALGROUP_MEMBERS_INFO_1 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public PSID lgrmi1_sid;
    public SID_NAME_USE lgrmi1_sidusage;
    public LPWSTR lgrmi1_name;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "lgrmi1_sid", "lgrmi1_sidusage", "lgrmi1_name" });
    }
}
