package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.UINT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class MENUITEMINFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public UINT cbSize;
    public UINT fMask;
    public UINT fType;
    public UINT fState;
    public UINT wID;
    public HMENU hSubMenu;
    public HBITMAP hbmpChecked;
    public HBITMAP hbmpUnchecked;
    public ULONG_PTR dwItemData;
    public String dwTypeData;
    public UINT cch;
    public HBITMAP hbmpItem;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cbSize", "fMask", "fType", "fState", "wID", "hSubMenu", "hbmpChecked", "hbmpUnchecked", "dwItemData", "dwTypeData", "cch", "hbmpItem" });
    }
}
