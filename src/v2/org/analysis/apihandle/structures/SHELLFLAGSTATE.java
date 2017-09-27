package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SHELLFLAGSTATE extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public BOOL fShowAllObjects;
    public BOOL fShowExtensions;
    public BOOL fNoConfirmRecycle;
    public BOOL fShowSysFiles;
    public BOOL fShowCompColor;
    public BOOL fDoubleClickInWebView;
    public BOOL fDesktopHTML;
    public BOOL fWin95Classic;
    public BOOL fDontPrettyPath;
    public BOOL fShowAttribCol;
    public BOOL fMapNetDrvBtn;
    public BOOL fShowInfoTip;
    public BOOL fHideIcons;
    public BOOL fAutoCheckSelect;
    public BOOL fIconsOnly;
    public UINT fRestFlags;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "fShowAllObjects", "fShowExtensions", "fNoConfirmRecycle", "fShowSysFiles", "fShowCompColor", "fDoubleClickInWebView", "fDesktopHTML", "fWin95Classic", "fDontPrettyPath", "fShowAttribCol", "fMapNetDrvBtn", "fShowInfoTip", "fHideIcons", "fAutoCheckSelect", "fIconsOnly", "fRestFlags" });
    }
}
