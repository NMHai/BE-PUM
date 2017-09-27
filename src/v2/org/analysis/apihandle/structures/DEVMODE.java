package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class DEVMODE extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public char[] dmDeviceName = new char[32];
    public WORD dmSpecVersion;
    public WORD dmDriverVersion;
    public WORD dmSize;
    public WORD dmDriverExtra;
    public DWORD dmFields;
    public DEVMODE_U7 tmp7;
    public short dmColor;
    public short dmDuplex;
    public short dmYResolution;
    public short dmTTOption;
    public short dmCollate;
    public char[] dmFormName = new char[32];
    public WORD dmLogPixels;
    public DWORD dmBitsPerPel;
    public DWORD dmPelsWidth;
    public DWORD dmPelsHeight;
    public DEVMODE_U18 tmp18;
    public DWORD dmDisplayFrequency;
    public DWORD dmICMMethod;
    public DWORD dmICMIntent;
    public DWORD dmMediaType;
    public DWORD dmDitherType;
    public DWORD dmReserved1;
    public DWORD dmReserved2;
    public DWORD dmPanningWidth;
    public DWORD dmPanningHeight;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dmDeviceName", "dmSpecVersion", "dmDriverVersion", "dmSize", "dmDriverExtra", "dmFields", "tmp7", "dmColor", "dmDuplex", "dmYResolution", "dmTTOption", "dmCollate", "dmFormName", "dmLogPixels", "dmBitsPerPel", "dmPelsWidth", "dmPelsHeight", "tmp18", "dmDisplayFrequency", "dmICMMethod", "dmICMIntent", "dmMediaType", "dmDitherType", "dmReserved1", "dmReserved2", "dmPanningWidth", "dmPanningHeight" });
    }
}
