package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.ptr.ShortByReference;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PRINTER_NOTIFY_OPTIONS_TYPE extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public WORD Type;
    public WORD Reserved0;
    public DWORD Reserved1;
    public DWORD Reserved2;
    public DWORD Count;
    public ShortByReference pFields;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Type", "Reserved0", "Reserved1", "Reserved2", "Count", "pFields" });
    }
}
