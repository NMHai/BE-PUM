package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WER_REPORT_INFORMATION extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwSize;
    public HANDLE hProcess;
    public char[] wzConsentKey = new char[64];
    public char[] wzFriendlyEventName = new char[128];
    public char[] wzApplicationName = new char[128];
    public char[] wzApplicationPath = new char[260];
    public char[] wzDescription = new char[512];
    public HWND hwndParent;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwSize", "hProcess", "wzConsentKey", "wzFriendlyEventName", "wzApplicationName", "wzApplicationPath", "wzDescription", "hwndParent" });
    }
}
