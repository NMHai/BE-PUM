package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class NET_DISPLAY_USER extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri1_name;
    public LPWSTR usri1_comment;
    public DWORD usri1_flags;
    public LPWSTR usri1_full_name;
    public DWORD usri1_user_id;
    public DWORD usri1_next_index;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri1_name", "usri1_comment", "usri1_flags", "usri1_full_name", "usri1_user_id", "usri1_next_index" });
    }
}
