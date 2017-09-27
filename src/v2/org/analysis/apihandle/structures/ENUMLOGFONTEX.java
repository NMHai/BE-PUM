package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class ENUMLOGFONTEX extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LOGFONT elfLogFont;
    public char[] elfFullName = new char[64];
    public char[] elfStyle = new char[32];
    public char[] elfScript = new char[32];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "elfLogFont", "elfFullName", "elfStyle", "elfScript" });
    }
}
