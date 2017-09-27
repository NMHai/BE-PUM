package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.PVOID;
import com.sun.jna.platform.win32.WinDef.WORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PROCESS_HEAP_ENTRY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public PVOID lpData;
    public DWORD cbData;
    public BYTE cbOverhead;
    public BYTE iRegionIndex;
    public WORD wFlags;
    public PROCESS_HEAP_ENTRY_U6 tmp6;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "lpData", "cbData", "cbOverhead", "iRegionIndex", "wFlags", "tmp6" });
    }
}
