package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WKSTA_TRANSPORT_INFO_0 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD wkti0_quality_of_service;
    public DWORD wkti0_number_of_vcs;
    public LPWSTR wkti0_transport_name;
    public char wkti0_transport_address;
    public BOOL wkti0_wan_ish;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wkti0_quality_of_service", "wkti0_number_of_vcs", "wkti0_transport_name", "wkti0_transport_address", "wkti0_wan_ish" });
    }
}
