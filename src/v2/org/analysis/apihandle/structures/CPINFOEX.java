package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.UINT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class CPINFOEX extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public UINT MaxCharSize;
    public BYTE[] DefaultChar = new BYTE[2];
    public BYTE[] LeadByte = new BYTE[12];
    public char UnicodeDefaultChar;
    public UINT CodePage;
    public char[] CodePageName = new char[260];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "MaxCharSize", "DefaultChar", "LeadByte", "UnicodeDefaultChar", "CodePage", "CodePageName" });
    }
}
