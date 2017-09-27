package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_TRANSPORT_INFO_0 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD svti0_numberofvcs;
    public char svti0_transportname;
    public byte svti0_transportaddress;
    public DWORD svti0_transportaddresslength;
    public char svti0_networkaddress;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "svti0_numberofvcs", "svti0_transportname", "svti0_transportaddress", "svti0_transportaddresslength", "svti0_networkaddress" });
    }
}
