package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class USE_INFO_1 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public char ui1_local;
    public char ui1_remote;
    public char ui1_password;
    public DWORD ui1_status;
    public DWORD ui1_asg_type;
    public DWORD ui1_refcount;
    public DWORD ui1_usecount;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "ui1_local", "ui1_remote", "ui1_password", "ui1_status", "ui1_asg_type", "ui1_refcount", "ui1_usecount" });
    }
}
