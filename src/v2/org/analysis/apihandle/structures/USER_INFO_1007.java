package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class USER_INFO_1007 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LPWSTR usri1007_comment;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usri1007_comment" });
    }
}
