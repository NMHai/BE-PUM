package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class CONNECTION_INFO_1 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD coni1_id;
    public DWORD coni1_type;
    public DWORD coni1_num_opens;
    public DWORD coni1_num_users;
    public DWORD coni1_time;
    public char coni1_username;
    public char coni1_netname;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "coni1_id", "coni1_type", "coni1_num_opens", "coni1_num_users", "coni1_time", "coni1_username", "coni1_netname" });
    }
}
