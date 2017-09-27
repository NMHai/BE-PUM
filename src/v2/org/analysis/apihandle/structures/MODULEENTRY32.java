package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class MODULEENTRY32 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwSize;
    public DWORD th32ModuleID;
    public DWORD th32ProcessID;
    public DWORD GlblcntUsage;
    public DWORD ProccntUsage;
    public byte modBaseAddr;
    public DWORD modBaseSize;
    public HMODULE hModule;
    public char[] szModule = new char[256];
    public char[] szExePath = new char[260];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwSize", "th32ModuleID", "th32ProcessID", "GlblcntUsage", "ProccntUsage", "modBaseAddr", "modBaseSize", "hModule", "szModule", "szExePath" });
    }
}
