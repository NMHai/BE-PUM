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
 
public  class NET_DISPLAY_GROUP extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR grpi3_name;
    public LPWSTR grpi3_comment;
    public DWORD grpi3_group_id;
    public DWORD grpi3_attributes;
    public DWORD grpi3_next_index;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "grpi3_name", "grpi3_comment", "grpi3_group_id", "grpi3_attributes", "grpi3_next_index" });
    }
}
