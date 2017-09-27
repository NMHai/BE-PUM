package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class NDDESHAREINFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LONG lRevision;
    public String lpszShareName;
    public LONG lShareType;
    public String lpszAppTopicList;
    public LONG fSharedFlag;
    public LONG fService;
    public LONG fStartAppFlag;
    public LONG nCmdShow;
    public LONG[] qModifyId = new LONG[2];
    public LONG cNumItems;
    public String lpszItemList;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "lRevision", "lpszShareName", "lShareType", "lpszAppTopicList", "fSharedFlag", "fService", "fStartAppFlag", "nCmdShow", "qModifyId", "cNumItems", "lpszItemList" });
    }
}
