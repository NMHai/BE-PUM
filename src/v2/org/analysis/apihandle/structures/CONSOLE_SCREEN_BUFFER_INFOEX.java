package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.WORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class CONSOLE_SCREEN_BUFFER_INFOEX extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG cbSize;
    public COORD dwSize;
    public COORD dwCursorPosition;
    public WORD wAttributes;
    public SMALL_RECT srWindow;
    public COORD dwMaximumWindowSize;
    public WORD wPopupAttributes;
    public BOOL bFullscreenSupported;
    public int[] ColorTable = new int[16];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cbSize", "dwSize", "dwCursorPosition", "wAttributes", "srWindow", "dwMaximumWindowSize", "wPopupAttributes", "bFullscreenSupported", "ColorTable" });
    }
}
