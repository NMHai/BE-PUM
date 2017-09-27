package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WKSTA_INFO_101 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD wki101_platform_id;
    public char wki101_computername;
    public char wki101_langroup;
    public DWORD wki101_ver_major;
    public DWORD wki101_ver_minor;
    public char wki101_lanroot;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wki101_platform_id", "wki101_computername", "wki101_langroup", "wki101_ver_major", "wki101_ver_minor", "wki101_lanroot" });
    }
}
