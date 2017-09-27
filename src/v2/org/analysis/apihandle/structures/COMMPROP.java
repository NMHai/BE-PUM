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
 
public  class COMMPROP extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public WORD wPacketLength;
    public WORD wPacketVersion;
    public DWORD dwServiceMask;
    public DWORD dwReserved1;
    public DWORD dwMaxTxQueue;
    public DWORD dwMaxRxQueue;
    public DWORD dwMaxBaud;
    public DWORD dwProvSubType;
    public DWORD dwProvCapabilities;
    public DWORD dwSettableParams;
    public DWORD dwSettableBaud;
    public WORD wSettableData;
    public WORD wSettableStopParity;
    public DWORD dwCurrentTxQueue;
    public DWORD dwCurrentRxQueue;
    public DWORD dwProvSpec1;
    public DWORD dwProvSpec2;
    public char[] wcProvChar = new char[1];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wPacketLength", "wPacketVersion", "dwServiceMask", "dwReserved1", "dwMaxTxQueue", "dwMaxRxQueue", "dwMaxBaud", "dwProvSubType", "dwProvCapabilities", "dwSettableParams", "dwSettableBaud", "wSettableData", "wSettableStopParity", "dwCurrentTxQueue", "dwCurrentRxQueue", "dwProvSpec1", "dwProvSpec2", "wcProvChar" });
    }
}
