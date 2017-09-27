package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BYTE;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PROCESSOR_POWER_POLICY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD Revision;
    public BYTE DynamicThrottle;
    public BYTE[] Spare = new BYTE[3];
    public DWORD DisableCStates;
    public DWORD Reserved;
    public DWORD PolicyCount;
    public PROCESSOR_POWER_POLICY_INFO[] Policy = new PROCESSOR_POWER_POLICY_INFO[3];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Revision", "DynamicThrottle", "Spare", "DisableCStates", "Reserved", "PolicyCount", "Policy" });
    }
}
