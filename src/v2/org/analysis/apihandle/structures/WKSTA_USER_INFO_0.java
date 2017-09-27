package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WKSTA_USER_INFO_0 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public char wkui0_username;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wkui0_username" });
    }
}
