package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.ULONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_TRANSPORT_INFO_3 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD svti3_numberofvcs;
    public char svti3_transportname;
    public byte svti3_transportaddress;
    public DWORD svti3_transportaddresslength;
    public char svti3_networkaddress;
    public char svti3_domain;
    public ULONG svti3_flags;
    public DWORD svti3_passwordlength;
    public BYTE[] svti3_password = new BYTE[256];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "svti3_numberofvcs", "svti3_transportname", "svti3_transportaddress", "svti3_transportaddresslength", "svti3_networkaddress", "svti3_domain", "svti3_flags", "svti3_passwordlength", "svti3_password" });
    }
}
