package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPSTR;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class GCP_RESULTS extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD lStructSize;
    public String lpOutString;
    public IntByReference lpOrder;
    public IntByReference lpDx;
    public IntByReference lpCaretPos;
    public LPSTR lpClass;
    public LPWSTR lpGlyphs;
    public UINT nGlyphs;
    public int nMaxFit;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "lStructSize", "lpOutString", "lpOrder", "lpDx", "lpCaretPos", "lpClass", "lpGlyphs", "nGlyphs", "nMaxFit" });
    }
}
