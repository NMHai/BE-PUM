package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinUser.SIZE;
import com.sun.jna.ptr.ByteByReference;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class FORM_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD Flags;
    public String pName;
    public SIZE Size;
    public RECTL ImageableArea;
    public ByteByReference pKeyword;
    public DWORD StringType;
    public String pMuiDll;
    public DWORD dwResourceId;
    public String pDisplayName;
    public short wLangId;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Flags", "pName", "Size", "ImageableArea", "pKeyword", "StringType", "pMuiDll", "dwResourceId", "pDisplayName", "wLangId" });
    }
}
