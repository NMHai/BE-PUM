package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.WORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class OFSTRUCT extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public BYTE cBytes;
    public BYTE fFixedDisk;
    public WORD nErrCode;
    public WORD Reserved1;
    public WORD Reserved2;
    public CHAR[] szPathName = new CHAR[128];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cBytes", "fFixedDisk", "nErrCode", "Reserved1", "Reserved2", "szPathName" });
    }
}
