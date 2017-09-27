package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WKSTA_INFO_100 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD wki100_platform_id;
    public char wki100_computername;
    public char wki100_langroup;
    public DWORD wki100_ver_major;
    public DWORD wki100_ver_minor;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wki100_platform_id", "wki100_computername", "wki100_langroup", "wki100_ver_major", "wki100_ver_minor" });
    }
}
