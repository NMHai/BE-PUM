package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVICE_TRIGGER extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwTriggerType;
    public DWORD dwAction;
    public GUID pTriggerSubtype;
    public DWORD cDataItems;
    public SERVICE_TRIGGER_SPECIFIC_DATA_ITEM pDataItems;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwTriggerType", "dwAction", "pTriggerSubtype", "cDataItems", "pDataItems" });
    }
}
