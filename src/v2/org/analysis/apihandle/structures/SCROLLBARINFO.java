package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.RECT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SCROLLBARINFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD cbSize;
    public RECT rcScrollBar;
    public int dxyLineButton;
    public int xyThumbTop;
    public int xyThumbBottom;
    public int reserved;
    public DWORD[] rgstate = new DWORD[6];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cbSize", "rcScrollBar", "dxyLineButton", "xyThumbTop", "xyThumbBottom", "reserved", "rgstate" });
    }
}
