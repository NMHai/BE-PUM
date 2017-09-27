package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WSAPROTOCOL_INFO extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD dwServiceFlags1;
    public DWORD dwServiceFlags2;
    public DWORD dwServiceFlags3;
    public DWORD dwServiceFlags4;
    public DWORD dwProviderFlags;
    public GUID ProviderId;
    public DWORD dwCatalogEntryId;
    public WSAPROTOCOLCHAIN ProtocolChain;
    public int iVersion;
    public int iAddressFamily;
    public int iMaxSockAddr;
    public int iMinSockAddr;
    public int iSocketType;
    public int iProtocol;
    public int iProtocolMaxOffset;
    public int iNetworkByteOrder;
    public int iSecurityScheme;
    public DWORD dwMessageSize;
    public DWORD dwProviderReserved;
    public char[] szProtocol = new char[256];

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "dwServiceFlags1", "dwServiceFlags2", "dwServiceFlags3", "dwServiceFlags4", "dwProviderFlags", "ProviderId", "dwCatalogEntryId", "ProtocolChain", "iVersion", "iAddressFamily", "iMaxSockAddr", "iMinSockAddr", "iSocketType", "iProtocol", "iProtocolMaxOffset", "iNetworkByteOrder", "iSecurityScheme", "dwMessageSize", "dwProviderReserved", "szProtocol" });
    }
}
