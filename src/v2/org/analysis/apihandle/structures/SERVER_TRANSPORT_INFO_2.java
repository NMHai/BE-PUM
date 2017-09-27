package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_TRANSPORT_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD svti2_numberofvcs;
    public char svti2_transportname;
    public byte svti2_transportaddress;
    public DWORD svti2_transportaddresslength;
    public char svti2_networkaddress;
    public char svti2_domain;
    public ULONG svti2_flags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "svti2_numberofvcs", "svti2_transportname", "svti2_transportaddress", "svti2_transportaddresslength", "svti2_networkaddress", "svti2_domain", "svti2_flags" });
    }
}
