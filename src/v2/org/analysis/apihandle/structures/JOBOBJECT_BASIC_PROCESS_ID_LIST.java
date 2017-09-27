package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class JOBOBJECT_BASIC_PROCESS_ID_LIST extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD NumberOfAssignedProcesses;
    public DWORD NumberOfProcessIdsInList;
    public ULONG_PTR[] ProcessIdList = new ULONG_PTR[1];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "NumberOfAssignedProcesses", "NumberOfProcessIdsInList", "ProcessIdList" });
    }
}
