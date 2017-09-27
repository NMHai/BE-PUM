package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PDH_COUNTER_INFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwLength;
    public DWORD dwType;
    public DWORD CVersion;
    public DWORD CStatus;
    public LONG lScale;
    public LONG lDefaultScale;
    public DWORD_PTR dwUserData;
    public DWORD_PTR dwQueryUserData;
    public String szFullPath;
    public PDH_COUNTER_INFO_U10 tmp10;
    public String szExplainText;
    public DWORD[] DataBuffer = new DWORD[1];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwLength", "dwType", "CVersion", "CStatus", "lScale", "lDefaultScale", "dwUserData", "dwQueryUserData", "szFullPath", "tmp10", "szExplainText", "DataBuffer" });
    }
}
