package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class NETINFOSTRUCT extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD cbStructure;
    public DWORD dwProviderVersion;
    public DWORD dwStatus;
    public DWORD dwCharacteristics;
    public ULONG_PTR dwHandle;
    public WORD wNetType;
    public DWORD dwPrinters;
    public DWORD dwDrives;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cbStructure", "dwProviderVersion", "dwStatus", "dwCharacteristics", "dwHandle", "wNetType", "dwPrinters", "dwDrives" });
    }
}
