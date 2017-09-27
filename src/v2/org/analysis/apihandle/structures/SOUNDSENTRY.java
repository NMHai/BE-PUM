package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SOUNDSENTRY extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public UINT cbSize;
    public DWORD dwFlags;
    public DWORD iFSTextEffect;
    public DWORD iFSTextEffectMSec;
    public DWORD iFSTextEffectColorBits;
    public DWORD iFSGrafEffect;
    public DWORD iFSGrafEffectMSec;
    public DWORD iFSGrafEffectColor;
    public DWORD iWindowsEffect;
    public DWORD iWindowsEffectMSec;
    public String lpszWindowsEffectDLL;
    public DWORD iWindowsEffectOrdinal;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cbSize", "dwFlags", "iFSTextEffect", "iFSTextEffectMSec", "iFSTextEffectColorBits", "iFSGrafEffect", "iFSGrafEffectMSec", "iFSGrafEffectColor", "iWindowsEffect", "iWindowsEffectMSec", "lpszWindowsEffectDLL", "iWindowsEffectOrdinal" });
    }
}
