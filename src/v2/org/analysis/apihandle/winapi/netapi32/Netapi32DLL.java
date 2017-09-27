/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet
 * File name: WininetDLL.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.netapi32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author YenNLH
 */
public interface Netapi32DLL extends StdCallLibrary {
    Netapi32DLL INSTANCE = (Netapi32DLL) Native.loadLibrary("netapi32", Netapi32DLL.class);
    Netapi32DLL SYNC_INSTANCE = (Netapi32DLL) Native.synchronizedLibrary(INSTANCE);

    /**
     *
     * @param lpServer
     * @param InfoLevel
     * @param Buffer
     * @return
     */
    DWORD DsRoleGetPrimaryDomainInformation(Pointer lpServer, int InfoLevel, Pointer Buffer);

    /**
     * The DsRoleFreeMemory function frees memory allocated by the DsRoleGetPrimaryDomainInformation function.
     *
     * @param buffer Pointer to the buffer to be freed.
     */
    void DsRoleFreeMemory(/*_In_*/ Pointer buffer);
}
