package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.UINT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class NONCLIENTMETRICS extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public UINT cbSize;
    public int iBorderWidth;
    public int iScrollWidth;
    public int iScrollHeight;
    public int iCaptionWidth;
    public int iCaptionHeight;
    public LOGFONT lfCaptionFont;
    public int iSmCaptionWidth;
    public int iSmCaptionHeight;
    public LOGFONT lfSmCaptionFont;
    public int iMenuWidth;
    public int iMenuHeight;
    public LOGFONT lfMenuFont;
    public LOGFONT lfStatusFont;
    public LOGFONT lfMessageFont;
    public int iPaddedBorderWidth;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cbSize", "iBorderWidth", "iScrollWidth", "iScrollHeight", "iCaptionWidth", "iCaptionHeight", "lfCaptionFont", "iSmCaptionWidth", "iSmCaptionHeight", "lfSmCaptionFont", "iMenuWidth", "iMenuHeight", "lfMenuFont", "lfStatusFont", "lfMessageFont", "iPaddedBorderWidth" });
    }
}
