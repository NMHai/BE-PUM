/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.netapi32.functions
 * File name: DsRoleFreeMemory.java
 * Created date: 2017-05-08
 * Description:
 */
package v2.org.analysis.apihandle.winapi.netapi32.functions;

import com.sun.jna.Pointer;
import v2.org.analysis.apihandle.winapi.netapi32.Netapi32DLL;
import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;

/**
 * The DsRoleFreeMemory function frees memory allocated by the DsRoleGetPrimaryDomainInformation function.
 *
 * @param buffer Pointer to the buffer to be freed.
 *
 * @author YenNLH
 */
public class DsRoleFreeMemory extends WininetAPI {

    public DsRoleFreeMemory() {
        super();
        NUM_OF_PARMS = 1;
    }

    @Override
    public void execute() {
        long t1 = this.params.get(0);

        Pointer buffer = memory.getPointer(t1);

        Netapi32DLL.INSTANCE.DsRoleFreeMemory(buffer);
    }

}