package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class CONVINFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD cb;
    public DWORD_PTR hUser;
    public HANDLE hConvPartner;
    public HANDLE hszSvcPartner;
    public HANDLE hszServiceReq;
    public HANDLE hszTopic;
    public HANDLE hszItem;
    public UINT wFmt;
    public UINT wType;
    public UINT wStatus;
    public UINT wConvst;
    public UINT wLastError;
    public HANDLE hConvList;
    public CONVCONTEXT ConvCtxt;
    public HWND hwnd;
    public HWND hwndPartner;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cb", "hUser", "hConvPartner", "hszSvcPartner", "hszServiceReq", "hszTopic", "hszItem", "wFmt", "wType", "wStatus", "wConvst", "wLastError", "hConvList", "ConvCtxt", "hwnd", "hwndPartner" });
    }
}
