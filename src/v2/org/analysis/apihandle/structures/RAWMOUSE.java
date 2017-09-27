package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.USHORT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class RAWMOUSE extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public USHORT usFlags;
    public RAWMOUSE_U2 tmp2;
    public ULONG ulRawButtons;
    public LONG lLastX;
    public LONG lLastY;
    public ULONG ulExtraInformation;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "usFlags", "tmp2", "ulRawButtons", "lLastX", "lLastY", "ulExtraInformation" });
    }
}
