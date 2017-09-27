package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_TRANSPORT_INFO_1 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD svti1_numberofvcs;
    public char svti1_transportname;
    public byte svti1_transportaddress;
    public DWORD svti1_transportaddresslength;
    public char svti1_networkaddress;
    public char svti1_domain;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "svti1_numberofvcs", "svti1_transportname", "svti1_transportaddress", "svti1_transportaddresslength", "svti1_networkaddress", "svti1_domain" });
    }
}
