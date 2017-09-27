package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinUser.SIZE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class ENHMETAHEADER extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD iType;
    public DWORD nSize;
    public RECTL rclBounds;
    public RECTL rclFrame;
    public DWORD dSignature;
    public DWORD nVersion;
    public DWORD nBytes;
    public DWORD nRecords;
    public WORD nHandles;
    public WORD sReserved;
    public DWORD nDescription;
    public DWORD offDescription;
    public DWORD nPalEntries;
    public SIZE szlDevice;
    public SIZE szlMillimeters;
    public DWORD cbPixelFormat;
    public DWORD offPixelFormat;
    public DWORD bOpenGL;
    public SIZE szlMicrometers;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "iType", "nSize", "rclBounds", "rclFrame", "dSignature", "nVersion", "nBytes", "nRecords", "nHandles", "sReserved", "nDescription", "offDescription", "nPalEntries", "szlDevice", "szlMillimeters", "cbPixelFormat", "offPixelFormat", "bOpenGL", "szlMicrometers" });
    }
}
